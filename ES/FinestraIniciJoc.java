package ES;

import principal.Joc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rol.*;


//Descripcio: Finestra principal encarregada de demanar les dades basiques de la partida i inicialitzar el propi joc
public class FinestraIniciJoc extends JDialog {
	
	private Joc j;//joc a inicialitzar!
	
	//Components basics de la finestra
	private JTextField numJugadorsHumans;
	private JTextField numJugadorsMaquina;
	private JSlider sliderJugadors;
	private JSlider sliderMaquines;
	private JTextField numMonedesJoc;
	private JTextField numMonedesVictoria;
	private JCheckBox mode;
	
	private ArrayList<Rol> rols = new ArrayList<Rol>();//Llista de rols de la partida
	private ArrayList<String> nomJugadors =  new ArrayList<String>();//Llista de noms de jugadors
	/**
     * @pre joc no es troba inicialitzat
     * @post genera una finestra que l'usuari usara per inicialitzar el joc
     * @param joc joc a inicialitzar
     */
    public FinestraIniciJoc(Joc joc) {
    	j=joc;
    	setTitle("Mascarade - El joc");
        setSize(685, 800);
        setLocationRelativeTo(null);
       
        getContentPane().setBackground( Color.decode("#4F4F4F") );
        Box contenidor = Box.createVerticalBox();
        
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menu.setBackground( Color.decode("#4F4F4F") );
        menu.setPreferredSize(new Dimension(685, 800));
        menu.setMaximumSize(new Dimension(685, 800));
        menu.setAlignmentX(LEFT_ALIGNMENT);
        
        JPanel botonsMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonsMenu.setBackground(Color.decode("#4F4F4F"));

        botonsMenu.setPreferredSize(new Dimension(500, 500));
        botonsMenu.setMaximumSize(new Dimension(500, 500));
        
        botonsMenu.setLayout(new BoxLayout(botonsMenu, BoxLayout.Y_AXIS));
        OmpleMenu(menu,botonsMenu);
        botonsMenu.setBorder(new EmptyBorder(10, 150, 10, 0));
        
        contenidor.setAlignmentX(LEFT_ALIGNMENT);
        contenidor.add(menu);
        
        add(contenidor);
        setModal(true);
    }
    /**
     * @pre --
     * @post afegeix els botons a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     * @param menu panell principal de la finestra
     */
    private void OmpleMenu(JPanel menu, JPanel botonsMenu){
    	BufferedImage logo = null;
		JLabel imatgeLogo = null; 
    	try{
			logo = ImageIO.read(new File("mascarade.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	imatgeLogo = new JLabel(new ImageIcon(logo),JLabel.CENTER);
    	menu.add(imatgeLogo);
    	OmpleBotonsMenu(botonsMenu);
    	menu.add(botonsMenu);
    }
    /**
     * @pre --
     * @post afegeix els botons a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     */
    private void OmpleBotonsMenu(JPanel botonsMenu){
    	
    	AfegeixJugadors(botonsMenu);
    	AfegeixMonedes(botonsMenu);
    	AfegeixRols(botonsMenu);
    	AfegeixJoc(botonsMenu);
    }
    /**
     * @pre --
     * @post afegeix els botons a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     */
    private void AfegeixJugadors(JPanel botonsMenu){
    	//Etiqueta jugadors humans
    	JLabel textJugadorsHumans = new JLabel("Jugadors humans:");
    	textJugadorsHumans.setForeground(Color.decode("#E4B841"));
    	
    	
    	//JUGADORS
    	Box jugadorsHumans = Box.createHorizontalBox();
    	jugadorsHumans.add(textJugadorsHumans);
    	sliderJugadors = new JSlider(JSlider.HORIZONTAL,1,10,3);
    	sliderJugadors.setBackground(Color.decode("#4F4F4F"));
    	sliderJugadors.setMajorTickSpacing(24);
    	sliderJugadors.setMinorTickSpacing(5);
    	sliderJugadors.setPaintLabels(true);
    	sliderJugadors.setPaintTicks(true);
    	sliderJugadors.setPreferredSize(new Dimension(10,10));
    	sliderJugadors.setForeground(Color.decode("#E4B841"));
    	numJugadorsHumans = new JTextField(2);
    	numJugadorsHumans.setMaximumSize(new Dimension(25,25));
    	numJugadorsHumans.setText("2");
    	sliderJugadors.addChangeListener(new ChangeListener(){
    		public void stateChanged(ChangeEvent event) {
    			String factor = String.valueOf(sliderJugadors.getValue());
    			numJugadorsHumans.setText(factor);
    	      }
    	    });
    	
    	jugadorsHumans.add(sliderJugadors);
    	
    	
    	jugadorsHumans.add(numJugadorsHumans);
    	
    	botonsMenu.add(jugadorsHumans);
    	
    	//Etiqueta jugadors maquines
    	JLabel textJugadorsMaquina = new JLabel("Jugadors màquina:");
    	textJugadorsMaquina.setForeground(Color.decode("#E4B841"));
    	//Slider de maquines
    	Box jugadorsMaquina = Box.createHorizontalBox();
    	jugadorsMaquina.add(textJugadorsMaquina);
    	sliderMaquines = new JSlider(JSlider.HORIZONTAL,1,10,3);
    	sliderMaquines.setBackground(Color.decode("#4F4F4F"));
    	sliderMaquines.setMajorTickSpacing(25);
    	sliderMaquines.setMinorTickSpacing(5);
    	sliderMaquines.setPaintLabels(true);
    	sliderMaquines.setPaintTicks(true);
    	sliderMaquines.setForeground(Color.decode("#E4B841"));
    	sliderMaquines.setPreferredSize(new Dimension(10,10));
    	sliderMaquines.addChangeListener(new ChangeListener(){
    		public void stateChanged(ChangeEvent event) {
    			String factor = String.valueOf(sliderMaquines.getValue());
    			numJugadorsMaquina.setText(factor);
    	      }
    	    });
    	jugadorsMaquina.add(sliderMaquines);
    	
    	numJugadorsMaquina = new JTextField(2);
    	numJugadorsMaquina.setMaximumSize(new Dimension(25,25));
    	numJugadorsMaquina.setText("0");
    	jugadorsMaquina.add(numJugadorsMaquina);
    	botonsMenu.add(jugadorsMaquina);
    	
    }
    /**
     * @pre --
     * @post afegeix les monedes a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     */
    private void AfegeixMonedes(JPanel botonsMenu){
    	Box monedesJoc = new Box(BoxLayout.X_AXIS);
    	
    	JLabel textMonedesJoc = new JLabel("Monedes en joc");
    	textMonedesJoc.setForeground(Color.decode("#E4B841"));
    	monedesJoc.add(textMonedesJoc);

    	numMonedesJoc = new JTextField(2);
    	numMonedesJoc.setMaximumSize(new Dimension(25,25));
    	numMonedesJoc.setText("6");

    	monedesJoc.add(numMonedesJoc);
    	
    	
    	JButton reduirMonedes = new JButton("-");
    	reduirMonedes.addActionListener(new ActionListener() { 
    		  public void actionPerformed(ActionEvent e) { 
    			  	if(numMonedesJoc.getText()!=null){
    			  		int factor = Integer.parseInt(numMonedesJoc.getText());
    			  		factor--;
    			  		if(factor>=0)
    			  			numMonedesJoc.setText(String.valueOf(factor));
    			  	}
    			  	
    			  } 
    			} );
    	monedesJoc.add(reduirMonedes);
    	JButton augmentarMonedes = new JButton("+");
    	augmentarMonedes.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
  			  	if(numMonedesJoc.getText()!=null){
  			  		int factor = Integer.parseInt(numMonedesJoc.getText());
  			  		factor++;
  			  		if(factor>=0)
  			  			numMonedesJoc.setText(String.valueOf(factor));
  			  	}
  			  	
  			  } 
  			} );

    	monedesJoc.add(augmentarMonedes);
    	monedesJoc.setOpaque(true);

    	monedesJoc.setBorder(new EmptyBorder(10, 0, 10, 130));
    	botonsMenu.add(monedesJoc);

    	
    	JLabel textMonedesGuanyar = new JLabel("Monedes per guanyar");
    	textMonedesGuanyar.setForeground(Color.decode("#E4B841"));
    	Box monedesGuanyar = Box.createHorizontalBox();
    	monedesGuanyar.add(textMonedesGuanyar);
    	numMonedesVictoria = new JTextField(2);
    	numMonedesVictoria.setMaximumSize(new Dimension(25,25));
    	numMonedesVictoria.setText("13");
    	monedesGuanyar.add(numMonedesVictoria);
    	JButton reduirMonedesF = new JButton("-");
    	reduirMonedesF.addActionListener(new ActionListener() { 
    		  public void actionPerformed(ActionEvent e) { 
    			  	if(numMonedesVictoria.getText()!=null){
    			  		int factor = Integer.parseInt(numMonedesVictoria.getText());
    			  		factor--;
    			  		if(factor>=0)
    			  			numMonedesVictoria.setText(String.valueOf(factor));
    			  	}
    			  	
    			  } 
    			} );
    	monedesGuanyar.add(reduirMonedesF);
    	JButton augmentarMonedesF = new JButton("+");
    	augmentarMonedesF.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
  			  	if(numMonedesVictoria.getText()!=null){
  			  		int factor = Integer.parseInt(numMonedesVictoria.getText());
  			  		factor++;
  			  		if(factor>=0)
  			  			numMonedesVictoria.setText(String.valueOf(factor));
  			  	}
  			  	
  			  } 
  			} );
    	monedesGuanyar.add(augmentarMonedesF);
    	monedesGuanyar.setBorder(new EmptyBorder(10, 0, 10, 80));
    	botonsMenu.add(monedesGuanyar);
    }
    /**
     * @pre --
     * @post afegeix els botons de selec. rol a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     */
    private void AfegeixRols(JPanel botonsMenu){
    	JLabel textRols = new JLabel("Seleccionar rols:");
    	textRols.setForeground(Color.decode("#E4B841"));
    	Box lrols = Box.createHorizontalBox();
    	lrols.add(textRols);
    	JButton seleccionarRols = new JButton("Entrar al seleccionador");
    	seleccionarRols.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) {

  			  	if(!numJugadorsHumans.getText().equals("") && !numJugadorsMaquina.getText().equals("")){
  			  		int humans = Integer.parseInt(numJugadorsHumans.getText());
  			  		int maquines = Integer.parseInt(numJugadorsMaquina.getText());
  			  		FinestraSeleccioRols fr = new FinestraSeleccioRols(rols,humans+maquines);
  			  	}
			  	
			  } 
			} );
    	lrols.add(seleccionarRols);
    	lrols.setBorder(new EmptyBorder(10, 0, 10, 260));
    	botonsMenu.add(lrols);
    	
    }
    /**
     * @pre --
     * @post afegeix els botons de inici joc a la finestra
     * @param botonsMenu panell principal de botons de la finestra
     */
    private void AfegeixJoc(JPanel botonsMenu){
    	Box opcionsPartida = Box.createHorizontalBox();
    	
    	botonsMenu.add(opcionsPartida);
    	JButton inici = new JButton("Iniciar la partida");
    	inici.addActionListener(new ActionListener() { 
    		  public void actionPerformed(ActionEvent e) {
    			int humans = Integer.parseInt(numJugadorsHumans.getText());
    			int maquines = Integer.parseInt(numJugadorsMaquina.getText());
  			  	
  			  	int monedesJoc = Integer.parseInt(numMonedesJoc.getText());
  			  	int monedesGuanyar = Integer.parseInt(numMonedesVictoria.getText());  	
  			  	
  			  	int njug = humans+maquines;
  			  	boolean rolsOk = ( (njug==2 || njug==3) && rols.size()>=6) || (njug>=4 && rols.size()>=4);
  			  	if(monedesJoc>=1 && njug>=2 && monedesGuanyar>monedesJoc && rolsOk){
	  			    FinestraNoms fr = new FinestraNoms(nomJugadors,humans);
	  			    int dif = 0;
	  			    
	  			    if(!numJugadorsMaquina.getText().equals("") && !numJugadorsMaquina.getText().equals("0")){
	  			    	FinestraDificultat fd = new FinestraDificultat();
	  			    	dif = fd.seleccionar();
	  			    }
	  			    
	  			    
	  			  	j.inicialitzarJoc(humans, maquines, monedesJoc, monedesGuanyar, rols, dif);
	  			  	
	  			    for(int i = 0; i < humans; i++){
	  			    	j.getJugador(i).setNom(nomJugadors.get(i));
	  			    }
	  			    if(maquines>0){
		  			    for(int i = humans; i < humans+maquines; i++){
		  			    	j.getJugador(i).setNom("CPU_"+(i+1));
		  			    }
	  			    }
	  			  	dispose();
	  			  	setVisible(false);
  			  	}
  			  } 
  			} );
    	opcionsPartida.add(inici);
    	opcionsPartida.setBorder(new EmptyBorder(10, 0, 10, 260));

    }
 
    


}