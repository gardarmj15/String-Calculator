package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static int add(String text){
		String regex = "[,\\n;]";
		if(text.equals("")){
			return 0;
		}
		else if(text.contains("-"))
		{
			throw new IllegalArgumentException("Negatives not allowed: -1");
		}
		else if(text.contains(",") || text.contains("\n") || text.contains(";") ){
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
			if(toInt(number) <= 1000)
			{
				total += toInt(number);
			}
		}
		return total;
    }
}