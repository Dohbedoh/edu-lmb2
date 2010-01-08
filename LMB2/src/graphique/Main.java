/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.Aspirateur;

public class Main {

	//------------------
	// Main
	//------------------
	/**
	 * Lancement du programme principal
	 */
	public static void main(String[] args){
		Aspirateur laspirateur = new Aspirateur();
		new VueChoixWorkspace(laspirateur);
	}
	
}
