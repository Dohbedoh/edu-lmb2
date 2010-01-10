package graphique;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class JTextAreaAdapter extends OutputStream {
    private JTextArea textControl;
    
    JTextAreaAdapter(JTextArea control) {
        textControl = control;
    }
    
    public void write(int b) throws IOException {
        textControl.append(String.valueOf((char)b));
    }   
}