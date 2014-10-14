package com.reflection;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import com.reflection.fragment.*;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, TagsDrawerFragment.TagsDrawerCallbacks {
    /**
     * Tag to help in debugging
     */
    private final String TAG = MainActivity.class.getSimpleName();

    /**
     * The drawer layout
     */
    private DrawerLayout mDrawer;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Right menu Drawer Fragment
     */
    private TagsDrawerFragment mTagsDrawerFragment;
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

        mTagsDrawerFragment = (TagsDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.tags_drawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mTagsDrawerFragment.setUp(
                R.id.tags_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:

                getSupportFragmentManager().popBackStackImmediate();
                replaceFragment(new MainFragment()/*PlaceholderFragment.newInstance(1)*/, true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, "Home");
                break;
            case 1:
                replaceFragment(new PersonalReflectionFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, "PersonalReflection");
                break;
            case 2:
                replaceFragment(new ReflectionResponseFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, "ReflectionResponse");
                break;
            case 3:
                replaceFragment(new SettingsFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, "Settings");
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                Log.i(TAG, "Default frag");
                replaceFragment(new MainFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, "Home");
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section_home);
                break;
            case 1:
                mTitle = getString(R.string.title_post);
                break;
            case 2:
                mTitle = "Reflect";
                break;
            case 3:
                mTitle = getString(R.string.title_settings);
                break;
            case 4:
                mTitle = getString(R.string.title_section_account);
                break;
            case 5:
                mTitle = getString(R.string.title_section_terms);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.global, menu);
        restoreActionBar();
        return true;

        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_tags) {
            if (mTagsDrawerFragment.isDrawerOpen()) {
                mDrawer.closeDrawer(mTagsDrawerFragment.getView());
            } else {
                mDrawer.openDrawer(mTagsDrawerFragment.getView());
                if (mNavigationDrawerFragment.isDrawerOpen()) {
                    mDrawer.closeDrawer(mNavigationDrawerFragment.getView());
                }
            }
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    public void onTagItemSelected(int position) {

    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mDrawer.closeDrawer(mNavigationDrawerFragment.getView());
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Reflection");

            // set dialog message
            alertDialogBuilder.setMessage("Are you sure you want to exit Reflection?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, close
                    // current activity
                    MainActivity.super.onBackPressed();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel();
                }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Handles adding all fragments to the view.
     *
     * @param newFragment    The fragment to add.
     * @param addToBackstack Whether this Fragment should appear in the backstack or not.
     * @param transition     The transition animation to apply
     * @param backstackName  The name
     */
    public void replaceFragment(Fragment newFragment, boolean addToBackstack, int transition, String backstackName) {
        // use fragmentTransaction to replace the fragment
        Log.i(TAG, "Initializing Fragment Transaction");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Log.i(TAG, "Replacing the fragment and calling backstack");
        fragmentTransaction.replace(R.id.container, newFragment, backstackName);
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(backstackName);
        }
        Log.i(TAG, "setting the transition");
        fragmentTransaction.setTransition(transition);
        Log.i(TAG, "Commiting Transaction");
        fragmentTransaction.commit();
    }
}
