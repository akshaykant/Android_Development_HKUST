package com.example.akant.myapplication3;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class ChatClient extends Activity implements View.OnClickListener {

    Button sendButton;
    EditText messageText;
    ListView messageList;
    MyArrayAdapter mAdapter = null;
    ArrayList<Message> messages = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_client);

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        messageText = (EditText) findViewById(R.id.messageText);

        /*First,we need a reference to the ListView,
        then create the messages ArrayList,
        then create an adapter from the message ArrayList using the MyArrayAdapter
        class that was implemented earlier.
        Then finally, initialize the adapter for my ListView
        to be equal to the adapter which is just created.*/

        messageList = (ListView) findViewById(R.id.messageList);

        /*Create the ArrayList of the type Message. */
        messages = new ArrayList<Message>();

        /*use this messages ArrayList to construct the adapter in order to initialize the ListView.*/
        mAdapter = new MyArrayAdapter(this, messages);

        /*use this adapter to initialize the ListView and then cast the mAdapter as the ListAdapter..*/
        messageList.setAdapter((ListAdapter) mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sendButton:

                String messString = messageText.getText().toString();

                // If the message is not empty string
                if (!messString.equals("")) {

                    /*create a new message object and then initialize it with the appropriate values.
                    So since this message is created by the user himself or herself, so the first parameter is set to blank.
                    The second parameter is the actual message, so that'll be set to the messString.
                    The third parameter indicates whether this messages is from the user
                    or from the other person with whom you're communicating.
                    Since this message has been created by the user, we'll set the value to true.
                    And then finally the last parameter sets the date value so the time stamp.*/
                    Message message = new Message("",messString, true, new Date());

                    // Add the Message object to the ArrayList
                    messages.add(message);

                    /*Notify the adapter that the data has changed due to the addition of a new message object. This triggers an update of the ListView*/

                    /*the third thing that is needed to be done is to indicate to the adapter
                    that a new message has been added, so the user interface needs to be updated.
                    So calling this method called notifyDataSetChanged
                    causes the ListView to read in the updated adapter
                    and then refresh the ListView so that the new message now becomes part of the screen.*/
                    mAdapter.notifyDataSetChanged();

                    /*Finally, release the message. Object, it is no longer needed, and then also set the message text to blank.*/
                    message = null;
                    messageText.setText("");


                }

                break;

            default:
                break;
        }
    }
}