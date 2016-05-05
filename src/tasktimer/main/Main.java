package tasktimer.main;

import tasktimer.object.TaskTimer;
import tasktimer.alltask.readfile.Task1;

/**
 * Created by kamontat on 5/5/59.
 */
public class Main {
	public static void main(String[] args) {
		TaskTimer.execAndPrint(new Task1());
	}
}
