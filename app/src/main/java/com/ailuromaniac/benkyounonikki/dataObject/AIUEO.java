package com.ailuromaniac.benkyounonikki.dataObject;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class AIUEO {

    private String group;
    private String hiraganaA;
    private String hiraganaI;
    private String hiraganaU;
    private String hiraganaE;
    private String hiraganaO;
    private String katakanaA;
    private String katakanaI;
    private String katakanaU;
    private String katakanaE;
    private String katakanaO;
    private String romajiA;
    private String romajiI;
    private String romajiU;
    private String romajiE;
    private String romajiO;

    //constructor
    public AIUEO(String group, String hiraganaA, String hiraganaI, String hiraganaU,
                 String hiraganaE, String hiraganaO, String katakanaA, String katakanaI,
                 String katakanaU, String katakanaE, String katakanaO, String romajiA,
                 String romajiI, String romajiU, String romajiE, String romajiO) {
        this.group = group;
        this.hiraganaA = hiraganaA;
        this.hiraganaI = hiraganaI;
        this.hiraganaU = hiraganaU;
        this.hiraganaE = hiraganaE;
        this.hiraganaO = hiraganaO;
        this.katakanaA = katakanaA;
        this.katakanaI = katakanaI;
        this.katakanaU = katakanaU;
        this.katakanaE = katakanaE;
        this.katakanaO = katakanaO;
        this.romajiA = romajiA;
        this.romajiI = romajiI;
        this.romajiU = romajiU;
        this.romajiE = romajiE;
        this.romajiO = romajiO;
    }

    // getters
    public String getGroup() { return group; }

    public String getHiraganaA() { return hiraganaA; }

    public String getHiraganaI() { return hiraganaI; }

    public String getHiraganaU() { return hiraganaU; }

    public String getHiraganaE() { return hiraganaE; }

    public String getHiraganaO() { return hiraganaO; }

    public String getKatakanaA() {
        return katakanaA;
    }

    public String getKatakanaI() { return katakanaI; }

    public String getKatakanaU() {
        return katakanaU;
    }

    public String getKatakanaE() {
        return katakanaE;
    }

    public String getKatakanaO() {
        return katakanaO;
    }

    public String getRomajiA() {
        return romajiA;
    }

    public String getRomajiI() {
        return romajiI;
    }

    public String getRomajiU() {
        return romajiU;
    }

    public String getRomajiE() {
        return romajiE;
    }

    public String getRomajiO() {
        return romajiO;
    }
}
