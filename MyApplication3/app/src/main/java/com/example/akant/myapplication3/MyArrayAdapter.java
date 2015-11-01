package com.example.akant.myapplication3;

/**
 * Created by akant on 8/27/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/* ArrayAdapters enable us to take the data and then construct views from the data.*/
public class MyArrayAdapter extends ArrayAdapter<Message> {
    private final Context context;
    private final ArrayList<Message> messages;

    public MyArrayAdapter(Context context, ArrayList<Message> messages) {
        super(context, R.layout.message, messages);
        this.context = context;
        this.messages = messages;
    }

    // This method constructs the ListItem's view from the data obtained
    // from the Message object. This will be called by ListView while it
    // is being drawn.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*create a variable called as MessageView,
        then we get access to the Layoutinflater that is available in Android.*/
        View messageView;

        /*Get a reference to the LayoutInflater. This helps construct the view from the layout file*/
        /*using this inflater, we can then go ahead and construct our view using this inflater.*/
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        /*inflate the MessageView from the message.XML file, so we are constructing this MessageView by using the message layout.
        The second one is the ViewGroup within which it resides, which is the parent, and finally, we'll say false.
        So this statement enables us to inflate the MessageView.*/
        messageView = inflater.inflate(R.layout.message, parent, false);

        /*Get the reference to the two TextViews in the message layout and set them to the time and message string respectively*/
        /*to initialize the various TextViews inside this message, we need references to these TextViews, */
        TextView msgView = (TextView) messageView.findViewById(R.id.messageTextView);

        /*we need to set this messageView to the message strict. And set the message by finding the message at that particular position for which we
        are currently inflating the view, and then call the method getMessage().*/
        msgView.setText(messages.get(position).getMessage());

        /*Now we need to initialise the time view and then set the view at the particular position and then call the method getTime().*/
        TextView timeView = (TextView) messageView.findViewById(R.id.timeTextView);
        timeView.setText(messages.get(position).getTime());

        /*Now once we have initialized the two, TextViews and the MessageView,
        we can now go ahead and then return this MessageView from this method.*/
        return messageView;


    }
}