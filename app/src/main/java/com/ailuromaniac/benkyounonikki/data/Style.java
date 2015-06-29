package com.ailuromaniac.benkyounonikki.data;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Style {

    private int id;
    private String name;

    // constructor
    public Style(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
