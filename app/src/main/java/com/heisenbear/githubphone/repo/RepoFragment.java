package com.heisenbear.githubphone.repo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.heisenbear.githubphone.R;

public class RepoFragment extends Fragment {

    public static RepoFragment newInstance() {
        return new RepoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.repo_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                //make the project fav
                if (item.getIcon().getConstantState().equals(getResources()
                        .getDrawable(R.drawable.star_empty).getConstantState())
                        )
                {
                    item.setIcon(R.drawable.star);
                    //here set the fav state on the project
                }
                else
                {
                    item.setIcon(R.drawable.star_empty);
                    //here set the unfav state on the project
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo, container, false);
    }
}
