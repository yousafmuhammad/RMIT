
public class Station {
	
	public  int stCounter = 0;
	public   int edCounter = 0;
	
	public enum StationZone{zone1(1), zone2(1.5);
		private double value;
		private StationZone(double d) {
		
        this.value = d;
   }
   public double getValue(){
    return value;
   }	
   public void setValue(double value){
	    this.value = value;
	   }
	}
	
	String sId;
	public String stName;
	StationZone stZone;
	
	public Station( String stName, Station.StationZone stZone) {
		super();
		this.stName = stName;
		this.stZone = stZone;
	}

	@Override
	public String toString() {
		return "Station [Name=" + stName + ", Zone=" + stZone + "]";
	}
	
	public Station getStation(String stnName)
	{
		if (this.stName.equals(stnName) )
		{
			return this;
		}
		return null;
	}
	
	
}
