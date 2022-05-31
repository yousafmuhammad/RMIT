

	import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
	import java.util.HashMap;
	import java.util.LinkedHashMap;
	import java.util.Map.Entry;

import javax.print.attribute.standard.PrinterMessageFromOperator;
	public class MenuGenerator {
		
		protected static final double CHRG_MAX = 100;
		protected static final double CHRG_MULTIPLE = 5;
		protected static double totalCharge = 0 ;
		protected static final String PROG_TITLE = "Welcome to MyTi !";
		protected static final String MENU_START = "Choose an option:";
		protected static final String MENU_SELECT = "Please make a selection:";
		protected static final String MENU_BUY = "What time period:";
		protected static final String MENU_BUY_ZONE = "Which zone:";
		
		HashMap<String, Entry<String, String>> m_menu;
		HashMap<String, Entry<String, String>> buy_menu;
		HashMap<String, Entry<String, String>> buy_2hr_menu;
		HashMap<String, Entry<String, String>> buy_ad_menu;
		HashMap<String, Entry<String, String>> credit_menu;
		HashMap<String, Entry<String, String>> admin_menu;
		HashMap<String, Entry<String, String>> customer_menu;
		HashMap<String, Entry<String, String>> station_menu;
		//HashMap<String, Entry<String, String>> pt_menu;
		
		
		Scanner scObj;
		MyTicket ticket ;
		Pass pass;
		//Journey journey;
		Customer customer;
		Station station;
		static int jrnyCounter = 1; 
		public enum TICKET_VAL{
			// Consulted https://stackoverflow.com/questions/3990319/storing-integer-values-as-constants-in-enum-manner-in-java
			// To call: TICKET_VAL.FD_ZONE_1_2.get_val()
			// Fares are set with enum.func
			HR_ZONE_1(2.5),
			HR_ZONE_1_2(3.5),
			FD_ZONE_1(4.9),
			FD_ZONE_1_2(6.8);
			
			private final double fare;
			
			TICKET_VAL(final double val){fare = val;}
			public double get_val() {return fare;}
			
		}
		
		public MenuGenerator()
		{
			 ticket = new MyTicket(100);
			 //ticket.fillJourneyObj();
			// journey = ticket.fillJourneyObj();
			/*Structure of menu
			 * m_menu
			 * -buy_menu
			 * --buy_2HR_menu
			 * --buy_AD_menu
			 * -ch_menu
			 * --
			 * --
			 * -cr_menu
			 * --
			 * 
			 * */
			 m_menu = new LinkedHashMap<String , Entry<String, String>>();
			
			buy_menu = new LinkedHashMap<String , Entry<String, String>>();
			//HashMap<Integer, String> buy_a_menu = new HashMap<>();
			//HashMap<Integer, String> buy_b_menu = new HashMap<>();
			buy_2hr_menu = new LinkedHashMap<String , Entry<String, String>>();
			buy_ad_menu = new LinkedHashMap<String , Entry<String, String>>();
			
			credit_menu = new LinkedHashMap<String , Entry<String, String>>();
			
			 admin_menu = new LinkedHashMap<String , Entry<String, String>>();
			 customer_menu = new LinkedHashMap<String , Entry<String, String>>();
			 station_menu = new LinkedHashMap<String , Entry<String, String>>();
			
			/*
			 * 1. Buy a travel pass
				2. Charge my MyTi
				3. Show remaining credit
				0. Quit
			 * */

			//m_menu.put("1", new SimpleEntry<String, String>("buy_menu" , "Buy a travel pass") );
			m_menu.put("1", new SimpleEntry<String, String>("buy_menu" , "Buy a travel pass for a customer") );
			m_menu.put("2" , new SimpleEntry<String, String>("chrg_menu" ,  "Charge my MyTi"));
			m_menu.put("3", new SimpleEntry<String, String>("cr_menu" ,  "Load starting data!"));
			m_menu.put("4", new SimpleEntry<String, String>("pt_menu" , "Print Purchases"));
			m_menu.put("5",new SimpleEntry<String, String>("admin_menu", "Administration"));
			m_menu.put("0", new SimpleEntry<String, String>("qt_menu" , "Quit"));
			
			/*What time period:
			a) 2 Hours
			b) All Day
			c) cancel
			Your selection: a
			*/
			
			admin_menu.put("a", new SimpleEntry<String, String>("customer_menu", "Customers"));
			admin_menu.put("b" , new SimpleEntry<String, String>("station_menu",  "Stations"));
			admin_menu.put("c" , new SimpleEntry<String, String>("admin_cancel_menu",  "Cancel"));
			
			customer_menu.put("a", new SimpleEntry<String, String>("cust_add_menu","Add customer"));
			customer_menu.put("b" , new SimpleEntry<String, String>("cust_rem_menu",  "Remove customer"));
			customer_menu.put("c" , new SimpleEntry<String, String>("cust_prt_menu",  "Print customer"));
			customer_menu.put("d", new SimpleEntry<String, String>("cust_cancel_menu","Cancel"));

			station_menu.put("a", new SimpleEntry<String, String>("stn_add_menu","Add station"));
			station_menu.put("b" , new SimpleEntry<String, String>("stn_rem_menu",  "Remove station"));
			station_menu.put("c" , new SimpleEntry<String, String>("stn_prt_menu",  "Print station"));
			station_menu.put("d" , new SimpleEntry<String, String>("buy_2hr_z1_z2_menu",  "Alter station zone"));  
			station_menu.put("e" , new SimpleEntry<String, String>("buy_2hr_z1_menu",  "Alter zone price"));
			station_menu.put("f" , new SimpleEntry<String, String>("buy_ad_z1_menu",  "Station usage stats"));
			station_menu.put("g", new SimpleEntry<String, String>("stn_cancel_menu","Cancel"));

			
			buy_menu.put("a", new SimpleEntry<String, String>("buy_2hr_menu", "Buy a pass for journey"));
			//buy_menu.put("b" , new SimpleEntry<String, String>("buy_ad_menu",  "All Day"));
			buy_menu.put("b",  new SimpleEntry<String, String>("buy_cancel_menu","Cancel"));
			
			
			buy_2hr_menu.put("a", new SimpleEntry<String, String>("buy_2hr_z1_menu","Alter prices of zones"));
			buy_2hr_menu.put("b" , new SimpleEntry<String, String>("buy_2hr_z1_z2_menu",  "Alter zone of station"));
			buy_2hr_menu.put("c", new SimpleEntry<String, String>("buy_2hr_cancel_menu","Cancel"));
			
			buy_ad_menu.put("a", new SimpleEntry<String, String>("buy_ad_z1_menu", "Zone 1"));
			buy_ad_menu.put("b" , new SimpleEntry<String, String>("buy_ad_z1_z2_menu", "Zone 1 & 2"));
			buy_ad_menu.put("c", new SimpleEntry<String, String>("buy_ad_cancel_menu", "Cancel"));
			
			scObj = new Scanner(System.in);

		}
		
		public static void main(String [] args) {
		
		}
		
		public String disp_menu(String menu_state) {
			
			String input_ch = "";
			
			
			switch(menu_state) {
			case "m_menu":
				System.out.println(MENU_START);
				display_menu_item(m_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  m_menu.get(input_ch).getKey().toString();
				//System.out.print(input_ch);
				
				
				break;
			case "buy_menu":
				display_menu_item(buy_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  buy_menu.get(input_ch).getKey();
				//System.out.print(input_ch);
				break;
			case "admin_menu":
				display_menu_item(admin_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  admin_menu.get(input_ch).getKey();
				//System.out.print(input_ch);
				break;
			case "customer_menu":
				display_menu_item(customer_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  customer_menu.get(input_ch).getKey();
				//System.out.print(input_ch);
				break;
			case "station_menu":
				display_menu_item(station_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  station_menu.get(input_ch).getKey();
				//System.out.print(input_ch);
				break;
			case "cust_add_menu":
				if(this.newCustomer()!=null)
				System.out.println("Customer added successfully!");
				else
					printMsg("Something went wrong during new customer generation!\n");
				input_ch = "admin_menu";
				break;
			case "cust_rem_menu":
				printMsg("Enter customre id: ");
				String cId =  scObj.next();
				ticket.customerDirectory.removeCustomer( ticket.customerDirectory.getCustomer(cId));
				System.out.println(ticket.customerDirectory.toString());
				input_ch = "admin_menu";
				break;
			case "cust_prt_menu":
				
				System.out.println(ticket.customerDirectory.toString());
				input_ch = "admin_menu";
				break;
			case "stn_add_menu":
				this.newStation();
				System.out.println(ticket.stationDirectory.toString());
				input_ch = "admin_menu";
				break;
			case "stn_rem_menu":
				printMsg("\nEnter station name: ");
				String stName = scObj.next();
				Station stn = ticket.stationDirectory.getStation(stName);
				ticket.stationDirectory.removeStation(stn);
				printMsg(ticket.stationDirectory.toString());
				input_ch = "admin_menu";
				break;
			case "stn_prt_menu":
				
				printMsg(ticket.stationDirectory.toString());
				input_ch = "admin_menu";
				break;
			case "buy_2hr_menu":
				//display_menu_item(buy_2hr_menu);
				
				//String cId, String cEmail, String cName, Customer.CustType cType, double cCr
				//AdultCustomer cust = new AdultCustomer("you", "a@xyz.com", "first",  20);
				//Station
				//Station sSt = new Station("flagstaff", Station.StationZone.zone1);
				//Station eSt = new Station("parliment", Station.StationZone.zone1);
				
				System.out.print(MENU_SELECT);
				Journey jrny = this.newJourney();
				ticket.computeJourney(jrny);
				//input_ch = scObj.next();
				//input_ch =  buy_2hr_menu.get(input_ch).getKey();
				input_ch = "buy_menu";
				break;
			case "buy_2hr_z1_menu":
				this.alterZonePrice();
				/*pass = new HourlyPassZ1();
				//ticket.buyPass(pass);
				//dispalyBuyTicket(pass);
				printMsg("\nWhich User:");
				input_ch = scObj.next();
				customer = ticket.customerDirectory.getCustomer(input_ch);
				
				printMsg("\nFrom what station: ");
				input_ch = scObj.next();
				station = ticket.stationDirectory.getStation(input_ch);
				
				printMsg("\nWhat day: ");
				input_ch = scObj.next();
				DayOfWeek day = java.time.DayOfWeek.valueOf(input_ch);
				
				//d.valueOf(input_ch);
				
				printMsg("\nDeparture Time: ");
				String departTime = scObj.next();
				
				printMsg("\nArrival Time: ");
				String arrivalTime = scObj.next();
				
				//java.time.LocalTime lt = lt.g
				
				//Journey journey = ticket.fillJourneyObj();
				//TODO: Remove after testing
				//journey.stDtTm = journey.stDtTm.plusHours(jrnyCounter*2);
				//journey.edDtTm = journey.edDtTm.plusHours(jrnyCounter*2);
				//jrnyCounter += 1;

				//ticket.computeJourney(journey);
				//System.out.println("You Purchased " + pass.Print() + "\nYour remaining credit is $ " + ticket.remainingCR() );
				 * */
				 
				input_ch = "m_menu";
				break;
			case "buy_2hr_z1_z2_menu":
				//pass = new HourlyPassZ1Z2();
				//ticket.buyPass(pass);
				//dispalyBuyTicket(pass);
				this.alterZone();
				input_ch = "m_menu";
				break;
			case "buy_2hr_cancel_menu":
				input_ch = "buy_menu";
				break;
			case "buy_cancel_menu":
				input_ch = "m_menu";
				break;
			case "admin_cancel_menu":
				input_ch = "m_menu";
				break;
			case "cust_cancel_menu":
				input_ch = "admin_menu";
				break;
			case "stn_cancel_menu":
				input_ch = "admin_menu";
				break;
				
				
			case "buy_ad_menu":
				display_menu_item(buy_ad_menu);
				System.out.print(MENU_SELECT);
				input_ch = scObj.next();
				input_ch =  buy_ad_menu.get(input_ch).getKey();
				break;
			case "buy_ad_z1_menu":
				//pass = new DayPassZ1();
				//ticket.buyPass(pass);
				//dispalyBuyTicket(pass);
				printMsg(ticket.stationDirectory.printStationStats());
				input_ch = "m_menu";
				break;
			case "buy_ad_z1_z2_menu":
				pass = new DayPassZ1Z2();
				ticket.buyPass(pass);
				dispalyBuyTicket(pass);
				input_ch = "m_menu";
				break;
			case "buy_ad_cancel_menu":
				input_ch = "buy_menu";
				break;
				
			case "chrg_menu":
				//Get input from user
				//ticket.setTi_val(100); //////////////////
				chrgMyTi(0);
				//showCredit();
				
				input_ch = "m_menu";
				break;
			case "cr_menu":
				//System.out.println( ticket.remainingCR());
				//showCredit();
				ticket.fillJourneyObj();
				
				input_ch = "m_menu";
				break;
			case "pt_menu":
				//System.out.println( ticket.printPasses());
				ticket.printAllJournies();
				input_ch = "m_menu";
				break;
			case "qt_menu":
				System.out.println("\nGood Bye!");
				System.exit(0);
			default:
				System.out.println();
				
				
			}
			//scObj.close();
			return input_ch;
		}
		
		private void dispalyBuyTicket(Pass pass) {
			// TODO Auto-generated method stub
			System.out.println("You Purchased " + pass.Print() + "\nYour remaining credit is $ " + ticket.remainingCR() );
			
		}

		private void display_menu_item(HashMap<String, Entry<String, String >> menu_state) {
			for (Entry<String, Entry<String, String>> entry : menu_state.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue().getValue();
			    System.out.println(key + ". " + value);
			    
			}
		}
		
		private void chrgMyTi(double amt)
		{
			Customer cust;
			String cid;
			printMsg("Enter customer ID: ");
			cid = scObj.next();
			
			cust = ticket.customerDirectory.getCustomer(cid);
			
			System.out.println("How much do you want add: ");
			amt = scObj.nextDouble();
			
			if (amt+ ticket.customerDirectory.chrgBalance(0)> 100) {
				System.out.println("\nSorry, the max amount of credit allowed is $" + CHRG_MAX);
				//showCredit();
			}
			else if(amt%CHRG_MULTIPLE != 0.0 ) {
				System.out.println(amt%CHRG_MULTIPLE + "\nSorry, you can add multiples of $" + CHRG_MULTIPLE);
				//showCredit();
				//ticket.remainingCR();
			}
			else {
				//totalCharge += amt;
				//ticket.setTi_val(amt);
				//showCredit();
				printMsg("\nNew balance is: " +  ticket.customerDirectory.chrgBalance(amt) +"\n");
			}
			
		}
		
	
		
	
		
		private void printMsg(String msg)
		{
			System.out.print(msg);
		}
		
		public Customer newCustomer()  {
			
			JuniorCustomer jrCtr=null;
			SeniorCustomer srCtr=null;
			AdultCustomer adCtr=null;
			String type, id, name, email;
			double cr;
			printMsg("\nEnter customer type (jr/sr/ad): ");
			type = scObj.next();
			printMsg("\nEnter customer ID: ");
			id = scObj.next();
			//Check if already existing
			if(ticket.customerDirectory.getCustomer(id) != null)
			{
				printMsg("\nCustomer Id already used!\n\n");
				return null;
				
			}
			printMsg("\nEnter customer name: ");
			name = scObj.next();
			printMsg("\nEnter customer email: ");
			email = scObj.next();
			printMsg("\nEnter credit amount: ");
			cr  = Double.parseDouble( scObj.next());
			
			if(type.toLowerCase().equals("jr"))
				{
					jrCtr = new JuniorCustomer(id, email, name, cr);
					ticket.customerDirectory.addCustomer(jrCtr);
					//printMsg(ticket.customerDirectory.toString());
					return jrCtr;
				}
			if(type.toLowerCase().equals(  "sr"))
			{
				srCtr = new SeniorCustomer(id, email, name, cr);
				ticket.customerDirectory.addCustomer(srCtr);
				return srCtr;
			}
			if(type.toLowerCase().equals( "ad"))
			{
				adCtr = new AdultCustomer(id, email, name, cr);
				ticket.customerDirectory.addCustomer(adCtr);
				return adCtr;
			}
			return null;
		}
		
		public void newStation() 
		{
			String stnName, zone;
			Station station = null;
			Station.StationZone stZone;
			
			printMsg("\nEnter station name(Flagstaff): ");
			stnName = scObj.next();
			printMsg("\nEnter station zone(z1/z2): ");
			zone = scObj.next();
			if (zone.toLowerCase().equals("z1"))
				stZone = Station.StationZone.zone1;
			else
				stZone = Station.StationZone.zone2;
			
			station = new Station(stnName, stZone);
			ticket.stationDirectory.addStation(station);
			
		}
		
		public Journey newJourney()
		{
			String cId, dptSt, arrSt, stTime, edTime;
			
			Pass pass = null;
			Customer cust = null;
			Station departSt, arrivalSt;
			LocalDateTime ldtSt, ldtEd;
			Journey journey;
			
			StringSelection selection = new StringSelection("2022-05-27T10:00:00");
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
			
			printMsg("\nJourney start time(2022-05-27T10:00:00) :");
			//System.out.print("\r2022-05-23T10:00:00");
			stTime = scObj.next();
			ldtSt=  LocalDateTime.parse(stTime); //2022-05-23T10:00:00
			printMsg("\nJourney end time(2022-05-27T10:30:00) :");
			edTime = scObj.next();
			ldtEd = LocalDateTime.parse(edTime);
			printMsg("\nEnter customer ID(my) :");
			cId = scObj.next();
			cust = ticket.customerDirectory.getCustomer(cId);
			printMsg("\nDeparture station(Flagstaff) :");
			dptSt = scObj.next();
			departSt = ticket.stationDirectory.getStation(dptSt);
			ticket.stationDirectory.keepUsageStats(dptSt, 1, 0);
			printMsg("\nArrival station(Parliment) :");
			arrSt = scObj.next();
			arrivalSt = ticket.stationDirectory.getStation(arrSt);
			ticket.stationDirectory.keepUsageStats(arrSt, 0, 1);
			journey = new Journey(ldtSt, ldtEd, pass, cust, departSt, arrivalSt);
			//ticket.computeJourney(journey);
			//printMsg(ticket.stationDirectory.printStationStats());
			return journey;
		}
		
		public void alterZone()
		
		{
			Station stn = null;
			printMsg(ticket.stationDirectory.toString());
			printMsg("\nEnter station name to change zone: ");
			String stnName = scObj.next();
			stn = ticket.stationDirectory.getStation(stnName);
			printMsg("a. Select zone 1\n");
			printMsg("b. Select zone 2\n");
			String opt = scObj.next();
			Station.StationZone sz;
			if(opt.toLowerCase().equals("a"))
			{
				sz = Station.StationZone.zone1;
				stn = ticket.stationDirectory.alterStationZone(stnName, sz);
				printMsg(stn.toString()+"\n");
			}
			
			else if(opt.toLowerCase().equals("b"))
			{
				sz = Station.StationZone.zone2;
				stn = ticket.stationDirectory.alterStationZone(stnName, sz);
				printMsg(stn.toString()+"\n");
			}
			else
			{
				printMsg("\nInvalid choice selected!\n");
			}
			
			
		}
		
		public void alterZonePrice()
		{
			Station stn = null;
			printMsg("\nHourly Zone 1: " + Pass.PassCost.hourlyZ1.getValue() + "\n");
			printMsg("\nDaily Zone 1: " + Pass.PassCost.allDayZ1.getValue() + "\n");
			printMsg("\nHourly Zone 2: " + Pass.PassCost.hourlyZ2.getValue() + "\n");
			printMsg("\nDaily Zone 2: " + Pass.PassCost.allDayZ2.getValue() + "\n");
			
			printMsg("a. Alter hourly zone 1\n");
			printMsg("b. Alter daily zone 1\n");
			printMsg("c. Alter hourly zone 2\n");
			printMsg("d. Alter daily zone 2\n");
			
			String opt = scObj.next();
			//Station.StationZone sz;
			
			if(opt.toLowerCase().equals("a"))
			{
				printMsg("Hourly Zone 1 new price: ");
				double price = scObj.nextDouble();
				Pass.PassCost.hourlyZ1.setValue(price); 
				//printMsg(Station.StationZone"\n");
			}
			
			else if(opt.toLowerCase().equals("b"))
			{
				printMsg("Daily Zone 1 new price: ");
				double price = scObj.nextDouble();
				Pass.PassCost.allDayZ1.setValue(price);
			}
			else if(opt.toLowerCase().equals("c"))
			{
				printMsg("Hourly Zone 2 new price: ");
				double price = scObj.nextDouble();
				Pass.PassCost.hourlyZ2.setValue(price);
			}
			else if(opt.toLowerCase().equals("b"))
			{
				printMsg("Daily Zone 2 new price: ");
				double price = scObj.nextDouble();
				Pass.PassCost.allDayZ2.setValue(price);
			}
			else
			{
				printMsg("\nInvalid choice selected!\n");
			}
		}
					
	
		
	}
