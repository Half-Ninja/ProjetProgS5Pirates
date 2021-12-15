package up.mi.pirates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppPart2 {
	private static final String noArgumentMsg = "Veuiller entre un fichier";
	private static final String fileNotFoundMsg = "fichier introuvable";

	private AppPart2() {
	}
	
	private static void montresListePirates(Equipage eq) {
		System.out.println("liste des pirates :");
		for (String nom : eq.getPirates().keySet()) {
			System.out.println(" - " + nom + " : " + eq.getPirateParNom(nom).getPreferencesString());
		}
	}
	
	private static void montresListePiratesObjets(Equipage eq) {
		System.out.println("liste des pirates et de leurs objets :");
		for (String nom : eq.getPirates().keySet()) 
			System.out.println(nom + ":" + eq.getPirateParNom(nom).getObjet());
	}

	public static void main(String[] args) {
		//verifie qu'un argument as bien été donné
		if (args.length > 0) {
			try {
				File fin = new File(args[0]);
				Scanner sc;
				
				Equipage titanic = UtilPirates.genererDepuisFichier(fin);
				
				montresListePirates(titanic);
				
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
