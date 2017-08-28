package accio;

/**
* 	@class Protesta
* 	@brief Una protesta del joc mascarade
*   @details Invariant: Rol, jugadors i cartes han de formar part del joc actual
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import java.util.*;
import principal.*;
import jugador.*;
import rol.*;
import ES.*;

public class Protesta extends Accio{

	private LinkedList<Jugador> _jugadors; ///< Conjunt de jugadors que volen protestar. El primer es la victima
	private LinkedList<Carta> _cartes; ///< Cartes associades a la disputa entre jugadors
	private LinkedList<Integer> _pos;
	private Rol _rol; ///< Rol disputat

	//Constructors

	/**
    *   @pre Cert
    *   @post S'ha inicialitzat la protesta sense cap valor associat
    */
	public Protesta(){
		_cartes = null;
	}

	/**
    *   @pre Cert
    *   @post S'ha inicialitzat la protesta amb els valors de la E/S
    *   @param j Jugadors que protesten
    *   @param c Cartes amb les que els jugadors protesten
    *   @param pos Posició de les cartes
    *   @param r Rol que es reclama
    */
	public Protesta(LinkedList<Jugador> j, LinkedList<Carta> c, LinkedList<Integer> pos, Rol r){
		_jugadors = j;
		_cartes = c;
		_rol = r;
		_pos = pos;
	}

	//Mètodes publics

	/**
    *   @pre Cert
    *   @post S'ha realitzat la protesta juntament amb l'accio de rol corresponent.
    */
	public LinkedList<Jugador> realitzarProtesta(){
        LinkedList<Jugador> rao = new LinkedList<Jugador>();
        Iterator<Jugador> it = _jugadors.iterator();
        Iterator<Carta> itc = _cartes.iterator();
        Iterator<Integer> itp = _pos.iterator();
        while(it.hasNext()){ //Recorrem tot i actualitzem jugadors maquina
            Jugador actual = it.next();
            Carta cActual = itc.next();
            int posActual = itp.next();
            
            //Té raó?
            if(cActual.getRol().equals(_rol))
                rao.add(actual);
            else if(cActual.getRol() instanceof Maso){
                if(((Maso) cActual.getRol()).adoptat()==null || ((Maso) cActual.getRol()).adoptat().equals(_rol)){
                    ((Maso) cActual.getRol()).adoptarRol(_rol);
                    rao.add(actual);
                }
            }
            //Ha vist carta, seguent torn no pot:
            actual.consultat(posActual);
            //Actualitzem memoria JugMaquina
            for(int i = 0; i < _joc.getJugadors().size(); i++){
                Jugador jugM = _joc.getJugador(i);
                if(jugM instanceof Maquina)
                    ((Maquina)jugM).modificarCarta(actual, cActual, posActual);
            }
        }
        ES.mostrarCartesProtesta(_cartes, _jugadors, _rol, rao); //Mostrem per E/S
        return rao;
	}
}