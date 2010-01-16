/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import statistiques.Statistiques;

import Aspirateur.Aspirateur;
import Aspirateur.Meta;

public class VueMetaStat extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5617211546923946546L;

	//------------------
	// Attributs
	//------------------
	public Statistiques stats;

	public JTextArea text;
	//------------------
	// Constructeur
	//------------------
	public VueMetaStat(Statistiques stats){
		this.stats = stats;
		
		setLayout(new BorderLayout());
		TitledBorder afact = BorderFactory.createTitledBorder("Méta-Données");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		// Création des éléments graphiques
		text = new JTextArea("");
		text.setEditable(false);
		
		// Ajout des elements
		add(text, BorderLayout.CENTER);
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	public String getText() {
		return text.getText();
	}
	
	public void setText(String t) {
		this.text.setText(t);
	}
}
