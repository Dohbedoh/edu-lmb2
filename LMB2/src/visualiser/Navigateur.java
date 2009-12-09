/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package visualiser;

import graphique.IGAspirateur;


import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Navigateur extends JFrame{

	//------------------
	// Attributs
	//------------------
	String path;
	URL previousURL;
	//ArrayList<URL> previousURL = new ArrayList<URL>();
	URL nextURL;
	//ArrayList<URL> nextURL = new ArrayList<URL>();
	
	
	JEditorPane conteneur;
	JButton precedent;
	JButton suivant;
	JButton fermer;
	
	//--------------
	// Constructeur
	//--------------
	public Navigateur(String path){
		super(path);
		this.path = path;
		this.setLayout(new BorderLayout());
		
		// Creation des éléments graphiques
		JPanel outils = new JPanel(new FlowLayout());
		precedent = new JButton("Precedent");
		suivant = new JButton("Suivant");
		fermer = new JButton("Fermer");
		
		try {
			File file = new File(path + "index.html");
			if (file != null) {
				conteneur = new JEditorPane(file.toURL());

			} else {
				File file2 = new File(path + "index.php");
				conteneur = new JEditorPane(file2.toURL());
			}
			
			// Options des éléments graphiques
			conteneur.setEditable(false);
			precedent.setEnabled(false);
			suivant.setEnabled(false);
			conteneur.addHyperlinkListener(new HyperlinkListener() {
				public void hyperlinkUpdate(HyperlinkEvent e) {
					if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						JEditorPane pane = (JEditorPane) e.getSource();
						previousURL = ((JEditorPane)e.getSource()).getPage() ;
						precedent.setEnabled(true);
						try {
							pane.setPage(e.getURL());
							nextURL = e.getURL() ;
						} catch (IOException ex) {
							pane.setText("ERREUR : " + ex.getMessage());
						}
					}
				}
			});
			
			precedent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						conteneur.setPage(previousURL);
					} catch (IOException e1) {}
				}
			});
			
			suivant.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						conteneur.setPage(nextURL);
					} catch (IOException e1) {}
				}
			});
			
			fermer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					setVisible(false);
				}
			});
			
			suivant.setForeground(Color.BLUE);
			precedent.setForeground(Color.BLUE);
		} catch (IOException ex) { ex.printStackTrace(); }
		
		
		// Ajouts des éléments graphiques
		JScrollPane scrollPane = new JScrollPane(conteneur);
		outils.add(fermer);
		outils.add(precedent);
		outils.add(suivant);
		add(scrollPane,BorderLayout.CENTER);
		add(outils, BorderLayout.NORTH);
		
		
		//----------
		pack();
		setVisible(true);
		setSize(840,524);
	}
	
	//--------------
	// Methodes
	//--------------
	
	//------------------
	// Main
	//------------------
	public static void main(String arg[]){
		new Navigateur("C:/LMB2/Test1/7-12-2009/");
	}

}
