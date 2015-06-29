package com.ailuromaniac.benkyounonikki.data;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Content {

    private Fragment fragment;
    private Style style;
    private int position;
    private String content;

    // constructor
    public Content(Fragment fragment, Style style, int position, String content) {
        this.fragment = fragment;
        this.style = style;
        this.position = position;
        this.content = content;
    }

    // getters and setters
    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
