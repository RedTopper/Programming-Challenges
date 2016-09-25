package filefrag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Wtf {

	/**
	 * Obtains all unique results of the files at a specified index.
	 * @param sortedList The list to find pairs from already sorted by length.
	 * @param index The index to start searching from.
	 * @param nextIndex The amount of index advanced to perform the operation
	 * @return All orders of all possibilities at a specific index, traversing until the length of the 
	 * object changes.
	 */
    public static ArrayList<String> obtainAllPossibilitiesAtLength(ArrayList<String> sortedList, int index, int[] deltaIndex) {
    	deltaIndex[0] = 0;
    	
    	//Unique set
    	HashSet<String> uniqueResults = new HashSet<>();
    	
    	//Get the corresponding strings at the specific index AND at the inverse location at the end of the list.
		String absoluteMin = sortedList.get(index);
		String absoluteMax = sortedList.get(sortedList.size() - 1 - index);
    	for(int i = index; i < sortedList.size(); i++) {
    		
    		//Do the same for the one we are looking at
    		String thisMin = sortedList.get(i);
    		String thisMax = sortedList.get(sortedList.size() - 1 - i);
    		
    		//Compare their lengths to the original.
    		if(thisMin.length() == absoluteMin.length() && thisMax.length() == absoluteMax.length()) {
    			uniqueResults.add(thisMin + thisMax);
    			uniqueResults.add(thisMax + thisMin);
    			
    			//update the change in the index.
    			deltaIndex[0]++;
    		} else {
    			
    			//Stop searching when the lengths change on us.
    			break;
    		}
    	}
    	ArrayList<String> result = new ArrayList<>();
    	result.addAll(uniqueResults);
		return (result.size() > 0 ? result : null);
    }
    
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int testCases = reader.nextInt();
		reader.nextLine(); //Advance scanner past int.
		reader.nextLine(); //Read over blank line.
		while(reader.hasNextLine() && testCases-- > 0) {
			ArrayList<String> input = new ArrayList<>();
			
			//read all lines
			while(reader.hasNextLine()) {
				String in = reader.nextLine();
				if(in.length() == 0) break;
				input.add(in);
			}
			
			//sort by length
			Collections.sort(input, new Comparator<String>() {
				public int compare(String s1,String s2){
					return s1.length() - s2.length();
				}
			});
			
			//Current index we are on.
			int[] deltaIndex = {0};
			int index = 0;
			
			//List of possible end result files.
			ArrayList<String> result = obtainAllPossibilitiesAtLength(input, 0, deltaIndex);
			index += deltaIndex[0];
			for(int i = index; i < input.size(); i++) {
				if(result.size() != 1) {
					ArrayList<String> next = obtainAllPossibilitiesAtLength(input, i, deltaIndex);
					if(next == null) continue;
					result.retainAll(next);
					i += deltaIndex[0];
				} else {
					break;
				}
			}
			Collections.sort(result);
			System.out.println(result.get(0));
			if(testCases != 0) System.out.println();
		}
		reader.close();
	}
}
