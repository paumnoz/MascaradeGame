/**
*   @class Joc
*   @brief Classe Rol Viuda
*   @details Classe que implementa el rol viuda
*            Accio Viuda: agafa 2 monedes del banc nacional si la quantitat de monedes
*            que te no supera el limit de monedes per guanyar trampos, si supere aquest limit
*            no passa res
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/
package rol;

import principal.Joc;
import jugador.Jugador;

public class Viuda implements Rol{
//Descroipcio: classe personatje Viuda
    
//Atributs
    private String nom_;    ///< nom del rol
    
//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Viuda(){
        nom_="Viuda";
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
    	if(game.arribaMonedesLimit(player)){
            if(player.monedes() + 2 >= game.monedesFinals())
                return 1;
            else
                return 0.55;
        }
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
     * @post retorna una descripcio de l'accio feta
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) {
        String descripcio="--------Accio Rol Viuda-------- \n"+"El jugador: "+player.getNom();
        int monedes=2;
        if(!game.arribaMonedesLimit(player)){
            player.modificarMonedes(monedes); //el valor de monedes es pot variar, poso dos pk si, pro si voleu posar 3 o 1 o 1.2354335 pos ok
            descripcio+=" ha guanyat "+monedes+" monedes.";
        }
        else{
            descripcio+=" no ha guanyat res ja que supera o iguala el limit de monedes permeses per aconseguir ingressos.";
        }
        return descripcio;
    }
}
