package com.ailuromaniac.benkyounonikki.controller;

import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.AIUEODao;
import com.ailuromaniac.benkyounonikki.dao.FragmentDAO;
import com.ailuromaniac.benkyounonikki.data.AIUEO;
import com.ailuromaniac.benkyounonikki.data.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller {

    private AIUEODao aiueoDao;
    private FragmentDAO fragmentDao;

    private List<AIUEO> aiueos;
    private List<Fragment> fragments;


    public Controller(Context context) {
        aiueoDao = new AIUEODao(context);
        fragmentDao = new FragmentDAO(context);

        aiueos = getAllAIUEOs();
        fragments = getAllFragments();
    }

    // getters
    public List<AIUEO> getAiueos() {
        return aiueos;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    /**
     * Get all the AIEUO characters for hiragana/katakana list display
     * @return a list of AIUEO
     */
    private List<AIUEO> getAllAIUEOs() {
        List<AIUEO> aiueoList = null;

        aiueoDao.open();
        aiueoList = aiueoDao.getAllAIUEOs();
        aiueoDao.close();

        return aiueoList;
    }

    private List<Fragment> getAllFragments() {
        List<Fragment> fragmentList = null;

        fragmentDao.open();
        fragmentList = fragmentDao.getAllFragments();
        fragmentDao.close();

        return fragmentList;
    }

    public String[] getAllFragmentNames() {
        String[] fragmentNames = new String[fragments.size()];

        for(int i=0; i<fragments.size(); i++) {
            fragmentNames[i] = fragments.get(i).getName();
        }

        return fragmentNames;
    }

}