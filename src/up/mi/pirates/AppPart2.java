package up.mi.pirates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppPart2 {
	/**
	 * menu
	 */
	private static String menuFichier = "1 - Resolution automatique\n" + "2 - Resolution manuelle\n"
			+ "3 - Sauvegarde\n" + "4 - Fin";
	/**
	 * menu
	 */
	private static String menuSecondaire = "1 - Echanger objets\n" + "2 - Afficher cout\n" + "3 - Fin";

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
	 * @param sc lecture de donnees sur l’entree au clavier
	 * @param eq une equipage
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

		eq.echange(eq.getPirates().get(nom1), eq.getPirates().get(nom2));

		montresListePiratesObjets(eq);
		System.out.println();
	}

	public static void main(String[] args) {

		// verifie qu'un argument a bien ete donne
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
					try {
						choixInt = sc.nextInt();
					}catch(InputMismatchException e) {
						choixInt = 0;
						sc.next();
					}
					
					switch (choixInt) {
					case 1: // Resolution automatique
						System.out.println("\nResolution automatique");
						UtilPirates.resolutionNaive(titanic, titanic.getRelations().size() * 4);
						System.out.println("Cout = " + Integer.toString(titanic.coutTotal()) + "\n");
						break;
						
					case 2: // Resolution manuelle
						titanic.assignerObjets();
						
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
								System.out.println("Cout total : " + Integer.toString(titanic.coutTotal()) + "\n");
								break;
								
							case 3: // sort du système
								System.out.println("Sortie ... ");
								break;
								
							default:
								System.out.println("Entree non reconnue\n");
							}
						} while (choixInt != 3);
						break;
						
					case 3: // Sauvegarde
						//si l'equipage est resolu
						if (titanic.isResolu()) { 
							System.out.println("Veuillez indiquer le nom du fichier :");
							String outFile = sc.next();
							System.out.println("sauvegarde");
							UtilPirates.sauvegardeFichier(titanic, outFile);
						} else
							System.out.println("Veuiller resoudre le probleme avant de sauvegarder la solution");
						break;
						
					case 4: // sort du systeme
						System.out.println("Sortie ... ");
						break;
						
					default:
						System.out.println("Entree non reconnue\n");
					}
				} while (choixInt != 4);

			} catch (FileNotFoundException e) {
				System.out.println("fichier introuvable");
				e.printStackTrace();
			}
		} else {
			System.out.println("Veuiller entre un fichier en argument");
		}

	}

}
