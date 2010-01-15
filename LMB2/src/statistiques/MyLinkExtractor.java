/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.net.URL;
import java.util.*;

import org.htmlparser.beans.LinkBean;
import org.htmlparser.util.ParserException;

public class MyLinkExtractor {

	//------------------
	// Attributs
	//------------------
	private String nomFichier;
	
	private URL[] lesURL;
	private Hashtable<URL, Integer> tableUrl;
	//------------------
	// Constructeur
	//------------------
	public MyLinkExtractor(String nomFichier){
		this.nomFichier = nomFichier;
		
		tableUrl = new Hashtable<URL,Integer>();
		lesURL = extractLinks();
		
		analyserURL(lesURL);
		
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	public Hashtable<URL, Integer> getTableUrl() {
		return tableUrl;
	}

	public void setTableUrl(Hashtable<URL, Integer> tableUrl) {
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
	           	tableUrl.put(urls[i],new_valeur);
           	 
    		}else{
    			tableUrl.put(urls[i], Integer.valueOf(1));
    		}
    	}
    }

    /*
	public static void main(String[] args){
		MyLinkExtractor le = new MyLinkExtractor("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470/index.html");
		System.out.println(le.getTableUrl());
	}
	*/
}
