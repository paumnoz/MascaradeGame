/**
*   @class Joc
*   @brief Classe Rol Bisbe
*   @details Classe que implementa el rol bisbe.
*            Accio Bisbe: Agafa 2 monedes del jugador mes ric, si hi ha empat el bisbe escollira un dels jugadors empatats
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.*;
import principal.Joc;

public class Bisbe implements Rol{
//Descripcio: classe personatje bisbe

//Atributs
    private String nom_;    ///< nom del rol

//Met.Publics

    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Bisbe(){
        nom_="Bisbe";
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
     * @post retorna un double
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return double
     */
    @Override
    public double guany(Jugador player, Joc game){
    	Jugador ric = game.jugadorMesRic(player, true);
        if(player.monedes() + 2 >= game.monedesFinals())
            return 1;
        else
            return (double)ric.monedes()/(double)game.monedesFinals();
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
     * @post retorna una explicacio de lo que ha passat al realitzar laccio Camperol
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) {
        Jugador ric=game.jugadorMesRic(player, false);
        if(ric.monedes()>1){
		ric.modificarMonedes(-2);
		player.modificarMonedes(2);
	}
        else{
            player.modificarMonedes(ric.monedes());
            ric.modificarMonedes(-(ric.monedes()));
        }
        return "--------Accio Rol Bisbe-------- \n S'ha tret 2 monedes del jugador: "+ric.getNom()+" Ja que era el mes ric i si hi havia igualtat de riquesa, el bisba l'ha escolit. \n"+"Aquestes 2 monedes s'han sumat al jugador: "+player.getNom()+" que s'ha manifestat com a bisbe.  \n";
    }
}
