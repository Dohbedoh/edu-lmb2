/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;

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
		list.setBackground(new Color(150,200,250));
		return this;
	}

}

