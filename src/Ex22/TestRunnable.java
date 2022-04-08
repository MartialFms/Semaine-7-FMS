package Ex22;

import java.util.Collections;

public class TestRunnable implements Runnable {

	public void run() {
		
		for (int i = 0; i < 9; i++)
			System.out.println(String.join("", Collections.nCopies(i, "*")));			
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new TestRunnable());
		thread.start();
	}
}
