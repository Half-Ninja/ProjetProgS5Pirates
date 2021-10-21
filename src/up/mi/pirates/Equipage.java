package up.mi.pirates;

import java.util.ArrayList;
import java.util.HashMap;

public class Equipage {
	// Les objets sont (pour l'instant) représenté par des int
	
	private class Relation {
		Pirate p1, p2;
		int cout;

		/**
		 * 
		 * @return cout - le cout de la relation;
		 */
		public int getCout() {
			return cout;
		}

		/**
		 * 
		 * @param p1 - le premier pirate
		 * @param p2 - le second pirate
		 * @return True si se sont les pirates
		 */
		public boolean relieTElle(Pirate p1, Pirate p2) {
			return (this.p1.equals(p1) && this.p2.equals(p2)) || 
					(this.p1.equals(p2) && this.p2.equals(p1));
		}

		public Pirate getP1() {
			return p1;
		}

		public Pirate getP2() {
			return p2;
		}
	}

	ArrayList<Relation> relations; // Liste d'adjacence des pirates
	HashMap<String, Pirate> pirates; // les pirates

	/**
	 * Crée un equipage d'une certaine taille avec des certains noms
	 * 
	 * @param noms      la liste de noms
	 * @param nbPirates le nombre de pirates
	 */
	public Equipage(String[] noms, int nbPirates) {
		// initialise les variables
		this.relations = new ArrayList<>();
		this.pirates = new HashMap<>();

		// initialise les Pirates
		for (String nom : noms)
			pirates.put(nom, new Pirate(nom, nbPirates));
	}
	
	//TODO Ajout relation
	//TODO calcul cout total

}
