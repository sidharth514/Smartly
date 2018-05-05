package com.example.dell.smartly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Main6Activity extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
         passwordEmail=(EditText)findViewById(R.id.editText4);
         resetPassword=(Button)findViewById(R.id.button4);

         firebaseAuth=FirebaseAuth.getInstance();

         resetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String useremail=passwordEmail.getText().toString().trim();
                 if(useremail.equals(""))
                 {
                     Toast.makeText(getApplicationContext(),"Please enter your registered email id",Toast.LENGTH_SHORT).show();
                 }else{
                     firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(getApplicationContext(),"Password reset e-mail sent...",Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(Main6Activity.this,MainActivity.class));
                           }
                           else{
                               Toast.makeText(getApplicationContext(),"Error in sending password reset e-mail",Toast.LENGTH_SHORT).show();
                           }
                         }
                     });
                 }
             }
         });
    }
}
