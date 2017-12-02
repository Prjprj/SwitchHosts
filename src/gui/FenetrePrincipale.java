package gui;

import java.awt.Container;

import javax.swing.JFrame;

/**
 * Classe de gestion de la Fenetre Principale
 * 
 * @author Prj
 * @version 0.1
 */
public class FenetrePrincipale extends JFrame {
	private static final long serialVersionUID = 8291072742407045918L;

	/**
	 * Constructeur de la Fenetre
	 * 
	 * @param titre
	 *            Nom dans la barre de titre
	 * @param largeur
	 *            Largeur de la Fenetre
	 * @param hauteur
	 *            Hauteur de la Fenetre
	 * @param emplacementDossier
	 *            Emplacement du Dossier qui permettra de remplir la ComboBox
	 */
	public FenetrePrincipale(String titre, int largeur, int hauteur,
			String emplacementDossier) {
		super();
		this.setTitle(titre);
		this.setSize(largeur, hauteur);

		ContenuFenetrePrincipale contenuFenetrePrincipale = new ContenuFenetrePrincipale(
				this, emplacementDossier);
		Container leContenant = this.getContentPane();
		leContenant.add(contenuFenetrePrincipale);
		addWindowListener(new ListenerFenetrePrincipale(this));
	}

	/**
	 * Methode pour rendre visible la Fenetre Principale
	 */
	public void rendreVisible() {
		this.setVisible(true);
	}

	/**
	 * Methode pour rendre invisible la Fenetre Principale
	 */
	public void rendreInvisible() {
		this.setVisible(false);
	}
}
