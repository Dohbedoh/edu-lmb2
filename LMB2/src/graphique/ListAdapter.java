package graphique;

import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractListModel;
import statistiques.Statistiques;

public class ListAdapter extends AbstractListModel implements Observer{


	private static final long serialVersionUID = -8345246054788105890L;
	private Statistiques stats;
	private int nb;
	
	
	public ListAdapter(Statistiques stats){
		super();
		this.stats = stats;
		//stats.addObserver(this);
		//nb = stats.getLineCount();
	}


	@Override
	public void update(Observable o, Object arg) {
		/*int nbL = stats.getLineCount();
		if(nbL==nb){
			fireContentsChanged(this, 0,getSize());
		}else{
			if(nbL<nb){
				fireContentsChanged(this, 0, getSize());
			}else{
				fireContentsChanged(this, 0, getSize());
			}
		}*/
		
	}


	@Override
	public Object getElementAt(int arg0) {
		//return (stats.getLine(arg0));
		return null;
	}


	@Override
	public int getSize() {
		//return stats.getLineCount();
		return 0;
	}
	
}
