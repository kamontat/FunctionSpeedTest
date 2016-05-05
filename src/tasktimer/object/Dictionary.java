package tasktimer.object;

import java.io.InputStream;

/**
 * Created by kamontat on 5/5/59.
 */
public class Dictionary {

	public static InputStream getWordsAsStream() {
		String fileName = "tasktimer/wordlist.txt";

		return TaskTimer.class.getClassLoader().getResourceAsStream(fileName);

	}
}
