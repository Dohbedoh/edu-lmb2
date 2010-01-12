/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

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
	Hashtable<String, Integer> dataMots;

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
		/*
		for(int i = 0; i < lesFichiersEnregistres.size();i++){
			System.out.println(lesFichiersEnregistres.get(i).getPath());
		}
		*/
		
		// Lancement du process de recuperation des statistiques
		lireFichier(lesFichiersEnregistres.get(0));
		
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

	public Hashtable<String, Integer> getDataMots() {
		return dataMots;
	}

	public void setDataMots(Hashtable<String, Integer> dataMots) {
		this.dataMots = dataMots;
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

	/**
	 * Cette methode permet de lire un fichier
	 * @param le fichier que l'on veut lire
	 */
	public void lireFichier(File file){
		
		/*
		 * A FAIRE
		 *
		try {
			
			// Creation d'un StreamTokenizer pour parser les informations
			StreamTokenizer tokenizer = new StreamTokenizer (new FileReader(file));
			
			// Options du tokenizer
			//tokenizer.eolIsSignificant (true);
			while (StreamTokenizer.TT_EOF != tokenizer.nextToken()){
				System.out.print (tokenizer.sval + " ");
			}
			
		} 
		catch (FileNotFoundException e) {System.out.println(e.getMessage());} 
		catch (IOException e) {System.out.println(e.getMessage());}
		 * 
		 * A FAIRE
		 */
	}
	
	//------------------
	// Tests
	//------------------
	public static void main(String[] args){
		
		// Fichier de test
		File test = new File("/users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470/");
		
		// Creation du modele
		Statistiques stats = new Statistiques(test);
	}
}
