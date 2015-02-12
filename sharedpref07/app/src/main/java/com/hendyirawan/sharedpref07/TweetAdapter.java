package com.hendyirawan.sharedpref07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ceefour on 11/02/15.
 */
public class TweetAdapter extends ArrayAdapter<String> {

    private final LayoutInflater inflater;

    public TweetAdapter(Context context, String[] objects) {
        super(context, R.layout.row_tweet, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.row_tweet, parent, false);
        final TextView tvHeader = (TextView) view.findViewById(R.id.tv_header);
        tvHeader.setText( getItem(position) );
        return view;
    }
}
