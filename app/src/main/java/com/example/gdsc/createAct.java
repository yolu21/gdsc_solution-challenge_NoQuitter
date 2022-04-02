package com.example.gdsc;

import static android.view.View.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class createAct extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("activity");
    EditText reason_Act,place_Act,time_Act,title_Act;
    Button send_Act;
    Button btn_history2,btn_help2, btn_home2;
    TextView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        reason_Act = findViewById(R.id.r_Act);
        place_Act = findViewById(R.id.p_Act);
        time_Act = findViewById(R.id.t_Act);
        title_Act = findViewById(R.id.ti_Act);
        send_Act = findViewById(R.id.send_Act);
        re = findViewById(R.id.re);
        btn_help2= findViewById(R.id.btn_help2);
        btn_history2 = (Button)findViewById(R.id.btn_history2);
        btn_home2 = (Button)findViewById(R.id.btn_home2);
        send_Act.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = place_Act.getText().toString();
                String time = time_Act.getText().toString();
                String ti = title_Act.getText().toString();
                String c = reason_Act.getText().toString();
                root.child("activity").setValue(place);
                root.child(place).child("act1").child("content").setValue(c);
                root.child(place).child("act1").child("time").setValue(time);
                root.child(place).child("act1").child("title").setValue(ti);
                re.setText("Your application is currently being reviewed");
            }
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        btn_history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory2();
            }
        });
        btn_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome2();
            }
        });
        btn_help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp2();
            }
        });
    }
    public void openHistory2(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void openhome2(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void openhelp2(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }
}