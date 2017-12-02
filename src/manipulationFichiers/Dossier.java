package manipulationFichiers;

import java.io.File;
import java.util.Vector;

import util.DossierVideException;

/**
 * Classe contenant les fonction de manipulation d'un dossier
 * 
 * @author Prj
 * @version 0.1
 */
public class Dossier {
	// D�claration des attributs de la classe
	private String emplacement;
	private File dossier;
	private Vector<Fichier> listeFichiers;

	// Cr�ation des Getters
	/**
	 * Permet de r�cup�rer l'emplacement du dossier dans le disque-dur
	 * 
	 * @return L'emplacement du dossier
	 */
	public String getEmplacement() {
		return this.emplacement;
	}

	/**
	 * Permet de r�cup�rer l'objet File repr�sentant le Dossier
	 * 
	 * @return Le pointeur sur le dossier
	 */
	private File getDossier() {
		return this.dossier;
	}

	/**
	 * Permet de r�cup�rer la liste de Fichiers contenus dans le dossier
	 * 
	 * @return Le Vecteur de Fichiers
	 */
	public Vector<Fichier> getListeFichiers() {
		return this.listeFichiers;
	}

	// Cr�ation des Setters
	/**
	 * Permet de param�trer l'emplacement du dossier sur le disque-dur
	 * 
	 * @param emplacement
	 *            L'emplacement du dossier
	 */
	private void setEmplacement(String emplacement) {
		this.emplacement = new String(emplacement);
	}

	/**
	 * Permet de cr�er l'objet File pointant sur le dossier
	 * 
	 */
	private void setDossier() {
		this.dossier = new File(this.getEmplacement());
	}

	/**
	 * Permet d'initialiser la liste de Fichier avec les fichiers contenus dans
	 * le dossier sur le disque-dur
	 * 
	 */
	private void setListeFichiers() throws DossierVideException {
		this.listeFichiers = new Vector<Fichier>();
		try {
			remplissageVector();
		} catch (DossierVideException e) {
			throw new DossierVideException();
		}
	}

	/**
	 * Constructeur de la classe Dossier
	 * 
	 * @param emplacement
	 *            L'emplacement du dossier sur le disque-dur
	 */
	public Dossier(String emplacement) throws DossierVideException {
		if (emplacement.endsWith("\\"))
			this.setEmplacement(emplacement);
		else
			this.setEmplacement(emplacement + "\\");
		this.setDossier();
		try {
			this.setListeFichiers();
		} catch (DossierVideException e) {
			throw new DossierVideException();
		}
	}

	/**
	 * Constructeur de la classe Dossier
	 * 
	 * @param emplacement
	 *            L'emplacement du dossier sur le disque-dur
	 */
	public Dossier(String emplacement, String inutile) {
		if (emplacement.endsWith("\\"))
			this.setEmplacement(emplacement);
		else
			this.setEmplacement(emplacement + "\\");
		this.setDossier();
		try {
			this.setListeFichiers();
		} catch (DossierVideException e) {
			;
		}
	}

	/**
	 * Permet de remplir la liste de Fichier � partir des fichiers pr�sents sur
	 * le disque-dur
	 * 
	 */
	private void remplissageVector() throws DossierVideException {
		String[] chaineListeFichiers;
		int i;

		chaineListeFichiers = this.getDossier().list();
		if (chaineListeFichiers.length == 0) {
			throw new DossierVideException();
		} else {
			for (i = 0; i < chaineListeFichiers.length; i++) {
				this.ajoutFichier(chaineListeFichiers[i]);
			}
		}
	}

	/**
	 * Permet d'ajouter un fichier � la liste de fichiers
	 * 
	 * @param nom
	 *            Le nom du fichier � ajouter
	 */
	public void ajoutFichier(String nom) {
		this.getListeFichiers().add(new Fichier(this.getEmplacement(), nom));
	}

	/**
	 * Permet d'ajouter un fichier � la liste de fichiers avec un contenu
	 * 
	 * @param nom
	 *            Le nom du fichier � ajouter
	 * @param contenu
	 *            Le contenu � ajouter
	 */
	public void ajoutFichier(String nom, String contenu) {
		this.getListeFichiers().add(
				new Fichier(this.getEmplacement(), nom, contenu));
	}

	/**
	 * Permet de r�cup�rer le fichier stock� � l'emplacement donn� en argument
	 * dans la liste des fichiers
	 * 
	 * @param emplacement
	 *            L'emplacement (en nombre) du fichier dans la liste
	 * @return Le fichier demand�
	 */
	public Fichier getFichierIndex(int emplacement) {
		return this.getListeFichiers().get(emplacement);
	}

	/**
	 * Permet de savoir combien de fichiers existent dans la liste de Fichier de
	 * la classe
	 * 
	 * @return Le nombre de fichier pr�sents dans le dossier
	 */
	public int getNbFichiers() {
		return this.getListeFichiers().size();
	}

	/**
	 * Permet de r�cup�rer un fichier � partir de son nom
	 * 
	 * @param emplacementFichier
	 *            L'emplacement du Fichier
	 * @param nomFichier
	 *            Le nom du Fichier
	 * @return Le Fichier
	 */
	public Fichier getFichierParNom(String emplacementFichier, String nomFichier) {
		Fichier fichier = null;
		for (int i = 0; i < this.getNbFichiers(); i = i + 1) {
			if (this.getFichierIndex(i).equals(emplacementFichier, nomFichier)) {
				fichier = this.getFichierIndex(i);
			}
		}
		return fichier;
	}

	/**
	 * M�thode toString de la classe, liste les fichiers contenus dans le
	 * dossier
	 * 
	 * @return La liste des fichiers contenus dans le dossier
	 */
	public String toString() {
		String retour = new String("");
		for (int i = 0; i < this.getNbFichiers(); i = i + 1) {
			retour += this.getFichierIndex(i).getEmplacementFichier()
					+ this.getFichierIndex(i).getNomFichier();
			retour += "\n";
		}
		return retour;
	}

	/**
	 * M�thode equals de la classe, se base sur l'emplacement du dossier.
	 * N�cessite que l'objet soit un Dossier
	 * 
	 * @param dossier
	 *            le dossier � comparer
	 * @return true si les dossiers sont les m�mes, false sinon
	 */
	public boolean equals(Dossier dossier) {
		if (this.getEmplacement().equals(dossier.getEmplacement())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * M�thode equals de la classe, se base sur l'emplacement du dossier
	 * 
	 * @param objet
	 *            le dossier � comparer
	 * @return true si les dossiers sont les m�mes, false sinon
	 */
	public boolean equals(Object objet) {
		return this.equals((Dossier) objet);
	}
}
