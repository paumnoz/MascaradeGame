/**
*   @class Joc
*   @brief Classe Rol Camperol
*   @details Classe que implementa el rol camperol
*            Accio Camperol: Quan s'executa l'accio rol camperol es fa una especie de manifestacio
*            que consisteix a que cada jugador diu si es camperol o no i amb quina carta juguen.
*            Despres mostren les cartes i si no es camperol paga una moneda al palau de justicia
*            com a multa, si nomes hi ha un camperol manifestat real (correspon amb la carta jugada)
*            aquest cobrara 1 nomeda del banc nacional. Si hi ha +1 camperol manifestat real cadascu
*            cobrara 2 monedes del banc nacional.
*   @author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

package rol;

import jugador.*;
import principal.Joc;
import ES.*;
import java.util.LinkedList;


public class Camperol implements Rol{
//Descripcio: classe personatje camperol
    
//Atributs
    private String nom_;    ///< nom del rol
    private String descripcioAccio_; ///< descripcio de l'accio realitzada (ho poso com a atribut per pode simplificar el codi de la funcio "ulti")

//Met.Privats
    
    /**
     * @pre la llista jug i game existeix
     * @post retorna una llista de jugadors que son realment camperols, tambe pot retorna la llista buida si no hi ha cap camperol de la llista jug
     * @param jug Llista de jugadors que diuen que son camperols
     * @param game El joc general
     * @return LinkedList<Jugador>
     */
    private LinkedList<Jugador> agafarCamper(LinkedList<Jugador> jug, Joc game){
        LinkedList<Jugador> camperols=new LinkedList<Jugador>();
        for(Jugador j: jug){
            j.consultat(0);
            descripcioAccio_=descripcioAccio_+"El Jugador: "+j.getNom()+" mostra la carta: "+j.getCarta(0).getRol().toString()+"\n";
            if(j.menteix(this, 0)){ // si menteix vol dir que no es camperol, llavors multa
                descripcioAccio_=descripcioAccio_+"Paga 1 moneda al banc per no ser Camperol i manifestarse com a tal \n";
                j.modificarMonedes(-1);
                game.afegirAPalauJusticia(1);
            }
            else
                camperols.add(j);
        }
        return camperols;
    }
    
//Met.Publics
    
    /**
     * @pre Cert
     * @post cosntructor per defecte
     */
    public Camperol(){
        nom_="Camperol";
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
    	//Hi ha un altre camperol amb certesa?
    	if(((Maquina)player).hiHaCamperol(this))
    		return 0.4; //No és segur que reclami
    	else
    		return 0.3; //Nomes guanyem 1
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
        descripcioAccio_="--------Accio Rol Camperol-------- <br>";
        LinkedList<Jugador> escollits=ES.demanarCamperols(game.getJugadors(), player);
        if(escollits==null){
            player.modificarMonedes(1);
            descripcioAccio_+="El jugador: "+player.getNom()+" ha guanyat 1 moneda i es el responsable d'executar l'accio del rol camperol <br>";
        }
        //comprovem que es veritat, que son camperols
        else{ 
            escollits.add(player);
            LinkedList<Jugador> camperols=agafarCamper(escollits, game); //agafem els camperols
            escollits.clear();
            if(camperols.isEmpty())
                descripcioAccio_+="Ningu era rol, llavors tothom ha pagat una multa <br>";
            else if(camperols.size()==1){
                camperols.getFirst().modificarMonedes(1);
                descripcioAccio_+="El jugador: "+camperols.getFirst().getNom()+" guanya 1 moneda <br>";
            }
            else{
                descripcioAccio_+="Els jugadors: <br>";
                for(Jugador j: camperols){
                    descripcioAccio_+=j.getNom()+"<br>";
                    j.modificarMonedes(2);
                }
                descripcioAccio_+="Guanyen 2 monedes per ser Camperols <br>";
            }
        }
        return descripcioAccio_;
    }
}
