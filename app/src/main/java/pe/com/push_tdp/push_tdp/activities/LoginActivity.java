package pe.com.push_tdp.push_tdp.activities;

import android.app.ProgressDialog;
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
import pe.com.push_tdp.push_tdp.network.APIConnection;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;


public class LoginActivity extends AppCompatActivity {

    Context context = this;

    private TextInputEditText usernameTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    //REMEMBER: Buttons has to be public to be able to use in class MyAsyncTask
    public Button logInButton;
    public Button signUpButton;
    private APIConnection apiConnection = new APIConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPreferencesUtil.getKeepLogged(context)) {
            manageFirebase();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        usernameTextInputEditText = (TextInputEditText) findViewById(R.id.usernameTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    logInButton.setEnabled(false);
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Autentificando...");
                    progressDialog.show();

                    manageFirebase();

                    String username = usernameTextInputEditText.getText().toString();
                    String password = passwordTextInputEditText.getText().toString();
                    String accessToken = SharedPreferencesUtil.getTokenFromPrefs(context);

                    apiConnection.login(context, username, password, accessToken, new APIConnection.VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(context, MainActivity.class));
                            SharedPreferencesUtil.setKeepLogged(context, true);
                            // TODO: activate firebase
                            finish();
                        }

                        @Override
                        public void onErrorResponse(String error) {
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
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
                finish();
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
