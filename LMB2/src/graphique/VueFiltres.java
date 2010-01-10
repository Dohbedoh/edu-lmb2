/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

import Aspirateur.*;

public class VueFiltres extends JPanel{

	
	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	public JLabel afficheFiltre;
	
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
    private JCheckBox check14 = new JCheckBox(".html");
    private JCheckBox check15 = new JCheckBox(".pdf");
    private JCheckBox check16 = new JCheckBox(".zip");
    
	
	//------------------
	// Contructeur
	//------------------
	public VueFiltres(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout());
		JPanel panneauFiltres = new JPanel();
		panneauFiltres.setLayout(new GridLayout(1,3,50,0));	
		
		// Creation des elements graphiques de VueFiltres
		afficheFiltre = new JLabel("Filtres");
		
		// Ajout des elements dans le conteneur VueFiltres
		add(afficheFiltre,BorderLayout.NORTH);
		add(panneauFiltres,BorderLayout.CENTER);
		
		
		//1er panneau : pour les filtres d'images
		JPanel filtres1 = new JPanel();
		filtres1.setLayout(new GridLayout(6,1));
		JScrollPane scroll1 = new JScrollPane(filtres1);
		filtres1.add(new JLabel("Images :"));
		filtres1.add(check1);
		filtres1.add(check2);
		filtres1.add(check3);
		filtres1.add(check4);
		filtres1.add(check5);
		filtres1.setBackground(Color.ORANGE);
		panneauFiltres.add(scroll1);		
		
		//2er panneau : pour les filtres d'audios / videos
		JPanel filtres2 = new JPanel();
		filtres2.setLayout(new GridLayout(6,1));
		JScrollPane scroll2 = new JScrollPane(filtres2);
		filtres2.add(new JLabel("Audio / Video :"));
		filtres2.add(check6);
		filtres2.add(check7);
		filtres2.add(check8);
		filtres2.add(check9);
		filtres2.add(check10);
		filtres2.setBackground(Color.BLUE);
		panneauFiltres.add(scroll2);
		
		//3eme panneau : pour les autres filtres
		JPanel filtres3 = new JPanel();
		filtres3.setLayout(new GridLayout(7,1));
		JScrollPane scroll3 = new JScrollPane(filtres3);
		filtres3.add(new JLabel("Autres :"));
		filtres3.add(check11);
		filtres3.add(check12);
		filtres3.add(check13);
		filtres3.add(check14);
		filtres3.add(check15);
		filtres3.add(check16);
		filtres3.setBackground(Color.RED);
		panneauFiltres.add(scroll3);
		
		
		// Ajout des actions
		
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public static void main(String[] args){
		JFrame fp = new JFrame("Test");
		fp.add(new VueFiltres(new Aspirateur()));
		fp.pack();
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
