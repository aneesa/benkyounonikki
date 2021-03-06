package com.ailuromaniac.benkyounonikki.dataObject;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class Content {

    private int id;
    private int fragmentId;
    private String style;
    private String content;

    // constructor
    public Content(int id, int fragmentId, String style, String content) {
        this.id = id;
        this.fragmentId = fragmentId;
        this.style = style;
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

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
