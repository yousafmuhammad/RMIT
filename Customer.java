public interface Customer {
	public enum CustType {adult(1.0), junior(0.5), senior(0.5);
		private double value;
	       private CustType(double d) {
	            this.value = d;
	       }
	       public double getValue(){
	        return value;
	       }
		}
	public Customer getCustomer(String custId);
	//public CustType getCustType();
	public String toString();
	public Customer.CustType getCustType(String CustId);
	public String getCustId(Customer cust);
	public double chrgBalance(double amt);
}