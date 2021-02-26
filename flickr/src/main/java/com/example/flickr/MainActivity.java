package com.example.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button getImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.getImageButton).setOnClickListener(new GetImageOnClickLister());

        findViewById(R.id.button).setOnClickListener(v->{
            Intent ListActivity = new Intent(this, com.example.flickr.ListActivity.class);
            startActivity(ListActivity);
        });
    }

    public class GetImageOnClickLister implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject jsonObject;
                    String link = null;

                    try {
                        //getting the json file from the flickr API
                        jsonObject = new AsyncFlickrJSONData().execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json").get();
                        link = jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("media").getString("m"); //parsing the json to get the link of the image
                    } catch (ExecutionException | JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //modifying the view with the new image
                    ImageView im = findViewById(R.id.image);
                    AsyncTask<String, Void, Bitmap> as = new AsyncBitmapDownloader().execute(link);
                    try {
                        im.setImageBitmap(as.get());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

}