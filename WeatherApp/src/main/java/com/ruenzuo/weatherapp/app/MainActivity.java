package com.ruenzuo.weatherapp.app;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.ContentFragment;
import com.ruenzuo.weatherapp.fragments.DetailFragment;
import com.ruenzuo.weatherapp.fragments.MenuFragment;

public class MainActivity extends ActionBarActivity implements ContentFragment.OnItemSelectedListener, MenuFragment.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
        MenuFragment menuFragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.left_drawer);
        drawerList = menuFragment.getListView();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        /*
        if (!(screenType.equals("phone-port") ||
              screenType.equals("phone-land"))) {
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.content_frame);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            if(params.leftMargin == (int)getResources().getDimension(R.dimen.drawer_size)) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.setScrimColor(getResources().getColor(R.color.drawer_no_shadow));
                isDrawerLocked = true;
            }
        }
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(currentTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
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
    public void onContentItemSelected(int content) {
        /*
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag("detailFragment");
        if (fragment != null && fragment.isInLayout()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();
            fragmentManager.beginTransaction().replace(R.id.detail_frame, detailFragment, "detailFragment").commit();
        } else {
        */
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(ContentFragment.ARG_OPTION_NUMBER, currentOption);
            intent.putExtra(DetailFragment.ARG_DETAIL_NUMBER, content);
            startActivity(intent);
        //}
    }

    @Override
    public void onMenuItemSelected(int option) {
        selectItem(option);
    }

    private void selectItem(int position) {
        currentOption = position;
        Fragment contentFragment = new ContentFragment();
        Bundle contentArgs = new Bundle();
        contentArgs.putInt(ContentFragment.ARG_OPTION_NUMBER, position);
        contentFragment.setArguments(contentArgs);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        drawerList.setItemChecked(position, true);
        setTitle(drawerOptions[position]);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void setTitle(CharSequence title) {
        currentTitle = title.toString();
        getSupportActionBar().setTitle(currentTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

}
