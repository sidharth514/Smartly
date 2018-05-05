package com.example.dell.smartly;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Main4Activity extends AppCompatActivity {
    private TextView resultText;
    String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        resultText=(TextView)findViewById(R.id.textView3);

        Intent intent=getIntent();
        ip= intent.getStringExtra("IP");

        Toast.makeText(Main4Activity.this,ip,Toast.LENGTH_SHORT).show();

    }
    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.imageButton)
        {
            promtSpeechInput();
        }
    }
    public void promtSpeechInput()
    {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.EXTRA_LANGUAGE_MODEL);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something!");
        try{
            startActivityForResult(i,100);
        }
        catch(ActivityNotFoundException e)
        {
            Toast.makeText(Main4Activity.this,"Sorry ! your device doesn't support language!",Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code,int result_code,Intent i)
    {
        String say=null;
        super.onActivityResult(request_code,result_code,i);
        switch(request_code)
        {
            case 100:if(result_code==RESULT_OK && i!=null)
            {
                ArrayList<String> result=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                say=result.get(0).toLowerCase();
                resultText.setText(say);

                if(say.equals("led on")||say.equals("turn on led"))
                {
                    Client c = new Client(ip,"Led On");
                }
                else if(say.equals("led off")||say.equals("led of")||say.equals("turn of led"))
                {
                    Client c = new Client(ip,"Led Off");
                }
                else if(say.equals("fan on")||say.equals("turn on fan"))
                {
                    Client c = new Client(ip,"Fan On");
                }
                else if(say.equals("fan off")||say.equals("fan of")||say.equals("turn off fan"))
                {
                    Client c = new Client(ip,"Fan Off");
                }
                else{
                    Client c = new Client(ip,"say one more time");
                    resultText.setText(say);
                }
                //Toast.makeText(Main3Activity.this,result.get(0),Toast.LENGTH_LONG).show();
            }
                break;
        }
    }

}
