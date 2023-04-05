package app;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class LightSensor implements Runnable{
	DataExchange DEobj;

	private static SensorModes color1 = new EV3ColorSensor(SensorPort.S3);
	public static int intensityValue;
	SampleProvider sp = color1.getMode("Red");
	public LightSensor(DataExchange DE) {
		DEobj = DE;
	}
	
	@Override
	public void run() {
		while(true) {
		float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);
        intensityValue = (int)(sample[0]*100);
        
        DEobj.setIntensityValue(intensityValue);
		}
	}

}
