package com.heisenbear.githubphone.search;

import android.content.Context;

/**
 * Created by rorod on 14/05/2017.
 */

public class SearchLister {
    public enum Filter {
        REPOS, USERS, NO
    }

    private final Context context;

    public SearchLister(Context context) {
        this.context = context;
    }

}
