package com.example.trademanagement.acceso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trademanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etEmail;
    EditText etPwd;

    String mail;
    String pwd;

    //TODO elementos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO cambiar id´s por etXXxLogin
        etEmail=findViewById(R.id.etEmail);
        etPwd=findViewById(R.id.etPassword);

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();
/*
        if (fu != null){
            //TODO saltar login o pedir contraseña allways?
        }else{

        }*/
    }

    public void acceder(View view) {
        mail = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();


    }


}
