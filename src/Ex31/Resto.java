package Ex31;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Resto {

	public static void main(String[] args) {

		Menu commandeN1 = new Menu(1, "salade", "boeuf", "frites", "soda", "tiramisu");
		Menu commandeN2 = new Menu(2, "quiche", "poulet", "légumes", "eau plate", "mousse au chocolat");
		Menu commandeN3 = new Menu(3, "soupe", "vegan", "pates", "tiramisu");

		ArrayList<Menu> commandes = new ArrayList<>();
		commandes.add(commandeN1);
		commandes.add(commandeN2);
		commandes.add(commandeN3);
		
		File client = new File("menu.txt");
		String ligne = String.join("", Collections.nCopies(6, "*"));

		try {
			BufferedWriter fichierClient = new BufferedWriter(new FileWriter("menu.txt"));
			commandes.stream().forEach(x -> {
				try {
					fichierClient.write(ligne + "Resumé de la commande N°" + x.getId() + " : " + ligne + "\n"
							+ x.getEntree() + "\n" + x.getPlat() + "\n" + x.getAccompagnement() + "\n" + x.getBoisson()
							+ "\n" + x.getDessert() + "\n\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			fichierClient.newLine();
			fichierClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}