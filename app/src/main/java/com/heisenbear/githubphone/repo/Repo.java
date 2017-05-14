package com.heisenbear.githubphone.repo;

/**
 * Created by rorod on 13/05/2017.
 */

public class Repo {
    private String name = "Something";
    private String owner = "Someone";
    private String language = "Unknown Language";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == "null")
        {
            name = "Something";
        }
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if(owner == "null")
        {
            owner = "Someone";
        }
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {

        if(language == "null")
        {
            language = "Unknown";
        }
        this.language = language;
    }
}
