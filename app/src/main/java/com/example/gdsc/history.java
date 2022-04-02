package com.example.gdsc;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class history extends AppCompatActivity {
    TextView txv_his, txv_showhis;
    Button btn_his, btn_history5,btn_help5, btn_home5;
    EditText edt_his;
    FirebaseAuth mAuth;
    FirebaseUser msUser = mAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        txv_his = findViewById(R.id.txv_his);
        btn_his = findViewById(R.id.btn_his);
        edt_his = findViewById(R.id.edt_his);
        txv_showhis = findViewById(R.id.txv_showhis);
        btn_help5= findViewById(R.id.btn_help5);
        btn_history5 = (Button)findViewById(R.id.btn_history5);
        btn_home5 = (Button)findViewById(R.id.btn_home5);
        txv_his.setText("You can enter the day to see your diary Between 0 - "+getDate());


        btn_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edt_his.getText().toString();
                String uid = msUser.getUid();
                myRef.child("article").child(uid).child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String txv_showhis_str = snapshot.getValue(String.class);
                        txv_showhis.setVisibility(View.VISIBLE);
                        txv_showhis.setText(txv_showhis_str);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        txv_showhis.setText("error");
                    }
                });
            }
        });
        btn_history5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory5();
            }
        });
        btn_home5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome5();
            }
        });
        btn_help5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp5();
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
    public void openHistory5(){
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
    public void openhome5(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void openhelp5(){
        Intent intent = new Intent(this, help.class);
        startActivity(intent);
    }
}