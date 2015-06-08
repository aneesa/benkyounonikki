package com.ailuromaniac.benkyounonikki.data;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEOType {
    public static final String TAG = "AIUEOType";

    private long id;
    private String type;

    // constructors
    public AIUEOType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    // setters and getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
