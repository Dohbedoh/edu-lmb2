/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class LinkListAdapter extends AbstractListModel{

	//------------------
	// Attributs
	//------------------
	private static final long serialVersionUID = -8345246054788105890L;
	private ArrayList<String> lesLinks;
	
	//------------------
	// Constructeur
	//------------------
	public LinkListAdapter(ArrayList<String> lesFiles){
		super();
		this.lesLinks = lesFiles;
	}

	//------------------
	// Méthodes
	//------------------
	public Object getElementAt(int arg0) {
		return this.lesLinks.get(arg0);
	}

	public int getSize() {
		return this.lesLinks.size();
	}
	
}
