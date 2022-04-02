package com.example.gdsc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class diary extends AppCompatActivity {
    private EditText daily;
    private Button btn_daily;
    Button btn_history3,btn_help3, btn_home3;
    FirebaseAuth mAuth;
    FirebaseUser msUser = mAuth.getInstance().getCurrentUser();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("article");
    public class Diary
    {
        private String id;
        private String word;
        public Diary(){}
        @RequiresApi(api = Build.VERSION_CODES.O)
        public Diary(String id, String word)
        {
            LocalDate date = LocalDate.now();
            this.id = id+date.toString();
            this.word = word;
        }
        public String getId(){
            return this.id=id;
        }
        public String getWord(){
            return this.word=word;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);
        daily = findViewById(R.id.daily);
        btn_help3= findViewById(R.id.btn_help3);
        btn_history3 = (Button)findViewById(R.id.btn_history3);
        btn_home3 = (Button)findViewById(R.id.btn_home3);
        btn_daily = (Button)findViewById(R.id.btn_daily);
        btn_daily.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String uid = msUser.getUid();
                Diary data=new Diary("email",daily.getText().toString());
                root.child(uid).child(getDate()).setValue(data.word);
                diary.this.finish();
            }
        });
        btn_history3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory3();
            }
        });
        btn_home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome3();
            }
        });
        btn_help3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp3();
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
    public void openHistory3(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void openhome3(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void openhelp3(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }
}
