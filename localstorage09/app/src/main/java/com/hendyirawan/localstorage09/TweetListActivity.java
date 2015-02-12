package com.hendyirawan.localstorage09;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.hendyirawan.localstorage09.models.Tweet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TweetListActivity extends ListActivity {

    public static final String TWEET_CACHE_FILENAME = "tweet_cache.ser";
    protected final String[] prefixes = new String[] { "Car", "Bird", "Elephant", "Phone", "Building" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        List<Tweet> tweets = new ArrayList<>();

        // read cached tweets (if any)
        try (FileInputStream ins = openFileInput(TWEET_CACHE_FILENAME)) {
            try (ObjectInputStream ois = new ObjectInputStream(ins)) {
                tweets = (List<Tweet>) ois.readObject();
                Log.i(TweetListActivity.class.getName(), "Read " + tweets.size() + " tweets from " + TWEET_CACHE_FILENAME);
            }
        } catch (Exception e) {
            Log.i(TweetListActivity.class.getName(), "Cached file " + TWEET_CACHE_FILENAME + " cannot be read: " + e);
        }

        final Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            final String name = prefixes[rand.nextInt(prefixes.length)] + ' ' + (rand.nextInt(10) + 1);
            final Tweet tweet = new Tweet((long) i, name, "Some random body for " + name);
            tweets.add(0, tweet);
        }
//        final ArrayAdapter<String> tweetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                names.toArray(new String[]{}));
        final TweetAdapter tweetsAdapter = new TweetAdapter(this, tweets.toArray(new Tweet[] {}));

        try (FileOutputStream out = openFileOutput(TWEET_CACHE_FILENAME, MODE_PRIVATE)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(tweets);
            }
            Log.i(TweetListActivity.class.getName(), "Successfully written " + tweets.size() + " tweets to " + TWEET_CACHE_FILENAME);
        } catch (Exception e) {
            Log.e(TweetListActivity.class.getName(), "Cannot open file " + TWEET_CACHE_FILENAME + ": " + e);
        }

        final ListView lvTweet = getListView();
        lvTweet.setAdapter(tweetsAdapter);
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
        final Intent intent = new Intent(this, TweetDetailActivity.class);
        startActivity(intent);
    }
}
