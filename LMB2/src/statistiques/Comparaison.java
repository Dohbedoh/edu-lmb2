/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

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
	 * Cette méthode permet de tester si oui ou non deux fichiers sont égaux
	 */
	public boolean compareFile(File file1, File file2){
		return true;
	}
	
	
	/**
	 * Cette méthode initialise la comparaison et remplit les ArrayList d'ajouts, de suppression et de modification
	 */
	public void init(){
		
		// Pour tous les fichiers de stats1
		for (int i = 0 ; i < stats1.getLesFichiersEnregistres().size() ; i++){
			
			// Si le fichier est contenu dans stats2
			if(stats2.getLesFichiersEnregistres().contains(stats1.getLesFichiersEnregistres().get(i))){
				
				// S'il n'a pas été modifié
				int indTmp = stats2.getLesFichiersEnregistres().indexOf(stats1.getLesFichiersEnregistres().get(i));
				if(compareFile(stats1.getLesFichiersEnregistres().get(i), stats2.getLesFichiersEnregistres().get(indTmp))){
					// TOUT VA BIEN
				}else{
					// LE FICHIER A ETE MODIFIE
					lesFichiersModifies.add(stats2.getLesFichiersEnregistres().get(indTmp));
				}
				
			}else{
			// Sinon le fichier n'est pas contenu dans stats2
				// LE FICHIER A ETE AJOUTE
				lesFichiersAjoutes.add(stats1.getLesFichiersEnregistres().get(i));
			}
		}
		
		// Pour tous les fichiers de stats2
		for (int i = 0 ; i < stats2.getLesFichiersEnregistres().size() ; i++){
			
			// Si le fichier n'est pas contenu dans stats1
			if(! stats1.getLesFichiersEnregistres().contains(stats2.getLesFichiersEnregistres().get(i))){
				// LE FICHIER A ETE SUPPRIME
				lesFichiersSupprimes.add(stats2.getLesFichiersEnregistres().get(i));
			}
		}
		
	}
	
	
	
	
	
	
	

	
}
