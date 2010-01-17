/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.JFrame;

public class SpreadSheet extends JFrame{

	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	private VueTableau vueTableau;
	private VueChart vueChart;
	//------------------
	// Constructeurs
	//------------------
	public SpreadSheet(Hashtable<String, Integer> dataMotsComplet){
		super("Aspirateur - LMB2");
		this.dataMotsComplet = dataMotsComplet;
		setLayout(new BorderLayout());
		
		// Cr�ation des �l�ments graphiques
		vueChart = new VueChart(dataMotsComplet);
		vueTableau = new VueTableau(dataMotsComplet);
		
		// Ajouts des �lements graphiques
		add(vueTableau,BorderLayout.WEST);
		add(vueChart,BorderLayout.CENTER);
		
		// Options de la JFrame
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		//setPreferredSize(new Dimension(800,800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//cons-1
	
	//------------------
	// M�thodes
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
		SpreadSheet s = new SpreadSheet(tab1);
	}
}
