/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.net.URL;
import java.util.*;
import org.htmlparser.beans.LinkBean;

public class MyLinkExtractor {

	//------------------
	// Attributs
	//------------------
	private String nomFichier;
	
	private URL[] lesURL;
	private Hashtable<String, Integer> tableUrl;
	//------------------
	// Constructeur
	//------------------
	public MyLinkExtractor(String nomFichier){
		this.nomFichier = nomFichier;
		
		tableUrl = new Hashtable<String,Integer>();
		lesURL = extractLinks();
		
		analyserURL(lesURL);
		
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	public Hashtable<String, Integer> getTableUrl() {
		return tableUrl;
	}

	public void setTableUrl(Hashtable<String, Integer> tableUrl) {
		this.tableUrl = tableUrl;
	}
	
	/**
	 * Cette méthode extrait tous les links dans le tableau d'URL
	 * @return toutes les URL contenues dans la page
	 */
	public URL[] extractLinks(){
		LinkBean lb;
		lb = new LinkBean();
		lb.setURL(this.nomFichier);
		
		return (lb.getLinks());
	}
	
    /**
     * Cette méthode permet de compter les occurrences d'URL dans un site
     * @param  urls est la liste de toutes les URLs de la page
     */
    public void analyserURL(URL[] urls){
    	
    	for(int i = 0; i < urls.length ; i++){
    		if(tableUrl.containsKey(urls[i])){
    			
    			// On incremente le nombre d'occurrences
	           	 Integer old_valeur = tableUrl.get(urls[i]);
	           	 int a = old_valeur.intValue() + 1 ;
	           	 Integer new_valeur = new Integer(a);
	           	 
	           	 //dataMots.remove(current); Normalement Hashtable ecrase 
	           	tableUrl.put(urls[i].getPath(),new_valeur);
           	 
    		}else{
    			tableUrl.put(urls[i].getPath(), Integer.valueOf(1));
    		}
    	}
    }

}
