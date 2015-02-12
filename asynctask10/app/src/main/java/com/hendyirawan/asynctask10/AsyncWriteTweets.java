package com.hendyirawan.asynctask10;

import android.os.AsyncTask;
import android.util.Log;

import com.hendyirawan.asynctask10.models.Tweet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by ceefour on 2/12/15.
 */
public class AsyncWriteTweets extends AsyncTask<Void, Void, Void> {

    private final TweetListActivity activity;
    private final List<Tweet> tweets;

    public AsyncWriteTweets(TweetListActivity activity, List<Tweet> tweets) {
        this.activity = activity;
        this.tweets = tweets;
    }

    @Override
    protected Void doInBackground(Void... params) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try (FileOutputStream out = activity.openFileOutput(TweetListActivity.TWEET_CACHE_FILENAME, TweetListActivity.MODE_PRIVATE)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(tweets);
            }
            Log.i(AsyncWriteTweets.class.getSimpleName(), "Successfully written " + tweets.size() + " tweets to " + TweetListActivity.TWEET_CACHE_FILENAME);
        } catch (Exception e) {
            Log.e(AsyncWriteTweets.class.getSimpleName(), "Cannot open file " + TweetListActivity.TWEET_CACHE_FILENAME + ": " + e);
        }

        return null;
    }

}
