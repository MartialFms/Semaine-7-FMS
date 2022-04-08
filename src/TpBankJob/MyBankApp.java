/************************************************************************************************
 * Version 1.3 d'une appli bancaire simplifi√©e offrant la possibilit√©e de cr√©er des clients,	*
 * des comptes bancaires associ√©s et des op√©rations ou										*
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 				*
 * + permet d'afficher l'historique des transactions sur un compte								*
 * + la gestion des cas particuliers par les exceptions											*
 * Bonus : un menu administrateur pour ajouter des comptes et des utilisateurs					*
 * 																								*
 * @author El babili - 2022 / Derand M. - 2022													*
 * 																								*
 ***********************************************************************************************/

package TpBankJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import TpBankBusiness.IBankBusinessImpl;
import TpBankEntities.Account;
import TpBankEntities.Current;
import TpBankEntities.Customer;
import TpBankEntities.Saving;
import TpBankEntities.Transaction;

public class MyBankApp {
	public static Scanner scan = new Scanner(System.in);
	public static ArrayList<Current> accountList = new ArrayList<Current>();
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static Current currentAccount;
	public static IBankBusinessImpl bankJob;
	
	/****************************************************************************
	* Fonction principale & cycle de vie de l'application						*
	* @param customer, account, bankjob, accountList, cutomerList				*
	****************************************************************************/
	public static void main(String[] args) {
		Customer customer1 = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer customer2 = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");
		Customer customer3 = new Customer(0, "macro", "emmanuel", "emanuel.macro@encul.moi");
		Current account1 = new Current(100200300, new Date(), 1500, 200, customer1);
		Current account2 = new Current(200300400, new Date(), 2000, 300, customer2);
		Current admin = new Current(1, new Date(), 0, 0, customer3);
		
		
		loginActivity();
		// menu pour l'amin (login = 0)
		if(currentAccount == admin) { adminMenuManagement(); }
		// menu pour les utilisateurs
		else { menuChoiceActivity(); }
	}

	/****************************************************************************
	* Fonction pour logger l'utilisateur,										*
	* @param account, accountList, currentAccount								*
	****************************************************************************/
	public static void loginActivity() {
		System.out.println("Saisissez un numero de compte valide :");
		try {
			while (!scan.hasNextInt()) scan.next();
			currentAccount = accountList.stream().filter(n -> scan.nextInt() == n.getAccountId()).findFirst().get();
		} catch (NoSuchElementException e) {
			System.out.println("Vous demandez un compte inexistant !");
		}
		System.out.print("Bienvenue " + currentAccount.getCustomer().getFirstName());
	}

	/****************************************************************************
	* Fonction du menu de l'utilisateur											*
	* @param activity, choice, first											*
	****************************************************************************/
	public static void menuChoiceActivity() {
		boolean activity = true;
		while (activity) {
			boolean first = true;
			String str = first ? ", que souhaitez vous faire ? tapez le numero correspondant" : "\n----------------tapez le numero correspondant----------------";
			System.out.println(str);
			first = false;
			System.out.println("1:versement - 2:retrait - 3:virement - 4:information sur ce compte - 5:liste des operations - 6:changer d'utilisateur - 7:sortir");
			try {
				while (!scan.hasNextInt()) scan.next();
				switch (scan.nextInt()) {
				case 1: putMoney(); break;
				case 2: withdrawMoney(); break;
				case 3: transfertMoney(); break;
				case 4: System.out.println(currentAccount.showAccount()); break;
				case 5: transactionsConsult(); break;
				case 6: loginActivity(); break;
				case 7: System.out.println("Sortie"); activity = false; break;
				default: System.out.println("Mauvaise saisie");break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/****************************************************************************
	* Fonction pour ajouter de l'argent sur le compte							*
	* @param currentAccount, bankjob, value									*
	****************************************************************************/
	public static void putMoney() {
		bankJob = new IBankBusinessImpl();
		System.out.println("Saisissez le montant ‡ verser sur ce compte : ");
		while (!scan.hasNextInt()) scan.next();
		bankJob.pay(currentAccount.getAccountId(), scan.nextInt());
	}

	/****************************************************************************
	* Fonction pour retirer de l'argent du compte								*
	* @param currentAccount, bankjob, value									*
	****************************************************************************/
	public static void withdrawMoney() {
		bankJob = new IBankBusinessImpl();
		System.out.println("Saisissez le montant ‡ retirer sur ce compte : ");
		while (!scan.hasNextInt()) scan.next();
		int value = scan.nextInt();
		if (value < currentAccount.getBalance()) {
			bankJob.withdraw(currentAccount.getAccountId(), value);
		} else {
			System.out.println("Vous avez dÈpassÈ vos capacitÈs de retrait !");
		}
	}

	/**************************************************************************** 
	* Fonction pour ajouter de l'argent sur le compte							*
	* @param currentAccount, bankjob											*
	****************************************************************************/
	public static void transfertMoney() {
		System.out.println("Saisissez le numero du compte du destinataire : ");
		Current targetAccount;
		while (!scan.hasNextInt()) scan.next();
		try {
			targetAccount = accountList.stream().filter(n -> scan.nextInt() == n.getAccountId()).findFirst().get();
			System.out.println("Saisissez la valeur");
			while (!scan.hasNextInt()) scan.next();
			int value = scan.nextInt();
			if (value < currentAccount.getBalance()) {
				System.out.println("Vous avez dÈpassÈ vos capacitÈs de virement !");
			}
			else {
				System.out.println("Vous ne pouvez et retirer sur le meme compte !");
			}
			bankJob.transfert(currentAccount.getAccountId(), targetAccount.getAccountId(), value);
		} catch (NoSuchElementException e) {
			System.out.println("Vous demandez un compte inexistant !");
		}
	}

	/**************************************************************************** 
	* Fonction pour transferer de l'argent d'un compte ‡ un autre				*
	* @param currentAccount, bankjob											*
	****************************************************************************/
	public static void transactionsConsult() {
		System.out.println("liste des transactions de " + currentAccount.getCustomer().getName() + " " + currentAccount.getCustomer().getFirstName());
		for (Transaction trans : bankJob.listTransactions(currentAccount.getAccountId()))
			System.out.println(trans);
	}
	
	/**************************************************************************** 
	* Fonction du menu de l'administrateur										*
	* @param currentAccount, bankjob											*
	****************************************************************************/
	public static void adminMenuManagement(){
		boolean activity = true;
		while (activity) {
			System.out.println("----------------Choisissez le numero d'administration----------------");
			System.out.println("1:ajout utilisateur - 2:supprimer utilisateur - 3:creer un compte - 4:supprimer un compte - 5:afficher les comptes - 6:revenir au menu");
			while (!scan.hasNextInt()) scan.next();
			switch (scan.nextInt()) {
			case 1: addCustomer(); break;
			case 2: deleteCustomer(); break;
			case 3: createAccount(); break;
			case 4: deleteAccount(); break;
			case 5: showAccounts(); break;
			case 6: loginActivity(); break;
			case 7: System.out.println("Sortie"); activity = false; break;
			default: System.out.println("Mauvaise saisie"); break;}
		}
	}

	/**************************************************************************** 
	* Fonction pour ajouter un utilisateur										*
	* Note : ici ne pourra etre que "customer4"									*
	* @param customerList, id, name, firstName, email							*
	****************************************************************************/
	public static void addCustomer(){
        try{
        	int id = customerList.size()+2;
        	System.out.println("Saisissez le nom du client");
        	String name = scan.next();
        	System.out.println("Saisissez le prenom du client");
        	String firstName = scan.next();
        	System.out.println("Saisissez un email");
            String email = scan.next( "[\\w.-]+@[\\w.-]+\\.[a-z]{2,}" );
            Customer customer4 = new Customer(id, name, firstName, email);
            customerList.add(customer4);
        } catch(InputMismatchException e){
            System.out.println("Email non valide");
        }
	}

	/**************************************************************************** 
	* Fonction pour supprimer un utilisateur									*								*
	* @param customerList														*
	****************************************************************************/
	public static void deleteCustomer(){
		System.out.println("Saisissez le numero de client");
		customerList.stream().forEach( c -> System.out.print(c.getCustomerId() + ":" + c.getName() + " " + c.getFirstName() + "   "));
		try {
			while (!scan.hasNextInt()) scan.next();
			customerList.remove(customerList.stream().filter(n -> scan.nextInt() == n.getCustomerId()).findFirst().get());
		} catch (Exception e) {
			System.out.println("Mauvaise saisie.");
		}
	}
	
	/**************************************************************************** 
	* Fonction pour ajouter un compte											*
	* Note : ici ne pourra etre que "account3"									*
	* @param id, balance, date, customerToDelete								*
	****************************************************************************/
	public static void createAccount(){
		System.out.println("Saisissez le numero de compte ‡ ajouter");
		while (!scan.hasNextInt()) scan.next();
		int id = scan.nextInt();		
		System.out.println("Combien vers t'il de base");
		while (!scan.hasNextInt()) scan.next();
		int balance = scan.nextInt();
		System.out.println("Entrez son prenom)");
		String firstName = scan.next();
		System.out.println("Choisir le client (avec son numero)");
		Customer customerToDelete = customerList.stream().filter(n -> scan.nextInt() == n.getCustomerId()).findFirst().get();
		customerList.stream().forEach( c -> System.out.print(c.getCustomerId() + ":" + c.getName() + " " + c.getFirstName() + "   "));
		while (!scan.hasNextInt()) scan.next();
		Current account3 = new Current(id, new Date(), balance, 0, customerToDelete);
        accountList.add(account3);
	}
	
	/**************************************************************************** 
	* Fonction pour supprimer un compte											*
	* @param currentAccount, accountToDelete									*
	****************************************************************************/	
	public static void deleteAccount(){
		System.out.println("Saisissez le numero de compte ‡ supprimer");
		accountList.stream().forEach( c -> System.out.print(c.getAccountId()));
		try {
			while (!scan.hasNextInt()) scan.next();
			Current accountToDelete = accountList.stream().filter(n -> scan.nextInt() == n.getAccountId()).findFirst().get();
			accountList.remove(accountToDelete);
		} catch (Exception e) {
			System.out.println("Mauvaise saisie.");
		}	
	}
	
	/**************************************************************************** 
	* Fonction pour afficher les comptes										*
	* @param accountList														*
	****************************************************************************/
	public static void showAccounts(){
		accountList.stream().forEach(e -> System.out.println(e));
	}
}

