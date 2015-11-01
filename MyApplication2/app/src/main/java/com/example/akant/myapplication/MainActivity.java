package com.example.akant.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends ListActivity {

    String [] names;

    /*We use TAG to output some log messages on the screen. So using log messages,
     we are able to type out things into the log window so that we can track the progress of our application as it is executing on the device.*/
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Construct a string array from the String Array resource "friends" in the strings.xml file*/
        names = getResources().getStringArray(R.array.friends);

        /*we need to specify that this list of names should be displayed in the ListView that is going to be displayed on the user
        interface for this application. So to do that, we will say, setListAdapter.
        And we need to supply an ArrayAdapter here as the parameter for this ListAdapter.
        So to supply the parameter here-- so we'll say new ArrayAdapter.
        So this is the standard ArrayAdapter that is part of what Android supplies.
        And ArrayAdapter takes a string, and then converts it into a form that the ListView within Android.

        can make use of when it is constructing the list of items to be displayed on the screen.*/
        /*An ArrayAdapter is of the type string type. And the three parameters for this ArrayAdapter construction
        is this, which is this activity as the context. Then the second parameter is the layout file.
        So we would say, R.layout.friend_item. So basically, we are specifying here that each of those names
        should be displayed on the screen using this layout as the method of constructing the layout on the screen.
        And the third part is the actual array of strings that we are going to supply to this ArrayAdapter. So this is names.*/

        /*This method call sets the names string array as the source of the names
        for the list of items in the listview. The ArrayAdapter object is used
        to adapt the string array and construct a list of layout items that are
        then used by the ListView of the ListActivity to construct the list of friends.
        The layout of each item is specified by the friend_item.xml file*/
        setListAdapter( new ArrayAdapter<String>(this,R.layout.friend_item, names));

        Log.i(TAG, "in onCreate()");
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
    /*The parameter "id" indicates the index of the item that was selected from
    the list of friends. This is used to index into the names[] array to get
    the name of the friend selected. Rest of the code is similar to the earlier
    exercise.*/
    /*the long ID specifies which of the items in the list was clicked.*/
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

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

        in.putExtra("message",names[(int) id]);

        /*to start the another activity we are supposed to say startActivity() and supply the intent.*/
        /*We start the new activity by calling this method to inform the Android framework to start
          the new activity. The parameter is the Intent we just created earlier*/
        startActivity(in);

    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "in onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(TAG, "in onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "in onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "in onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "in onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "in onDestroy()");
    }
}
