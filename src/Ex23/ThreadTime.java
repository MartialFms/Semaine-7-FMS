package Ex23;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTime {

	public static DateFormat df = new SimpleDateFormat("HH:mm:ss");
	public static Date date = new Date();
  public static void main(String[] args) {
    
    Thread thread = new Thread(new MonRunnable(1000));				// defini le teamps
    System.out.print(df.format(new Date()));
    thread.start();
    
  }

  private static class MonRunnable implements Runnable {

    private long delai;

    public MonRunnable(long delai) {
      this.delai = delai;
    }

    @Override
    public void run() {
    	while(true) {
	      try {
	        Thread.sleep(delai);
	        System.out.println(df.format(new Date()));
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }

    	}
    }
  }
}