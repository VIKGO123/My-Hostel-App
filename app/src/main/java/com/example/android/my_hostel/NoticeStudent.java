package com.example.android.my_hostel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NoticeStudent extends AppCompatActivity {
    TextView t;
    String msg1;
    String tag="MYACTIVITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_student);
          t=(TextView) findViewById(R.id.admin_notice);
//             Notice n1=new Notice();
//           msg1=n1.message();
////          Log.e("check",msg1);
//           if(msg1.isEmpty())
//       {
//            t.setText("No message from admin");
//       }
//        else
//        {
//            t.setText(msg1);
//        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Notices");
        Query lastQuery = myRef.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message="";
                msg1 = dataSnapshot.getValue().toString();
              //  t.setText("");
                for(int i=22;i<msg1.length();i++)
                {if(msg1.charAt(i)!='}')
                    message=message+msg1.charAt(i);
                }
               t.setText(message);
//                Log.d(tag,message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
