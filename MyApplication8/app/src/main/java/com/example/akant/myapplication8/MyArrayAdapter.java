package com.example.akant.myapplication8;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by muppala on 4/5/15.
 */
public class MyArrayAdapter extends ArrayAdapter<Contacts.FriendInfo> {
    private final Context context;
    private final List<Contacts.FriendInfo> friendInfoList;

    public MyArrayAdapter(Context context, List<Contacts.FriendInfo> friendInfoList) {
        super(context, R.layout.friend_item, friendInfoList);
        this.context = context;
        this.friendInfoList = friendInfoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View friendInfoView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Change the layout based on who the message is from
        friendInfoView = inflater.inflate(R.layout.friend_item, parent, false);
        TextView friendName = (TextView) friendInfoView.findViewById(R.id.friendName);
        friendName.setText(friendInfoList.get(position).name);
        TextView statusMsg = (TextView) friendInfoView.findViewById(R.id.statusMsg);
        statusMsg.setText(friendInfoList.get(position).statusMsg);

        // This set of steps are used to load the friend's picture into the ImageView. We take
        // the help of the Picasso image downloading library to do this for us asynchronously
        // TODO load the image in the background asynchronously using Picasso library
        /* need to download an image, either read in from the Assets folder or download the image from a server, this is going to take some time.
        Now, if we end up doing this in this method, then it is going to literally freeze your user interface at this point.
        So we want this download of the image to be done in the background again. And then when the image is downloaded in the background,
        then we want the ImageView to be updated. Now, you can also employ an AsyncTask to do that at this point, and offload that work to AsyncTask.
        That's the natural instinct that you will have at this point. Fortunately for us, there is another library available
        for us called as the Picasso library, which does all this work for you in the background.
        All that you need to specify is the URL of the image from where you need to download and specify
        the ImageView that needs to be updated when that URL is downloaded. And the Picasso library takes all this work into the background,
        and then does the processing asynchronously, and then your UI will be updated behind the scenes when the image has been downloaded.*/

        ImageView imageview = (ImageView) friendInfoView.findViewById(R.id.avatar);

        /*So when we call Picasso library and hand over all this information to it, then Picasso library's code will takeover at that point
        and then do this work in the background. And then when the image has been downloaded, then the ImageView will be automatically updated asynchronously at this point.*/
        Picasso.with(context).load("file:///android_asset/"+friendInfoList.get(position).imageURL).into(imageview);


        return friendInfoView;
    }
}
