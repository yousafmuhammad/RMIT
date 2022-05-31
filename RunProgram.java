
public class RunProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			

		MenuGenerator assign_obj = new MenuGenerator();
		
	
		String input_choice = "m_menu";
		System.out.println("Welcome to MyTi !");
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

}
