package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import principal.*;



import jugador.*;



//Descripcio: Finestra que mostra totes les cartes d'un jugador per pantalla
public class FinestraMostrarCartes extends JDialog implements ActionListener {
	
	private LinkedList<Carta> cj;
	private Jugador jugador;//jugador actual
	private JPanel Cartes;//panell de cartes
    private static final int amp_=300;//amplada finestra
    private static final int alt_=500;//altura finestra
    /**
     * @pre --
     * @post genera la finestra
     * @param jug jugador actual
     */
  public FinestraMostrarCartes(Jugador jug,LinkedList<Carta> cartesj) {
	jugador = jug;
	if(cartesj!=null)
	    	cj=cartesj;
	    
	if(jugador!=null)
		setTitle("Jugador num: "+String.valueOf(jug.getId())+"("+jug.getNom()+")");
	else
		setTitle("Cartes del tauler");
    JPanel cartes = new JPanel();
    JLabel ma = new JLabel("Llista de cartes");
    ma.setForeground(Color.decode("#E4B841"));
    cartes.add(ma);
    getContentPane().add(cartes);
    JPanel panell = new JPanel();
    panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
    JPanel pboto = new JPanel();
    JButton button = new JButton("FET"); 
    
    button.addActionListener(this);
    
    Cartes = new JPanel();
    cartes.setBackground( Color.decode("#4F4F4F") );
    Cartes.setBackground( Color.decode("#4F4F4F") );
    panell.setBackground( Color.decode("#4F4F4F") );
    MostrarCartes();
    panell.add(Cartes);
    pboto.add(button);
    pboto.setBackground( Color.decode("#4F4F4F") );
    panell.add(pboto); 
    getContentPane().add(panell, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack(); 
    setModal(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  /**
   * @pre --
   * @post  tanquem la finestra
   * @param e esdeveniment a escoltar
   */
  public void actionPerformed(ActionEvent e) {
	
    setVisible(false); 
    dispose(); 
  }

  /**
   * @pre --
   * @post  Afegim les cartes a la finestra
   */
  private void MostrarCartes(){
	    
		LinkedList<Carta> cartesj;
		if(cj==null){
			cartesj = jugador.cartes();
		}
		else{
			cartesj = cj;
		}
		for(Carta c: cartesj) {
			BufferedImage logo = null;
			JLabel carta = null; 
			if(c.getRol().toString()=="Bisbe"){
				
				try{
					logo = ImageIO.read(new File("bisbe.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Rei"){
				try{
					logo = ImageIO.read(new File("rei.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Reina"){
				try{
					logo = ImageIO.read(new File("reina.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Lladre"){
				try{
					logo = ImageIO.read(new File("lladre.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Jutge"){
				try{
					logo = ImageIO.read(new File("jutge.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Viuda"){
				try{
					logo = ImageIO.read(new File("viuda.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Bruixa"){
				try{
					logo = ImageIO.read(new File("bruixa.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
				
			}
			else if(c.getRol().toString()=="Bufo"){
				try{
					logo = ImageIO.read(new File("bufo.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Camperol"){
				try{
					logo = ImageIO.read(new File("camperol.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Espia"){
				try{
					logo = ImageIO.read(new File("espia.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Inquisidor"){
				try{
					logo = ImageIO.read(new File("inquisidor.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Trampos"){
				try{
					logo = ImageIO.read(new File("trampos.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Maso"){
				try{
					logo = ImageIO.read(new File("maso.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			else if(c.getRol().toString()=="Puta"){
				try{
					logo = ImageIO.read(new File("puta.jpg"));
					logo = resizeImage(logo,amp_,alt_,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    carta = new JLabel(new ImageIcon(logo),JLabel.CENTER);
			
				
			}
			
			Cartes.add(carta);
			
		}
	}
  /**
   * @pre ajusta la mida d'una imatge donada
   * @post retorna una imatge amb mida ajustada
   * @param original imatge origina
   * @param ample amplada desitjada
   * @param llarg desitjat
   * @return Imatge
   */
  private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {  
	    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	    Graphics2D g = resizedImage.createGraphics();  
	    g.drawImage(originalImage, 0, 0, width, height, null);  
	    g.dispose();  
	    return resizedImage;  
	}
}
