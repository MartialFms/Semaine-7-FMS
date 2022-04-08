package Ex1;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		System.out.println("**************************************************");
		System.out.println("* Bienvenur dans mon application de la semaine 7 *");
		System.out.println("**************************************************");
		System.out.println("                               ____________\r\n"
				+ "                             __/ ///////// /|\r\n"
				+ "                            /              ¯/|\r\n"
				+ "                           /_______________/ |\r\n"
				+ "    ________________       |  __________  |  |\r\n"
				+ "   /               /|      | | Bienvenu | |  |\r\n"
				+ "  /               / |      | | dans mon | |  |\r\n"
				+ " /_______________/  |/\\   | | program. | |  |\r\n"
				+ "(_______________(   |  \\  | |__________| | /   \r\n"
				+ "(_______________(   |   \\ |______________|/ ___/\\\r\n"
				+ "(_______________(  /     |____>______<_____/     \\\r\n"
				+ "(_______________( /     / = ==== ==== ==== /|    _|_\r\n"
				+ "(   FMS - 2022  (/     / ========= === ===/ /   ////\r\n"
				+ "(_______________/     / ========= === ===/ /   /  / \r\n"
				+ "                     <__________________<_/    ¯¯¯ "
				+ "*************** FAITE VOTRE CHOIX ********************");
				System.out.println(" 1/Exercice 1.1 | 2/Exercice 1.2 | 3/Exercice 2.1 | 4/Exercice 2.2 | 5/Exercice 2.3 | 6/Exercice 3 | 7/Tp Bank");
				Scanner scan = new Scanner(System.in);
				while (!scan.hasNextInt()) scan.next();
				switch (scan.nextInt()) {
				case 1: Ex11.FoundEception.main(args); ; break;
				case 2: Ex12.BaseEx4Array.main(args); ; break;
				case 3: Ex21.TestThread.main(args); ; break;
				case 4: Ex22.TestRunnable.main(args); break;
				case 5: Ex23.ThreadTime.main(args); break;
				case 6: Ex31.Resto.main(args); break;
				case 7: TpBankJob.MyBankApp.main(args);; break;
				default: System.out.println("!!!! Essai encore !!!!");break;
				}	
	}
}
