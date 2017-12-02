package test;

import util.DossierVideException;
import manipulationFichiers.Fichier;
import manipulationFichiers.Dossier;

public class testFichier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fichier fichier = new Fichier("C:\\WINDOWS\\system32\\drivers\\etc\\",
				"hosts");
		fichier.afficheFichier();
		Fichier fichier2 = new Fichier("C:\\", "test.txt", fichier
				.getFichierChaine());
		System.out.println(fichier2);

		Dossier dossier = null;
		try {
			dossier = new Dossier("C:\\WINDOWS\\system32\\drivers\\etc\\");
		} catch (DossierVideException e) {
			e.printStackTrace();
		}

		System.out.println(dossier);
		System.out.println(fichier.equals(dossier.getFichierIndex(0)));

		fichier2.ajouterChaine("Test");
		System.out.println(fichier2);
	}
}