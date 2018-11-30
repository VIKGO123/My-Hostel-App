package com.example.android.my_hostel;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;

import java.util.Calendar;

public class AccountType extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_account);
        CardView c=(CardView) findViewById(R.id.student_card);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(AccountType.this,Authentication.class);
                startActivity(intent1);
            }
        });
        CardView c1=(CardView) findViewById(R.id.admin_card);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(AccountType.this,Admin.class);
                startActivity(intent2);
            }
        });



        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR,1);
        cal.set(Calendar.MINUTE,52);
        cal.set(Calendar.SECOND,10);
//        c.add(Calendar.SECOND,10);

        Intent in=new Intent(AccountType.this,Notification_receiver.class);

        PendingIntent alarmIntentRTC = PendingIntent.getBroadcast(AccountType.this, 100, in, PendingIntent.FLAG_UPDATE_CURRENT);

        //getting instance of AlarmManager service
       AlarmManager alarmManagerRTC = (AlarmManager)AccountType.this.getSystemService(ALARM_SERVICE);

        //Setting alarm to wake up device every day for clock time.
        //AlarmManager.RTC_WAKEUP is responsible to wake up device for sure, which may not be good practice all the time.
        // Use this when you know what you're doing.
        //Use RTC when you don't need to wake up device, but want to deliver the notification whenever device is woke-up
        //We'll be using RTC.WAKEUP for demo purpose only
        alarmManagerRTC.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntentRTC);






    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);




            this.finish();
        }

        return super.onKeyDown(keyCode, event);
    }
    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


}
