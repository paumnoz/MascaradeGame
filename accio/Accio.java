package accio;

/**
* 	@class Accio
* 	@brief Una accio del joc Mascarade (realitzada per un jugador)
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import principal.Joc;

public abstract class Accio{
	//Atributs
	protected static Joc _joc; ///< El joc es el mateix per a totes les accions

	/**
	*	@pre Cert
	*	@post L'atribut de classe joc val el joc especificat
	*	@param joc Joc actual i on es jugaran les accions
	*/
	public static void setJoc(Joc joc){
		Accio._joc = joc;
	}
}
