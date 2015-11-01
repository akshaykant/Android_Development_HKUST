package com.example.akant.myapplication8;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG="Contacts";

    Toolbar toolbar;
    String[] names;

    ListView friendView;

    MyArrayAdapter myArrayAdapter = null;

    public class FriendInfo {
        int id;
        String name;
        String statusMsg;
        String imageURL;
    }

    List<FriendInfo> friendInfoList = null;

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mContext = this;

        // If you are using a ListView widget, then your activity should implement
        // the onItemClickListener. Then you should set the OnItemClickListener for
        // the ListView.
        friendView = (ListView) findViewById(R.id.friendListView);
        friendView.setOnItemClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_contacts); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        // Start the AsyncTask to process the Json string in the background and then initialize the listview

        // TODO create Asynctask for doing the background processing
        FriendsProcessor mytask = new FriendsProcessor();
        /*when you call execute, you are essentially issuing a call to the Android framework.
        At that point, Android framework is going to take over and then call the methods that you
        implement in the AsyncTask in a particular order in order to get your work done.*/
        mytask.execute("friendsjson.txt");
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

    // This AsyncTask processes the Json string by reading it from a file in the assets folder and
    // then converts the string into a list of FriendInfo objects. You will also see the use of
    // a progress dialog to show that work is being processed in the background.

    private class FriendsProcessor extends AsyncTask<String, Void, Integer> {

        ProgressDialog progressDialog;
        int delay = 5000 ; // ms

        public FriendsProcessor() {
            super();
        }

        // The onPreExecute is executed on the main UI thread before background processing is
        // started. In this method, we start the progressdialog.
        /*The code inside the onPreExecute method is going to be executed on the main UI thread.*/
        /*in Android you can never modify the user interface from any thread other than the main UI thread.
        So if you're doing work in the background, the background thread can not directly modify the user interface.
        So that's the reason why we have the onPreExecute, so before you start you want to do something on the main user interface
        before you move on into the background processing stage.*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show the progress dialog on the screen
            progressDialog = ProgressDialog.show(mContext, "Wait!","Downloading Friends List");
        }

        // This method is executed in the background and will return a result to onPostExecute
        // method. It receives the file name as input parameter.
        @Override
        protected Integer doInBackground(String... strings) {

            // Open an input stream to read the file
            InputStream inputStream;
            BufferedReader in;

            // this try/catch is used to create a simulated delay for doing the background
            // processing so that you can see the progress dialog on the screen. If the
            // data to be processed is large, then you don't need this.
            try {
                // Pretend downloading takes a long time
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Now we read the file, line by line and construct the
            // Json string from the information read in.
            try {

                // TODO read the file and process the string
                /*This string[0] essentially is the set of strings that got delivered as parameters for the doInBackground.
                In this case, since we passed only one string, which is the name of the file, we only have one single parameter coming in,
                and that is accessed by specifying it as string[0].*/
                inputStream = mContext.getAssets().open(strings[0]);
                in = new BufferedReader( new InputStreamReader(inputStream));

                String readLine;
                StringBuffer buf = new StringBuffer();

                while((readLine = in.readLine()) != null){
                    buf.append(readLine);
                }

                //Convert the read  in information  to a JSON  string.
                String infoString = buf.toString();

                //now process the string  using  the method  that we  implemented in the previous exercise.
                processFriendInfo(infoString);

                if(null != in){
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return (Integer) 1;
        }

        // This method will be executed on the main UI thread and can access the UI and update
        // the listview. We dismiss the progress dialog after updating the listview.
        /*So when we come into this method, we are now initializing the array adapter. And then setting the adapter for my ListView to be this array adapter.
        And then we are dismissing the progress dialog. So at this point, the ListView has been rendered, and then we can dismiss the ProgressDialog,
        so that the user can be returned to show the list of friends.*/
        /*If you need to cancel the method, you will call the tasksCancelled method. That'll be a call into the Android framework.
        At the point, the Android framework will come in and call the onCancelled method.
        In here, you can do something to handle the fact that your background processing has been suddenly canceled by the user.*/
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            myArrayAdapter = new MyArrayAdapter(mContext, friendInfoList);
            friendView.setAdapter((ListAdapter) myArrayAdapter);

            progressDialog.dismiss();
        }

        // This method is called if we cancel the background processing
        /*So if you're AsyncTask is doing some work in the background and you want to cancel that work, then the Android framework
        can call the onCancelled method, and then essentially stop the background processing on your behalf.*/
        @Override
        protected void onCancelled() {
            super.onCancelled();

            progressDialog.dismiss();
        }
    }

    // This class processes the Json string and converts it into a list of FriendInfo objects
    // We make use of the Gson library to do this automatically
    private void processFriendInfo(String infoString) {

        // Create a new Gson object
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        // Use the Gson library to automatically process the string and convert it into
        // the list of FriendInfo objects. The use of the library saves you the need for
        // writing your own code to process the Json string
        friendInfoList = new ArrayList<FriendInfo>();
        friendInfoList = Arrays.asList(gson.fromJson(infoString, FriendInfo[].class));
    }
}
