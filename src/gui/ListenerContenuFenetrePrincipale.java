package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import application.Constantes;

import util.FiltreFichierTexte;
import util.ParseurFichierConf;

import manipulationFichiers.Fichier;

/**
 * Classe g�rant les �v�nements li�s au contenu de la Fen�tre principale
 * 
 * @author Prj
 * @version 0.1
 */
public class ListenerContenuFenetrePrincipale implements ActionListener {
	// D�claration des attributs de la classe
	private ContenuFenetrePrincipale contenuFenetrePrincipale;

	// Cr�ation des Getters
	/**
	 * M�thode permettant de r�cup�rer le ContenuFenetre ayant cr�� ce Listener
	 * 
	 * @return Le ContenuFenetre ayant cr�� ce Listener
	 */
	private ContenuFenetrePrincipale getContenuFenetre() {
		return contenuFenetrePrincipale;
	}

	// Cr�ation des Setters
	/**
	 * M�thode permettant d'initialiser le ContenuFenetre ayant cr�� ce Listener
	 * 
	 * @param contenuFenetrePrincipale
	 *            Le ContenuFenetre ayant cr�� ce Listener
	 */
	private void setContenuFenetre(
			ContenuFenetrePrincipale contenuFenetrePrincipale) {
		this.contenuFenetrePrincipale = contenuFenetrePrincipale;
	}

	/**
	 * Constructeur du Listener
	 * 
	 * @param contenuFenetrePrincipale
	 *            Le ContenuFenetre ayant cr�� ce Listener
	 */
	public ListenerContenuFenetrePrincipale(
			ContenuFenetrePrincipale contenuFenetrePrincipale) {
		super();
		this.setContenuFenetre(contenuFenetrePrincipale);
	}

	/**
	 * Gestion des actions du listener
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText().equals(
				ParseurFichierConf.getProperty("Lb_Bouton_Quitter",
						Constantes.nomFichierTrad))) {
			this.actionBoutonQuitter();
		}
		if (((JButton) e.getSource()).getText().equals(
				ParseurFichierConf.getProperty("Lb_Bouton_Valider",
						Constantes.nomFichierTrad))) {
			this.actionBoutonValider();
		}
		if (((JButton) e.getSource()).getText().equals(
				ParseurFichierConf.getProperty("Lb_Bouton_Selectionner",
						Constantes.nomFichierTrad))) {
			this.actionBoutonS�lectionner();
		}
		if (((JButton) e.getSource()).getText().equals(
				ParseurFichierConf.getProperty("Lb_Bouton_Editer",
						Constantes.nomFichierTrad))) {
			this.actionBoutonEditer();
		}
		if (((JButton) e.getSource()).getText().equals(
				ParseurFichierConf.getProperty("Lb_Bouton_Changer",
						Constantes.nomFichierTrad))) {
			this.actionBoutonChanger();
		}
	}

	/**
	 * M�thode contenant l'action associ�e au bouton "Quitter"
	 */
	private void actionBoutonQuitter() {
		System.exit(0);
	}

	/**
	 * M�thode contenant l'action associ�e au bouton "Valider"
	 */
	private void actionBoutonValider() {
		// Affichage d'une ligne d'information pour l'utilisateur
		this.getContenuFenetre().getLabel().setText(
				ParseurFichierConf.getProperty("Lb_Label_Clic_Valider_1",
						Constantes.nomFichierTrad)
						+ this.getContenuFenetre().getListeFichiers()
								.getSelectedItem().toString()
						+ " "
						+ ParseurFichierConf.getProperty(
								"Lb_Label_Clic_Valider_2",
								Constantes.nomFichierTrad));
		this.getContenuFenetre().getLabel().setVisible(true);
		this.getContenuFenetre().getLabel().setForeground(Color.GREEN);
		// RAF
		System.out.println("TODO");
	}

	/**
	 * M�thode contenant l'action associ�e au bouton "S�lectionner"
	 */
	private void actionBoutonS�lectionner() {
		// Affichage du contenu du fichier s�lectionn� dans l'aire de texte
		this.getContenuFenetre().getAffichageContenuFichier().setText(
				this.getContenuFenetre().getDossier().getFichierParNom(
						this.getContenuFenetre().getDossier().getEmplacement(),
						this.getContenuFenetre().getListeFichiers()
								.getSelectedItem().toString())
						.getFichierChaine());
	}

	/**
	 * M�thode contenant l'action associ�e au bouton "Changer"
	 */
	private void actionBoutonChanger() {
		// Param�trage d'une fen�tre de Parcours du File System
		JFileChooser fenetreParcourir = new JFileChooser();
		fenetreParcourir.setAcceptAllFileFilterUsed(false);
		FileFilter fileFilter = new FiltreFichierTexte();
		fenetreParcourir.setFileFilter(fileFilter);
		fenetreParcourir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fenetreParcourir.setApproveButtonText(ParseurFichierConf.getProperty(
				"Lb_Nom_Fenetre_Choix_Fichier_Changer",
				Constantes.nomFichierTrad));
		// Affichage d'une fen�tre de choix pour r�cup�rer un fichier dans le
		// dossier manipul�
		if (fenetreParcourir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// Ouverture du fichier s�lectionn� par l'utilisateur
			Fichier fichier = new Fichier(fenetreParcourir.getSelectedFile()
					.getParent(), fenetreParcourir.getSelectedFile().getName());
			// Cr�ation du fichier dans le dossier g�r� par l'application
			this.getContenuFenetre().getDossier().ajoutFichier(
					fichier.getNomFichier(), fichier.getFichierChaine());
			// Suppression des limitations dues au fait que le dossier pourrait
			// �tre vide
			this.getContenuFenetre().getLabel().setText("");
			this.getContenuFenetre().getLabel().setForeground(Color.BLACK);
			this.getContenuFenetre().getLabel().setVisible(false);
			this.getContenuFenetre().getBoutonSelectionner().setEnabled(true);
			this.getContenuFenetre().getBoutonEditer().setEnabled(true);
			this.getContenuFenetre().getBoutonValider().setEnabled(true);
			// Masquage et re-cr�ation de la ComboBox d'affichage des fichiers
			this.getContenuFenetre().getSpLayout().removeLayoutComponent(
					this.getContenuFenetre().getListeFichiers());
			this.getContenuFenetre().getListeFichiers().setVisible(false);
			this.getContenuFenetre().remplissageComboBox(
					this.getContenuFenetre().getDossier().getEmplacement());
			this.getContenuFenetre().placementObjets();
		}
	}

	/**
	 * M�thode contenant l'action associ�e au bouton "Editer"
	 */
	private void actionBoutonEditer() {
		// RAF
		System.out.println("TODO");
	}
}
