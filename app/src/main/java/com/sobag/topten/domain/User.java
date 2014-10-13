package com.sobag.topten.domain;

/**
 * Created by tzhmufl2 on 13.10.14.
 */
public class User
{
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    private String username;
    private String firstname;
    private String lastname;
    private String town;

    public User(String username,String firstname,String lastname, String town)
    {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.town = town;
    }

    // ------------------------------------------------------------------------
    // GETTER & SETTER
    // ------------------------------------------------------------------------

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }
}
