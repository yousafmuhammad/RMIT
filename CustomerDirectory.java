import java.util.List;
import java.util.ArrayList;

public class CustomerDirectory implements Customer  {
	
	private List<Customer> customerList = new ArrayList<>();
	private Customer currCust;
	
	
	public void addCustomer(Customer cust)
	{
		if (cust!=null)
		{
			customerList.add(cust);
			this.currCust = cust;
		}
	}
	
	public void removeCustomer(Customer cust)
	{
		customerList.remove(cust);
		this.currCust = null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String output = "";
		for(Customer cust : customerList)
		{
			output = output +  cust.toString() + '\n';
		}
		return output;
	}

	//@Override
	public Customer getCustomer(String custId) {
		// TODO Auto-generated method stub
		
		for(Customer cust : customerList)
		{
			if (cust.getCustomer(custId) != null)
			{
				this.currCust = cust;
				return cust;
			}
				
		}
		//return output;
		return null;
	}

	@Override
	public Customer.CustType getCustType(String custId) {
		// TODO Auto-generated method stub
		for(Customer cust : customerList)
		{
			if (cust.getCustomer(custId) != null)
				return cust.getCustType(custId);
		}
		//return output;
		return null;
	}

	@Override
	public String getCustId(Customer cust) {
		// TODO Auto-generated method stub
		for(Customer cus : customerList)
		{
			if (cus == cust )
				return cus.getCustId(cust);
		}
		return null;
	}
	
	@Override
	public double chrgBalance(double amt) {
		// TODO Auto-generated method stub
		for(Customer cus : customerList)
		{
			if(cus == this.currCust)
				return this.currCust.chrgBalance(amt);
		}
		return -1;
	}
	
}
