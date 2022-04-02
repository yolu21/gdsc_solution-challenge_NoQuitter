package com.example.gdsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class after_register extends AppCompatActivity {
    EditText thing1, thing2, thing3, reason;
    Button send_reason;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_register);
        reason = findViewById(R.id.reason);
        thing1 = findViewById(R.id.thing1);
        thing2 = findViewById(R.id.thing2);
        thing3 = findViewById(R.id.thing3);
        send_reason = findViewById(R.id.send_reason);

        send_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewThing(thing1.getText().toString(), thing2.getText().toString(), thing3.getText().toString(), reason.getText().toString());
                Finish();
            }

            private void Finish() {
                Intent intent = new Intent(after_register.this, home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
    public void writeNewThing(String thing1, String thing2, String thing3, String reason) {
        String uid = user.getUid();
        myRef.child("thing").child(uid).child("thing1").setValue(thing1);
        myRef.child("thing").child(uid).child("thing2").setValue(thing2);
        myRef.child("thing").child(uid).child("thing3").setValue(thing3);
        myRef.child("reason").child(uid).child("reason").setValue(reason);
    }
}