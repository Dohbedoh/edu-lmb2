/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.*;

public class InvisibleFileFilter implements FileFilter {

	/**
	 * Cette classe permet de filter les fichiers invisibles
	 * c'est-à-dire les fichiers commencant par un .
	 */
	//------------------
	// Constructeur
	//------------------
	public InvisibleFileFilter(){
		
	}
	
	//------------------
	// Methodes
	//------------------
	public boolean accept(File pathname) {
		return (!pathname.getName().startsWith("."));
	}

}
