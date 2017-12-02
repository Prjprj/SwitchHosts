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
	// D�claration des attributs de la classe
	private String nomFichier;
	private String emplacementFichier;
	private File fic;

	// Cr�ation des Getters
	/**
	 * M�thode permettant de r�cup�rer le nom du fichier
	 * 
	 * @return Le nom du fichier
	 */
	public String getNomFichier() {
		return nomFichier;
	}

	/**
	 * M�thode permettant de r�cup�rer l'emplacement du fichier
	 * 
	 * @return L'emplacement du fichier
	 */
	public String getEmplacementFichier() {
		return emplacementFichier;
	}

	/**
	 * M�thode permettant de r�cup�rer l'objet File repr�sentant le fichier
	 * 
	 * @return Le pointeur sur le fichier
	 */
	private File getFic() {
		return this.fic;
	}

	// Cr�ation des Setters
	/**
	 * M�thode permettant de param�trer le nom du fichier
	 * 
	 * @param nomFichier
	 *            Le nom du fichier
	 */
	private void setNomFichier(String nomFichier) {
		this.nomFichier = new String(nomFichier);
	}

	/**
	 * M�thode permettant de param�trer l'emplacement du fichier sur le
	 * disque-dur
	 * 
	 * @param emplacementFichier
	 *            L'emplacement du fichier sur le disque-dur
	 */
	private void setEmplacementFichier(String emplacementFichier) {
		this.emplacementFichier = new String(emplacementFichier);
	}

	/**
	 * M�thode allouant un objet de type File pour stocker le fichier
	 * 
	 */
	private void setFichier() {
		this.fic = new File(this.getEmplacementFichier() + this.getNomFichier());
	}

	/**
	 * Constructeur de la classe pour ouvrir un fichier d�j� existant en lecture
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
	 * Constructeur de la classe pour ouvrir un fichier en �criture (cr�ation ou
	 * suppression+ajout)
	 * 
	 * @param emplacementFichier
	 *            l'adresse du fichier
	 * @param nomFichier
	 *            le nom du fichier
	 * @param contenuFichier
	 *            le contenu qui doit �tre �crit dans le fichier
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
	 * Fonction permettant d'�crire dans un fichier
	 * 
	 * @param chaine
	 *            Chaine � �crire dans le fichier (en suppression+ajout)
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
	 * Fonction permettant de cr�er un fichier sur le disque-dur
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
			System.out.println("Affichage Termin�");
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
	 * M�thode permettant d'ajouter une ligne (ou plus) � un Fichier
	 * 
	 * @param chaine
	 *            La chaine � ajouter
	 */
	public void ajouterChaine(String chaine) {
		String fichier = this.getFichierChaine();

		fichier += "\n";
		fichier += chaine;

		this.ecrireFichier(fichier);
	}

	/**
	 * M�thode toString de la classe, fait appel � la classe
	 * retourneChaineFichier()
	 * 
	 * @return le contenu du fichier
	 */
	public String toString() {
		return this.getFichierChaine();
	}

	/**
	 * M�thode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier. N�cessite un argument du type Fichier.
	 * 
	 * @param fichier
	 *            le fichier � comparer
	 * @return true si les fichiers sont les m�mes, false sinon
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
	 * M�thode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier
	 * 
	 * @param emplacementFichier
	 *            l'emplacement du fichier � comparer
	 * @param nomFichier
	 *            le nom du fichier � comparer
	 * @return true si les fichiers sont les m�mes, false sinon
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
	 * M�thode equals de la classe, se base sur l'emplacement et le nom du
	 * fichier
	 * 
	 * @param objet
	 *            le fichier � comparer
	 * @return true si les fichiers sont les m�mes, false sinon
	 */
	public boolean equals(Object objet) {
		return this.equals((Fichier) objet);
	}
}