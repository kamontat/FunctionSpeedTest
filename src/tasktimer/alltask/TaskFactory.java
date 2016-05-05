package tasktimer.alltask;

import tasktimer.alltask.readfile.Task1;
import tasktimer.alltask.readfile.Task2;
import tasktimer.alltask.readfile.Task3;

/**
 * Created by kamontat on 5/5/59.
 */
public enum TaskFactory {
	TASK1(new Task1()),
	TASK2(new Task2()),
	TASK3(new Task3());

	private Runnable runnable;

	private TaskFactory(Runnable runnable) {
		this.runnable = runnable;
	}

	public Runnable getTask() {
		return runnable;
	}
}
