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

public class RegistroUser extends AppCompatActivity {

    //Private?
    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etNomApel;
    EditText etTlfno;
    EditText etNick;
    EditText etEmail;
    EditText etPwd;
    EditText etPwdConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        etNomApel=findViewById(R.id.etNombreApellidosReg);
        etTlfno=findViewById(R.id.etTlfnoReg);
        etNick=findViewById(R.id.etUserNameReg);
        etEmail=findViewById(R.id.etUserMailReg);
        etPwd=findViewById(R.id.etUserPwdReg);
        etPwdConf=findViewById(R.id.etUserPwdConfReg);

        fa = FirebaseAuth.getInstance();
    }
    public void registrar(View view){
        String nomApel= etNomApel.getText().toString();
        String tlfno= etTlfno.getText().toString();
        String email = etEmail.getText().toString();
        String nick= etNick.getText().toString();
        String pwd= etPwd.getText().toString();
        String pwdConf= etPwdConf.getText().toString();

        if (nomApel.equals("") || tlfno.equals("") || email.equals("") || nick.equals("") || pwd.equals("") || pwdConf.equals("")){
            Toast.makeText(this,"Uno de los campos está vacio",Toast.LENGTH_LONG).show();

        }else if(!pwd.equals(pwdConf)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }else if (tlfno.length()<9){
            Toast.makeText(this, "El telefono tiene que tener al menos 9 dígitos ", Toast.LENGTH_LONG).show();
        }else{
             fa.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         fu = fa.getCurrentUser();
                         Toast.makeText(RegistroUser.this, getString(R.string.toast_userreg_ok),Toast.LENGTH_SHORT).show();
                     }else {
                         //Exception exception = task.getException();
                         Toast.makeText(RegistroUser.this, getString(R.string.toast_userreg_err),Toast.LENGTH_SHORT).show();
                     }
                 }
             });

        }
        etTlfno.setText(">"+tlfno);
        etNomApel.setText(">"+nomApel);
        etEmail.setText(">"+email);
        etNick.setText(">"+nick);
        etPwd.setText(">"+pwd);
        etPwdConf.setText(">"+pwdConf);
    }
}
