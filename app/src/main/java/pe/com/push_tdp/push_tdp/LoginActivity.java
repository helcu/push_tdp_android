package pe.com.push_tdp.push_tdp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    //REMEMBER: Buttons has to be public to be able to use in class MyAsyncTask
    public Button logInButton;
    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTextInputEditText = (TextInputEditText) findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    logInButton.setEnabled(false);

                    //TODO: consume the service asynchronously
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean validate(){
        if (emailTextInputEditText.getText().toString().trim().equals("") && passwordTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your email and password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (emailTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your email.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTextInputEditText.getText()).matches()) {
            Toast.makeText(getBaseContext(), "This email address is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordTextInputEditText.getText().length() <= 4) {
            Toast.makeText(getBaseContext(), "This password is to short.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}
