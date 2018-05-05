package com.example.dell.smartly;

/**
 * Created by dell on 12-04-2018.
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class Client  extends AsyncTask implements Serializable{

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private static String serverIP;
    private transient Socket connection;
    public static String s;
    //    public Client()
    {
        ;
    }

    public Client(String host,String message){
        serverIP=host;
        s=message;
        this.execute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        //connectMe();
        runMe();
        return null;
    }


    public void connectToServer()throws IOException{
        Log.e("Connection","Attempting to connect!");
        connection=new Socket(InetAddress.getByName(serverIP),6789);
        Log.e("Connection","Connected to "+connection.getInetAddress().getHostName());
    }
    public void setupStreams()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        Log.e("Connection","You are now setup!");
    }
    private void whileRunning()throws IOException{

    }
    public void connectMe()
    {
        try{
            connectToServer();
            runMe();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try{
            output.writeObject(message);
            output.flush();
        }catch(Exception e5){
            e5.printStackTrace();
        }
    }
    public void runMe() {
        //String s=null;
        try {
            // String s="connected";
            //Connection
            connectToServer();
            setupStreams();
            whileRunning();
            Log.e("meaasge to send",s);
            sendMessage(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
