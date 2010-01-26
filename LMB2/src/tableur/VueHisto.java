package tableur;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Aspirateur.Meta;

public class VueHisto extends JPanel{


	//------------------
	// Attributs
	//------------------
	private Meta meta1;
	private Meta meta2;
	
	//------------------
	// Constructeurs
	//------------------
	public VueHisto(Meta meta1, Meta meta2, String date1, String date2, String nomSite){
		this.meta1 = meta1;
		this.meta2 = meta2;
		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Graphique");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);

		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Enumeration<String> e = this.meta1.getTagsTable().keys();
		String cle = e.nextElement();
		while (e.hasMoreElements()){
			dataset.addValue(this.meta1.getTagsTable().get(cle), date1,cle.toLowerCase());
			cle = e.nextElement();
		}
		e = this.meta1.getTagsTable().keys();
		cle = e.nextElement();
		while (e.hasMoreElements()){
			dataset.addValue(this.meta2.getTagsTable().get(cle), date2,cle.toLowerCase());
			cle = e.nextElement();
		}
		JFreeChart chart = ChartFactory.createBarChart3D(
				"Comparaison de Versions du projet \""+ nomSite + "\"", 
				"Tags", 
				"Occurences", 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				true);
	    ChartPanel cPanel = new ChartPanel(chart);
	    add(cPanel); 

	    CategoryPlot plot = chart.getCategoryPlot();
	    
		NumberAxis axe1 = (NumberAxis) plot.getRangeAxis();
		axe1.setTickLabelFont(new java.awt.Font("Arial", Font.BOLD, 7));
		axe1.setLabelFont(new java.awt.Font("Arial", Font.BOLD, 7));
		 
		org.jfree.chart.axis.Axis axe2 = plot.getDomainAxis();
		axe2.setTickLabelFont(new java.awt.Font("Arial", Font.BOLD, 7));
		axe2.setLabelFont(new java.awt.Font("Arial", Font.BOLD, 7));
		
		/**
		 * UTILISER LA JFREE CHART
		 * METTRE DES BOUTONS
		 * ETC.
		 */
	}
	
}
