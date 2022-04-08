package Ex11;

import java.util.Date;

public class FoundEception {
	public static void main(String[] args) {
		Date date = null;
		Date today = new Date();
		try {
			System.out.println(date.getClass().getName());
		} catch (Exception e) {
			System.out.println("erreur de date");
		}
		
		try {
			System.out.println(today.getClass().getName());
		} catch (Exception e) {
			System.out.println("erreur de today");
		}
	}
}
