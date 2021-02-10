package com.example.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new MyAdapter(getBaseContext());

        ListView listView = (ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);

        AsyncFlickrDl();
    }

    protected void AsyncFlickrDl(){
        new AsyncFlickrJSONDataForList(adapter).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
    }
}