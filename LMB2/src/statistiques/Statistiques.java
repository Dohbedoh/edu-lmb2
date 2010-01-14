/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.htmlparser.parserapplications.StringExtractor;
import org.htmlparser.util.ParserException;
public class Statistiques {

	//------------------
	// Attributs
	//------------------
	
	//------------------
	// Attributs relatifs à la version sélectionnée

	File version;
	String date;
	
	InvisibleFileFilter filtre;	
	ArrayList<File> lesFichiersEnregistres;
	
	//------------------
	// Attributs relatifs aux calculs des statistiques
	private Hashtable<String, Integer> dataMotsComplet;

	//------------------
	// Constructeurs
	//------------------
	public Statistiques(File version){
		this.version = version;
		
		// Creation des elements necessaires
		filtre = new InvisibleFileFilter();
		lesFichiersEnregistres = new ArrayList<File>();
		
		this.date = getDateVersion(version);
		//System.out.println(date);
		
		// Recuperation de tous les fichiers fils
		listerFils(version);
		
		for(int i = 0; i < lesFichiersEnregistres.size();i++){
			System.out.println(lesFichiersEnregistres.get(i).getPath());
		}
		
		
		// Lancement du process de recuperation des statistiques
		
		// Pour tous les fichiers de lesFichiersEnregistres, si le fichier est un fichier html alors
		MyStringExtractor stringExtractor = new MyStringExtractor(lesFichiersEnregistres.get(1).getPath());
		// Fusionner stringExtractor.dataMots avec dataMotsComplet
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	
	//----------------------------------------------------------------------------------
	// Acces aux attributs
	public File getVersion() {
		return version;
	}

	public void setVersion(File version) {
		this.version = version;
	}

	//----------------------------------------------------------------------------------
	// Procédures nécessaires à la récupération des données
	
	/**
	 * Retourne la date de la version selectionnee par l'utilisateur
	 * @param La version selectionnee
	 */
	public String getDateVersion(File file){
		
		String str = "";
		
		if(file.getName().matches("[0-9]{13}")){
		
			// On converti le timestamp
			Date currentDate = new Date(Long.parseLong(file.getName()));
			int heure = currentDate.getHours();
			int minutes = currentDate.getMinutes();
			int secondes = currentDate.getSeconds();
						
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE);
			str = df.format(currentDate);
			str += " - "+heure+"h"+minutes+"m"+secondes+"s";
		}
		return str;
	}
	
	/**
	 * Cette méthode nous donne la liste de tous les fichiers inclus dans le fichier file
	 * @param le fichier dont on veut connaitre tous les fils
	 */
	public void listerFils(File file){
		for(File fils : file.listFiles(this.filtre)){
			if(fils.isDirectory()){
				listerFils(fils);
			}else{
				lesFichiersEnregistres.add(fils);
			}
		}
	}


	//------------------
	// Tests
	//------------------
	public static void main(String[] args){
		
		// Fichier de test
		File test = new File("C:/LMB2/Site01/1262982386294/");
		
		// Creation du modele
		Statistiques stats = new Statistiques(test);
	}
}
