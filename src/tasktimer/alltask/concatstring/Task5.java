package tasktimer.alltask.concatstring;

import tasktimer.object.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Append all the words from the dictionary to a String.
 * This shows why you should be careful about using "string1"+"string2".
 * <p>
 * Created by kamontat on 5/5/59.
 */
public class Task5 implements Runnable {
	private int maxCount;
	private BufferedReader br;

	public Task5(int maxCount) {
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
			this.maxCount = maxCount;
		} catch (Exception ex) {
			System.out.println("Could not open dictionary: " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		String result = "";
		String word = null;
		int count = 0;
		try {
			while ((word = br.readLine()) != null && count < maxCount) {
				result = result + word;
				count++;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.printf("Done appending %d words to string.\n", count);
	}

	@Override
	public String toString() {
		return "append " + maxCount + " words to a String using + ";
	}
}
