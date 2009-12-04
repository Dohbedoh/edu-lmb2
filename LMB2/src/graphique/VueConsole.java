/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import Aspirateur.*;

public class VueConsole extends JPanel{

	//------------------
	// Attributs
	//------------------
	private JTextArea laconsole;
	public Aspirateur laspirateur;
	
	//--------------
	// Constructeur
	//--------------
	public VueConsole(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		
		// Creation des elements graphiques
		laconsole = new JTextArea(10,65);
		PrintStream out = new PrintStream( new JTextAreaAdapter(laconsole));
		
		// Ajout des elements graphiques
		JScrollPane scroll = new JScrollPane(laconsole);
		this.add(scroll);
		
		
		// Redirection des sorties standard et d'erreur
		System.setOut(out);
		//System.setErr(out);

		// Options des elements graphiques
		laconsole.setEditable(false);
		laconsole.setFont(new Font("TimesRoman",Font.PLAIN,10));
		
		this.setBorder(BorderFactory.createTitledBorder("Console"));
	}//cons-1

	//------------------
	// Methodes
	//------------------
	
}
