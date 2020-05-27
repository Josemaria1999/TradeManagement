package com.example.trademanagement.acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    //TODO elementos
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
            //TODO saltar login o pedir contraseña allways?
            etEmail.setText(fu.getEmail());
        }
    }

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

                            }else{

                            }
                        }
                    });
        }

        mail = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();



    }

    private String comprobar() {
        String msj=null;

        if (mail.equals("") || pwd.equals("") ){
            msj = "introduce tu e-mail y contraseña";
        }
       return msj;
    }


}
