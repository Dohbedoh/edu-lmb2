package Aspirateur;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.SwingUtilities;

import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.EncodingChangeException;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Aspirateur extends Observable {

	// ------------------
	// Attributs
	// ------------------
	NodeList list;

	Parser parser;
	/**
	 * Préfixe de l'URL WEB donnée (généralement le dossier parent de
	 * "index.html" par exemple
	 */
	private String urlSource;

	/** Préfixe de l'URL LOCAL donnée (le dossier où l'on sauvegarde */
	private String urlLocal;

	/** Chemin absolu de sauvegarde */
	private String path;

	/** Liste des ressources à copier */
	private ArrayList<String> ressources;

	/** Liste des pages à copier */
	private ArrayList<String> pages;

	/** Liste des ressources déjà copiées */
	private ArrayList<String> ressourcesCopied;

	/** Liste des pages déjà copiées */
	private ArrayList<String> pagesCopied;

	/** Liste des erreurs */
	private ArrayList<Exception> urlErrors;

	/** Nom du projet */
	private String name;

	/** Notre pool de thread */
	private ThreadPool pagesPool;
	private ThreadPool ressourcesPool;

	// ------------------
	// Constructeur
	// ------------------
	public Aspirateur() {
		ressources = new ArrayList<String>();
		pages = new ArrayList<String>();
		ressources = new ArrayList<String>();
		ressources = new ArrayList<String>();
		pagesCopied = new ArrayList<String>();
		ressourcesCopied = new ArrayList<String>();
		urlErrors = new ArrayList<Exception>();
		pagesPool = new ThreadPool(1);
		ressourcesPool = new ThreadPool(2);
		parser = new Parser();
		PrototypicalNodeFactory factory;
		factory = new PrototypicalNodeFactory();
		/* On definit les Tag qui nous interesse */
		factory.registerTag(new LocalLinkTag());
		factory.registerTag(new LocalImageTag());
		factory.registerTag(new LocalFrameTag());
		factory.registerTag(new CSSTag());
		factory.registerTag(new JSTag());
		parser.setNodeFactory(factory);
	}

	// ------------------
	// Methodes
	// ------------------

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
	 * Obtenir le chemin du workspace
	 */
	public String getPath() {
		return this.path;
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
	 * Obtenir le nom du repertoire contenant le site
	 * 
	 * @param str
	 */
	public String getName() {
		return name;
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
	 * Obtenir le lien local ou sera enregistre le projet
	 * 
	 * @return
	 */
	public String getLocal() {
		return urlLocal;
	}

	/**
	 * Cette methode permet de generer le chemin absolu ou sera sauvegarde le
	 * site
	 */
	public void makeURLLocal() {
		assert (name != null || path != null) : "Il faut specifie un nom et un chemin";
		Date date = new Date();

		String URL = getPath();
		if (URL.endsWith("/")) {
			URL = URL.substring(0, URL.length() - 1);
		}
		urlLocal = URL + "/" + name + "/" + date.getDate() + "-"
				+ (date.getMonth() + 1) + "-" + (date.getYear() + 1900);
		System.out.println("URL LOCAL : " + urlLocal);

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
	}

	/**
	 * Procédure qui extrait le Préfixe(chemin absolu du dossier parent)
	 * 
	 * @param url
	 *            : l'URL
	 */
	public String getSource(String url) {
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}else{
			url = url.substring(0, url.lastIndexOf("/"));
		}
		return url;
	}

	/**
	 * Procedure qui modifie le Prefixe qui sera utilise
	 * 
	 * @param url
	 *            : l'URL donnee par l'utilisateur
	 */
	public void setSource(String url) {
		urlSource = getSource(url);
		pages.add(url);

		// Avertir les vues que le modele change
		setChanged();
		notifyObservers();
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

	public void join() {
		ressourcesPool.join();
		pagesPool.join();
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
	 * 
	 * @param link
	 *            : chemin absolue de la ressource
	 * @return
	 */
	private String deleteSpecialChar(String str) {
		String res = str;
		while (res.contains("?")) {
			res = res.replace("?", "");
			res = res.replace("*", "");
			res = res.replace(":", "");
		}
		return res;

	}

	/**
	 * Retourne le lien utilisé en local pour les liens du type
	 * "<urlSource>/http://unlien.com/deuxiemeLien/fichier.qqc" Ce lien sera par
	 * exemple enregistré dans le répertoire "/unlien.com/deuxiemeLien/"
	 * 
	 * @param url
	 * @return
	 */
	public String toLocalLink(String url) {
		url = url.replace("http:/", "");
		return url;
	}

	/**
	 * Fonction qui retourne si la ressource sera capturée
	 * 
	 * @param link
	 *            : chemin absolue de la ressource
	 * @return
	 */
	private boolean isToBeCaptured(String link) {
		return (link.toLowerCase().startsWith(urlSource.toLowerCase()));
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
		setSource(url);
		System.out.println(urlSource);
		pagesPool.runTask(visitTask());
		while (pagesPool.isAlive() || ressourcesPool.isAlive()) {
		}
		System.out.println("fini");
		setChanged();
		notifyObservers();
		afficherCopied();
		afficherErrors();

	}

	public Runnable visitTask() {
		return new Runnable() {

			@Override
			public void run() {
					try {

						String urlPage = "";
						synchronized (pages) {
							System.out.println("\nCurrent Page : "
									+ pages.get(0));
							parser.setURL(pages.get(0));
							urlPage = pages.remove(0);
							list = new NodeList();
							parser.reset();
						}
						try {
							for (NodeIterator it = parser.elements(); it
									.hasMoreNodes();) {
								list.add(it.nextNode());
							}
						} catch (EncodingChangeException e) {
							/*
							 * Si l'encodage n'est pas le bon, il faut faire un
							 * reset et il est changé automatiquement
							 */
							parser.reset();
							list = new NodeList();
							for (NodeIterator it = parser.elements(); it
									.hasMoreNodes();) {
								list.add(it.nextNode());
							}
						}
						copyPage(urlPage);
						/*
						 * afficherCopied(); afficherImages(); afficherPages();
						 * afficherCSS(); afficherJS();
						 */

					} catch (ParserException e) {
						urlErrors.add(e);
						e.printStackTrace();
						System.out.println("\nError in : " + parser.getURL()
								+ "\n");
						pages.remove(0);
					}
					if(pages.size()==0){
						join();
					}
			}
		};
	}

	/**
	 * Procédure qui lance l'enregistrement local de la page HTML d'URL
	 * 'relativeURL' Elle lance également l'enregistrement de toutes les images
	 * et fichiers CSS contenus dans cette page
	 * 
	 * @param relativeURL
	 */
	public void copyPage(final String relativeURL) {
		System.out.println("\tcapture Page : \"" + relativeURL);
		copyHTML(relativeURL);
		pagesCopied.add(relativeURL);
	}

	/**
	 * Procédure qui lance l'enregistrement copie du fichier CSS d'URL
	 * 'relativeURL'
	 * 
	 * @param relativeURL
	 */
	public Runnable copyTask() {
		return new Runnable() {

			@Override
			public void run() {
					System.out.println("\tcapture Ressource : \""
							+ ressources.get(0));
					String URL;
					synchronized (ressources){
						URL = ressources.remove(0);
					}
					copyRessources(URL);
					ressourcesCopied.add(URL);
			}
		};
	}

	/**
	 * Procédure qui copie le contenu d'un fichier et l'enregistre sous le lien
	 * local correspondant
	 * 
	 * @param URL
	 */
	public void copyRessources(String URL) {
		File file;
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
					System.out.println("\tcopy RESSOURCES : \""
							+ file.getAbsolutePath() + "\n");
					out = new FileOutputStream(file);
					try {
						while (-1 != (read = in.read(data, 0, data.length)))
							out.write(data, 0, read);
					} finally {
						out.close();
					}
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} finally {
					in.close();
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
		setChanged();
		notifyObservers();
	}

	/**
	 * Procédure qui copie le contenu de la page HTML "parsée" et l'enregistre
	 * sous le lien local correspondant
	 * 
	 * @param URL
	 */
	public void copyHTML(String URL) {
		File file;
		if (URL.equals(urlSource + "/")) {
			file = new File(urlLocal + "/index.html");
		} else {
			file = new File(urlLocal + "/"
					+ deleteSpecialChar(toRelativeLink(URL)));
		}
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			OutputStreamWriter out;
			System.out.println("\tcopy HTML : \"" + file.getAbsolutePath()
					+ "\n");
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
			System.out.println("\n\tPages déjà copiées!");
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
			System.out.println("\n\tRessources déjà copiées!");
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
			System.out.println("\n\tPages à copier!");
			System.out
					.println("\t------------------------------------------------");
			Iterator<String> it = pages.iterator();
			while (it.hasNext()) {
				System.out.println("\t" + urlSource + "/" + it.next());
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
				System.out.println("\n\t");
				System.out.println("\t" + it.next().getLocalizedMessage());
			}
			System.out
					.println("\t------------------------------------------------");
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'img' est rencontré
	 * 
	 * @author Stolen_Flame_57
	 */
	class LocalImageTag extends ImageTag {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4246051430371965999L;

		@Override
		public void doSemanticAction() throws ParserException {
			String image = getImageURL();
			if (isToBeCaptured(image)) {
				if (!ressources.contains(image) && !ressourcesCopied.contains(image)) {
					// System.out.println("\n\t----------new Image----------");
					// System.out.println("\tImage URL : " + image);
					ressources.add(image);
					ressourcesPool.runTask(copyTask());
					image = deleteSpecialChar(toRelativeLink(makeLocalLink(
							image, parser.getLexer().getPage().getUrl())));
					setImageURL(image);
					// System.out.println("\t----------------------------\n");
				}
			}
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'href' est rencontré
	 * 
	 * @author Stolen_Flame_57
	 */
	class LocalLinkTag extends LinkTag {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			String link = getLink();
			if (isToBeCaptured(link)) {
				if (isHtml(link)) {
					if (!pages.contains(link) && !pagesCopied.contains(link)) {
						// System.out.println("\n\t----------new Page----------");
						// System.out.println("\tLink URL : " + link);
						pages.add(link);
						pagesPool.runTask(visitTask());
						// System.out.println("\t----------------------------\n");
					}
				} else {
					if (!ressources.contains(link) && !ressourcesCopied.contains(link)) {
						// System.out.println("\n\t----------new Image----------");
						// System.out.println("\tImage URL : " + link);
						ressources.add(link);
						ressourcesPool.runTask(copyTask());
					}
				}
				link = deleteSpecialChar(toRelativeLink(makeLocalLink(link,
						parser.getLexer().getPage().getUrl())));
				setLink(link);
			}
		}
	}

	/**
	 * Classe Interne qui permet de redéfinir la fonction à effectuer lorsqu'un
	 * tag de type 'href' est rencontré
	 * 
	 * @author Stolen_Flame_57
	 */
	class LocalFrameTag extends FrameTag {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2082635255821131373L;

		@Override
		public void doSemanticAction() throws ParserException {
			String link = getFrameLocation();
			if (isToBeCaptured(link)) {
				if (isHtml(link)) {
					if (!pages.contains(link) && !pagesCopied.contains(link)) {
						// System.out.println("\n\t----------new Page----------");
						// System.out.println("\tLink URL : " + link);
						pages.add(link);
						pagesPool.runTask(visitTask());
					}
				} else {
					if (!ressources.contains(link)
							&& !ressourcesCopied.contains(link)) {
						// System.out.println("\n\t----------new Image----------");
						// System.out.println("\tImage URL : " + link);
						ressources.add(link);
						ressourcesPool.runTask(copyTask());
					}
				}
				link = deleteSpecialChar(toRelativeLink(makeLocalLink(link,
						parser.getLexer().getPage().getUrl())));
				setFrameLocation(link);
				// System.out.println("\t----------------------------\n");
			}
		}
	}

	class CSSTag extends org.htmlparser.tags.HeadTag {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2558739946355789992L;

		public void doSemanticAction() throws ParserException {
			/* le lien css que l'on recherche */
			String cssLink = "";
			/* Le Tag "link" dans lequel est de trouve l'URL */
			TagNode tagLink = null;
			for (int i = 0; i < getChildCount(); i++) {
				/* On cherche le tag qui contient la chaîne 'rel="stylesheet"' */
				if (getChild(i) != null
						& getChild(i).toString().contains("rel=\"stylesheet\"")
						& getChild(i).toString().contains(".css")) {
					// System.out.println("OK CSS");
					tagLink = ((TagNode) getChild(i));
					int j = 0;
					/* On cherche à présent l'attribut contenant l'URL : 'href' */
					while (!cssLink.contains("href")) {
						cssLink = tagLink.getAttributesEx().get(j).toString();
						j++;
					}
					/* On récupère seulement le lien */
					cssLink = cssLink.substring(cssLink.indexOf('"') + 1);
					cssLink = cssLink.substring(0, cssLink.indexOf('"'));
					String source = getSource(parser.getLexer().getPage()
							.getUrl());
					while (cssLink.contains("../")) {
						source = getSource(parser.getLexer().getPage().getUrl());
						source = source.substring(0, source.lastIndexOf("/"));
						cssLink = cssLink
								.substring(cssLink.lastIndexOf("../") + 3);
					}
					/* On ajoute le préfixe */
					cssLink = getSource(source) + "/" + cssLink;
					if (isToBeCaptured(cssLink)) {
						if (!ressources.contains(cssLink)
								&& !ressourcesCopied.contains(cssLink)) {
							// System.out.println("\n\t----------new CSS-----------");
							// System.out.println("\tCSS URL : " + cssLink);
							ressources.add(cssLink);
							ressourcesPool.runTask(copyTask());
							// System.out.println("\t----------------------------\n");
						}
					}
				}
			}
		}
	}

	class JSTag extends org.htmlparser.tags.ScriptTag {

		/**
			 * 
			 */
		private static final long serialVersionUID = -2558739946355789992L;

		public void doSemanticAction() throws ParserException {
			/* le lien css que l'on recherche */
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
				if (!ressources.contains(jsLink) && !ressourcesCopied.contains(jsLink)) {
					ressources.add(jsLink);
					ressourcesPool.runTask(copyTask());
				}
				jsLink = deleteSpecialChar(toLocalLink(jsLink));
				text[1] = "\"" + jsLink + "\"";
				setText("<script" + text[0] + text[1] + text[2] + "></script>");
			}
		}
	}
}
