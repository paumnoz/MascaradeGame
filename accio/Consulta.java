package accio;

/**
* 	@class Consulta
* 	@brief Una Consulta del joc mascarade
*   @details Invariant: La carta ha de formar part del conjunt de cartes del jugador
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import principal.Carta;
import jugador.*;
import rol.*;

public class Consulta extends Accio{

    private Carta _carta; ///< Carta que es vol consultar
    private Jugador _jugador; ///< Jugador que vol realitzar la consulta

    //Constructors

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat la Consulta sense cap carta associada
    */
    public Consulta(){
        _carta = null;
        _jugador = null;
    }

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat la Consulta de la carta solicitada
    *   @param c Carta a consultar
    *   @param j Jugador que vol consultar
    */
    public Consulta(Carta c, Jugador j){
        _carta = c;
        _jugador = j;
    }

    /**
    *   @pre Cert
    *   @post Retorna el guany associat a la consulta
    *   @param probabilitat Probabilitat de coneixement
    *   @param rol Rol que creiem que tenim
    *   @return double
    */
    public double guany(double probabilitat, Rol rol){
        //Quin guany té la carta que creiem que tenim?
    	if(probabilitat > 0.3){
    		AccioRol consulta = new AccioRol(_jugador, rol);
        	return consulta.guany(probabilitat)*0.8; //Donem preferència a una bona accio de rol
    	}
    	else
    		return 0.5;
    }
    
    /**
    *   @pre Cert
    *   @post Retorna la carta a consultar
    *   @return Carta
    */
    public Carta carta(){
    	return _carta;
    }

    /**
    *   @pre Cert
    *   @post S'ha consultat la carta
    *   @return String
    */
    public String consultar(){
        if(_jugador instanceof Maquina){
            ((Maquina)_jugador).modificarCarta(_jugador, _carta, 0);
            return "Jugador maquina ha consultat una carta.";
        }else
            return "La teva carta es "+_carta.getRol().toString(); //Simplement mostrem el nom
    }
    
}
