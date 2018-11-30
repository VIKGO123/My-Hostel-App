package com.example.android.my_hostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity  {
    EditText e;
    String pass;
    String s;
    Button b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        e=(EditText) findViewById(R.id.admin_password);
        b1=(Button) findViewById(R.id.submit);


        s="2708";

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pass=e.getEditableText().toString();
                    if(pass.equals(s))
                    {Toast.makeText(Admin.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                        Intent intent3=new Intent(Admin.this,AdminView.class);
                        startActivity(intent3);
                    }
                    else
                    {
                        Toast.makeText(Admin.this,"Wrong Passkey",Toast.LENGTH_SHORT).show();
                    }

                }
            });



    }
}
