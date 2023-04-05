package app;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import app.DataExchange;

public class ObjectDodge implements Runnable{
	DataExchange DEobj;
	

	private static EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S2);
	SampleProvider sp2 = us1.getDistanceMode();
    
    public ObjectDodge(DataExchange DE) {
		DEobj = DE;
	
	}

    @Override
    public void run() {
    	int turn = 1;
    	int distanceValue = 0;
        while(true) {
        	
            float [] sample2 = new float[sp2.sampleSize()];
            sp2.fetchSample(sample2, 0);
            distanceValue = (int)(sample2[0]*100);

            System.out.println("Distance: " + distanceValue);
            
            
            if (distanceValue < 25) {
            System.out.println("Objekti huomattu");
            	// jos objekti huomataan toisen kerran
            	if (turn >= 2) {
            	System.out.println("Objekti huomattu");
                DEobj.setOD(3);
            	}
                // jos objekti huomataan ekan kerran
	            if (turn < 2) {
	            DEobj.setOD(2);
	            turn += 1;
	            }
            
	            
            }
        }
        
    }
        
    



}
