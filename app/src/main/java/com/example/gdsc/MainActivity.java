package com.example.gdsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText inputemail, inputpassword;
    Button btnlogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);

        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnlogin = findViewById(R.id.ButtonLogin);

        final CheckBox CodeCheckBox = (CheckBox) findViewById(R.id.CheckBox01);
        final TextView tv1 = (TextView) findViewById(R.id.TextViewCheckBox);


        final Button ButtonRegister = (Button) findViewById(R.id.ButtonRegister);
        tv1.setText("show the password");

        CodeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (CodeCheckBox.isChecked()) {
                    inputpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    inputpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login() {
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();


        if (!email.matches(emailPattern)) {
            inputemail.requestFocus();
        } else if (password.isEmpty() || password.length() < 6) {
            inputpassword.setError("enter the password");
        } else {
            progressDialog.setMessage("login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        main_page();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT ).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT ).show();

                    }
                }
            });
        }
    }

    private void main_page() {
        Intent intent = new Intent(MainActivity.this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}