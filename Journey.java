
import java.time.LocalDateTime;

public class Journey {
	LocalDateTime stDtTm , edDtTm;
	Pass pass;
	Customer cust;
	double consessionRate;
	public Station stStation, edStation;
	public Station getStStation() {
		return stStation;
	}

	public void setStStation(Station stStation) {
		this.stStation = stStation;
	}

	JourneyStatus jrnyStatus;
	double custReaminingCr;
	
	public double getCustReaminingCr() {
		return custReaminingCr;
	}

	public void setCustReaminingCr(double custReaminingCr) {
		this.custReaminingCr =  custReaminingCr;
	}

	public enum JourneyStatus {newJrny("New journey: "), coveredJrny("Already covered journey: "), upgdeJrny("Upgraded to all day: ");
		private String value;
	       private JourneyStatus(String d) {
	            this.value = d;
	       }
	       public String getValue(){
	        return value;
	       }
		}
	
	
	
	public enum JourneyCost {hourly(1.25), allDay(2.45);
		private double value;
	       private JourneyCost(double d) {
	            this.value = d;
	       }
	       public double getValue(){
	        return value;
	       }
		}
	
	public Journey(LocalDateTime stDtTm, LocalDateTime edDtTm,  Pass pass, Customer cust, Station stStn, Station edStn) {
		//super();
		this.stDtTm = stDtTm;
		this.edDtTm = edDtTm;
		this.pass = pass;
		this.cust = cust;
		this.consessionRate = cust.getCustType(cust.getCustId(cust)).getValue();
		this.stStation = stStn;
		this.edStation = edStn;
	}

	@Override
	public String toString() {
		//return "Journey [stDtTm=" + stDtTm + ", edDtTm=" + edDtTm + ", pass=" + pass + ", cust=" + cust + ", stStation="
			//	+ stStation + ", edStation=" + edStation + ", jrnyStatus=" + jrnyStatus + ", Cost=" + this.getJourneyCost() + "]";
		return this.jrnyStatus.getValue() + pass.Validity() + " " + cust.getCustType(cust.getCustId(cust)) + " travel pass purchased for " + 
			cust.getCustId(cust) + ". Remaining balance is: " + this.getCustReaminingCr();
				}
	
	public String print()
	{
		String str = this.cust.getCustId(cust) +  " (" + cust.getCustType(cust.getCustId(cust)) + ") travelled from: " + this.stStation + " to " +this.edStation + " " +this.edStation.edCounter;
		return str;
				
	}
	
	public double getJourneyCost(double cost)   
	{
		//double cost = 0.0;
		//cost = this.pass.Cost();
		//if(this.stStation.stZone == Station.StationZone.zone2 || this.edStation.stZone == Station.StationZone.zone2 ) 
		
		if (this.jrnyStatus != Journey.JourneyStatus.coveredJrny && this.jrnyStatus != Journey.JourneyStatus.upgdeJrny)
			cost =  cost   * this.consessionRate;
		else 
		{
			cost = 0.0;
		}
	return cost;
		
	}
	
	
}
