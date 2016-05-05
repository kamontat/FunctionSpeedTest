package tasktimer.alltask.concatstring;

import tasktimer.object.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Append all the words from the dictionary to a StringBuilder.
 * Compare how long this takes to appending to String.
 * <p>
 * Created by kamontat on 5/5/59.
 */
public class Task6 implements Runnable {
	private int maxCount;
	private BufferedReader br;

	public Task6(int maxCount) {
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
			this.maxCount = maxCount;
		} catch (Exception ex) {
			System.out.println("Could not open dictionary: " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		StringBuilder result = new StringBuilder();
		String word = null;
		int count = 0;
		try {
			while ((word = br.readLine()) != null && count < maxCount) {
				result.append(word);
				count++;
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.printf("Done appending %d words to StringBuilder.\n", count);
	}

	@Override
	public String toString() {
		return "append " + maxCount + " words to a StringBuilder";
	}
}
