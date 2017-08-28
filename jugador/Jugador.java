package jugador;

/**
*   @class Jugador
*   @brief Un jugador del joc mascarade
*   @details Un jugador genèric i gestionat per un humà
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import java.util.*;
import accio.*;
import rol.*;
import principal.*;

public class Jugador implements Comparable<Jugador>{
	
    //Atributs
    private int _monedes; ///< Monedes que té el jugador. Valor per defecte: 0
    private int _id; ///< Id del jugador. De moment no tenen nom :)
    private LinkedList<Carta> _cartes; ///< Cartes del jugador
    private int[] _consultat; ///< Vector de restriccions d'ús de cartes
    private String _nom; ///< Nom del jugador

    //Constructors

    /**
    *   @post S'ha inicialitzat Jugador amb els valors per defecte
    *   @desc Un jugador inicialitzat per defecte NO és apte per el joc
    */
    public Jugador(){
        _id = -1;
        _monedes = 0;
        _cartes = new LinkedList<Carta>();
        _consultat = new int[3]; //Com a maxim tindre 3 cartes
        _consultat[0] = 2;
        _consultat[1] = 2;
        _consultat[2] = 2;
        //Funcionament consultat: A cada torn el fem creixer en 1 i quan es realitza una protesta el resetejem a 0.
        //El jugador nomes podra fer servir la carta si el numero és > 1;
    }

    /**
    *   @pre MonedesIncials > 0
    *   @post S'ha inicialitzat Jugador els valors demanats
    *   @param monedesInicials Monedes amb les que comença el jugador
    *   @param id identificador únic del jugador
    */
    public Jugador(int monedesInicials, int id){
        _id = id;
        _monedes = monedesInicials;
        _cartes = new LinkedList<Carta>();
        _consultat = new int[3]; //Com a maxim tindre 3 cartes
        _consultat[0] = 2;
        _consultat[1] = 2;
        _consultat[2] = 2;
    }

    /**
    *   @pre Cert
    *   @post El nom del jugador pasa a valer el valor demanar
    *   @param n Nom del jugador
    */
    public void setNom(String n){
    	_nom=n;
    }
    
    /**
    *   @pre Cert
    *   @post El nom del jugador pasa a valer el valor demanar
    *   @return String
    */
    public String getNom(){
    	return _nom;
    }
    
    /**
    *   @pre 0 <= pos < 3
    *   @post Cert si el jugador ha descobert la seva carta al torn anterior
    *   @param pos Posició a consultar
    *   @return boolean
    */
    public boolean haConsultat(int pos){
        return _consultat[pos] < 2;
    }

    /**
    *   @pre 0 <= pos < 3
    *   @post Es reseteja el comptador
    *   @param pos Posició a resetejar
    */
    public void consultat(int pos){
        _consultat[pos] = 0; //Ha consultat
    }
    
    /**
    *   @pre Cert
    *   @post Suma 1 a tot el vector de restriccions
    */
    public void sumarConsultat(){
    	_consultat[0]++;
        _consultat[1]++;
        _consultat[2]++;
    }

    /**
    *   @pre Cert
    *   @post S'han modificat les monedes del jugador. Retorna el nombre de monedes restants
    *   @param n Nombre de monedes a afegir/restar
    *   @return int
    */
    public int modificarMonedes(int n){
        if(_monedes + n < 0)
            _monedes = 0;
        else
            _monedes += n;
        return _monedes;
    }

    /**
    *   @pre Cert
    *   @post Retorna el nombre de monedes
    *   @return int
    */
    public int monedes(){
        return _monedes;
    }

    /**
    *   @pre Cert
    *   @post Retorna una string amb les cartes del jugador (poder millor toString)
    *   @return String
    */
    public String getCartes(){
        String ret = "";
        for(Carta c: _cartes)
            ret += c.getRol().toString() + "\n";
        return ret;
    }

    /**
    *   @pre Cert
    *   @post Retorna una string amb les cartes del jugador (poder millor toString)
    *   @return LinkedList<Carta>
    */
    public LinkedList<Carta> cartes(){
        return _cartes;
    }

    /**
    *   @pre Carta te un rol que forma part del conjunt de rols de la partida
    *   @post S'ha afegit la carta a la llista de cartes del Jugador
    *   @param c Carta a afegir
    */
    public void afegirCarta(Carta c){
        _cartes.add(c);
    }

    /**
    *   @pre nC >= 0 i nC < _cartes.size() 
    *   @post S'ha borrat la carta de la llista de cartes del Jugador
    *   @param nC numero de carta a borrar
    */
    public void borrarCarta(int nC){
        _cartes.remove(nC);
    }

    /**
    *   @pre 0 <= 0 < _cartes.size()
    *   @post Retorna la carta de Jugador
    *   @param n Carta a obtenir
    *   @return Carta
    */
    public Carta getCarta(int n){
        return _cartes.get(n);
    }

    /**
    *   @pre Cert
    *   @post Retorna l'indentificador del jugador
    *   @return int
    */
    public int getId(){
        return _id;
    }

    /**
    *   @pre nJugadors < nombre total de jugadors
    *   @post Canvia la carta entre dos jugadors
    *   @param nCarta carta nostre a intercanviar
    *   @param llista2 llista de cartes de l'altre jugador implicat
    *   @param nCarta2 carta de l'altre jugador implicat
    *   @param jug2 Altre jugador implicat
    *   @param real Cert si l'intercanvi és real
    */
    public void intercanviCarta(int nCarta, LinkedList<Carta> llista2, int nCarta2, int jug2, boolean real){
        Intercanvi i = new Intercanvi(_cartes, nCarta, getId()+1, llista2, nCarta2, jug2+1, real);
        i.realitzarIntercanvi();
    }

    /**
    *   @pre nJugadors < nombre total de jugadors
    *   @post Realitza l'accio de rol definida del jugador
    *   @param rol Rol a jugar
    *   @param game Joc actual
    *   @return String
    */
    public String jugarRol(Rol rol, Joc game){
        //Creem una accio de rol amb el rol adoptat del jugador i la seva suposada carta
        AccioRol aR = new AccioRol(this, rol);
        return aR.realitzarAccioRol();
    }

    /**
    *   @pre El numero de torn ha de ser superior o igual a 4
    *   @post Retorna el nom de la carta
    *   @param nCarta nombre de carta a consultar
    *   @return String
    */
    public String consultarCarta(int nCarta){
        //Busquem la carta i la retornem
        Carta carta = _cartes.get(nCarta);
        Consulta c = new Consulta(carta, this);
        return c.consultar();
    }

    //Sobreescriptura del comparador (per a la llista de jugadors de joc)
    @Override
    public int compareTo(Jugador jug2){
        //Comparem per nombre de monedes
        if(_monedes > jug2.monedes())
            return -1;
        else
            return 1;
    }
        
    /**
    *   @pre jugador ha dexistir
    *   @post intercanvia monedes amb el jugador this i player
    *   @param player Jugador amb el que canviarem les monedes
    */
    public void intercanviarMonedes(Jugador player){
        int aux=_monedes;
        _monedes=player._monedes;
        player._monedes=aux;
    }
    
    /**
    *   @pre jugador ha d'exitir
    *   @post intercanvia les cartes this amb el player
    *   @param player Jugador amb el que canviarem totes les cartes
    */
    public void intercanviarTotesCartes(Jugador player){
        LinkedList<Carta> aux=_cartes;
        _cartes=player._cartes;
        player._cartes=aux;
    }
    
    /**
    *   @pre rol existeix
    *   @post retorna cert si el rol!=rolCarta, fals altrament
    *   @param dit Rol que suposadament te
    *   @param nCarta Nombre de carta amb la que vol jugar
    */
    public boolean menteix(Rol dit, int nCarta){
        boolean menteix=false;
        if(_cartes.get(nCarta).getRol() instanceof Maso){
            if(!dit.toString().equals("Maso") && (((Maso) _cartes.get(nCarta).getRol()).adoptat()==null || ((Maso) _cartes.get(nCarta).getRol()).adoptat().equals(dit)))
                ((Maso) _cartes.get(nCarta).getRol()).adoptarRol(dit);
            else
                menteix=true;
        }
        else
            menteix=!dit.equals(_cartes.get(nCarta).getRol());
        return menteix;
    }
}