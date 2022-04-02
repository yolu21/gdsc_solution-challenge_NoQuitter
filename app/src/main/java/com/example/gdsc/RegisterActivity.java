package com.example.gdsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText inputemail, inputpassword, inputpassword_confirm;
    Button send;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        inputpassword_confirm = findViewById(R.id.password_confirm);
        send = (Button) findViewById(R.id.send);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth() {
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();
        String password_confirm = inputpassword_confirm.getText().toString();

        if (!email.matches(emailPattern)){
            inputemail.requestFocus();
        }
        else if (password.isEmpty() || password.length()<6){
            inputpassword.setError("enter the password");
        }
        else if (!password.equals(password_confirm)){
            inputpassword_confirm.setError("password confirm different");
        }
        else {
            progressDialog.setMessage("registering...");
            progressDialog.setTitle("Registion");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        first_time_register();
                        Toast.makeText(RegisterActivity.this, "registration complete", Toast.LENGTH_SHORT ).show();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT ).show();

                    }

                }
            });
        }
    }

    private void first_time_register() {
        Intent intent = new Intent(RegisterActivity.this, after_register.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}