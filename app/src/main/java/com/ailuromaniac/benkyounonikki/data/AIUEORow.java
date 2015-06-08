package com.ailuromaniac.benkyounonikki.data;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEORow {

    public static final String TAG = "AIUEORow";

    private long id;
    private AIUEOGroup group;
    private AIUEOType type;
    private String vowelA;
    private String vowelI;
    private String vowelU;
    private String vowelE;
    private String vowelO;

    // constructor
    public AIUEORow(long id, AIUEOGroup group, AIUEOType type, String vowelA, String vowelI, String vowelU, String vowelE, String vowelO) {
        this.id = id;
        this.group = group;
        this.type = type;
        this.vowelA = vowelA;
        this.vowelI = vowelI;
        this.vowelU = vowelU;
        this.vowelE = vowelE;
        this.vowelO = vowelO;
    }

    // setters and getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AIUEOGroup getGroup() {
        return group;
    }

    public void setGroup(AIUEOGroup group) {
        this.group = group;
    }

    public AIUEOType getType() {
        return type;
    }

    public void setType(AIUEOType type) {
        this.type = type;
    }

    public String getVowelA() {
        return vowelA;
    }

    public void setVowelA(String vowelA) {
        this.vowelA = vowelA;
    }

    public String getVowelI() {
        return vowelI;
    }

    public void setVowelI(String vowelI) {
        this.vowelI = vowelI;
    }

    public String getVowelU() {
        return vowelU;
    }

    public void setVowelU(String vowelU) {
        this.vowelU = vowelU;
    }

    public String getVowelE() {
        return vowelE;
    }

    public void setVowelE(String vowelE) {
        this.vowelE = vowelE;
    }

    public String getVowelO() {
        return vowelO;
    }

    public void setVowelO(String vowelO) {
        this.vowelO = vowelO;
    }

    // return a String of information
    public String[] getAIUEORowString() {
        return new String[]{this.getGroup().getGroup(), this.getType().getType(), this.getVowelA(),
                            this.getVowelI(), this.getVowelU(), this.getVowelE(), this.getVowelO()};
    }
}
