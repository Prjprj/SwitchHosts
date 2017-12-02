package manipulationFichiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.ParseurFichierConf;
import application.Constantes;

/**
 * Classe contenant les fonction de manipulation d'un fichier
 * 
 * @author Prj
 * @version 0.1
 */
public class Fichier {
	// Déclaration des attributs de la classe
	private String nomFichier;
	private String emplacementFichier;
	private File fic;

	// Création des Getters
	/**
	 * Méthode permettant de récupérer le nom du fichier
	 * 
	 * @return Le nom du fichier
	 */
	public String getNomFichier() {
		return nomFichier;
	}

	/**
	 * Méthode permettant de récupérer l'emplacement du fichier
	 * 
	 * @return L'emplacement du fichier
	 */
	public String getEmplacementFichier() {
		return emplacementFichier;
	}

	/**
	 * Méthode permettant de récupérer l'objet File représentant le fichier
	 * 
	 * @return Le pointeur sur le fichier
	 */
	private File getFic() {
		return this.fic;
	}

	// Création des Setters
	/**
	 * Méthode permettant de paramétrer le nom du fichier
	 * 
	 * @param nomFichier
	 *            Le nom du fichier
	 */
	private void setNomFichier(String nomFichier) {
		this.nomFichier = new String(nomFichier);
	}

	/**
	 * Méthode permettant de paramétrer l'emplacement du fichier sur le
	 * disque-dur
	 * 
	 * @param emplacementFichier
	 *            L'emplacement du fichier sur le disque-dur
	 */
	private void setEmplacementFichier(String emplacementFichier) {
		this.emplacementFichier = new String(emplacementFichier);
	}

	/**
	 * Méthode allouant un objet de type File pour stocker le fichier
	 * 
	 */
	private void setFichier() {
		this.fic = new File(this.getEmplacementFichier() + this.getNomFichier());
	}

	/**
	 * Constructeur de la classe pour ouvrir un fichier déjà existant en lecture
	 * 
	 * @param emplacementFichier
	 *            l'adresse du fichier
	 * @param nomFichier
	 *            le nom du fichier
	 */
	public Fichier(String emplacementFichier, String nomFichier) {
		if (emplacementFichier.endsWith("\\"))
			this.setEmplacementFichier(emplacementFichier);
		else
			this.setEmplacementFichier(emplacementFichier + "\\");
		this.setNomFichier(nomFichier);
		this.setFichier();

		if (!this.getFic().exists()) {
			this.creerFichier();
		}
	}

	/**
	 * Constructeur de la classe pour ouvrir un fichier en écriture (création ou
	 * suppression+ajout)
	 * 
	 * @param emplacementFichier
	 *            l'adresse du fichier
	 * @param nomFichier
	 *            le nom du fichier
	 * @param contenuFichier
	 *            le contenu qui doit être écrit dans le fichier
	 */
	public Fichier(String emplacementFichier, String nomFichier,
			String contenuFichier) {
		if (emplacementFichier.endsWith("\\"))
			this.setEmplacementFichier(emplacementFichier);
		else
			this.setEmplacementFichier(emplacementFichier + "\\");
		this.setNomFichier(nomFichier);
		this.setFichier();

		if (!this.getFic().exists()) {
			this.creerFichier();
		}
		this.ecrireFichier(contenuFichier);
	}

	/**
	 * Fonction permettant d'écrire dans un fichier
	 * 
	 * @param chaine
	 *            Chaine à écrire dans le fichier (en suppression+ajout)
	 */
	public void ecrireFichier(String chaine) {
		FileWriter sortie;
		try {
			sortie = new FileWriter(this.getFic());
			sortie.write(chaine);
			sortie.flush();
			sortie.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fonction permettant de créer un fichier sur le disque-dur
	 * 
	 */
	private void creerFichier() {
		try {
			this.getFic().createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fonction permettant d'afficher le contenu d'un fichier sur la sortie
	 * standard
	 * 
	 */
	public void afficheFichier() {
		BufferedReader brFichier;
		String ligne = "";
		try {
			brFichier = new BufferedReader(new FileReader(this.getFic()));
			if (brFichier == null) {
				throw new FileNotFoundException(ParseurFichierConf.getProperty(
						"Lb_Libelle_File_Not_Found_Exception",
						Constantes.nomFichierTrad)
						+ this.emplacementFichier + this.nomFichier);
			}
			do {
				ligne = brFichier.readLine();
				if (ligne != null) {
					System.out.println(ligne);
				}
			} while (ligne != null);
			brFichier.close();
			System.out.println();
			System.out.println("Affichage Terminé");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fonction permettant de renvoyer dans un String le contenu d'un fichier
	 * 
	 * @return le contenu du fichier
	 */
	public String getFichierChaine() {
		BufferedReader brFichier;
		String ligne = "";
		String retour = "";
		try {
			brFichier = new BufferedReader(new FileReader(this.getFic()));
			if (brFichier == null) {
				throw new FileNotFoundException(ParseurFichierConf.getProperty(
						"Lb_Libelle_File_Not_Found_Exception",
						Constantes.nomFichierTrad)
						+ this.emplacementFichier + this.nomFichier);
			}
			do {
				ligne = brFichier.readLine();
				if (ligne != null) {
					retour += ligne;
					retour += "\n";
				}
			} while (ligne != null);
			brFichier.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return retour;
	}

	/**
	 * Méthode permettant d'ajouter une ligne (ou plus) à un Fichier
	 * 
	 * @param chaine
	 *            La chaine à ajouter
	 */
	public void ajouterChaine(String chaine) {
		String fichier = this.getFichierChaine();

		fichier += "\n";
		fichier += chaine;

		this.ecrireFichier(fichier);
	}

	/**
	 * Méthode toString de la classe, fait appel à la classe
	 * retourneChaineFichier()
	 * 
	 * @return le contenu du fichier
	 */
	public String toString() {
		return this.getFichierChaine();
	}

	/**
	 * Méthode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier. Nécessite un argument du type Fichier.
	 * 
	 * @param fichier
	 *            le fichier à comparer
	 * @return true si les fichiers sont les mêmes, false sinon
	 */
	public boolean equals(Fichier fichier) {
		if (this.getEmplacementFichier()
				.equals(fichier.getEmplacementFichier())) {
			if (this.getNomFichier().equals(fichier.getNomFichier())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Méthode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier
	 * 
	 * @param emplacementFichier
	 *            l'emplacement du fichier à comparer
	 * @param nomFichier
	 *            le nom du fichier à comparer
	 * @return true si les fichiers sont les mêmes, false sinon
	 */
	public boolean equals(String emplacementFichier, String nomFichier) {
		if (this.getEmplacementFichier().equals(emplacementFichier)) {
			if (this.getNomFichier().equals(nomFichier)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Méthode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier
	 * 
	 * @param objet
	 *            le fichier à comparer
	 * @return true si les fichiers sont les mêmes, false sinon
	 */
	public boolean equals(Object objet) {
		return this.equals((Fichier) objet);
	}
}