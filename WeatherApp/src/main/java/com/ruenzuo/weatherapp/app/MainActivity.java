package com.ruenzuo.weatherapp.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.ContentFragment;
import com.ruenzuo.weatherapp.fragments.DetailFragment;
import com.ruenzuo.weatherapp.fragments.DrawerFragment;

public class MainActivity extends ActionBarActivity implements ContentFragment.OnItemSelectedListener, DrawerFragment.OnItemSelectedListener {

    private DrawerLayout drawerLayout = null;
    private ActionBarDrawerToggle actionBarDrawerToggle = null;

    private int currentOption;
    private String drawerTitle;
    private String currentTitle;
    private String[] drawerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String screenType = getResources().getString(R.string.screen_type);
        setContentView(R.layout.activity_main);
        drawerTitle = getResources().getString(R.string.drawer_title);
        drawerOptions = getResources().getStringArray(R.array.menu_options);
        if ((screenType.equals("phone-port") ||
             screenType.equals("phone-land"))) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

                public void onDrawerClosed(View view) {
                    getSupportActionBar().setTitle(currentTitle);
                }

                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(drawerTitle);
                }

            };
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
        }
        else {
            setupDetailFragment();
        }
        if (savedInstanceState == null) {
            selectDrawerItem(0);
        }
    }

    public void setupDetailFragment() {
        DetailFragment detailFragment = new DetailFragment();
        Bundle contentArgs = new Bundle();
        contentArgs.putInt(DetailFragment.ARG_DETAIL_NUMBER, 0);
        detailFragment.setArguments(contentArgs);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_frame, detailFragment, "detailFragment").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDrawerItemSelected(int drawerItem) {
        selectDrawerItem(drawerItem);
        if (drawerLayout == null &&
            actionBarDrawerToggle == null) {
            onContentItemSelected(0);
        }
    }

    private void selectDrawerItem(int drawerItem) {
        currentOption = drawerItem;
        Fragment contentFragment = new ContentFragment();
        Bundle contentArgs = new Bundle();
        contentArgs.putInt(ContentFragment.ARG_OPTION_NUMBER, drawerItem);
        contentFragment.setArguments(contentArgs);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        setTitle(drawerOptions[drawerItem]);
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onContentItemSelected(int contentItem) {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag("detailFragment");
        if (fragment != null) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle contentArgs = new Bundle();
            contentArgs.putInt(DetailFragment.ARG_DETAIL_NUMBER, contentItem);
            detailFragment.setArguments(contentArgs);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_frame, detailFragment, "detailFragment").commit();
        } else {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(ContentFragment.ARG_OPTION_NUMBER, currentOption);
            intent.putExtra(DetailFragment.ARG_DETAIL_NUMBER, contentItem);
            startActivity(intent);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        currentTitle = title.toString();
        getSupportActionBar().setTitle(currentTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (actionBarDrawerToggle != null) {
            actionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (actionBarDrawerToggle != null) {
            actionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

}
