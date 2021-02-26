package com.example.flickr;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import org.w3c.dom.Text;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    Vector<String> vector;
    Context context;

    public MyAdapter(Context context) {
        this.vector = new Vector<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.bitmaplayout, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Response.Listener<Bitmap> rep_listener = response -> {
            imageView.setImageBitmap(response);
        };

        ImageRequest request = new ImageRequest(vector.get(position), rep_listener, 0, 0, ImageView.ScaleType.CENTER_CROP, null, null);

        MySingleton.getInstance(parent.getContext()).addToRequestQueue(request);

        return convertView;
    }

    public void add(String url){
        vector.add(url);
        Log.i("JFL", "Adding to adapter url : " + url);
    }
}