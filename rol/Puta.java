/**
*   @class Joc
*   @brief Classe Rol 
*   @details Classe que implementa el rol 
*            Accio Puta: Inicia una subhasta seqüencial, cada pujador puja una quantitat X de monedes
*            El primer pujador que arribi a la quantitat X de monedes guanya i paga les X monedes a la 
*            puta. Llavors el jugador guanyat escull una carta d'un jugador o una seva i la mira, si es la seva carta
*            l'intercanvia o ho fa veure amb el tauler, si no es la seva carta lintercanvia o ho fa veure amb el jugador seleccionat.
*            Si tothom puja 0 monedes la puta no cobra res
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import ES.*;
import java.util.LinkedList;
import jugador.*;
import principal.Carta;
import principal.Joc;

public class Puta implements Rol{
//Descripcio: Classe personatje puta
    
//Atributs
    private String nom_;    ///< nom del rol
    
//Met.Privats
    
    /**
     * @pre pujador i joc han d'existir
     * @post retorna la id del jugador triat
     * @param pujador Jugador guanyador de la subhasta
     * @param game El joc general
     * @return int
     */
    private int escullJug(Jugador pujador, Joc game){
        int triat;
        if(pujador instanceof Maquina)
            triat = ((Maquina)pujador).jugadorMillorCartaNoPropia(game);
        else
            triat = ES.escollirJug(game.getJugadors(),game.getJugadors().size())-1; //demana el jugador menys tauler
        ES.mostrar("La carta es: "+game.getJugador(triat).getCarta(0).getRol().toString()); //DAMAGAT, per aixo no ho mostro a descripcio
        return triat;
    }
    
    /**
     * @pre pujador, game i escollit hgasn d'existir
     * @post retorna la carta que ha escollit
     * @param pujador es el que ha guanyat la subhasta
     * @param game El joc general
     * @param escollit Id del jugador escollit
     * @return int
     */
    private int escullCartaTauler(Jugador pujador, Joc game, int escollit){
        if(pujador instanceof Maquina)
            return ((Maquina)pujador).maximRolJugador(game, escollit);
        else
            return ES.demanarCarta(escollit, true, 0); 
    }

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Puta(){
        nom_="Puta";
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
    	return ((Maquina)player).coneixementMillorCartaNoPropia(game)*0.6; //Podem ningu subhasta...
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
     * @post retorna una descripcio de l'accio puta feta
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) {
        String descripcio="--------Accio Rol Puta-------- \n El Jugador: "+player.getNom()+" ha executat el Rol Puta ";
        int [] JugadorPaga=ES.subhasta(player, game.getJugadors());
        if(JugadorPaga==null){ //ningu ha pagat
            descripcio+="\n No guanya diners ja que ningu ha pujat \n";
        }
        else{//hi ha hagut algun guanyador
            player.modificarMonedes(JugadorPaga[1]);
            game.getJugador(JugadorPaga[0]).modificarMonedes(-JugadorPaga[1]);
            descripcio+="Ha guanyat "+JugadorPaga[1]+" per part del Jugador: "+game.getJugador(JugadorPaga[0]).getNom()+"\n";
            //Ara escull jugador
            int triat=escullJug(game.getJugador(JugadorPaga[0]), game);         
            int nCarta = 0;
            LinkedList<Carta> llistaCartes; //De on vol intercanviar
            if(JugadorPaga[0]==triat){// s'ha triat a ell mateix
                nCarta=escullCartaTauler(game.getJugador(JugadorPaga[0]), game, triat);
                llistaCartes=game.getCartes(); //del tauler
            }
            else
                llistaCartes=game.getJugador(triat).cartes(); //del jugador triat
            int real = 1;
            if(!(game.getJugador(JugadorPaga[0]) instanceof Maquina))
                real = ES.real();
            if(real!=0){
                if(triat<game.getJugadors().size()) game.getJugador(JugadorPaga[0]).consultat(0);
                game.getJugador(JugadorPaga[0]).intercanviCarta(0, llistaCartes, nCarta, triat, true);
            }
            descripcio+=" El jugador que ha guanyat la subhasta ha mirat una carta X i l'ha intercanviat amb la seva \n";
        }
        return descripcio;
    }
}
