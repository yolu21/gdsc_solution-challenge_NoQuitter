package com.example.gdsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity extends AppCompatActivity {
    private TextView ActTitle,ActTime,ActContnet;
    private EditText location;
    private Button checkLoc,createAct;
    Button btn_history1,btn_help1, btn_home1;
    private RelativeLayout rel1;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold);
        ActTitle = findViewById(R.id.ActTitle);
        ActTime = findViewById(R.id.ActTime);
        ActContnet = findViewById(R.id.ActContent);
        location = findViewById(R.id.location);
        rel1 = findViewById(R.id.rel1);
        checkLoc=(Button)findViewById(R.id.checkLoc);
        createAct=(Button)findViewById(R.id.createAct);
        btn_help1= findViewById(R.id.btn_help1);
        btn_history1 = (Button)findViewById(R.id.btn_history1);
        btn_home1 = (Button)findViewById(R.id.btn_home1);
        checkLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc=location.getText().toString();
                root.child("activity").child(loc).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        rel1.setVisibility(View.VISIBLE);
                        String Title = snapshot.child("act1").child("title").getValue().toString();
                        String Time = snapshot.child("act1").child("time").getValue().toString();
                        String Con = snapshot.child("act1").child("content").getValue().toString();
                        ActTitle.setText(Title);
                        ActTime.setText(Time);
                        ActContnet.setText(Con);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        createAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreate();
            }
        });
        btn_history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory1();
            }
        });
        btn_home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome1();
            }
        });
        btn_help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp1();
            }
        });

    }
    public void openCreate(){
        Intent intent = new Intent(this, createAct.class);
        startActivity(intent);
    }
    public void openHistory1(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void openhome1(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void openhelp1(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }

}
