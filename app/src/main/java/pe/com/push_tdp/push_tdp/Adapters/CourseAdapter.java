package pe.com.push_tdp.push_tdp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.ViewHolder;
import pe.com.push_tdp.push_tdp.models.Course;

/**
 * Created by Angelo-pooh on 23/10/2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Course> ListCourse;
    private Context context;
    private View view;

    public CourseAdapter(List<Course> listCourse) {
        ListCourse = listCourse;
    }

    public CourseAdapter() {
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_courses_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Course course=ListCourse.get(position);
        holder.imageCourse.setImageResource(course.getImageCourse());
        holder.nameCourse.setText(course.getNameCourse());
        holder.countOfStudents.setText(String.valueOf(course.getCountOfStudents()));
    }

    @Override
    public int getItemCount() {
        return ListCourse.size();
    }

    public List<Course> getListCourse() {
        return ListCourse;
    }

    public void setListMovieObject(List<Course> listMovieObject) {
        ListCourse = listMovieObject;
    }
}
