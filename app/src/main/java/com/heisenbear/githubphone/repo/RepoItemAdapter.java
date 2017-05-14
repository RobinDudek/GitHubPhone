package com.heisenbear.githubphone.repo;


import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heisenbear.githubphone.R;

/**
 * Created by rorod on 07/04/2017.
 */

public class RepoItemAdapter extends BaseAdapter {
    private ArrayList<Repo> repos;
    private LayoutInflater repoInf;

    //constructor
    public RepoItemAdapter(Context c, ArrayList<Repo> repos){
        this.repos=repos;
        repoInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return repos.size();
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

        RelativeLayout searchLay = (RelativeLayout)repoInf.inflate
                (R.layout.repo_item, parent, false);

        TextView nameView = (TextView)searchLay.findViewById(R.id.name);
        TextView ownerView = (TextView)searchLay.findViewById(R.id.owner);
        TextView languageView = (TextView)searchLay.findViewById(R.id.language);

        Repo currRepo = repos.get(position);
        if(position%2 == 1)
        {
            searchLay.setBackgroundColor(Color.argb(175, 175, 175, 255));
        }

        nameView.setText(currRepo.getName());
        ownerView.setText(currRepo.getOwner());
        languageView.setText(currRepo.getLanguage());
        //set position as tag
        searchLay.setTag(position);
        return searchLay;
    }

}