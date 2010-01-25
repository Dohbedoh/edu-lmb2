/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package Aspirateur;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Meta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793617748921430510L;
	private String url;
	private String meta;
	private HashSet<String> mailTos;
	private long time;
	private Hashtable<String, Integer> tags;
	private ArrayList<String> lesFiltres;
	/*private ArrayList<String> brokenLinks;
	private ArrayList<String> filtredLinks;*/
	private int nbThreadPages;
	private int nbThreadRess;
	private int profondeur;
	private long tailleSite;
	private long tailleRess;
	private long taillePages;
	
	
	public Meta(){
		meta = "";
		url = "";
		mailTos = new HashSet<String>();
		tags = new Hashtable<String, Integer>();
		lesFiltres = new ArrayList<String>();
		nbThreadPages = 0;
		nbThreadRess = 0;
		profondeur = -1;
		tailleSite = -1;
		tailleRess = -1;
		taillePages = -1;
		time = 0;
		
	}

	public void increment(String key) {
		if(!tags.containsKey(key)){
			tags.put(key, new Integer(1));
		}else{
			tags.put(key, tags.get(key)+1);
		}
	}
	
	public Hashtable<String, Integer> getTagsTable(){
		return tags;
	}
	
	
	
	public ArrayList<String> getLesFiltres() {
		return lesFiltres;
	}

	public void setLesFiltres(ArrayList<String> lesFiltres) {
		this.lesFiltres = lesFiltres;
	}

	public int getNbThreadPages() {
		return nbThreadPages;
	}

	public void setNbThreadPages(int nbThreadPages) {
		this.nbThreadPages = nbThreadPages;
	}

	public int getNbThreadRess() {
		return nbThreadRess;
	}

	public void setNbThreadRess(int nbThreadRess) {
		this.nbThreadRess = nbThreadRess;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public long getTailleSite() {
		return tailleSite;
	}

	public void setTailleSite(long tailleSite) {
		this.tailleSite = tailleSite;
	}

	public long getTailleRess() {
		return tailleRess;
	}

	public void setTailleRess(long tailleRess) {
		this.tailleRess = tailleRess;
	}

	public long getTaillePages() {
		return taillePages;
	}

	public void setTaillePages(long taillePages) {
		this.taillePages = taillePages;
	}

	public void setURL(String url){
		this.url = url;
	}
	
	public void setMetaData(String meta){
		this.meta = meta;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
	
	public HashSet<String> getMailTosList(){
		return mailTos;
	}
	
	public String getMetaData(){
		return meta;
	}
	
	public String getURL(){
		return url;
	}
	
	public void addMailTo(String tag){
		mailTos.add(tag);
	}
	
	public String toString(){
		return url + "\n" + meta;
	}
	
	public void serializer(String nom) throws IOException	{
		String nomFic = nom;
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFic));
		try	{
			oos.writeObject(this);
		} catch(IOException ioe)	{
			System.err.println("Erreur dans 'serialiser()' : " + ioe.toString());
		}
		oos.close();
	}
	
	public static Meta deserializer(String nom) throws IOException	{
		String nomFic = nom;
		Meta tmp = new Meta();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFic));
		try	{
			tmp = (Meta)ois.readObject();
		} catch(IOException ioe)	{
			System.err.println("Erreur dans 'deserialiser()' : " + ioe.toString());
		}
		catch(ClassNotFoundException cnfe)	{
			System.err.println("Erreur dans 'Classe Inconnue' : " + cnfe.toString());
		}
		ois.close();
		return (tmp);
	}
}
