package Robot;

public class DataExchange {
	
	private int OD = 1;  // if 1, follow line
	private int intensityValue = 0;
	
	public DataExchange() {
		
	}
	public void setOD(int oD) {
		OD = oD;
	}
	public int getOD() {
		return OD;
	}
	
	public int getIntensityValue() {
		return intensityValue;
	}
	public void setIntensityValue(int intensityValue) {
		this.intensityValue = intensityValue;
	}
	


}