package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.network.APIConection;
import pe.com.push_tdp.push_tdp.util.Constants;

public class PublicationDetailActivity extends AppCompatActivity {

    Context context = this;
    APIConection apiConection;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Curso");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int courseId = intent.getIntExtra("courseId", -1);

        if (courseId == -1) {
            Toast.makeText(context, Constants.MESSAGE_APP_ERROR, Toast.LENGTH_SHORT).show();
            finish();
        }

        apiConection = new APIConection();
        requestCourseId(courseId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: SEND ID AND COURSE ID
                Toast.makeText(context, "TODO: SEND ID AND COURSE ID", Toast.LENGTH_SHORT).show();
            }
        });

    }


    void requestCourseId(int courseId) {
        apiConection.getCourseDetail(context, courseId, new APIConection.CourseCallback() {
            @Override
            public void onSuccessResponse(Course course) {

                getSupportActionBar().setTitle("Curso " + course.getNameCourse());

                ((TextView) findViewById(R.id.nameTextView)).setText(course.getNameCourse());
                ((TextView) findViewById(R.id.vacanciesTextView)).setText(
                        course.getNumberOfStudents() + "/" + course.getCapacity());
            }

            @Override
            public void onErrorResponse(String error) {
                Toast.makeText(context, Constants.MESSAGE_APP_ERROR, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
