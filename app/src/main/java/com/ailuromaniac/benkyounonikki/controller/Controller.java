package com.ailuromaniac.benkyounonikki.controller;

import android.content.Context;

import com.ailuromaniac.benkyounonikki.dao.AIUEOGroupDAO;
import com.ailuromaniac.benkyounonikki.dao.AIUEORowDAO;
import com.ailuromaniac.benkyounonikki.dao.AIUEOTypeDAO;
import com.ailuromaniac.benkyounonikki.data.AIUEOGroup;
import com.ailuromaniac.benkyounonikki.data.AIUEORow;
import com.ailuromaniac.benkyounonikki.data.AIUEOType;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class Controller {

    private AIUEOGroupDAO aiueoGroupDAO;
    private AIUEOTypeDAO aiueoTypeDAO;
    private AIUEORowDAO aiueoRowDAO;

    public Controller(Context context) {
        aiueoGroupDAO = new AIUEOGroupDAO(context);
        aiueoTypeDAO = new AIUEOTypeDAO(context);
        aiueoRowDAO = new AIUEORowDAO(context);
    }

    public List<AIUEOGroup> getAllAIUEOGroups() {
        return aiueoGroupDAO.getAllAIUEOGroups();
    }

    public List<AIUEOType> getAllAIUEOTypes() {
        return aiueoTypeDAO.getAllAIUEOTypes();
    }

    public List<AIUEORow> getAllAIUEORows() {
        return aiueoRowDAO.getAllAIUEORows();
    }
}