package com.example.dell.smartly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView login,signup,fp,attempt;
    EditText uname,upass;
    FirebaseAuth firebaseAuth;
    private int counter=5;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(TextView)findViewById(R.id.textView);
        signup=(TextView)findViewById(R.id.textView4);
        fp=(TextView)findViewById(R.id.textView2);
        attempt=(TextView)findViewById(R.id.textView5);
        uname=(EditText)findViewById(R.id.editText2);
        upass=(EditText)findViewById(R.id.editText8);

        attempt.setText("Attempts"+counter);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();

            if(user!=null)
            {
                finish();
                openActivity2();
            }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"login",Toast.LENGTH_SHORT).show();
               validate(uname.getText().toString(),upass.getText().toString());

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
    }
    private void validate(String username,String userpassword)
    {
        progressDialog.setMessage("You can wait until it validate the authorized user...");
        progressDialog.show();
           firebaseAuth.signInWithEmailAndPassword(username,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {
                     progressDialog.dismiss();
                     Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                     openActivity2();
                 }
                 else{
                     Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                     counter--;
                     attempt.setText("Attempts Remain"+counter);
                     progressDialog.dismiss();
                     if(counter==0)
                     {
                         login.setEnabled(false);
                     }
                 }
               }
           });

    }
    public void openActivity4()
    {
        Intent intent;
        intent = new Intent(this,Main6Activity.class);
        //   intent.putExtra("IP adresss",ip1);
        startActivity(intent);
    }
    public void openActivity2()
    {
        Intent intent;
        intent = new Intent(this,Main2Activity.class);
        //   intent.putExtra("IP adresss",ip1);
        startActivity(intent);
    }
    public void openActivity3()
    {
        Intent intent;
        intent = new Intent(this,Main5Activity.class);
        //   intent.putExtra("IP adresss",ip1);
        startActivity(intent);
    }
}
