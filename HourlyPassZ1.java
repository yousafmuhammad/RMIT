
public class HourlyPassZ1 extends Pass {

	@Override
	public String Print() {
		// TODO Auto-generated method stub
		return "Two Hour pass for "+ this.Zone() + ", costing $"+ this.Cost() ;
	}

	@Override
	public String Zone() {
		// TODO Auto-generated method stub
		return "Zone 1";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return Pass.PassCost.hourlyZ1.getValue();// 2.50;
	}

	@Override
	public String Validity() {
		// TODO Auto-generated method stub
		return "Two Hours";
	}
	
	public double validity() {
		return Pass.hourlyPass;
	}

}