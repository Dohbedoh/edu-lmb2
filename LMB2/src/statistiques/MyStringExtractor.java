/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.util.*;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;

public class MyStringExtractor {

	//------------------
	// Attributs
	//------------------
	private String nomFichier;
	private String contenuFichier;
	
	private Hashtable<String, Integer> dataMots;
	
	//------------------
	// Constructeur
	//------------------
	public MyStringExtractor(String nomFichier){
		this.nomFichier = nomFichier;
		
		dataMots = new Hashtable<String, Integer>();
		try {
			
			// On extrait le texte de la page
			contenuFichier = extractStrings(false);
			
			// On analyse le texte extrait de la page
			analyserTexte(contenuFichier);
		} catch (ParserException e) {System.out.println("Fichier inexistant");}
		
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	public Hashtable<String, Integer> getDataMots() {
		return dataMots;
	}

	public void setDataMots(Hashtable<String, Integer> dataMots) {
		this.dataMots = dataMots;
	}
	
	/**
	 * Cette méthode extrait le texte de nomFichier
	 * @param Si oui ou non on veut recuperer les liens hypertextes
	 */
    public String extractStrings (boolean links) throws ParserException {
	    StringBean sb;
	    sb = new StringBean();
	    sb.setLinks (links);
	    sb.setURL (this.nomFichier);
	    return (sb.getStrings ());
    }

    /**
     * Cette méthode permet de compter les occurrences de mots dans un site
     * @param  big est la grosse chaine de caractere contenant tous les mots du texte
     */
    public void analyserTexte(String big){
    	
    	 StringTokenizer st = new StringTokenizer(big);
    	 while (st.hasMoreTokens()) {
    		 String current = st.nextToken();
    		 
    		 // Definir les contraintes sur le token
    		 /**
    		  * A FAIRE pour les caracteres suivants : ! ? < > , ; .
    		  * + lower case
    		  */
    		 
             if(dataMots.containsKey(current)){
            	
            	 // On incremente le nombre d'occurrences
            	 Integer old_valeur = dataMots.get(current);
            	 int a = old_valeur.intValue() + 1 ;
            	 Integer new_valeur = new Integer(a);
            	 
            	 //dataMots.remove(current); Normalement Hashtable ecrase 
            	 dataMots.put(current,new_valeur);
            	 
             }else{
            	 // On ajoute
            	 dataMots.put(current, new Integer(1));
             }
         }
    	
    	 //System.out.println(dataMots.toString());
    }
	
}
