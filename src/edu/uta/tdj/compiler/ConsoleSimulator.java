package edu.uta.tdj.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import edu.uta.tdj.ui.ConsolPanel;

/**
 * Class for console simulation
 * 
 */
public class ConsoleSimulator extends Thread implements Runnable {

	private volatile boolean isStop = false;

	private static final int INFO = 0;

	private static final int ERROR = 1;

	private InputStream is;
	private int type;

	/** Creates a new instance of StreamInterceptor */
	public ConsoleSimulator(InputStream is, int type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		String s;
		try {
			while ((!isStop) && (s = reader.readLine()) != null) {
				if (s.length() != 0) {
					if (type == INFO) {
						ConsolPanel.getInstance().setConsoleText(" INFO> " + s);
					} else {
						ConsolPanel.getInstance()
								.setConsoleText(" ERROR> " + s);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void RunCode(String path) throws IOException,
			InterruptedException {
		Process child = Runtime.getRuntime().exec("java -classpath " + path);
		InputStream stdin = child.getInputStream(); //
		InputStream stderr = child.getErrorStream();
		Thread tIn = new Thread(new ConsoleSimulator(stdin, INFO));
		Thread tErr = new Thread(new ConsoleSimulator(stderr, ERROR));
		tIn.start();
		tErr.start();
		tIn.join();
		tErr.join();
	}

}