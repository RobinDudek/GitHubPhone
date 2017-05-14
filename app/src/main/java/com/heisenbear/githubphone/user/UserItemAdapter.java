package com.heisenbear.githubphone.user;


import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heisenbear.githubphone.R;

/**
 * Created by rorod on 07/04/2017.
 */

public class UserItemAdapter extends BaseAdapter {
    private ArrayList<User> users;
    private LayoutInflater userInf;

    //constructor
    public UserItemAdapter(Context c, ArrayList<User> users){
        this.users=users;
        userInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        RelativeLayout searchLay = (RelativeLayout)userInf.inflate
                (R.layout.user_item, parent, false);

        ImageView avatarView = (ImageView)searchLay.findViewById(R.id.avatar);
        TextView pseudoView = (TextView)searchLay.findViewById(R.id.pseudo);
        TextView locationView = (TextView)searchLay.findViewById(R.id.location);

        User currUser = users.get(position);

        if(position%2 == 1) {
            searchLay.setBackgroundColor(Color.argb(175, 175, 175, 255));
        }


        pseudoView.setText(currUser.getName());
        locationView.setText(currUser.getLocation());

        searchLay.setTag(position);
        return searchLay;
    }

}