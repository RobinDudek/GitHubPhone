package com.heisenbear.githubphone.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.heisenbear.githubphone.R;

import static com.heisenbear.githubphone.user.UserLister.Filter.PROFILE;
import static com.heisenbear.githubphone.user.UserLister.Filter.REPOS;
import static com.heisenbear.githubphone.user.UserLister.Filter.TEAMS;


public class UserFragment extends Fragment {
    private Button buttonProfile;
    private Button buttonRepos;
    private Button buttonTeams;
    private ListView userList;
    private UserLister.Filter activeFilter;


    public static UserFragment newInstance() {
        return new UserFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        initializeView(rootView);
        return rootView;
    }

    private void initializeView(View rootView) {
        initializeFilterButtons(rootView);

        userList = (ListView) rootView.findViewById(R.id.searchResults);

    }

    private void initializeFilterButtons(View rootView) {
        buttonProfile = (Button) rootView.findViewById(R.id.buttonProfile);
        buttonProfile.setSelected(true);
        activeFilter = PROFILE;
        buttonProfile.setOnClickListener(new UserFilter(PROFILE));

        buttonRepos = (Button) rootView.findViewById(R.id.buttonRepos);
        buttonRepos.setOnClickListener(new UserFilter(REPOS));

        buttonTeams = (Button) rootView.findViewById(R.id.buttonTeams);
        buttonTeams.setOnClickListener(new UserFilter(TEAMS));

    }

    private void unselectFilterButtons() {
        buttonProfile.setSelected(false);
        buttonRepos.setSelected(false);
        buttonTeams.setSelected(false);
    }

    private class UserFilter implements View.OnClickListener {

        private UserLister.Filter filter;

        public UserFilter(UserLister.Filter filter) {
            this.filter = filter;
        }

        @Override
        public void onClick(View view) {
            unselectFilterButtons();
            view.setSelected(true);
            activeFilter = filter;
        }
    }
}
