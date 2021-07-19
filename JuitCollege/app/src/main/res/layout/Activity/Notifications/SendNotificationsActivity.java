
package com.example.collegeadminapp.Activity.Notifications;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeadminapp.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class SendNotificationsActivity extends AppCompatActivity {

    EditText title;
    EditText message;
    Button sendBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notifications);


        FirebaseMessaging.getInstance().subscribeToTopic("all");





        title = findViewById(R.id.editTexttitle);
        message = findViewById(R.id.editTextmessage);

        sendBtn = findViewById(R.id.sendBtn);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){
                    SendingNotifications sendingNotifications = new SendingNotifications("/topics/all", title.getText().toString(), message.getText().toString(),getApplicationContext(), SendNotificationsActivity.this);
                    sendingNotifications.SendNotifications();
                }else{
                    Toast.makeText(SendNotificationsActivity.this, "Enter title and message first.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}