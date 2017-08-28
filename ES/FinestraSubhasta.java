package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Descripcio: finestra encarregada dajudar als usuaris a jugar la seva subhasta
//els usuaris entren els valors de diners que volen entrar en joc


public class FinestraSubhasta extends JDialog{
	
	private JTextField diners;
	private int eleccio;//eleccio de l'usuari(diners)
	
	/**
     * @pre --
     * @post genera una finestra de pregunta
     * @param t pregunta a realitzar
     */
	public FinestraSubhasta(String t){
		setTitle("Finestra de subhasta:");
		
		
		JPanel panell = new JPanel();
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		panell.setBackground(Color.decode("#4F4F4F"));
		JLabel text = new JLabel(t);
		text.setForeground(Color.yellow);
		panell.add(text);
		AfegirElements(panell);
		getContentPane().add(panell, BorderLayout.CENTER);
		setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 

	}
	/**
     * @pre --
     * @post afegeix els botons a la finestra
     * @param panell panell principal de la finestra
     */
	private void AfegirElements(JPanel panell){
		JPanel pdiners = new JPanel();
		diners = new JTextField(6);
		pdiners.add(diners);
		pdiners.setBackground(Color.decode("#4F4F4F"));
		panell.add(pdiners);
		JButton afegir = new JButton("Jugar diners en subhasta");
		afegir.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(diners.getText()!=null && !diners.getText().equals("")){
  			  		eleccio = Integer.parseInt(diners.getText());
  			  		dispose();
  			  		setVisible(false);
				}
			}} );
		JPanel pafegir = new JPanel();
		pafegir.add(afegir);
		pafegir.setBackground(Color.decode("#4F4F4F"));
		panell.add(pafegir);
	}
	/**
     * @pre --
     * @post obtenim l'eleccio de l'usuari
     * @return retorna la opcio escollida
     */
	public int selecciona(){
		setLocationRelativeTo(null);
		setVisible(true);
		return eleccio;
	}
 
}