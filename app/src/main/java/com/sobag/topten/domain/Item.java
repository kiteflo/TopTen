package com.sobag.topten.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by tzhmufl2 on 22.04.15.
 */
@ParseClassName("Item")
public class Item extends ParseObject
{
    private String name;
    private String description;

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        put("name",name);
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        put("description",description);
    }
}
