package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rol.Rol;


public class FinestraMostrar extends JDialog{
	/**
     * @pre --
     * @post  crea una finestra amb un missatge a mostrar per pantalla
     * @param t missatge a mostrar
     */
	public FinestraMostrar(String t){
		setTitle("Missatge del joc:");
		
		
		JPanel panell = new JPanel();
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		panell.setBackground(Color.decode("#4F4F4F"));
		JPanel ptext = new JPanel();
		ptext.setBackground(Color.decode("#4F4F4F"));
		JLabel text = new JLabel("<html><center>"+t+"</center></html>");
		ptext.add(text);
		text.setForeground(Color.yellow);
		panell.add(ptext);
		JPanel pboto = new JPanel();
		pboto.setBackground(Color.decode("#4F4F4F"));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose();
				setVisible(false);
			}} );
		pboto.add(ok);
		panell.add(pboto);
		getContentPane().add(panell, BorderLayout.CENTER);
		setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
 
}
