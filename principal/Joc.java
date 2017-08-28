package principal;

/**
*   @class Joc
*   @brief Gestiona un joc del mascarade
*   @details Un joc del mascarade. Invariant: Minim 2 jugadors i 6 cartes
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import java.util.*;
import accio.*;
import rol.*;
import jugador.*;
import ES.*;

public class Joc{

    private LinkedList<Jugador> _jugadors;  ///< Array de jugadors
    private LinkedList<Carta> _cartesJoc; ///< Cartes del tauler de joc
    private int _nMonedesFinals; ///< Nombre de monedes per a guanyar
    private int _nMonedesPalauJusticia; ///< Monedes del palau de justicia
    private int _estatJoc; ///< 0 = no inicialitzat, 1 = en joc, 2 = acabat
    private int _ronda; ///< Ronda actual
    private ArrayList<Rol> _rols; ///< Rols que formen part del joc: Per a donar més informació a la ES

    //Constructors
    
    /**
     *	@pre  Cert
     *	@post Constructor per defecte
     */
    public Joc(){
        //Constructor per defecte
        //Inicialitzem llistes
        _jugadors = new LinkedList<Jugador>();
        _cartesJoc = new LinkedList<Carta>();
        _estatJoc = 0;
        _nMonedesPalauJusticia = 0; //Comencem amb 0 monedes
        _ronda = 0;
    }

    //Metodes publics
    
    /**
    *	@pre  Cert
    *	@post Retorna els rols que formen part del joc
    *	@return ArrayList<Rol>
    */
    public ArrayList<Rol> getRols(){
    	return _rols;
    }

    /**
    *	@pre  1 < nJugR+nJugA < 14
    *	@post S'ha inicialitzat el joc amb les cartes inicials repartides als jugadors
    *	@param nJugR Nombre de jugadors reals
    *	@param nJugA Nombre de jugadors artificials
    *	@param nMonI Nombre de monedes inicials
    *	@param nMonF Nombre de monedes finals
    *	@param rols Rols que formen part de la partida
    *	@param dificultat Dificultat dels jugadors màquina 
    */
    public void inicialitzarJoc(int nJugR, int nJugA, int nMonI, int nMonF, ArrayList<Rol> rols, int dificultat){
    	//Afegim totes les cartes al tauler (en un principi)
    	_rols = rols;
        for(int i = 0; i < rols.size(); i++){
        	_cartesJoc.add(new Carta(rols.get(i)));
        }
        //Monedes
        _nMonedesFinals = nMonF;
        //Creem jugadors
        for(int i = 0; i < nJugR+nJugA; i++){
            if(i < nJugR)
                _jugadors.add(new Jugador(nMonI, i));
            else
                _jugadors.add(new Maquina(nMonI, i, nJugR+nJugA, _cartesJoc.size(), _cartesJoc, dificultat));
        }
        //Repartir cartes
        repartirCartesInicials();
        _estatJoc = 1;
        //Avans de fer qualsevol accio, li assignem aquest joc
        Accio.setJoc(this);
    }

    /**
    *   @pre estat del joc = 1
    *   @post S'ha jugat un torn del joc
    */
    public void jugarTorn(){
        Jugador jAct = getJugador(_ronda % _jugadors.size());
        ES.infoJugador(_ronda % _jugadors.size() + 1, jAct); //Mostrem informació del jugador
        if(jAct instanceof Maquina){
            jugarTornMaquina((Maquina)jAct);
        }
        else
            jugarTornReal(jAct);
        
        //Sumem per restriccions
        for(Jugador j: _jugadors){
            if(j.monedes() == 0)
                _estatJoc = 2;
            j.sumarConsultat();
        }
        _ronda++;
    }

    /**
    *   @pre estat del joc = 1
    *   @post S'ha jugat un torn del joc (jugMaquina)
    *   @param m Maquina que juga el torn
    */
    private void jugarTornMaquina(Maquina m){
        Accio accio = m.jugar(this, _ronda < 4);
        if(accio instanceof AccioRol)
            accioRol(accio, m);
        else if(accio instanceof Intercanvi)
            intercanviar(accio, m);
        else if(accio instanceof Consulta)
            ES.mostrar(m.consultarCarta(m.cartes().indexOf(((Consulta)accio).carta())));
    }

    /**
    *   @pre estat del joc = 1
    *   @post S'ha jugat un torn del joc (jugador normal)
    *   @param j Jugador que juga el torn
    */
    private void jugarTornReal(Jugador j){
        if(_ronda < 4){
            ES.mostrar("Nomes pots intercanviar.");
            intercanviar(null, j);
        }
        else{
            int accio = ES.preguntarAccio(j);
            if(accio == 1){
            	int pos = 0;
            	if(j.cartes().size() > 1)
            		pos = ES.demanarCarta(j.getId(), false, 1);
            	ES.mostrar(j.consultarCarta(pos));
            }
            else if(accio == 2)
                intercanviar(null, j);
            else
                accioRol(null, j);
        }
    }

    /**
    *   @pre estat del joc = 1
    *   @post S'ha realitzat un intercanvi
    *   @param accio Acció a realitzar
    *   @param j Jugador que fa l'acció
    */
    private void intercanviar(Accio accio, Jugador j){
        if(j instanceof Maquina){
            Intercanvi inter = ((Intercanvi)accio);
            inter.realitzarIntercanvi();
        }
        else{
            int carta1 = 0;
            int carta2 = 0;
            LinkedList<Carta> llista2;
            //Demanem jugador
            int jugador2 = ES.demanarJugador(_jugadors.size(), _ronda % _jugadors.size() + 1, true);
            //Demanem carta
            if(_jugadors.size() <= 3){
            	carta1 = ES.demanarCarta(j.getId(), false, 0);
            }
            if(jugador2 == _jugadors.size()+1){ //Si és tauler..
            	if(_cartesJoc.size() > 1)
                    carta2 = ES.demanarCarta(jugador2-1, true, 0);
                llista2 = _cartesJoc;
            }
            else{
            	if(_jugadors.size() <= 3)
                    carta2 = ES.demanarCarta(jugador2-1, false, 0);
            	llista2 = getJugador(jugador2-1).cartes();
            }
            int real = ES.real();
            j.intercanviCarta(carta1, llista2, carta2, jugador2-1, real == 1);
        }
    }

    /**
    *   @pre estat del joc = 1
    *   @post S'ha realitzat una acció de Rol
    *   @param accio Acció a realitzar
    *   @param j Jugador que fa l'acció
    */
    private void accioRol(Accio accio, Jugador j){
        LinkedList<Carta> cartes = new LinkedList<>();
        LinkedList<Jugador> jugadors = new LinkedList<>();
        LinkedList<Integer> pos = new LinkedList<>();
        Rol r;
        int posJug = 0;
        if(!(j instanceof Maquina)){
            r = ES.demanarRol(false);
            if(j.cartes().size() > 1)
                posJug = ES.demanarCarta(j.getId(), false, 2);
        }
        else{
            AccioRol aR = (AccioRol)accio;
            r = aR.rol();
        }
        ES.preguntarProtestes(j, posJug, cartes, jugadors, pos, r, _ronda % _jugadors.size(), _jugadors.size()); //La E/S Pregunta
        if(jugadors.size() > 1) //Si és més gran de 1 vol dir que, a més de la victima, algu reclama el rol
            protestar(jugadors, cartes, pos, r);
        else{
            ES.mostrar("Ningu protesta, es realitza l'accio de rol");
            ES.mostrar(j.jugarRol(r, this));
            if(j.monedes() >= _nMonedesFinals)
                _estatJoc = 2;
        }
    }

    /**
    *   @return int
    *   @pre Cert
    *   @post Retorna l'estat del joc
    *   Tipus d'estats:
    *   -> 0 = Joc NO inicialitzat. NO podem començar la partida ni realitzar cap accio.
    *   -> 1 = Joc s'esta jugant. NO podem mostrar puntuacions ni afegir noves cartes ni jugadors.
    *   -> 2 = Joc finalitzat. PODEM mostrar puntuacions, no podem jugar cap torn ni accio.
    */
    public int getEstat(){
        return _estatJoc;
    }

    /**
    *	@pre Joc finalitzat (estat = 2)
    *	@post Retorna un string amb les puntuacions amb format Jugador - Monedes (Ordenat per monedes)
    *	@return String
    */
    public String puntuacionsFinals(){
        Collections.sort(_jugadors);
        String ret = "";
        for(Jugador j: _jugadors)
            ret += "Jugador " + j.getNom() + " - " + j.monedes() + " monedes<br>";
        return ret;
    }

    /**
    *	@pre idJug ha de ser un identificador vàlid
    *	@post Retorna el jugador associat a l'identificador
    *	@param idJug jugador a obtenir
    *	@return Jugador
    */
    public Jugador getJugador(int idJug){
        return _jugadors.get(idJug);
    }
    
    /**
     * 	@return LinkedList<Jugador>
     *  @pre Cert
     *  @post retorna una LinkedList de jugadors
     */
    public LinkedList<Jugador> getJugadors(){
        return _jugadors;
    }

    /**
    *	@pre Cert
    *	@post Retorna les monedes necessaries per a guanyar la partida
    *	@return int
    */
    public int monedesFinals(){
        return _nMonedesFinals;
    }

    /**
    *	@return Jugador
    *	@pre Cert
    *	@post Retorna el jugador més ric (Cas no controlat: EMPAT)
    * 	@param jug Busca excloent aquest jugador
    *   @param guany Cert si el metode s'esta executant sota una comprovació de guany
    */
    public Jugador jugadorMesRic(Jugador jug, boolean guany){
        LinkedList<Jugador> mesRics = new LinkedList<Jugador>();
        for(Jugador j: _jugadors){
            if(jug != j){
                if(!mesRics.isEmpty()){
                    if(j.monedes() > mesRics.getFirst().monedes()){
                        mesRics.clear();
                        mesRics.add(j);
                    }
                    else if(j.monedes() == mesRics.getFirst().monedes())
                        mesRics.add(j);
                }
                else{
                    mesRics.add(j);
                }
            }
        }
        if(mesRics.size()==1)
            return mesRics.getFirst();
        else{
            if(jug != null && (jug instanceof Maquina || guany)){
            //Si es màquina li passem els jugadors i mirem quin té més valor
                return ((Maquina)jug).triarJugador(mesRics, this);
            }
            else{
                ES.mostrar("Hi ha +1 jugador ric !!!");
                return _jugadors.get(ES.escollirJug(mesRics, mesRics.size())-1); //controlar entrada
            }
        }
    }

    /**
    *	@pre j forma part del Joc
    *	@post Retorna els jugadors del canto del jugador (dret i esquerra)
    *   @param j Jugador a mirar els del cantó
    *	@return Jugador[]
    */
    public Jugador[] getJugadorsCanto(Jugador j){
        //De moment nomes 3+ jugadors
        Jugador[] jugs = new Jugador[2];
        int index = j.getId();
        //Jugador esquerre
        if(index == 0)
            jugs[0] = _jugadors.getLast();
        else
            jugs[0] = _jugadors.get(index - 1);
        //Jugador dret
        if(index == _jugadors.size()-1)
            jugs[1] = _jugadors.getFirst();
        else
            jugs[1] = _jugadors.get(index + 1);
        return jugs;
    }

    /**
    *	@pre Cert
    *	@post Retorna la llista de cartes del tauler
    *	@return LinkedList<Carta>
    */
    public LinkedList<Carta> getCartes(){
        return _cartesJoc;
    }

    /**
    *	@pre Cert
    *	@post Retorna una string amb les cartes del tauler (noms)
    *	@return String
    */
    public String cartesTauler(){
        String ret = "Cartes tauler \n";
        for(Carta c: _cartesJoc)
            ret += c.getRol().toString() + "\n";
        return ret;
    }

    /**
    *	@pre el primer jugador és la victima (la que ha intentat executar l'accio de Rol). Cartes aixecades
    *	@post Retorna qui tenia rao i executa l'accio de Rol. Si ningú tenia rao, retorna NULL
    *   @param jugadors Llista de jugadors que protesten
    *   @param cartes llista de cartes dels jugadors que protesten
    *   @param pos llista de posicions de les cartes als jugadors
    *   @param rol Rol que es protesta
    */
    private void protestar(LinkedList<Jugador> jugadors, LinkedList<Carta> cartes, LinkedList<Integer> pos, Rol rol){
        Protesta p = new Protesta(jugadors, cartes, pos, rol);
        LinkedList<Jugador> rao = p.realitzarProtesta(); //pot ser que hi hagin 2 o 3(Meso) camperols
        if(!rao.isEmpty()){
            ES.mostrar(rao.getFirst().jugarRol(rol, this)); //Juguem l'accio de rol
            if(rao.getFirst().monedes() >= _nMonedesFinals)
                _estatJoc = 2;
            if(rao.size()>1){
                if(rao.get(1).monedes() >= _nMonedesFinals)
                    _estatJoc = 2;
            }
            if(rao.size()>2){
                if(rao.get(2).monedes() >= _nMonedesFinals)
                    _estatJoc = 2;
            }
            for(Jugador j: rao){
                if(!jugadors.isEmpty())
                    jugadors.remove(j);
            }
        }
        if(!jugadors.isEmpty()){
            for(Jugador j: jugadors){
                j.modificarMonedes(-1);
                ES.mostrar("El jugador " + j.getNom() + " perd una moneda per mentider. Ara en te " + j.monedes());
                _nMonedesPalauJusticia++;
            }
            ES.mostrar("Ara el palau de justicia te "+ _nMonedesPalauJusticia + " monedes.");
        }
    }

    /**
    *   @pre Cert
    *   @post Retorna les monedes del palau
    *   @return int
    */
    public int monedesPalau(){
        return _nMonedesPalauJusticia;
    }

    /**
    *	@pre Cert
    *	@post S'ha afegit monedes al palau de justicia
    */
    public void afegirAPalauJusticia(int monedes){
        _nMonedesPalauJusticia+=monedes;
    }

    /**
    *	@pre Cert
    *	@post El palau de justicia s'ha buidat (Per l'execució del rol del Jutge)
    *	@return int
    */
    public int buidarMonedesPalau(){
        int monedes = _nMonedesPalauJusticia;
        _nMonedesPalauJusticia = 0;
        return monedes;
    }

    /**
    *	@pre Cert 
    *	@post retorna true si el jugador ha arribat al limit de monedes, fals altrament
    *   @param j Jugador a comprovar
    *	@return boolean
    */
    public boolean arribaMonedesLimit(Jugador j){
        return j.monedes()>= _nMonedesFinals*9/10-1;
    }
    
    /**
    *  @pre cert
    *  @post retorna un limit de monedes nes petit que les monedesFinals (10 per cent)
    *  @return int
    */
    public int getMonedesLimit(){
        return _nMonedesFinals*9/10-1;
    }
    
    /**
    *  @pre 0<=estat<=2
    *  @post canvia l'estat del joc
    *   @param estat Estat que prendra el joc
    */    
    public void canviarEstat(int estat){
        _estatJoc=estat;
    }

    //Metodes privats

    /**
    *	@pre Hi ha un nombre de jugadors dins el rang (2-13)
    *	@post S'han repartit les cartes als jugadors amb les restriccions pertinents
    */
    private void repartirCartesInicials(){
        int nJugTotal = _jugadors.size();
        int cartesPerJugador = 1;
        if(nJugTotal == 2)
            cartesPerJugador = 3;
        else if(nJugTotal == 3)
            cartesPerJugador = 2;
        //Per a cada jugador afegim cartesPerJugador
        //i borrem la carta del tauler
        for(Jugador jAct:_jugadors){
            for(int i = 0; i < cartesPerJugador; i++){
                Carta c = cartaAleatoriaTauler();
                jAct.afegirCarta(c);
            }
        }
        //Memòria inicial dels jugadors màquina
        for(Jugador jAct:_jugadors){
            if(jAct instanceof Maquina){
                //Afegim (amb probabilitat) totes les cartes tant dels jugadors
                for(Jugador jAfegir:_jugadors){
                	int aux = 0;
                    for(Carta c: jAfegir.cartes()){
                        ((Maquina)jAct).modificarCarta(jAfegir, c, aux);
                    	aux++;
                    }
                }
                //Afegim cartes tauler
                for(Carta c: _cartesJoc)
                    ((Maquina)jAct).modificarCarta(null, c, _cartesJoc.indexOf(c));
            }
        }
    }

    /**
    *	@pre Hi ha cartes al tauler i el joc no ha començat
    *	@post Retorna una carta aleatoria del tauler i l'elimina d'aquest
    *	@return Carta
    */
    private Carta cartaAleatoriaTauler(){
        Random random = new Random();
        int valor = random.nextInt(_cartesJoc.size());
        Carta c = _cartesJoc.get(valor);
        _cartesJoc.remove(valor); //Borrem la carta del tauler
        return c;
    }
}

