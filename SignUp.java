package com.example.dell.smartly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main5Activity extends AppCompatActivity {
      private TextView signupA;
      private EditText uname,upass,uemail,umobile;
      private Button usersignup;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
          uemail=(EditText)findViewById(R.id.editText5);
          upass=(EditText)findViewById(R.id.editText3);
        //umobile=(EditText)findViewById(R.id.editText4);
        uname=(EditText)findViewById(R.id.editText7);
         signupA=(TextView)findViewById(R.id.textView6);

        firebaseAuth=FirebaseAuth.getInstance();

        usersignup=(Button)findViewById(R.id.button3);

        usersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                  String user_name=uemail.getText().toString().trim();
                  String user_pass=upass.getText().toString().trim();
                    Toast.makeText(getApplicationContext(),"in box",Toast.LENGTH_LONG).show();
                  firebaseAuth.createUserWithEmailAndPassword(user_name,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                              startActivity(new Intent(Main5Activity.this,MainActivity.class));
                          }
                          else{
                              Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                          }
                      }
                  });
              }
            }
        });
        signupA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main5Activity.this,MainActivity.class));
            }
        });
    }
    private Boolean validate()
    {
        Boolean result=false;
        String name=uname.getText().toString();
        String password=upass.getText().toString();
        String email=uemail.getText().toString();

        if(name.isEmpty()||password.isEmpty()||email.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_LONG).show();
        }else{
        result= true;
        }
        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
        return  result;
    }
}
