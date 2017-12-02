package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Classe gérant les évènements sur la Fenetre Principale
 * 
 * @author Prj
 * @version 0.1
 */
public class ListenerFenetrePrincipale implements WindowListener {
	// Déclaration des attributs de la classe
	private FenetrePrincipale fenetrePrincipale;

	// Création des Getters
	/**
	 * Méthode permettant de récupérer la Fenetre ayant créé ce Listener
	 * 
	 * @return La Fenetre ayant créé ce Listener
	 */
	private FenetrePrincipale getFenetre() {
		return fenetrePrincipale;
	}

	// Création des Setters
	/**
	 * Méthode permettant d'initialiser la Fenetre ayant créé ce Listener
	 * 
	 * @param fenetrePrincipale
	 *            La Fenetre ayant créé ce Listener
	 */
	private void setFenetre(FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
	}

	/**
	 * Constructeur du Listener
	 * 
	 * @param fenetrePrincipale
	 *            La Fenetre ayant créé ce Listener
	 */
	public ListenerFenetrePrincipale(FenetrePrincipale fenetrePrincipale) {
		super();
		this.setFenetre(fenetrePrincipale);
	}

	/**
	 * Méthode héritée, actions à réaliser à la fermeture de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowClosing(WindowEvent e) {
		this.getFenetre().rendreInvisible();
		System.exit(0);
	}

	/**
	 * Méthode héritée, actions à réaliser à l'activation de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode héritée, actions à réaliser à la fin de la fermeture de la
	 * fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode héritée, actions à réaliser à la désactivation de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode héritée, actions à réaliser à la désiconification de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode héritée, actions à réaliser à l'iconification de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Méthode héritée, actions à réaliser à l'ouverture de la fenêtre
	 * 
	 * @param e
	 *            L'évènement traité
	 */
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
