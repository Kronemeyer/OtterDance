package com.example.utmobile.ui.weather;

import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPSTrackerActivity extends AsyncTask<String, Void, JSONObject> {
    Exception mException = null;
    private double longitude = -122.1438405;
    private double latitude = 37.401245;
    private String SECRET_API_KEY = "bff97e56087b0a6314c03c2a18392040";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.mException = null;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://api.openweathermap.org/data/2.5/weather?");
        urlString.append("lat=").append(latitude);
        urlString.append("&lon=").append(longitude);
        urlString.append("&units=imperial");
        urlString.append("&appid=").append(SECRET_API_KEY);


        HttpURLConnection urlConnection = null;
        URL url = null;
        JSONObject object = null;
        InputStream inStream = null;


        try {
            url = new URL(urlString.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp = "";
            StringBuilder response = new StringBuilder();
            while ((temp = bReader.readLine()) != null)
                response.append(temp);
            bReader.close();
            inStream.close();
            urlConnection.disconnect();
            object = (JSONObject) new JSONTokener(response.toString()).nextValue();
        } catch (Exception e) {
            this.mException = e;
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            return (object);
        }

    }
}