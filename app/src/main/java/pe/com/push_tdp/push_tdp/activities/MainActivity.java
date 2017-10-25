package pe.com.push_tdp.push_tdp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import pe.com.push_tdp.push_tdp.Adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.Course;

import static pe.com.push_tdp.push_tdp.R.drawable.estadistica;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabs = (TabLayout) findViewById(R.id.tab);
        setSupportActionBar(toolbar);

        tabs.addTab(tabs.newTab().setText("NEWTAB"));
        tabs.addTab(tabs.newTab().setText("TUYOS"));
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        final ArrayList<Course> listCourse=new ArrayList<Course>();

        listCourse.add(new Course(1,"Redes y Comunicacion de Datos",10, R.drawable.redes));

        listCourse.add(new Course(2,"Estadistica Aplicada 1",20, R.drawable.estadistica));


        listCourse.add(new Course(3,"Calculo 2",25, R.drawable.calculo2));


        RecyclerView contenedor=(RecyclerView) findViewById(R.id.contenedor);
        contenedor.setHasFixedSize(true);

        LinearLayoutManager layout=new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedor.setAdapter(new CourseAdapter(listCourse));
        contenedor.setLayoutManager(layout);

        FloatingActionButton addNewRequest = (FloatingActionButton) findViewById(R.id.addNewReqest);
        addNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddRequestActivity.class);
                startActivity(intent);
            }
        });
    }

}
