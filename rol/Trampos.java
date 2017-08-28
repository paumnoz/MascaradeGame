/**
*   @class Joc
*   @brief Classe Rol Trampos
*   @details Classe que implementa el rol Trampos
*            Accio Trampos: quan executa laccio i te un nombre de monedes igual o superior 
*            al limit per guanya trampos, guanya la pratida, altramen no passa res
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import principal.Joc;
import jugador.Jugador;

public class Trampos implements Rol {
//Descripcio: classe personatje trampos
    
//Atributs
    private String nom_;    ///< nom del rol

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Trampos(){
        nom_="Trampos";
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
    	if(player.monedes() >= game.monedesFinals() - 3) //Si no guanya directament no val la pena
    		return 1;
    	return 0;
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
     * @post retorna una descripcio de l'accio Trampos feta
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game){
        String descripcio="--------Accio Rol Trampos-------- \n El jugador: "+player.getNom()+" te "+player.monedes()+" \n";
        if(game.arribaMonedesLimit(player)){
            descripcio+="Guanya la partida, ja que ha arribat al nombre de monedes limit!!! Monedes per guanyar trampos: "+game.getMonedesLimit();
            game.canviarEstat(2);
        }
        else
            descripcio+="No ha arribat al minim de monedes per guanyar. Monedes per guanyar trampos: "+game.getMonedesLimit();
        return descripcio;      
    }
}
