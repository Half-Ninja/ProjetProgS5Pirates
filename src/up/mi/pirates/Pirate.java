package up.mi.pirates;

public class Pirate {
	// Les objets sont (pour l'instant) représenté par des int

	private String[] preferences;
	private String nom;
	private String objet;

	/**
	 * Crée un Pirate avec un nom et un certain nombre de préférence
	 * 
	 * @param nom      le nom du pirate
	 * @param nbObjets le nombre d'objets
	 */
	public Pirate(String nom, int nbObjets) {
		this.preferences = new String[nbObjets];
		for (int i = 0 ; i < nbObjets ; i++)
			this.preferences[i] = "";
		this.nom = nom;
		this.objet = null;
	}

	/**
	 * 
	 * @param p Pirate avec lequel evaluer
	 * @return True si les deux pirates sont les mêmes
	 */
	public boolean equals(Pirate p) {
		return p.nom == nom;
	}

	/**
	 * Donne un objet au pirate
	 * 
	 * @param objets l'objet a donner
	 */
	public void donneLObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * verifie si le pirate préfere l'objet objet
	 * 
	 * @param objet l'objet a verifier
	 */
	public boolean prefere(String objet) {
		for (String n : preferences) {
			if (n.equals(objet))
				return true;
			if (n == this.objet)
				return false;
		}
		return false;
	}

	/**
	 * 
	 * @param index l'index du pirate
	 * @return	la préférence du pirate
	 */
	public String getPreferences(int index) {
		return preferences[index];
	}

	/**
	 * 
	 * @param index l'index du pirate
	 * @return	les préférences du pirate
	 */
	public String[] getPreferences() {
		return preferences;
	}


	/**
	 * 
	 * @param index l'index du pirate
	 * @return	les préférences du pirate sous forme de String
	 */
	public String getPreferencesString() {
		String res = "";
		for(String n : preferences) {
			res += ", " + n;
		}
		return "(" + res.substring(1) + ")";
	}

	/**
	 * 
	 * @return le nom du pirate
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return l'objet actuellement tenu par le pirate
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * 
	 * @return true si le pirate possede un objet
	 */
	public boolean hasObjet() {
		return objet != null;
	}

	/**
	 * 
	 * @param preferences les preferences a donner au pirate
	 */
	public void setPreferences(String[] preferences) {
		this.preferences = preferences;
	}

}
