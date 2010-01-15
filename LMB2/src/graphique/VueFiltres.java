/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Aspirateur.*;

public class VueFiltres extends JPanel{

	
	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	//public JLabel afficheFiltre;
	
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	ArrayList<String> listeFiltres = new ArrayList<String>();

	
	//Les cases à cocher
	//Images
	private JCheckBox check1 = new JCheckBox(".gif");
    private JCheckBox check2 = new JCheckBox(".ico");
    private JCheckBox check3 = new JCheckBox(".jpeg");
    private JCheckBox check4 = new JCheckBox(".jpg");
    private JCheckBox check5 = new JCheckBox(".png");
    //Audio / Video
    private JCheckBox check6 = new JCheckBox(".aac");    
    private JCheckBox check7 = new JCheckBox(".avi");
    private JCheckBox check8 = new JCheckBox(".mkv");
    private JCheckBox check9 = new JCheckBox(".mp3");
    private JCheckBox check10 = new JCheckBox(".wav");
    //Autres
    private JCheckBox check11 = new JCheckBox(".css");    
    private JCheckBox check12 = new JCheckBox(".doc");
    private JCheckBox check13 = new JCheckBox(".exe");
    private JCheckBox check14 = new JCheckBox(".js");
    private JCheckBox check15 = new JCheckBox(".pdf");
    private JCheckBox check16 = new JCheckBox(".zip");
    private JCheckBox check17 = new JCheckBox(".xml");
	
	//------------------
	// Contructeur
	//------------------
	public VueFiltres(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout());
		JPanel panneauFiltres = new JPanel();
		
		setLayout(new BorderLayout());
		TitledBorder afact = BorderFactory.createTitledBorder("Filtres");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		// Creation des elements graphiques de VueFiltres
		//afficheFiltre = new JLabel("Filtres", SwingUtilities.CENTER);
		
		// Ajout des elements dans le conteneur VueFiltres
		//add(afficheFiltre,BorderLayout.NORTH);
		add(panneauFiltres,BorderLayout.CENTER);
		
		
		//1er panneau : pour les filtres d'images
		JPanel filtres1 = new JPanel();
		filtres1.setLayout(new GridLayout(6,1));
		JScrollPane scroll1 = new JScrollPane(filtres1);
		filtres1.add(new JLabel("Images", SwingUtilities.CENTER));
		filtres1.add(check1); checkBoxes.add(check1);
		filtres1.add(check2); checkBoxes.add(check2);
		filtres1.add(check3); checkBoxes.add(check3);
		filtres1.add(check4); checkBoxes.add(check4);
		filtres1.add(check5); checkBoxes.add(check5);
		panneauFiltres.add(scroll1);		
		
		//2er panneau : pour les filtres d'audios / videos
		JPanel filtres2 = new JPanel();
		filtres2.setLayout(new GridLayout(6,1));
		JScrollPane scroll2 = new JScrollPane(filtres2);
		filtres2.add(new JLabel("Audio / Video", SwingUtilities.CENTER));
		filtres2.add(check6); checkBoxes.add(check6);
		filtres2.add(check7); checkBoxes.add(check7);
		filtres2.add(check8); checkBoxes.add(check8);
		filtres2.add(check9); checkBoxes.add(check9);
		filtres2.add(check10); checkBoxes.add(check10);
		panneauFiltres.add(scroll2);
		
		//3eme panneau : pour les autres filtres
		JPanel filtres3 = new JPanel();
		filtres3.setLayout(new GridLayout(8,1));
		JScrollPane scroll3 = new JScrollPane(filtres3);
		filtres3.add(new JLabel("Autres", SwingUtilities.CENTER));
		filtres3.add(check11); checkBoxes.add(check11);
		filtres3.add(check12); checkBoxes.add(check12);
		filtres3.add(check13); checkBoxes.add(check13);
		filtres3.add(check14); checkBoxes.add(check14);
		filtres3.add(check15); checkBoxes.add(check15);
		filtres3.add(check17); checkBoxes.add(check17);
		filtres3.add(check16); checkBoxes.add(check16);
		panneauFiltres.add(scroll3);
		
		//-------------------------------
		// Options
		
		// Couleurs
		filtres1.setBackground(new Color(150,200,250));
		filtres2.setBackground(new Color(150,200,250));
		filtres3.setBackground(new Color(150,200,250));
		
		// Taille
		filtres1.setPreferredSize(new Dimension(100,150));
		filtres2.setPreferredSize(new Dimension(100,150));
		filtres3.setPreferredSize(new Dimension(100,150));
		
		// Divers
		//afficheFiltre.setFont(new Font("", Font.BOLD,12));
		
		//-------------------------------
		// Ajout des actions
		for(int i=0; i<checkBoxes.size(); i++){
			checkBoxes.get(i).addActionListener(new ActionCheck());
			checkBoxes.get(i).setSelected(true);
		}
		
		
		
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public ArrayList<String> getListeFiltres(){
		return this.listeFiltres;
	}
	
	//Action des cases à cocher :
	private class ActionCheck implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				// Recuperation de la source de l'action
				JCheckBox current = (JCheckBox)e.getSource();
				
				if(current.isSelected())
					listeFiltres.add(current.getText());
				else
					listeFiltres.remove(current.getText());
				
				/*// Visualiser le contenu de la liste des Filtres
				for (int i = 0; i < listeFiltres.size(); i++){
					System.out.println(listeFiltres.get(i));
				}*/
				
			}
	}
	
	/*
	public static void main(String[] args){
		JFrame fp = new JFrame("Test");
		fp.add(new VueFiltres(new Aspirateur()));
		fp.pack();
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	*/
}
