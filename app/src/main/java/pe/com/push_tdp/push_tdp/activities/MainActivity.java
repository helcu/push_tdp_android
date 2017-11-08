package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.adapters.ViewPagerAdapter;
import pe.com.push_tdp.push_tdp.fragments.AllPublicationsFragment;
import pe.com.push_tdp.push_tdp.fragments.SpecificPublicationsFragment;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addNewRequest = (FloatingActionButton) findViewById(R.id.addNewCourse);
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AllPublicationsFragment(), "All Publications");
        viewPagerAdapter.addFragment(new SpecificPublicationsFragment(), "Specific Publications");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        addNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item_search = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item_search);
        return true;
    }

    /*@Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<Course> newlistCourse=new ArrayList<>();
        for(Course course : listCourse)
        {
           String nameCourse=course.getNameCourse().toLowerCase();
            if(nameCourse.contains(newText))
            {
                newlistCourse.add(course);
            }
        }
        setFilter(newlistCourse);

        return true;
    }

    public void  setFilter(ArrayList<Course> listCourses){

        listCourse.addAll(listCourses);
        courseAdapter.notifyDataSetChanged();

    }*/


}
