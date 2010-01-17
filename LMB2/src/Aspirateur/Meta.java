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
import org.htmlparser.Tag;

public class Meta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793617748921430510L;
	private String url;
	private String meta;
	private ArrayList<String> divTags;
	private ArrayList<String> tableTags;
	private ArrayList<String> headingTags;
	private ArrayList<String> metaTags;
	private long time;
	
	public Meta(){
		meta = "";
		url = "";
		divTags = new ArrayList<String>();
		tableTags = new ArrayList<String>();
		headingTags = new ArrayList<String>();
		metaTags = new ArrayList<String>();
		time = 0;
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
	
	public ArrayList<String> getDivTagsList(){
		return divTags;
	}
	
	public ArrayList<String> getTableTagsList(){
		return tableTags;
	}
	
	public ArrayList<String> getMetaTagsList(){
		return metaTags;
	}
	
	public ArrayList<String> getHeadingTagsList(){
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

	public void addTableTag(String tag){
		tableTags.add(tag);
	}
	
	public void addHeadingTag(String tag){
		headingTags.add(tag);
	}
	
	public void addMetaTag(String tag){
		metaTags.add(tag);
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