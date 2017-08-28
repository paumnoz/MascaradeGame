/**
*   @class Joc
*   @brief Classe Rol Inquisidor
*   @details Classe que implementa el rol inquisidor
*            Accio Inquisidor: demana un jugador i escull una carta del jugador.
*            El jugador escollit diu quin rol es la carta i la mostra, si menteix
*            ha de pagar 4 monedes al inquisidor, si diu la veritat no passa res
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.*;
import principal.Joc;
import ES.*;

public class Inquisidor implements Rol{
//Descripcio: classe personatje inquisidor

//Atributs
    private String nom_;    ///< nom del rol
    private String descripcioAccio_;    ///< descriu l'accio realitsada al executarla (ho guardo com a atr per simplificar la funcio "ulti")
    
//Met.Privats
    
    /**
     * @pre player i escolit ha d'existir
     * @post player te més monedes, escollit te menys monedes
     * @param player El que fa de inquisidor
     * @param escollit El que fa d'escollit
     */
    private void modificarMonedes(Jugador player, Jugador escollit){
        if(escollit.monedes()>3){
            player.modificarMonedes(4);
            escollit.modificarMonedes(-4);
            descripcioAccio_+="El jugador: "+escollit.getNom()+" ha perdut -4 monedes per mentider \n"+"El jugador: "+player.getNom()+" ha guanyat 4 monedes, ja que es el que ha invocat l'accio rol inquisidor \n";
        }
        else{
            player.modificarMonedes(escollit.monedes());
            escollit.modificarMonedes(-(escollit.monedes()));
            descripcioAccio_+="El jugador: "+escollit.getNom()+" ha perdut "+escollit.monedes()+" monedes per mentider \n"+"El jugador: "+player.getNom()+" ha guanyat "+escollit.monedes()+" monedes, ja que es el que ha invocat l'accio rol inquisidor  \n";
        }
    }

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Inquisidor(){
        nom_="Inquisidor";
    }
    
    /**
     * @pre Cert
     * @post retorna el nom del rol
     * @return String
     */    
    @Override
    public String toString(){
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
    	//Si podem guanyar:
    	//A qui li demanariem?
    	Jugador victima = ((Maquina)player).jugadorMaximConeixement(game);
    	//Casos en els que podem guanyar:
    	if(player.monedes() + 4 >= game.monedesFinals() || victima.monedes() > 4 || (victima.monedes() <= 4 && game.jugadorMesRic(player, false).monedes() < player.monedes()))
            return 0.9; //El guany és molt alt pero hi ha la possibilitat de de no guanyar res
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
     * @post retorna una descripcio de l'accio rol inquisidor fet
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player,  Joc game) {
        descripcioAccio_="--------Accio Rol Inquisidor-------- \n";
        Jugador escollit;
        if(player instanceof Maquina){
            Maquina m = (Maquina)player;
            escollit = m.jugadorMaximConeixement(game);      
        }
        else
            escollit=game.getJugador(ES.escollirJug(game.getJugadors(), player.getId())-1);
        Rol dit;
        if(escollit instanceof Maquina){
            dit = ((Maquina)escollit).rolPropi();
        }
        else{
            ES.mostrar("Jugador "+escollit.getNom()+" t'han escollit");
            dit=ES.demanarRol(true);
            descripcioAccio_+="La carta es: "+(escollit.getCarta(0).getRol().toString());
        }
        escollit.consultat(0);
        //mira si menteix
        if(escollit.menteix(dit, 0)){
            modificarMonedes(player, escollit);
        }
        //no ha mentit
        else{
            descripcioAccio_+="El jugador: "+escollit.getNom()+" no ha mentit, per tant no ha hagut de pagar res.  \n Llavors el jugador: "+player.getNom()+" no ha guanyat res i es el que ha invocat l'accio rol inquisidor  \n";
        }
        return descripcioAccio_;
    }
}
