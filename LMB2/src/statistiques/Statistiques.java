/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

import Aspirateur.Meta;

public class Statistiques extends Observable {

	//------------------
	// Attributs
	//------------------
	
	//------------------
	// Attributs relatifs à la version sélectionnée
	File version;
	String date;
	
	InvisibleFileFilter filtre;	
	ArrayList<File> lesFichiersEnregistres;
	
	private int traitement = 0;
	private int length;
	//------------------
	// Attributs relatifs aux calculs des statistiques
	private ArrayList<File> dataHTML;
	private ArrayList<File> dataImages;
	private ArrayList<File> dataCSS;
	private ArrayList<File> dataJS;
	
	private Meta meta;
	private Hashtable<String, Integer> dataMotsComplet;
	private Hashtable<String, Integer> dataLinksComplet;
	
	//------------------
	// Divers
	private String extensionsImages[] = {".gif",".jpg",".jpeg",".png"};
	
	//------------------
	// Constructeurs
	//------------------
	public Statistiques(File version){
		this.version = version;
		
		length = 0;
		
		// Creation des elements necessaires
		filtre = new InvisibleFileFilter();
		lesFichiersEnregistres = new ArrayList<File>();
		
		dataHTML = new ArrayList<File>();
		dataImages = new ArrayList<File>();
		dataCSS = new ArrayList<File>();
		dataJS = new ArrayList<File>();
		
		dataMotsComplet = new Hashtable<String, Integer>();
		dataLinksComplet = new Hashtable<String, Integer>();
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
		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}

	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date){
		this.date = date;
		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
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
		long temp = length/(1024*1024);
		if(temp>=1){
			return temp+" Mo";
		}else{
			temp = length/1024;
		}
			if(temp>=1){
				return temp + " Ko";
			}else{
				return length + " octets";
			}
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers inclus dans la version
	 */
	public ArrayList<File> getLesFichiersEnregistres() {
		return lesFichiersEnregistres;
	}

	/**
	 * retourne les métadonnées
	 * @return
	 */
	public Meta getMetaData(){
		return meta;
	}
	
	public Meta loadMetaData(){
		try{
			this.meta = Meta.deserializer(version.getAbsolutePath()+"/meta.dat");
			return meta;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers HTML inclus dans la version
	 */
	public ArrayList<File> getDataHTML() {
		return dataHTML;
	}
	
	/**
	 * Cette méthode retourne une Hashtable contenant toutes les occurrences du texte + le nombre contenues dans les fichiers de la version
	 */
	public Hashtable<String, Integer> getDataMotsComplet() {
		return dataMotsComplet;
	}

	/**
	 * Cette méthode retourne une Hashtable contenant toutes les occurrences des Links + le nombre contenues dans les fichiers de la version
	 */
	public Hashtable<String, Integer> getDataLinksComplet() {
		return dataLinksComplet;
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers images de la version
	 */
	public ArrayList<File> getDataImages(){
		return dataImages;
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers css de la version
	 */
	public ArrayList<File> getDataCSS(){
		return dataCSS;
	}
	
	/**
	 * Cette méthode retourne une ArrayList contenant tous les fichiers javascript de la version
	 */
	public ArrayList<File> getDataJS(){
		return dataJS;
	}
	
	public int getTraitement(){
		return this.traitement;
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
	
	/**
	 * Cette méthode permet des recuperer les paths de toutes les versions soeurs a la version courante
	 * @return les versions soeurs de la version courante
	 */
	public ArrayList<File> getOthersVersion(){
		ArrayList<File> retour = new ArrayList<File>();
		File pere = version.getParentFile();
		
		for(File frere : pere.listFiles()){
			if(frere.getAbsolutePath().compareTo(version.getAbsolutePath()) != 0){
				if(frere.isDirectory()){
					if(frere.getName().matches("[0-9]{13}")){
						retour.add(frere);
						//System.out.println(frere.getName());
					}
				}
			}
		}
		return retour;
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
				if(fils.getName().compareTo("meta.dat")!=0){
					System.err.println(fils.getName());
					length += fils.length();
				}
			}
		}
	}

	/**
	 * Cette méthode permet de trier l'ensemble des fichiers enregistrées
	 */
	public void processSortFiles(){
		for(int i = 0 ; i < lesFichiersEnregistres.size();i++){
			String current = lesFichiersEnregistres.get(i).getName();
			traitement = i;
			// HTML
			if(current.endsWith(".html") || current.endsWith(".htm") || current.contains(".php?=") ){
				dataHTML.add(lesFichiersEnregistres.get(i));
			}
			
			// Gestion Images
			for(int j = 0; j < extensionsImages.length ; j++){
				if(current.endsWith(extensionsImages[j])) {
					dataImages.add(lesFichiersEnregistres.get(i));
				}
			}
			
			// CSS
			if(current.endsWith(".css"))
				dataCSS.add(lesFichiersEnregistres.get(i));
			
			// JS
			if(current.endsWith(".js"))
				dataJS.add(lesFichiersEnregistres.get(i));
		}
	}
	
	/**
	 * Cette méthode permet de recuperer dataMotsComplets
	 */
	public void processDataMots(){
		
		
		for(int i = 0 ; i < dataHTML.size();i++){
			//String current = dataHTML.get(i).getName();
			//System.out.println("Traitement dataMots de : "+current);
				
			// On récupère les données sur la page current
			MyStringExtractor stringExtractor = new MyStringExtractor(dataHTML.get(i).getPath());
			Hashtable<String, Integer> new_data = stringExtractor.getDataMots();
				
			// On fusionne avec les données déjà existantes
			merge(dataMotsComplet, new_data);
		}
	}
	
	/**
	 * Cette méthode permet de recuperer dataLinks
	 */
	public void processDataLinks(){
		
		
		for(int i = 0 ; i < dataHTML.size();i++){
			//String current = dataHTML.get(i).getName();
			//System.out.println("Traitement dataLinks de : "+current);
			
			// On récupère les données sur la page current
			MyLinkExtractor urlExtractor = new MyLinkExtractor(dataHTML.get(i).getPath());
			Hashtable<String,Integer> new_data = urlExtractor.getTableUrl();
			
			// On fusionne avec les données déjà existantes
			merge(dataLinksComplet, new_data);
		}
	}
	
	
	
	
	
	/**
	 * Cette méthode lance toutes les statistiques
	 */
	public void init(){
		
		this.date = getDateVersion(version);
		// Recuperation de tous les fichiers fils
		listerFils(version);
		loadMetaData();
		setChanged();
		notifyObservers();
		// Lancement du process de recuperation des statistiques
			// Tri des fichiers - images
			processSortFiles();
		
			// Recuperation des dataMotsComplet
			//processDataMots();
		
			// Recuperation des dataLinkComplet
			//processDataLinks();
			
		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}
	
	//------------------
	// Tests
	//------------------
	public static void main(String[] args){
		
		// Fichier de test
		File test = new File("/Users/renaudmathieu/Desktop/LMB2/Site01/1263824938003/");
		
		// Creation du modele
		Statistiques stats = new Statistiques(test);
		stats.init();
		
		//stats.getOthersVersion();
		
		//System.out.println(stats.getDataHTML());
		//System.out.println(stats.getDataCSS());
		//System.out.println(stats.getDataJS());
		
		//System.out.println(stats.getDataMotsComplet());
		//System.out.println(stats.getDataLinksComplet());
		
		
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
