package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.network.APIConnection;

public class AddCourseActivity extends AppCompatActivity {
    Context context = this;

    private TextInputEditText courseNameTextInputEditText;
    private TextInputEditText vacanciesTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton registerFloatingActionButton = (FloatingActionButton) findViewById(R.id.regiterNewCourse);

        courseNameTextInputEditText = (TextInputEditText) findViewById(R.id.courseTextInputEditText);
        vacanciesTextInputEditText = (TextInputEditText) findViewById(R.id.vacanciesTextInputEditText);

        registerFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerFloatingActionButton.setEnabled(false);

                if (validate()) {
                    String courseName = courseNameTextInputEditText.getText().toString();
                    int vacancies = Integer.parseInt(vacanciesTextInputEditText.getText().toString());
                    String urlImage = "";

                    APIConnection apiConnection = new APIConnection();
                    apiConnection.addCourse(context, courseName, vacancies, new APIConnection.VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onErrorResponse(String error) {
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                            registerFloatingActionButton.setEnabled(true);
                        }
                    });
                }
            }
        });
    }

    private boolean validate() {
        if (courseNameTextInputEditText.getText().toString().trim().equals("") && vacanciesTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escribe todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (courseNameTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escriba el nombre del curso.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (vacanciesTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escriba el número de vacantes.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

}
