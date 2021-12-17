package up.mi.pirates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class UtilPirates {

	private UtilPirates() {
	}

	/**
	 * genere des nom
	 * 
	 * @param nombre
	 * @return
	 */
	public static String[] genererNoms(int nombre) {
		final char base = 'A';
		String[] res = new String[nombre];

		if (nombre < 1)
			throw new IllegalArgumentException("le nombre de pirate doit etre positif");

		for (int i = 0; i < nombre; i++)
			res[i] = "" + (char) (base + i) + (i > 26 ? Integer.toString((i - (i % 26)) / 26) : ""); //

		return res;
	}

	/**
	 * Genere un Objet Equipage en fonction d'un fichier
	 * 
	 * @param fin - le fichier
	 * @return l'equipage généré
	 * @throws FileNotFoundException
	 */
	public static Equipage genererDepuisFichier(File fin) throws FileNotFoundException {
		Scanner sc = new Scanner(fin);
		Equipage res;
		ArrayList<String> nomsPirates = new ArrayList<>();
		ArrayList<String> nomsObjets = new ArrayList<>();
		ArrayList<String[]> relations = new ArrayList<>();
		ArrayList<String[]> preferences = new ArrayList<>();

		while (sc.hasNextLine()) {
			String[] parsedLine = parseLine(sc.nextLine());

			// genere de quoi generer l'objet correspondant a la ligne
			switch (parsedLine[0]) {
			case "pirate":
				nomsPirates.add(parsedLine[1]);
				break;

			case "objet":
				nomsObjets.add(parsedLine[1]);
				break;

			case "deteste":
				String[] relationBuffer = new String[2];
				relationBuffer[0] = parsedLine[1];
				relationBuffer[1] = parsedLine[2];
				relations.add(relationBuffer);
				break;

			case "preferences":
				String[] preferenceBuffer = new String[parsedLine.length - 1];
				for (int i = 1; i < parsedLine.length; i++)
					preferenceBuffer[i - 1] = parsedLine[i];
				preferences.add(preferenceBuffer);
				break;
			}
		}

		// crée l'equipage
		res = new Equipage(nomsPirates.toArray(new String[0]), nomsObjets.toArray(new String[0]));

		// ajoute les relation
		for (String[] relation : relations) {
			res.ajoutRelation(res.getPirateParNom(relation[0]), res.getPirateParNom(relation[1]));
		}

		// ajoute les preferences
		for (String[] preference : preferences) {
			String[] prefFinal = new String[preference.length - 1];
			for (int i = 1; i < preference.length; i++)
				prefFinal[i - 1] = preference[i];
			res.getPirateParNom(preference[0]).setPreferences(prefFinal);
		}

		sc.close();
		return res;
	}

	/**
	 * encode une ligne sous la forme "Objet(arg1,arg2...)" en tableau
	 * 
	 * @param ligne - la ligne a deconder
	 * @return un tableau sous la forme ["Objet", "arg1", "arg2", ...]
	 */
	public static String[] parseLine(String ligne) {
		ArrayList<String> res = new ArrayList<>();
		String buffer = "";

		int pos = 0;

		while (ligne.charAt(pos) != '(' && pos < ligne.length()) {
			buffer += ligne.charAt(pos);
			pos++;
		}
		res.add(buffer);

		while (ligne.charAt(pos) != ')' && pos < ligne.length()) {
			buffer = "";
			pos++;
			while (ligne.charAt(pos) != ',' && ligne.charAt(pos) != ')' && pos < ligne.length()) {
				buffer += ligne.charAt(pos);
				pos++;
			}
			res.add(buffer);
		}

		return res.toArray(new String[0]);
	}

	public static void resolutionNaive(Equipage eq, int k) {

		eq.assignerObjets();
		if(eq.getRelations().size() < 1) // s'il n'y as pas de relation
			return;
		
		for (int i = 0; i < k; i++) {
			int coutApres, coutAvant = eq.coutTotal();
			int randChoice = (int) (Math.random() * eq.getRelations().size());
			
			// choisis les pirates d'une relation au hasard
			Pirate p1 = eq.getRelations().get(randChoice).getP1();
			Pirate p2 = eq.getRelations().get(randChoice).getP2();
			
			eq.echange(p1, p2); // echange les objetsde p1 et p2
			
			coutApres = eq.coutTotal();
			
			if(coutApres > coutAvant)
				eq.echange(p1, p2); // retourne a p1 ett p2 leurs objets
		}
	}

	/**
	 * le programme enregistre la solution actuelle dans un fichier
	 * 
	 * @param eq             - l'equipage a sauvegarder
	 * @param outputFilePath - le chemin du fichier sur lequel sauvegarder
	 */
	public static void sauvegardeFichier(Equipage eq, String outputFilePath) {
		File file = new File(outputFilePath);

		// Si le fichier n'existe pas
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// liste qui retourne les pirates avec l'objet obtenu
		ArrayList<String> res = new ArrayList<String>();

		for (Pirate pirate : eq.getPirates().values()) {
			res.add(pirate.getNom() + ":" + pirate.getObjet());
		}

		try {
			FileWriter writer = new FileWriter(file);

			for (int i = 0; i < res.size(); i++) {

				writer.write(res.get(i) + "\n");
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();// affiche l'erreur dans la console
		}

	}
}
