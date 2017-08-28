/**
*   @class Joc
*   @brief Classe Rol Reina
*   @details Classe que implementa el rol
*            Accio Reina: agafa 2 monedes del banc nacionalñ
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/
package rol;

import jugador.Jugador;
import principal.Joc;

public class Reina implements Rol{
//Descripcio: classe personatje reina

//Atributs
    private String nom_;


//Met.Publics

    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Reina(){
        nom_="Reina";    ///< nom del rol
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
    	if(player.monedes() + 2 >= game.monedesFinals())
            return 1;
        else
        	return 0.5;
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
     * @post retorna una descripcio de l'accio Reina feta
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) { 
        player.modificarMonedes(2);
        return "--------Accio Rol Reina-------- \n El jugador: "+player.getNom()+" ha guanyat 2 monedes agafades del Banc Nacional";
    }
}
