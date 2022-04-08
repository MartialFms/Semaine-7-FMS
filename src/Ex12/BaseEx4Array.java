package Ex12;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BaseEx4Array {

	public static void main(String[] args) {
		ArrayList<String> nomsPrenoms = new ArrayList<String>();

		ArrayList<Double> notes = new ArrayList<Double>();
try {
		System.out.println("Saisissez le nombre d'eleve : ");
		Scanner scanNbElev = new Scanner(System.in);
		int nbElev = scanNbElev.nextInt();

		for (int el = 0; el < nbElev; el++) {
			System.out.println("Saisissez le nom du "+ (el+1)+ "e eleve : ");
			Scanner scanNom = new Scanner(System.in);
			String nom = scanNom.nextLine();

			System.out.println("Saisissez le prenom de l'eleve : ");
			Scanner scanPrenom = new Scanner(System.in);
			String prenom = scanPrenom.nextLine();

			System.out.println("Saisissez le nombre de notes à entrer : ");
			Scanner scanNbNote = new Scanner(System.in);
			int nbNote = scanNbNote.nextInt();

			System.out.println("Saisissez les notes : ");
			int listeNote[] = new int[nbNote];
			for (int i = 0; i < nbNote; i++) {
				listeNote[i] = scanNbNote.nextInt();
			}
		
		

			double moyenNote = moyenneDesNotes(listeNote);
			int meilleurNote = meilleurNote(listeNote);
			int pireNote = pireNote(listeNote);
			String nomsPrenom = nom + " " + prenom;

			System.out.println(nomsPrenom + " a une moyenne de " + moyenNote + ". \nSa plus mauvaise note est de "
					+ pireNote + "/10 et sa meilleure note  " + meilleurNote + "/10.\n");

			nomsPrenoms.add(nom + " " + prenom);

			notes.add(moyenNote);

		}
		System.out.println("\n--------------------------------------------- \nVoici le classement des éleves :");
		for (int cl = 0; cl < nbElev; cl++) {
			Collections.sort(notes);
			System.out.println(nomsPrenoms.get(cl) + " a " + notes.get(cl) + " de moyenne.");
		}

		System.out.println(
				"\n--------------------------------------------- \nVoulez vous les notes d'un eleve en particulier ?");
		String nomCible;
		double noteCible;

		Scanner scanNom = new Scanner(System.in);
		nomCible = scanNom.nextLine();
		for (int sc = 0; sc < nbElev; sc++) {
			if (nomCible.equalsIgnoreCase(nomsPrenoms.get(sc))) {
				System.out.println("Sa moyenne est de " + notes.get(sc) + "/10 !");
			} else {
				System.out.println("L'eleve est absent.");
			}
		}
	}catch(InputMismatchException e) {System.out.println("Ce n'est pas le bon type -> " + e);}

	}

	private static double moyenneDesNotes(int[] notes) {
		int total = 0;
		double moyenNote = 5;
		for (int i = 0; i < notes.length; i++) {
			total += notes[i];
			moyenNote = total / notes.length;
		}
		return moyenNote;
	}

	private static int pireNote(int[] notes) {
		int pireNote = 10;
		for (int i = 0; i < notes.length; i++) {
			if (notes[i] < pireNote) {
				pireNote = notes[i];
			}
		}
		return pireNote;
	}

	private static int meilleurNote(int[] notes) {
		int meilleurNote = 0;
		for (int i = 0; i < notes.length; i++) {
			if (notes[i] > meilleurNote) {
				meilleurNote = notes[i];
			}
		}
		return meilleurNote;
	}

}

// le programe ensuite affichera : "-5 2 -8 31 15 4 4 15 31 -8 2 -5"  meme si j'ai des doutes sur les index "j-1"'