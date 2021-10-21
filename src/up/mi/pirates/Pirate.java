package up.mi.pirates;

public class Pirate {
	int[] preferences;
	String nom;

	public Pirate(String nom, int nbObjets) {
		this.preferences = new int[nbObjets];
		this.nom = nom;
	}

}
