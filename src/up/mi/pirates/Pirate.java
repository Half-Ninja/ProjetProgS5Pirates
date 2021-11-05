package up.mi.pirates;

public class Pirate {
	// Les objets sont (pour l'instant) représenté par des int

	int[] preferences;
	String nom;
	int objet;

	/**
	 * Crée un Pirate avec un nom et un certain nombre de préférence
	 * 
	 * @param nom      le nom du pirate
	 * @param nbObjets le nombre d'objets
	 */
	public Pirate(String nom, int nbObjets) {
		this.preferences = new int[nbObjets];
		this.nom = nom;
	}

	public boolean equals(Pirate p) {
		return p.nom == nom;
	}

	/**
	 * Donne un objet au pirate
	 * 
	 * @param objets l'objet a donner
	 */
	public void donneLObjet(int objet) {
		this.objet = objet;
	}

	/**
	 * verifie si le pirate préfere l'objet objet
	 * 
	 * @param objet l'objet a verifier
	 */
	public boolean prefere(int objet) {
		if(objet >= preferences.length)
			throw new ArrayIndexOutOfBoundsException("Objet " + objet + " non compris dans les préférences du pirate " + nom);
		for (int n : preferences) {
			if (n == objet)
				return true;
			if (n == this.objet)
				return false;
		}
		return false;
	}

	public int getPreferences(int index) {
		return preferences[index];
	}

	public int[] getPreferences() {
		return preferences;
	}

	public String getPreferencesString() {
		String res = "";
		for(int n : preferences) {
			res += ", " + Integer.toString(n);
		}
		return "[" + res.substring(1) + "]";
	}

	public String getNom() {
		return nom;
	}

	public int getObjet() {
		return objet;
	}

	public void setPreferences(int[] preferences) {
		this.preferences = preferences;
	}

}
