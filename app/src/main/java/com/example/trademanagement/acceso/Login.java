package com.example.trademanagement.acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trademanagement.MainActivity;
import com.example.trademanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etEmail;
    EditText etPwd;

    String mail;
    String pwd;

    //TODO SplashScreen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO cambiar id´s por etXXxLogin
        etEmail=findViewById(R.id.etEmail);
        etPwd=findViewById(R.id.etPasswordLogin);

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        if (fu != null){
            
            etEmail.setText(fu.getEmail());
        }else {
            etEmail.setText("");
            etPwd.setText("");
        }
    }

    //TODO casilla recordar?

    public void acceder(View view) {

        String msj = comprobar();
        if(comprobar()!=null){
            Toast.makeText(this,msj,Toast.LENGTH_LONG).show();
        }else{
            fa.signInWithEmailAndPassword(mail, pwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fu = fa.getCurrentUser();
                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);
                                //putExtra?
                            }else{
                                Toast.makeText(Login.this,"fok",Toast.LENGTH_SHORT).show();
                                //TODO onFailureListener
                            }
                        }
                    });
        }
    }

    private String comprobar() {
        mail = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();
        String msj=null;

        if (mail.isEmpty() || pwd.isEmpty()){
            msj = "introduce tu e-mail y contraseña";
        }
       return msj;
    }


}
