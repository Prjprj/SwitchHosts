package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import application.Constantes;

/**
 * Classe permettant de r�cup�rer des informations dans un fichier de
 * configuration
 * 
 * @author Prj
 * @version 0.1
 */
public class ParseurFichierConf {
	private static Properties P = null;

	/**
	 * M�thode permettant de r�cup�rer la valeur d'un objet Property � partir
	 * d'un fichier de configuration standard (Property=Valeur)
	 * 
	 * @param Item
	 *            Nom de la Property � r�cup�rer
	 * @param nomFichier
	 *            Le nom du fichier dans lequel on recherche les properties
	 * @return La valeur de la Property pass�e en argument
	 */
	public static String getProperty(String Item, String nomFichier) {
		try {
			// Initialisation de la liste des Properties contenues dans le
			// fichier de configuration
			P = new Properties();
			// Ouverture du fichier de configuration
			FileInputStream in = new FileInputStream(nomFichier);
			// Lecture de la liste des Properties contenues dans le fichier de
			// configuration
			P.load(in);
			// Fermeture du fichier de configuration
			in.close();
		} catch (Exception e) {
			System.out
					.println(ParseurFichierConf.getProperty(
							"Lb_Libelle_File_Not_Found_Exception",
							Constantes.nomFichierTrad)+nomFichier);
			return null;
		}
		// R�cup�ration de la Property dans la liste des Properties contenues
		// dans le fichier de configuration
		String S = P.getProperty(Item);
		if (S == null)
			// La Property n'est pas trouv�e
			return null;
		else
			// On renvoie le r�sultat
			return (S.trim());
	}

	/**
	 * Cette m�thode permet de retourner la valeur True si le fichier de
	 * configuration existe.
	 * 
	 * @param nomFichier
	 *            Le nom du fichier dans lequel on recherche les properties
	 * @return True si le fichier existe, False sinon
	 */
	public static boolean existanceFichier(String nomFichier) {
		File f = new File(nomFichier);
		return f.exists();
	}

}
