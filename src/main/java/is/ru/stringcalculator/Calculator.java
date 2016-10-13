package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator 
{

	public static int add(String text){
		String regex = "[,\\n;]";
		if(text.equals(""))
		{
			return 0;
		}
		else if(text.contains(",") || text.contains("\n") || text.contains(";") )
		{
			return sum(splitNumbers(text, regex));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers, String regex){
	    return numbers.split(regex);
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
			if(toInt(number) < 0)
			{
				throw new IllegalArgumentException(NegativeNumber(numbers));
			}
			if(toInt(number) <= 1000)
			{
				total += toInt(number);
			}
		}
		return total;
    }
	
	private static String NegativeNumber(String[] numbers) {
    	String errorMessage = "Negatives not allowed: ";
    	for(String number : numbers) {
    		if(toInt(number) < 0) {
    			errorMessage += number + ", ";
    		} 
    	}
    	if(errorMessage.charAt(errorMessage.length() - 1) == ',') {
    		errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
    	}
    	return errorMessage;
    }
	
	private static int Exceptions(String msg, String regex)
	{
		String[] numbers = splitNumbers(msg, regex);
    	int curr = 0;
		List<Integer> negNumbers = new ArrayList<Integer>();
		for(String number : numbers)
		{
    		curr = toInt(number);
		    if(curr < 0)
		    	negNumbers.add(curr);
		}
		if(!negNumbers.isEmpty())
			throw new IllegalArgumentException("Negatives not allowed: "+ negNumbers.toString());
		return 1;
	}
}