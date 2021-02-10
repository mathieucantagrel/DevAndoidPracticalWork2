package com.example.flickr;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            convertView = inflater.inflate(R.layout.text_view_layout, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(vector.get(position));

        return convertView;
    }

    public void add(String url){
        vector.add(url);
        Log.i("JFL", "Adding to adapter url : " + url);
    }
}