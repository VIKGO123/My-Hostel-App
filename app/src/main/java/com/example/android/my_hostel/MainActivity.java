package com.example.android.my_hostel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
//    private static int SPLASH_TIME_OUT=4000;
    TextView n;
    Boolean res;

    CardView nt;
    TextView att;
    int attend=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        att=(TextView) findViewById(R.id.attend);
        nt=(CardView) findViewById(R.id.notice1);

        nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,NoticeStudent.class);
                startActivity(in);
            }
        });


        final String name= getIntent().getStringExtra("Name");
        n=(TextView) findViewById(R.id.name);
        n.setText("Welcome "+name);



        Switch s=(Switch) findViewById(R.id.switch1);
        s.setTextOn("Yes");
        s.setTextOff("No");
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("Messages");


        res=s.isChecked();
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              res=b;
                String result=res.toString();
                final HashMap<String, String> Geeks = new HashMap<>();
                String online;

              if(res == true)

              {


                  Geeks.put("Name",name);
                  attend=attend+1;
                  Geeks.put("Attendence",String.valueOf(attend));
                  myRef.push().setValue(Geeks);
//                    myRef.onDisconnect().setValue(ServerValue.TIMESTAMP);

                  att.setText("Your attendence has been marked");
              }

            }
        });



        CardView report1=(CardView) findViewById(R.id.Report_mess);
        report1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:MessManager@thapar.edu")); // only email apps should handle this

                intent.putExtra(Intent.EXTRA_EMAIL, "MessManager@thapar.edu");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Reporting mess issues");
//                intent.putExtra(Intent.EXTRA_TEXT,s);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        CardView menu=(CardView) findViewById(R.id.chk_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://drive.google.com/file/d/1Cqy0CLjynr9pzhrhTkfAgczxaJhF6vU1/view?usp=drivesdk"); // missing 'http://' will cause crashed
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent2);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.sign_out_menu)
        {   AuthUI.getInstance().signOut(this);
            Intent intent3=new Intent(MainActivity.this,Authentication.class);
            startActivity(intent3);
            finish();

            return true;
        }
        else
        {return super.onOptionsItemSelected(item);}
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {   Intent intent4=new Intent(MainActivity.this,AccountType.class);
            startActivity(intent4);
            intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}