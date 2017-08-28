package ES;

import principal.*;
import rol.*;
import java.util.*;
import jugador.*;




public class ES{
    
    private static Joc _joc;

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat el joc amb l'input desitjat
    */
    private static void inicialitzarAmbDades(){
    	FinestraIniciJoc f = new FinestraIniciJoc(_joc);
        f.setVisible(true);
        mostrarCartes(_joc.getJugadors().size()); //Mostrem cartes inicials
    }

    /**
    *   @pre Cert
    *   @post Pregunta a tots els jugadors si volen protestar l'accio de Rol a realtizar
    *///FET
    public static void preguntarProtestes(Jugador jug, int posCartaJug, LinkedList<Carta> c, LinkedList<Jugador> j, LinkedList<Integer> pos, Rol rol, int torn, int nJugadors){
    	
        FinestraMostrar fmost = new FinestraMostrar("El jugador " + _joc.getJugador(torn).getNom() + " vol executar l'accio de " + rol.toString() + " amb la carta " + 0);
      
        //Hi afegim el jugador victima (a protestes)
        j.add(jug); //Jugador victima
        c.add(jug.getCarta(0)); //Carta del jugador victima
        pos.add(posCartaJug);
        //Preguntem a la resta
        for(int i = 0; i < nJugadors; i++){
            if(i != torn){
                jug = _joc.getJugador(i);
                if(jug instanceof Maquina){ //Si és maquina..

                	Jugador vict = _joc.getJugador(torn);
                    int posCarta = ((Maquina)jug).protestar(vict, rol.toString(), _joc);
                    if(posCarta >=0){
                        j.add(jug);
                        c.add(jug.getCarta(posCarta));
                        pos.add(posCarta);
                        FinestraMostrar fmosprot = new FinestraMostrar("El jugador CPU_" + (i+1) + " ha protestat amb la carta: "+posCarta);
                    }
                    else{
                        FinestraMostrar fmosprot = new FinestraMostrar("El jugador CPU_" + (i+1) + " no ha protestat.");
                    }

                }
                else{
                    FinestraSiNo fsino = new FinestraSiNo("Jugador " + _joc.getJugador(i).getNom() + ", vols protestar?");
                    int protesta = fsino.seleccionar();
                    if(protesta == 1){
                        //El jugador vol protestar
                        j.add(jug);
                        int nCarta = 0;
                        if(jug.cartes().size() > 1)
                        	nCarta = demanarCarta(jug.getId(), false, 3);
                        pos.add(nCarta);
                        c.add(jug.getCarta(nCarta));
                    }
                }
            }
        }
    }


    /**
    *   @pre Cert
    *   @post Mostra les cartes de tots els jugadors i tauler
    */
    public static void mostrarCartes(int nJugadors){
    	for(int i = 0; i < nJugadors; i++){ // 4 = nJugadors -> mostrarCartesJugadors
            FinestraMostrarCartes fc = new FinestraMostrarCartes(_joc.getJugador(i),null);
        }
    	FinestraMostrarCartes fc = new FinestraMostrarCartes(null,_joc.getCartes());
    	
    }

    /**
    *   @pre Cert
    *   @post Mostra la informació bàsica d'un jugador
    */
    public static void infoJugador(int n, Jugador j){
    	String jt;

        jt = "Juga el jugador "+ _joc.getJugador(n-1).getNom() +" ("+ j.monedes() + " monedes).";
        
        mostrar(jt); 
    }

    /**
    *   @pre Cert
    *   @post Mostra la informació desitjada al Log
    */
    public static void mostrar(String s){//FET
    	FinestraMostrar fmos = new FinestraMostrar(s);
    }

    /**
    *   @pre Cert
    *   @post Retorna l'acció que el jugador vol realitzar
    */    
    public static int preguntarAccio(Jugador j){//FET
    	int accio = 0;
    	if(_joc.getJugadors().size() >= 4 && j.haConsultat(0)){
    		FinestraMenuJugador fmenu = new FinestraMenuJugador(j,true);
    		accio = fmenu.opcio();
    	}
    	else{
    		FinestraMenuJugador fmenu = new FinestraMenuJugador(j,false);
        	accio = fmenu.opcio();
    	}

        return accio;
    }

    /**
    *   @pre Cert
    *   @post Mostra les cartes despres d'una protesta
    */  
    public static void mostrarCartesProtesta(LinkedList<Carta> c, LinkedList<Jugador> j, Rol rol, LinkedList<Jugador> rao){//FET
    	//Mostrem les cartes
        Iterator<Carta> it = c.iterator();
        for(Jugador jug: j){
            Carta cart= it.next();
            FinestraMostrar fmos = new FinestraMostrar("El jugador "+jug.getNom()+" tenia la carta "+cart.getRol().toString()+".");
            //System.out.println("El jugador "+(jug.getId()+1)+" tenia la carta "+cart.getRol().nomRol()+".");
        }
        if(!rao.isEmpty()){
            for(Jugador jug: rao){
                FinestraMostrar fmos = new FinestraMostrar("Tenia rao el jugador " +jug.getNom()+".");
                //System.out.println("Tenia rao el jugador " + (jug.getId()+1)+".");
            }
        }
        else{
            FinestraMostrar fmos = new FinestraMostrar("NINGU TENIA RAO");
        }
    }
    //getId
      
    
    /**
     *  
     */
    public static Rol demanarRol(boolean mostrarMaso){//FET
    	FinestraAccioRol frol = new FinestraAccioRol(_joc.getRols(), mostrarMaso);
        return frol.seleccionar();
    }
    
    /**
    *   @pre n > torn
    *   @post Demana al jugador amb quin altre jugador vol realitzar l'accio (intercanvi)
    */  
    public static int demanarJugador(int n, int torn, boolean intercanviar){
    	String text;
        if(_joc.getJugadors().size()<4)
            torn=_joc.getJugadors().size()+1; //els jugadors poden intercanviarse la carta
        if(intercanviar){
            text = "per a intercanviar carta";
            FinestraDemanarJugador fjug = new FinestraDemanarJugador(text,_joc.getJugadors(),torn-1,false, true);
            return fjug.seleccionar()+1;
        }
        else{
            text = "per a consultar carta";
            FinestraDemanarJugador fjug = new FinestraDemanarJugador(text,_joc.getJugadors(),torn-1,false, true);
            return fjug.seleccionar()+1;
        }
        
    }
    
    /**
    *   @pre Cert (opcions 0==intercanviar, 1==mirar, 2==jugar=
    *   @post Demana quina carta del jugador/tauler vol.
    */  
    public static int demanarCarta(int nJug, boolean tauler, int opcio){
    	int eleccio = 0;
        String nom=null;
        if(opcio==0) //intercanviar carta
            nom="intercanviar";
        else if(opcio==1)//mirar carta
            nom="mirar";
        else if(opcio==2)
            nom="jugar";
        else
            nom="protestar";
        if(tauler){
            FinestraDemanaCarta fdem1 = new FinestraDemanaCarta(_joc.getCartes().size(),nom,-1,null);
            eleccio = fdem1.seleccionar();
        }
        else{
            FinestraDemanaCarta fdem2 = new FinestraDemanaCarta(_joc.getJugador(nJug).cartes().size(),nom,opcio,_joc.getJugador(nJug));
            eleccio = fdem2.seleccionar();
        }
        return eleccio;
    }
    
    /**
    *   @pre Cert
    *   @post 1 si vol un intercanvio real. 0 altrament
    */  
    public static int real(){
    	FinestraSimularIntercanvi fsim = new FinestraSimularIntercanvi();
        return fsim.seleccionar();
    }
    
    /**
     *  @pre Cert
     *  @post retorna un array d'int on la pos 0 es el jugador que ha guanyat la subhasta i la posicio 1 lo que ha pagat, si no ha guanyat ningu retorna null
     */
    public static int[] subhasta(Jugador player, LinkedList<Jugador> pujadors){
    	int []JugadorPaga=new int[2];
        int monedes=0;
        Jugador mesPujat=null;
        FinestraMostrar fmost = new FinestraMostrar("INICIEM LA SUBHASTA!");
        for(Jugador j: pujadors){
            if(j.getId()!=player.getId() && j.monedes()>1){
            	if(j instanceof Maquina){
                    int nouMonedes = ((Maquina)player).subhasta(_joc, monedes);
                    if(nouMonedes > monedes){
                        mesPujat = j;
                        monedes = nouMonedes;
                    }
                }
            	else{
            		FinestraSubhasta fsubasta = new FinestraSubhasta("Jugador: "+j.getNom()+" Quantes monedes vols pujar? ");
            		int i = fsubasta.selecciona();
            		while(j.monedes()<=i){// comprovem que no es passi, com a mínim li ha de quedar 1 moneda
            			FinestraSubhasta fsubasta2 = new FinestraSubhasta(j.getNom()+" No tens suficients monedes, torna a afegir diners");
            			i=fsubasta2.selecciona();
            		}
            		if(i>monedes){
            			mesPujat=j;
            			monedes=i;
            		}
                }
            }
        }
        
        if(mesPujat!=null){
            FinestraMostrar fmost1 = new FinestraMostrar("SUBHASTA FINALITZADA!" + "El Jugador: "+mesPujat.getNom()+" ha guanyat la subhasta");
            JugadorPaga[0]=mesPujat.getId();
            JugadorPaga[1]=monedes;
        }
        else{
            FinestraMostrar fmost1 = new FinestraMostrar("SUBHASTA FINALITZADA!" + "Ningu ha pujat");
            JugadorPaga=null;
        }
        return JugadorPaga;
    }

    /**
     *  @pre Cert
     *  @post retorna la id+1 d'un jugador escollit
     */
    public static int escollirJug(LinkedList<Jugador> jugs, int jug){ //FET
    	FinestraDemanarJugador fjug = new FinestraDemanarJugador("",jugs,jug,false, false);
        return fjug.seleccionar()+1;
    }
    
    /**
     *  @pre Cert
     *  @post retorna els jugadors manifestat com a camperols, si no hi ha cap retorna null
     */
    public static LinkedList<Jugador> demanarCamperols(LinkedList<Jugador> jugs, Jugador jug){//FET
    	LinkedList<Jugador> manifestats= new LinkedList<Jugador>();
        for(Jugador j: jugs){
            if(j != jug){
				if(j instanceof Maquina){
					if(((Maquina)j).rolPropi().toString().equals("Camperol"))
						manifestats.add(j);
				}
				else{
					FinestraSiNo fman = new FinestraSiNo(jug.getNom()+" et manifestes com a camperol?");
					if(fman.seleccionar()>=1)
						manifestats.add(j);
				}
            }
        }
        if(manifestats.isEmpty())
            return null;
        else
            return manifestats;
    }
 
    //Programa principal
    /**
    * @pre Cert
    * @post S'ha jugat una partida del mascarade
    */
    public static void main(String arg[]){
    	_joc = new Joc();
        inicialitzarAmbDades(); //També mostra com s'ha inicialitzat
        FinestraMarcador marcador = new FinestraMarcador(_joc.getJugadors(),_joc.getRols(),_joc.monedesFinals(),_joc.monedesPalau(), _joc.getMonedesLimit());
        
        //Comenca el joc
        while(_joc.getEstat() != 2){
            _joc.jugarTorn();
            marcador.ActualitzarMarcador(_joc.monedesPalau());
           
        }
        //Joc acabat!
        FinestraMostrar fmos = new FinestraMostrar("Joc acabat, puntuacions finals:<br>"+_joc.puntuacionsFinals());
    }
}
