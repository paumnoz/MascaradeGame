package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;



import jugador.Jugador;

//Descripcio: Finestra que serveix per a seleccionar un jugador objectiu d'alguna jugada

public class FinestraDemanarJugador extends JDialog {
	
	private String s;
	
	private LinkedList<Jugador> js;//llista de jugadors
	private JLabel[] texts;// array de etiquetes per identificar noms de jugadors
	int eleccio;//jugador escollit
	int torn;//jugador actual
        private boolean tauler_;
	
	/**
     * @pre actual >= 0
     * @post genera una finestra util per a seleccionar jugador
     * @param st text informatiu mostra motiu per a seleccionar jugador
     * @param jugs llista de jugadors
     * @param actual jugador actual
     */
	public FinestraDemanarJugador(String st,LinkedList<Jugador> jugs, int actual, boolean intercanvi, boolean tauler) {
		
		setTitle("Seleccionar el jugador objectiu");
		s = st;
		js = jugs;
		texts = new JLabel[jugs.size()+1];
		torn = actual;
                tauler_ = tauler;
		
		JPanel panell = new JPanel();
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		panell.setBackground( Color.decode("#4F4F4F") );
	    
    
	    
	    
	    afegirImatges(panell);
	    getContentPane().add(panell, BorderLayout.CENTER);
	    setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 

	    
	}
	/**
     * @pre --
     * @post obtenim el numero del jugador seleccionat(index)
     * @param return retorna lindex del jugador escollit
     */
	public int seleccionar(){
		setLocationRelativeTo(null);
		setVisible(true);
		return eleccio;
	}
 
	
	/**
     * @pre --
     * @post afegim imatges de jugadors al panell
     * @param panell panell on afegirem imatges representatives de jugadors
     */
	private void afegirImatges(JPanel pan){
		JPanel panell = new JPanel();
	    panell.setBackground( Color.decode("#4F4F4F") );
	    int i = 0;
		for(i=0; i < js.size(); i++){
			if(i!=torn){
				Box c = Box.createVerticalBox();
				texts[i] = new JLabel("Jugador: "+js.get(i).getNom());
				texts[i].setForeground(Color.decode("#E4B841"));
				BufferedImage ljug = null;
				JLabel ijug = null; 
				try{
					ljug = ImageIO.read(new File("jugadormenu.jpg"));
					ljug = ajustaMidaImatge(ljug,100,100);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ijug = new JLabel(new ImageIcon(ljug),JLabel.CENTER);
				c.add(ijug);
				c.add(texts[i]);
				final int pos = i;
				c.addMouseListener(new MouseListener() {
	
					public void mouseClicked(MouseEvent e) {
						if(texts[pos].getForeground()==Color.red){
							texts[pos].setForeground(Color.decode("#E4B841"));
						
						}
						else{
							texts[pos].setForeground(Color.red);
							eleccio = js.get(pos).getId();
						}
					}
	
					@Override
					public void mouseEntered(MouseEvent arg0) {
					
					}
	
					@Override
					public void mouseExited(MouseEvent arg0) {
					
					}
	
					@Override
					public void mousePressed(MouseEvent arg0) {
					
					}
	
					@Override
					public void mouseReleased(MouseEvent arg0) {
					
					
					}});
				
				panell.add(c);
				}
			}
			
                        if(tauler_){
                            Box tau= Box.createVerticalBox();
                            texts[i] = new JLabel("Tauler");
                            texts[i].setForeground(Color.decode("#E4B841"));
                            BufferedImage ltau = null;
                            JLabel itau = null; 

                            try{
                                    ltau = ImageIO.read(new File("jugadormenu.jpg"));
                                    ltau = ajustaMidaImatge(ltau,100,100);
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                            itau = new JLabel(new ImageIcon(ltau),JLabel.CENTER);
                            tau.add(itau);
                            tau.add(texts[i]);
                            final int ptau = i;
                            tau.addMouseListener(new MouseListener() {

                                    public void mouseClicked(MouseEvent e) {
                                            if(texts[ptau].getForeground()==Color.red){
                                                    texts[ptau].setForeground(Color.decode("#E4B841"));

                                            }
                                            else{
                                                    texts[ptau].setForeground(Color.red);
                                                    eleccio = ptau;
                                            }
                                    }

                                    @Override
                                    public void mouseEntered(MouseEvent arg0) {

                                    }

                                    @Override
                                    public void mouseExited(MouseEvent arg0) {

                                    }

                                    @Override
                                    public void mousePressed(MouseEvent arg0) {

                                    }

                                    @Override
                                    public void mouseReleased(MouseEvent arg0) {


                                    }});
                            panell.add(tau);
                        }
			
			JPanel pafegir = new JPanel();
			pafegir.setBackground( Color.decode("#4F4F4F") );
    		JButton Afegir = new JButton("Seleccionar Jugador "+s);
    		Afegir.addActionListener(new ActionListener() { 
    			public void actionPerformed(ActionEvent e) { 
  			  		if(eleccio >= 0){
  			  			dispose();
  			  			setVisible(false);
  			  		}
    			}} );
    		pafegir.add(Afegir);
    		pan.add(panell);
    		pan.add(pafegir);
    		
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
