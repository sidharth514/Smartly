package com.example.dell.smartly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity {
    Button LedOn,LedOff,FanOn,FanOff,voice;
    String ip;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        LedOn=(Button)findViewById(R.id.button2);
        LedOff=(Button)findViewById(R.id.button5);
        FanOn=(Button)findViewById(R.id.button6);
        FanOff=(Button)findViewById(R.id.button7);
        voice=(Button)findViewById(R.id.button8);



        firebaseAuth=FirebaseAuth.getInstance();


        Intent intent=getIntent();
        ip= intent.getStringExtra("IP adresss");

        LedOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //c[1] = new Client("192.168.225.114");

                Client c = new Client(ip,"Led On");
                //c.runMe("Led On");
                Toast.makeText(getApplicationContext(),ip,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"Led On",Toast.LENGTH_LONG).show();
            }
        });

        LedOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client c = new Client(ip,"Led Off");
                // c.runMe("Led Off");
                // openActivity2();
                Toast.makeText(getApplicationContext(),"Led Off",Toast.LENGTH_LONG).show();
            }
        });
        FanOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client c = new Client(ip,"Fan On");
                // c.runMe("Led Off");
                //openActivity2();
                Toast.makeText(getApplicationContext(),"Led Off",Toast.LENGTH_LONG).show();
            }
        });
        FanOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Client c = new Client(ip,"Fan Off");
                // c.runMe("Led Off");
                // openActivity2();
                Toast.makeText(getApplicationContext(),"Led Off",Toast.LENGTH_LONG).show();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
                // Toast.makeText(getApplicationContext(),"Led Off",Toast.LENGTH_LONG).show();
            }
        });


    }

     private void Logout()
     {
         firebaseAuth.signOut();
         finish();
         startActivity(new Intent(Main3Activity.this,MainActivity.class));
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
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)){
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openActivity2()
    {
        Intent intent;
        intent = new Intent(this,Main4Activity.class);
           intent.putExtra("IP",ip);
        startActivity(intent);
    }
}
