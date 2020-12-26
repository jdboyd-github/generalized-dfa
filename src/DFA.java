// Import documentation
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DFAValidate
{
	// Instance Variables
	private static List<String> states = new ArrayList<String>();
	private static List<Character> sigma = new ArrayList<Character>();
	private static List<String> finalStates = new ArrayList<String>();
	private static String initial = "";
	private static List<String> delta = new ArrayList<String>();
	private static Map<String, Map<Character, String>> transitions;
	private static String current = initial;
	
	// Driver Method
	public static void main(String[] args) throws FileNotFoundException
	{
		if(args.length == 0)
		{
			System.out.println("DFAValidate: no input files specified");
			return;
		}
		
		else if(args.length == 1 || args.length > 2)
		{
			System.out.println("DFAValidate: invalid usage - the program must be given two files as input");
			return;
		}
		
		String fileName = args[0];
		File file = new File(fileName);
		
		// Initializing map of transitions
		transitions = new HashMap<String, Map<Character, String>>();
		
		// Parsing input file for information and storing into variables
		parseFile(fileName);
		
		// Taking delta and turning into usable Map functions
		getTransitions(delta);
		
		fileName = args[1];
		
		// Checking sigma input through DFA to check for acceptance
		if(file.exists())
			check(fileName);
	}
	
	// Method used to parse file for information
	private static void parseFile(String fileName)
	{
		int count = 0;
		
		try {
			// Instance Variables
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			List<String> list = new ArrayList<String>();
			
			// Moving lines from file into list
			while(scan.hasNextLine())
				list.add(scan.nextLine());
			
			// Loop to check all lines of input file
			for(int i = 0; i < list.size(); i++)
			{
				// Comment Lines
				if(list.get(i).substring(0, 1).equals("%"))
					count++;
				
				// % Q - Add to states array list
				else if(count == 1)
					states.add(list.get(i));
				
				// % Sigma - Add to sigma array list
				else if(count == 2)
					sigma.add(list.get(i).charAt(0));
				
				// % F - Add to finalStates array list
				else if(count == 3)
					finalStates.add(list.get(i));
				
				// % Q0 - Initial state variable
				else if(count == 4)
					initial = list.get(i);
				
				// % Delta - Add to delta array list
				else if(count == 5)
					delta.add(list.get(i));
			}
		
		// Throw error if filename not found
		} catch(FileNotFoundException e) {
			System.out.println("DFAValidate: the file '" + fileName + "' could not be opened");
			return;
		}
	}
	
	// Create transitions from delta information
	private static void getTransitions(List<String> arr)
	{
		// Loop through List parameter with all delta lines
		for(int i = 0; i < arr.size(); i++)
		{
			// Splitting line into different elements, with space delimiter
			String[] elements = arr.get(i).split(" ");
			
			// If the key containing source value doesn't exist, create it
			if(!transitions.containsKey(elements[0]))
				transitions.put(elements[0], new HashMap<Character, String>());
			
			// Add source and target values to transition map
			transitions.get(elements[0]).put(elements[1].charAt(0), elements[2]);
		}
	}
	
	// Gets information from input file, and checks through DFA
	private static void check(String fileName)
	{	
		try {
			// Instance Variables
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			List<String> list = new ArrayList<String>();
			
			// Adding lines from file into list
			while(scan.hasNextLine())
				list.add(scan.nextLine());
			
			// Looping through list to get individual line information
			for(int i = 0; i < list.size(); i++)
			{
				// Printing input, followed by accepted or rejected, based on the run method
				System.out.print(list.get(i) + " ");
				
				if(run(list.get(i)))
					System.out.println("accepted");
				
				else
					System.out.println("rejected");
			}
		
		// Throw error if filename not found
		} catch(FileNotFoundException e) {
			System.out.println("DFAValidate: the file '" + fileName + "' could not be opened");
			return;
		}
	}
	
	// Helper method for check, used to return true or false if the end state is accepted or rejected
	private static boolean run(String input)
	{
		// Resetting current state to the start state
		current = initial;
		
		// Looping through the input sequence
		for(int i = 0; i < input.length(); i++)
			current = transitions.get(current).get(input.charAt(i));
		
		// Returns true if end is a final state, false otherwise
		return finalStates.contains(current);
	}
}
