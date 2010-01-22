/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class VueChart extends JPanel {
	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	//------------------
	// Constructeurs
	//------------------
	public VueChart(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;

		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Graphique");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
	    DefaultPieDataset pieDataset = new DefaultPieDataset();
		Enumeration<String> e = this.dataMotsComplet.keys();
		String cle = e.nextElement();
		while (e.hasMoreElements()){
			pieDataset.setValue(cle.toLowerCase(), dataMotsComplet.get(cle));
			cle = e.nextElement();
		}
		//pieDataset.sortByKeys(SortOrder.ASCENDING);
		

	    JFreeChart pieChart = ChartFactory.createPieChart3D("Diagramme", 
	      pieDataset, true, true, true);
	    PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.7f);
        plot.setNoDataMessage("No data to display");

	    ChartPanel cPanel = new ChartPanel(pieChart);
	    add(cPanel); 

		
		/**
		 * UTILISER LA JFREE CHART
		 * METTRE DES BOUTONS
		 * ETC.
		 */
	}//cons-1
}
