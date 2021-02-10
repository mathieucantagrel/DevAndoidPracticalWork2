package com.example.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MyAdapter adapter = new MyAdapter();

        ListView listView = (ListView) findViewById(R.id.List);
        listView.setAdapter(adapter);
    }
}