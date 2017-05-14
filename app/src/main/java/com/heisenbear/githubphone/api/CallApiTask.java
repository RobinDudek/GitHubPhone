package com.heisenbear.githubphone.api;

import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rorod on 14/05/2017.
 */
class CallApiTask extends AsyncTask<String, Void, JSONObject>
{
    private Exception e;

    protected JSONObject doInBackground(String...urls) {

        String resourceUrl = urls[0];

        JSONObject json = null;

        StringBuilder inputRequest = null;
        InputStream inputStream;
        try {
            HttpURLConnection request = (HttpURLConnection)new URL(resourceUrl).openConnection();

            request.setRequestMethod("GET");
            request.setDoInput(true);
            request.connect();


            int status = request.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    boolean apiLimitExceeded = false;
                    if(sb.toString().contains("API rate limit exceeded")){
                        apiLimitExceeded =true;
                    }else {
                        json = (JSONObject) new JSONTokener(sb.toString()).nextValue();
                    }

                    br.close();
                    request.disconnect();
                    inputRequest = sb;
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    protected void onPostExecute(JSONObject json) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

}