
public class HourlyPassZ1Z2 extends Pass {

	@Override
	public String Print() {
		// TODO Auto-generated method stub
		return "Two Hour pass for "+ this.Zone() + ", costing $"+ this.Cost() ;
	}

	@Override
	public String Zone() {
		// TODO Auto-generated method stub
		return "Zone1 and Zone2";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return Pass.PassCost.hourlyZ2.getValue(); //3.50;
	}

	@Override
	public String Validity() {
		// TODO Auto-generated method stub
		return "Two Hours";
	}

	@Override
	protected double validity() {
		// TODO Auto-generated method stub
		return Pass.hourlyPass;
	}

}