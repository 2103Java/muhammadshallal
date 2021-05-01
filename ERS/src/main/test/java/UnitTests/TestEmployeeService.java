package UnitTests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Random;

import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.service.EmployeeServiceImpl;

public class TestEmployeeService {

	private static int curEmployeeCountBeforeTesting = EmployeeServiceImpl.getInstance().getEmployeeCount();
	
	//Adapted: https://www.baeldung.com/java-random-string
	public static String generateAlphaNumericString() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    System.out.println(generatedString);
	    return generatedString;
	}
		
	@Test
	public void testEmployeeExists() {
		//boolean employeeExists(String username)
		
		//Test for an existing user
		assertTrue(EmployeeServiceImpl.getInstance().employeeExists("hshallal@icloud.com"));
		
		//Test for a nonexisting user
		String username = generateAlphaNumericString() + "@trial.com";
		assertFalse(EmployeeServiceImpl.getInstance().employeeExists(username.toLowerCase()));
	}
	
	//How to test this
	@Test
	public void testGetEmployeeCount() {
		// int getEmployeeCount()
		System.out.println(curEmployeeCountBeforeTesting);
		// Test whether the added employee above was counted
		assertEquals(EmployeeServiceImpl.getInstance().getEmployeeCount(), curEmployeeCountBeforeTesting);
	}
	
	@Test
	public void testRegisterEmployee() {
		//boolean registerEmployee(Employee employee);

		//Register a regular employee
		String username = generateAlphaNumericString()+"@trial.com";
		String password = generateAlphaNumericString();
		Employee employee = new Employee("x".toUpperCase(), "z".toUpperCase(), username.toLowerCase(), password, false);
		
		assertTrue(EmployeeServiceImpl.getInstance().registerEmployee(employee));
		
		//Try to Register an employee with an email that already exists
		employee.setEmail("hshallal@icloud.com");
		assertFalse(EmployeeServiceImpl.getInstance().registerEmployee(employee));
	}
	
	
	
	@Test
	public void testLogin() {
		// Employee login(String username, String password)
		
		//test login of an account that doesn't exist
		String username = generateAlphaNumericString()+"@trial.com";
		String password = generateAlphaNumericString();
		Employee employee = new Employee("x".toUpperCase(), "z".toUpperCase(), username.toLowerCase(), password, false);
		
		assertEquals(EmployeeServiceImpl.getInstance().login(username.toLowerCase(), password), null);
		
		
		//test login of an same account after registration
		assertTrue(EmployeeServiceImpl.getInstance().registerEmployee(employee));
		
		Employee registeredEmployee = EmployeeServiceImpl.getInstance().login(username.toLowerCase(), password);
		//assertEquals each field except the password because we are not supposed to have access to the hashed password
		assertEquals(registeredEmployee.getEmail(), employee.getEmail());
		assertNotEquals(registeredEmployee.getPassword(), employee.getPassword());
		assertEquals(registeredEmployee.getFirstName(), employee.getFirstName());
		assertEquals(registeredEmployee.getLastName(), employee.getLastName());
		assertEquals(registeredEmployee.getIsManager(), employee.getIsManager());
	
	}
	
	@Test 
	public void testSubmitReimbursment() {
		// boolean submitReimbursment(Reimbursment reimbursment)

		//Test the submission of a reimbursement by an existing employee
		Reimbursment reimbursment = new Reimbursment("hshallal@icloud.com", 120.0, "travel", "unittests");
		assertTrue(EmployeeServiceImpl.getInstance().submitReimbursment(reimbursment));
		
		//Test submission of a reimbursement by a non registered employee
		String username = generateAlphaNumericString()+"@trial.com";
		reimbursment = new Reimbursment(username.toLowerCase(), 120.0, "travel", "unittests");
		assertFalse(EmployeeServiceImpl.getInstance().submitReimbursment(reimbursment));
		
	}
	
	@Test public void testShowMyPreviousReimbursments() {
		//List<Reimbursment> showMyPreviousReimbursments(String username, String filter)
		//generate a random user, register the user, submit a reimbursement for the user, get a list
		//of the tickets of this user, assert the size of this list is 1 and that that amount, type,
		//description matches the submitted reimbursement
		
		String username = generateAlphaNumericString()+"@trial.com";
		String password = generateAlphaNumericString();
		Employee employee = new Employee("x".toUpperCase(), "z".toUpperCase(), username.toLowerCase(), password, false);
	
		//registration
		assertTrue(EmployeeServiceImpl.getInstance().registerEmployee(employee));
		
		//logginin
		Employee registeredEmployee = EmployeeServiceImpl.getInstance().login(username.toLowerCase(), password);
		//assertEquals each field except the password because we are not supposed to have access to the hashed password
		assertEquals(registeredEmployee.getEmail(), employee.getEmail());
		assertNotEquals(registeredEmployee.getPassword(), employee.getPassword());
		assertEquals(registeredEmployee.getFirstName(), employee.getFirstName());
		assertEquals(registeredEmployee.getLastName(), employee.getLastName());
		assertEquals(registeredEmployee.getIsManager(), employee.getIsManager());
		
		//Submission
		Reimbursment reimbursment = new Reimbursment(username.toLowerCase(), 120.0, "other", "unittestshow");
		assertTrue(EmployeeServiceImpl.getInstance().submitReimbursment(reimbursment));
		
		//showPrevReimbursements
		List<Reimbursment> retreivedReimbursmentList = EmployeeServiceImpl.getInstance().showMyPreviousReimbursments(username.toLowerCase(), "all", "pending");
		assertEquals(retreivedReimbursmentList.size(), 1);
		assertEquals(retreivedReimbursmentList.get(0).getEmployeeId(), username.toLowerCase());
		assertEquals(retreivedReimbursmentList.get(0).getType(), "other");
		assertEquals(retreivedReimbursmentList.get(0).getDescription(), "unittestshow");	
	}
}
