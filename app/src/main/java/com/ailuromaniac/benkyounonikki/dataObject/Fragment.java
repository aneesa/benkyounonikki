package com.ailuromaniac.benkyounonikki.dataObject;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Fragment {

    private int id;
    private String name;
    private String layout;

    // constructor
    public Fragment(int id, String name, String layout) {
        this.id = id;
        this.name = name;
        this.layout = layout;
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

    public void setName(String name) { this.name = name; }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) { this.layout = layout; }
}
