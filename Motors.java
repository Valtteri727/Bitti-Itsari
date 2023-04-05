package app;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.motor.*;

import java.io.File;

import lejos.ev3.*;

public class Motors implements Runnable{
	DataExchange DEobj;

	
	private static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	
	public Motors(DataExchange DE) {
		DEobj = DE;
		
	}

	@Override
	public void run() {
		// 3 = musta
		// 27 = valkoinen
		// 7-20 = keskellä
        

        int desiredIntensityValue = 19;
        float correctionMultiplier = 5f;
       
        while(true) {
        	 if(DEobj.getOD() == 1) { //LineFollow
					motorA.forward();
					motorB.forward();
					float correction = (desiredIntensityValue - DEobj.getIntensityValue()) * correctionMultiplier;

					motorA.setSpeed(220 - (int) correction);
					motorB.setSpeed(220 + (int) correction);
					motorA.forward();
					motorB.forward();

					System.out.println("light intensity: " + DEobj.getIntensityValue());

					if (Button.getButtons() != 0) {
						break;
					}
				}

				if (DEobj.getOD() == 2) { // Objectdodge
        		motorA.stop(true);
        		motorB.stop(true);
        		Delay.msDelay(500);
        		motorA.setSpeed(200);
               	motorB.setSpeed(200);
             	motorB.rotate(150);
             	motorB.stop(true);
        		motorA.stop(true);
        		Delay.msDelay(500);
        		motorB.setSpeed(200);
        		motorA.setSpeed(200);
        		motorB.forward();
              	motorA.forward();
             	Delay.msDelay(1500);
        		motorA.stop(true);
        		motorB.stop(true);
        		Delay.msDelay(200);
             	
                 do {
                	motorA.setSpeed(200); //180
                    motorB.setSpeed(110); //140
                	motorA.forward();
             		motorB.forward();    
                 }while (DEobj.getIntensityValue() > 10);
                 motorA.stop(true);
         		 motorB.stop(true);
         		Delay.msDelay(500);
         		do {
         			motorB.setSpeed(110);
                    motorA.setSpeed(70);
             		motorA.backward(); 
             		motorB.forward();
                 }while (DEobj.getIntensityValue() < 22);
         		 motorB.stop(true);
                 DEobj.setOD(1);

             }
        	 
        	
        	 if(DEobj.getOD() == 3) {
        		 motorA.stop(true);
         		 motorB.stop(true);
        		 motorB.setSpeed(90);
                 motorA.setSpeed(90);
          		 motorA.backward(); 
          		 motorB.forward();
        		 Sound.playSample(new File("X2Download.app_-_Rocky_III_-_Gonna_Fly_Now_128_kbps.wav"), Sound.VOL_MAX);
          		 System.exit(0);
        	 }
        
        }	
	}
}
