package com.ailuromaniac.benkyounonikki.controller;

import android.app.Application;
import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.ContentDao;
import com.ailuromaniac.benkyounonikki.dao.FragmentDao;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.dataObject.Fragment;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller extends Application {

    private FragmentDao fragmentDao;
    private ContentDao contentDao;

    private List<Fragment> fragments;

    @Override
    public void onCreate() {
        super.onCreate();
        fragmentDao = new FragmentDao(this);
        contentDao = new ContentDao(this);

        fragments = getAllFragments();
    }

    // getters
    public List<Fragment> getFragments() {
        return fragments;
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

    public List<Content> getAllContentsByFragmentPosition(int position) {

        // find the fragment id
        int fragmentId = getFragmentIdByPosition(position);

        List<Content> contentList = null;

        contentDao.open();
        contentList = contentDao.getContentByFragmentId(fragmentId);
        contentDao.close();

        return contentList;
    }

    public List<Content> getAllContentsBySearchString(String searchString) {
        List<Content> contentList = null;

        contentDao.open();
        contentList = contentDao.getContentBySearchString(searchString);
        contentDao.close();

        return contentList;
    }

    public int getFragmentIdByPosition(int position){
        // find the fragment id
        List<Fragment> curFragments = this.getFragments();
        for(int i=0; i<curFragments.size(); i++){
            Fragment curFragment = curFragments.get(i);
            if (curFragment.getPosition() == position) {
                return curFragment.getId();
            }
        }
        return 0;
    }

    public int getFragmentPositionById(int fragmentId){
        // find the fragment position
        List<Fragment> curFragments = this.getFragments();
        for(int i=0; i<curFragments.size(); i++){
            Fragment curFragment = curFragments.get(i);
            if (curFragment.getId() == fragmentId) {
                return curFragment.getPosition();
            }
        }
        return 0;
    }
}