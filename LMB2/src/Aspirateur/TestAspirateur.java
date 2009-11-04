package Aspirateur;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class TestAspirateur {

	Parser parser;
	
	public static void main (String[] args){
		System.out.println("Si tu vois ce texte, c'est que tu as bien suivi les instructions");
		System.out.println("J'ai bien suivi les instructions mon p'tit!");
		System.out.println("J'ai bien suivi les instructions mon p'tit!, by Greg");
        try
        {
            Parser parser = new Parser ("http://www.renaudmathieu.fr/lmb2/");
            NodeList list = parser.parse (null);
            System.out.println (list.toHtml ());
        }
        catch (ParserException pe)
        {
            pe.printStackTrace ();
        }
    }

}
