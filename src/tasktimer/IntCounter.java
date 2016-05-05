package tasktimer;

import java.util.function.IntConsumer;

/**
 * Define a customer Consumer class that computes <b>both</b> the average
 * and count of values.
 * An IntConsumer is a special Consumer interface the has an 'int' parameter
 * in accept().
 * <p>
 * Created by kamontat on 5/5/59.
 */

public class IntCounter implements IntConsumer {
	// count the values
	public int count = 0;
	// total of the values
	private long total = 0;

	/**
	 * accept consumes an int. In this method, count the value and add it to total.
	 */
	@Override
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