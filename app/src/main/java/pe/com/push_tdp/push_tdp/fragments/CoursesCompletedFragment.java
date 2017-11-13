package pe.com.push_tdp.push_tdp.fragments;

import android.content.Context;
import android.content.Intent;
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
import pe.com.push_tdp.push_tdp.activities.PublicationDetailActivity;
import pe.com.push_tdp.push_tdp.adapters.CourseAdapter;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.network.APIConnection;

public class CoursesCompletedFragment extends Fragment {

    Context context = getContext();
    APIConnection apiConnection = new APIConnection();

    RecyclerView courseRecyclerView;
    CourseAdapter courseAdapter;

    public CoursesCompletedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_courses_completed, container, false);

        List<Course> courses = new ArrayList<>();
        courseRecyclerView = view.findViewById(R.id.specificPublicationsRecyclerView);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        courseAdapter = new CourseAdapter(courses);
        courseAdapter.setCourseAdapterInterface(new CourseAdapter.CourseAdapterInterface() {
            @Override
            public void onItemClick(int courseId) {
                Intent intent = new Intent(getContext(), PublicationDetailActivity.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);
            }
        });
        courseRecyclerView.setAdapter(courseAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestCoursesCompleted();
    }

    void requestCoursesCompleted() {
        apiConnection.getCoursesCompleted(context, new APIConnection.CoursesCallback() {
            @Override
            public void onSuccessResponse(List<Course> coursesAPI) {
                courseAdapter.setListCourse(coursesAPI);
                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(String error) {
                //Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
