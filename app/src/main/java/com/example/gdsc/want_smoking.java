package com.example.gdsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class want_smoking extends AppCompatActivity {
    private static final String TAG = "ReadAndWriteSnippets";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView reason, thing1, thing2, thing3 ;
    Button btn_history6,btn_help6, btn_home6;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_smoking);
        reason = findViewById(R.id.reason_show);
        thing1 = findViewById(R.id.thing1_show);
        thing2 = findViewById(R.id.thing2_show);
        thing3 = findViewById(R.id.thing3_show);
        btn_help6= findViewById(R.id.btn_help6);
        btn_history6 = (Button)findViewById(R.id.btn_history6);
        btn_home6 = (Button)findViewById(R.id.btn_home6);
        GetThing();
        btn_history6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory6();
            }
        });
        btn_home6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome6();
            }
        });
        btn_help6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp6();
            }
        });
    }

    private void GetThing() {
        String uid = user.getUid();
        myRef.child("thing").child(uid).child("thing1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String thing1_str = snapshot.getValue(String.class);
                thing1.setText(thing1_str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("thing").child(uid).child("thing2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String thing2_str = snapshot.getValue(String.class);
                thing2.setText(thing2_str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("thing").child(uid).child("thing3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String thing3_str = snapshot.getValue(String.class);
                thing3.setText(thing3_str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.child("reason").child(uid).child("reason").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String reason_str = snapshot.getValue(String.class);
                reason.setText(reason_str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void openHistory6(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void openhome6(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void openhelp6(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }
}