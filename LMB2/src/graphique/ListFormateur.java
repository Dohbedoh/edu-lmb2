package graphique;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListFormateur extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
		int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());
		setForeground(isSelected ? Color.red : Color.black);
		return this;
	}
	
	public void setText(String val){
		setForeground(Color.blue);
		super.setText(val);
	}

}

