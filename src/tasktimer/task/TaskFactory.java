package tasktimer.task;

/**
 * Created by kamontat on 5/5/59.
 */
public enum TaskFactory {
	TASK1(new Task1()),
	TASK2(new Task2());

	private Runnable runnable;

	private TaskFactory(Runnable runnable) {
		this.runnable = runnable;
	}

	public Runnable getTask() {
		return runnable;
	}
}
