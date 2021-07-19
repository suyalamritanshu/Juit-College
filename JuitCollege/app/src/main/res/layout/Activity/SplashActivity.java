package com.example.collegeadminapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeadminapp.R;

public class SplashActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1000);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        thread.start();

//        //btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                        .setTitle("Please Verify")
//                        .setDescription("User Authentication is required to proceed")
//                        .setNegativeButtonText("Cancel")
//                        .build();
//
//                getPrompt().authenticate(promptInfo);
//            }
//        });
//
//
//    }
//    private BiometricPrompt getPrompt(){
//        Executor executor = ContextCompat.getMainExecutor(this);
//        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                notifyUser(errString.toString());
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
//                notifyUser("Authentication Succeeded!!!!");
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                notifyUser("Authentication Failed!!!!!");
//            }
//        };
//
//        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
//        return biometricPrompt;
//    }
//
//
//    private void notifyUser(String message){
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
    }

}