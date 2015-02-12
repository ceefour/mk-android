package com.hendyirawan.localstorage09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hendyirawan.localstorage09.models.Tweet;

/**
 * Created by ceefour on 11/02/15.
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

    private final LayoutInflater inflater;

    public TweetAdapter(Context context, Tweet[] objects) {
        super(context, R.layout.row_tweet, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.row_tweet, parent, false);
        final TextView tvHeader = (TextView) view.findViewById(R.id.tv_header);
        final TextView tvBody = (TextView) view.findViewById(R.id.tv_body);
        final Tweet tweet = getItem(position);
        tvHeader.setText( tweet.getTitle() );
        tvBody.setText( tweet.getBody() );
        return view;
    }
}
