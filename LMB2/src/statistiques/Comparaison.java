/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.File;
import java.util.ArrayList;

public class Comparaison {

	//------------------
	// Attributs
	//------------------
	private Statistiques stats1;
	private Statistiques stats2;
	
	private ArrayList<File> lesFichiersAjoutes;
	private ArrayList<File> lesFichiersSupprimes;
	private ArrayList<File> lesFichiersModifies;
	
	//------------------
	// Constructeur
	//------------------
	public Comparaison(Statistiques stats1, Statistiques stats2){
		this.stats1 = stats1;
		this.stats2 = stats2;
		
		// On lance les statistiques pour les stats2 (en théorie le init() a déjà été fait pour stats1)
		stats2.init();
		
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
	 * Cette méthode initiliase la comparaison et remplit les ArrayList d'ajouts, de suppression et de modification
	 */
	public void init(){
		/**
		 * A FAIRE
		 */
	}
	
	
	
	
	
	
	

	
}
