package com.example.collegeadminapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.collegeadminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class UploadPdfActivity extends AppCompatActivity {

    CardView addPdf;
    private TextView pdfTv;
    private final int REQ = 1;

    private EditText PdfTitle;
    private Button uploadPdfbtn;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadUrl = "";
    private ProgressDialog dialog;
    private Uri pdfData;
    private Uri uri;
    private String pdfName;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        dialog = new ProgressDialog(this);

        addPdf = findViewById(R.id.addPdf);
        PdfTitle = findViewById(R.id.pdfTitle);
        uploadPdfbtn = findViewById(R.id.uploadPdfbtn);
        pdfTv = findViewById(R.id.pdfTextView);


        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        uploadPdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = PdfTitle.getText().toString();
                if (title.isEmpty()){
                    PdfTitle.setError("Empty");
                    PdfTitle.requestFocus();
                    
                }else if (pdfData == null){
                    Toast.makeText(UploadPdfActivity.this, "Please upload a pdf to continue.", Toast.LENGTH_SHORT).show();
                }else{
                    uploadPdf();
                }
            }
        });
    }

    private void uploadPdf() {
        dialog.setTitle("Please wait for a while");
        dialog.setTitle("Uploading Pdf");
        dialog.show();
        StorageReference reference = storageReference.child("Pdf/"+ pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri = uriTask.getResult();
                uploadData(String.valueOf(uri));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String downloadUrl) {
        String uinqueKey = databaseReference.child("Pdf").push().getKey();
        HashMap data = new HashMap();
        data.put("pdfTitle",title);
        data.put("pdfUrl",downloadUrl);

        databaseReference.child("Pdf").child(uinqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(UploadPdfActivity.this, "Pdf Uploaded Successfully.", Toast.LENGTH_SHORT).show();
                    PdfTitle.setText("");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Failed to upload the Pdf.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF file"),REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode== RESULT_OK){
          pdfData = data.getData();
          if (pdfData.toString().startsWith("content://")){
              Cursor cursor = null;
              try {
                  cursor = UploadPdfActivity.this.getContentResolver().query(pdfData,null, null, null, null);
                  if(cursor!=null && cursor.moveToFirst()){
                      pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }else if (pdfData.toString().startsWith("file://")){
              pdfName = new File(pdfData.toString()).getName();
          }
          pdfTv.setText(pdfName);

        }
    }
}