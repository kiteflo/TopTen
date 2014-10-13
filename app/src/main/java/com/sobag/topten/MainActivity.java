package com.sobag.topten;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.sobag.topten.domain.User;


public class MainActivity extends ActionBarActivity {

    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);

        // invokeCrashlytics();
        invokeGSON();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------

    // trigger crashlytics...
    private void invokeCrashlytics()
    {
        Crashlytics.start(this);
        Toast.makeText(this,"Division by zero..." +4/0,Toast.LENGTH_SHORT).show();
    }

    // trigger GSON...
private void invokeGSON()
{
    User user = new User("barty","Bart","Simpson","Springfield");

    // serialize from object to JSON
    String userJSON = new Gson().toJson(user);
    Log.i(LOG_TAG,"User JSON:" +userJSON);

    // deserialize from JSON to object
    User deserializedUser = new Gson().fromJson(userJSON,User.class);
    Log.i(LOG_TAG,"Username: " +deserializedUser.getUsername());
}
}
