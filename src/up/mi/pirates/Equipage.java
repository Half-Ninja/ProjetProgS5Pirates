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
		 * @param p1   - le premier pirate
		 * @param p2   - le second pirate
		 * @param cout - le cout
		 */
		public Relation(Pirate p1, Pirate p2, int cout) {
			this.p1 = p1;
			this.p2 = p2;
			this.cout = cout;
		}

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
			return (this.p1.equals(p1) && this.p2.equals(p2)) || (this.p1.equals(p2) && this.p2.equals(p1));
		}

		public Pirate getP1() {
			return p1;
		}

		public Pirate getP2() {
			return p2;
		}
	}

	private ArrayList<Relation> relations; // Liste d'adjacence des pirates
	private HashMap<String, Pirate> pirates; // les pirates

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

	/**
	 * 
	 * @param p1   - le premier pirate
	 * @param p2   - le second pirate
	 * @param cout - le cout de la relation;
	 */
	public void ajoutRelation(Pirate p1, Pirate p2, int cout) {
		relations.add(new Relation(p1, p2, cout));
	}

	/**
	 * 
	 * @param p1 - le premier pirate
	 * @param p2 - le second pirate
	 */
	public void ajoutRelation(Pirate p1, Pirate p2) {
		ajoutRelation(p1, p2, 1);
	}

	/**
	 * Calcule le cout total
	 * 
	 * @return le cout total
	 */
	public int coutTotal() {
		int res = 0;

		for (Relation r : relations) {
			if (r.getP1().prefere(r.getP2().getObjet()))
				res += r.getCout();

			if (r.getP2().prefere(r.getP1().getObjet()))
				res += r.getCout();
		}

		return res;
	}
	
	/**
	 * Assigne les objets
	 * 
	 */
	public void assignerObjets() {
		boolean[] disponibiliteObjet = new boolean[pirates.size()];
		
		// initialise le tableau de disponibilite
		for(int i = 0; i < disponibiliteObjet.length; i++)
			disponibiliteObjet[i] = true;
		
		//assigne les objets
		for(Pirate p : pirates.values()){
			int i = 0;
			//prend la preference si elle est disponible
			while (!disponibiliteObjet[p.getPreferences(i)]) {
				i++;
			}
			p.donneLObjet(p.getPreferences(i));
			
			//rend l'objet indisponible
			disponibiliteObjet[p.getPreferences(i)] = false;
		}
	}
	
	/**
	 * echange les objets entre 2 pirates
	 * 
	 * @param p1 - le premier pirate
	 * @param p2 - le second pirate
	 */
	public void echange(Pirate p1,Pirate p2) { 
		int tmp = p2.getObjet();
		p2.donneLObjet(p1.getObjet());
		p1.donneLObjet(tmp);
	}

	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public HashMap<String, Pirate> getPirates() {
		return pirates;
	}
	


}
