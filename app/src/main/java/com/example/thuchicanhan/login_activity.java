package com.example.thuchicanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button btn_login;
        final EditText et_email;
        final EditText et_password;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        btn_login=(Button)findViewById(R.id.btn_login);
        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go= new Intent(login_activity.this,MainActivity.class);
                String email="ngocthienTCVan.b10@gmail.com";
                String password="admin";
                if(et_email.getText().toString().equals(email)&&et_password.getText().toString().equals(password))
                {
                    startActivity(go);

                }
                else
                {

                    Toast.makeText(login_activity.this,"Login fail",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
