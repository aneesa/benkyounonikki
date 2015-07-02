package com.ailuromaniac.benkyounonikki.controller;

import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.ContentDao;
import com.ailuromaniac.benkyounonikki.dao.FragmentDao;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.dataObject.Fragment;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller {

    private FragmentDao fragmentDao;
    private ContentDao contentDao;

    private List<Fragment> fragments;
    private List<Content> contents;


    public Controller(Context context) {
        fragmentDao = new FragmentDao(context);
        contentDao = new ContentDao(context);

        fragments = getAllFragments();
        contents = getAllContents();
    }

    // getters
    public List<Fragment> getFragments() {
        return fragments;
    }

    public List<Content> getContents() {
        return contents;
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