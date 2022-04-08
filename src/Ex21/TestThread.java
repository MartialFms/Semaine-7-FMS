package Ex21;

public class TestThread extends Thread {

	public TestThread(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 5; i++)
			System.out.print(this.getName());
	}

	public static void main(String[] args) {
		try {
			TestThread t = new TestThread("1- ");
			t.start();
			t.join();
			TestThread t2 = new TestThread("3--- ");
			t2.start();
			t2.join();
			TestThread t3 = new TestThread("4---- ");
			t3.start();
			t3.join();
			TestThread t4 = new TestThread("2-- ");
			t4.start();
			t4.join();
			TestThread t5 = new TestThread("5----- ");
			t5.start();
			t5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
