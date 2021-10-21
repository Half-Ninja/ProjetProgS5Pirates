package up.mi.pirates;

public class Equipage {
	Pirate[] pirates;
	int[][] relations;

	public Equipage(String[] noms, int nbPirates) {
		this.pirates = new Pirate[nbPirates];
		this.relations = new int[nbPirates][nbPirates];

	}

}
