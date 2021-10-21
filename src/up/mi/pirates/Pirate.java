package up.mi.pirates;

public class Pirate {
	// Les objets sont (pour l'instant) représenté par des int
	
	int[] preferences;
	String nom;

	/**
	 * Crée un Pirate avec un nom et un certain nombre de préférence
	 * 
	 * @param	nom 		le nom du pirate
	 * @param	nbObjets 	le nombre d'objets 
	 */
	public Pirate(String nom, int nbObjets) {
		this.preferences = new int[nbObjets];
		this.nom = nom;
	}
	
	public boolean equals(Pirate p) {
		return p.nom == nom;
	}

}
