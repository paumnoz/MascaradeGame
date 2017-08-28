/**
*   @class Joc
*   @brief Classe Rol 
*   @details Classe que implementa el rol Maso
*            Accio Maso: Executa l'accio del rol adoptat 
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.Jugador;
import principal.Joc;

public class Maso implements Rol{
//Descripcio_ classe personatje maso
    
//Atributs
    private String nom_;    ///< nom del rol
    private Rol adoptat_;   ///< rol adoptat

//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Maso(){
        nom_="Maso";
        adoptat_=null;
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
    	if(adoptat_ == null)
            return 0.7;
    	else
            return adoptat_.guany(player, game);
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
     * @post retorna una descripcio de l'accio maso fet
     * @param player Jugador que executa el rol
     * @param game El joc general
     * @return String
     */
    @Override
    public String ulti(Jugador player, Joc game) {
        String descripcio="--------Accio Rol Maso-------- \n El jugador: "+player.getNom();
        if(adoptat_!=null){ //restriccio, ningu pot executar el rol maso, es simplement un joquer
            descripcio+="Executa Rol Adoptat: \n"+adoptat_.ulti(player, game);
        }
        return descripcio;
    }
    
    /**
    * @pre Cert
    * @post adoptat_=null
    */
    public void treureRolAdoptat(){
        adoptat_=null;
    }
    
    /**
     * @pre Cert
     * @post adopta un rol r si no ha adoptat un ja
     * @param r Adoptara aquest rol
     */
    public void adoptarRol(Rol r){
        if(adoptat_==null)
            adoptat_=r;
    }
    
    /**
    * @pre Cert
    * @post retorna el adoptat_
    * @return Rol
    */
    public Rol adoptat(){
        return adoptat_;
    }
}
