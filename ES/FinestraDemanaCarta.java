package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jugador.Jugador;


//Descripcio: Finestra que serveix per demanar una carta a seleccionar per diversos motius en la partida. 
//Serveix per obtenir un numero de carta en forma d'enter
public class FinestraDemanaCarta extends JDialog{
	
	private JPanel tanterior;
	private int eleccio;//eleccio(carta escollida)
	private int max;//max. de cartes
	private Jugador jug;
	private int opcio;
	/**
     * @pre num >0 
     * @post constructor de la finestra
     * @param t text que dona informacio sobre el motiu per al que cal seleccionar carta
     * @param num numero de cartes
     */
	public FinestraDemanaCarta(int num, String t, int op, Jugador j){
		setTitle("Escull la carta:");
		max = num;
		jug = j;
		opcio = op;
		
		JPanel panell = new JPanel();
		panell.setBackground(Color.decode("#4F4F4F"));
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		
		JPanel ptext = new JPanel();
		JLabel text = new JLabel("Quina carta vols "+t);
	    
		text.setForeground(Color.red);
		ptext.add(text);
		ptext.setBackground(Color.decode("#4F4F4F"));
		panell.add(ptext);
		OmplirCartes(panell);
		OmplirBotons(panell);
		getContentPane().add(panell, BorderLayout.CENTER);
		setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    
	}
	/**
     * @pre existeixen cartes a seleccionar
     * @post omple el panell de rols amb les cartes a seleccionar(girades)
     * @param p panell principal on omplirem cartes
     */
	private void OmplirCartes(JPanel panell){
		JPanel panCartes = new JPanel();
		
		for(int i=0; i < max;i++){
			if(jug==null || (!(jug.haConsultat(i) && opcio == 2))){
				BufferedImage logo = null;
				JLabel carta = null; 
				  
				try{
					logo = ImageIO.read(new File("revers.jpg"));
					logo = ajustaMidaImatge(logo,190,260);
				} 
				catch (IOException e) {
						e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				final int posicioActual = i;
				final JPanel infoCarta = new JPanel();
				infoCarta.setLayout(new BoxLayout(infoCarta, BoxLayout.Y_AXIS));
				infoCarta.setPreferredSize(new Dimension(210, 310));
		        infoCarta.setMaximumSize(new Dimension(210, 310));
				infoCarta.setBackground(Color.decode("#4F4F4F"));
				JLabel cartaPos = new JLabel("Carta num "+String.valueOf(posicioActual));
				cartaPos.setForeground(Color.pink);
				cartaPos.setForeground(Color.pink);
				final JButton botoCanvi = new JButton("Seleccionar");
				botoCanvi.addActionListener(new ActionListener() { 
			  		  public void actionPerformed(ActionEvent e) {
			  			    
			  				eleccio = posicioActual;
				  			infoCarta.setBackground(Color.decode("#D0BE78"));
				  			if(tanterior!=null)
				  				tanterior.setBackground(Color.decode("#4F4F4F"));
				  			tanterior=infoCarta;
	
						} });
				infoCarta.add(cartaPos);
				infoCarta.add(carta);
				infoCarta.add(botoCanvi);
				panCartes.add(infoCarta);
			}
			panell.add(panCartes);
		}
		
	}
	/**
     * @pre --
     * @post omple el panell amb els botons de la finestra
     * @param p panell principal on omplirem botons
     */
	private void OmplirBotons(JPanel panell){
		JPanel pboto = new JPanel();
		pboto.setBackground(Color.decode("#4F4F4F"));
		JButton demana = new JButton("Seleccionar");
		demana.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose();
				setVisible(false);
			}} );
		pboto.add(demana);
		panell.add(pboto);
	}
	
	/**
     * @pre existeixen cartes a seleccionar
     * @post Seleccionem la carta desti
     * @return retorna la posicio de la carta escollida
     */
	public int seleccionar(){
		setLocationRelativeTo(null);
		setVisible(true);
		return eleccio;
		
	}
	/**
     * @pre ajusta la mida d'una imatge donada
     * @post retorna una imatge amb mida ajustada
     * @param original imatge origina
     * @param ample amplada desitjada
     * @param llarg desitjat
     * @return Imatge
     */
	private BufferedImage ajustaMidaImatge(BufferedImage original, int ample, int llarg) throws IOException {  
	    BufferedImage midaNova = new BufferedImage(ample, llarg, BufferedImage.TYPE_INT_RGB);  
	    Graphics2D g = midaNova.createGraphics();  
	    g.drawImage(original, 0, 0, ample, llarg, null);  
	    g.dispose();  
	    return midaNova;  
	}
 
}