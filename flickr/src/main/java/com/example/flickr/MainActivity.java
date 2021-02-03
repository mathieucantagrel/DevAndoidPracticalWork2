package com.example.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button getImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.getImageButton).setOnClickListener(new GetImageOnClickLister());
    }

    public class GetImageOnClickLister implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new AsyncFlickrJSONData().execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
        }
    }
}