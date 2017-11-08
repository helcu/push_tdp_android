package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.network.APIConnection;
import pe.com.push_tdp.push_tdp.util.Constants;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;

public class PublicationDetailActivity extends AppCompatActivity {

    Context context = this;
    APIConnection apiConnection;
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
        final int courseId = intent.getIntExtra("courseId", -1);

        if (courseId == -1) {
            Toast.makeText(context, Constants.MESSAGE_APP_ERROR, Toast.LENGTH_SHORT).show();
            finish();
        }

        apiConnection = new APIConnection();
        requestCourseId(courseId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = Integer.parseInt(SharedPreferencesUtil.getUserIdFromPrefs(context));
                apiConnection.subscribeCourse(context, courseId, userId, new APIConnection.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String error) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    void requestCourseId(int courseId) {
        apiConnection.getCourseDetail(context, courseId, new APIConnection.CourseCallback() {
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
