package com.ailuromaniac.benkyounonikki;

/**
 * Created by Aneesa on 7/4/2015.
 */
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ailuromaniac.benkyounonikki.controller.Controller;
import com.ailuromaniac.benkyounonikki.dataObject.Content;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_result);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search

            final List<Content> searchResultArrayList = ((Controller)getApplication()).getAllContentsBySearchString(query);

            ArrayAdapter searchResultArrayAdapter = new ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_2,
                    android.R.id.text1,
                    searchResultArrayList){

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView contentString = (TextView) view.findViewById(android.R.id.text1);

                    Content content = searchResultArrayList.get(position);
                    contentString.setText(content.getContent());

                    return view;
                }
            };

            ((ListView)findViewById(R.id.search_results)).setAdapter(searchResultArrayAdapter);

            // Redirect to the fragment search result in Main Activity
//            Intent mainIntent = new Intent(this.getApplicationContext(), MainActivity.class);
//            startActivity(mainIntent);
        }
    }
}
