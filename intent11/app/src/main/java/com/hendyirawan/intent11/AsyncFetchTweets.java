package com.hendyirawan.intent11;

import android.os.AsyncTask;
import android.util.Log;

import com.hendyirawan.intent11.models.Tweet;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by ceefour on 2/12/15.
 */
public class AsyncFetchTweets extends AsyncTask<Void, Void, Void> {

    protected final String[] prefixes = new String[] { "Car", "Bird", "Elephant", "Phone", "Building" };

    private final TweetListActivity activity;
    private final List<Tweet> tweets;

    public AsyncFetchTweets(TweetListActivity activity, List<Tweet> tweets) {
        this.activity = activity;
        this.tweets = tweets;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            final String name = prefixes[rand.nextInt(prefixes.length)] + ' ' + (rand.nextInt(10) + 1);
            final Tweet tweet = new Tweet((long) i, name, "Some random body for " + name, new Date());
//            tweetsAdapter.insert(tweet, 0);
            tweets.add(0, tweet);
        }
        Log.i(AsyncFetchTweets.class.getSimpleName(), "Prepended random tweets to list, now: " + tweets.size());

//        final ArrayAdapter<String> tweetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                names.toArray(new String[]{}));
        new AsyncWriteTweets(activity, tweets).execute();

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i(AsyncFetchTweets.class.getSimpleName(), "Updated tweets, now: " + tweets.size());
        activity.renderTweets();
    }
}
