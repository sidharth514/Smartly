
import RPi.GPIO as GPIO
import time

sensor= 22
led=16

GPIO.setmode(GPIO.BOARD)
GPIO.setup(sensor,GPIO.IN)
GPIO.setup(led,GPIO.OUT)
count=0
test=1
GPIO.output(led,False)
print ("IR Sensor Ready...")
print (" ")

try:
    while True:
        if GPIO.input(sensor):
            count=count+1
            test=count%2
            if test!=0:
                GPIO.output(led,True)
            else:
                print ("Object is detected")
        else:
            print ("Nothing is detected")
except KeyboardInterrupt:
    GPIO.cleanup()
