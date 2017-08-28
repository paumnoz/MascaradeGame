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
	import java.util.ArrayList;
	
	
	import javax.imageio.ImageIO;
	import javax.swing.Box;
	import javax.swing.BoxLayout;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JDialog;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	
	
	import rol.*;
	//Descripcio: Finestra d'accio de rol ens permet escollir quin rol volem jugar com a jugadors de la partida.
	//Seleccionem el rol desitjat d'una llista d'imatges i noms que representen els rols i el juguem prement un boto.
	//L'objectiu d'aquesta finestra es retornar el rol seleccionat per a jugar!
	
	public class FinestraAccioRol extends JDialog{
		
		
		//etiquetes dels rols a seleccionar
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
		private JLabel tputa;
                private JLabel tmaso;
		
		//llista de rols a jugar(rols de partida)
                private ArrayList<Rol> rols_;
                private boolean mostrarMaso_;
            
	    //rols anterior i actual per a marcar
	    //rol anterior
		private JLabel tanterior = null;
		//rol actual
		private Rol seleccionat;
	        
	        private static final int amp_=100;
	        private static final int alt_=150;
	
	        
	        /**
	         * @pre existeixcen rols i la partida de joc esta en marxa
	         * @post construeix la finestra amb els rols posibles a jugar
	         * @param rols llista de rols de la partida
	         */
		public FinestraAccioRol(ArrayList<Rol> rols, boolean mostrarMaso){
			setTitle("Seleciona accio de rol");
	                rols_=rols;
                        mostrarMaso_=mostrarMaso;
			JPanel panell = new JPanel();
			panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
			panell.setBackground(Color.decode("#4F4F4F"));
			
			OmplirRols(panell);
	
			OmplirBotons(panell);
			
			getContentPane().add(panell, BorderLayout.CENTER);
			
			setModal(true);
		    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		    pack(); 
		    
		}
		/**
	     * @pre la partida del mascarade esta en marxa i tenim rols per jugar
	     * @post obtenim el rol seleccionat
	     * @return retorna el rol seleccionat
	     */
		public Rol seleccionar(){
			setLocationRelativeTo(null);
			setVisible(true);
			return seleccionat;
		}
		/**
	     * @pre existeixen rols a la clase
	     * @post omple el panell de rols amb els posibles rols a seleccionar
	     * @param p panell principal on omplirem rols
	     */
		private void OmplirRols(JPanel p){
	            JPanel panellRols = new JPanel();
	            panellRols.setBackground(Color.decode("#4F4F4F"));
	            boolean camperolMostrat=false;    
	            for(Rol r: rols_){
	                if(r instanceof Bisbe){
	                    Box bisbe = Box.createVerticalBox();
	                            tbisbe = new JLabel("Bisbe");
	                            tbisbe.setForeground(Color.decode("#E4B841"));
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
	                            else{
	                                    tbisbe.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                            tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tbisbe;
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
	
	                        panellRols.add(bisbe);
	                }
	                else if(r instanceof Bruixa){
	                    Box bruixa = Box.createVerticalBox();
	                            tbruixa = new JLabel("Bruixa");
	                            tbruixa.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(bruixa);
	                    bruixa.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tbruixa.getForeground()==Color.red)
	                                    tbruixa.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tbruixa.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                            tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tbruixa;
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
	                }
	                else if(r instanceof Bufo){
	                    Box bufo = Box.createVerticalBox();
	                            tbufo = new JLabel("Bufo");
	                            tbufo.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(bufo);
	                    bufo.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tbufo.getForeground()==Color.red)
	                                    tbufo.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tbufo.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tbufo;
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
	                }
	                else if(r instanceof Camperol && !camperolMostrat){
	                    camperolMostrat = true;
	                    Box camperol = Box.createVerticalBox();
	                            tcamperol = new JLabel("Camperol");
	                            tcamperol.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(camperol);
	                    camperol.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tcamperol.getForeground()==Color.red)
	                                    tcamperol.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tcamperol.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tcamperol;
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
	                }
	                else if(r instanceof Espia){
	                    Box espia = Box.createVerticalBox();
	                            tespia = new JLabel("Espia");
	                            tespia.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(espia);
	                    espia.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tespia.getForeground()==Color.red)
	                                    tespia.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tespia.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tespia;
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
	                }
	                else if(r instanceof Inquisidor){
	                    Box inquisidor = Box.createVerticalBox();
	                            tinquisidor = new JLabel("Inquisidor");
	                            tinquisidor.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(inquisidor);
	                    inquisidor.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tinquisidor.getForeground()==Color.red)
	                                    tinquisidor.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tinquisidor.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tinquisidor;
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
	                }
	                else if(r instanceof Jutge){
	                    Box jutge = Box.createVerticalBox();
	                            tjutge = new JLabel("Jutge");
	                            tjutge.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(jutge);
	                    jutge.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tjutge.getForeground()==Color.red)
	                                    tjutge.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tjutge.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tjutge;
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
	                }
	                else if(r instanceof Lladre){
	                    Box lladre = Box.createVerticalBox();
	                            tlladre = new JLabel("Lladre");
	                            tlladre.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(lladre);
	                    lladre.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tlladre.getForeground()==Color.red)
	                                    tlladre.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tlladre.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = tlladre;
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
	                }
                        else if(r instanceof Maso && mostrarMaso_){
                            Box maso = Box.createVerticalBox();
                                    tmaso = new JLabel("Maso");
                                    tmaso.setForeground(Color.decode("#E4B841"));
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
                            panellRols.add(maso);
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
                        
	                else if(r instanceof Puta){
	                            Box puta = Box.createVerticalBox();
	                            tputa = new JLabel("Puta");
	                            tputa.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(puta);
	                    puta.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tputa.getForeground()==Color.red)
	                                    tputa.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tputa.setForeground(Color.red);
	                                    //tanterior.setForeground(Color.decode("#E4B841"));
	                                    //tanterior = tputa;
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
	                }
	                else if(r instanceof Rei){
	                    Box rei = Box.createVerticalBox();
	                            trei = new JLabel("Rei");
	                            trei.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(rei);
	                    rei.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(trei.getForeground()==Color.red)
	                                    trei.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    trei.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = trei;
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
	                }
	                else if(r instanceof Reina){
	                    Box reina = Box.createVerticalBox();
	                            treina = new JLabel("Reina");
	                            treina.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(reina);
	                    reina.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(treina.getForeground()==Color.red)
	                                    treina.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    treina.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = treina;
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
	                }
	                else if(r instanceof Trampos){
	                    Box trampos = Box.createVerticalBox();
	                            ttrampos = new JLabel("Trampos");
	                            ttrampos.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(trampos);
	                    trampos.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(ttrampos.getForeground()==Color.red)
	                                    ttrampos.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    ttrampos.setForeground(Color.red);
	                                    if(tanterior!=null)
	                                    tanterior.setForeground(Color.decode("#E4B841"));
	                                    tanterior = ttrampos;
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
	                }
	                else if(r instanceof Viuda){
	                    Box viuda = Box.createVerticalBox();
	                            tviuda = new JLabel("Viuda");
	                            tviuda.setForeground(Color.decode("#E4B841"));
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
	                    panellRols.add(viuda);
	                    viuda.addMouseListener(new MouseListener() {
	
	                        public void mouseClicked(MouseEvent e) {
	                            if(tviuda.getForeground()==Color.red)
	                                    tviuda.setForeground(Color.decode("#E4B841"));
	                            else{
	                                    tviuda.setForeground(Color.red);
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
	                }

	            }
			p.add(panellRols);
		}
		/**
	     * @pre --
	     * @post omple el panell de la finestra amb els botons de seleccio
	     * @param p panell principal de la finestra
	     */
		private void OmplirBotons(JPanel p){
			JPanel panellBotons = new JPanel();
			panellBotons.setBackground(Color.decode("#4F4F4F"));
			
			JButton Afegir = new JButton("Realitzar accio de rol");
	    	Afegir.addActionListener(new ActionListener() { 
	    		  public void actionPerformed(ActionEvent e) { 
	                        if(tbisbe != null &&tbisbe.getForeground()==Color.red){
	                            seleccionat=(new Bisbe());
	                        }
	                        else if(tbruixa != null &&tbruixa.getForeground()==Color.red){
	                            seleccionat=(new Bruixa());
	                        }
	                        else if(tbufo != null &&tbufo.getForeground()==Color.red){
	                            seleccionat=(new Bufo());
	                        }
	                        else if(tcamperol != null && tcamperol.getForeground()==Color.red){
	                            seleccionat=(new Camperol());
	                        }
	                        else if(tespia != null &&tespia.getForeground()==Color.red){
	                            seleccionat=(new Espia());
	                        }
	                        else if(tinquisidor != null &&tinquisidor.getForeground()==Color.red){
	                            seleccionat=(new Inquisidor());
	                        }
	                        else if(tjutge != null &&tjutge.getForeground()==Color.red){
	                            seleccionat=(new Jutge());
	                        }
	                        else if(tlladre != null &&tlladre.getForeground()==Color.red){
	                            seleccionat=(new Lladre());
	                        }
	                        else if(trei != null &&trei.getForeground()==Color.red){
	                            seleccionat=(new Rei());
	                        }
	                        else if(treina != null &&treina.getForeground()==Color.red){
	                            seleccionat=(new Reina());
	                        }
	                        else if(ttrampos != null &&ttrampos.getForeground()==Color.red){
	                            seleccionat=(new Trampos());
	                        }
	                        else if(tviuda != null &&tviuda.getForeground()==Color.red){
	                            seleccionat=(new Viuda());
	                        }
	                        else if(tputa != null &&tputa.getForeground()==Color.red){
	                            seleccionat=(new Puta());
	                        }
                                else if(tmaso != null &&tmaso.getForeground()==Color.red){
                                    seleccionat=(new Maso());
                                }
	    		  
	    		  dispose();
	    		  setVisible(false);
	  			}} );
	    	panellBotons.add(Afegir);
			
			p.add(panellBotons);
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
