package red;

import java.util.ArrayList;

public class Memory {
	
	private ArrayList<char[]> memory;
	private int memoryPointer = 0;
	private int memoryPointerMax = 0;
	private long reads = 0;
	private long writes = 0;
	private long pages = 0;

	/**
	 * Creates memory for the program to run on.
	 */
	public Memory() {
		memory = new ArrayList<>();
		memory.add(page());
	}
	
	/**
	 * Resets all of the memory.
	 */
	public void clear() {
		System.out.println("Total reads: " + reads);
		System.out.println("Total writes: " + writes);
		System.out.println("Maximum cell: " + memoryPointerMax);
		System.out.println("Total pages created: " + pages);
		reads = 0;
		writes = 0;
		pages = 0;
		memoryPointer = 0;
		memory.clear();
		memory.add(page());
	}
	
	/**
	 * Gets the current value at the pointer.
	 * @return A char value of the current pointer.
	 */
	public char get() {
		reads++;
		return memory.get(currentPage())[memoryPointer % Main.PAGE_SIZE];
	}
	
	/**
	 * Sets the current value at the pointer.
	 * @param value The value to set.
	 */
	public void set(char value) {
		writes++;
		memory.get(currentPage())[memoryPointer % Main.PAGE_SIZE] = value;
	}
	
	/**
	 * Adds 1 to the current pointer.
	 */
	public void increment() {
		writes++;
		memory.get(currentPage())[memoryPointer % Main.PAGE_SIZE]++;
	}
	
	/**
	 * Subtracts 1 to the current pointer.
	 */
	public void decrement() {
		writes++;
		memory.get(currentPage())[memoryPointer % Main.PAGE_SIZE]--;
	}
	
	/**
	 * Moves the pointer to the right.
	 */
	public void next() {
		memoryPointer++;
		if(currentPage() == memory.size()) {
			memory.add(page());
		}
		if(memoryPointer > memoryPointerMax) memoryPointerMax = memoryPointer;
	}
	
	/**
	 * Moves the pointer to the left.
	 * This is safe even if the pointer is at position 0.
	 */
	public void previous() {
		memoryPointer--;
		if(memoryPointer < 0) {
			memory.add(0, page());
			memoryPointer += Main.PAGE_SIZE;
		}
	}

	/**
	 * Creates a new page.
	 * @return A new char[Main.PAGE_SIZE] that is empty.
	 */
	private char[] page() {
		pages++;
		return new char[Main.PAGE_SIZE];
	}
	
	/**
	 * Gets the current page of the pointer.
	 * @return The page that the pointer is within.
	 */
	private int currentPage() {
		return (int)((double)memoryPointer / (double)Main.PAGE_SIZE);
	}
}
