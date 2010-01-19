/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class MyLessIcon implements Icon {

	// ------------------
	// Constructeurs
	// ------------------
	public MyLessIcon() {
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
		g.fillRect(5, 8, 11, 3);
	}
}