package com.ailuromaniac.benkyounonikki.dataObject;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Fragment {

    private int id;
    private int position;
    private String name;

    // constructor
    public Fragment(int id, int position, String name) {
        this.id = id;
        this.position = position;
        this.name = name;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }
}
