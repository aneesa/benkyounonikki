package com.ailuromaniac.benkyounonikki.dataObject;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Content {

    private int id;
    private int fragmentId;
    private int position;
    private String style;
    private String drawable;
    private String content;

    // constructor
    public Content(int id, int fragmentId, int position, String style, String drawable, String content) {
        this.id = id;
        this.fragmentId = fragmentId;
        this.position = position;
        this.style = style;
        this.drawable = drawable;
        this.content = content;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public int getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(int fragmentId) { this.fragmentId = fragmentId; }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public String getDrawable() { return drawable; }

    public void setDrawable(String drawable) { this.drawable = drawable; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
