package up.mi.pirates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppPart2 {
	/**
	 * les messages erreurs
	 */
	private static final String noArgumentMsg = "Veuiller entre un fichier";
	private static final String fileNotFoundMsg = "fichier introuvable";

	private AppPart2() {
	}
	
	/**
	 * Afficher la liste des pirates avec leur preferences
	 * 
	 * @param eq une equipage
	 */
	private static void montresListePirates(Equipage eq) {
		System.out.println("liste des pirates :");
		for (String nom : eq.getPirates().keySet()) {
			System.out.println(" - " + nom + " : " + eq.getPirateParNom(nom).getPreferencesString());
		}
	}
	
	/**
	 * Afficher la liste des pirates avec leur objets obtenu
	 * 
	 * @param eq une equipage
	 */
	private static void montresListePiratesObjets(Equipage eq) {
		System.out.println("liste des pirates et de leurs objets :");
		for (String nom : eq.getPirates().keySet()) 
			System.out.println(nom + ":" + eq.getPirateParNom(nom).getObjet());
	}
	
	/**
	 * Pour echanger les objet de pirate
	 * 
	 * @param sc	lecture de données sur l’entrée au clavier
	 * @param eq	une équipage
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
	


		
	
	
	
	
	/**
	 * menu
	 */
	private static String menuFichier = "1 - Résolution automatique\n" + "2 - Résolution manuelle\n" +"3 - Sauvegarde\n"+ "4 - Fin";
	private static String menuSecondaire = "1 - Echanger objets\n" + "2 - Afficher coût\n" + "3 - Fin";

	
	
	public static void main(String[] args) {
		
		//verifie qu'un argument a bien été donné
		if (args.length > 0) {
			try {
				File fin = new File(args[0]);
				Scanner sc = new Scanner(System.in);
				int choixInt = 0;
				
				Equipage titanic = UtilPirates.genererDepuisFichier(fin);
				
				montresListePirates(titanic);
				
				// menu 
				do {
					System.out.println(menuFichier);
					choixInt = sc.nextInt();
					switch (choixInt) {
					case 1:
						System.out.println("\nRésolution automatique");
						
						System.out.println("Coût = " + Integer.toString(titanic.coutTotal()) + "\n");				
						break;
					case 2:
						do {
							System.out.println(menuSecondaire);
							choixInt = sc.nextInt();
							switch (choixInt) {
							case 1:
								System.out.println("\nEchanger objets");
								echangeObjets(sc, titanic);
								break;
							case 2:
								montresListePiratesObjets(titanic);
								System.out.println("Coût total : " + Integer.toString(titanic.coutTotal()) + "\n");
								break;
							case 3: // sort du système
								System.out.println("Sortie ... ");
								
								break;
							default:
								System.out.println("Entrée non reconnue\n");
							}
						} while (choixInt != 3);
						break;
					case 3: 
						System.out.println("sauvegarde");
						titanic.sauvegardeFichier();
						break;	
					case 4: // sort du système
						System.out.println("Sortie ... ");
						break;
					default:
						System.out.println("Entrée non reconnue\n");
					}
				} while (choixInt != 4);
				
				
			}
			catch(FileNotFoundException e) {
				System.out.println(fileNotFoundMsg);
				e.printStackTrace();
			}
		} else {
			System.out.println(noArgumentMsg);
		}

	}

}
