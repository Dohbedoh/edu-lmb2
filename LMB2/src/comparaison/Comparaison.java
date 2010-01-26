/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;

import statistiques.Statistiques;

public class Comparaison extends Observable {

	//------------------
	// Attributs
	//------------------
	private Statistiques stats1;
	private Statistiques stats2;
	private ArrayList<File> lesFichiersAjoutes;
	private ArrayList<File> lesFichiersSupprimes;
	private ArrayList<File> lesFichiersModifies;
	private String dirPath;
	
	//------------------
	// Constructeur
	//------------------
	public Comparaison(Statistiques stats1, Statistiques stats2){
		this.stats1 = stats1;
		this.stats2 = stats2;

		// On lance les statistiques pour les stats2 (en théorie le init() a déjà été fait pour stats1)
		//stats2.init();
		
		// Création des ArrayList pour la comparaison
		lesFichiersAjoutes = new ArrayList<File>();
		lesFichiersSupprimes = new ArrayList<File>();
		lesFichiersModifies = new ArrayList<File>();
		
	}//cons-1
	
	//------------------
	// Getters-Setters
	//------------------
	public Statistiques getStatsCourante() {
		return stats1;
	}
	
	public Statistiques getStats2() {
		return stats2;
	}

	public void setStatsCourante(Statistiques stats1) {
		this.stats1 = stats1;
	}
	
	public void setStats2(Statistiques stats2) {
		this.stats2 = stats2;
	}
	
	//------------------
	public ArrayList<File> getLesFichiersAjoutes() {
		return lesFichiersAjoutes;
	}

	public void setLesFichiersAjoutes(ArrayList<File> lesFichiersAjoutes) {
		this.lesFichiersAjoutes = lesFichiersAjoutes;
	}

	public ArrayList<File> getLesFichiersSupprimes() {
		return lesFichiersSupprimes;
	}

	public void setLesFichiersSupprimes(ArrayList<File> lesFichiersSupprimes) {
		this.lesFichiersSupprimes = lesFichiersSupprimes;
	}

	public ArrayList<File> getLesFichiersModifies() {
		return lesFichiersModifies;
	}

	public void setLesFichiersModifies(ArrayList<File> lesFichiersModifies) {
		this.lesFichiersModifies = lesFichiersModifies;
	}

	public void comparerFichiers(){
		if(stats1.getLesFichiersEnregistres().size() != stats2.getLesFichiersEnregistres().size()){
			System.err.println("Differences");
		}
	}
	
	
	//------------------
	// Méthodes
	//------------------
	
	/**
	 * Cette méthode permet de tester si oui ou non deux fichiers sont égaux
	 */
	public boolean compareFile(File file1, File file2){
		/**
		 * Lecture bytes a bytes d'un fichier
		 */
		return true;
	}
	
	
	/**
	 * Cette méthode initialise la comparaison et remplit les ArrayList d'ajouts, de suppression et de modification
	 */
	public void init(){
		dirPath = stats1.getVersion().getParent();
		stats2.init();
		
		ArrayList<String> lesFichiersStats1 = new ArrayList<String>();
		ArrayList<String> lesFichiersStats2 = new ArrayList<String>();

		if(stats1.getLesFichiersEnregistres()!=null){
			for(int i=0;i<stats1.getLesFichiersEnregistres().size();i++){
				lesFichiersStats1.add(stats1.getLesFichiersEnregistres().get(i).getAbsolutePath().replace(stats1.getVersion().getAbsolutePath(), ""));
			}
		}

		if(stats2.getLesFichiersEnregistres()!=null){
			for(int i=0;i<stats2.getLesFichiersEnregistres().size();i++){
				lesFichiersStats2.add(stats2.getLesFichiersEnregistres().get(i).getAbsolutePath().replace(stats2.getVersion().getAbsolutePath(), ""));
			}
		}
		System.err.println("stats1 : "+lesFichiersStats1.size());
		System.err.println("stats2 : "+lesFichiersStats2.size());
		System.err.println("enr1 : "+stats1.getLesFichiersEnregistres().size());
		System.err.println("enr2 : "+stats2.getLesFichiersEnregistres().size());
		
		// Pour tous les fichiers de stats1
		for (int i = 0 ; i < lesFichiersStats1.size() ; i++){
			
			// Si le fichier est contenu dans stats2
			if(lesFichiersStats2.contains(lesFichiersStats1.get(i))){
				
				// S'il n'a pas été modifié
				int indTmp = lesFichiersStats2.indexOf(lesFichiersStats1.get(i));
				if(compareFile(stats1.getLesFichiersEnregistres().get(i),stats2.getLesFichiersEnregistres().get(indTmp))){
					// TOUT VA BIEN
				}else{
					// LE FICHIER A ETE MODIFIE
					lesFichiersModifies.add(new File(stats2.getVersion().getAbsolutePath()+lesFichiersStats2.get(indTmp)));
				}
				
			}else{
			// Sinon le fichier n'est pas contenu dans stats2
				// LE FICHIER A ETE AJOUTE
				lesFichiersAjoutes.add(new File(stats1.getVersion().getAbsolutePath()+lesFichiersStats1.get(i)));
			}
		}
		
		// Pour tous les fichiers de stats2
		for (int i = 0 ; i < lesFichiersStats2.size() ; i++){
			
			// Si le fichier n'est pas contenu dans stats1
			if(! lesFichiersStats1.contains(lesFichiersStats2.get(i))){
				// LE FICHIER A ETE SUPPRIME
				System.err.println(stats2.getVersion().getAbsolutePath()+lesFichiersStats2.get(i));
				lesFichiersSupprimes.add(new File(stats2.getVersion().getAbsolutePath()+"\\"+lesFichiersStats2.get(i)));
			}
		}
		/*System.err.println("stats1 : "+lesFichiersStats1.size());
		System.err.println("stats2 : "+lesFichiersStats2.size());
		System.err.println("add : "+getLesFichiersAjoutes().size());
		System.err.println("mod : "+getLesFichiersModifies().size());
		System.err.println("rem : "+getLesFichiersSupprimes().size());*/
		setChanged();
		notifyObservers();
	}
	
	
	
	
	
	
	

	
}
