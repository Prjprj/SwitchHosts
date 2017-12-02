package test;

import application.Constantes;
import util.ParseurFichierConf;
import gui.FenetrePrincipale;

public class testFenetre {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (ParseurFichierConf.existanceFichier(Constantes.nomFichierTrad)) {
			FenetrePrincipale fenetre = new FenetrePrincipale(
					ParseurFichierConf.getProperty("Lb_Nom_Appli",
							Constantes.nomFichierTrad), 640, 480, "C:\\test");
			fenetre.rendreVisible();
		}
	}

}
