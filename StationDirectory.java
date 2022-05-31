import java.util.*;
public class StationDirectory {

	private List<Station> stationList = new ArrayList<>();
	
	public void addStation(Station stn)
	{
		if(stn != null)
		stationList.add(stn);
	}
	
	public void removeStation(Station stn)
	{
		stationList.remove(stn);
	}

	@Override
	public String toString() {
		String output = "";
		for(Station stn : stationList)
		{
			output = output + stn.toString() + '\n';
		}
		return output;
	}
	
	
	public Station getStation(String stnName) {
		// TODO Auto-generated method stub
		
		for(Station stn : stationList)
		{
			if (stn.getStation(stnName) != null)
				return stn;
		}
		//return output;
		return null;
	}
	
	public Station alterStationZone(String stnName, Station.StationZone sz) {
		// TODO Auto-generated method stub
		
		for(Station stn : stationList)
		{
			if (stn.getStation(stnName) != null)
			{
				stn.stZone = sz;
				return stn;
			}
		}
		//return output;
		return null;
	}
	
	
	public void keepUsageStats(String stnName, int stCounter, int edCounter)
	{
		// TODO Auto-generated method stub
		
		for(Station stn : stationList)
		{
			if (stn.getStation(stnName) != null)
			{
				stn.edCounter+=edCounter;
				stn.stCounter+=stCounter;
				
						
			}
				
		}
		//return output;
	}
	
	public String printStationStats() {
		
		String output ="";
		for(Station stn : stationList)
		{
			
			output +=  stn.stName + ": " + "Departures: " + stn.stCounter + ", Arrivals: " + stn.edCounter + "\n" ;
				
						
			
				
		}
		return output;
	}

}
