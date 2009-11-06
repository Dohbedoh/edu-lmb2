package Aspirateur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;



public class TestAspirateur{

	NodeList list;
	Parser parser;
	ArrayList<URL> urlVisited;
	
	
	public TestAspirateur(String url){
		parser = new Parser();
		try {
			parser.setURL(url);
			list = parser.parse(null);
			System.out.println(list.toHtml());
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
	}
	
	public void remplirURLs(NodeList list){
		
	}
	
	public static void main (String[] args){
		TestAspirateur test = new TestAspirateur("http://localhost/Projet%20CESI/page_cnx.html");
	}
}
