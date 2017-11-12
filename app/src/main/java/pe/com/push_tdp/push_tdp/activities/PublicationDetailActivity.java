package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.adapters.UserAdapter;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.models.User;
import pe.com.push_tdp.push_tdp.network.APIConnection;
import pe.com.push_tdp.push_tdp.util.Constants;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;

public class PublicationDetailActivity extends AppCompatActivity {

    Context context = this;
    APIConnection apiConnection;
    Toolbar toolbar;
    UserAdapter userAdapter;
    RecyclerView studentsRecyclerView;
    CardView cardView;

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
        refreshCourseAndStudent(courseId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = Integer.parseInt(SharedPreferencesUtil.getUserIdFromPrefs(context));
                apiConnection.subscribeCourse(context, courseId, userId, new APIConnection.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        refreshCourseAndStudent(courseId);
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorResponse(String error) {
                        //Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    void requestCourseId(int courseId) {
        apiConnection.getCourseDetail(context, courseId, new APIConnection.CourseCallback() {
            @Override
            public void onSuccessResponse(Course course) {

                getSupportActionBar().setTitle(course.getNameCourse());

                //((TextView) findViewById(R.id.nameTextView)).setText(course.getNameCourse());
                ((TextView) findViewById(R.id.vacanciesTextView)).setText(
                        course.getNumberOfStudents() + "/" + course.getCapacity());
            }

            @Override
            public void onErrorResponse(String error) {
                //Toast.makeText(context, Constants.MESSAGE_APP_ERROR, Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }

    void studentsOfaCourse(int courseId) {
        apiConnection.usersSubscribed(context, courseId, new APIConnection.UsersCallback() {
            @Override
            public void onSuccessResponse(List<User> users) {
                studentsRecyclerView = (RecyclerView) findViewById(R.id.studentsRecyclerView);
                studentsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                userAdapter = new UserAdapter(users);
                studentsRecyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onErrorResponse(String error) {
                //Toast.makeText(context, Constants.MESSAGE_APP_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void refreshCourseAndStudent(int courseId) {
        requestCourseId(courseId);
        studentsOfaCourse(courseId);
    }

}
