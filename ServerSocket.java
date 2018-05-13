import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.platform.Platform;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import com.pi4j.platform.PlatformManager;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;
import com.pi4j.util.ConsoleColor;

public class LedFlasher
{
     public  static GpioController gpio;
    public static GpioPinDigitalOutput pin;
     public static GpioPinDigitalOutput pinA;                        
public static final int port=6789;
  private ServerSocket ss=null;
private static String message=null;
  private static String temp="On";
   static int t=1;  

 
 public void runServer() throws IOException,ClassNotFoundException
  {
    ss=new ServerSocket(port);
     System.out.println("Connection is established");
    while(true){  
       //LedOn l1=new LedOn();
      Socket socket=ss.accept();
      ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
      message=(String)is.readObject();
       System.out.println(message);
      if(message.equals("Led On"))
       {
           ledOn();
          System.out.println(message);
        }
       else if(message.equals("Led Off"))
        {
             ledOff();
           System.out.println(message);
        }
        else if(message.equals("Fan On"))
        {
           System.out.println(message);
             fanOn();
        }
        else if(message.equals("Fan Off"))
        {
           System.out.println(message);
            fanOff();
        }
       if(message.equals("Bye")){
           socket.close();
             break;
         }
    }
  }
  public void ledOn()
 {
   pin.high();
  }
   public void ledOff()
  {
   pin.low();
  }
  public void fanOn()
 {
   pinA.high();
 }
 public void fanOff()
 {
   pinA.low();
 }
 public static void main(String x[])throws InterruptedException,IOException,Exception
 {
   System.out.println("Hello World");
   gpio=GpioFactory.getInstance();
   pin=gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,"RedLed",PinState.LOW);
   pinA=gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"PinA");
       new LedFlasher().runServer();
   gpio.shutdown();   
 }

} 
