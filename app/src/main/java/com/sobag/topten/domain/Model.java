package com.sobag.topten.domain;

import com.google.inject.Singleton;

/**
 * Created by tzhmufl2 on 13.10.14.
 */
@Singleton
public class Model
{
    // ------------------------------------------------------------------------
    // MEMBERS
    // ------------------------------------------------------------------------

    private User user = null;
    private int counter = 0;

    // ------------------------------------------------------------------------
    // GETTER & SETTER
    // ------------------------------------------------------------------------

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public int getCounter()
    {
        return counter;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }
}
