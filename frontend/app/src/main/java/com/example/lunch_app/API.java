package com.example.lunch_app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API extends Activity {
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);

        textView = findViewById(R.id.textView);

        new APITask().execute("https://jsonplaceholder.typicode.com/posts/1");
    }

    private class APITask extends AsyncTask<String, Void, String>{
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls){
            String url = urls[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String responseData){
            if (responseData != null){
                //textView.setText(responseData);
                try{
                    JSONObject responseJson = new JSONObject(responseData);
                    String title = responseJson.getString("userId");
                    textView.setText(title);
                }
                catch (JSONException e){
                    e.printStackTrace();
                    textView.setText("Error parsing data");
                }
            }
            else{
               textView.setText("Error fetching data");
            }
        }
    }
}
