package tasktimer.task;

import tasktimer.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Process all the words in a file (one word per line) using BufferedReader
 * and the readLine() method.  readLine() returns null when there is no more input.
 * Display summary statistics and elapsed time.
 * <p>
 * Created by kamontat on 5/5/59.
 */
public class Task2 implements Runnable {
	private BufferedReader br;

	public Task2() {
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			System.out.println("Could not open dictionary: " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		int count = 0;
		long totalsize = 0;
		String word = null;

		try {
			while ((word = br.readLine()) != null) {
				totalsize += word.length();
				count++;
			}
			double averageLength = ((double) totalsize) / (count > 0 ? count: 1);
			System.out.printf("Average length of %,d words is %.2f\n", count, averageLength);
		} catch (IOException e) {
			System.out.println(e);
			return;
		} finally {
			try {
				br.close();
			} catch (Exception ex) { /* ignore it */ }
		}
	}

	@Override
	public String toString() {
		return "Starting task: read words using BufferedReader.readLine() with a loop";
	}
}