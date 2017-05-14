package com.heisenbear.githubphone.user;

import android.content.Context;

public class UserLister {
    public enum Filter {
        PROFILE, REPOS, TEAMS, NO
    }

    private final Context context;

    public UserLister(Context context) {
        this.context = context;
    }

}
