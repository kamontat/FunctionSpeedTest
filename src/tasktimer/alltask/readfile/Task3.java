package tasktimer.alltask.readfile;

import tasktimer.object.Dictionary;
import tasktimer.object.IntCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Process all the words in a file (one word per line) using BufferedReader
 * and the lines() method which creates a Stream of Strings (one item per line).
 * Then use the stream to compute summary statistics.
 * In a lambda you cannot access a local variable unless it is final,
 * so (as a cludge) we use an attribute for the count.
 * When this method is rewritten as a Runnable, it can be a non-static attribute
 * of the runnable.
 * Display summary statistics and elapsed time.
 * <p>
 * Created by kamontat on 5/5/59.
 */
public class Task3 implements Runnable {
	private BufferedReader br;

	public Task3() {
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception e) {
			System.out.println("Could not open dictionary: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		IntCounter counter = new IntCounter();

		// read text on file -> change it to int -> for loop put into accept method
		br.lines().mapToInt(String::length).forEach(counter::accept);

		System.out.printf("Average length of %,d words is %.2f\n", counter.getCount(), counter.average());

		try {
			br.close();
		} catch (IOException e) {
			System.out.println("can't close Buffered");
		}
	}

	@Override
	public String toString() {
		return "read words using BufferedReader and Stream";
	}
}
