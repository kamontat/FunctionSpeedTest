package tasktimer.object;

import java.io.InputStream;

/**
 * read txt from the file and return inputStream
 * Created by kamontat on 5/5/59.
 */
public class Dictionary {

	/**
	 * read file from <b>"tasktimer/wordlist.txt"</b> and get into inputStream
	 *
	 * @return the inputStream
	 */
	public static InputStream getWordsAsStream() {
		String fileName = "tasktimer/wordlist.txt";
		return TaskTimer.class.getClassLoader().getResourceAsStream(fileName);
	}
}
