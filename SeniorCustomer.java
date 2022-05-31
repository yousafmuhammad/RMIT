public class SeniorCustomer implements Customer {
	
	
	
	String cId;
	String cEmail;
	String cName;
	CustType cType;
	double cCr;
	
	public SeniorCustomer(String cId, String cEmail, String cName, double cCr) 
	{
		super();
		this.cId = cId;
		this.cEmail = cEmail;
		this.cName = cName;
		this.cType = Customer.CustType.senior;
		this.cCr = cCr;
	}

	@Override
	public String toString() {
		return "SeniorCustomer [Customer Id=" + cId + ", Email=" + cEmail + ", Name=" + cName + ", Customer Type=" + cType + ", Credit="
				+ cCr + "]";
	}
	
	@Override
	public Customer getCustomer(String custId) {
		// TODO Auto-generated method stub
		if (this.cId.equals(custId))
		{
			return this;
		}
		return null;
				
	}

	@Override
	public Customer.CustType getCustType(String custId) {
		// TODO Auto-generated method stub
		if (this.cId.equals(custId))
			return this.cType;
		return null;
	}
	
	@Override
	public String getCustId(Customer cust) {
		// TODO Auto-generated method stub
		return this.cId;
		
	}
	
	@Override
	public double chrgBalance(double amt) {
		// TODO Auto-generated method stub
		return this.cCr+=amt;
	}

}