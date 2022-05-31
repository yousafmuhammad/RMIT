
public abstract class Pass {
	
	public enum PassCost {hourlyZ1(2.5), allDayZ1(4.9) , hourlyZ2(3.5), allDayZ2(6.8);
		private double value;
	       private PassCost(double d) {
	            this.value = d;
	       }
	       public double getValue(){
	        return value;
	       }
	       public void setValue(double value){
	   	    this.value = value;
	   	   }
		}	
	
	//public enum PassType{"Hourly" , "AllDay"};
	public static double hourlyPass = 2;
	
	
	abstract public String Print();
	abstract public String Zone();
	abstract public double Cost();
	abstract public String Validity();
	protected abstract double validity();
	
	
}
