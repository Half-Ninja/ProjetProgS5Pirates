package up.mi.pirates;

import java.util.Scanner;

public class App {
	private static String menuPrincipal = "1 - Ajouter une relation\n" + "2 - Ajouter une préférence\n" + "3 - Fin";
	private static String menuSecondaire = "1 - Echanger objets\n" + "2 - Afficher coût\n" + "3 - Fin";
	
	private static void montresListePirates(Equipage eq) {
		System.out.println("liste des pirates :");
		for (String nom : eq.getPirates().keySet()) {
			System.out.println(" - " + nom + " : " + eq.getPirates().get(nom).getPreferencesString());
		}
	}
	
	private static void montresListePiratesObjets(Equipage eq) {
		System.out.println("liste des pirates et de leurs objets :");
		for (String nom : eq.getPirates().keySet()) 
			System.out.println(nom + ":o" + Integer.toString(eq.getPirates().get(nom).getObjet()));
	}
	

	private static void ajoutRelations(Scanner sc, Equipage eq) {
		String choix1, choix2;
		montresListePirates(eq);

		// lis le 1er nom
		do {
			System.out.print("Premier pirate : ");
			choix1 = sc.next();
		} while (!eq.getPirates().containsKey(choix1));

		// lis le second nom
		do {
			System.out.print("Second pirate  : ");
			choix2 = sc.next();
		} while (!eq.getPirates().containsKey(choix2));

		eq.ajoutRelation(eq.getPirates().get(choix1), eq.getPirates().get(choix2));
	}

	private static void ajoutPreference(Scanner sc, Equipage eq, int nombre) {
		String nom;
		int[] preference = new int[nombre];
		boolean check;

		// initialise les préférence a -1
		for (int i = 0; i < nombre; i++)
			preference[i] = -1;

		montresListePirates(eq);

		do {
			// lire le nom
			System.out.println("Préférences : ");
			nom = sc.next();

			check = !eq.getPirates().containsKey(nom);

			if (!check)
				// lire les préférences dans la chaine
				for (int i = 0; i < nombre; i++) {
					preference[i] = sc.nextInt();
					if (preference[i] > nombre || preference[i] < 0) {
						check = true;
						System.out.println("le nombre " + Integer.toString(preference[i])
								+ " n'est pas un objet valide, veuiller recommencer");
						if (i < nombre - 1)
							sc.nextLine();
						i = nombre;

					}
				}
			else {
				System.out.println("le nom \"" + nom + "\" n'est pas un pirate valide, veuiller recommencer");
				sc.nextLine();
			}
		} while (check);
		
		eq.getPirates().get(nom).setPreferences(preference);
	}
	
	/**
	 * pour echanger les objet de pirate
	 * 
	 */
	private static void echangeObjets(Scanner sc, Equipage eq) {
		 
		String nom1, nom2;
		
		// lis le 1er nom
		do {
			System.out.print("Premier pirate : ");
			nom1 = sc.next();
		} while (!eq.getPirates().containsKey(nom1));

		// lis le second nom
		do {
			System.out.print("Second pirate  : ");
			nom2 = sc.next();
		} while (!eq.getPirates().containsKey(nom2));		
		
		eq.echange(eq.getPirates().get(nom1),eq.getPirates().get(nom2));
		
		montresListePiratesObjets(eq);
		System.out.println();
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choixInt = 0;

		int NB_PIRATES;
		System.out.print("nombre de pirates : ");
		NB_PIRATES = sc.nextInt();
		
		System.out.println("\n");

		String[] noms = UtilPirates.genererNoms(NB_PIRATES);

		Equipage blackPearl = new Equipage(noms, NB_PIRATES);

		// menu principal
		do {
			System.out.println(menuPrincipal);
			choixInt = sc.nextInt();
			switch (choixInt) {
			case 1:
				System.out.println("\nAjouter une relation");
				ajoutRelations(sc, blackPearl);
				break;
			case 2:
				System.out.println("\nAjouter une préférence");
				ajoutPreference(sc, blackPearl, NB_PIRATES);
				break;
			case 3: // sort du système
				System.out.println("Passage au menu suivant ... \n");
				break;
			default:
				System.out.println("Entrée non reconnue");
			}
		} while (choixInt != 3);
		
		montresListePirates(blackPearl);
		blackPearl.assignerObjets();
		
		System.out.println("\n");
		//second menu
		do {
			System.out.println(menuSecondaire);
			choixInt = sc.nextInt();
			switch (choixInt) {
			case 1:
				System.out.println("\nEchanger objets");
				echangeObjets(sc, blackPearl);
				break;
			case 2:
				montresListePiratesObjets(blackPearl);
				System.out.println("Coût total : " + Integer.toString(blackPearl.coutTotal()) + "\n");
				break;
			case 3: // sort du système
				System.out.println("Sortie ... ");
				break;
			default:
				System.out.println("Entrée non reconnue\n");
			}
		} while (choixInt != 3);
		
		
		montresListePirates(blackPearl);
		montresListePiratesObjets(blackPearl);
		
		
		sc.close();
	}

}
