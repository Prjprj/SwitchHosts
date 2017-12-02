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
 * Classe gérant les évènements liés au contenu de la Fenêtre principale
 * 
 * @author Prj
 * @version 0.1
 */
public class ListenerContenuFenetrePrincipale implements ActionListener {
	// Déclaration des attributs de la classe
	private ContenuFenetrePrincipale contenuFenetrePrincipale;

	// Création des Getters
	/**
	 * Méthode permettant de récupérer le ContenuFenetre ayant créé ce Listener
	 * 
	 * @return Le ContenuFenetre ayant créé ce Listener
	 */
	private ContenuFenetrePrincipale getContenuFenetre() {
		return contenuFenetrePrincipale;
	}

	// Création des Setters
	/**
	 * Méthode permettant d'initialiser le ContenuFenetre ayant créé ce Listener
	 * 
	 * @param contenuFenetrePrincipale
	 *            Le ContenuFenetre ayant créé ce Listener
	 */
	private void setContenuFenetre(
			ContenuFenetrePrincipale contenuFenetrePrincipale) {
		this.contenuFenetrePrincipale = contenuFenetrePrincipale;
	}

	/**
	 * Constructeur du Listener
	 * 
	 * @param contenuFenetrePrincipale
	 *            Le ContenuFenetre ayant créé ce Listener
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
	 *            L'évènement traité
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
			this.actionBoutonSélectionner();
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
	 * Méthode contenant l'action associée au bouton "Quitter"
	 */
	private void actionBoutonQuitter() {
		System.exit(0);
	}

	/**
	 * Méthode contenant l'action associée au bouton "Valider"
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
	 * Méthode contenant l'action associée au bouton "Sélectionner"
	 */
	private void actionBoutonSélectionner() {
		// Affichage du contenu du fichier sélectionné dans l'aire de texte
		this.getContenuFenetre().getAffichageContenuFichier().setText(
				this.getContenuFenetre().getDossier().getFichierParNom(
						this.getContenuFenetre().getDossier().getEmplacement(),
						this.getContenuFenetre().getListeFichiers()
								.getSelectedItem().toString())
						.getFichierChaine());
	}

	/**
	 * Méthode contenant l'action associée au bouton "Changer"
	 */
	private void actionBoutonChanger() {
		// Paramétrage d'une fenêtre de Parcours du File System
		JFileChooser fenetreParcourir = new JFileChooser();
		fenetreParcourir.setAcceptAllFileFilterUsed(false);
		FileFilter fileFilter = new FiltreFichierTexte();
		fenetreParcourir.setFileFilter(fileFilter);
		fenetreParcourir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fenetreParcourir.setApproveButtonText(ParseurFichierConf.getProperty(
				"Lb_Nom_Fenetre_Choix_Fichier_Changer",
				Constantes.nomFichierTrad));
		// Affichage d'une fenêtre de choix pour récupérer un fichier dans le
		// dossier manipulé
		if (fenetreParcourir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// Ouverture du fichier sélectionné par l'utilisateur
			Fichier fichier = new Fichier(fenetreParcourir.getSelectedFile()
					.getParent(), fenetreParcourir.getSelectedFile().getName());
			// Création du fichier dans le dossier géré par l'application
			this.getContenuFenetre().getDossier().ajoutFichier(
					fichier.getNomFichier(), fichier.getFichierChaine());
			// Suppression des limitations dues au fait que le dossier pourrait
			// être vide
			this.getContenuFenetre().getLabel().setText("");
			this.getContenuFenetre().getLabel().setForeground(Color.BLACK);
			this.getContenuFenetre().getLabel().setVisible(false);
			this.getContenuFenetre().getBoutonSelectionner().setEnabled(true);
			this.getContenuFenetre().getBoutonEditer().setEnabled(true);
			this.getContenuFenetre().getBoutonValider().setEnabled(true);
			// Masquage et re-création de la ComboBox d'affichage des fichiers
			this.getContenuFenetre().getSpLayout().removeLayoutComponent(
					this.getContenuFenetre().getListeFichiers());
			this.getContenuFenetre().getListeFichiers().setVisible(false);
			this.getContenuFenetre().remplissageComboBox(
					this.getContenuFenetre().getDossier().getEmplacement());
			this.getContenuFenetre().placementObjets();
		}
	}

	/**
	 * Méthode contenant l'action associée au bouton "Editer"
	 */
	private void actionBoutonEditer() {
		// RAF
		System.out.println("TODO");
	}
}
