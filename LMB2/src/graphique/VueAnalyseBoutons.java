/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import statistiques.Statistiques;
import tableur.*;

public class VueAnalyseBoutons extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	
	private String liste[] = {"Détails Statistiques","Fréquence des mots" , "Liens Hypertextes","Balises"};
	private JComboBox combo;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseBoutons(Statistiques stats){
		this.stats = stats;
		
		combo = new JComboBox(liste);
		add(combo);
		combo.addActionListener(new ActionStats());
		
		if(stats.getLesFichiersEnregistres().size() == 0){
			combo.setEnabled(false);
		}
		
	}//cons-1
	
	/**
	 * Désactive les boutons
	 */
	public void setEnabled(boolean b){
		combo.setEnabled(b);
	}
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
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
	private class ActionStats implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			int index = ((JComboBox)arg0.getSource()).getSelectedIndex();
			switch (index) {
			case 1:
				if(stats.getDataMotsComplet().size()>0){
					SpreadSheet s = new SpreadSheet(stats.getDataMotsComplet());
				}else{
					JOptionPane.showMessageDialog(null, "Aucun mot!", "Information",JOptionPane.INFORMATION_MESSAGE);
				}
				break;
			case 2:
				if(stats.getDataLinksComplet().size()>0){
					SpreadSheet s = new SpreadSheet(stats.getDataLinksComplet());
				}else{
					JOptionPane.showMessageDialog(null, "Aucun lien hypertexte!", "Information",JOptionPane.INFORMATION_MESSAGE);
				}
				break;
			case 3:
				if(stats.getMetaData().getTagsTable().size()>0){
					SpreadSheet s = new SpreadSheet(stats.getMetaData().getTagsTable());
				}else{
					JOptionPane.showMessageDialog(null, "Aucun lien hypertexte!", "Information",JOptionPane.INFORMATION_MESSAGE);
				}
				break;	

			default:
				break;
			}
		
		}
	}
	
	
}
