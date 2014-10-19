package com.sobag.topten;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.sobag.topten.domain.Model;
import com.sobag.topten.domain.User;

import org.json.JSONObject;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity
        implements Validator.ValidationListener
{
    public static final String MIXPANEL_TOKEN = "60d24642e95dfd3859423d1d9004c7b5";
    private MixpanelAPI mixpanel;

    private Validator validator = null;
    private MainActivity selfReference;

    @InjectView(R.id.iv_image)
    ImageView ivImage;

    @InjectView(R.id.tv_hello)
    TextView tvHello;

    @InjectView(R.id.but_increase)
    Button butIncrease;

    @Required(order = 1)
    @Email(order = 2)
    @InjectView(R.id.et_username)
    EditText etUsername;

    @Password(order = 3)
    @TextRule(order = 4, minLength = 6, message = "Enter at least 6 characters.")
    @InjectView(R.id.et_password)
    EditText etPassword;

    @Inject
    Model model;

    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Crashlytics.start(this);
        setContentView(R.layout.activity_main);

        // invokeCrashlytics();
        // invokeGSON();
        // invokeRoboGuice();
        // invokeSaripaar();
        // invokeGlide();
        // invokeSlidingMenu();
        // invokeActiveAndroid();
        invokeMixpanel();
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

    public void onNext(View view)
    {
        validator.validate();
    }

    public void onValidationSucceeded()
    {
        Toast.makeText(this, "Validation suceeded", Toast.LENGTH_SHORT).show();
    }

    public void onValidationFailed(View failedView, Rule<?> failedRule)
    {
        String message = failedRule.getFailureMessage();

        if (failedView instanceof EditText)
        {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        } else
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        mixpanel.flush();
        super.onDestroy();
    }

    public void onStop() {
        super.onStop();
        mixpanel.flush();
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
        Log.i(LOG_TAG, "Username: " + deserializedUser.getUsername());
    }

    // use RoboGuice
    private void invokeRoboGuice()
    {
        tvHello.setText("RobiGuice is so simple...");
    }

    private void invokeSaripaar()
    {
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void invokeGlide()
    {
        Glide.with(this).load("http://www.loewe-fenster.de/wp-content/uploads/loewe-fenster-maskottchen2.jpg").into(ivImage);
    }

    private void invokeSlidingMenu()
    {
        selfReference = this;
        View view = getLayoutInflater().inflate(R.layout.sidebar_menu, null);
        Button but1 = (Button)view.findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(selfReference,"Hello Sidebar",Toast.LENGTH_SHORT).show();
            }
        });

        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(15);
        menu.setBehindOffsetRes(15);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(view);
    }

    private void invokeActiveAndroid()
    {
        User user = new User("toptenuser","florian","mueller","frankfurt");
        user.save();
        Toast.makeText(this,"Created user, user ID = " +user.getId().toString(),Toast.LENGTH_LONG).show();

        User lookedUp = User.findById(User.class, 1l);
        Toast.makeText(this,"Looked up user, username: " +lookedUp.getUsername(),Toast.LENGTH_LONG).show();
    }

    private void invokeMixpanel()
    {
        mixpanel = MixpanelAPI.getInstance(
                this, MIXPANEL_TOKEN);

        butIncrease.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    JSONObject props =
                            new JSONObject();
                    props.put("Button",
                            "Increase");
                    props.put("Clicktype",
                            "Short");
                    mixpanel.track(
                            "Short Click",
                            props);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        butIncrease.setOnLongClickListener(
                new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                try
                {
                    JSONObject props =
                            new JSONObject();
                    props.put("Button",
                            "Increase");
                    props.put("Clicktype",
                            "Long");
                    mixpanel.track(
                            "Long Click",
                            props);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                return false;
            }
        });
    }
}
