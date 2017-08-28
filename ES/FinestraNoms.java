package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//Descripcio: Finestra a usar per a posar noms als jugadors humans
public class FinestraNoms extends JDialog {
	

	private ArrayList<String> anoms;//noms a omplir
	
	private JTextField[] ahumans;//jugadors humans
	
	private JLabel[] lhumans;//nom jugadors humans(index)
	
	/**
     * @pre --
     * @post generem la finestra i omplim noms amb noms de humans
     * @param noms llista de noms a omplir
     * @param humans llista de jugadors humans
     */
	public FinestraNoms(ArrayList<String> noms, int humans) {
		setTitle("Escollir noms");
		anoms = noms;
		setBackground( Color.decode("#4F4F4F") );
		
		JPanel pnoms = new JPanel();
		pnoms.setLayout(new BoxLayout(pnoms, BoxLayout.Y_AXIS));
		pnoms.setBackground( Color.decode("#4F4F4F") );
		
		AfegirNomsHumans(humans,pnoms);

		AfegirBoto(pnoms);
		
		getContentPane().add(pnoms, BorderLayout.CENTER);
	    setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	/**
     * @pre --
     * @post afegeix recuadres de text per a noms humans a la finestra
     * @param p panell principal de la finestra
     */
	private void AfegirNomsHumans(int n,JPanel p){
		ahumans = new JTextField[n];
		lhumans = new JLabel[n];
		for(int i = 0; i < n; i++){
			lhumans[i] = new JLabel("Jugador num "+(String.valueOf(i+1)+": "));
			lhumans[i].setForeground(Color.decode("#E4B841"));
			lhumans[i].setBorder(new EmptyBorder(10, 10, 10, 10));
			p.add(lhumans[i]);
			ahumans[i] = new JTextField(4);
			ahumans[i].setBorder(new EmptyBorder(10, 10, 10, 10));
			ahumans[i].setText("Manel"+String.valueOf(i));
			p.add(ahumans[i]);
		}
	}
	
	/**
     * @pre --
     * @post afegeix els botons a la finestra
     * @param p panell principal de la finestra
     */
	private void AfegirBoto(JPanel p){
		JButton afegir = new JButton("Afegir noms i endavant");
		afegir.setBorder(new EmptyBorder(10, 10, 10, 10));
		afegir.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
  			  for(int i = 0; i < ahumans.length; i++){
  				  anoms.add(ahumans[i].getText());
  			  }

			  dispose();
			  setVisible(false);
			  } 
			} );
		p.add(afegir);
	}
}
