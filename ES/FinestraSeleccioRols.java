package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.*;




import rol.*;



//Descripcio: Finestra que ens permet  obtenir una llista de rols a jugar a la partida
public class FinestraSeleccioRols extends JDialog {
	
	//lista de rols a omplir
	private ArrayList<Rol> arols;
	//etiquetes amb noms dels rols
	private JLabel tbisbe;
	private JLabel tbruixa;
	private JLabel tbufo;
	private JLabel tcamperol;
	private JLabel tespia;
	private JLabel tinquisidor;
	private JLabel tjutge;
	private JLabel tlladre;
	private JLabel trei;
	private JLabel treina;
	private JLabel ttrampos;
	private JLabel tviuda;
	private JLabel tmaso;
	private JLabel tputa;
	
    private static final int amp_=131;//amplada finestra
    private static final int alt_=200;//altura finestra
    
    private int njug;
	
    /**
     * @pre --
     * @post genera la finestra per a escollir rols
     * @param rols llista de rols a omplir amb mes d'un rol

     */
	public FinestraSeleccioRols( ArrayList<Rol> rols, int njugadors) {
		
            setTitle("Selecciona rols de partida");
            arols = rols;
            njug = njugadors;
	    setLocation(600,600);
	    setPreferredSize(new Dimension(1000, 600));
	    setBackground( Color.decode("#4F4F4F") );
    
	    JPanel imatges = new JPanel();
	    imatges.setBackground( Color.decode("#4F4F4F") );
	    afegirImatges(imatges);
	    getContentPane().add(imatges, BorderLayout.CENTER);
	    setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
 
	
	/**
     * @pre --
     * @post afegeix imatges de rols al panell
     * @param panell panell principal de la finestra
     */
	private void afegirImatges(JPanel panell){
		Box bisbe = Box.createVerticalBox();
		tbisbe = new JLabel("Bisbe");
		tbisbe.setForeground(Color.red);
		BufferedImage lbisbe = null;
		JLabel ibisbe = null; 
    	try{
			lbisbe = ImageIO.read(new File("bisbe.jpg"));
			lbisbe = ajustaMidaImatge(lbisbe,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ibisbe = new JLabel(new ImageIcon(lbisbe),JLabel.CENTER);
    	bisbe.add(ibisbe);
    	bisbe.add(tbisbe);
    	bisbe.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(tbisbe.getForeground()==Color.red)
    	    		tbisbe.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		tbisbe.setForeground(Color.red);
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

    	panell.add(bisbe);
    	
    	Box bruixa = Box.createVerticalBox();
		tbruixa = new JLabel("Bruixa");
		tbruixa.setForeground(Color.red);
		BufferedImage lbruixa = null;
		JLabel ibruixa = null; 
    	try{
			lbruixa = ImageIO.read(new File("bruixa.jpg"));
			lbruixa = ajustaMidaImatge(lbruixa,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ibruixa = new JLabel(new ImageIcon(lbruixa),JLabel.CENTER);
    	
    	bruixa.add(ibruixa);
    	bruixa.add(tbruixa);
    	panell.add(bruixa);
    	bruixa.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(tbruixa.getForeground()==Color.red)
    	    		tbruixa.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		tbruixa.setForeground(Color.red);
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
    	
    	Box bufo = Box.createVerticalBox();
		tbufo = new JLabel("Bufo");
		tbufo.setForeground(Color.red);
		BufferedImage lbufo = null;
		JLabel ibufo = null; 
    	try{
			lbufo = ImageIO.read(new File("bufo.jpg"));
			lbufo = ajustaMidaImatge(lbufo,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ibufo = new JLabel(new ImageIcon(lbufo),JLabel.CENTER);
    	
    	bufo.add(ibufo);
    	bufo.add(tbufo);
    	panell.add(bufo);
    	bufo.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(tbufo.getForeground()==Color.red)
    	    		tbufo.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		tbufo.setForeground(Color.red);
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
    	if(njug>=6){
	    	Box camperol = Box.createVerticalBox();
			tcamperol = new JLabel("Camperol");
			tcamperol.setForeground(Color.red);
			BufferedImage lcamperol = null;
			JLabel icamperol = null; 
	    	try{
				lcamperol = ImageIO.read(new File("camperol.jpg"));
				lcamperol = ajustaMidaImatge(lcamperol,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	icamperol = new JLabel(new ImageIcon(lcamperol),JLabel.CENTER);
	    	
	    	camperol.add(icamperol);
	    	camperol.add(tcamperol);
	    	panell.add(camperol);
	    	camperol.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tcamperol.getForeground()==Color.red)
	    	    		tcamperol.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tcamperol.setForeground(Color.red);
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
    	}
    	if(njug>=4){
	    	Box espia = Box.createVerticalBox();
			tespia = new JLabel("Espia");
			tespia.setForeground(Color.red);
			BufferedImage lespia = null;
			JLabel iespia = null; 
	    	try{
				lespia = ImageIO.read(new File("espia.jpg"));
				lespia = ajustaMidaImatge(lespia,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	iespia = new JLabel(new ImageIcon(lespia),JLabel.CENTER);
	    	
	    	espia.add(iespia);
	    	espia.add(tespia);
	    	panell.add(espia);
	    	espia.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tespia.getForeground()==Color.red)
	    	    		tespia.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tespia.setForeground(Color.red);
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
    	}
    	if(njug>=6){
	    	Box inquisidor = Box.createVerticalBox();
			tinquisidor = new JLabel("Inquisidor");
			tinquisidor.setForeground(Color.red);
			BufferedImage linquisidor = null;
			JLabel iinquisidor = null; 
	    	try{
				linquisidor = ImageIO.read(new File("inquisidor.jpg"));
				linquisidor = ajustaMidaImatge(linquisidor,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	iinquisidor = new JLabel(new ImageIcon(linquisidor),JLabel.CENTER);
	    	
	    	inquisidor.add(iinquisidor);
	    	inquisidor.add(tinquisidor);
	    	panell.add(inquisidor);
	    	inquisidor.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tinquisidor.getForeground()==Color.red)
	    	    		tinquisidor.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tinquisidor.setForeground(Color.red);
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
    	}
    	Box jutge = Box.createVerticalBox();
		tjutge = new JLabel("Jutge");
		tjutge.setForeground(Color.red);
		BufferedImage ljutge = null;
		JLabel ijutge = null; 
    	try{
			ljutge = ImageIO.read(new File("jutge.jpg"));
			ljutge = ajustaMidaImatge(ljutge,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ijutge = new JLabel(new ImageIcon(ljutge),JLabel.CENTER);
    	
    	jutge.add(ijutge);
    	jutge.add(tjutge);
    	panell.add(jutge);
    	tjutge.setForeground(Color.red);
    	jutge.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(tjutge.getForeground()==Color.red)
    	    		tjutge.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		tjutge.setForeground(Color.red);
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
        if(njug>=3){
	    	Box lladre = Box.createVerticalBox();
			tlladre = new JLabel("Lladre");
			tlladre.setForeground(Color.red);
			BufferedImage llladre = null;
			JLabel illadre = null; 
	    	try{
				llladre = ImageIO.read(new File("lladre.jpg"));
				llladre = ajustaMidaImatge(llladre,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	illadre = new JLabel(new ImageIcon(llladre),JLabel.CENTER);
	    	
	    	lladre.add(illadre);
	    	lladre.add(tlladre);
	    	panell.add(lladre);
	        
	    	lladre.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tlladre.getForeground()==Color.red)
	    	    		tlladre.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tlladre.setForeground(Color.red);
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
        }
        if(njug>=6){
	        Box maso = Box.createVerticalBox();
			tmaso = new JLabel("Maso");
			tmaso.setForeground(Color.red);
			BufferedImage lmaso = null;
			JLabel imaso = null; 
	    	try{
				lmaso = ImageIO.read(new File("maso.jpg"));
				lmaso = ajustaMidaImatge(lmaso,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	imaso = new JLabel(new ImageIcon(lmaso),JLabel.CENTER);
	    	
	    	maso.add(imaso);
	    	maso.add(tmaso);
	    	panell.add(maso);
	    	maso.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tmaso.getForeground()==Color.red)
	    	    		tmaso.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tmaso.setForeground(Color.red);
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
        }
        if(njug>=4){
	        Box puta = Box.createVerticalBox();
			tputa = new JLabel("Puta");
			tputa.setForeground(Color.red);
			BufferedImage lputa = null;
			JLabel iputa = null; 
	    	try{
				lputa = ImageIO.read(new File("puta.jpg"));
				lputa = ajustaMidaImatge(lputa,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	iputa = new JLabel(new ImageIcon(lputa),JLabel.CENTER);
	    	
	    	puta.add(iputa);
	    	puta.add(tputa);
	    	panell.add(puta);
	    	puta.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(tputa.getForeground()==Color.red)
	    	    		tputa.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		tputa.setForeground(Color.red);
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
    	
        }
    	Box rei = Box.createVerticalBox();
		trei = new JLabel("Rei");
		trei.setForeground(Color.red);
		BufferedImage lrei = null;
		JLabel irei = null; 
    	try{
			lrei = ImageIO.read(new File("rei.jpg"));
			lrei = ajustaMidaImatge(lrei,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	irei = new JLabel(new ImageIcon(lrei),JLabel.CENTER);
    	
    	rei.add(irei);
    	rei.add(trei);
    	panell.add(rei);
    	rei.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(trei.getForeground()==Color.red)
    	    		trei.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		trei.setForeground(Color.red);
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
    	Box reina = Box.createVerticalBox();
		treina = new JLabel("Reina");
		treina.setForeground(Color.red);
		BufferedImage lreina = null;
		JLabel ireina = null; 
    	try{
			lreina = ImageIO.read(new File("reina.jpg"));
			lreina = ajustaMidaImatge(lreina,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ireina = new JLabel(new ImageIcon(lreina),JLabel.CENTER);
    	
    	reina.add(ireina);
    	reina.add(treina);
    	panell.add(reina);
    	reina.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(treina.getForeground()==Color.red)
    	    		treina.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		treina.setForeground(Color.red);
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
    	if(njug>=4){
	    	Box trampos = Box.createVerticalBox();
			ttrampos = new JLabel("Trampos");
			ttrampos.setForeground(Color.red);
			BufferedImage ltrampos = null;
			JLabel itrampos = null; 
	    	try{
				ltrampos = ImageIO.read(new File("trampos.jpg"));
				ltrampos = ajustaMidaImatge(ltrampos,amp_,alt_);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	itrampos = new JLabel(new ImageIcon(ltrampos),JLabel.CENTER);
	    	
	    	trampos.add(itrampos);
	    	trampos.add(ttrampos);
	    	panell.add(trampos);
	    	trampos.addMouseListener(new MouseListener() {
	
	    	    public void mouseClicked(MouseEvent e) {
	    	    	if(ttrampos.getForeground()==Color.red)
	    	    		ttrampos.setForeground(Color.decode("#E4B841"));
	    	    	else
	    	    		ttrampos.setForeground(Color.red);
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
    	}
    	Box viuda = Box.createVerticalBox();
		tviuda = new JLabel("Viuda");
		tviuda.setForeground(Color.red);
		BufferedImage lviuda = null;
		JLabel iviuda = null; 
    	try{
			lviuda = ImageIO.read(new File("viuda.jpg"));
			lviuda = ajustaMidaImatge(lviuda,amp_,alt_);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	iviuda = new JLabel(new ImageIcon(lviuda),JLabel.CENTER);
    	
    	viuda.add(iviuda);
    	viuda.add(tviuda);
    	panell.add(viuda);
    	viuda.addMouseListener(new MouseListener() {

    	    public void mouseClicked(MouseEvent e) {
    	    	if(tviuda.getForeground()==Color.red)
    	    		tviuda.setForeground(Color.decode("#E4B841"));
    	    	else
    	    		tviuda.setForeground(Color.red);
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

            
    	JButton Afegir = new JButton("Afegir rols seleccionats");
    	Afegir.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) {
                	
                        if(tbisbe  != null && tbisbe.getForeground()==Color.red){
                                arols.add(new Bisbe());
                        }
                        if(tbruixa != null &&tbruixa.getForeground()==Color.red){
                                arols.add(new Bruixa());
                        }
                        if(tbufo != null && tbufo.getForeground()==Color.red){
                                arols.add(new Bufo());
                        }
                        if(tcamperol != null && tcamperol.getForeground()==Color.red){
                                arols.add(new Camperol());
                                arols.add(new Camperol());
                        }
                        if(tespia  != null && tespia.getForeground()==Color.red){
                                arols.add(new Espia());
                        }
                        if(tinquisidor  != null && tinquisidor.getForeground()==Color.red){
                                arols.add(new Inquisidor());
                        }
                        if(tjutge.getForeground()==Color.red){
                                arols.add(new Jutge());
                        }
                        if(tlladre  != null && tlladre.getForeground()==Color.red){
                                arols.add(new Lladre());
                        }
                        if(tmaso  != null && tmaso.getForeground()==Color.red){
                                arols.add(new Maso());
                        }
                        if(tputa  != null && tputa.getForeground()==Color.red){
                                arols.add(new Puta());
                        }
                        if(trei.getForeground()==Color.red){
                                arols.add(new Rei());
                        }
                        if(treina.getForeground()==Color.red){
                                arols.add(new Reina());
                        }
                        if(ttrampos  != null && ttrampos.getForeground()==Color.red){
                                arols.add(new Trampos());
                        }
                        if(tviuda.getForeground()==Color.red){
                                arols.add(new Viuda());
                        }
                    //}
                  if(( (njug==2 || njug==3) && arols.size()>=6) || (njug>=4 && arols.size()>=4) ){
                	  dispose();
                  	  setVisible(false);
                  }
  			}} );
    	panell.add(Afegir);
	}
	/**
     * @pre --
     * @post obtenim els rols seleccionats
     * @param llista de rols seleccionats
     */
        public ArrayList<Rol> getRols(){
            return arols;
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
