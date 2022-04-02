package com.example.gdsc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class help extends AppCompatActivity {
    private TextView txt,textView;
    private Button helpHos, helpA1, helpWord, activity;
    Button btn_history4,btn_help4, btn_home4;
    Thread thread;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        txt = findViewById(R.id.txt);
        helpHos = findViewById(R.id.helpHos);
        helpA1 = findViewById(R.id.helpA1);
        helpWord = findViewById(R.id.helpWord);
        activity = findViewById(R.id.activity);
        btn_help4= findViewById(R.id.btn_help4);
        btn_history4 = (Button)findViewById(R.id.btn_history4);
        btn_home4 = (Button)findViewById(R.id.btn_home4);
        helpHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root.child("quitSmoking").child("hospital").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String HosVal = snapshot.getValue().toString();
                        //post1.setText(data1);
                        Uri uri = Uri.parse(HosVal);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        helpA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root.child("quitSmoking").child("helpWay").child("W1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String helpWay = snapshot.getValue().toString();
                        //post1.setText(data1);
                        Uri uri1 = Uri.parse(helpWay);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri1);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        helpWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root.child("quitSmoking").child("helpWord").child("A1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String helpWay = snapshot.getValue().toString();
                        //post1.setText(data1);
                        Uri uri = Uri.parse(helpWay);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
        btn_history4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistory1();
            }
        });
        btn_home4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome1();
            }
        });
        btn_help4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhelp1();
            }
        });


    }
    public void openActivity() {
        Intent intent = new Intent(this, activity.class);
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