package com.example.android.my_hostel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Notice extends AppCompatActivity {
    Button b;
    EditText e;
    String msg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Notices");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        b=(Button) findViewById(R.id.send);
        e=(EditText) findViewById(R.id.editText);
        final HashMap<String, String> meeks = new HashMap<>();
        meeks.put("Name","Admin");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg=e.getText().toString();
                meeks.put("message",msg);
                myRef.push().setValue(msg);
                e.setText("");
                Toast.makeText(Notice.this,"Message sent",Toast.LENGTH_SHORT).show();

            }
        });


    }
    public String message()
    {
        return  msg;
    }
}
