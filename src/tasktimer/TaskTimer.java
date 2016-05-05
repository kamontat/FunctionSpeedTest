package tasktimer;

import tasktimer.task.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

/**
 * Time how long it takes to perform some tasks
 * using different programming constructs.
 * <p>
 * TODO Improve this code by restructuring it to eliminate duplicate code.
 */
public class TaskTimer {

	// Limit number of words read.  Otherwise, the next task could be very sloooow.
	static final int MAXCOUNT = 50_000;

	/**
	 * Append all the words from the dictionary to a String.
	 * This shows why you should be careful about using "string1"+"string2".
	 */
	public static void task5() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			out.println("Could not open dictionary: " + ex.getMessage());
			return;
		}

		out.println("Starting task: append " + MAXCOUNT + " words to a String using +");
		long starttime = System.nanoTime();
		String result = "";
		String word = null;
		int count = 0;
		try {
			while ((word = br.readLine()) != null && count < MAXCOUNT) {
				result = result + word;
				count++;
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.printf("Done appending %d words to string.\n", count);
		long stoptime = System.nanoTime();
		out.printf("Elapsed time is %f sec\n", (stoptime - starttime) * 1.0E-9);
	}

	/**
	 * Append all the words from the dictionary to a StringBuilder.
	 * Compare how long this takes to appending to String.
	 */
	public static void task6() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			out.println("Could not open dictionary: " + ex.getMessage());
			return;
		}

		out.println("Starting task: append " + MAXCOUNT + " words to a StringBuilder");
		long starttime = System.nanoTime();
		StringBuilder result = new StringBuilder();
		String word = null;
		int count = 0;
		try {
			while ((word = br.readLine()) != null && count < MAXCOUNT) {
				result.append(word);
				count++;
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.printf("Done appending %d words to StringBuilder.\n", count);
		long stoptime = System.nanoTime();
		out.printf("Elapsed time is %f sec\n", (stoptime - starttime) * 1.0E-9);
	}


	/**
	 * Run all the tasks.
	 */
	public static void main(String[] args) {


		execAndPrint(new Task1());


	}

	public static void execAndPrint(Runnable task) {
		StopWatch watch = new StopWatch();
		System.out.println("Starting task: " + task.toString());

		watch.start();
		task.run();
		watch.stop();

		System.out.printf("Elapsed time is %.5f sec\n", watch.getElapsed());
		watch.reset();
	}

}
