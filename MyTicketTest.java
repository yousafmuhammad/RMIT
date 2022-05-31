import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class MyTicketTest {

	private MyTicket myTicketValidator;
	//@Before
	public void setUp() throws Exception {
		myTicketValidator = new MyTicket(100);
	}

	@SuppressWarnings("deprecation")
	//@Test
	public void test()  {
		//fail("Not yet implemented");
	
		 myTicketValidator.setTi_val(50);  //getEvenNumberFromUser(inputStream, printStream);
		double actualValue = myTicketValidator.getTi_val();
		
		assertEquals(50, actualValue, 0.0001);
	}
	@Test
	public void testOverValue()  {
		
	
		 myTicketValidator.setTi_val(120);  //getEvenNumberFromUser(inputStream, printStream);
		double actualValue = myTicketValidator.getTi_val();
		
		assertEquals(120, actualValue, 0.0001);
	}
	
	@Test
	public void testInvalidMultiple()  {
		
	
		 myTicketValidator.setTi_val(56);  //getEvenNumberFromUser(inputStream, printStream);
		double actualValue = myTicketValidator.getTi_val();
		
		assertEquals(56, actualValue, 0.0001);
	}
	
	@Test
	public void testMaxValue()  {
		
	
		 myTicketValidator.setTi_val(100);  //getEvenNumberFromUser(inputStream, printStream);
		double actualValue = myTicketValidator.getTi_val();
		
		assertEquals(100, actualValue, 0.0001);
	}
}
