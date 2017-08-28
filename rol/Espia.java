/**
*   @class Joc
*   @brief Classe Rol Espia
*   @details Classe que implementa rol Espia
*            Accio Espia: Mira una de les seves cartes i al d'un jugador i les intercanvia o ho fa veure
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import principal.*;
import jugador.*;
import ES.*;
import java.util.LinkedList;

public class Espia implements Rol{
//Descripcio: classe personatje Espia

//Atributs
    private String nom_;    ///< nom del rol

//Met.Privats
    
    /**
     * @pre player i game existeix
     * @post retorna id un jugador o el tauler
     * @param player El jugador que a iniciat l'accio rol
     * @param game El joc general
     * @return int
     */
    private int escullJug(Jugador player, Joc game){
        if(player instanceof Maquina)
            return ((Maquina)player).jugadorRolAEspiar(game);
        else
            return ES.demanarJugador(game.getJugadors().size(), player.getId()+1, true)-1;
    }
    
    /**
     * @pre player, game y escollit han d'existir
     * @post retorna la carta que ha escollit
     * @param player Es el que fa d'espia 
     * @param game El joc general
     * @param escollit Id del jugador escollit
     * @return int
     */
    private int escullCartaTauler(Jugador player, Joc game, int escollit){
        int nCarta=0;
        if(player instanceof Maquina){
            nCarta=((Maquina)player).maximRolJugador(game, escollit);
            ((Maquina)player).modificarCarta(null, game.getCartes().get(nCarta), nCarta);
        }
        else{
            nCarta=ES.demanarCarta(-1, true, 0);
            ES.mostrar("La carta es: "+game.getCartes().get(nCarta).getRol().toString()+" D'AMAGAT");//DAMAGAT, per aixo no ho poso a la descripció
        }
        return nCarta;
    }
    
//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Espia(){
        nom_="Espia";
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
    	Rol r = ((Maquina)player).rolAEspiar(game);
    	if(r == null)
    		return 0;
    	else
    		return r.guany(player, game);
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
    public String ulti(Jugador player, Joc game){
        String descripcio="--------Accio Rol Espia-------- \n";
        //Escull la seva carta
        descripcio+="El Jugador: "+player.getNom()+" mira la seva carta";
        if(player instanceof Maquina)
            ((Maquina)player).modificarCarta(player, player.getCarta(0), 0);
        else
            ES.mostrar("La carta es:"+player.getCarta(0).getRol().toString()+" D'AMAGAT"); //DAMAGAT, per aixo no ho poso a la descripció
        //escull jugador o tauler
        int escollit=escullJug(player, game);
        int nCarta=0;
        LinkedList<Carta> llistaCartes=null; //de on vol intercanviar la carta Tauler o jugador
        if(escollit>=game.getJugadors().size()){
            nCarta=escullCartaTauler(player, game, escollit);
            llistaCartes=game.getCartes();
            descripcio+=" i la del tauler";
        }       
        else{
            llistaCartes=game.getJugador(escollit).cartes();
            if(!(player instanceof Maquina))
                ES.mostrar(game.getJugador(escollit).getCarta(nCarta).getRol().toString()+" D'AMAGAT");//DAMAGAT, per aixo no ho poso a la descripció
            descripcio+=" i la del Jugador: "+game.getJugador(escollit).getNom();
        }
        //intercanvi ?
        int real = 1;
        if(!(player instanceof Maquina))
            real = ES.real();
        if(real == 1){ 
            player.intercanviCarta(0, llistaCartes, nCarta, escollit, true);
        }
        player.consultat(0);
        return descripcio+" i les intercanvia \n";
    }
}
