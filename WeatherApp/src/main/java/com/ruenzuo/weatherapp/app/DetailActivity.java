package com.ruenzuo.weatherapp.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.ContentFragment;
import com.ruenzuo.weatherapp.fragments.DetailFragment;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int option = extras.getInt(ContentFragment.ARG_OPTION_NUMBER);
            int detail = extras.getInt(DetailFragment.ARG_DETAIL_NUMBER);
            String title = "Option: " + option + ", Detail: " + detail;
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView txtDetail = (TextView) findViewById(R.id.txtDetail);
            txtDetail.setText("Detail " + detail);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
