/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import statistiques.Statistiques;

public class VueAnalyseList extends JPanel implements Observer{
	
	private Statistiques stats;
	private JList jlist;
	private JButton imagesBut, addrBut, cssBut, jsBut;
	
	
	public VueAnalyseList(Statistiques stats){
		this.stats = stats;
		this.setLayout(new BorderLayout(5,5));
		//stats.addObserver(this);
		//setBorder(BorderFactory.createTitledBorder("Listes de Fichiers"));
		jlist = new JList(new ListAdapter(stats));
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		add(jlist,BorderLayout.CENTER);
		
		Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		
		imagesBut = new JButton("Voir Images");
		addrBut = new JButton("Voir Adresse");
		cssBut = new JButton("Voir CSS");
		jsBut = new JButton("Voir JS");
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    		.addComponent(imagesBut)
	                    )
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                        .addComponent(addrBut)
		                )
	    	            .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(cssBut)
                        )
        	    	    .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(jsBut)
                        )
	                )
	            )
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(imagesBut)
	                		.addComponent(addrBut)
	                		.addComponent(cssBut)
	                		.addComponent(jsBut)
	                )
	                .addGap(10)
	          )
		);
		
		add(cont,BorderLayout.SOUTH);
		
		/*if(stats.getLineCount()!=0){
			jlist.setSelectedIndex(0);
		}*/
		update(null,null);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
