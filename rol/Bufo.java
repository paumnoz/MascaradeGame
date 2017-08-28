/**
*   @class Joc
*   @brief Classe Rol Bufo
*   @details Classe on simplementa el rol Bufo
*            Accio Bufo: Agafa 2 monedes del banc nacional
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.*;
import principal.Joc;
import ES.*;

public class Bufo implements Rol{
//Descripcio: classe personatje Bufo

//Atributs
    private String nom_;    ///< nom del rol

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Bufo(){
        nom_="Bufo";
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
    	return 0.5; //El guany es mes gran que amb la reina -> Tenim la oportunitat de confondre els rivals
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
        player.modificarMonedes(2);
        Jugador jug1, jug2;
        if(player instanceof Maquina){ //si es maquina no fa falta demanar jugador a E/S, ja ho fa internament
            jug1 = ((Maquina)player).maximGuanyGlobal(game, null);
            jug2 = ((Maquina)player).canviarCartes(jug1, game);
        }
        else{
            jug1=game.getJugador(ES.escollirJug(game.getJugadors(), game.getJugadors().size())-1);
            jug2=game.getJugador(ES.escollirJug(game.getJugadors(), jug1.getId())-1);
        }
        int real;
        //intercanvia ?
        if(player instanceof Maquina)
            real = 1;
        else
            real = ES.real();
        if(real==1){
            jug1.intercanviarTotesCartes(jug2);
        }
        String descripcio="--------Accio Rol Bufo-------- \n El Jugador: "+player.getNom()+" agafa 2 monedes del Banc Nacional \n";
        descripcio+="El jugador: "+jug1.getNom()+" s'intercanvia les cartes amb el jugador: "+jug2.getNom()+" per orde del jugador: "+player.getNom()+" que ha executat l'accio de Bufo.  \n";
        return descripcio;
        
    }
}
