package tasktimer.alltask;

import tasktimer.alltask.concatstring.Task5;
import tasktimer.alltask.concatstring.Task6;
import tasktimer.alltask.readfile.*;

/**
 * create on 5/5/59.
 *
 * @author kamontat
 */
public enum TaskFactory {
	TASK1(new Task1()),
	TASK2(new Task2()),
	TASK3(new Task3()),
	TASK4(new Task4()),
	TASKBYME(new TaskByMe()),
	TASK5(new Task5(50000)),
	TASK6(new Task6(50000));

	private Runnable runnable;

	private TaskFactory(Runnable runnable) {
		this.runnable = runnable;
	}

	public Runnable getTask() {
		return runnable;
	}
}
