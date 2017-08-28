/**
*   @class Joc
*   @brief Interface Rol
*   @details Interface on s'agrupen les operacions comunes sense implementar dels diversos rols
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.Jugador;
import principal.Joc;

public interface Rol {
//Descripcio: interface de rol   

    /**
     * @pre Cert
     * @post retorna el nom del rol
     * @return String
     */
    public String toString();

    /**
     * @pre Jugador i Joc han d'existir
     * @post retorna un double
     * @param player
     * @param game
     * @return double
     */
    public double guany(Jugador player, Joc game);
   
    /**
     * @pre Rol ha dexistir
     * @post retorna true si this==r, fals altrament
     * @param r
     * @return boolean
     */
    public boolean equals(Rol r);

    /**
     * @pre Jugador i Joc han dexistir
     * @post retorna una descripcio de l'accio feta
     * @param player
     * @param game
     * @return String
     */
    public String ulti(Jugador player, Joc game);
    
}
