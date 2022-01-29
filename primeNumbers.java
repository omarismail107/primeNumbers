// Import Statements
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class primeNumbers
{ 
	// Creating integer = 10^8
	final static int n = (int)Math.pow(10, 8);
	// Creating boolean Array of size 10^8
	public static boolean primeArray [] = new boolean [n + 1];
	// Creating atomic variables used in multithreading computation
	public static AtomicInteger count = new AtomicInteger(0); 
	public static AtomicLong totalSum = new AtomicLong(0);
	public static ArrayList<Integer> topTen= new ArrayList<Integer>();

	public static class Threading extends Thread
	 {
	 	private int range;
		private int prevNumber;

		// Constructor
	 	public Threading (int prevNumber, int range)
	 	{
	 		this.prevNumber = prevNumber;
	 		this.range = range;
	 	}

	 	@Override
	 	public void run ()
	 	{
	 		// Runs through Sieve's algorithm and counts up
	 		// the total number of primes and the total sum
	 		// if and only if boolean value of i = true
	 		for (int i = prevNumber; i < range; i++)
	 		{
	 			if (primeArray[i] == true)
	 			{
	 				if (range == 100000000)
	 					topTen.add(i);
	 				count.getAndIncrement();
	 				totalSum.getAndAdd(i);

	 			}
	 		}

	 	}
	 }
	
	public static void main (String [] args)
	{
		// Starting stopWatch
		long start = System.nanoTime();
		
		// Initializing all array values to true
		Arrays.fill(primeArray, true);

		// Implementation of Sieve's Algorithm
		for (int i = 2; i <= Math.sqrt(n); i++)
		{
			if (primeArray[i] == true)
			{
				for (int j = 2; i * j < n; j++)
				{
					primeArray[i * j] = false;
				}
			
			}
		}

		int number = (int)Math.pow(10, 8) / 8;
		int prevNumber = 2;
		int newNumber = 12500000;
		ArrayList<Threading> threads = new ArrayList<Threading>();
		
		// Creating first thread	
		Threading t = new Threading(prevNumber, newNumber);
		threads.add(t);
		t.start();
		
		// Creating all threads but first and initializing ranges
		for (int i = 1; i < 8; i++)
		{
			prevNumber = newNumber;
			newNumber = newNumber + number;
			Threading thread = new Threading(prevNumber, newNumber);
			threads.add(thread);
			thread.start();
		}

		// Joining all threads together
		for (int i = 0; i < 8; i++)
		{
			Threading thread = threads.get(i);
			try{
				thread.join();
			}catch(Exception e)
			{

			}
		}

		// Stopping StopWatch
		long end = System.nanoTime();
		long elapsedTime = end - start;

		System.out.println("Elapsed Time in seconds: " + ((double)elapsedTime / (double)1000000000));
		System.out.println();

		System.out.println("Total Number Of Primes Found: ");
		System.out.println(count.get());
		System.out.println();
		System.out.println("Total Sum Of Primes Found: ");
		System.out.println(totalSum.get());
		System.out.println();
		
		ArrayList<Integer> ten = new ArrayList<Integer>();

		System.out.println("Top Ten Maximum Primes: ");
		for (int i = topTen.size() - 10; i < topTen.size(); i++)
		{
			System.out.println(topTen.get(i));
		}
		
	}

}
