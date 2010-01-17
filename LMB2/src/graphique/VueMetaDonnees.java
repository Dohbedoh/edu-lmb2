/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Aspirateur.Aspirateur;

public class VueMetaDonnees extends JPanel {

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;

	public JTextArea valeur;
	//------------------
	// Constructeur
	//------------------
	public VueMetaDonnees(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		
		setLayout(new BorderLayout());
		TitledBorder afact = BorderFactory.createTitledBorder("M�ta-Donn�es");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		// Cr�ation des �l�ments graphiques
		valeur = new JTextArea("<Entrer vos commentaires>");
		valeur.setLineWrap(true);
		valeur.setWrapStyleWord(true);
		
		// Ajout des elements
		add(valeur, BorderLayout.CENTER);
	}//cons-1
	
	//------------------
	// M�thodes
	//------------------
	public JTextArea getValeur() {
		return valeur;
	}
	
	public void setValeur(JTextArea valeur) {
		this.valeur = valeur;
	}
}
