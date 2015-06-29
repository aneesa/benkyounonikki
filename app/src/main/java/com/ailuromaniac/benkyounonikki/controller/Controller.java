package com.ailuromaniac.benkyounonikki.controller;

import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.AIUEODao;
import com.ailuromaniac.benkyounonikki.dao.ContentDao;
import com.ailuromaniac.benkyounonikki.dao.FragmentDao;
import com.ailuromaniac.benkyounonikki.dataObject.AIUEO;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.dataObject.Fragment;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller {

    private AIUEODao aiueoDao;
    private FragmentDao fragmentDao;
    private ContentDao contentDao;

    private List<AIUEO> aiueos;
    private List<Fragment> fragments;
    private List<Content> contents;


    public Controller(Context context) {
        aiueoDao = new AIUEODao(context);
        fragmentDao = new FragmentDao(context);
        contentDao = new ContentDao(context);

        aiueos = getAllAIUEOs();
        fragments = getAllFragments();
        contents = getAllContents();
    }

    // getters
    public List<AIUEO> getAiueos() {
        return aiueos;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public List<Content> getContents() {
        return contents;
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

    private List<Content> getAllContents() {
        List<Content> contentList = null;

        contentDao.open();
        contentList = contentDao.getAllContents();
        contentDao.close();

        return contentList;
    }

    // TODO: Map these!
    public List<Content> getAllContentsByFragmentId(int fragmentId) {
        List<Content> contentList = null;

        contentDao.open();
        contentList = contentDao.getContentByFragmentId(fragmentId);
        contentDao.close();

        return contentList;
    }
}