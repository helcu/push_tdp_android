package pe.com.push_tdp.push_tdp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.adapters.ViewPagerAdapter;
import pe.com.push_tdp.push_tdp.fragments.AllPublicationsFragment;
import pe.com.push_tdp.push_tdp.fragments.SpecificPublicationsFragment;
import pe.com.push_tdp.push_tdp.models.Course;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addNewRequest = (FloatingActionButton) findViewById(R.id.addNewReqest);
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AllPublicationsFragment(), "All Publications");
        viewPagerAdapter.addFragment(new SpecificPublicationsFragment(), "Specific Publications");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        addNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddRequestActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item_search=menu.findItem(R.id.menuSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item_search);
        return true;
    }

}
