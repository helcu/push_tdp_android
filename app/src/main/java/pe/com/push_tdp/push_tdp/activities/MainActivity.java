package pe.com.push_tdp.push_tdp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.Course;

public class MainActivity extends AppCompatActivity {
    RecyclerView courseRecyclerView;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ArrayList<Course> listCourse=new ArrayList<Course>();
        listCourse.add(new Course(1,"Redes y Comunicacion de Datos",10, "https://www.informatica-hoy.com.ar/redes/imagenes/redes-informaticas_clip_image012.jpg"));
        listCourse.add(new Course(2,"Estadistica Aplicada 1",20,"https://cdn.geogebra.org/material/EI3VYWGILDOAdW4LKDKopvDqBzqC9Fot/material-ieKWOaRF.png"));
        listCourse.add(new Course(3,"Calculo 2",25, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Spherical_Coordinates_%28Colatitude%2C_Longitude%29.svg/360px-Spherical_Coordinates_%28Colatitude%2C_Longitude%29.svg.png"));

        courseRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setAdapter(new CourseAdapter(listCourse));

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
