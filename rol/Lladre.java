/**
*   @class Joc
*   @brief Classe Rol Lladre
*   @details Classe que implementa el rol lladre
*            Accio Lladre: Agafa 1 moneda dels 2 jugadors que te al canto
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/
package rol;

import principal.Joc;
import jugador.Jugador;

public class Lladre implements Rol{
//Descripcio: classe personatje lladre
 
        
//Atributs
    private String nom_;
    
//Met.Privats

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Lladre(){
        nom_="Lladre";
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
    	if(player.monedes() + 2 >= game.monedesFinals())
            return 1;
        else{
            Jugador[] costat = game.getJugadorsCanto(player);
            if((costat[0].monedes() - 1 <= 0 || costat[1].monedes() -1 <= 0) && game.jugadorMesRic(null, false) == player)
                return 1;
            else
                return 0.6;
        }
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
     * @post retorna una descripcio de l'accio rol lladre fet
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) {//roba 1 moneda del jugador de la seva dreta i 1 esquerra
        Jugador []jugCant=game.getJugadorsCanto(player);
        jugCant[0].modificarMonedes(-1); //no fa falta comprovar si un jugador te 0 monedes, ja que aixo ja ho fa joc
        jugCant[1].modificarMonedes(-1);
        player.modificarMonedes(2);
        return "--------Accio Rol Lladre-------- \n"+"Els jugador: "+jugCant[0].getNom()+" i "+jugCant[1].getNom()+" perden 1 moneda cadascu. \n"+"El jugador: "+player.getNom()+" guanya 2 monedes robades dels 2 jugadors anterioras per haver invocat el rol lladre.";
    }
}
