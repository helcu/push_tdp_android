package pe.com.push_tdp.push_tdp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.network.APIConection;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;


public class LoginActivity extends AppCompatActivity {

    Context context = this;

    private TextInputEditText usernameTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    //REMEMBER: Buttons has to be public to be able to use in class MyAsyncTask
    public Button logInButton;
    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTextInputEditText = (TextInputEditText) findViewById(R.id.usernameTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    logInButton.setEnabled(false);

                    manageFirebase();

                    String username = usernameTextInputEditText.getText().toString();
                    String password = passwordTextInputEditText.getText().toString();
                    String accessToken = SharedPreferencesUtil.getTokenFromPrefs(context);

                    APIConection apiConection = new APIConection();
                    apiConection.login(context, username, password, accessToken, new APIConection.VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onErrorResponse(String error) {
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                            logInButton.setEnabled(true);
                        }
                    });

                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private boolean validate() {
        if (usernameTextInputEditText.getText().toString().trim().equals("") && passwordTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your email and password.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (usernameTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your email.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Please, write your password.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordTextInputEditText.getText().length() <= 4) {
            Toast.makeText(getBaseContext(), "This password is to short.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void manageFirebase() {
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferencesUtil.saveTokenToPrefs(context, token);
        Log.d("token ==> ", token);
    }
}
