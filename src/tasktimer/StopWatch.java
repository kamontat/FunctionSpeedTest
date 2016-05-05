package tasktimer;

/**
 * Created by kamontat on 5/5/59.
 */
public class StopWatch {
	private long startTime;
	private long stopTime;

	public void start() {
		startTime = System.nanoTime();
	}

	public void stop() {
		stopTime = System.nanoTime();
	}

	public double getElapsed() {
		return (stopTime - startTime) * 1.0E-9;
	}

	public void reset() {
		// reset time
		startTime = 0;
		stopTime = 0;
	}
}
