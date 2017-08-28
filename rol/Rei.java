/**
*   @class Joc
*   @brief Classe Rol Rei
*   @details Classe que implementa el rol
*            Accio Rei: agafa 3 monedes del banc nacional
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/
package rol;

import jugador.Jugador;
import principal.Joc;

public class Rei implements Rol{
//Descripcio: classe del personatje rei

//Atributs
    private String nom_;     ///< nom del rol
    
//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Rei(){
        nom_="Rei";
    }
    
    /**
     * @pre Cert
     * @post retorna el nom del rol
     * @return String
     */
    @Override
    public String toString() {
        return nom_;
    }
    
    /**
     * @pre Jugador i Joc han d'existir
     * @post retorna el guany maxim del rol a jugar
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return double
     */
    @Override
    public double guany(Jugador player, Joc game){
    	if(player.monedes() + 3 >= game.monedesFinals())
            return 1;
        else
            return 0.7;
    }

    /**
     * @pre Rol ha dexistir
     * @post retorna true si this==r, fals altrament
     * @param r Rol a comparar
     * @return boolean
     */
    @Override
    public boolean equals(Rol r) {
        return nom_.compareTo(r.toString())==0;
    }
    
    /**
     * @pre Jugador i Joc han dexistir
     * @post retorna una descripcio de l'accio Rei feta
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */    
    @Override
    public String ulti(Jugador player, Joc game) {
        player.modificarMonedes(3);
         return "--------Accio Rol Rei-------- \n El jugador: "+player.getNom()+" ha guanyat 3 monedes agafades del Banc Nacional";
    }
}
