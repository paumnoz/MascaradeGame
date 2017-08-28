/**
*   @class Joc
*   @brief  Classe Rol Jutge
*   @details Classe que implementa le rol jutge
*            Accio Jutge: Agafa totes les monedes del Palau de Justicia
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import principal.Joc;
import jugador.Jugador;

public class Jutge implements Rol{
//Descripcio: classe personatje Jutge

//Atributs
    private String nom_;    ///< nom del rol

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Jutge(){
        nom_="Jutge";
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
    	int monedesPalau = game.monedesPalau();
        if(player.monedes() + monedesPalau >= game.monedesFinals())
                return 1;
        else{
            if(monedesPalau > 3)//Més guany que amb rei
            	return 0.8;
            else if(monedesPalau == 0)
            	return 0;
            else
            	return 0.5;
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
     * @post retorna una descripcio de l'accio rol jutge fet
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) { //agafar totes les monedes de Palau justicia
        int monedes=game.buidarMonedesPalau();
        player.modificarMonedes(monedes);
        return "--------Accio Rol Jutge-------- \n"+"El jugador: "+player.getNom()+" guanya "+monedes+" monedes, les que hi havien al Palau de Justicia.";
    }
}
