package tasktimer;

import tasktimer.task.TaskFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static java.lang.System.out;

/**
 * Time how long it takes to perform some tasks
 * using different programming constructs.
 * <p>
 * TODO Improve this code by restructuring it to eliminate duplicate code.
 */
public class TaskTimer {

	/**
	 * Process all the words in a file (one word per line) using BufferedReader
	 * and the lines() method which creates a Stream of Strings (one item per line).
	 * Then use the stream to compute summary statistics.
	 * In a lambda you cannot access a local variable unless it is final,
	 * so (as a cludge) we use an attribute for the count.
	 * When this method is rewritten as a Runnable, it can be a non-static attribute
	 * of the runnable.
	 * Display summary statistics and elapsed time.
	 */
	public static void task3() {
		// initialize: open the words file as InputStream
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			out.println("Could not open dictionary: " + ex.getMessage());
			return;
		}

		out.println("Starting task: read words using BufferedReader and Stream");
		long starttime = System.nanoTime();

		long totalsize = 0;
		long count = 0;
		// This code uses Java's IntStream.average() method.
		// But there is no way to also get the count of items.
		// averageLength = br.lines().mapToInt( (word) -> word.length() )
		//                         .average().getAsDouble();
		// So instead we write out own IntConsumer to count and average the stream,
		// and use our IntConsumer to "consume" the stream.
		IntCounter counter = new IntCounter();
		br.lines().mapToInt(word -> word.length()).forEach(counter);
		// close the input
		try {
			br.close();
		} catch (IOException ex) { /* ignore it */ }
		out.printf("Average length of %,d words is %.2f\n", counter.getCount(), counter.average());

		long stoptime = System.nanoTime();
		out.printf("Elapsed time is %f sec\n", (stoptime - starttime) * 1.0E-9);

	}

	/**
	 * Define a customer Consumer class that computes <b>both</b> the average
	 * and count of values.
	 * An IntConsumer is a special Consumer interface the has an 'int' parameter
	 * in accept().
	 */
	static class IntCounter implements IntConsumer {
		// count the values
		public int count = 0;
		// total of the values
		private long total = 0;

		/**
		 * accept consumes an int. In this method, count the value and add it to total.
		 */
		public void accept(int value) {
			count++;
			total += value;
		}

		/**
		 * Get the average of all the values consumed.
		 */
		public double average() {
			return (count > 0) ? ((double) total) / count: 0.0;
		}

		public int getCount() {
			return count;
		}
	}

	/**
	 * Process all the words in a file (one word per line) using BufferedReader
	 * and the lines() method which creates a Stream of Strings (one item per line).
	 * Then use the stream to compute summary statistics.
	 * This is same as task3, except we use a Collector instead of Consumer.
	 */
	public static void task4() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(Dictionary.getWordsAsStream()));
		} catch (Exception ex) {
			out.println("Could not open dictionary: " + ex.getMessage());
			return;
		}

		out.println("Starting task: read words using BufferedReader and Stream with Collector");
		long starttime = System.nanoTime();
		// We want the Consumer to add to the count and total length,
		// but a Lambda can only access local variables (from surrounding scope) if
		// they are final.  That means, we can't use an int, long, or double variable.
		// So, use AtomicInteger and AtomicLong, which are mutable objects.
		final AtomicLong total = new AtomicLong();
		final AtomicInteger counter = new AtomicInteger();
		//TODO Use a Collector instead of Consumer
		Consumer<String> consumer = new Consumer<String>() {
			public void accept(String word) {
				total.getAndAdd(word.length());
				counter.incrementAndGet();
			}
		};

		br.lines().forEach(consumer);  // Ha! No loop.
		// close the input
		try {
			br.close();
		} catch (IOException ex) { /* ignore it */ }

		int count = counter.intValue();
		double averageLength = (count > 0) ? total.doubleValue() / count: 0.0;
		out.printf("Average length of %,d words is %.2f\n", count, averageLength);

		long stoptime = System.nanoTime();
		out.printf("Elapsed time is %f sec\n", (stoptime - starttime) * 1.0E-9);
	}

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
		Runnable task;
		StopWatch watch = new StopWatch();

		for (TaskFactory factory : TaskFactory.values()) {
			watch.start();

			task = factory.getTask();
			task.toString();
			task.run();

			watch.stop();
			System.out.printf("Elapsed time is %.5f sec\n", watch.getElapsed());
			watch.reset();
		}


	}

}
