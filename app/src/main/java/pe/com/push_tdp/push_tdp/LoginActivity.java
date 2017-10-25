package pe.com.push_tdp.push_tdp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import pe.com.push_tdp.push_tdp.activities.AddRequestActivity;
import pe.com.push_tdp.push_tdp.activities.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_SHORT).show();
    }
}
