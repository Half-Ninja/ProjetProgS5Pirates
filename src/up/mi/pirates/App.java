package up.mi.pirates;

import java.util.Scanner;

public class App {
	private static String menuPrincipal = "1 - Ajouter une relation\n" +
										  "2 - Ajouter une préférence\n" +
										  "3 - Fin";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choixInt = 0;
		String choixString = "";
		do {
			System.out.println(menuPrincipal);
			choixInt = sc.nextInt();
			switch(choixInt) {
			case 1:
				System.out.println("choix 1");
				break;
			case 2:
				System.out.println("choix 2");
				break;
			case 3:
				System.out.println("Sortie...");
				break;
			default:
				System.out.println("Entrée non reconnue");
			}
		}
		while (choixInt != 3);
	}

}
