/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class MyCrossIcon implements Icon {

	// ------------------
	// Constructeurs
	// ------------------
	public MyCrossIcon() {
	}

	// ------------------
	// Methodes
	// ------------------
	public int getIconHeight() {
		return 10;
	}

	public int getIconWidth() {
		return 10;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(new Color(150,200,250));
		g.fillRect(9, 5, 3, 11);
		g.fillRect(5, 9, 11, 3);
		
		//g.fillRect(7, 0, 5, 20);
		//g.fillRect(0, 7, 20, 5);
	}
}