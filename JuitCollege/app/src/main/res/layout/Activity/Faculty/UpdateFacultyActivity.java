package com.example.collegeadminapp.Activity.Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeadminapp.Adapters.TeacherAdapter;
import com.example.collegeadminapp.Models.TeacherData;
import com.example.collegeadminapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFacultyActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView csDepartment, civilDepartment, humanDepartment, chemistryDepartment;
    private LinearLayout csNoData, civilNoData, humannodata, chemistryNoData;
    private List<TeacherData> list1, list2, list3, list4;
    private DatabaseReference reference, dbref;
    private TeacherAdapter adapter;
    




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");
        fab = findViewById(R.id.fab);
        csDepartment = findViewById(R.id.csDepartment);
        civilDepartment = findViewById(R.id.civilDepartment);
        humanDepartment = findViewById(R.id.humanDepartment);
        chemistryDepartment = findViewById(R.id.chemistryDepartment);
        csNoData = findViewById(R.id.csNoData);
        civilNoData = findViewById(R.id.civilNoData);
        humannodata = findViewById(R.id.humannodata);
        chemistryNoData = findViewById(R.id.chemistryNoData);
        csDepartment();
        civilDepartment();
        humanDepartment();
        chemistryDepartment();





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFacultyActivity.this, AddTeachersActivity.class));
            }
        });
    }

    private void csDepartment() {
        dbref = reference.child("Computer Science Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if (!snapshot.exists()){
                    csNoData.setVisibility(View .VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View .GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFacultyActivity.this));
                    adapter = new TeacherAdapter(list1, UpdateFacultyActivity.this, "Computer Science Engineering");
                    csDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFacultyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void civilDepartment() {
        dbref = reference.child("Civil Engineering");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()){
                    civilNoData.setVisibility(View .VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }else{
                    civilNoData.setVisibility(View .GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(UpdateFacultyActivity.this));
                    adapter = new TeacherAdapter(list2, UpdateFacultyActivity.this, "Civil Engineering");
                    civilDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFacultyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void humanDepartment() {
        dbref = reference.child("Humanities Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()){
                    humannodata.setVisibility(View .VISIBLE);
                    humanDepartment.setVisibility(View.GONE);
                }else{
                    humannodata.setVisibility(View .GONE);
                    humanDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    humanDepartment.setHasFixedSize(true);
                    humanDepartment.setLayoutManager(new LinearLayoutManager(UpdateFacultyActivity.this));
                    adapter = new TeacherAdapter(list3, UpdateFacultyActivity.this, "Humanities Department");
                    humanDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFacultyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chemistryDepartment() {
        dbref = reference.child("Chemistry Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()){
                    chemistryNoData.setVisibility(View .VISIBLE);
                    chemistryDepartment.setVisibility(View.GONE);
                }else{
                    chemistryNoData.setVisibility(View .GONE);
                    chemistryDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data = snapshot1.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    chemistryDepartment.setHasFixedSize(true);
                    chemistryDepartment.setLayoutManager(new LinearLayoutManager(UpdateFacultyActivity.this));
                    adapter = new TeacherAdapter(list4, UpdateFacultyActivity.this, "Chemistry Department");
                    chemistryDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFacultyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}