package com.example.akant.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button greetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a reference to the greetButton on the UI
        greetButton = (Button) findViewById(R.id.greetButton);
        // Set the onClickListener for the greetButton to be this class.
        // This requires that the class implement the View.OnClickListener callback
        // the onClick() method
        greetButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {


        //get a reference to the EditText so that we can read in the value typed
        // by the user
        EditText editFriendName = (EditText) findViewById(R.id.editFriendName);

        // get the name of the friend typed in by the user in the EditText field
        String friendName = editFriendName.getText().toString();

        // TODO 1
        // Add the code to display the appropriate greeting message. Your output message should be exactly the same as the one you see on the video with exactly the same number of spaces in the same positions.
        // The code from the previous exercise is commented and kept below for your reference
        switch (v.getId()) {

            case R.id.greetButton:

                /*An intent is a message-passing mechanism that is built into Android that enables you to pass messages
                from one component to another component through the Android framework.*/
                /*create a new intent. The first parameter is the Context which is the current Activity.
                Hence we use "this". The second parameter is the Activity class that we wish to start.
                Hence it is specified as ShowMessage.class*/
                Intent in =  new Intent(this,ShowMessage.class);

                /*In order to pass the string from the current activity to the next activity, we're going to take the help of a method called as putExtra
                that is part of the intent class.*/
                /*Add the message as a payload to the Intent. We add data to be carried by the intern using
                the putExtra() methods. The data is specified as a key-value pair. The first parameter is
                the key, specified as a string, and the second parameter is the value.*/
                in.putExtra("message",friendName);

                /*to start the another activity we are supposed to say startActivity() and supply the intent.*/
                /*We start the new activity by calling this method to inform the Android framework to start
                the new activity. The parameter is the Intent we just created earlier*/
                startActivity(in);

                break;

            default:
                break;
        }

    }
}
