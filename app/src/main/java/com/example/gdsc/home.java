package com.example.gdsc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

public class home extends AppCompatActivity {
    private TextView date_count;
    private Button btn_end,btn_history, btn_smoking,signout,btn_help;
    FirebaseAuth mAuth;
    FirebaseUser msUser = mAuth.getInstance().getCurrentUser();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        date_count = (TextView) findViewById(R.id.date_count);
        btn_end = (Button)findViewById(R.id.btn_end);
        btn_history = (Button)findViewById(R.id.btn_history);
        btn_smoking = (Button)findViewById(R.id.btn_smoking);
        btn_help= (Button)findViewById(R.id.btn_help);
        signout = (Button)findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();
        msUser = mAuth.getCurrentUser();
        String date = getDate();
        date_count.setText(date+" day");


        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory();
            }
        });
        btn_smoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensmoking();
            }
        });
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                logout();
            }
        });
    }

    private String getDate() {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        //return String.valueOf((msUser.getMetadata().getCreationTimestamp()));
        //return formatter.format(mUser.getMetadata().getCreationTimestamp());
        float day = System.currentTimeMillis() - msUser.getMetadata().getCreationTimestamp();
        day = day/ 1000;
        day = day/ 60;
        day = day/ 60;
        day = day/ 24;
        int res = (int)day;
        return String.valueOf(res);

    }

    private void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, diary.class);
        startActivity(intent);
    }
    public void openHistory(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void opensmoking(){
        Intent intent = new Intent(this, want_smoking.class);
        startActivity(intent);
    }
    public void openhelp(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }

}