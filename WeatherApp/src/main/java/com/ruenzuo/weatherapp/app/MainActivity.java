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

public class MainActivity extends ActionBarActivity implements ContentFragment.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String drawerTitle;
    private String currentTitle;
    private String[] drawerOptions;

    private boolean isDrawerLocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String screenType = getResources().getString(R.string.screen_type);
        setContentView(R.layout.activity_main);
        currentTitle = getTitle().toString();
        drawerTitle = getResources().getString(R.string.drawer_title);
        drawerOptions = getResources().getStringArray(R.array.drawer_options);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, drawerOptions));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
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
        if (!isDrawerLocked) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close) {
                public void onDrawerClosed(View view) {
                    getSupportActionBar().setTitle(currentTitle);
                }

                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(drawerTitle);
                }
            };
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
        }
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(drawerList)) {
                    drawerLayout.closeDrawer(drawerList);
                }
                else {
                    drawerLayout.openDrawer(drawerList);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onContentItemSelected(int content) {
        String detail = currentTitle + " Content: " + content;
        /*
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag("detailFragment");
        if (fragment != null && fragment.isInLayout()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();
            fragmentManager.beginTransaction().replace(R.id.detail_frame, detailFragment, "detailFragment").commit();
        } else {
        */
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DETAIL, detail);
            startActivity(intent);
        //}
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment contentFragment = new ContentFragment();
        Bundle contentArgs = new Bundle();
        contentArgs.putInt(ContentFragment.ARG_OPTION_NUMBER, position);
        contentFragment.setArguments(contentArgs);

        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.main_frame, contentFragment);


        DetailFragment detailFragment = new DetailFragment();
        transaction.replace(R.id.detail_frame, detailFragment, "detailFragment");

        /*
        Fragment contentFragment2 = new ContentFragment();
        Bundle contentArgs2 = new Bundle();
        contentArgs2.putInt(ContentFragment.ARG_OPTION_NUMBER, position);
        contentFragment2.setArguments(contentArgs2);
        transaction.replace(R.id.detail_frame, contentFragment2, "detailFragment");
        */
        transaction.commit();
        //detailFragment.setText(currentTitle + " Content: " + 0);

        drawerList.setItemChecked(position, true);
        setTitle(drawerOptions[position]);
        if (!isDrawerLocked) {
            drawerLayout.closeDrawer(drawerList);
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
        if (!isDrawerLocked) {
            actionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!isDrawerLocked) {
            actionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

}
