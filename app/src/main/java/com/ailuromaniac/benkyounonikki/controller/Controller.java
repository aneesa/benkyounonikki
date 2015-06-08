package com.ailuromaniac.benkyounonikki.controller;

import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.AIUEODao;
import com.ailuromaniac.benkyounonikki.data.AIUEO;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller {

    private AIUEODao aiueoDao;

    public Controller(Context context) {
        aiueoDao = new AIUEODao(context);
    }

    /**
     * Get all the AIEUO characters for hiragana/katakana list display
     * @return a list of AIUEO
     */
    public List<AIUEO> getAllAIUEOs() {
        return aiueoDao.getAllAIUEOs();
    }

}