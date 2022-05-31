/* Marking Criteria
 * Assignment 1 rubric
Assignment 1 rubric
Criteria	Ratings	Pts
Menu selection operations correctly implemented and properly handling any input 3 pts
At least 5 test cases scripted or implemented/demonstrated, tesing different functionalities for both expected operation and potential failure (e.g,. out-of-scope menu options) 3 pts
Correct functionality on choosing menu items, purchasing correct Travel Pass options and maintaining correct MyTi balance 3 pts
Appropriate choice of data structures and class designs, use of polymorphism where appropriate. 2 pts
Clear, meaningful and appropriate output after all operations, whether successful or failed 2 pts
Well-formed code, adequately documented, appropriate variable names and code properly indented as per Java standards 2 pts
Total points: 15

 * 
 * 
 * https://tinyurl.com/fz9u6k93
 * */

import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
public class AssignmentPrtA {
	
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
	
	Scanner scObj;
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
	
	public AssignmentPrtA()
	{
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
		
		
		/*
		 * 1. Buy a travel pass
			2. Charge my MyTi
			3. Show remaining credit
			0. Quit
		 * */

		m_menu.put("1", new SimpleEntry<String, String>("buy_menu" , "Buy a travel pass") );
		m_menu.put("2" , new SimpleEntry<String, String>("chrg_menu" ,  "Charge my MyTi"));
		m_menu.put("3", new SimpleEntry<String, String>("cr_menu" ,  "Show remaining credit"));
		m_menu.put("0", new SimpleEntry<String, String>("qt_menu" , "Quit"));
		
		/*What time period:
		a) 2 Hours
		b) All Day
		c) cancel
		Your selection: a
		*/
		buy_menu.put("a", new SimpleEntry<String, String>("buy_2hr_menu", "2 Hours"));
		buy_menu.put("b" , new SimpleEntry<String, String>("buy_ad_menu",  "All Day"));
		buy_menu.put("c",  new SimpleEntry<String, String>("buy_cancel_menu","Cancel"));
		
		
		buy_2hr_menu.put("a", new SimpleEntry<String, String>("buy_2hr_z1_menu","Zone 1"));
		buy_2hr_menu.put("b" , new SimpleEntry<String, String>("buy_2hr_z1_z2_menu",  "Zone 1 & 2"));
		buy_2hr_menu.put("c", new SimpleEntry<String, String>("buy_2hr_cancel_menu","Cancel"));
		
		buy_ad_menu.put("a", new SimpleEntry<String, String>("buy_ad_z1_menu", "Zone 1"));
		buy_ad_menu.put("b" , new SimpleEntry<String, String>("buy_ad_z1_z2_menu", "Zone 1 & 2"));
		buy_ad_menu.put("c", new SimpleEntry<String, String>("buy_ad_cancel_menu", "Cancel"));
		
		scObj = new Scanner(System.in);

	}
	
	public static void main(String [] args) {
		/*
		 * 1. Load Main Menu
		 * */
		
		AssignmentPrtA assign_obj = new AssignmentPrtA();
		
		
		//Testing
		/*
		assign_obj.chrgMyTi(50);
		assign_obj.showCredit();
		assign_obj.buyMyTi("buy_2hr_z1_menu");
		assign_obj.buyMyTi("buy_2hr_z1_z2_menu");
		assign_obj.buyMyTi("buy_ad_z1_menu");
		assign_obj.buyMyTi("buy_ad_z1_z2_menu");
		*/
		String input_choice = "m_menu";
		System.out.println(PROG_TITLE);
		do {
			
			//if(pre_choice )
		try {	
		input_choice = assign_obj.disp_menu(input_choice);
		System.out.println();
		}
		catch(Exception ex) {
			System.out.println("\nInvalid Input!\n There is an exception! Program will skip this input! \n Details are as: \n" + ex.toString()+"\n");
			input_choice = "m_menu";
		}
		catch(java.lang.Error er) {
			System.out.println("\n" + er.toString() + "\n");
			input_choice = "m_menu";
		}
		
		} while(input_choice != "0");
		
		
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
		case "buy_2hr_menu":
			display_menu_item(buy_2hr_menu);
			System.out.print(MENU_SELECT);
			input_ch = scObj.next();
			input_ch =  buy_2hr_menu.get(input_ch).getKey();
			break;
		case "buy_2hr_z1_menu":
			buyMyTi("buy_2hr_z1_menu");
			//display_menu_item(buy_2HR_menu);
			//System.out.print(MENU_SELECT);
			//input_ch = scObj.next();
			input_ch = "m_menu";
			break;
		case "buy_2hr_z1_z2_menu":
			buyMyTi("buy_2hr_z1_z2_menu");
			//display_menu_item(buy_2HR_menu);
			//System.out.print(MENU_SELECT);
			//input_ch = scObj.next();
			input_ch = "m_menu";
			break;
		case "buy_2hr_cancel_menu":
			input_ch = "buy_menu";
			break;
		case "buy_cancel_menu":
			input_ch = "m_menu";
			break;
			
			
		case "buy_ad_menu":
			display_menu_item(buy_ad_menu);
			System.out.print(MENU_SELECT);
			input_ch = scObj.next();
			input_ch =  buy_ad_menu.get(input_ch).getKey();
			break;
		case "buy_ad_z1_menu":
			buyMyTi("buy_ad_z1_menu");
			//display_menu_item(buy_2HR_menu);
			//System.out.print(MENU_SELECT);
			//input_ch = scObj.next();
			input_ch = "m_menu";
			break;
		case "buy_ad_z1_z2_menu":
			buyMyTi("buy_ad_z1_z2_menu");
			//display_menu_item(buy_2HR_menu);
			//System.out.print(MENU_SELECT);
			//input_ch = scObj.next();
			input_ch = "m_menu";
			break;
		case "buy_ad_cancel_menu":
			input_ch = "buy_menu";
			break;
			
		case "chrg_menu":
			//Get input from user
			
			chrgMyTi(24);
			//display_menu_item(buy_2HR_menu);
			//System.out.print(MENU_SELECT);
			//input_ch = scObj.next();
			input_ch = "m_menu";
			break;
		case "cr_menu":
			showCredit();
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
	
	private void display_menu_item(HashMap<String, Entry<String, String >> menu_state) {
		for (Entry<String, Entry<String, String>> entry : menu_state.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue().getValue();
		    System.out.println(key + ". " + value);
		    
		}
	}
	
	private void chrgMyTi(double amt)
	{
		System.out.println("How much do you want add: ");
		amt = scObj.nextDouble();
		
		if (amt+totalCharge > 100) {
			System.out.println("\nSorry, the max amount of credit allowed is $" + CHRG_MAX);
			showCredit();
		}
		else if(amt%CHRG_MULTIPLE != 0.0 ) {
			System.out.println(amt%CHRG_MULTIPLE + "\nSorry, you can add multiples of $" + CHRG_MULTIPLE);
			showCredit();
		}
		else {
			totalCharge += amt;
			showCredit();
		}
		
	}
	
	private void showCredit() {
		System.out.println("Your Credit = $" + totalCharge);
	}
	
	private void buyMyTi(String choice) {
		switch(choice) {
		case "buy_2hr_z1_menu":
			if(totalCharge - TICKET_VAL.HR_ZONE_1.get_val() < 0.0 ) throw new java.lang.Error("Not enough credit!");
			totalCharge -= TICKET_VAL.HR_ZONE_1.get_val();
			System.out.println("You purchased 2 Hour pass for Zone 1, costing $" + TICKET_VAL.HR_ZONE_1.get_val() + "\nYour remaining credit is $" + totalCharge);
			break;
		case "buy_2hr_z1_z2_menu":
			if(totalCharge - TICKET_VAL.HR_ZONE_1_2.get_val() < 0.0 ) throw new java.lang.Error("Not enough credit!");
			totalCharge -= TICKET_VAL.HR_ZONE_1_2.get_val();
			System.out.println("You purchased 2 Hour pass for Zone 1 and Zone 2, costing $" + TICKET_VAL.HR_ZONE_1_2.get_val() + "\nYour remaining credit is $" + totalCharge);
			break;
		case "buy_ad_z1_menu":
			if(totalCharge - TICKET_VAL.FD_ZONE_1.get_val() < 0.0 ) throw new java.lang.Error("Not enough credit!");
			totalCharge -= TICKET_VAL.FD_ZONE_1.get_val();
			System.out.println("You purchased all day pass for Zone 1, costing $" + TICKET_VAL.FD_ZONE_1.get_val() + "\nYour remaining credit is $" + totalCharge);
			break;
		case "buy_ad_z1_z2_menu":
			if(totalCharge - TICKET_VAL.FD_ZONE_1_2.get_val() < 0.0 ) throw new java.lang.Error("Not enough credit!");
			totalCharge -= TICKET_VAL.FD_ZONE_1_2.get_val();
			System.out.println("You purchased all day pass for Zone 1 and Zone 2, costing $" + TICKET_VAL.FD_ZONE_1_2.get_val() + "\nYour remaining credit is $" + String.format("%.2f", totalCharge) );
			break;

		}
	}
}
