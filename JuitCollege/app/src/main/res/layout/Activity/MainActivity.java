package com.example.collegeadminapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.collegeadminapp.Activity.Faculty.UpdateFacultyActivity;
import com.example.collegeadminapp.Activity.Notice.DeleteNoticeActivity;
import com.example.collegeadminapp.Activity.Notice.UploadNoticeActivity;
import com.example.collegeadminapp.Activity.Notifications.SendNotificationsActivity;
import com.example.collegeadminapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView addNotice, addGalleyImage, addEbook, faculty, deleteNotice, sendNotifications;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        addNotice = findViewById(R.id.addNotice);
        addGalleyImage = findViewById(R.id.addGalleyImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        sendNotifications = findViewById(R.id.sendNotifications);

        addNotice.setOnClickListener(this);
        addGalleyImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        sendNotifications.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNotice:
                startActivity(new Intent(MainActivity.this, UploadNoticeActivity.class));
                break;
            case R.id.addGalleyImage:
                startActivity(new Intent(MainActivity.this, UploadImageActivity.class));
                break;
            case R.id.addEbook:
                startActivity(new Intent(MainActivity.this, UploadPdfActivity.class));
                break;
            case R.id.faculty:
                startActivity(new Intent(MainActivity.this, UpdateFacultyActivity.class));
                break;
            case R.id.deleteNotice:
                startActivity(new Intent(MainActivity.this, DeleteNoticeActivity.class));
                break;
            case R.id.sendNotifications:
                startActivity(new Intent(MainActivity.this, SendNotificationsActivity.class));
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this,  LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}