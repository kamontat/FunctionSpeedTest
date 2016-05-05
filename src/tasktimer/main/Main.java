package tasktimer.main;

import tasktimer.object.TaskTimer;
import tasktimer.alltask.readfile.*;
import tasktimer.alltask.concatstring.*;

/**
 * Created by kamontat on 5/5/59.
 */
public class Main {
	public static void main(String[] args) {
		TaskTimer.execAndPrint(new Task1());
		TaskTimer.execAndPrint(new Task2());
		TaskTimer.execAndPrint(new Task3());
		TaskTimer.execAndPrint(new Task4());
		TaskTimer.execAndPrint(new Task5(30000));
		TaskTimer.execAndPrint(new Task6(30000));
	}
}
