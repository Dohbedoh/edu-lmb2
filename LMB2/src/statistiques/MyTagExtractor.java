/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

public class MyTagExtractor {

	//------------------
	// Attributs
	//------------------
	private String nomFichier;
	
	private TagNameFilter meta;
	private TagNameFilter h1;
	
	private NodeFilter[] filters;
	
	private NodeList lesNoeuds;
	//------------------
	// Constructeurs
	//------------------
	public MyTagExtractor(String nomFichier){
		this.nomFichier = nomFichier;
		
		meta = new TagNameFilter("META");
		h1 = new TagNameFilter("H1");
		filters = new TagNameFilter[]{ meta };
		extractTag();
		
	}//cons-1
	
	public NodeList getLesNoeuds() {
		return lesNoeuds;
	}

	public void setLesNoeuds(NodeList lesNoeuds) {
		this.lesNoeuds = lesNoeuds;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void extractTag(){
		 FilterBean fb = new FilterBean();
	     fb.setFilters(filters);
	     fb.setURL (this.nomFichier);
	     
	     /*
	     for(int i= 0 ; i < fb.getNodes().size(); i++){
	    	 System.out.println (fb.getNodes().elementAt(i).toHtml());
	     }
	     */
	     
	     System.out.println(fb.getNodes().toHtml());
	}
	

	public static void main(String[] args){
		MyTagExtractor le = new MyTagExtractor("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470/index.html");
	}
}
