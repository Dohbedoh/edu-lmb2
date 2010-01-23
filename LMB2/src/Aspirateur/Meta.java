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
	private HashSet<String> divTags;
	private HashSet<String> headingTags;
	private HashSet<String> metaTags;
	private HashSet<String> mailTos;
	private long time;
	private Hashtable<String, Integer> tags;
	
	
	public Meta(){
		meta = "";
		url = "";
		divTags = new HashSet<String>();
		headingTags = new HashSet<String>();
		metaTags = new HashSet<String>();
		mailTos = new HashSet<String>();
		time = 0;
		tags = new Hashtable<String, Integer>();
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
	
	public HashSet<String> getDivTagsList(){
		return divTags;
	}
	
	public HashSet<String> getMetaTagsList(){
		return metaTags;
	}
	
	public HashSet<String> getMailTosList(){
		return mailTos;
	}
	
	public HashSet<String> getHeadingTagsList(){
		return headingTags;
	}
	
	public String getMetaData(){
		return meta;
	}
	
	public String getURL(){
		return url;
	}
	
	public void addDivTag(String tag){
		divTags.add(tag);
	}
	
	public void addHeadingTag(String tag){
		headingTags.add(tag);
	}
	
	public void addMetaTag(String tag){
		metaTags.add(tag);
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
