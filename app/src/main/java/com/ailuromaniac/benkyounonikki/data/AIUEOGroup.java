package com.ailuromaniac.benkyounonikki.data;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEOGroup {
    public static final String TAG = "AIUEOGroup";

    private long id;
    private String group;

    // constructors
    public AIUEOGroup(long id, String group) {
        this.id = id;
        this.group = group;
    }

    // setters and getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
