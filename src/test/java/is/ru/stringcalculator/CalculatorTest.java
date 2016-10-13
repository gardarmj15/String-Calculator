package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest 
{

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }
	@Test
    public void testNumbersWithNewline(){
    	assertEquals(3, Calculator.add("1\n2"));
    }
	@Test
    public void testNumbersWithNewlineAndComma(){
    	assertEquals(6, Calculator.add("1\n2,3"));
    }
	@Test(expected = IllegalArgumentException.class)
    public void testNegativeNumbers(){
    	Calculator.add("1,-1");
    }
	@Test
    public void displayNegativeError()
	{
    	try
		{
			Calculator.add("-1,2");
		}
		catch( final IllegalArgumentException ex)
		{
			final String error = "Negatives not allowed: -1";
			assertEquals(error, ex.getMessage());
		}
    }
	@Test
	public void TestMultipleNegativesError()
	{
	  try
	  {
		Calculator.add("2,-4,3,-5");
	  }
	  catch( final IllegalArgumentException ex )
	  {
	    final String msg = "Negatives not allowed: -4, -5";
	    assertEquals(msg, ex.getMessage());
	  }
	}

	@Test
	public void testNumberSmallerThan1000(){
		assertEquals(1002, Calculator.add("1000,2"));
	}
	@Test
	public void testNumberLargerThan1000(){
		assertEquals(2, Calculator.add("1001,2"));
	}
}