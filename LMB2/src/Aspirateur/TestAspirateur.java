package Aspirateur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.util.EncodingChangeException;
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
	
	/** Liste des erreurs */
	private ArrayList<ParserException> urlErrors;
	
	
	public TestAspirateur(){

		images = new ArrayList<String>();
		pages = new ArrayList<String>();
		css = new ArrayList<String>();
		urlCopied = new ArrayList<String>();
		urlErrors = new ArrayList<ParserException>();
		parser = new Parser();
		PrototypicalNodeFactory factory;
		factory = new PrototypicalNodeFactory ();
		/*On définit les Tag qui nous intéresse*/
		factory.registerTag (new LocalLinkTag());
		factory.registerTag (new LocalImageTag());
		factory.registerTag (new LocalFrameTag());
		factory.registerTag (new CSSTag());
		parser.setNodeFactory (factory);
	}
	
	
	public void setLocal(String URL){
		urlLocal = URL;
		System.out.println("URL LOCAL : "+ urlLocal);
	}
	
	
	public String getLocal(){
		return urlLocal;
	}
	
	/**
	 * Procédure qui extrait le Préfixe(chemin absolu du dossier parent
	 * @param url : l'URL
	 */
	public String getSource(String url){
		if(url.endsWith("/")){
			url = url.substring(0,url.length()-1);
		}else{
			if(url.endsWith(".html")){
				url = url.substring(0,url.lastIndexOf("/"));
			}
		}
		return url;
	}
	
	
	/**
	 * Procédure qui modifie le Préfixe qui sera utilisé
	 * @param url : l'URL donnée par l'utilisateur
	 */
	public void setSource(String url){
		urlSource = getSource(url);
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
        return ( link.toLowerCase ().startsWith (urlSource.toLowerCase ()));
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
    public void launchProcess(String url){
		setSource(url);
    	while(pages.size()>0){
    		try {

    			System.out.println("\nCurrent Page : " + urlSource+"/"+pages.get(0));
				parser.setURL(urlSource+ "/"+ pages.get(0));
				list = new NodeList();
				parser.reset();
		        
				try{
					for(NodeIterator it = parser.elements(); it.hasMoreNodes();){
						list.add(it.nextNode());
					}
				}catch(EncodingChangeException e){
					//String encode = e.getMessage().substring(e.getMessage().indexOf("to ")+3, e.getMessage().indexOf(" at "));
					parser.reset();
					list = new NodeList ();
	                for (NodeIterator it = parser.elements (); it.hasMoreNodes (); ){
	                    list.add (it.nextNode ());
	                }
				}

				/*afficherCopied();
    			afficherImages();
    			afficherPages();
    			afficherCSS();*/

    			copyPage(pages.remove(0));
				/*System.out.println(list.toHtml());*/
				
			} catch (ParserException e) {
				urlErrors.add(e);
				e.printStackTrace();
				System.out.println("\nError in : " + parser.getURL() + "\n");
				pages.remove(0);
			}
    	}
    	afficherCopied();
    	afficherErrors();
    }
    
    
    /**
     * Procédure qui lance la copie de la page HTML d'URL 'relativeURL'
     * Elle lance également la copie de toutes les images et fichiers CSS contenus
     * dans cette page
     * @param relativeURL
     */
    public void launchCopy(String URL){
    	setLocal(URL);
    	while(pages.size()!=0){
    		copyPage(pages.remove(0));
    	}
    }
    
    
    /**
     * Procédure qui lance l'enregistrement local de la page HTML d'URL 'relativeURL'
     * Elle lance également l'enregistrement de toutes les images et fichiers CSS contenus
     * dans cette page
     * @param relativeURL
     */
    public void copyPage(String relativeURL){
    	System.out.println("\tcapture : \"" + urlSource+ "/"+  relativeURL);
    	//copy(relativeURL);
    	while(css.size() != 0){
    		copyCSS(css.remove(0));
    	}
    	while(images.size() != 0){
    		copyImage(images.remove(0));
    	}
    	urlCopied.add(urlSource+ "/"+  relativeURL);
    }
    
    /**
     * Procédure qui lance l'enregistrement copie du fichier CSS d'URL 'relativeURL'
     * @param relativeURL
     */
    public void copyCSS(String relativeURL){
    	System.out.println("\tcapture : \"" + urlSource+ "/"+  relativeURL);
    	//copy(relativeURL);
    	urlCopied.add(urlSource+ "/"+  relativeURL);
    }
    
    /**
     * Procédure qui enregistre l'image d'URL 'relativeURL' et l'enregistre sous le lien 
     * local correspondant
     * @param relativeURL
     */
    public void copyImage(String relativeURL){
    	System.out.println("\tcapture : \"" + urlSource+ "/"+  relativeURL);
    	//copy(relativeURL);
    	urlCopied.add(urlSource+ "/"+  relativeURL);
    }
    
    /**
     * Procédure qui enregistre le contenu d'un fichier et l'enregistre sous le lien 
     * local correspondant
     * @param URL
     */
    public void copy(String URL){
    	File file;
		if(URL.equals("")){
			file = new File(urlLocal + "/index.html");
		}else{
			file = new File(urlLocal + "/" + URL);
		}
    	File dir = file.getParentFile ();
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	try
        {
    		int read;
    		InputStream in;
    		FileOutputStream out;
    		URL source = new URL(urlSource+ "/"+  URL);
    		byte[] data = new byte [1024];
            try
            {
                in = source.openStream ();
                try
                {
                    out = new FileOutputStream (file);
                    try
                    {
                        while (-1 != (read = in.read (data, 0, data.length)))
                            out.write (data, 0, read);
                    }
                    finally
                    {
                        out.close ();
                    }
                }
                catch (FileNotFoundException fnfe)
                {
                    fnfe.printStackTrace ();
                }
                finally
                {
                    in.close ();
                }
            }
            catch (FileNotFoundException fnfe)
            {
                System.err.println ("broken link " + fnfe.getMessage () + " ignored");
            }
        }
        catch (MalformedURLException murle)
        {
            murle.printStackTrace ();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace ();
        }
    }
    
    
    /**
     * Affichage des fichiers déjà copiés
     */
    public void afficherCopied(){
    	if(urlCopied.size()!=0){
    		System.out.println("\n\tURL déjà copiées!");
	    	System.out.println("\t------------------------------------------------");
	    	Iterator<String> it = urlCopied.iterator();
	    	while(it.hasNext()){
	    		System.out.println("\t" + it.next());
	    	}
	    	System.out.println("\t------------------------------------------------");
    	}
    }
    
    /**
     * Affichage des fichiers Images à copier
     */
    public void afficherImages(){
    	if(images.size()!=0){
	    	System.out.println("\n\tImages à copier!");
	    	System.out.println("\t------------------------------------------------");
	    	Iterator<String> it = images.iterator();
	    	while(it.hasNext()){
	    		System.out.println("\t" + urlSource+ "/" + it.next());
	    	}
	    	System.out.println("\t------------------------------------------------");
    	}
    }
    
    /**
     * Affichage des pages HTML à copier
     */
    public void afficherPages(){
    	if(pages.size()!=0){
	    	System.out.println("\n\tPages à copier!");
	    	System.out.println("\t------------------------------------------------");
	    	Iterator<String> it = pages.iterator();
	    	while(it.hasNext()){
	    		System.out.println("\t" + urlSource+ "/" + it.next());
	    	}
	    	System.out.println("\t------------------------------------------------");
    	}
    }
    
    /**
     * Affichage des fichiers CSS à copier
     */
    public void afficherCSS(){
    	if(css.size()!=0){
	    	System.out.println("\n\tCSS à copier!");
	    	System.out.println("\t------------------------------------------------");
	    	Iterator<String> it = css.iterator();
	    	while(it.hasNext()){
	    		System.out.println("\t" + urlSource+ "/" + it.next());
	    	}
	    	System.out.println("\t------------------------------------------------");
    	}
    }
    
    /** 
     * Affichage des erreurs 
     */
    public void afficherErrors(){
    	if(urlErrors.size()!=0){
	    	System.out.println("\n\tURL Errors!");
	    	System.out.println("\t------------------------------------------------");
	    	Iterator<ParserException> it = urlErrors.iterator();
	    	while(it.hasNext()){
	    		System.out.println("\n\t");
	    		System.out.println("\t"+ it.next().getLocalizedMessage());
	    	}
	    	System.out.println("\t------------------------------------------------");
    	}
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
					//System.out.println("\n\t----------new Image----------");
					//System.out.println("\tImage URL : " + image);
					images.add(toRelativeLink(image));
					image = makeLocalLink (image, parser.getLexer ().getPage ().getUrl ());
					setImageURL(image);
					//System.out.println("\t----------------------------\n");
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
					//System.out.println("\n\t----------new Page----------");
					//System.out.println("\tLink URL : " + link);
					pages.add(toRelativeLink(link));
					link = makeLocalLink (link, parser.getLexer ().getPage ().getUrl ());
					setLink(link);
					//System.out.println("\t----------------------------\n");
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
						//System.out.println("\n\t----------new Page----------");
						//System.out.println("\tLink URL : " + link);
						pages.add(toRelativeLink(link));
					}
				}else{
					if(!images.contains(toRelativeLink(link)) && !urlCopied.contains(link)){
						//System.out.println("\n\t----------new Image----------");
						//System.out.println("\tImage URL : " + link);
						images.add(toRelativeLink(link));
					}
				}
				link = makeLocalLink (link, parser.getLexer ().getPage ().getUrl ());
				setFrameLocation(link);
				//System.out.println("\t----------------------------\n");
			}
		}
	}
	
	class CSSTag extends org.htmlparser.tags.HeadTag{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2558739946355789992L;
		
		@Override
		public void doSemanticAction() throws ParserException {
			/* le lien css que l'on recherche */
			String cssLink = "";
			/* Le Tag "link" dans lequel est de trouve l'URL */
			TagNode tagLink = null;
			for(int i=0; i<getChildCount(); i++){
				/* On cherche le tag qui contient la chaîne 'rel="stylesheet"' */
				if(getChild(i)!=null 
						& getChild(i).toString().contains("rel=\"stylesheet\"") 
						& getChild(i).toString().contains(".css")){
					//System.out.println("OK CSS");
					tagLink = ((TagNode)getChild(i));
					int j=0;
					/* On cherche à présent l'attribut contenant l'URL : 'href' */
					while(!cssLink.contains("href")){
						cssLink = tagLink.getAttributesEx().get(j).toString();
						j++;
					}
					/* On récupère seulement le lien */
					cssLink = cssLink.substring(cssLink.indexOf('"')+1);
					cssLink = cssLink.substring(0,cssLink.indexOf('"'));
					String source = getSource(parser.getLexer().getPage().getUrl());
					while(cssLink.contains("../")){
						source = getSource(parser.getLexer().getPage().getUrl());
						source = source.substring(0,source.lastIndexOf("/"));
						cssLink = cssLink.substring(cssLink.lastIndexOf("../")+3);
					}
					/* On ajoute le préfixe */
					cssLink = getSource(source) + "/" + cssLink;
					if(isToBeCaptured(cssLink)){
						if(!css.contains(toRelativeLink(cssLink)) && !urlCopied.contains(cssLink)){
							//System.out.println("\n\t----------new CSS-----------");
							//System.out.println("\tCSS URL : " + cssLink);
							css.add(toRelativeLink(cssLink));
							//System.out.println("\t----------------------------\n");
						}
					}
				}
			}
		}
	}
	
	
	public static void main (String[] args){
		TestAspirateur test = new TestAspirateur();
		test.launchProcess("http://www.renaudmathieu.fr/lmb2/");
		//test.launchCopy("C:/LMB2/Test/");
	}
}
