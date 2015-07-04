package com.ailuromaniac.benkyounonikki;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;

import com.ailuromaniac.benkyounonikki.controller.Controller;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.style.FragmentAIUEOTextView;
import com.ailuromaniac.benkyounonikki.style.FragmentTextView;
import com.ailuromaniac.benkyounonikki.style.FragmentTitleTextView;

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

    private static Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle args = getArguments();
            int sectionNumber = args.getInt(ARG_SECTION_NUMBER);

            int[] fragmentIds = {
                    R.layout.fragment_main,     // default fragment
                    R.layout.fragment_a_i_u_e_o,
                    R.layout.fragment_to_be_or_not_to_be
            };

            View rootView = inflater.inflate(fragmentIds[sectionNumber-1], container, false);

            LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.fragment_linear_layout);

            List<com.ailuromaniac.benkyounonikki.dataObject.Fragment> fragmentList = controller.getFragments();
            this.generateHeaderTextView(rootView, linearLayout, fragmentList.get(sectionNumber-1));

            // main
            if (sectionNumber == 1){
                generateGeneralView(rootView, linearLayout, fragmentList.get(sectionNumber - 1));
            }
            // fragment A-I-U-E-O
            else if (sectionNumber == 2) {
                generateAIUEOView(rootView, linearLayout, fragmentList.get(sectionNumber-1));
            }
            // fragment to be
            else if (sectionNumber == 3) {
                generateGeneralView(rootView, linearLayout, fragmentList.get(sectionNumber-1));
            }

            return rootView;
        }

//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }

        // fragment header
        private void generateHeaderTextView(View view, LinearLayout linearLayout,
                                            com.ailuromaniac.benkyounonikki.dataObject.Fragment fragment){

            // set the headers' textviews and their styles
            FragmentTitleTextView fragmentTitleTextView =
                    new FragmentTitleTextView(view.getContext(), fragment);
            linearLayout.addView(fragmentTitleTextView);
        }

        // for fragment main
        // for fragment to be
        private void generateGeneralView(View view, LinearLayout linearLayout,
                                      com.ailuromaniac.benkyounonikki.dataObject.Fragment fragment){

            List<Content> contentList = controller.getAllContentsByFragmentId(fragment.getId());

            for(Content content : contentList){
                FragmentTextView contentTV = new FragmentTextView(view.getContext(), content);
                linearLayout.addView(contentTV);
            }
        }

        // for fragment A-I-U-E-O
        // because of the table in this fragment, we cannot just loop the contents
        // we have to manually go through the contents
        private void generateAIUEOView(View view, LinearLayout linearLayout,
                                       com.ailuromaniac.benkyounonikki.dataObject.Fragment fragment){

            List<Content> contentList = controller.getAllContentsByFragmentId(fragment.getId());

            // section header
            FragmentTextView sectionHeaderTV = new FragmentTextView(view.getContext(), contentList.get(0));
            linearLayout.addView(sectionHeaderTV);

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

            for(int i=1; i<4; i++){
                FragmentTextView contentTV = new FragmentTextView(view.getContext(), contentList.get(i));
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
            int i = 4;
            do {
                LinearLayout japaneseTableRow = new LinearLayout(view.getContext());
                japaneseTableRow.setLayoutParams(rowLayoutParams);
                japaneseTableRow.setOrientation(LinearLayout.HORIZONTAL);

                // set the group (i.e a, ka, ga, etc..)
                FragmentAIUEOTextView groupTV = new FragmentAIUEOTextView(view.getContext(), contentList.get(i));
                i++;        // go to the next char
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
    }
}
