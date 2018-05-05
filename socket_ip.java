package com.example.dell.smartly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    Button con,butOn,butOff;
    EditText ip;
    String ip1;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ip=(EditText)findViewById(R.id.editText);
        con=(Button)findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();
        //  final Client[] c = new Client[3];
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),ip.getText().toString(),Toast.LENGTH_LONG).show();
                ip1=ip.getText().toString();
                Client c = new Client( ip1,"connected");

                openActivity2();
                //c.runMe();
               /*
                  */
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            }
        });


    }
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Main2Activity.this,MainActivity.class));
    }
    public void openActivity2()
    {
        Intent intent;
        intent = new Intent(this,Main3Activity.class);
        intent.putExtra("IP adresss",ip1);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode==KeyEvent.KEYCODE_BACK)){
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
}
