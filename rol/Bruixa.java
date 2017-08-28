/**
*   @class Joc
*   @brief Classe Rol Bruixa
*   @details Classe que implementa el rol bruixa
*            Accio Bruixa: Escull un jugador i intercanvia totes les monedes
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/
package rol;

import jugador.*;
import principal.Joc;
import ES.*;

public class Bruixa implements Rol {
//Descripcio: classe peronatje Buixa

//Atributs
    private String nom_;    ///< nom del rol

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Bruixa(){
        nom_="Bruixa";
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
     * @post retorna el guany del rol
     * @param player Jugador que executa el rol
     * @param game Joc general
     * @return double
     */
    @Override
    public double guany(Jugador player, Joc game){
    	Jugador jug = game.jugadorMesRic(player, true);
    	if(jug.monedes() < player.monedes()){
    		return 0;
    	}
    	else{
    		double prob = (double)jug.monedes()/(double)player.monedes();
    		if(prob >= 2)
                return 1;
            else
                return prob-1;
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
     * @post retorna una explicacio de lo que ha passat al realitzar laccio Camperol
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) { 
    	Jugador escollit;
    	if(player instanceof Maquina) //si es maquina no fa falta demanar jugador a E/S, ja ho fa internament
    		escollit = game.jugadorMesRic(player, false);
    	else
    		escollit = game.getJugador(ES.escollirJug(game.getJugadors(), player.getId())-1);
        int a=player.monedes(), b=escollit.monedes();
        player.intercanviarMonedes(escollit);
        return "--------Accio Rol Bruixa-------- \n S'han intercanviat les monedes entre el jugador: "+player.getNom()+"(tenia "+a+" monedes) amb "+escollit.getNom()+"(tenia "+b+" monedes).  \n";
    }
}
