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
	private ArrayList<File> dataImages;

	//------------------
	// Divers
	private String extensionsImages[] = {".gif",".jpg",".jpeg",".png"};
	
	//------------------
	// Constructeurs
	//------------------
	public Statistiques(File version){
		this.version = version;
		
		// Creation des elements necessaires
		filtre = new InvisibleFileFilter();
		lesFichiersEnregistres = new ArrayList<File>();
		
		dataMotsComplet = new Hashtable<String, Integer>();
		dataImages = new ArrayList<File>();
		
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
		
		// Recuperation des dataMots
		processDataMots();
		
		// Tri des fichiers - images
		processSortFiles();
		
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

	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	/**
	 * Cette méthode retourne le nom du dossier père de la version 
	 * ie. le Nom du site
	 */
	public String getNomSite(){
		return this.version.getParentFile().getName();
	}
	
	/**
	 * Donne le poids de la capture faite dans le répertoire version en bytes.
	 */
	public String getLength(){
		return this.version.length()+"";
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers inclus dans la version
	 */
	public ArrayList<File> getLesFichiersEnregistres() {
		return lesFichiersEnregistres;
	}

	/**
	 * Cette méthode retourne une Hashtable contenant toutes les occurrences + le nombre contenues dans les fichiers de la version
	 */
	public Hashtable<String, Integer> getDataMotsComplet() {
		return dataMotsComplet;
	}

	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers images de la version
	 */
	public ArrayList<File> getDataImages(){
		return dataImages;
	}
	//----------------------------------------------------------------------------------
	// Procédures de traitement
	
	/**
	 * Cette méthode permet la fusion de deux hashtables
	 * ie. mets dans tab1 le contenu de tab2
	 */
	public void merge(Hashtable<String, Integer> tab1, Hashtable<String, Integer> tab2){
		
		Enumeration<String> e = tab2.keys();
		while (e.hasMoreElements()) {
			String str = (String)e.nextElement();
			
			if(tab1.containsKey(str)){
				int old_value = tab1.get(str).intValue();
				int new_value = old_value + tab2.get(str).intValue();
				tab1.put(str, Integer.valueOf(new_value));
			}else{
				tab1.put(str, tab2.get(str));
			}
			
		}
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
	 * Cette méthode permet d'instancier dataMotsComplets
	 */
	public void processDataMots(){
		for(int i = 0 ; i < lesFichiersEnregistres.size();i++){
			String current = lesFichiersEnregistres.get(i).getName();
			
			/**
			 * 	ATTENTION : vérifier la conditionnelle
			 */
			if(current.endsWith(".html") || current.endsWith(".htm") || current.contains(".php?=") ){
				
				System.out.println("Traitement de : "+current);
				
				// On récupère les données sur la page current
				MyStringExtractor stringExtractor = new MyStringExtractor(lesFichiersEnregistres.get(i).getPath());
				Hashtable<String, Integer> new_data = stringExtractor.getDataMots();
				
				// On fusionne avec les données déjà existantes
				merge(dataMotsComplet, new_data);
			}
		}
	}
	
	/**
	 * Cette méthode permet de trier l'ensemble des fichiers enregistrées
	 */
	public void processSortFiles(){
		for(int i = 0 ; i < lesFichiersEnregistres.size();i++){
			String current = lesFichiersEnregistres.get(i).getName();
		
			// Gestion Images
			for(int j = 0; j < extensionsImages.length ; j++){
				if(current.endsWith(extensionsImages[j])) {
					dataImages.add(lesFichiersEnregistres.get(i));
				}
			}
			
			// Autres
			/**
			 * A FAIRE
			 */
		}
	}
	//------------------
	// Tests
	//------------------
	public static void main(String[] args){
		
		// Fichier de test
		File test = new File("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470");
		
		// Creation du modele
		Statistiques stats = new Statistiques(test);
		
		// Test de la fonction merge
		/*Hashtable<String, Integer> tab1 = new Hashtable<String, Integer>();
		Hashtable<String, Integer> tab2 = new Hashtable<String, Integer>();
		
		tab1.put("a", Integer.valueOf(1));
		tab1.put("b", Integer.valueOf(2));
		tab1.put("c", Integer.valueOf(3));
		
		tab2.put("c", Integer.valueOf(3));
		tab2.put("d", Integer.valueOf(4));
		
		stats.merge(tab1,tab2);
		
		System.out.println(tab1);
		*/
	}
}
