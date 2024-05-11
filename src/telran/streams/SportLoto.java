package telran.streams;

import java.util.Random;

record LotoArgs(int minimal, int maximal, int amount) {
	
}

public class SportLoto {

	public static void main(String[] args) {
		
		try {
			LotoArgs lotoArgs = getLotoArgs(args);			
			printSportLoto(lotoArgs);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printSportLoto(LotoArgs lotoArgs) {
		System.out.printf ("**** SportLOTO %d digits from %d to %d!****\n", lotoArgs.amount(),  lotoArgs.minimal(), lotoArgs.maximal());		
		System.out.printf ("\nAnd the result is: ");	
		
		new Random().ints(lotoArgs.minimal(), lotoArgs.maximal()+1).distinct().limit(lotoArgs.amount()).forEach(i -> System.out.printf("\u001B[33m%d \u001B[0m", i));
		
		System.out.println("\nWe are shure,that You are the winner!\n ");
		System.out.println("*********** END OF THE GAME! ***********");
	
	}
		
	

	private static LotoArgs getLotoArgs(String[] args)throws Exception {
		int minimal = getMinimal(args);
		int maximal = getMaximal(args);
		int amount = getAmount(args);
		return new LotoArgs(minimal, maximal, amount);
	
	}

	private static int getAmount(String[] args) throws Exception {
		if(args[2] == null || args.length <3) {
			throw new Exception("Input amount value!");
		}
		int amount = getAmountValue(args[2], args[0], args[1]);
		return amount;
	}

	private static int getAmountValue(String amountString, String minString,String maxString )throws Exception {
		try {
			int result = Integer.parseInt(amountString);
			int min = getMinimalValue(minString);
			int max = getMaximalValue(maxString, minString);
			if (result <1 || result >((max-min)+1) ) {
				throw new Exception("Value argument can't be less then 1 and more then diapason between minimal and maximal arguments inclusively!");
			}
		return result;
		}
		catch (NumberFormatException e) {
			throw new Exception("value must be a number");
		}
		
	}

	private static int getMaximal(String[] args) throws Exception {
		if(args[1] == null || args.length <2) {
			throw new Exception("Input maximal value!");
		}
		int maximal = getMaximalValue(args[1],args[0]);
		
		return maximal;
			
		
	}

	private static int getMaximalValue(String maxString, String minString) throws Exception {
		try {
			int result = Integer.parseInt(maxString);	
			int min = getMinimalValue(minString);
			
			if (result < min) {
				throw new Exception("maximal argument can't be less then minimal!");
			}
		return result;
		}
		catch (NumberFormatException e)  {
			throw new Exception("Maximal must be a number");
		}
	}
	
	private static int getMinimal(String[] args) throws Exception{
		if(args == null || args.length == 0) {
			throw new Exception("Input arguments!");
		}
		int minimal = getMinimalValue(args[0]);
		
		return minimal;
	}
		
	private static int getMinimalValue(String minString) throws Exception {
		try {
			int result = Integer.parseInt(minString);
			if (result < 1) {
				throw new Exception("minimal argument can't be less then 1!");
			}
		return result;
		}
		catch (NumberFormatException e) {
			throw new Exception("Minimal must be a number");
		}
	
	}

	}


