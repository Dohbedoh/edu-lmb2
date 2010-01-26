/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import comparaison.Comparaison;

public class SpreadSheetComparaison  extends JFrame{

	private VueTableauComparaison vueTableau;
	private VueHisto vueHisto;
	private Comparaison comparaison;
	
	//------------------
	// Constructeurs
	//------------------
	public SpreadSheetComparaison(Comparaison comparaison){
		super("Aspirateur - LMB2");
		this.comparaison = comparaison;
		setLayout(new BorderLayout());
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem masquer = new JMenuItem("Masquer/Afficher la Table");
		masquer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(vueTableau.isVisible()){
					vueTableau.setVisible(false);
				}else{
					vueTableau.setVisible(true);
				}
			}
		});
		menubar.add(menu);
		menu.add(masquer);
		
		// Création des éléments graphiques
		vueHisto = new VueHisto(comparaison.getStatsCourante().getMetaData(), comparaison.getStats2().getMetaData(),comparaison.getStatsCourante().getDate(), comparaison.getStats2().getDate(), comparaison.getStats2().getNomSite());
		vueTableau = new VueTableauComparaison(comparaison.getStatsCourante().getMetaData().getTagsTable(), comparaison.getStats2().getMetaData().getTagsTable());
		// Ajouts des élements graphiques
		vueTableau.setPreferredSize(new Dimension(300,200));
		add(vueTableau,BorderLayout.WEST);
		add(vueHisto,BorderLayout.CENTER);
		
		this.setJMenuBar(menubar);
		
		// Options de la JFrame
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		//setPreferredSize(new Dimension(800,800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------

	
	//------------------
	// Main
	//------------------
	public static void main(String[] args){
		
		Hashtable<String, Integer> tab1 = new Hashtable<String, Integer>();
		tab1.put("a", Integer.valueOf(1));
		tab1.put("b", Integer.valueOf(2));
		tab1.put("c", Integer.valueOf(3));
		
		// TEST
		SpreadSheetStatistiques s = new SpreadSheetStatistiques(tab1);
	}
}
