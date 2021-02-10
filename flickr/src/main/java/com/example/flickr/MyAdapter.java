package com.example.flickr;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    Vector<String> vector;

    @Override
    public int getCount() {
        return 0;
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
        Log.i("JFL", "TODO: ");
        return null;
    }

    public void dd(String url){
        vector.add(url);
    }
}
