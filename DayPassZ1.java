
public class DayPassZ1 extends Pass {

	
	@Override
	public String Print() {
		// TODO Auto-generated method stub
		return "All day pass for "+ this.Zone() + ", costing $"+ this.Cost() ;
	}

	@Override
	public String Zone() {
		// TODO Auto-generated method stub
		return "Zone 1";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return Pass.PassCost.allDayZ1.getValue();// 4.90;
	}

	@Override
	public String Validity() {
		// TODO Auto-generated method stub
		return "Daily";
	}

	@Override
	protected double validity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
