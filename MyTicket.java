import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/*
 * 
 * */
public class MyTicket {
	

	
	private double ti_val;
	
	private ArrayList <Pass> passes;
	private ArrayList <Journey> journeyLst;
	private ArrayList <AdultCustomer> customers;
	public CustomerDirectory customerDirectory = new CustomerDirectory();
	public StationDirectory stationDirectory = new StationDirectory();
	
	//Pass class //composition

	public double getTi_val() {
		return ti_val;
	}

	public void setTi_val(double ti_val) {
		if (ti_val < 0) {
			throw new java.lang.Error("Negative or invalid amount!");
		}
		else if( ti_val%5 != 0) {
			throw new java.lang.Error("Amount should be in multiples of 5!");
			
		}
		else if(ti_val + this.ti_val > 100 ) {
			throw new java.lang.Error("Total amount should not be more than 100!");
		}
		else {
		this.ti_val += ti_val;
		}
	}

	public MyTicket(int maxPasses) {
		ti_val = 0;
		passes = new ArrayList<>(maxPasses);
		journeyLst = new ArrayList<>();
	}
	
	public double remainingCR() {
		Pass pass;
		double remainingVal = 0;
		for(int i = 0;i< passes.size(); i++ ) {
			pass = passes.get(i);
			remainingVal += pass.Cost();
			
		}
		return getTi_val() - remainingVal ;
	}
	
	public void buyPass(Pass pass) {
		if(remainingCR() >=  pass.Cost())
		passes.add(pass);
		
		else {
			throw new java.lang.Error("Not enough credit!");
		}
	}
	
	public String printPasses() {
		//+"\nYour remaining credit is $"+ this.
		Pass pass;
		
		String printed = "";
		for(int i = 0;i< passes.size(); i++ ) {
			pass = passes.get(i);
			printed += i+1 +":- " + pass.Print()+"\n";
			
		}
		printed += "\nYour remaining credit is $"+ this.remainingCR();
		return "Purchases:\n" + printed;
	}
	
	public ArrayList<Journey> findByCustDay(String custId, LocalDateTime ldt) {
		
		Journey jry;
		ArrayList <Journey> lJryLst = new ArrayList<> ();
		
		
			for (Journey journey : journeyLst) {
				if(journey.cust.getCustId(journey.cust) == custId && journey.stDtTm.toLocalDate().compareTo(ldt.toLocalDate()) == 0   )
				{
					lJryLst.add(journey);
					
				}
					
				}
			return lJryLst;
					
	}
	
	public void computeJourney(Journey journey) 
{
		ArrayList<Journey> ftdJrny = findByCustDay(journey.cust.getCustId(journey.cust), journey.stDtTm); //new ArrayList<>();
		double custConcessionRate =  journey.cust.getCustType(journey.cust.getCustId(journey.cust)).getValue();
		// if count 0 add new (2hr/ad) 
		if (ftdJrny.isEmpty()  ) 
		{
			if (  Duration.between(journey.stDtTm, journey.edDtTm).toHours()   <= Pass.hourlyPass  )// Hourly Pass 
				
			{
				// 2 hr pass
				if(journey.stStation.stZone == Station.StationZone.zone1 &&  journey.edStation.stZone == Station.StationZone.zone1)
				{
					journey.pass = new HourlyPassZ1();
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.hourlyZ1.getValue() * custConcessionRate ) );
				}
				else
				{
					journey.pass = new HourlyPassZ1Z2();
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.hourlyZ2.getValue() * custConcessionRate) );
				}
				journey.jrnyStatus = Journey.JourneyStatus.newJrny;
		
				journeyLst.add(journey);
			}
			else
			{
				if(journey.stStation.stZone == Station.StationZone.zone1 &&  journey.edStation.stZone == Station.StationZone.zone1)
				{
					journey.pass = new DayPassZ1();
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.allDayZ1.getValue() * custConcessionRate ) );
				}
				else 
				{
					journey.pass = new DayPassZ1Z2();
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.allDayZ2.getValue() * custConcessionRate ) );
				}
				journey.jrnyStatus = Journey.JourneyStatus.newJrny;
				journeyLst.add(journey);
			}
		}
		else // When previously has travelled
		{	
			Journey jnr = null;  
			for(Journey j: ftdJrny) 
			{ 
				if (j.jrnyStatus == Journey.JourneyStatus.newJrny) 
				{jnr = j;}
			}	
					//.filter(n-> (n.jrnyStatus == Journey.JourneyStatus.newJrny) );
			if (  jnr.stDtTm.plusHours((long)jnr.pass.validity()).compareTo(journey.edDtTm )  >= 0 )
			{
				if(journey.stStation.stZone == Station.StationZone.zone1 &&  journey.edStation.stZone == Station.StationZone.zone1)
				{
					journey.pass = new HourlyPassZ1();
					journey.setCustReaminingCr( journey.cust.chrgBalance(0 ) ) ;
				}
				else
				{
					journey.pass = new HourlyPassZ1Z2();
					journey.setCustReaminingCr( journey.cust.chrgBalance(0 ) ) ;
				}
				journey.jrnyStatus = Journey.JourneyStatus.coveredJrny;
				
				journeyLst.add(journey);
			}
			else if(jnr.pass.Zone().equals("Daily") )
			{
				if(journey.stStation.stZone == Station.StationZone.zone1 &&  journey.edStation.stZone == Station.StationZone.zone1)
				{
					journey.pass = new DayPassZ1();
					journey.setCustReaminingCr( journey.cust.chrgBalance(0 ) ) ;
				}
				else
				{
					journey.pass = new DayPassZ1Z2();
					journey.setCustReaminingCr( journey.cust.chrgBalance(0 ) ) ;
				}
				journey.jrnyStatus = Journey.JourneyStatus.coveredJrny;
				
				journeyLst.add(journey);
			
			}
			
			else {
				// new journey status will be new and previous will be upgdeJrny
				
				updateJrnyStatus(journey.cust.getCustId(journey.cust), journey.stDtTm, Journey.JourneyStatus.upgdeJrny);
				
				if(journey.stStation.stZone == Station.StationZone.zone1 &&  journey.edStation.stZone == Station.StationZone.zone1)
				{
				
					journey.pass = new DayPassZ1(); //TODO: Implement if cost of new hourly(two) is less than daily
					journey.cust.chrgBalance( Pass.PassCost.hourlyZ1.getValue() * custConcessionRate );
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.allDayZ1.getValue() * custConcessionRate ) ) ;
				}
				else
				{
					journey.pass = new DayPassZ1Z2(); //TODO: Implement if cost of new hourly(two) is less than daily
					journey.cust.chrgBalance( Pass.PassCost.hourlyZ2.getValue() * custConcessionRate);
					journey.setCustReaminingCr( journey.cust.chrgBalance(- Pass.PassCost.allDayZ2.getValue() * custConcessionRate ) ) ;
				}
				journey.jrnyStatus = Journey.JourneyStatus.newJrny;
				journeyLst.add(journey);
			}
			
			
		}
		
		journeyLst.forEach(name -> {
		    System.out.println(name);
		});
		
	}
	
	public void updateJrnyStatus(String custId, LocalDateTime lDT, Journey.JourneyStatus jSt)
	{
		for(Journey jnr : journeyLst)
		{
			if(jnr.cust.getCustId(jnr.cust) == custId && lDT.toLocalDate().compareTo(jnr.stDtTm.toLocalDate()) == 0 )
			{
				jnr.jrnyStatus = jSt;
			}
		}
	}
	//buy_pass
	//lst_bought_pass
	public void fillJourneyObj()
	
	{
		AdultCustomer adCust1 = new AdultCustomer("my", "a@xyz.com", "first", 20); 
		AdultCustomer adCust2 = new AdultCustomer("Cust2", "b@xyz.com", "second", 20);
		//CustomerDirectory adDirectory = new CustomerDirectory();
		this.customerDirectory.addCustomer(adCust1);
		this.customerDirectory.addCustomer(adCust2);
		//adDirectory.addCustomer(adCust1);
		//adDirectory.addCustomer(adCust2);
		
		SeniorCustomer srCust1 = new SeniorCustomer("srCust1", "sa@xyz.com", "first", 20); 
		SeniorCustomer srCust2 = new SeniorCustomer("srCust2", "sb@xyz.com", "second", 20);
		this.customerDirectory.addCustomer(srCust1);
		this.customerDirectory.addCustomer(srCust2);
		//CustomerDirectory srDirectory = new CustomerDirectory();
		//srDirectory.addCustomer(srCust1);
		//srDirectory.addCustomer(srCust2);

		
		JuniorCustomer jrCust1 = new JuniorCustomer("jrCust1", "ja@xyz.com", "first", 20); 
		JuniorCustomer jrCust2 = new JuniorCustomer("jrCust2", "jb@xyz.com", "second", 20);
		this.customerDirectory.addCustomer(jrCust1);
		this.customerDirectory.addCustomer(jrCust2);
		//CustomerDirectory jrDirectory = new CustomerDirectory();
		//jrDirectory.addCustomer(jrCust1);
		//jrDirectory.addCustomer(jrCust2);
		
		
		//CustomerDirectory directory = new CustomerDirectory();
		//directory.addCustomer(adDirectory);
		//directory.addCustomer(srDirectory);
		//directory.addCustomer(jrDirectory);
		System.out.println("Loading Customer Data! \n"+this.customerDirectory.toString() + "\nLoaded Customer Data! ");
		
		
		//Pass is abstract class so no obj can be instantiated
		Pass pass = null;
		//String cId, String cEmail, String cName, Customer.CustType cType, double cCr
		//AdultCustomer cust = new AdultCustomer("Cust1", "a@xyz.com", "first",  20);
		//Station
		Station sSt = new Station("flagstaff", Station.StationZone.zone1);
		Station eSt = new Station("parliment", Station.StationZone.zone1);
		this.stationDirectory.addStation(sSt);
		this.stationDirectory.addStation(eSt);
		
		System.out.print(this.stationDirectory.toString());
		
		//LocalDateTime stDtTm, LocalDateTime edDtTm,  Pass pass, Customer cust, Station stStn, Station edStn
		//Journey jObj = new Journey(LocalDateTime.now() , LocalDateTime.now().plusMinutes(20), pass, cust, sSt, eSt);
		//return jObj;
		 
	}
	
	public void printAllJournies()
	{
		journeyLst.forEach(name -> {
		    System.out.println(name.print()+"\n");
		});
	}
	
	
	public void printStationStats()
	{
		
	}
}
