package UnitTests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


// Adapted: https://www.tutorialspoint.com/junit
public class TestJunit {
   @Test
	
   public void testAdd() {
      String str = "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
}