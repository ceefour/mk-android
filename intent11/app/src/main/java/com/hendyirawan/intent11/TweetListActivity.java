package com.hendyirawan.intent11;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.hendyirawan.intent11.models.Tweet;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends ListActivity {

    public static final String TWEET_CACHE_FILENAME = "tweet_cache.ser";
    private TweetAdapter tweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        List<Tweet> tweets = new ArrayList<>();

        // read cached tweets (if any)
        try (FileInputStream ins = openFileInput(TWEET_CACHE_FILENAME)) {
            try (ObjectInputStream ois = new ObjectInputStream(ins)) {
                tweets = (List<Tweet>) ois.readObject();
                Log.i(TweetListActivity.class.getSimpleName(), "Read " + tweets.size() + " tweets from " + TWEET_CACHE_FILENAME);
            }
        } catch (Exception e) {
            Log.i(TweetListActivity.class.getSimpleName(), "Cached file " + TWEET_CACHE_FILENAME + " cannot be read: " + e);
        }

        final ListView lvTweet = getListView();
        tweetsAdapter = new TweetAdapter(this, tweets);
        lvTweet.setAdapter(tweetsAdapter);

        new AsyncFetchTweets(this, tweets).execute();
    }

    public void renderTweets() {
        tweetsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        final TextView itemBody = (TextView) v.findViewById(R.id.tv_body);
//        itemBody.setText("I was clicked!!");
        final Tweet tweet = tweetsAdapter.getItem(position);
        final Intent intent = new Intent(this, TweetDetailActivity.class);
        intent.putExtra("tweet", tweet);
        startActivity(intent);
    }
}
