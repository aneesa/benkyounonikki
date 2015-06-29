package com.ailuromaniac.benkyounonikki;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ailuromaniac.benkyounonikki.controller.Controller;
import com.ailuromaniac.benkyounonikki.data.AIUEO;
import com.ailuromaniac.benkyounonikki.data.AIUEOListAdapter;

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
            this.generateHeaderTextView(rootView, linearLayout, sectionNumber-1);

            // main
            if (sectionNumber == 1){
                generateMainView(rootView);
            }
            // fragment A-I-U-E-O
            else if (sectionNumber == 2) {
                generateAIUEOView(rootView);
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
        private void generateHeaderTextView(View view, LinearLayout linearLayout, int fragmentIndex){
            String[] fragments = controller.getAllFragmentNames();

            TextView headerTv = new TextView(view.getContext());
            headerTv.setTextAppearance(view.getContext(), R.style.MyFragmentHeaderTextView);
            headerTv.setBackgroundResource(R.drawable.fragment_textview_bordered);
            headerTv.setText(fragments[fragmentIndex]);

            linearLayout.addView(headerTv);
        }

        // for fragment main
        private void generateMainView(View view){

//            TextView valueTV = new TextView(view.getContext());
//            valueTV.setText("hallo hallo");
//            valueTV.setId(5);
//            valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//
//            ((LinearLayout) linearLayout).addView(valueTV);
        }

        // for fragment A-I-U-E-O
        private void generateAIUEOView(View view){
            // generate AIUEO List
            List<AIUEO> aiueoList = controller.getAiueos();
            final AIUEOListAdapter aiueoAdapter = new AIUEOListAdapter(view.getContext(), aiueoList);

            ListView listView = (ListView)view.findViewById(R.id.list_aiueo);
            listView.setAdapter(aiueoAdapter);
        }
    }
}
