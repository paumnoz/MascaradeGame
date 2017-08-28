package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


//Descripcio: Finestra que serveix per escollir la dificultat del joc maquina
public class FinestraDificultat extends JDialog{
	
	private int eleccio;
	
	/**
     * @pre --
     * @post genera la finestra per escollir dificultat
     */
	public FinestraDificultat(){
		setTitle("Escull:");
		
		
		JPanel panell = new JPanel();
		panell.setBackground(Color.decode("#4F4F4F"));
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		
		JPanel ptext = new JPanel();
		ptext.setBackground(Color.decode("#4F4F4F"));
		JLabel text = new JLabel("Escull la dificultat del joc robot");
		ptext.add(text);
		
		text.setForeground(Color.yellow);
		panell.add(ptext);
		OmplirBotons(panell);
		getContentPane().add(panell, BorderLayout.CENTER);
		setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    
	}
	
	/**
     * @pre --
     * @post omple el panell amb botons
     * @param panell panell principal de la finestra
     */
	private void OmplirBotons(JPanel panell){
		JPanel botons = new JPanel();
		botons.setBackground(Color.decode("#4F4F4F"));
		JButton facil = new JButton("Senzill");
		facil.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
  			  	eleccio = 0;
				dispose();
				setVisible(false);
			}} );
		JButton mig = new JButton("Intermig");
		mig.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
  			  	eleccio = 1;
				dispose();
				setVisible(false);
			}} );
		
		JButton complex = new JButton("Complex");
		complex.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
  			  	eleccio = 2;
				dispose();
				setVisible(false);
			}} );
		botons.add(facil);
		botons.add(mig);
		botons.add(complex);
		panell.add(botons);
	}
	/**
     * @pre --
     * @post seleccionem dificultat
     * @return obtenim nivell de dificultat seleccionat
     */
	public int seleccionar(){
		setLocationRelativeTo(null);
		setVisible(true);
		return eleccio;
		
	}
 
}