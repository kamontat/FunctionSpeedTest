package tasktimer.alltask.readfile;

import tasktimer.alltask.concatstring.Task6;
import tasktimer.object.Dictionary;

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
public class Task2 extends Task6 implements Runnable {
	private BufferedReader br;

	public Task2() {
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			System.out.println("Could not open dictionary: " + ex.getMessage());
		}
	}

	/**
	 * run this task (2)
	 */
	@Override
	public void run() {
		int count = 0;
		long totalSize = 0;
		String word = null;

		try {
			while ((word = br.readLine()) != null) {
				totalSize += word.length();
				count++;
			}
			double averageLength = ((double) totalSize) / (count > 0 ? count: 1);
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

	/**
	 * return String that explain this task (2)
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "read words using BufferedReader.readLine() with a loop";
	}
}
