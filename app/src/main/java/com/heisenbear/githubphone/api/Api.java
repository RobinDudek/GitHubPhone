package com.heisenbear.githubphone.api;

import com.heisenbear.githubphone.repo.Repo;
import com.heisenbear.githubphone.search.Item;
import com.heisenbear.githubphone.search.SearchLister;
import com.heisenbear.githubphone.user.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rorod on 13/05/2017.
 */

public class Api {

    //Les données pour le oauth2
    private String clientId = "fda9af5f1b8aa3a9aba7";
    private String clientSecret = "9615fcc5dfbc56eae8970c8b420cf3b95c5972cb";
    private JSONObject json;
    private JSONArray items;
    private String jsonString;
    private String resourceUrl;

    public Api(String requestUrl) {
        this.resourceUrl = "https://api.github.com/" + requestUrl;
    }


    public JSONObject getJson() {
        return json;
    }

    public String getJsonString() {
        return jsonString;
    }

    public ArrayList<Repo> getRepos() {

        ArrayList<Repo> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray)items;
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i=0;i<len;i++){

                    JSONObject json = jsonArray.getJSONObject(i);
                    Repo repo = new Repo();
                    repo.setName(json.getString("name"));
                    repo.setOwner(json.getJSONObject("owner").getString("login"));
                    repo.setLanguage(json.getString("language"));
                    list.add(repo);

                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;

    }
    public ArrayList<User> getUsers() {

        ArrayList<User> list = new ArrayList<>();
        JSONArray jsonArray = (JSONArray)items;
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i=0;i<len;i++){

                    JSONObject json = jsonArray.getJSONObject(i);
                    User user = new User();
                    user.setName(json.getString("login"));
                    user.setAvatar(json.getString("avatar_url"));

                    Api api = new Api("users/" + user.getName());
                    api.callApi();
                    JSONObject userJson = api.getJson();

                    user.setLocation(userJson.getString("location"));

                    list.add(user);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;

    }

    public void callApi()
    {
        try {
            json = new CallApiTask().execute(resourceUrl).get();
            jsonString = json.toString();
            items = json.getJSONArray("items");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
