package ES;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jugador.Jugador;

import principal.*;

//Descripcio; Finestra del menu del jugador, permet escollir quina accio realitzara en el seu torn
public class FinestraMenuJugador extends JDialog {
	
	private Jugador jug;
	private int op;
	private boolean lim;
	/**
     * @pre --
     * @post  crea la finestra
     * @param j jugador actual
     * @param lim indica si la finestra es troba limitada o no a nomes les jugades basiques inici 3 jugadors(limita accio rol)
     */
	public FinestraMenuJugador(Jugador j, boolean limit){
		lim=limit;
		jug = j;
		JPanel panell = new JPanel();
		panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
		panell.setPreferredSize(new Dimension(300, 350));
        panell.setMaximumSize(new Dimension(300, 350));
		panell.setBackground(Color.decode("#4F4F4F"));
		
        AfegirInformacio(panell);
        AfegirBotons(panell);
        
        getContentPane().add(panell, BorderLayout.CENTER);
		
		setModal(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	}
	
	/**
     * @pre --
     * @post retorna la opcio de joc escollida
     * @return opcio de joc escollida
     */
	public int opcio(){
		setLocationRelativeTo(null);
	    setVisible(true);
	    return op;
	}
	/**
     * @pre --
     * @post  afegim la informacio sobre jugades al panell
     * @param panell panell principal de joc
     */
	private void AfegirInformacio(JPanel p){
		
		BufferedImage jugador = null;

    	try{
			jugador = ImageIO.read(new File("jugadormenu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	JLabel imgJugador = new JLabel(new ImageIcon(jugador),JLabel.CENTER);
    	
    	p.add(imgJugador);
    	
    	JLabel nomJugador = new JLabel("Jugador: "+jug.getNom()+" Monedes: "+String.valueOf(jug.monedes()));
    	nomJugador.setForeground(Color.decode("#E4B841"));
    	nomJugador.setBorder(new EmptyBorder(10, 10, 10, 10));
    	
    	p.add(nomJugador);
		
	}
	/**
     * @pre --
     * @post  afegim la informacio sobre botons al panell
     * @param panell panell principal de joc
     */
	private void AfegirBotons(JPanel p){
		
		Box menu = Box.createVerticalBox();
		JButton consultar = new JButton("Consultar carta");
		JButton intercanviar = new JButton("Intercanviar carta");
		JButton baccio = new JButton("Accio de rol");
		
		consultar.addActionListener(new ActionListener() { 
	  		  public void actionPerformed(ActionEvent e) { 
				  op = 1;
	  			  dispose();
	  			  setVisible(false);
				  	
				  } 
				} );
		
		intercanviar.addActionListener(new ActionListener() { 
	  		  public void actionPerformed(ActionEvent e) { 
	  			
	  			  op=2;
	  			  
	  			  dispose();
	  			  setVisible(false);
				  	
				  } 
				} );
		
		baccio.addActionListener(new ActionListener() { 
	  		  public void actionPerformed(ActionEvent e) { 
				  
	  			  op=3;
	  			  dispose();
	  			  setVisible(false);
				  	
				  } 
				} );
		
		menu.setBorder(new EmptyBorder(10, 10, 10, 10));
		menu.add(consultar);
		menu.add(Box.createRigidArea(new Dimension(5,0)));
		menu.add(intercanviar);
		menu.add(Box.createRigidArea(new Dimension(5,0)));
		if(!lim)
			menu.add(baccio);
		
		p.add(menu);
	}
	

}
