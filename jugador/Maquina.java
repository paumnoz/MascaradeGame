package jugador;

/**
*	@class Maquina
*	@brief Un jugador màquina del joc mascarade
*	@details Un jugador gestionat per la màquina que té certa memòria guardada.
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import principal.*;
import java.util.LinkedList;
import accio.*;
import rol.*;

public class Maquina extends Jugador{
    private Memoria _memoria; ///< Memoria del jugador màquina
    private final int _dificultat; ///< Dificultat del jugador Màquina (0 -> Facil, 1 -> Mig, 2 -> Dificil)

    //Constructors:
    
    /**
	*  @pre Cert
	*  @post Constructor per defecte
	*/
    public Maquina(){
        super();
        _memoria = new Memoria(this, 0, 0, null);
        _dificultat = 1;
    }

    /**	
    *   @pre cartes = cartes del joc
   	*   @post Constructor per defecte
   	*   @param nModI Nombre de monedes inicials
   	*   @param id Identificador del jugador
   	*   @param nJugs Nombre de jugadors totals de la partida
   	*   @param nCartes Nombre de cartes totals de la partida
   	*/
    public Maquina(int nModI, int id, int nJugs, int nCartes, LinkedList<Carta> cartes, int dificultat){
        super(nModI,id);
        _memoria = new Memoria(this, nJugs, nCartes, cartes);
        _dificultat = dificultat;
    }
    
    //Operacions bàsiques/generiques:

    /**	
    *   @pre j1 i j2 formen part de la partida
   	*   @post S'ha actualitzat la memoria amb les dades entrades
   	*   @param j1 Primer jugador implicat en l'intercanvi
   	*   @param j2 Segon jugador implicat en l'intercanvi
   	*   @param pos1 Posició de carta asociada al primer jugador
   	*   @param pos2 Posició de la carta asociada al segon jugador
   	*   @param propi Cert si a l'intercanvi hi participem nosaltres
   	*/
    public void actualitzarIntercanvi(Jugador j1, Jugador j2, int pos1, int pos2, boolean propi){
    	if(j1 == null){
    		if(j2 == null){
    			_memoria.actualitzarIntercanvi(-1, -1, pos1, pos2, propi);
    		}
    		else{
    			_memoria.actualitzarIntercanvi(-1, j2.getId(), pos1, pos2, propi);
    		}
    	}
    	else{
    		if(j2 == null){
    			_memoria.actualitzarIntercanvi(j1.getId(), -1, pos1, pos2, propi);
    		}
    		else
    			_memoria.actualitzarIntercanvi(j1.getId(), j2.getId(), pos1, pos2, propi);
    	}
    }

    /**	
    *   @return int
    *   @pre Cert
   	*   @post Retorna la carta amb la que vol protestar
   	*   @param j Jugador que reclama l'acció/rol
   	*   @param rol Rol que vol executar el jugador
   	*   @param joc Joc actual
   	*/
    public int protestar(Jugador j, String rol, Joc joc){
        return _memoria.protestar(j, rol, joc);
    }
    
    /**	
    * 	@pre c i jug formen part del joc
   	*   @post S'ha modificat la carta amb l'informació entrada
   	*   @param jug Jugador del qual hem obtingut coneixement
   	*   @param c Carta de la qual hem obtingut coneixement
   	*   @param pos Posició de la carta dins del jugador
   	*/
    public void modificarCarta(Jugador jug, Carta c, int pos){
    	_memoria.modificarCarta(jug, c, pos);
    }

    /**
    * 	@return Accio
    * 	@pre Cert
   	*   @post Retorna l'acció (ja configurada) que vol realitzar el joc màquina
   	*   @param j Joc actual
   	*   @param restringit Cert si no podem realitzar totes les accions
   	*/
    public Accio jugar(Joc j, boolean restringit){
        return _memoria.millorOpcio(j, restringit);
    }
    
    /**
    * 	@return inr
    * 	@pre Cert
   	*   @post Retorna la dificultat del jugador màquina
   	*/
    public int dificultat(){
    	return _dificultat;
    }

    //Rol Bisbe:

    /**
    *   @return Jugador
    *   @pre Cert
    *   @post Retorna el jugador del quan en tenim el màxim coneixement
    *   @param jugs Llista de jugadors
    */
    public Jugador triarJugador(LinkedList<Jugador> jugs, Joc joc){
        return _memoria.triarJugador(jugs, joc);
    }
    
    //Rol Bufó:
    
    /**
    * 	@return Jugador
    * 	@pre Cert
   	*   @post Retorna el jugador que té un guany conjunt (de totes les seves cartes) major
   	*   @param joc Joc actual
   	*   @param jugAnulat Jugador que no participa en la busqueda
   	*/
    public Jugador maximGuanyGlobal(Joc joc, Jugador jugAnulat){
    	Jugador jug = null;
    	double max = 0;
    	for(Jugador j: joc.getJugadors()){
    		if(jugAnulat != j && j != this){
	    		double nouMax = _memoria.guanyGlobal(j, joc);
	    		if(nouMax > max){
	    			max = nouMax;
	    			jug = j;
	    		}
    		}
    	}
    	return jug;
    }
    
    /**
    * 	@return Jugador
    * 	@pre Cert
   	*   @post Canvia les nostres cartes per les del jugador max
   	*   @param joc Joc actual
   	*   @param max Jugador amb el que volem intercanviar les cartes
   	*/
    public Jugador canviarCartes(Jugador max, Joc joc){
    	//Tenim 2 opcions -> Si el guany és major que el nostre canviem amb nosaltres
    	//Sino, busquem el segon amb més guany i els hi canviem per liar-los
    	double guanyGlobalPropi = _memoria.guanyGlobal(this, joc);
    	double guanyGlobalMax = _memoria.guanyGlobal(max, joc);
    	if(guanyGlobalPropi < guanyGlobalMax)
    		return this;
    	else
    		return maximGuanyGlobal(joc, max);
    }
    
    //Rol Inquisidor:
    
    /**
    * 	@return Jugador
    * 	@pre Cert
   	*   @post Retorna el jugador del quan en tenim el màxim coneixement
   	*   @param joc Joc actual
   	*/
    public Jugador jugadorMaximConeixement(Joc joc){
    	return _memoria.jugadorMaximConeixement(joc);
    }
    
    /**
    * 	@return Rol
    * 	@pre Cert
   	*   @post Retorna el rol que creu que té el jugador
   	*/
    public Rol rolPropi(){
    	return _memoria.rolPropi();
    }
    
    //Rol Espia:
    
    
    /**
    * 	@return Rol
    * 	@pre Cert
   	*   @post Retorna el Rol més adient a espiar
   	*   @param joc Joc actual
   	*/
    public Rol rolAEspiar(Joc joc){
    	return _memoria.buscarRolMaximNoPropi(joc);
    } 
    
    /**
    * 	@return int
    * 	@pre Cert
   	*   @post Retorna el jugador que volem espiar
   	*   @param joc Joc actual
   	*/
    public int jugadorRolAEspiar(Joc joc){
        return _memoria.jugadorRolAEspiar(joc);
    }
    
    /**
    * 	@return int
    * 	@pre Cert
   	*   @post Retorna el rol associat al jugador amb un guany més gran
   	*   @param joc Joc actual
   	*   @param jug Jugador del qual volem saber el rol
   	*/
    public int maximRolJugador(Joc joc, int jug){
        return _memoria.millorCartaJugadorEspiar(joc, jug);
    }
    
    /**
    * 	@return int
    * 	@pre Cert
   	*   @post Retorna la pitjor carta que tenim
   	*   @param joc Joc actual
   	*/
    public int pitjorCarta(Joc joc){
    	return _memoria.pitjorCartaPropia(joc);
    }
    
    //Rol Camperol:
    
    /**
    * 	@return boolean
    * 	@pre Cert
   	*   @post Retorna el jugador del quan en tenim el màxim coneixement
   	*   @param joc Joc actual
   	*/
    public boolean hiHaCamperol(Camperol r){
    	return _memoria.hiHaCamperol(r);
    }
    
    //Rol Puta:
    
    /**
    * 	@return int
    * 	@pre Cert
   	*   @post Retorna el jugador que té la millor carta (sense comptar-nos a nosaltres)
   	*   @param joc Joc actual
   	*/
    public int jugadorMillorCartaNoPropia(Joc joc){
        return _memoria.jugadorMillorCartaNoPropia(joc);
    }
    
    /**
    * 	@return int
    * 	@pre max >= 0
   	*   @post Retorna el el nombre de diners que estem diposats a subhastar
   	*   @param joc Joc actual
   	*/
    public int subhasta(Joc joc, int max){
        if(max >= monedes()*0.5)
            return 0;
        else{
            if(_memoria.coneixementMillorCartaNoPropia(joc) < 0.5)
                return max+1;
            else
                return 0;
        }
    }
    
    /**
    * 	@return double
    * 	@pre Cert
   	*   @post Retorna el el nombre de diners que estem diposats a subhastar
   	*   @param joc Joc actual
   	*/
    public double coneixementMillorCartaNoPropia(Joc joc){
        return _memoria.coneixementMillorCartaNoPropia(joc);
    }
}