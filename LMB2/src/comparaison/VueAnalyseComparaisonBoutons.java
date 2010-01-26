/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import tableur.*;

public class VueAnalyseComparaisonBoutons extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Comparaison comparaison;
	
	private JButton balises;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaisonBoutons(Comparaison comparaison){
		this.comparaison = comparaison;
		this.setLayout(new BorderLayout());
		
		balises = new JButton("Comparaison des balises");
		balises.addActionListener(new ActionComp());
		
		add(balises,BorderLayout.CENTER);
	}//cons-1
	
	/**
	 * Désactive les boutons
	 */
	public void setEnabled(boolean b){
		balises.setEnabled(b);
	}
	
	public void setComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
	}
	
	public Comparaison getComparaison(){
		return this.comparaison;
	}
		
	//------------------
	// Méthodes
	//------------------	
	public Insets getInsets() {
		Insets normal = super.getInsets();
		return new Insets(normal.top + 5, normal.left + 5, 
				normal.bottom + 5, normal.right + 5);
	}
	
	//------------------
	// Actions
	//------------------
	private class ActionComp implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			SpreadSheetComparaison s = new SpreadSheetComparaison(comparaison);
		}
	}
	
	
}
