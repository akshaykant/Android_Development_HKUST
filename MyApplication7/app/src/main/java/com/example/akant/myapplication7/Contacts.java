package com.example.akant.myapplication7;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    String[] names;

    ListView friendView;

    MyArrayAdapter myArrayAdapter;

    // This class stores all the information about your friend
    public class FriendInfo {
        int id;
        String name;
        String statusMsg;
    }

    List<FriendInfo> friendInfoList = null;

    String friendJsonString = "[" +
            "{" +
            "\"id\": 1," +
            "\"name\": \"John\"," +
            "\"statusMsg\": \"Imagine all the people ...\"" +
            "}," +
            "{" +
            "\"id\": 2," +
            "\"name\": \"Paul\"," +
            "\"statusMsg\": \"Let it be ...\"" +
            "}," +
            "{" +
            "\"id\": 3," +
            "\"name\": \"George\"," +
            "\"statusMsg\": \"Wait mister postman ...\"" +
            "}," +
            "{" +
            "\"id\": 4," +
            "\"name\": \"Ringo\"," +
            "\"statusMsg\": \"Yellow submarine ...\"" +
            "}" +
            "]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // convert the Json string into a list of objects
        processFriendInfo(friendJsonString);

        myArrayAdapter = new MyArrayAdapter(this, friendInfoList);

        // If you are using a ListView widget, then your activity should implement
        // the onItemClickListener. Then you should set the OnItemClickListener for
        // the ListView.
        friendView = (ListView) findViewById(R.id.friendListView);
        friendView.setAdapter(myArrayAdapter);
        friendView.setOnItemClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_contacts); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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

    // This class processes the Json string and converts it into a list of FriendInfo objects
    // We make use of the Gson library to do this automatically
    private void processFriendInfo(String infoString) {

        // Create a new Gson object
        // TODO Create a new Gson object
        /*Now, to process a JSON string, Android already has built in JSON string parsers called a JSON parser.
        You can make use of that, in order to parse the JSON string into its components, and then use them.
        But here, we are going to take the help of a very well established library from Google called the GSON library, GSON library.
        Now this allows you to take a JSON string and automatically construct Java objects from a JSON string.
        So it goes both ways, you can take a list of Java objects and then convert it into a JSON string, and you can take a JSON string
        and then load it into a list of objects.*/

        /*First, we need to create a GSON object. Now, if you are going to employ GSON in your application,
        first, you need to import the GSON library to be used within your Android application by goimng to build.gradle in dependencies and
        add "compile 'com.google.code.gson:gson:2.3'"*/

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();


        // Use the Gson library to automatically process the string and convert it into
        // the list of FriendInfo objects. The use of the library saves you the need for
        // writing your own code to process the Json string
        // TODO convert the string to a list of FriendInfo objects using Gson

        /*We creating an array list and then initializing the FriendInfo list variable that have been already declared earlier with this new array list.
        We are going to then take this array list, and then using the GSON object,  I'm going to convert the JSON string to an array of these FriendInfo objects.
        Now if you read the GSON documentation, then you would see that if you use this from JSON method of this GSON library,
        you can say gson.fromJson and the first parameter it takes is the JSON string. We have already acquired the JSON string, coming in as the parameter
        to this method, we'll supply that as the first one. The second parameter is an array list of FriendInfo objects. We have already seen how we declared the FriendInfo class earlier,
        so we are am going to supply that as the second parameter. So if I call this, and then say arrays as list, and then supply that,
        this is going to return an array list, which I am going to initialize my FriendInfo list with.
        So at the end of the execution of the statement, we will be left with an array list of FriendInfo objects.*/

        friendInfoList = new ArrayList<FriendInfo>();
        friendInfoList = Arrays.asList(gson.fromJson(infoString,FriendInfo[].class));
    }
}
