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
		assertEquals(5, Calculator.add("2,3"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
		assertEquals(11, Calculator.add("2,2,3,4"));
    	assertEquals(29, Calculator.add("1,3,3,3,3,3,3,3,7"));
    }
	@Test
    public void testNumbersWithNewline(){
    	assertEquals(3, Calculator.add("1\n2"));
		assertEquals(23, Calculator.add("1\n2\n3,4,6,7\n"));
    }
	@Test
    public void testNumbersWithNewlineAndComma(){
    	assertEquals(6, Calculator.add("1\n2,3"));
    }
	@Test (expected = IllegalArgumentException.class)
    public void testNegativeNumbers() {
    	assertEquals(3, Calculator.add("-1,2"));
    	assertEquals(2, Calculator.add("1,1"));
    	assertEquals(2, Calculator.add("2,2,-2,1,-3,-5,2,1"));
    }

	@Test
	public void testNumberBiggerThan1000(){
		assertEquals(1002, Calculator.add("1000,2"));
		assertEquals(2000, Calculator.add("1001,1000,500,500"));
	}
}