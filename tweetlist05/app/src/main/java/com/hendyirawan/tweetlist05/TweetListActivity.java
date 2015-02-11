package com.hendyirawan.tweetlist05;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TweetListActivity extends ActionBarActivity {

    protected final String[] prefixes = new String[] { "Car", "Bird", "Elephant", "Phone", "Building" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        final List<String> names = new ArrayList<>();
        final Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            final String name = prefixes[rand.nextInt(prefixes.length)] + ' ' + (rand.nextInt(10) + 1);
            names.add(name);
        }
        final ArrayAdapter<String> tweetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                names.toArray(new String[]{}));

        final ListView lvTweet = (ListView) findViewById(R.id.lv_tweet);
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
}
