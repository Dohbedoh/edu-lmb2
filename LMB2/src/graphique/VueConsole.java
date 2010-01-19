/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Aspirateur.*;

public class VueConsole extends JPanel  implements Observer{

	//------------------
	// Attributs
	//------------------
	private JTextArea laconsole;
	private JLabel currentPage;
	private JLabel currentPageLab;
	public Aspirateur laspirateur;
	public JScrollPane scroll;
	//------------------
	// Constructeur
	//------------------
	public VueConsole(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout(5,5));
		//this.setPreferredSize(new Dimension(200,150));
		
		laspirateur.addObserver(this);
		
		currentPageLab = new JLabel("Current Page : ");
		currentPage = new JLabel("");
		

		currentPage.setFont(new Font(null,1,11));
		currentPageLab.setFont(new Font(null,1,11));

		currentPage.setForeground(new Color(51,204,0));
		
		// Creation des elements graphiques
		laconsole = new JTextArea();
		laconsole.setRows(6);
		PrintStream out = new PrintStream( new JTextAreaAdapter(laconsole));
		
		// Ajout des elements graphiques
		scroll = new JScrollPane(laconsole,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Container cont = new Container();
		cont.setLayout(new BorderLayout());
		cont.add(currentPageLab, BorderLayout.WEST);
		cont.add(currentPage, BorderLayout.CENTER);
		
		this.add(cont, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		
		// Redirection des sorties standard et d'erreur
		System.setOut(out);
		//System.setErr(out);

		// Options des elements graphiques
		laconsole.setEditable(false);
		laconsole.setFont(new Font("TimesRoman",Font.PLAIN,12));
		
		TitledBorder afact = BorderFactory.createTitledBorder("Console");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
	}//cons-1

	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		JScrollBar jsb = scroll.getVerticalScrollBar() ;
		jsb.setValue( jsb.getMaximum() );
		currentPage.setText(laspirateur.getCurrentPage());
	}
}
