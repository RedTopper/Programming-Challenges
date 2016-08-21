package red;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static final int PAGE_SIZE = 2048;

	public static String[] manual = {
		">	increment the data pointer (to point to the next cell to the right).",
		"<	decrement the data pointer (to point to the next cell to the left).",
		"+	increment (increase by one) the byte at the data pointer.",
		"-	decrement (decrease by one) the byte at the data pointer.",
		".	output the byte at the data pointer.",
		",	accept one byte of input, storing its value in the byte at the data pointer.",
		"[	if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command.",
		"]	if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command."
	};
	
	public static Memory memory = new Memory();
	public static char[] program;
	public static int programPointer = 0;
	public static int nested = 0;
	public static int nestedTracker = 0;

	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Welcome to the Java based BRAINFART Interpreter.");
		main: while(true) {
			System.out.println("========================================================");
			System.out.println("Main menu:");
			System.out.println("c: Create a temporary program");
			System.out.println("h: Help");
			System.out.println("o: Open a program");
			System.out.println("p: Print the loaded program");
			System.out.println("r: Run the loaded program");
			System.out.println("s: Swap EOL (currently " + memory.getEOL() + ")");
			System.out.println("v: Run a program in VERBOSE mode (May spam the console!)");
			System.out.println("q: Quit");
			String user = getHumanString(reader, "Choice");
			execute: switch(user) {
			case "C":
			case "c":
				printProgram();
				program = getHumanString(reader, "Type your new program here").toCharArray();
				break;
			case "H":
			case "h":
				System.out.println("BRAINFART manual:");
				for(String manualLine : manual) {
					System.out.println(manualLine);
				}
				break;
			case "O":
			case "o":
			case "0":
				printProgram();
				String fileName = getHumanString(reader, "File path");
				StringBuilder loader = new StringBuilder();
				try {
					FileReader fileReader = new FileReader(fileName);
					BufferedReader bufferedReader =  new BufferedReader(fileReader);
					String line = null;
					while((line = bufferedReader.readLine()) != null) {
						loader.append(line.replaceAll("[^<>\\+\\-\\.,\\[\\]]",""));
					}
					bufferedReader.close();
					program = loader.toString().toCharArray();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "P":
			case "p":
				printProgram();
				break;
			case "V":
			case "v":
				boolean verbose = true; //TODO: Implement verbose mode.
			case "R":
			case "r":
				System.out.print("Program output:\n");
				long startTime = System.currentTimeMillis();
				while(programPointer != program.length) {
					switch(program[programPointer]) {
					case '>':
						memory.next();
						programPointer++;
						break;
					case '<':
						memory.previous();
						programPointer++;
						break;
					case '+':
						memory.increment();
						programPointer++;
						break;
					case '-':
						memory.decrement();
						programPointer++;
						break;
					case '.':
						System.out.print(memory.get());
						programPointer++;
						break;
					case ',':
						long startHuman = System.currentTimeMillis();
						memory.set(reader);
						startTime += System.currentTimeMillis() - startHuman;
						programPointer++;
						break;
					case '[':
						nestedTracker = nested + 1;
						if(memory.get() == (char)0) {
							while(++programPointer < program.length){
								if(program[programPointer] == '[') {
									nestedTracker++;
								}else if(program[programPointer] == ']') {
									nestedTracker--;
								}
								if(nestedTracker == nested) {
									break;
								}
							}
							if(programPointer == program.length) {
								System.out.println("Runtime error: reached end of program before finding a matching \"]\"");
								break execute;
							}
						}
						programPointer++;
						break;
					case ']':
						nestedTracker = nested + 1;
						if(!(memory.get() == (char)0)) {
							while(--programPointer >= 0){
								if(program[programPointer] == ']') {
									nestedTracker++;
								}else if(program[programPointer] == '[') {
									nestedTracker--;
								}
								if(nestedTracker == nested) {
									break;
								}
							}
							if(programPointer == -1) {
								System.out.println("Runtime error: reached beginning of program before finding a matching \"[\"");
								break execute;
							}
						}
						programPointer++;
						break;
					}
				}
				memory.clear();
				System.out.println("Total time taken: " + ((double)(System.currentTimeMillis() - startTime)/1000.0) + "s" );
				programPointer = 0;
				nested = 0;
				nestedTracker = 0;
				break;
			case "Q":
			case "q":
				break main;
			case "S":
			case "s":
				memory.swapEOL();
				break;
			default:
				System.out.println("Not a valid command!");
			}
		}
		reader.close();
	}
	
	/**
	 * Gets a string from the user via stdin.
	 * This method blocks.
	 * @param reader The Scanner (usually by System.in) that the input should be read from.
	 * @param prompt A string to display to the user. ": " is appended to this string.
	 * @return A user input.
	 */
	public static String getHumanString(Scanner reader, String prompt) {
		System.out.print(prompt + ": ");
		return reader.nextLine();
	}
	
	/**
	 * Gets an int from the user via stdin.
	 * This method blocks.
	 * @param reader The Scanner (usually by System.in) that the input should be read from.
	 * @param prompt A string to display to the user. ": " is appended to this string.
	 * @param lowRange The low limit of the number allowed.
	 * @param highRange The high limit of the number allowed.
	 * @return A user input as an integer within the low range and high range inclusive.
	 */
	public static int getHumanInt(Scanner reader, String prompt, int lowRange, int highRange) {
		int user = 0;
		boolean success = false;
		System.out.print("Input a number between " + lowRange + " and " + highRange + ".");
		do {
			System.out.print(prompt + ": ");
			try {
				user = reader.nextInt();
				if(user >= lowRange && user <= highRange) {
					success = true;
				} else {
					System.out.print("Input a number BETWEEN " + lowRange + " and " + highRange + ".");
				}
			} catch(InputMismatchException e) {
				System.out.print("Input a NUMBER between " + lowRange + " and " + highRange + ".");
				reader.next();
			}
		} while(!success);
		return user;
	}
	
	/**
	 * Prints the current loaded program.
	 */
	public static void printProgram() {
		System.out.print("Current loaded program: ");
		if(program == null) {
			System.out.println("No loaded program yet!");
			return;
		} else {
			StringBuilder output = new StringBuilder();
			for(char c : program) {
				output.append(c);
			}
			System.out.println("\n" + output);
		}
	}
}
