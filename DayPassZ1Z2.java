
public class DayPassZ1Z2 extends Pass {

	@Override
	public String Print() {
		// TODO Auto-generated method stub
		return "All day pass for "+ this.Zone() + ", costing $"+ this.Cost() ;
	}

	@Override
	public String Zone() {
		// TODO Auto-generated method stub
		return "Zone 1 and Zone 2" ;
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return Pass.PassCost.allDayZ2.getValue();//6.80;
	}

	@Override
	public String Validity() {
		// TODO Auto-generated method stub
		return "One Day";
	}

	@Override
	protected double validity() {
		// TODO Auto-generated method stub
		return 0;
	}

}