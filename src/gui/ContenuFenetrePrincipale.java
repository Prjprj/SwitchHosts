package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import application.Constantes;

import util.DossierVideException;
import util.ParseurFichierConf;

import manipulationFichiers.Dossier;
import manipulationFichiers.Fichier;

/**
 * Classe g�rant le contenu de la Fenetre Principale
 * 
 * @author Prj
 * @version 0.1
 */
public class ContenuFenetrePrincipale extends JPanel {
	// D�claration des attributs de la classe
	private static final long serialVersionUID = 5140175873074024647L;
	private FenetrePrincipale fenetrePrincipale;
	private SpringLayout spLayout;
	private ListenerContenuFenetrePrincipale listenerContenuFenetrePrincipale;
	private JLabel label;
	private JButton boutonValider;
	private JButton boutonEditer;
	private JButton boutonQuitter;
	private JButton boutonChanger;
	private JButton boutonSelectionner;
	private JComboBox listeFichiers;
	private JTextArea affichageContenuFichier;
	private JScrollPane ascenceur;
	private Dossier dossier;

	// Cr�ation des Setters
	/**
	 * M�thode permettant de r�cup�rer la Fenetre Appelante
	 */
	private void setFenetre(FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
	}

	/**
	 * M�thode permettant d'initialiser le SpringLayout affichant les objets de
	 * la Fenetre
	 * 
	 * @param spLayout
	 */
	private void setSpLayout(SpringLayout spLayout) {
		this.spLayout = spLayout;
	}

	/**
	 * M�thode permettant d'initialiser le bouton "Valider"
	 */
	private void setBoutonValider() {
		this.boutonValider = new JButton(ParseurFichierConf.getProperty(
				"Lb_Bouton_Valider", Constantes.nomFichierTrad));
	}

	/**
	 * M�thode permettant d'initialiser le bouton "Editer"
	 */
	private void setBoutonEditer() {
		this.boutonEditer = new JButton(ParseurFichierConf.getProperty(
				"Lb_Bouton_Editer", Constantes.nomFichierTrad));
	}

	/**
	 * M�thode permettant d'initialiser le bouton "Quitter"
	 */
	private void setBoutonQuitter() {
		this.boutonQuitter = new JButton(ParseurFichierConf.getProperty(
				"Lb_Bouton_Quitter", Constantes.nomFichierTrad));
	}

	/**
	 * M�thode permettant d'initialiser le bouton "Rechercher un fichier"
	 */
	private void setBoutonChanger() {
		this.boutonChanger = new JButton(ParseurFichierConf.getProperty(
				"Lb_Bouton_Changer", Constantes.nomFichierTrad));
	}

	/**
	 * M�thode permettant d'initialiser le bouton "S�lectionner"
	 */
	private void setBoutonSelectionner() {
		this.boutonSelectionner = new JButton(ParseurFichierConf.getProperty(
				"Lb_Bouton_Selectionner", Constantes.nomFichierTrad));
	}

	/**
	 * M�thode permettant d'initialiser la ComboBox affichant la liste des
	 * Fichiers
	 * 
	 * @param listeFichiers
	 *            La ComboBox
	 */
	private void setListeFichiers(JComboBox listeFichiers) {
		this.listeFichiers = listeFichiers;
	}

	/**
	 * M�thode permettant d'initialiser l'aire de texte permettant l'affichage
	 * des Fichiers, celle-ci est incluse dans un JScrollPane pour permettre de
	 * g�rer un "Ascenceur" si les fichiers sont trop longs
	 */
	private void setAffichageContenuFichier() {
		this.affichageContenuFichier = new JTextArea();
		this.getAffichageContenuFichier().setEditable(false);
		this.getAffichageContenuFichier().setAutoscrolls(false);
		this.setAscenceur(this.affichageContenuFichier);
		this.getAscenceur().setPreferredSize(new Dimension(550, 380));
		this.getAscenceur().setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getAscenceur().setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * M�thode permettant d'initialiser le Listener g�rant les actions sur les
	 * boutons
	 * 
	 * @param contenuFenetrePrincipale
	 *            L'objet cr�ant le Listeneur
	 */
	private void setListenerContenuFenetre(
			ContenuFenetrePrincipale contenuFenetrePrincipale) {
		this.listenerContenuFenetrePrincipale = new ListenerContenuFenetrePrincipale(
				contenuFenetrePrincipale);
	}

	/**
	 * M�thode permettant d'initialiser le Label affichant les informations �
	 * l'utilisateur
	 * 
	 * @param chaine
	 *            Chaine � afficher � l'initialisation
	 */
	private void setLabel(String chaine) {
		this.label = new JLabel(chaine);
	}

	/**
	 * M�thode permettant d'initialiser le Dossier qui contient les Fichiers
	 * affich�s par la ComboBox
	 * 
	 * @param emplacement
	 *            L'emplacement du Dossier
	 */
	private void setDossier(String emplacement) throws DossierVideException {
		try {
			this.dossier = new Dossier(emplacement);
		} catch (DossierVideException e) {
			this.getLabel().setText(
					ParseurFichierConf.getProperty("Lb_Label_Dossier_Vide_1",
							Constantes.nomFichierTrad)
							+ emplacement
							+ " "
							+ ParseurFichierConf.getProperty(
									"Lb_Label_Dossier_Vide_2",
									Constantes.nomFichierTrad));
			this.getLabel().setForeground(Color.RED);
			this.getLabel().setVisible(true);
			this.getBoutonSelectionner().setEnabled(false);
			this.getBoutonEditer().setEnabled(false);
			this.getBoutonValider().setEnabled(false);
			throw new DossierVideException();
		}
	}

	/**
	 * M�thode permettant d'initialiser le JScrollPane contenant l'aire
	 * d'affichage des fichiers
	 * 
	 * @param aireTexte
	 *            L'aire de texte
	 */
	private void setAscenceur(JTextArea aireTexte) {
		this.ascenceur = new JScrollPane(aireTexte);
	}

	/**
	 * M�thode permettant d'acc�der au Listener permettant de g�rer les actions
	 * sur les boutons
	 * 
	 * @return Le Listener
	 */
	private ListenerContenuFenetrePrincipale getListenerContenuFenetre() {
		return this.listenerContenuFenetrePrincipale;
	}

	/**
	 * M�thode permettant d'acc�der au JScrollPane contenant l'aire de texte
	 * pour g�rer un ascenceur
	 * 
	 * @return Le JScrollPane
	 */
	private JScrollPane getAscenceur() {
		return this.ascenceur;
	}

	// Cr�ation des Getters
	/**
	 * M�thode permettant d'acc�der au SpringLayout contenant tous les objets �
	 * afficher
	 * 
	 * @return Le SpringLayout
	 */
	public SpringLayout getSpLayout() {
		return this.spLayout;
	}

	/**
	 * M�thode permettant d'acc�der � la Fenetre Principale
	 * 
	 * @return La Fenetre Principale
	 */
	public FenetrePrincipale getFenetre() {
		return this.fenetrePrincipale;
	}

	/**
	 * M�thode permettant d'acc�der au bouton "Valider"
	 * 
	 * @return Le Bouton
	 */
	public JButton getBoutonValider() {
		return this.boutonValider;
	}

	/**
	 * M�thode permettant d'acc�der au bouton "Editer"
	 * 
	 * @return Le Bouton
	 */
	public JButton getBoutonEditer() {
		return this.boutonEditer;
	}

	/**
	 * M�thode permettant d'acc�der au bouton "Quitter"
	 * 
	 * @return Le Bouton
	 */
	public JButton getBoutonQuitter() {
		return this.boutonQuitter;
	}

	/**
	 * M�thode permettant d'acc�der au bouton "Rechercher un fichier"
	 * 
	 * @return Le Bouton
	 */
	public JButton getBoutonChanger() {
		return this.boutonChanger;
	}

	/**
	 * M�thode permettant d'acc�der au bouton "S�lectionner"
	 * 
	 * @return Le Bouton
	 */
	public JButton getBoutonSelectionner() {
		return this.boutonSelectionner;
	}

	/**
	 * M�thode permettant d'acc�der � la ComboBox
	 * 
	 * @return La ComboBox
	 */
	public JComboBox getListeFichiers() {
		return this.listeFichiers;
	}

	/**
	 * M�thode permettant d'acc�der � l'aire de texte affichant le contenu des
	 * fichiers
	 * 
	 * @return L'aire de texte
	 */
	public JTextArea getAffichageContenuFichier() {
		return this.affichageContenuFichier;
	}

	/**
	 * M�thode permettant d'acc�der au Label qui permet d'afficher des message �
	 * destination de l'utilisateur
	 * 
	 * @return Le Label
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/**
	 * M�thode permettant d'acc�der au Dossier qui permet de remplir la ComboBox
	 * 
	 * @return Le Dossier
	 */
	public Dossier getDossier() {
		return this.dossier;
	}

	/**
	 * Constructeur du contenu de la Fenetre Principale
	 * 
	 * @param fenetrePrincipale
	 *            La Fenetre Principale
	 * @param emplacementDossier
	 *            L'emplacement du Dossier n�cessaire au remplissage de la
	 *            ComboBox
	 */
	public ContenuFenetrePrincipale(FenetrePrincipale fenetrePrincipale,
			String emplacementDossier) {
		super();
		// Initialisation de la FenetrePrincipale
		this.setFenetre(fenetrePrincipale);

		// Initialisation du Layout
		this.setSpLayout(new SpringLayout());
		this.setLayout(this.getSpLayout());

		// Ajout d'un Listener pour les actions sur la Fenetre
		this.setListenerContenuFenetre(this);

		// Initialisation des composants affichables
		this.setAffichageContenuFichier();
		this.setLabel("");
		this.getLabel().setVisible(false);
		this.setBoutonValider();
		this.setBoutonEditer();
		this.setBoutonChanger();
		this.setBoutonQuitter();
		this.setBoutonSelectionner();

		// Param�trage de la fen�tre
		this.ajoutComposants();

		// Initialisation de la ComboBox
		this.remplissageComboBox(emplacementDossier);

		this.placementObjets();
		this.ajoutActionListener();
	}

	/**
	 * M�thode permettant le remplissage de la ComboBox � partir du contenu d'un
	 * Dossier
	 * 
	 * @param emplacementDossier
	 *            L'emplacement du Dossier
	 */
	public void remplissageComboBox(String emplacementDossier) {
		try {
			this.setDossier(emplacementDossier);
			String[] listeNoms = new String[this.getDossier().getNbFichiers()];

			Vector<Fichier> vecteurFichiers = this.getDossier()
					.getListeFichiers();
			for (int i = 0; i < this.getDossier().getNbFichiers(); i = i + 1) {
				listeNoms[i] = vecteurFichiers.get(i).getNomFichier();
			}
			this.setListeFichiers(new JComboBox(listeNoms));
			add(this.getListeFichiers());
		} catch (DossierVideException e) {
			this.dossier = new Dossier(emplacementDossier, "");
			String[] listeNoms = new String[1];
			listeNoms[0] = "<Dossier Vide>";
			this.setListeFichiers(new JComboBox(listeNoms));
			this.getListeFichiers().setEnabled(false);
			add(this.getListeFichiers());
		}
	}

	/**
	 * M�thode de placement des objets dans le SpringLayout
	 */
	public void placementObjets() {
		// Placement de la ComboBox
		this.getSpLayout().putConstraint(SpringLayout.WEST,
				this.getListeFichiers(), 30, SpringLayout.WEST,
				this.getFenetre().getContentPane());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getListeFichiers(), 5, SpringLayout.NORTH,
				this.getFenetre().getContentPane());

		// Placement du bouton associ� � la ComboBox
		this.getSpLayout().putConstraint(SpringLayout.WEST,
				this.getBoutonSelectionner(), 10, SpringLayout.EAST,
				this.getListeFichiers());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getBoutonSelectionner(), 0, SpringLayout.NORTH,
				this.getListeFichiers());

		// Placement de la zone de texte affichant le contenu du fichier
		this.getSpLayout().putConstraint(SpringLayout.WEST,
				this.getAscenceur(), 5, SpringLayout.WEST,
				this.getFenetre().getContentPane());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getAscenceur(), 5, SpringLayout.SOUTH,
				this.getListeFichiers());

		// Placement du bouton Changer en fonction de la zone de texte
		this.getSpLayout().putConstraint(SpringLayout.EAST,
				this.getBoutonChanger(), 630, SpringLayout.WEST,
				this.getFenetre().getContentPane());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getBoutonChanger(), 5, SpringLayout.NORTH,
				this.getFenetre().getContentPane());

		// Placement du bouton Editer en fonction du bouton Changer
		this.getSpLayout().putConstraint(SpringLayout.EAST,
				this.getBoutonEditer(), 0, SpringLayout.EAST,
				this.getBoutonChanger());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getBoutonEditer(), 200, SpringLayout.SOUTH,
				this.getBoutonChanger());

		// Placement du bouton Valider en dessous de la zone de texte et du
		// bouton Changer
		this.getSpLayout().putConstraint(SpringLayout.EAST,
				this.getBoutonValider(), 0, SpringLayout.EAST,
				this.getBoutonChanger());
		this.getSpLayout().putConstraint(SpringLayout.NORTH,
				this.getBoutonValider(), 5, SpringLayout.SOUTH,
				this.getAscenceur());

		// Placement du bouton Quitter
		this.getSpLayout().putConstraint(SpringLayout.WEST,
				this.getBoutonQuitter(), 5, SpringLayout.WEST,
				this.getFenetre().getContentPane());
		this.getSpLayout().putConstraint(SpringLayout.SOUTH,
				this.getBoutonQuitter(), 0, SpringLayout.SOUTH,
				this.getBoutonValider());

		// Placement du label
		this.getSpLayout().putConstraint(SpringLayout.WEST, this.getLabel(),
				100, SpringLayout.EAST, this.getBoutonQuitter());
		this.getSpLayout().putConstraint(SpringLayout.NORTH, this.getLabel(),
				5, SpringLayout.NORTH, this.getBoutonQuitter());
	}

	/**
	 * M�thode permettant d'ajouter les objets au Layout pour l'affichage
	 */
	private void ajoutComposants() {
		// Ajout des composants au SpringLayout
		add(this.getBoutonEditer());
		add(this.getBoutonChanger());
		add(this.getBoutonValider());
		add(this.getBoutonQuitter());
		add(this.getAscenceur());
		add(this.getBoutonSelectionner());
		add(this.getLabel());
	}

	/**
	 * M�tode permettant d'ajouter l'ActionListener sur tous les boutons
	 */
	private void ajoutActionListener() {
		// Ajout des listeners sur les boutons
		this.getBoutonQuitter().addActionListener(
				this.getListenerContenuFenetre());
		this.getBoutonValider().addActionListener(
				this.getListenerContenuFenetre());
		this.getBoutonSelectionner().addActionListener(
				this.getListenerContenuFenetre());
		this.getBoutonChanger().addActionListener(
				this.getListenerContenuFenetre());
		this.getBoutonEditer().addActionListener(
				this.getListenerContenuFenetre());

	}
}
