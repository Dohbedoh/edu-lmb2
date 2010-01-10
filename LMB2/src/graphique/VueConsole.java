/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Aspirateur.*;

public class VueConsole extends JPanel  implements Observer{

	//------------------
	// Attributs
	//------------------
	private JTextArea laconsole;
	public Aspirateur laspirateur;
	public JScrollPane scroll;
	//------------------
	// Constructeur
	//------------------
	public VueConsole(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		
		laspirateur.addObserver(this);
		
		// Creation des elements graphiques
		laconsole = new JTextArea(10,65);
		PrintStream out = new PrintStream( new JTextAreaAdapter(laconsole));
		
		// Ajout des elements graphiques
		scroll = new JScrollPane(laconsole,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll);
		
		// Redirection des sorties standard et d'erreur
		System.setOut(out);
		//System.setErr(out);

		// Options des elements graphiques
		laconsole.setEditable(false);
		laconsole.setFont(new Font("TimesRoman",Font.PLAIN,12));
		
		this.setBorder(BorderFactory.createTitledBorder("Console"));
	}//cons-1

	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		JScrollBar jsb = scroll.getVerticalScrollBar() ;
		jsb.setValue( jsb.getMaximum() );
	}
}
