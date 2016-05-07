package tasktimer.alltask.readfile;

import tasktimer.object.Dictionary;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Process all the words in a file (one word per line) using Stream
 * and the lines() method which creates a Stream of Strings (one item per line) by using Path.
 * Then use the stream to compute summary statistics.
 *
 * @author kamontat
 * @since 8/5/59 - 01:26
 */
public class TaskByMe implements Runnable {
	private Stream<String> stream;

	public TaskByMe() {
		Path path = new File("src/" + Dictionary.fileName).toPath();

		try {
			stream = Files.lines(path, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			System.out.println("Could not open dictionary: " + ex.getMessage());
		}
	}

	/**
	 * run this task (4)
	 */
	@Override
	public void run() {
		final AtomicLong total = new AtomicLong();
		final AtomicInteger counter = new AtomicInteger();
		stream.forEach(word -> {
			total.getAndAdd(word.length());
			counter.incrementAndGet();
		});  // Ha! No loop.

		// close the input
		stream.close();

		int count = counter.intValue();
		double averageLength = (count > 0) ? total.doubleValue() / count: 0.0;
		System.out.printf("Average length of %,d words is %.2f\n", count, averageLength);
	}

	/**
	 * return String that explain this task (4)
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "read words using Stream and Path";
	}
}
