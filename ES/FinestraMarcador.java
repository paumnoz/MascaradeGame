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
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rol.Bisbe;
import rol.Rol;

import jugador.Jugador;
import rol.*;

//Descripcio: Finestra marcador, conte info dels jugadors, el nom i les monedes que disposen

public class FinestraMarcador extends JDialog {
	
	

	private JLabel[] textJ;//text de jugadors

	private LinkedList<Jugador> jugadors;//jugadors de partida
	
	private ArrayList<Rol> rls;
	
	private JLabel mpalau;
	
	private static final int amp_=131;//amplada foto
    private static final int alt_=200;//altura foto

    
	/**
     * @pre --
     * @param jugs llista de jugadors de la partida
     * @post genera finestra dels jugadors
     */
	public FinestraMarcador(LinkedList<Jugador> jugs,ArrayList<Rol> rols, int mf, int mp, int mLim) {
		setTitle("Marcador de la partida");
		setBackground( Color.decode("#4F4F4F") );
		//setPreferredSize(new Dimension(600, 600));

		jugadors = jugs;
		rls = rols;
		
		JPanel panell = new JPanel();
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		
		JPanel ptitol = new JPanel();
		JLabel titol = new JLabel("<html><h2>Marcador de joc</h2></html>");
		titol.setForeground(Color.red);
		ptitol.add(titol);
		ptitol.setForeground(Color.decode("#E4B841"));
		ptitol.setBackground( Color.decode("#4F4F4F") );
		
		panell.add(ptitol);
		JPanel jugadors = new JPanel();
		jugadors.setBackground( Color.decode("#4F4F4F") );
		AfegirJugadors(jugadors);

		panell.add(jugadors);	
                
		JPanel monedesf = new JPanel();
		JLabel mg = new JLabel("Monedes per guanyar: "+String.valueOf(mf)+"  Monedes Limit : "+String.valueOf(mLim));
		mg.setForeground(Color.red);
		monedesf.add(mg);
		
		JPanel monedesp = new JPanel();
		mpalau = new JLabel("Monedes palau: "+String.valueOf(mp));
		mpalau.setForeground(Color.red);
		monedesp.setForeground(Color.red);
		monedesp.add(mpalau);
		monedesp.setBackground( Color.decode("#4F4F4F") );
		
		panell.add(monedesp);
                
		monedesf.setBackground( Color.decode("#4F4F4F") );
		panell.add(monedesf);
		
		OmplirRols(panell);
		//AfegirSortir(panell);
		
		//&setLocationRelativeTo(null);
		getContentPane().add(panell);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setVisible(true);

	}
	/**
     * @pre --
     * @param panell panell principal de la finestra
     * @post afegim boto de sortida del joc
     */
	public void AfegirSortir(JPanel panell){
		JPanel psortir = new JPanel();
		JButton pbt = new JButton("Sortir del joc");
		pbt.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			  	System.exit(0);
			}} );
		psortir.add(pbt);
		panell.add(psortir);
		
	}

	/**
     * @pre --
     * @post marcador actualitzat amb monedes de jugadors i els propis jugadors inicialment
     */
	private void AfegirJugadors(JPanel p){
		BufferedImage jugador = null;

    	try{
			jugador = ImageIO.read(new File("huma.jpg"));
			jugador = ajustaMidaImatge(jugador,150,150);
		} catch (IOException e) {
			e.printStackTrace();
		}

    	
    	Box[] contenidorJ= new Box[jugadors.size()];
    	textJ= new JLabel[jugadors.size()];
    	JLabel[] imgJugador = new JLabel[jugadors.size()];
    	
    	for(int i = 0; i < jugadors.size(); i++){
    		contenidorJ[i] = Box.createVerticalBox();
    		imgJugador[i] = new JLabel(new ImageIcon(jugador),JLabel.CENTER);
    		contenidorJ[i].add(imgJugador[i]);
    		textJ[i] = new JLabel("Monedes "+jugadors.get(i).getNom()+": "+String.valueOf(jugadors.get(i).monedes()));
    		textJ[i].setForeground(Color.decode("#E4B841"));
    		contenidorJ[i].add(textJ[i]);
    		p.add(contenidorJ[i]);
    	}
	}
	/**
     * @pre --
     * @param panell panell a omplir amb els rols de la partida
     * @post panell s'omple amb els rols de la partida
     */
	private void OmplirRols(JPanel panell){
		JPanel p = new JPanel();
		p.setBackground( Color.decode("#4F4F4F") );
		JPanel ptit = new JPanel();
		JLabel text = new JLabel("Rols seleccionats al inici per al joc: ");
		ptit.add(text);
		panell.add(ptit);
		for(Rol r: rls){
			
			if(r instanceof Bisbe){
				Box bisbe = Box.createVerticalBox();
                JLabel tbisbe = new JLabel("Bisbe");
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
				p.add(bisbe);
			}
			else if(r instanceof Bruixa){
				Box bruixa = Box.createVerticalBox();
                JLabel tbruixa = new JLabel("Bruixa");
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
				p.add(bruixa);
			}
			else if(r instanceof Bufo){
				Box bufo = Box.createVerticalBox();
                JLabel tbufo = new JLabel("Bufo");
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
				p.add(bufo);
				
			}
			else if(r instanceof Camperol){
				Box camperol = Box.createVerticalBox();
                JLabel tcamperol = new JLabel("Camperol");
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
				p.add(camperol);
			}
			else if(r instanceof Espia){
				Box espia = Box.createVerticalBox();
                JLabel tespia = new JLabel("Espia");
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
				p.add(espia);
			}
			else if(r instanceof Inquisidor){
				Box inquisidor = Box.createVerticalBox();
                JLabel tinquisidor = new JLabel("Inquisidor");
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
				p.add(inquisidor);
			}
			else if(r instanceof Jutge){
				Box jutge = Box.createVerticalBox();
                JLabel tjutge = new JLabel("Jutge");
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
				p.add(jutge);
			}
			else if(r instanceof Lladre){
				Box lladre = Box.createVerticalBox();
                JLabel tlladre = new JLabel("Lladre");
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
				p.add(lladre);
				
			}
			else if(r instanceof Maso){
				Box maso = Box.createVerticalBox();
                JLabel tmaso = new JLabel("Maso");
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
				p.add(maso);
			}
			else if(r instanceof Puta){
				Box puta = Box.createVerticalBox();
                JLabel tputa = new JLabel("Puta");
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
				p.add(puta);
				
			}
			else if(r instanceof Rei){
				Box rei = Box.createVerticalBox();
                JLabel trei = new JLabel("Rei");
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
				p.add(rei);
			}
			else if(r instanceof Reina){
				Box reina = Box.createVerticalBox();
                JLabel treina = new JLabel("Reina");
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
				p.add(reina);
			}
			else if(r instanceof Trampos){
				Box trampos = Box.createVerticalBox();
                JLabel ttrampos = new JLabel("Trampos");
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
				p.add(trampos);
			}
			else if(r instanceof Viuda){
				Box viuda = Box.createVerticalBox();
                JLabel tviuda = new JLabel("Viuda");
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
				p.add(viuda);
			}
			panell.add(p);
			
			
		}
	}

	
	/**
     * @pre --
     * @post marcador actualitzat amb monedes de jugadors
     */
	public void ActualitzarMarcador(int monedespalau){
		for(int i = 0; i <jugadors.size(); i++){
			textJ[i].setText("Monedes "+jugadors.get(i).getNom()+": "+String.valueOf(jugadors.get(i).monedes()));
		}
		mpalau.setText("Monedes palau: "+String.valueOf(monedespalau));
		
	}
	
	/**
     * @pre --
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

