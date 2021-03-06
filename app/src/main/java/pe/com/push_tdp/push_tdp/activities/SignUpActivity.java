package pe.com.push_tdp.push_tdp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.network.APIConnection;

public class SignUpActivity extends AppCompatActivity {

    Context context = this;

    private TextInputEditText userTextInputEditText;
    private TextInputEditText lastNameTextInputEditText;
    private TextInputEditText firstNameTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private APIConnection apiConnection = new APIConnection();

    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userTextInputEditText = (TextInputEditText) findViewById(R.id.userTextInputEditText);
        lastNameTextInputEditText = (TextInputEditText) findViewById(R.id.lastNameTextInputEditText);
        firstNameTextInputEditText = (TextInputEditText) findViewById(R.id.firstNameTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);

        signUpButton = (Button) findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpButton.setEnabled(false);

                if (validate()) {
                    final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Autentificando...");
                    progressDialog.show();

                    String username = userTextInputEditText.getText().toString();
                    String name = firstNameTextInputEditText.getText().toString();
                    String lastName = lastNameTextInputEditText.getText().toString();
                    String password = passwordTextInputEditText.getText().toString();
                    String token_id = "";

                    apiConnection.register(context, username, password, name, lastName, token_id, new APIConnection.VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            finish();
                        }

                        @Override
                        public void onErrorResponse(String error) {
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            signUpButton.setEnabled(true);
                        }
                    });
                }
            }
        });

    }

    private boolean validate() {
        if (userTextInputEditText.getText().toString().trim().equals("") && passwordTextInputEditText.getText().toString().trim().equals("") && firstNameTextInputEditText.getText().toString().trim().equals("") && lastNameTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escribe todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escriba su usuario.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (firstNameTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escriba su nombre.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (lastNameTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor, escriba su apellido.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordTextInputEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Por favor,escriba su contraseña.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordTextInputEditText.getText().length() <= 4) {
            Toast.makeText(getBaseContext(), "Su contraseña es muy corta.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }


}
