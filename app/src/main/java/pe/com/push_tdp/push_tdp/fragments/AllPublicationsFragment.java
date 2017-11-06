package pe.com.push_tdp.push_tdp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.models.Course;

public class AllPublicationsFragment extends Fragment {
    RecyclerView courseRecyclerView;

    public AllPublicationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_publications, container, false);

        final List<Course> listCourse=new ArrayList<Course>();
        listCourse.add(new Course(1,"Redes y Comunicacion de Datos",10, "https://www.informatica-hoy.com.ar/redes/imagenes/redes-informaticas_clip_image012.jpg"));
        listCourse.add(new Course(2,"Estadistica Aplicada 1",20,"https://cdn.geogebra.org/material/EI3VYWGILDOAdW4LKDKopvDqBzqC9Fot/material-ieKWOaRF.png"));
        listCourse.add(new Course(3,"Calculo 2",25, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Spherical_Coordinates_%28Colatitude%2C_Longitude%29.svg/360px-Spherical_Coordinates_%28Colatitude%2C_Longitude%29.svg.png"));

        courseRecyclerView = (RecyclerView) view.findViewById(R.id.allPublicationRecyclerView);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        courseRecyclerView.setAdapter(new CourseAdapter(listCourse));

        return view;
    }
}
