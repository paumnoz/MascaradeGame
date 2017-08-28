package principal;

/**
*   @class Carta
*   @brief Gestiona una carta del joc mascarade
*   @details Una carta del joc mascarade asociada a un rol
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import rol.*;

public class Carta {

    //Atributs
    private Rol _rol; ///< Rol associat a la carta 

    //Constructors

    /**
    *   @pre Cert
    *   @post S'ha inicialitzat Carta amb els valors per defecte
    */
    public Carta(){
        _rol = null;
    }
    
    /**
    *   @pre Cert
    *   @post S'ha inicialitzat Carta amb un rol determinat
    */
    public Carta(Rol r){
        _rol=r;
    }
    
    //Metodes publics

    /**
    *   @pre Cert
    *   @post Retorna el rol associat a la carta
    *   @return Rol
    */
    public Rol getRol(){
        return _rol;
    }
    
    
}
