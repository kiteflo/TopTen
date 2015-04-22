package com.sobag.topten;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.sobag.topten.domain.Item;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String applicationID = "CXGkcpXBGQlLoHrUDFMQKxm6xuQ9dPbdvz9UsFCC";
        String clientID = "FnbSSjQP4n6Ue20hjqDWQNsi0xdwF5shEWIo9ReX";

        try
        {
            Parse.initialize(this, applicationID, clientID);
            ParseObject.registerSubclass(Item.class);
        }
        catch (Error er)
        {
            er.printStackTrace();
        }

        invokeParse();
        saveItem();
        lookupItem();
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

    public void invokeParse()
    {
        ParseObject po = new ParseObject("Person");
        po.put("first","Donald");
        po.put("last", "Duck");
        po.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if (e != null)
                {
                    e.printStackTrace();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveItem()
    {
        Item item = new Item();
        item.setName("Apfel");
        item.setDescription("grünes saftiger Apfel...");
        item.saveEventually(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if (e == null)
                {
                    Toast.makeText(MainActivity.this, "Saved item!", Toast.LENGTH_SHORT).show();
                } else
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void lookupItem()
    {
        ParseQuery<Item> query = ParseQuery.getQuery("Item");
        query.whereContains("name","Apfel");
        query.findInBackground(new FindCallback<Item>()
        {
            @Override
            public void done(List<Item> list, ParseException e)
            {
                for (Item item : list)
                {
                    Log.i("MainActivty",item.getName());
                }
            }
        });
    }
}
