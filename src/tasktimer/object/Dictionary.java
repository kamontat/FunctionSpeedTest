package tasktimer.object;

import java.io.InputStream;

/**
 * read txt from the file and return inputStream
 * Created by kamontat on 5/5/59.
 */
public class Dictionary {
	public static String fileName = "tasktimer/wordlist.txt";

	/**
	 * read file from <b>"tasktimer/wordlist.txt"</b> and get into inputStream
	 *
	 * @return the inputStream
	 */
	public static InputStream getWordsAsStream() {
		return TaskTimer.class.getClassLoader().getResourceAsStream(fileName);
	}
}
