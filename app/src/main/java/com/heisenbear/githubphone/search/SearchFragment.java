package com.heisenbear.githubphone.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.heisenbear.githubphone.R;
import com.heisenbear.githubphone.api.Api;
import com.heisenbear.githubphone.repo.RepoItemAdapter;
import com.heisenbear.githubphone.user.UserItemAdapter;

import static com.heisenbear.githubphone.search.SearchLister.Filter.REPOS;
import static com.heisenbear.githubphone.search.SearchLister.Filter.USERS;

public class SearchFragment extends Fragment {


    private Button buttonFilterRepos;
    private Button buttonFilterUsers;
    private EditText searchText;
    private Button searchButton;
    private ListView searchList;
    private SearchLister.Filter activeFilter;


    public static SearchFragment newInstance() {
        return new SearchFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        initializeView(rootView);

        return rootView;
    }

    private void initializeView(View rootView) {
        initializeFilterButtons(rootView);
        searchText = (EditText) rootView.findViewById(R.id.searchText);
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new searchTask());

        searchList = (ListView) rootView.findViewById(R.id.searchResults);

    }

    private void initializeFilterButtons(View rootView) {
        buttonFilterRepos = (Button) rootView.findViewById(R.id.buttonFilterRepo);
        buttonFilterRepos.setSelected(true);
        activeFilter = REPOS;
        buttonFilterRepos.setOnClickListener(new SearchFilter(REPOS));

        buttonFilterUsers = (Button) rootView.findViewById(R.id.buttonFilterUsers);
        buttonFilterUsers.setOnClickListener(new SearchFilter(USERS));

    }

    private void unselectFilterButtons() {
        buttonFilterRepos.setSelected(false);
        buttonFilterUsers.setSelected(false);
    }

    private class SearchFilter implements View.OnClickListener {

        private SearchLister.Filter filter;

        public SearchFilter(SearchLister.Filter filter) {
            this.filter = filter;
        }

        @Override
        public void onClick(View view) {
            unselectFilterButtons();
            view.setSelected(true);
            activeFilter = filter;
        }
    }


    private class searchTask implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(searchText.getText().toString().matches(""))
            {
                Toast toast = Toast.makeText(getContext(), "Please Type something in the searchBar before hitting the searchButton", Toast.LENGTH_LONG);
                toast.show();
            }
            else
            {
                //api search request here
                if(activeFilter == REPOS)
                {
                    Api api = new Api("search/repositories?q="+searchText.getText().toString());
                    api.callApi();
                    searchList.setAdapter(new RepoItemAdapter(getContext(), api.getRepos()));
                    Toast toast = Toast.makeText(getContext(), api.getJsonString() , Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Api api = new Api("search/users?q="+searchText.getText().toString());
                    api.callApi();
                    searchList.setAdapter(new UserItemAdapter(getActivity(), api.getUsers()));
                }
            }
        }
    }
}
