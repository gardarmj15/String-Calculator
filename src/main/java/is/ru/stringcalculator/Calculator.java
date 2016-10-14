package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator 
{
	public static int add(String text)
	{
		if(containsNewLine(text)){
			text = replaceNewLine(text);
		}
		if(text.equals(""))
		{
			return 0;
		}
		else if(checkDelim(text))
		{
			if(checkCustomDelim(text))
			{
				return handleCustomDelimiters(text);
			}
			else
				return handleDelimiters(text);
		}
		else if(text.contains(","))
		{
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",");
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
	private static boolean containsNewLine(String text) {
    	return text.contains("\n");
	}
	private static String replaceNewLine(String text) {
    	return text.replace("\n",",");
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
	private static boolean checkDelim(String text) {
    	return (text.charAt(0) == '/' && text.charAt(1) == '/');
    }
	private static boolean checkCustomDelim(String text) {
		return (text.charAt(0) == '/' && text.charAt(1) == '/' && text.charAt(2) == '[');
	}
	private static int handleDelimiters(String text){
		String delimiter = String.valueOf(text.charAt(2));
		text = text.substring(4, text.length());
		return sum(text.split(delimiter));
	}
	private static int handleCustomDelimiters(String text){
		int delimBegin = 0;
		int delimEnd = 0;
		List<String> delimiters = new ArrayList<>();
		int counter = 0;
		
		//take // out of the string
		text = text.substring(2, text.length());
		
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '[') {
                delimBegin = i;
            }
			if(text.charAt(i) == ']') {
                delimEnd = i;
				
				//Custom delimiter is now inside the [ ] brackets
                String deliString = text.substring(delimBegin + 1, delimEnd);

                //Adding the delimiter to the arraylist
                delimiters.add(counter, deliString);
				text = text.substring(i + 1, text.length());
				String customDelimiter = delimiters.get(counter);
				
				//Change delimiter to comma
				text = text.replace(customDelimiter, ",");
				
				i = 0;
                counter++;
			}
		}
		text = text.substring(1, text.length());
		
		return sum(splitNumbers(text));
	}
}