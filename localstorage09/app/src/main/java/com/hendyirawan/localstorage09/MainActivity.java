package com.hendyirawan.localstorage09;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    private Button _loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i(MainActivity.class.getName(), "Created");
        final EditText usernameFld = (EditText) findViewById(R.id.usernameFld);
        final EditText passwordFld = (EditText) findViewById(R.id.passwordFld);
        final SharedPreferences prefs = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
        final String username = prefs.getString(USERNAME_KEY, null);
        final String password = prefs.getString(PASSWORD_KEY, null);
        if (username != null && password != null) {
            // already logged in
            usernameFld.setText(username);
            passwordFld.setText(password);
            final Intent intent = new Intent(MainActivity.this, TweetListActivity.class);
            startActivity(intent);
        }

        _loginBtn = (Button) findViewById(R.id.btn_login);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MainActivity.class.getName(), "Clicked");
                //_loginBtn.setText("I am clicked!");

                final String username = usernameFld.getText().toString();
                final String password = passwordFld.getText().toString();
                Log.d(MainActivity.class.getName(), "Username: " + username);

                final SharedPreferences.Editor editor = prefs.edit();
                editor.putString(USERNAME_KEY, username);
                editor.putString(PASSWORD_KEY, password);
                editor.commit();

                final Intent intent = new Intent(MainActivity.this, TweetListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
