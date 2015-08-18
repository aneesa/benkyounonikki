package com.ailuromaniac.benkyounonikki;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ailuromaniac.benkyounonikki.controller.Controller;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.style.FragmentAIUEOTextView;
import com.ailuromaniac.benkyounonikki.style.FragmentTextView;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String TAG = "MainActivity";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // if we are starting the main intent from search result page,
        // go to the correct fragment
        Bundle extras = getIntent().getExtras();

        if(extras!=null && !extras.isEmpty()){
            String searchQuery = extras.getString("searchQuery");

            if(searchQuery!=null && !searchQuery.isEmpty()) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(0, searchQuery))   // choose 0 for search fragment
                        .commit();
            }else {
                int selectedFragmentPosition = extras.getInt("selectedFragmentId");
                int selectedContentId = extras.getInt("selectedContentId");

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(selectedFragmentPosition, selectedContentId))
                        .commit();
            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//        }
//    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();

            // Associate searchable configuration with the SearchView
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));

            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_CONTENT_ID = "content_id";
        private static final String ARG_SEARCH_QUERY = "searc_query";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public static PlaceholderFragment newInstance(int sectionNumber, int selectedContentId) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_CONTENT_ID, selectedContentId);
            fragment.setArguments(args);
            return fragment;
        }

        public static PlaceholderFragment newInstance(int sectionNumber, String searchQuery) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_SEARCH_QUERY, searchQuery);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle args = getArguments();
            int sectionNumber = args.getInt(ARG_SECTION_NUMBER);
            int contentId = args.getInt(ARG_CONTENT_ID);
            String searchQuery = args.getString(ARG_SEARCH_QUERY);

            // TODO: find a way to automate this
            int[] fragmentIds = {
                    R.layout.fragment_search_result,    // search fragment (not in the drawer)
                    R.layout.fragment_main,     // default fragment
                    R.layout.fragment_a_i_u_e_o,
                    R.layout.fragment_to_be_or_not_to_be,
                    R.layout.fragment_lets_group_them
            };

            View rootView = inflater.inflate(fragmentIds[sectionNumber], container, false);

            LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.fragment_linear_layout);

            if(sectionNumber==0){
                this.generateSearchResultView(rootView, searchQuery);
            }
            // section number == fragment id in db
            else {
//                com.ailuromaniac.benkyounonikki.dataObject.Fragment fragment =
//                        ((Controller) getActivity().getApplication()).getFragments().get(sectionNumber - 1);

                // fragment A-I-U-E-O
                if (sectionNumber == 2) {
                    generateAIUEOView(rootView, linearLayout, sectionNumber, contentId);
                }
                // main
                // fragment to be or not to be
                // fragment let's group them
                else {
                    generateGeneralView(rootView, linearLayout, sectionNumber, contentId);
                }
            }

            return rootView;
        }

//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }

        // for fragment main
        // for fragment to be or not to be
        // for fragment let's group them
        private void generateGeneralView(View view, LinearLayout linearLayout,
                                            int fragmentId,
                                            int contentId){

            List<Content> contentList = ((Controller)getActivity().getApplication()).getAllContentsByFragmentId(fragmentId);

            for(Content content : contentList) {
                // if the content is just normal text view, add it to the layout right away
                if (content.getStyle().contains("TextView")) {
                    FragmentTextView contentTV = new FragmentTextView(view.getContext(), content);

                    // if content id is selected, request focus
                    if (contentId != 0 && contentTV.getId() == contentId) {
                        contentTV.requestFocus();
                    }

                    linearLayout.addView(contentTV);
                }
                // if the content is a verb row, create the row first
                else if (content.getStyle().compareToIgnoreCase("VerbRow")==0) {

                    // set the layout params first
                    LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                    rowLayoutParams.setMargins(0, 0, 0, 0);

                    // set the layout for the verb row
                    LinearLayout verbRow = new LinearLayout(view.getContext());
                    verbRow.setLayoutParams(rowLayoutParams);
                    verbRow.setOrientation(LinearLayout.HORIZONTAL);

                    // parse the content
                    String[] verbDisplay = content.getContent().split("::");

                    for(int i=0; i<verbDisplay.length; i++){

                        // reset the style and content
                        // we don't need the style anymore
                        // and we have already stored the contents in array verbDisplay
                        if(i==0) {
                            content.setStyle("HiraganaTextView");
                            content.setContent(verbDisplay[i]);
                        } else if(i==1){
                            content.setStyle("RomajiTextView");
                            content.setContent(verbDisplay[i]);
                        } else if(i==2){
                            content.setStyle("BoxedTextView");
                            content.setContent(verbDisplay[i]);
                        }

                        // create the content textview for each verb display
                        FragmentTextView contentTV = new FragmentTextView(view.getContext(), content);

                        // if content id is selected, request focus
                        if(contentId!=0 && contentTV.getId()==contentId) {
                            contentTV.requestFocus();
                        }
                        verbRow.addView(contentTV);
                    }
                    linearLayout.addView(verbRow);
                }
            }
        }

        // for fragment A-I-U-E-O
        // because of the table in this fragment, we cannot just loop the contents
        // we have to manually go through the contents
        private void generateAIUEOView(View view, LinearLayout linearLayout,
                                       int fragmentId,
                                        int contentId){

            List<Content> contentList = ((Controller)getActivity().getApplication()).getAllContentsByFragmentId(fragmentId);

            // section title and section header title
            for(int i=0; i<2; i++) {
                FragmentTextView titleTV = new FragmentTextView(view.getContext(), contentList.get(i));
                // if content id is selected, request focus
                if (contentId != 0 && titleTV.getId() == contentId) {
                    titleTV.requestFocus();
                }
                linearLayout.addView(titleTV);
            }

            // set up default row layout ================================================================
            LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

            // set layout margins to 3dp
            int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
                    getResources().getDisplayMetrics()));
            rowLayoutParams.setMargins(0, margins, 0, margins);

            // legend table =========================================================================
            LinearLayout legendTable = new LinearLayout(view.getContext());
            legendTable.setLayoutParams(rowLayoutParams);
            legendTable.setOrientation(LinearLayout.HORIZONTAL);

            for(int i=2; i<5; i++){
                FragmentTextView contentTV = new FragmentTextView(view.getContext(), contentList.get(i));
                // if content id is selected, request focus
                if(contentId!=0 && contentTV.getId()==contentId) {
                    contentTV.requestFocus();
                }
                legendTable.addView(contentTV);
            }
            linearLayout.addView(legendTable);

            // hiragana/katakana table =========================================================================
            LinearLayout japaneseTable = new LinearLayout(view.getContext());
            japaneseTable.setLayoutParams(rowLayoutParams);
            japaneseTable.setOrientation(LinearLayout.VERTICAL);

            // table content ========================
            boolean header = true;
            rowLayoutParams.setMargins(0, 0, 0, 0); //reset margin for the contents
            int i = 5;
            do {
                LinearLayout japaneseTableRow = new LinearLayout(view.getContext());
                japaneseTableRow.setLayoutParams(rowLayoutParams);
                japaneseTableRow.setOrientation(LinearLayout.HORIZONTAL);

                // set the group (i.e a, ka, ga, etc..)
                FragmentAIUEOTextView groupTV = new FragmentAIUEOTextView(view.getContext(), contentList.get(i));
                i++;        // go to the next char
                // if content id is selected, request focus
                if(contentId!=0 && groupTV.getId()==contentId) {
                    groupTV.requestFocus();
                }
                japaneseTableRow.addView(groupTV);

                // set the characters
                LinearLayout japaneseTableGroupRow = new LinearLayout(view.getContext());
                LinearLayout.LayoutParams japaneseTableGroupRowLayoutParams = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.85f);
                japaneseTableGroupRow.setOrientation(LinearLayout.VERTICAL);

                // set layout margins to 3dp
                japaneseTableGroupRow.setLayoutParams(japaneseTableGroupRowLayoutParams);

                // table header only has one row
                if(header) {
                    // TODO: See if we can extract this out
                    LinearLayout japaneseTableCharRow = new LinearLayout(view.getContext());
                    japaneseTableCharRow.setLayoutParams(rowLayoutParams);
                    japaneseTableCharRow.setOrientation(LinearLayout.HORIZONTAL);

                    // one column each for -a, -i, -u, -e, -o
                    for (int k = 0; k < 5; k++) {
                        // set the group
                        FragmentAIUEOTextView charTV = new FragmentAIUEOTextView(view.getContext(), contentList.get(i));
                        i++;        // go to the next char
                        // if content id is selected, request focus
                        if(contentId!=0 && charTV.getId()==contentId) {
                            charTV.requestFocus();
                        }
                        japaneseTableCharRow.addView(charTV);
                    }
                    japaneseTableGroupRow.addView(japaneseTableCharRow);

                    header = false;     // set header to false after the first use
                } else {
                    // one row each for hiragana, katakana and romaji
                    for (int j = 0; j < 3; j++) {
                        LinearLayout japaneseTableCharRow = new LinearLayout(view.getContext());
                        japaneseTableCharRow.setLayoutParams(rowLayoutParams);
                        japaneseTableCharRow.setOrientation(LinearLayout.HORIZONTAL);

                        // one column each for -a, -i, -u, -e, -o
                        for (int k = 0; k < 5; k++) {
                            // set the group
                            FragmentAIUEOTextView charTV = new FragmentAIUEOTextView(view.getContext(), contentList.get(i));
                            i++;        // go to the next char
                            // if content id is selected, request focus
                            if(contentId!=0 && charTV.getId()==contentId) {
                                charTV.requestFocus();
                            }
                            japaneseTableCharRow.addView(charTV);
                        }
                        japaneseTableGroupRow.addView(japaneseTableCharRow);
                    }
                }

                japaneseTableRow.addView(japaneseTableGroupRow);

                japaneseTable.addView(japaneseTableRow);
            } while (i<contentList.size());

            linearLayout.addView(japaneseTable);
        }

        private void generateSearchResultView(View view, String searchQuery){

            //TODO: see if we can separate these into other class
            // get the searched content from db
            final List<Content> searchResultList =
                    ((Controller)getActivity().getApplication()).getAllContentsBySearchString(searchQuery);

            // create the array adapter for displaying the search list
            ArrayAdapter searchResultArrayAdapter = new ArrayAdapter(
                    (Controller)getActivity().getApplication(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text1,
                    searchResultList){

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    // TODO: see if we can use FragmentTextView here
                    TextView contentString = (TextView) view.findViewById(android.R.id.text1);

                    // display the content only
                    Content content = (Content)getItem(position);
                    contentString.setText(content.getContent());
                    contentString.setTextColor(Color.BLACK);

                    return view;
                }
            };

            // set up and display the search list view
            ListView searchResultListView = (ListView)view.findViewById(R.id.search_results);
            searchResultListView.setAdapter(searchResultArrayAdapter);
            searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Content contentClicked = (Content)parent.getItemAtPosition(position);

                    // Redirect to the fragment search result in Main Activity when clicked
                    Intent mainIntent = new Intent(getActivity().getApplication(), MainActivity.class);
                    mainIntent.putExtra("selectedFragmentId", contentClicked.getFragmentId());
                    mainIntent.putExtra("selectedContentId", contentClicked.getId());
                    startActivity(mainIntent);

                }
            });
        }
    }
}
