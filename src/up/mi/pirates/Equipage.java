package up.mi.pirates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Equipage {

	public class Relation {
		private Pirate p1, p2;
		private int cout;

		/**
		 * constructeur de la classe Relation
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
		 * Obtenir le cout de la relation
		 * 
		 * @return le cout de la relation;
		 */
		public int getCout() {
			return cout;
		}

		/**
		 * Obtenir les informations du 1er pirate de la relation
		 * 
		 * @return le 1er pirate de la relation
		 */
		public Pirate getP1() {
			return p1;
		}

		/**
		 * Obtenir les informations du 2nd pirate de la relation
		 * 
		 * @return le 2nd pirate de la relation
		 */
		public Pirate getP2() {
			return p2;
		}
	}

	/**
	 * Liste d'adjacence des pirates
	 */
	private ArrayList<Relation> relations;

	/**
	 * liste des objets
	 */
	private ArrayList<String> objets;

	/**
	 * HashMap des pirates selon leur noms
	 */
	private HashMap<String, Pirate> pirates;

	/**
	 * CrÃ©e un equipage d'une certaine taille avec certains noms pour les pirates
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
	 * CrÃ©e un equipage d'une certaine taille avec certains noms pour les pirates
	 * et les objets
	 * 
	 * @param noms   - les noms des pirates
	 * @param Objets - les noms des objets
	 */
	public Equipage(String[] noms, String[] objets) {
		if (noms.length > objets.length)
			throw new ArrayIndexOutOfBoundsException("il y a plus de pirates que d'objet");

		// initialise les variables
		this.relations = new ArrayList<>();
		this.pirates = new HashMap<>();
		this.objets = new ArrayList<>();

		// initialise les Pirates
		for (String nom : noms)
			this.pirates.put(nom, new Pirate(nom, objets.length));

		// initialise les Objets
		for (String objet : objets)
			this.objets.add(objet);
	}

	/**
	 * ajoute une relation avec un cout donnÃ©
	 * 
	 * @param p1   - le premier pirate
	 * @param p2   - le second pirate
	 * @param cout - le cout de la relation
	 */
	public void ajoutRelation(Pirate p1, Pirate p2, int cout) {
		relations.add(new Relation(p1, p2, cout));
	}

	/**
	 * ajoute une relation
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
	 * @return le cout total, -1 si le cout ne peut pas etre calculé (pirate sans
	 *         objet)
	 */
	public int coutTotal() {
		int res = 0;
		try {
			for (Relation r : relations) {
				if (r.getP1().prefere(r.getP2().getObjet()))
					res += r.getCout();

				if (r.getP2().prefere(r.getP1().getObjet()))
					res += r.getCout();
			}
			return res;
		} catch (NullPointerException e) {
			return -1;
		}

	}

	/**
	 * Assigne les objets avec un algorithme naÃ¯f
	 * 
	 */
	public void assignerObjets() {
		boolean[] disponibiliteObjet = new boolean[pirates.size()];

		// initialise le tableau de disponibilite
		for (int i = 0; i < disponibiliteObjet.length; i++)
			disponibiliteObjet[i] = true;

		// assigne les objets
		for (Pirate p : pirates.values()) {
			int i = 0;
			// prend la preference si elle est disponible
			while (!disponibiliteObjet[objets.indexOf(p.getPreferences(i))]) {
				i++;
			}
			p.donneLObjet(p.getPreferences(i));

			// rend l'objet indisponible
			disponibiliteObjet[objets.indexOf(p.getPreferences(i))] = false;
		}
	}

	/**
	 * Echanger les objets entre 2 pirates
	 * 
	 * @param p1 - le premier pirate
	 * @param p2 - le second pirate
	 */
	public void echange(Pirate p1, Pirate p2) {
		String tmp = p2.getObjet();
		p2.donneLObjet(p1.getObjet());
		p1.donneLObjet(tmp);
	}

	/**
	 * Obtenir les relations
	 * 
	 * @return un ArrrayList des relations
	 */
	public ArrayList<Relation> getRelations() {
		return relations;
	}

	/**
	 * Obtenir les pirates
	 * 
	 * @return une HashMap des Pirates
	 */
	public HashMap<String, Pirate> getPirates() {
		return pirates;
	}

	/**
	 * cherche le pirate avec le nom donnÃ© en parametre
	 * 
	 * @param nom - le nom recherchÃ©
	 * @return le pirate avec le nom donnÃ© en parametre
	 * @throws NoSuchElementException s'il n'existe aucun pirate avec se nom
	 */
	public Pirate getPirateParNom(String nom) throws NoSuchElementException {
		if (!pirates.containsKey(nom))
			throw new NoSuchElementException("Il n'existe aucun pirate nommÃ© " + nom);
		return pirates.get(nom);
	}

	/**
	 * 
	 * @return true si une solution est proposée (tout les pirates ont des objets)
	 */
	public boolean isResolu() {
		for (Pirate p : pirates.values())
			if (!p.hasObjet())
				return false;
		return true;
	}

}
