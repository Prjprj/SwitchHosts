package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Classe g�rant les �v�nements sur la Fenetre Principale
 * 
 * @author Prj
 * @version 0.1
 */
public class ListenerFenetrePrincipale implements WindowListener {
	// D�claration des attributs de la classe
	private FenetrePrincipale fenetrePrincipale;

	// Cr�ation des Getters
	/**
	 * M�thode permettant de r�cup�rer la Fenetre ayant cr�� ce Listener
	 * 
	 * @return La Fenetre ayant cr�� ce Listener
	 */
	private FenetrePrincipale getFenetre() {
		return fenetrePrincipale;
	}

	// Cr�ation des Setters
	/**
	 * M�thode permettant d'initialiser la Fenetre ayant cr�� ce Listener
	 * 
	 * @param fenetrePrincipale
	 *            La Fenetre ayant cr�� ce Listener
	 */
	private void setFenetre(FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
	}

	/**
	 * Constructeur du Listener
	 * 
	 * @param fenetrePrincipale
	 *            La Fenetre ayant cr�� ce Listener
	 */
	public ListenerFenetrePrincipale(FenetrePrincipale fenetrePrincipale) {
		super();
		this.setFenetre(fenetrePrincipale);
	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � la fermeture de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowClosing(WindowEvent e) {
		this.getFenetre().rendreInvisible();
		System.exit(0);
	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � l'activation de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � la fin de la fermeture de la
	 * fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � la d�sactivation de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � la d�siconification de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � l'iconification de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * M�thode h�rit�e, actions � r�aliser � l'ouverture de la fen�tre
	 * 
	 * @param e
	 *            L'�v�nement trait�
	 */
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
