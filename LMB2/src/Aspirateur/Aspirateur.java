/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package Aspirateur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.EncodingChangeException;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Aspirateur extends Observable {

	// ------------------
	// Attributs
	// ------------------
	private NodeList list;

	private Parser parser;
	
	/**
	 * booléen qui indique que l'on est en train de capturer un site
	 */
	private boolean onCapture;
	
	/**
	 * Préfixe de l'URL WEB donnée (généralement le dossier parent de
	 * "index.html" par exemple
	 */
	private String urlSource;

	/** page courante */
	private String currentPage;
	
	/** Préfixe de l'URL LOCAL donnée (le dossier où l'on sauvegarde */
	private String urlLocal;

	/** Chemin absolu de sauvegarde */
	private String path;

	/** Liste des ressources à copier */
	private ArrayList<String> ressources;

	/** Liste des pages à copier */
	private ArrayList<String> pages;

	/** Liste des ressources déjà copiées */
	private HashSet<String> ressourcesCopied;

	/** Liste des pages déjà copiées */
	private HashSet<String> pagesCopied;

	/** Liste des erreurs */
	private ArrayList<Exception> urlErrors;

	/** Nom du projet */
	private String name;

	/** Nos pool de thread */
	private ThreadPool pagesPool;
	private ThreadPool ressourcesPool;
	
	/** Nos filtres */
	private ArrayList<String> filtres;
	
	/** Liste des urls Filtrées */
	private ArrayList<String> urlFiltred;
	
	/** Liste des extension Filtrées */
	private HashSet<String> extensionsFiltred;
	
	/** Indicateur de profondeur de pages*/
	private int profondeur;

	/** Indicateur de taille max de fichier*/
	private long taillePagesMax;
	
	/** Indicateur de taille max du site*/
	private long tailleSiteMax;
	private long tailleSite;
	
	/** Indicateur de taille max du site*/
	private long tailleRessourcesMax;
	
	/** Booléen permettant de contrôler le parsing */
	private boolean stop,pause;
	
	/** Les Metadonnées */
	private Meta meta;
	
	
	// ------------------
	// Constructeur
	// ------------------
	public Aspirateur() {
		tailleSite = 0;
		taillePagesMax = -1;
		tailleSiteMax = -1;
		tailleRessourcesMax = -1;
		stop = true;
		pause = false;
		ressources = new ArrayList<String>();
		pages = new ArrayList<String>();
		pagesCopied = new HashSet<String>();
		ressourcesCopied = new HashSet<String>();
		urlErrors = new ArrayList<Exception>();
		filtres = new ArrayList<String>();
		urlFiltred = new ArrayList<String>();
		extensionsFiltred = new HashSet<String>();
		meta = new Meta();
		profondeur = 0;
		pagesPool = new ThreadPool(1);
		ressourcesPool = new ThreadPool(2);
		reinitialise();
		urlSource = "";
		urlLocal = "";
		path = "";
		name = "";
	}
	
	/**
	 * Procédure qui rénitialise notre aspirateur
	 */
	public void reinitialise(){
		onCapture = false;
		currentPage = "";
		tailleSite = 0;
		stop = true;
		pause = false;
		ressources.clear();
		pages.clear();
		pagesCopied.clear();
		ressourcesCopied.clear();
		urlErrors.clear();
		urlFiltred.clear();
		extensionsFiltred.clear();
		pagesPool = new ThreadPool(1);
		ressourcesPool = new ThreadPool(2);
		parser = new Parser();
		PrototypicalNodeFactory factory;
		factory = new PrototypicalNodeFactory();
		/* On definit les Tag qui nous interesse */
		factory.registerTag(new LocalAppletTag());
		factory.registerTag(new LocalBaseHrefTag());
		factory.registerTag(new LocalBodyTag());
		factory.registerTag(new LocalBulletListTag());
		factory.registerTag(new LocalBulletTag());
		factory.registerTag(new LocalCompositeTag());
		factory.registerTag(new LocalDefinitionListTag());
		factory.registerTag(new LocalDivTag());
		factory.registerTag(new LocalDocTypeTag());
		factory.registerTag(new LocalFormTag());
		factory.registerTag(new LocalFrameTag());
		factory.registerTag(new LocalFrameSetTag());
		factory.registerTag(new LocalHeadingTag());
		factory.registerTag(new LocalHeadTag());
		factory.registerTag(new LocalHTMLTag());
		factory.registerTag(new LocalImageTag());
		factory.registerTag(new LocalJspTag());
		factory.registerTag(new LocalJSTag());
		factory.registerTag(new LocalLabelTag());
		factory.registerTag(new LocalLinkTag());
		factory.registerTag(new LocalMetaTag());
		factory.registerTag(new LocalObjectTag());
		factory.registerTag(new LocalOptionTag());
		factory.registerTag(new LocalParagraphTag());
		factory.registerTag(new LocalProcessingInstructionTag());
		factory.registerTag(new LocalSelectTag());
		factory.registerTag(new LocalSpanTag());
		factory.registerTag(new LocalStyleTag());
		factory.registerTag(new LocalTableColumnTag());
		factory.registerTag(new LocalTableHeaderTag());
		factory.registerTag(new LocalTableRowTag());
		factory.registerTag(new LocalTableTag());
		factory.registerTag(new LocalTextAreaTag());
		factory.registerTag(new LocalTitleTag());
		parser.setNodeFactory(factory);
	}

	// ------------------
	// Methodes
	// ------------------

	/** Modifieur permettant de stopper le parser */
	public void stop(){
		stop = true;
		ressources.clear();
		pages.clear();
		stopAll();
	}

	/** Modifieur permettant de mettre en pause le parser */
	public void suspend(){
		pause = true;
		suspendAll();
	}

	/** Modifieur permettant de reprendre le parser mis en pause */
	public void reprendre(){
		pause = false;
		resumeAll();
	}
	

	/**
	 * Finir les tâches en cours et arrêter
	 */
	private void joinAll() {
		ressourcesPool.join();
		pagesPool.join();
	}
	
	/**
	 * Fermer les ThreadPool
	 */
	private void stopAll(){
		ressourcesPool.stop();
		pagesPool.stop();
	}
	
	/**
	 * Suspend les tâches courantes
	 */
	private void suspendAll(){
		ressourcesPool.suspend();
		pagesPool.suspend();
	}
	
	/**
	 * Reprendre les tâches suspendues
	 */
	private void resumeAll(){
		ressourcesPool.resume();
		pagesPool.resume();
	}
	
	/**
	 * Modifier le booléen qui indique que l'on capture un site
	 */
	public void setOnCapture(boolean b){
		onCapture = true;
	}
	
	/** 
	 * Modifier la page courante
	 */
	public void setCurrentPage(String current){
		this.currentPage = current;
	}
	
	/**
	 * Importer des filtres dans l'aspirateur
	 * @param filtres
	 */
	public void setFiltres(ArrayList<String> filtres){
		this.filtres.clear();
		this.filtres.addAll(filtres);
	}
	
	/**
	 * Modifier le nb de thread à utiliser pour le traitement des pages
	 */
	public void setNbPagesThread(int nb) {
		pagesPool = new ThreadPool(nb);
	}

	/**
	 * Modifier le nb de thread à utiliser pour le traitement des ressources
	 */
	public void setNbRessourcesThread(int nb) {
		ressourcesPool = new ThreadPool(nb);
	}

	/**
	 * Remplir les metadonnées
	 * @param data
	 */
	public void setMeta(String data){
		meta.setMetaData(data);
	}
	
	
	/**
	 * Modifier le chemin du workspace
	 * 
	 * @param unPath
	 */
	public void setPath(String unPath) {
		this.path = unPath;

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}

	
	/**
	 * Changer la profondeur
	 * <0 pour effectuer l'aspiration sans profondeur
	 * @param prof
	 */
	public void setProfondeur(int prof){
		profondeur = prof;
	}
	
	
	/**
	 * Procedure qui modifie le Prefixe qui sera utilise
	 * 
	 * @param url
	 *            : l'URL donnee par l'utilisateur
	 */
	public void setSource(String url) {
		urlSource = toSource(url);
		System.err.println(urlSource);
		meta.setURL(url);
		pages.add(url);

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}
	/**
	 * Modifier le lien local ou sera enregistre le projet
	 * 
	 * @param URL
	 */
	public void setLocal(String URL) {
		this.urlLocal = URL;

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}

	/**
	 * Modifier le nom du repertoire contenant le site
	 * 
	 * @param str
	 */
	public void setName(String str) {
		name = str;

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Définir la taille maximale du site que l'on veut aspirer
	 * @param taille
	 */
	public void setTailleSiteMax(long taille){
		tailleSiteMax = taille;
	}
	
	/**
	 * Définir la taille maximale des fichiers ressources que l'on veut aspirer
	 * @param taille
	 */
	public void setTailleRessourcesMax(long taille){
		tailleRessourcesMax = taille;
	}
	
	/**
	 * Définir la taille maximale des pages (HTML et PHP) que l'on veut aspirer
	 * @param taille
	 */
	public void setTaillePagesMax(long taille){
		taillePagesMax = taille;
	}
	
	
	/**
	 * Savoir si on est en train de capturer un site
	 * @return
	 */
	public boolean isOnCapture(){
		return onCapture;
	}
	
	/**
	 * Obtenir la page courante (en parsing)
	 * @return
	 */
	public String getCurrentPage(){
		return currentPage;
	}
	
	
	/**
	 * Obtenir la taille totale des fichiers aspirés
	 * @return
	 */
	public long getTailleSite(){
		return tailleSite;
	}
	
	/**
	 * Obtenir la taille maximale définie pour un site
	 * @return
	 */
	public long getTailleSiteMax(){
		return tailleSiteMax;
	}
	
	/**
	 * Obtenir la taille maximale définie pour un fichier ressources
	 * @return
	 */
	public long getTailleRessourcesMax(){
		return tailleRessourcesMax;
	}
	
	/**
	 * Obtenir la taille maximale définie pour une page (HTML ou PHP)
	 * @return
	 */
	public long getTaillePagesMax(){
		return tailleRessourcesMax;
	}
	
	/**
	 * Obtenir le chemin du workspace
	 */
	public String getPath() {
		return this.path;
	}


	/**
	 * Obtenir le nom du repertoire contenant le site
	 * 
	 * @param str
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Obtenir le lien local ou sera enregistre le projet
	 * 
	 * @return
	 */
	public String getLocal() {
		return urlLocal;
	}

	
	/**
	 * Procédure qui retourne l'URL source
	 */
	public String getSource() {
		return urlSource;
	}


	public int getNbFiltredURL(){
		return urlFiltred.size();
	}
	
	public int getNbPagesCopiees() {
		return this.pagesCopied.size();
	}

	public int getNbRessourcesCopiees() {
		return this.ressourcesCopied.size();
	}

	public int getNbRessourcesACopiees() {
		return this.ressources.size();
	}

	public int getNbPagesACopiees() {
		return this.pages.size();
	}

	public int getNbFichiersCopies() {
		return getNbPagesCopiees() + getNbRessourcesCopiees();
	}

	public int getNbFichiersACopies() {
		return getNbRessourcesACopiees() + getNbPagesACopiees();
	}
	
	/**
	 * La profondeur en terme de pages
	 * @return
	 */
	public int getProfondeur(){
		return profondeur;
	}
	
	/**
	 * Retourne si le Parser n'est pas actif
	 * @return
	 */
	public boolean isStopped(){
		return stop;
	}
	
	/**
	 * Retourne si le Parser est en pause
	 * @return
	 */
	public boolean isPaused(){
		return pause;
	}
	
	/**
	 * 
	 */
	private boolean isNotTooDeep(String url){
    	if(profondeur>=0){
	    	String tmp = url.substring(url.indexOf(urlSource)+urlSource.length());
			if(tmp.startsWith("/")){
				tmp =  tmp.substring(1,tmp.length());
			}
			int i = 0;
			while(tmp.indexOf("/")!=-1){
				tmp = tmp.substring(tmp.indexOf("/")+1);
				i++;
			}
			if(i<=profondeur){
				return true;
			}
			return false;
    	}
    	return true;
	}

	/**
	 * 
	 * @param link
	 * @return
	 * @throws ParserException
	 */
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
     * Retourne vrai si le lien semble être une page (donc du contenu html)
     * @param url
     * @return
     */
    private boolean isPage(String url) throws ParserException{
    	String tmp = url.substring(url.indexOf(urlSource)+urlSource.length());
    	if(tmp.length()>urlSource.length()){
    		tmp = url.substring(url.lastIndexOf("/"));
    	}
		return (url!=urlSource &&
				(tmp.indexOf("?")!=-1 || 
				tmp.indexOf("&")!=-1 ||
				tmp.indexOf("#")!=-1 ||
				tmp.indexOf(".")==-1 ||
				url.toLowerCase().endsWith(".htm")||
				url.toLowerCase().endsWith(".php")||
				url.toLowerCase().endsWith(".html")||
				url.toLowerCase().endsWith("/")));
    }
    
	/**
	 * Fonction qui retourne si la ressource doit être capturée
	 * @param url : chemin absolu de la ressources
	 * @return
	 */
	private boolean isToBeCaptured(String url){
		if(url.contains(".")){
			String extension = url.substring(url.indexOf(urlSource)+urlSource.length());
			extension = url.substring(url.lastIndexOf("."),url.length()).toLowerCase();
			if(extension.toLowerCase().matches(".[a-z0-9]*") && filtres.contains(extension)){
				if(!extensionsFiltred.contains(extension)){
					extensionsFiltred.add(extension);
				}
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fonction qui retourne si la ressource appartient au domaine de l'aspiration
	 * (si elle commence par le même préfixe que celui donné par l'utilisateur)
	 * @param link: chemin absolue de la ressource/page
	 * @return
	 */
	private boolean isRelativeToTheSource(String link) {
		return (link.toLowerCase().startsWith(urlSource.toLowerCase()));
	}
	
	/**
	 * Sauvegarder les métadonnée dans un fichier "meta.dat"
	 */
	public void saveMeta(){
		File file = new File(urlLocal+"/meta.dat");
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			meta.serializer(urlLocal+"/meta.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Procédure qui extrait le Préfixe(chemin absolu du dossier parent)
	 * 
	 * @param url
	 *            : l'URL
	 */
	private String toSource(String url){
		if (url.endsWith("/") || url.endsWith("\\")) {
			url = url.substring(0, url.length() - 1);
		}else{
			if(url.indexOf("/")!=-1){
				return url;
			}else{
				if(url.indexOf("\\")!=-1){
					url = url.substring(0, url.lastIndexOf("\\"));
				}
			}
		}
		return url;
	}
	
	/**
	 * Ajouter un filtre
	 * @param filtre
	 */
	public void addFiltre(String filtre){
		if(!filtres.contains(filtre.toLowerCase())){
			filtres.add(filtre.toLowerCase());
		}
	}
	
	/**
	 * Supprimer un filtre
	 * @param filtre
	 */
	public void removeFiltre(String filtre){
		if(filtres.contains(filtre.toLowerCase())){
			filtres.remove(filtre.toLowerCase());
		}
	}
    
	/**
	 * Fonction qui retourne si la ressource sera capturée
	 * 
	 * @param link
	 *            : chemin absolue de la ressource
	 * @return
	 */
	private String deleteSpecialChar(String str) {
		String res = str;
		if(str.contains("?")){
				res = res.replace("?", "");
		}
		if(str.contains("*")){
			res = res.replace("*", "");
		}
		if(str.contains(":")){
			res = res.replace(":", "");
		}
		return res;

	}


	/**
	 * Cette methode permet de generer le chemin absolu ou sera sauvegarde le
	 * site
	 */
	public void makeURLLocal() {
		assert (name != null && path != null) : "Il faut specifie un nom et un chemin";
		Date date = new Date();

		String URL = getPath();
		if (URL.endsWith("/")) {
			URL = URL.substring(0, URL.length() - 1);
		}
		urlLocal = URL + "/" + name + "/" + date.getTime();
		System.out.println("URL LOCAL : " + urlLocal);

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Retourne le lien utilisé en local pour les liens du type
	 * "<urlSource>/http://unlien.com/deuxiemeLien/fichier.qqc" Ce lien sera par
	 * exemple enregistré dans le répertoire "/unlien.com/deuxiemeLien/"
	 * 
	 * @param url
	 * @return
	 */
	private String toLocalLink(String url) {
		url = url.replace("http:/", "");
		return url;
	}

	private String toLocalPage(String url){
		String tmp = url.substring(url.lastIndexOf("/"));
		if(url.endsWith("/")){
			url+="index.html";
		}else{
			if(tmp.indexOf(".")==-1 &&
					tmp.indexOf("?")==-1 && 
					tmp.indexOf("&")==-1 &&
					tmp.indexOf("=")==-1 &&
					tmp.indexOf("#")==-1 &&
					tmp.indexOf(":")==-1
					){
				url+="/index.html";
			}
		}
		return url;
	}

	/**
	 * Fonction qui créé une URL relative à partir de l'URL du lien en fonction
	 * de la source
	 * 
	 * @param link
	 *            : l'URL du lien
	 * @return
	 */
	private String toRelativeLink(String link) {
		if (link.equals(urlSource)
				|| (!urlSource.endsWith("/") && link.equals(urlSource + "/"))) {
			return "";
		} else {
			if (link.startsWith(urlSource)
					&& (link.length() > urlSource.length())) {
				return link.substring(urlSource.length() + 1);
			} else {
				return link;
			}
		}
	}

	/**
	 * Fonction qui créé une URL relative de la ressource à partir de l'URL du
	 * lien et de l'URL de la page où se trouve le lien
	 * 
	 * @param link
	 *            : l'URL du lien
	 * @param current
	 *            : l'URL de la page d'où vient la ressource
	 * @return
	 */
	private String makeLocalLink(String link, String current) {
		int i;
		int j;
		String ret;

		ret = toRelativeLink(link);

		if ((null != current) && link.startsWith(urlSource)
				&& (current.length() > urlSource.length())) {
			current = current.substring(urlSource.length() + 1);
			i = 0;
			while ((j = current.indexOf('/', i)) != -1) {
				ret = "../" + ret;
				i = j + 1;
			}
		}
		return (ret);
	}

	/**
	 * Procédure principale : Au départ, la taille de pages est de 1 : c'est le
	 * lien donnée par l'utilisateur Tant qu'il y a des pages (des url
	 * trouvées), on donne l'URL de la première page de la liste au Parser et on
	 * parse. On lance ensuite la fonction 'copy' qui va effectuer les copies
	 * des ressources incluses dans la pages (images, css, etc...)
	 */
	public void launchProcess(String url) {
		stop = false;
		long time = System.currentTimeMillis();
		setSource(url);
		System.out.println(urlSource);
		pagesPool.runTask(new PageTask());
		onCapture = true;
		setChanged();
		notifyObservers();
		while ((pagesPool.isAlive() || ressourcesPool.isAlive() || pause ) 
				&& !stop){
			if(tailleSiteMax-tailleSite<2000 && tailleSiteMax!=-1) {
				stop();
			}
		}
		onCapture = false;
		time = System.currentTimeMillis()-time;
		System.out.println("Temps d'éxecution : " + time);
		meta.setTime(time);
		currentPage = "";
		saveMeta();
		setChanged();
		notifyObservers();
		afficherExtentionsFiltred();
		afficherErrors();
		reinitialise();

	}

	/**
	 * Fonction qui parse le CSS pour récupérer les images
	 * @param page
	 */
	private void treatCSS(String page, String urlPage) {

		Pattern p = Pattern.compile("url\\(.*\\)");
		Matcher m = p.matcher(page);
		String link = "";
		while (m.find()) {
			String temp = page.substring(m.start(), m.end());
			temp = temp.substring(4);
			temp = temp.substring(0, temp.length() - 1);
			temp = temp.replace("'", "");
			temp = temp.replace("\"", "");
			
			link = urlPage.substring(0,urlPage.lastIndexOf("/"));
			if(!link.equals("http:/") && link.length()>0){
				if (temp.startsWith("/")) {
					link = urlPage.substring(0,urlPage.lastIndexOf("/"))+ temp;
				}else{
					link = urlPage.substring(0,urlPage.lastIndexOf("/")+1)+ temp;
				}
			}else{
				link = urlPage + "/" + temp;
			}
			
			
			if (!temp.contains(urlSource)) {
				if(isToBeCaptured(urlPage + temp)){
					if (!ressources.contains(link)
							&& !ressourcesCopied.contains(link)
							&& !urlFiltred.contains(link)) {

						ressources.add(link);
						ressourcesPool.runTask(new RessourceTask());
						//copyRessources(link);
							
					}
				}else{
					if(!urlFiltred.contains(link)){
						urlFiltred.add(link);
					}
				}

			} else{
				if(isToBeCaptured(temp)){
					ressources.add(temp);
					ressourcesPool.runTask(new RessourceTask());
					//copyRessources(temp);
				}else{
					if(!urlFiltred.contains(temp)){
						urlFiltred.add(urlPage + temp);
					}
				}
			}
		}
	}
	
	/**
	 * Procédure qui lance l'enregistrement local de la page HTML d'URL
	 * 'relativeURL' Elle lance également l'enregistrement de toutes les images
	 * et fichiers CSS contenus dans cette page
	 * 
	 * @param relativeURL
	 */
	private void copyPage(final String relativeURL) {
		copyHTML(relativeURL);
		pagesCopied.add(relativeURL);
	}

	/**
	 * Procédure qui copie le contenu d'un fichier et l'enregistre sous le lien
	 * local correspondant
	 * 
	 * @param URL
	 */
	private void copyRessources(String URL) {
		File file;
		long taille = 0;
		file = new File(urlLocal + "/"
				+ deleteSpecialChar(toLocalLink(toRelativeLink(URL))));
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			int read;
			InputStream in;
			FileOutputStream out;
			URL source = new URL(URL);
			byte[] data = new byte[1024];
			
			try {
				in = source.openStream();
				try {
					out = new FileOutputStream(file);
					try {
						while (-1 != (read = in.read(data, 0, data.length)) 
								&& (taille<tailleRessourcesMax || tailleRessourcesMax==-1)
								&& (tailleSiteMax>tailleSite || tailleSiteMax==-1))
							out.write(data, 0, read);
							taille=file.length();
					} finally {
						out.close();
					}
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} finally {
					in.close();
					if(URL.endsWith(".css")){
						String page = "";
						in = new FileInputStream(file);
						InputStreamReader ipsr = new InputStreamReader(in);
						BufferedReader br = new BufferedReader(ipsr);
						String ligne;
						while ((ligne=br.readLine())!=null){
							page+=ligne+"\n";
						}
						br.close(); 
						treatCSS(page, URL.substring(0, URL.lastIndexOf("/")));
					}
				}
			} catch (FileNotFoundException fnfe) {
				urlErrors.add(fnfe);
				System.err.println("broken link " + fnfe.getMessage()
						+ " ignored");
			}
		} catch (MalformedURLException murle) {
			murle.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		if((taille>tailleRessourcesMax && tailleRessourcesMax!=-1)
				|| (tailleSiteMax<tailleSite+taille && tailleSiteMax!=-1)){
			file.delete();
			if(!urlFiltred.contains(URL)){
				urlFiltred.add(URL);
			}
			System.out.println("\tTrop volumineuse! : " + taille + "\n");
		}else{
			tailleSite+=taille;
			ressourcesCopied.add(URL);
			System.out.println("\tcopy RESSOURCES : \""
					+ file.getAbsolutePath());
		}
		setChanged();
		notifyObservers();
	}

	
	/**
	 * Procédure qui copie le contenu de la page HTML "parsée" et l'enregistre
	 * sous le lien local correspondant
	 * 
	 * @param URL
	 */
	private void copyHTML(String URL) {
		File file;
		if (URL.equals(urlSource + "/") || URL.equals(urlSource)) {
			file = new File(urlLocal + "/index.html");
		} else {
			String link = URL;
			link = toLocalPage(link);
			file = new File(urlLocal + "/"
					+ deleteSpecialChar(toRelativeLink(link)));

		}
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			OutputStreamWriter out;
			try {
				out = new OutputStreamWriter(new FileOutputStream(file), parser
						.getEncoding());
				try {
					PrintWriter pw = new PrintWriter(out);
					for (int i = 0; i < list.size(); i++) {
						pw.write(list.elementAt(i).toHtml());
					}
					pw.close();
				} finally {
					out.close();
					tailleSite+=list.toHtml().getBytes().length;
					System.out.println("\tcopy HTML : \"" + file.getAbsolutePath());
				}
			} catch (FileNotFoundException fnfe) {
				System.err.println("broken link " + fnfe.getMessage()
						+ " ignored");
			}
		} catch (MalformedURLException murle) {
			murle.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Affichage des fichiers déjà copiés
	 */
	public void afficherCopied() {
		if (pagesCopied.size() != 0) {
			System.out.println("\n\tPages deja copiees!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = pagesCopied.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
		if (ressourcesCopied.size() != 0) {
			System.out.println("\n\tRessources deja copiees!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = ressourcesCopied.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}

	/**
	 * Affichage des pages HTML à copier
	 */
	public void afficherPages() {
		if (pages.size() != 0) {
			System.out.println("\n\tPages a copier!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = pages.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}
	
	/**
	 * Affichage des ressources à copier
	 */
	public void afficherRessources() {
		if (ressources.size() != 0) {
			System.out.println("\n\tRessources a copier!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = ressources.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}

	/**
	 * Affichage des URLS Filtrées
	 */
	public void afficherFiltred(){
		if (urlFiltred.size() != 0) {
			System.out.println("\n\tURLs Filtrees!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = urlFiltred.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}
	
	/**
	 * Affichage des extensions Filtrées
	 */
	public void afficherExtentionsFiltred(){
		if (extensionsFiltred.size() != 0) {
			System.out.println("\n\tExtensions Filtrees!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = extensionsFiltred.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}
	
	/**
	 * Affichage des erreurs
	 */
	public void afficherErrors() {
		if (urlErrors.size() != 0) {
			System.out.println("\n\tURL Errors!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<Exception> it = urlErrors.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + it.next().getLocalizedMessage());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'img' est rencontré
	 */
	class LocalImageTag extends ImageTag {

		private static final long serialVersionUID = -4246051430371965999L;

		@Override
		public void doSemanticAction() throws ParserException {
			meta.increment("Image");
			String image = getImageURL();
			if (isRelativeToTheSource(image)) {
				if(isToBeCaptured(image)){
					if (!ressources.contains(image) 
							&& !ressourcesCopied.contains(image)
							&& !urlFiltred.contains(image)) {
						// System.out.println("\n\t----------new Image----------");
						// System.out.println("\tImage URL : " + image);
						ressources.add(image);
						ressourcesPool.runTask(new RessourceTask());
						image = deleteSpecialChar(toRelativeLink(makeLocalLink(
								image, parser.getLexer().getPage().getUrl())));
						setImageURL(image);
						// System.out.println("\t----------------------------\n");
					}
				}else{
					if(!urlFiltred.contains(image)){
						urlFiltred.add(image);
					}
				}
			}
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'href' est rencontré
	 */
	class LocalLinkTag extends LinkTag {

		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			meta.increment("Link");
			String link = getLink();
			if (isRelativeToTheSource(link)) {
				if (isPage(link)) {
					if(isNotTooDeep(link)){
						if (!pages.contains(link) 
								&& !pagesCopied.contains(link)
								&& !urlFiltred.contains(link)) {
							// System.out.println("\n\t----------new Page----------");
							// System.out.println("\tLink URL : " + link);
							
							pages.add(link);
				    		link = toLocalPage(link);
							setLink(link);
							pagesPool.runTask(new PageTask());
							// System.out.println("\t----------------------------\n");
						}
					}else{
						if(!urlFiltred.contains(link)){
							urlFiltred.add(link);
						}
					}
				} else {
					if(isToBeCaptured(link)){
						if (!ressources.contains(link) 
								&& !ressourcesCopied.contains(link)
								&& !urlFiltred.contains(link)) {
							// System.out.println("\n\t----------new Image----------");
							// System.out.println("\tImage URL : " + link);
							ressources.add(link);
							ressourcesPool.runTask(new RessourceTask());
						}
					}else{
						if(!urlFiltred.contains(link)){
							urlFiltred.add(link);
						}
					}
				}
	    		link = toLocalPage(link);
				link = deleteSpecialChar(toRelativeLink(makeLocalLink(link,
						parser.getLexer().getPage().getUrl())));
				setLink(link);
			}
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'href' est rencontré
	 */
	class LocalFrameTag extends FrameTag {

		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			meta.increment("Frame");
			String link = getFrameLocation();
			if (isRelativeToTheSource(link)) {
				if (isPage(link)) {
					if(isNotTooDeep(link)){
						if (!pages.contains(link) 
								&& !pagesCopied.contains(link)
								&& !urlFiltred.contains(link)) {
							// System.out.println("\n\t----------new Page----------");
							// System.out.println("\tLink URL : " + link);
							
							pages.add(link);
				    		link = toLocalPage(link);
							setFrameLocation(link);
							pagesPool.runTask(new PageTask());
						}
					}else{
						if(!urlFiltred.contains(link)){
							urlFiltred.add(link);
						}
					}
				} else {
					if(isToBeCaptured(link)){
						if (!ressources.contains(link) 
								&& !ressourcesCopied.contains(link)
								&& !urlFiltred.contains(link)) {
							// System.out.println("\n\t----------new Image----------");
							// System.out.println("\tImage URL : " + link);
							ressources.add(link);
							ressourcesPool.runTask(new RessourceTask());
						}
					}else{
						if(!urlFiltred.contains(link)){
							urlFiltred.add(link);
						}
					}
				}
	    		link = toLocalPage(link);
				link = deleteSpecialChar(toRelativeLink(makeLocalLink(link,
						parser.getLexer().getPage().getUrl())));
				setFrameLocation(link);
				// System.out.println("\t----------------------------\n");
			}
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'script' est rencontré
	 */
	class LocalJSTag extends org.htmlparser.tags.ScriptTag {

		private static final long serialVersionUID = -2558739946355789992L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Script");
			/* le lien js que l'on recherche */
			String jsLink = "";
			String[] text = new String[3];
			text[2] = "";
			if (getText().toLowerCase().contains(".js")) {
				jsLink = getText().split("src=")[1];
				text[0] = getText().split("src=")[0] + "src=";
				if (jsLink.split(" type").length > 1) {
					text[1] = " type" + jsLink.split(" type")[1];
				}
				jsLink = jsLink.split(" type")[0];
				jsLink = jsLink.replace("\"", "");
				jsLink = jsLink.replace("\'", "");
				if(!jsLink.startsWith("http://")){
					if(!urlSource.endsWith("/") && !jsLink.startsWith("/")){
						jsLink = urlSource + "/" + jsLink;
					}else{
						jsLink = urlSource + jsLink;
					}
				}
				if (isToBeCaptured(jsLink)) {
					if (!ressources.contains(jsLink)
							&& !ressourcesCopied.contains(jsLink)
							&& !urlFiltred.contains(jsLink)) {
						ressources.add(jsLink);
						ressourcesPool.runTask(new RessourceTask());
					}
					jsLink = deleteSpecialChar(toLocalLink(jsLink));
					text[1] = "\"" + jsLink + "\"";
					setText("<script" + text[0] + text[1] + text[2]
							+ "></script>");
				}else {
					if (!urlFiltred.contains(jsLink)) {
						urlFiltred.add(jsLink);
					}
				}
			}
		}
	}
	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'style' est rencontré
	 */
	class LocalStyleTag extends org.htmlparser.tags.StyleTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Style");
			treatCSS(toString(),parser.getLexer().getPage().getUrl());
			
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'style' est rencontré
	 */
	class LocalDivTag extends org.htmlparser.tags.Div{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Div");
			meta.addMetaTag(toHtml());
		}
	}
	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'h1' est rencontré
	 */
	class LocalHeadingTag extends org.htmlparser.tags.HeadingTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Heading");
			meta.addHeadingTag(toHtml());
		}
	}
	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'meta' est rencontré
	 */
	class LocalMetaTag extends org.htmlparser.tags.MetaTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Meta");
			meta.addMetaTag(toHtml());
		}
	}
	
	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'table' est rencontré
	 */
	class LocalTableTag extends org.htmlparser.tags.TableTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Table");
		}
	}
	
	class LocalAppletTag extends org.htmlparser.tags.AppletTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Applet");
		}
	}
	
	class LocalBaseHrefTag extends org.htmlparser.tags.BaseHrefTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("BaseHref");
		}
	}
	
	class LocalBodyTag extends org.htmlparser.tags.BodyTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Body");
		}
	}
	
	class LocalBulletTag extends org.htmlparser.tags.Bullet{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Bullet");
		}
	}
	
	class LocalBulletListTag extends org.htmlparser.tags.BulletList{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("BulletList");
		}
	}
	
	class LocalCompositeTag extends org.htmlparser.tags.CompositeTag{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Composite");
		}
	}
	
	class LocalDefinitionListTag extends org.htmlparser.tags.DefinitionListBullet{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("DefinitionListBullet");
		}
	}
	
	class LocalDocTypeTag extends org.htmlparser.tags.DoctypeTag{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("DocType");
		}
	}
	class LocalFormTag extends org.htmlparser.tags.FormTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Form");
		}
	}
	
	class LocalFrameSetTag extends org.htmlparser.tags.FrameSetTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("FrameSet");
		}
	}
	
	class LocalHeadTag extends org.htmlparser.tags.HeadTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Head");
		}
	}
	
	class LocalHTMLTag extends org.htmlparser.tags.Html{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Html");
		}
	}
	
	class LocaInputTag extends org.htmlparser.tags.InputTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Input");
		}
	}
	
	class LocalJspTag extends org.htmlparser.tags.JspTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Jsp");
		}
	}
	
	class LocalLabelTag extends org.htmlparser.tags.LabelTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Label");
		}
	}
	
	class LocalObjectTag extends org.htmlparser.tags.ObjectTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Object");
		}
	}
	
	class LocalOptionTag extends org.htmlparser.tags.OptionTag{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Option");
		}
	}
	
	class LocalParagraphTag extends org.htmlparser.tags.ParagraphTag{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("DefinitionListBullet");
		}
	}
	
	class LocalProcessingInstructionTag extends org.htmlparser.tags.ProcessingInstructionTag{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("ProcessingInstruction");
		}
	}
	class LocalSelectTag extends org.htmlparser.tags.SelectTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Select");
		}
	}
	
	class LocalSpanTag extends org.htmlparser.tags.Span{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("Span");
		}
	}
	
	class LocalTableColumnTag extends org.htmlparser.tags.TableColumn{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("TableColumn");
		}
	}
	
	class LocalTableHeaderTag extends org.htmlparser.tags.TableHeader{
		
		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("TableHeader");
		}
	}
	class LocalTextAreaTag extends org.htmlparser.tags.TextareaTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("TextArea");
		}
	}
	
	class LocalTableRowTag extends org.htmlparser.tags.TableRow{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("TableRow");
		}
	}
	
	class LocalTitleTag extends org.htmlparser.tags.TitleTag{
		
		/* A compléter... */

		private static final long serialVersionUID = 1851549267225708433L;

		public void doSemanticAction() throws ParserException {
			meta.increment("TableColumn");
		}
	}
	
	class RessourceTask implements Runnable{

		@Override
		public void run() {
			String URL;
			synchronized (ressources){
				URL = ressources.remove(0);
			}
			copyRessources(URL);
		}
	}
	
	class PageTask implements Runnable{

		@Override
		public void run() {
			try {
				String urlPage = "";
				synchronized (pages.get(0)) {
					currentPage = pages.get(0);
					parser.setURL(pages.get(0));
					urlPage = pages.remove(0);
				}
				list = new NodeList();
				parser.reset();
				try {
					for (NodeIterator it = parser.elements(); it.hasMoreNodes();) {
						list.add(it.nextNode());
					}
				} catch (EncodingChangeException e) {
					/*
					 * Si l'encodage n'est pas le bon, il faut faire un reset et
					 * il est changé automatiquement
					 */
					parser.reset();
					list = new NodeList();
					for (NodeIterator it = parser.elements(); it.hasMoreNodes();) {
						list.add(it.nextNode());
					}
				}
				if(((taillePagesMax==-1 
						|| taillePagesMax>list.toHtml().getBytes().length))
						&& (tailleSiteMax>(tailleSite+list.toHtml().getBytes().length) || tailleSiteMax==-1)){
					copyPage(urlPage);
				}else{
					System.err.println("doh:"+list.toHtml().getBytes().length);
					System.err.println((tailleSiteMax>(tailleSite+list.toHtml().getBytes().length) || tailleSiteMax==-1));
					if(!urlFiltred.contains(urlPage)){
						urlFiltred.add(urlPage);
					}
				}
				/*
				 * afficherCopied(); afficherImages(); afficherPages();
				 * afficherCSS(); afficherJS();
				 */

			} catch (ParserException e) {
				urlErrors.add(e);
				e.printStackTrace();
				System.out.println("\nError in : " + parser.getURL() + "\n");
				synchronized(pages){
					pages.remove(0);
				};
			}
			if (pages.size() == 0) {
				joinAll();
			}
		}
		
	}
}
