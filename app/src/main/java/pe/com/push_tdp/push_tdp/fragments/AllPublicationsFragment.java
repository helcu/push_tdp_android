package pe.com.push_tdp.push_tdp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.network.APIConection;

public class AllPublicationsFragment extends Fragment {

    Context context = getContext();

    List<Course> courses;
    RecyclerView courseRecyclerView;
    CourseAdapter courseAdapter;

    public AllPublicationsFragment() {
        APIConection apiConection = new APIConection();

        apiConection.getCourses(context, new APIConection.CoursesCallback() {
            @Override
            public void onSuccessResponse(List<Course> coursesAPI) {
                courseAdapter.setListCourse(coursesAPI);
                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_all_publications, container, false);

        courses = new ArrayList<>();
        courseRecyclerView = view.findViewById(R.id.allPublicationRecyclerView);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        courseAdapter = new CourseAdapter(courses);
        courseRecyclerView.setAdapter(courseAdapter);

        return view;
    }
}
