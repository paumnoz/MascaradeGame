package accio;

/**
* 	@class Intercanvi
* 	@brief Un intercanvi de dues cartes
*   @details Invariant: les posicions han d'estae asociades a els jugadors
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import java.util.*;
import principal.*;
import jugador.*;
import ES.*;
import rol.Maso;

public class Intercanvi extends Accio{

    private LinkedList<Carta> _cartes1; ///< Jugador que vol fer l'intercanvi
    private LinkedList<Carta> _cartes2; ///< Jugador a intercanviar la carta
    private int _pos1; ///< Posició de la primera carta 
    private int _pos2; ///< Posició de la segona carta
    private int _jug1; ///< Id del primer jugador
    private int _jug2; ///< Id del segon jugador
    private boolean _real; ///< Cert si l'intercanvi és real

    //Constructors

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat l'intercanvi sense cap jugador associat
    */
    public Intercanvi(){
        _cartes1 = _cartes2 = null;
        _pos1 = _pos2 = -1;
    }

	/**
    *   @pre Cert
    *   @post S'ha inicialitzat l'intercanvi amb els valors de la E/S
    *   @param c1 llista de cartes del primer jugador
    *   @param pos1 posicio de la carta del primer jugador
    *   @param nJug1 Id del primer jugador
    *   @param c2 llista de cartes del segon jugador
    *   @param pos2 posicio de la carta del segon jugador
    *   @param nJug2 Id del segon jugador
    */
    public Intercanvi(LinkedList<Carta> c1, int pos1, int nJug1, LinkedList<Carta> c2, int pos2, int nJug2, boolean real){
        _cartes1 = c1;
        _cartes2 = c2;
        _pos1 = pos1;
        _pos2 = pos2;
        _jug1 = nJug1;
        _jug2 = nJug2;
        _real = real;
    }
    
    /**
    *   @pre Cert
    *   @post Assigna el valor real de la ES
    *   @param real Cert si intercanvi real, fals altrament
    */
    public void real(boolean real){
    	_real = real;
    }

    /**
    *   @pre Cert
    *   @post Retorna una descripció de l'acció
    *   @return String
    */
    private String descripcio(){
        String ret = "El jugador "+_joc.getJugador(_jug1-1).getNom()+" ha intercanviat la carta "+_pos1+" per la carta ";
        if(_jug2-1 >= _joc.getJugadors().size())
            ret+=_pos2+" del tauler.";
        else
            ret+=_pos2+" del jugador "+_joc.getJugador(_jug2-1).getNom();
        return ret;
    }
    
    /**
    *   @pre Cert
    *   @post Retorna el guany associat a l'intercanvi
    *   @param probabilitat Grau de coneixement
    *   @param c1 Primera carta implicada
    *   @param c2 Segona carta implicada
    *   @param j Primer jugador implicat
    *   @param j2 Segon jugador implicat
    *   @return double
    */
    public static double guany(double probabilitat, Carta c1, Carta c2, Jugador j, Jugador j2){
        //Actual criteri:
        //Suposem probabilitats ideals i apliquem el global al final
		AccioRol r1 = new AccioRol(j, c1.getRol());
        AccioRol r2 = new AccioRol(j, c2.getRol());
        double guany2 = r2.guany(1);
        double guany1 = r1.guany(1);
        if(guany1 == 0){
        	return guany2*probabilitat;
        }
        else{
        	double guany = guany2/guany1;
            if(guany >= 2)
                return guany2*probabilitat;
            else if(guany <= 1)
                return 0;
            else
                return (guany - 1)*probabilitat;
        }
    }
        
    /**
    *   @pre Cert
    *   @post S'ha realitzat l'intercanvi
    */
    public void realitzarIntercanvi(){
        ES.mostrar(descripcio());
        if(_real){
            Carta c1 = _cartes1.get(_pos1);
            Carta c2 = _cartes2.get(_pos2);
            if(c1.getRol() instanceof Maso){
                ((Maso) c1.getRol()).treureRolAdoptat();
            }
            else if(c2.getRol() instanceof Maso){
                ((Maso) c2.getRol()).treureRolAdoptat();
            }
            _cartes1.set(_pos1, c2);
            _cartes2.set(_pos2, c1);
        }
        //Actualitzem Jugadors CPU
        for(int i = 0; i < _joc.getJugadors().size(); i++){
            Jugador jug = _joc.getJugador(i);
            if(jug instanceof Maquina){
            	boolean propi = jug == _joc.getJugador(_jug1-1);
                //Si és màquina
                if(!(propi && !_real)){
                    if(_jug2-1 >= _joc.getJugadors().size()){ //Si és el tauler
                        ((Maquina)jug).actualitzarIntercanvi(_joc.getJugador(_jug1-1),null,0, _pos2, propi);
                    }
                    else{
                        ((Maquina)jug).actualitzarIntercanvi(_joc.getJugador(_jug1-1),_joc.getJugador(_jug2-1), _pos1, _pos2, propi);
                    }
                }
            }
        }
    }
}
