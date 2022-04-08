package Ex21;

public class TestThread2 extends Thread {

	public TestThread2(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 5; i++)
			System.out.print(this.getName());
	}

	public static void main(String[] args) {
		try {
			TestThread2 t = new TestThread2("1- ");
			t.start();
			t.sleep(10);
			TestThread2 t2 = new TestThread2("3--- ");
			t2.start();
			t2.sleep(10);
			TestThread2 t3 = new TestThread2("4---- ");
			t3.start();
			t3.sleep(10);
			TestThread2 t4 = new TestThread2("2-- ");
			t4.start();
			t4.sleep(10);
			TestThread2 t5 = new TestThread2("5----- ");
			t5.start();
			t5.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
