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

import javax.swing.text.html.HTML.Tag;

public class Meta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793617748921430510L;
	private String url;
	private String meta;
	private ArrayList<Tag> divTags;
	private ArrayList<Tag> tableTags;
	private ArrayList<Tag> headingTags;
	private ArrayList<Tag> metaTags;
	
	
	
	public Meta(){
		meta = "";
		url = "";
	}
	
	public String getURL(){
		return url;
	}
	
	public void setURL(String url){
		this.url = url;
	}
	
	public void setMetaData(String meta){
		this.meta = meta;
	}
	
	public String getMetaData(){
		return meta;
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
