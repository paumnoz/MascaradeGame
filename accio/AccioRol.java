package accio;

/**
* 	@class Accio Rol
* 	@brief Realització d'una acció de rol
*   @details Invariant: El jugador i el rol han de formar part del joc actual
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import rol.*;
import jugador.*;

public class AccioRol extends Accio{

    private Rol _rol; ///< Rol a executar
    private Jugador _jugador; ///< Jugador que executa l'acció

    //Constructors

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat l'accio de rol
    */
    public AccioRol(){
        _rol = null;
        _jugador = null;
    }

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat l'accio de rol amb les dades definides per la E/S
    *   @param j Jugador que fa l'acció
    *   @param rol Rol que vol jugar
    */
    public AccioRol(Jugador j, Rol r){
        _jugador = j;
        _rol = r;
    }

    /**
    *   @pre Cert
    *   @post Retorna el rol que vol jugar
    *   @return Rol
    */
    public Rol rol(){
        return _rol;
    }

    /**
    *   @pre Cert
    *   @post Guany de realitzar aquesta acció de rol
    *   @param probabilitat Probabilitat de coneixement
    *   @return double
    */
    public double guany(double probabilitat){
    	return _rol.guany(_jugador, _joc)*probabilitat;
    }

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat l'accio de rol amb les dades definides per la E/S
    *   @return String
    */
    //Tenim en compte la protesta? Tecnicament, aquest mètode es crida despres d'haver fet la suposada protesta
    public String realitzarAccioRol(){
        return _rol.ulti(_jugador, _joc);
    }
}
