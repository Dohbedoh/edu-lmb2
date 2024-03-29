/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tableur.*;

public class VueAnalyseBoutons extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	
	private String liste[] = {"D�tails Statistiques","Fr�quence des mots" , "Liens Hypertextes","Balises"};
	private JComboBox combo;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseBoutons(Statistiques stats){
		this.stats = stats;
		
		combo = new JComboBox(liste);
		this.setLayout(new BorderLayout());
		add(combo, BorderLayout.CENTER);
		combo.addActionListener(new ActionStats());
		
		if(stats.getLesFichiersEnregistres().size() == 0){
			combo.setEnabled(false);
		}
		
	}//cons-1
	
	/**
	 * D�sactive les boutons
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
	// Actions
	//------------------
	private class ActionStats implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			int index = ((JComboBox)arg0.getSource()).getSelectedIndex();
			switch (index) {
			case 1:
				
				if(stats.getDataMotsComplet().size()>0){
					SpreadSheetStatistiques s = new SpreadSheetStatistiques(stats.getDataMotsComplet());
				}else{
					stats.processDataMots();
					SpreadSheetStatistiques s = new SpreadSheetStatistiques(stats.getDataMotsComplet());
				}
				break;
			case 2:
				if(stats.getDataLinksComplet().size()>0){
					SpreadSheetStatistiques s = new SpreadSheetStatistiques(stats.getDataLinksComplet());
				}else{
					stats.processDataLinks();
					SpreadSheetStatistiques s = new SpreadSheetStatistiques(stats.getDataLinksComplet());
				}
				break;
			case 3:
				if(stats.getMetaData().getTagsTable().size()>0){
					SpreadSheetStatistiques s = new SpreadSheetStatistiques(stats.getMetaData().getTagsTable());
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
