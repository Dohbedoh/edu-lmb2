package Aspirateur;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;



public class TestAspirateur{

	NodeList list;
	Parser parser;
	
	/** Préfixe de l'URL WEB donnée (généralement le dossier parent de "index.html" par exemple*/
	private String urlSource;
	
	/** Préfixe de l'URL LOCAL donnée (le dossier où l'on sauvegarde*/
	private String urlLocal;
	
	/** Liste des images à copier */
	private ArrayList<String> images;

	/** Liste des css à copier */
	private ArrayList<String> css;
	
	/** Liste des pages à copier */
	private ArrayList<String> pages;
	
	/** Liste des ressources déjà copiées */
	private ArrayList<String> urlCopied;
	
	
	public TestAspirateur(String url){
		
		images = new ArrayList<String>();
		pages = new ArrayList<String>();
		css = new ArrayList<String>();
		urlCopied = new ArrayList<String>();
		urlLocal = "C:";
		System.out.println("URL LOCAL : "+ urlLocal);
		setSource(url);
		System.out.println("URL SOURCE : " + urlSource);
		parser = new Parser();
		PrototypicalNodeFactory factory;
		factory = new PrototypicalNodeFactory ();
		/*On définit les Tag qui nous intéresse*/
		factory.registerTag (new LocalLinkTag());
		factory.registerTag (new LocalImageTag());
		factory.registerTag (new LocalFrameTag());
		parser.setNodeFactory (factory);
		launchProcess();
	}
	
	
	/**
	 * Procédure qui extrait le Préfixe(chemin absolu) qui sera utilisé
	 * @param url : l'URL donnée par l'utilisateur
	 */
	public void setSource(String url){
		if(url.endsWith("/")){
			url = url.substring(0,url.length()-1);
		}else{
			if(url.endsWith(".html")){
				url = url.substring(0,url.lastIndexOf("/"));
			}
		}
		urlSource = url;
		pages.add(toRelativeLink(url));
	}
	
	
    private boolean isHtml (String link) throws ParserException{
	    URL url;
	    URLConnection connection;
	    String type;
	    boolean ret;
	    ret = false;
	    
	    try
	    {
	        url = new URL (link);
	        connection = url.openConnection ();
	        type = connection.getContentType ();
	        if (type == null)
	            ret = false;
	        else
	            ret = type.startsWith ("text/html");
	    }
	    catch (Exception e)
	    {
	        throw new ParserException ("URL " + link + " has a problem", e);
	    }
	    return (ret);
	}
	
	
	/**
	 * Fonction qui retourne si la ressource sera capturée
	 * @param link : chemin absolue de la ressource
	 * @return
	 */
    private boolean isToBeCaptured(String link)
    {
        return ( link.toLowerCase ().startsWith (urlSource.toLowerCase ())
            && (-1 == link.indexOf ('?'))
            && (-1 == link.indexOf ('#'))
            && (-1 == link.indexOf('%')));
    }
	
    /**
     * Fonction qui créé une URL relative à partir de l'URL du lien en fonction de la source
     * @param link : l'URL du lien
     * @return
     */
    private String toRelativeLink(String link){
    	if (link.equals (urlSource) || (!urlSource.endsWith ("/") && link.equals (urlSource + "/"))){
            return "";
        }
        else{ 
        	if (link.startsWith (urlSource) && (link.length () > urlSource.length ())){
        		return link.substring (urlSource.length () + 1);
        	}
        	else{
        		return link;
        	}
        }
    }
    
    /**
     * Fonction qui créé une URL relative de la ressource à partir de l'URL du lien et 
     * de l'URL de la page où se trouve le lien
     * @param link : l'URL du lien
     * @param current : l'URL de la page d'où vient la ressource
     * @return
     */
    private String makeLocalLink (String link, String current)
    {
        int i;
        int j;
        String ret;

        ret = toRelativeLink(link);
        
        
        if ((null != current) && link.startsWith (urlSource)&& (current.length () > urlSource.length ()))
        {
        	current = current.substring (urlSource.length () + 1);
            i = 0;
            while ((j = current.indexOf ('/', i)) != -1)
            {
                ret = "../" + ret;
                i = j + 1;
            }
        }
        return (ret);
    }
    
    
    /**
     * Procédure principale :
     * Au départ, la taille de pages est de 1 : c'est le lien donnée par l'utilisateur
     * Tant qu'il y a des pages (des url trouvées), on donne l'URL de la première page 
     * de la liste au Parser et on parse.
     * On lance ensuite la fonction 'copy' qui va effectuer les copies des ressources
     * incluses dans la pages (images, css, etc...)
     */
    public void launchProcess(){
    	while(pages.size()>0){
    		try {
    			
    			System.out.println("\nCurrent Page : " + urlSource+"/"+pages.get(0));
				parser.setURL(urlSource+ "/"+ pages.get(0));
				list = new NodeList();
				parser.reset();
		        
				for(NodeIterator it = parser.elements(); it.hasMoreNodes();){
					list.add(it.nextNode());
				}

				/*afficherCopied();
    			afficherImages();
    			afficherPages();*/

    			copy(pages.remove(0));
				/*System.out.println(list.toHtml());*/
				
			} catch (ParserException e) {
				e.printStackTrace();
				System.out.println("Exception in : " + parser.getURL() + "\n");
				pages.remove(0);
			}
    	}
    	afficherCopied();
    }
    
    
    /**
     * Procédure que copie la page HTML d'URL 'relativeURL'  et l'enregistre sous le
     * lien local correspondant
     * Elle lance également la copie de toutes les images contenues dans cette page
     * @param relativeURL
     */
    public void copy(String relativeURL){
    	System.out.println("\tcapture : \"" + urlSource+ "/"+  relativeURL + "\"  dans  \"" +
    			urlLocal + "/" + relativeURL +"\"");
    	while(images.size() != 0){
    		copyImage(images.remove(0));
    	}
    	urlCopied.add(urlSource+ "/"+  relativeURL);
    }
    
    /**
     * Procédure que copie l'image d'URL 'relativeURL' et l'enregistre sous le lien 
     * local correspondant
     * Elle lance également la copie de toutes les images contenues dans cette page
     * @param relativeURL
     */
    public void copyImage(String relativeURL){
    	System.out.println("\tcapture : \"" + urlSource+ "/"+  relativeURL + "\"  dans  \"" +
    			urlLocal + "/" + relativeURL +"\"");
    	urlCopied.add(urlSource+ "/"+  relativeURL);
    }
    
    public void afficherCopied(){
    	System.out.println("\n\tURL déjà copiées!");
    	Iterator<String> it = urlCopied.iterator();
    	while(it.hasNext()){
    		System.out.println("\t" + it.next());
    	}
    	System.out.println("\t------------------------");
    }
    
    
    public void afficherImages(){
    	System.out.println("\n\tImages à copier!");
    	Iterator<String> it = images.iterator();
    	while(it.hasNext()){
    		System.out.println("\t" + urlSource+ "/" + it.next());
    	}
    	System.out.println("\t------------------------");
    }
    
    
    public void afficherPages(){
    	System.out.println("\n\tPages à copier!");
    	Iterator<String> it = pages.iterator();
    	while(it.hasNext()){
    		System.out.println("\t" + urlSource+ "/" + it.next());
    	}
    	System.out.println("\t------------------------");
    }
    
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un tag de type
	 * 'img' est rencontré
	 * @author Stolen_Flame_57
	 */
	class LocalImageTag extends ImageTag{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -4246051430371965999L;

		@Override
		public void doSemanticAction() throws ParserException {
			String image = getImageURL();
			if(isToBeCaptured(image)){
				if(!images.contains(toRelativeLink(image)) && !urlCopied.contains(image)){
					System.out.println("\n\t----------new Image----------");
					System.out.println("\tImage URL : " + image);
					images.add(toRelativeLink(image));
					image = makeLocalLink (image, parser.getLexer ().getPage ().getUrl ());
					setImageURL(image);
					System.out.println("\t----------------------------\n");
				}
			}
		}	
	}
	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un tag de type
	 * 'href' est rencontré
	 * @author Stolen_Flame_57
	 */
	class LocalLinkTag extends LinkTag{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			String link = getLink();
			if(isToBeCaptured(link)){
				if(!pages.contains(toRelativeLink(link)) && !urlCopied.contains(link)){
					System.out.println("\n\t----------new Page----------");
					System.out.println("\tLink URL : " + link);
					pages.add(toRelativeLink(link));
					link = makeLocalLink (link, parser.getLexer ().getPage ().getUrl ());
					setLink(link);
					System.out.println("\t----------------------------\n");
				}
			}
		}
	}

	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un tag de type
	 * 'href' est rencontré
	 * @author Stolen_Flame_57
	 */
	class LocalFrameTag extends FrameTag{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			String link = getFrameLocation();
			if(isToBeCaptured(link)){
				if(isHtml(link)){
					if(!pages.contains(toRelativeLink(link)) && !urlCopied.contains(link)){
						System.out.println("\n\t----------new Page----------");
						System.out.println("\tLink URL : " + link);
						pages.add(toRelativeLink(link));
					}
				}else{
					if(!images.contains(toRelativeLink(link)) && !urlCopied.contains(link)){
						System.out.println("\n\t----------new Image----------");
						System.out.println("\tImage URL : " + link);
						images.add(toRelativeLink(link));
					}
				}
				link = makeLocalLink (link, parser.getLexer ().getPage ().getUrl ());
				setFrameLocation(link);
				System.out.println("\t----------------------------\n");
			}
		}
	}
	
	public static void main (String[] args){
		TestAspirateur test = new TestAspirateur("http://htmlparser.sourceforge.net/");
	}
}
