/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListFormateur extends JLabel implements ListCellRenderer {

	//------------------
	// Attributs
	//------------------
	private static final long serialVersionUID = 1L;


	//------------------
	// Méthodes
	//------------------
	public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
		
		setText(((File) value).getName()+"  ("+((File) value).length()+"bytes)");
		setForeground(isSelected ? Color.red : Color.black);
		setBackground(isSelected ? Color.gray : new Color(150,200,250));
		setFont(new Font("TimesRoman",Font.PLAIN,12));
		setIcon(new MyIcon());
		
		list.setBackground(new Color(150,200,250));
		return this;
	}

	class MyIcon implements Icon {

		  public MyIcon() {
		  }

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
}

