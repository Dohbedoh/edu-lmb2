/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;

import Aspirateur.Aspirateur;

public class Main {

	//------------------
	// Main
	//------------------
	/**
	 * Lancement du programme principal
	 */
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        try {
	        	//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
	        	UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
	        } catch (Exception e) {
	          System.out.println("Substance Raven Graphite failed to initialize");
	        }
	        Aspirateur laspirateur = new Aspirateur();
			new VueChoixWorkspace(laspirateur);
			
	      }
	    });
	}
	
}
