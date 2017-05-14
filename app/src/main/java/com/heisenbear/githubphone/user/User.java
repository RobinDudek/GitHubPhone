package com.heisenbear.githubphone.user;

/**
 * Created by rorod on 13/05/2017.
 */

public class User {
    private String avatar;
    private String name = "No Name";
    private String location = "Somewhere";

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == "null")
        {
            name = "Someone";
        }
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(location == "null")
        {
            location = "Somewhere";
        }
        this.location = location;
    }
}
