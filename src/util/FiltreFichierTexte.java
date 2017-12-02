package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import application.Constantes;

/**
 * Classe permettant le filtrage dans la fenetre de recherche d'un fichier
 * 
 * @author Prj
 * @version 0.1
 */
public class FiltreFichierTexte extends FileFilter {

	/**
	 * M�thode autorisant les dossiers, fichiers .txt et le fichier hosts
	 * 
	 * @return true si le fichier est accept�, false sinon
	 */
	@Override
	public boolean accept(File f) {
		boolean retour = false;
		retour = f.getName().toLowerCase().endsWith(".txt");
		if (f.isDirectory())
			retour = true;
		if (f.getName().toLowerCase().equals("hosts"))
			retour = true;
		return retour;
	}

	/**
	 * M�thode affichant le nom du filtre dans la Fenetre
	 * 
	 * @return Le nom du Filtre
	 */
	@Override
	public String getDescription() {
		return ParseurFichierConf.getProperty(
				"Lb_Libelle_Filtre_Fenetre_Choix_Fichier_Changer",
				Constantes.nomFichierTrad);
	}

}
