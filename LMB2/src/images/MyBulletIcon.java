/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

class MyIcon implements Icon {

	// ------------------
	// Constructeurs
	// ------------------
	public MyIcon() {
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
		g.setColor(Color.BLACK);
		g.drawOval(5, 5, 5, 5);
	}
}