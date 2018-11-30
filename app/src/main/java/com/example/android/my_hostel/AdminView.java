package com.example.android.my_hostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminView extends AppCompatActivity {
    CardView cardView;
    String attendence;
    TextView m;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_admin);
        m=(TextView) findViewById(R.id.meal_attend);


        cardView=(CardView) findViewById(R.id.notice_card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminView.this,Notice.class);
                startActivity(i);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Messages");
        Query lastQuery = myRef.orderByKey().limitToLast(1);

        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message="";
                String al;
//                HashMap<String, String> Geeks = new HashMap<>();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    m.setText((ds.child("Attendence").getValue()).toString());
                }
//                message=al;
//                 // t.setText("");
//
//                m.setText(message);
//                Log.d(tag,message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
