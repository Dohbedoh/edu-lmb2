/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractListModel;
import statistiques.Statistiques;

public class ListAdapter extends AbstractListModel{

	//------------------
	// Attributs
	//------------------
	private static final long serialVersionUID = -8345246054788105890L;
	private ArrayList<File> lesFiles;
	private int nb;
	
	//------------------
	// Constructeur
	//------------------
	public ListAdapter(ArrayList<File> lesFiles){
		super();
		this.lesFiles = lesFiles;
	}

	//------------------
	// Méthodes
	//------------------
	public Object getElementAt(int arg0) {
		return this.lesFiles.get(arg0);
	}

	public int getSize() {
		return this.lesFiles.size();
	}
	
}
