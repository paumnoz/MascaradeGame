package jugador;

/**
*	@class Memoria
*	@brief La memòria d'un jugador màquina
*	@details Memòria d'un jugador màquina. Gestiona el coneixement de totes les cartes i jugadors del joc. També incorpora un conjunt de mètodes pel seu adequat tractament.
*	@author Oriol Camps, Jaume Gauchola, Pau Muñoz
*/

import java.util.*;
import principal.*;
import accio.*;
import rol.*;

public class Memoria{

    private double[][] _memoria; ///< Estructura bàsica de la memòria
    private final Maquina _jugador; ///< Jugador associat a la memòria
    private double _guanyMax; ///< Guany màxim per a realitzar l'exploratòria d'opcions
    private final int _posTauler; ///< Posicio on comença el tauler
    private Accio _accioMax; ///< Accio màxima per a realitzar l'exploratòria d'opcions
    private ArrayList<Carta> _columMem; ///< Carta associada a cada columna de _memoria

    //Constructors:
    
    /**
	*  @pre Cert
	*  @post S'ha generat la memoria amb les dades entrades
	*  @param j Jugador màquina
	*  @param nJugs Nombre de jugadors de joc
	*  @param nCartes Nombre de cartes de joc
	*  @param cartes Conjunt de totes les cartes que formen el joc
	*/
    public Memoria(Maquina j, int nJugs, int nCartes, LinkedList<Carta> cartes){
    	_accioMax = null;
    	_guanyMax = -1;
        _jugador = j;
        int nFiles;
        int cartesTauler;
        if(nJugs == 2)
            nFiles = nJugs*3;
        else if(nJugs == 3)
            nFiles = nJugs*2;
        else
        	nFiles = nJugs;
        cartesTauler = nCartes - nFiles;
        _posTauler = nFiles;
        nFiles += cartesTauler;
        _memoria = new double[nFiles][nCartes]; //S'inicialitza a 0 -> Correcte, ningú té cap carta
        _columMem = new ArrayList<>();
        for(Carta c: cartes) //Emplenem indexs de cartes (per acces rapid)
        	_columMem.add(c);
    }

    //Gestió bàsica:
    
    /**
	*  @pre Cert
	*  @post S'ha afegit la informació al mapejat de memòria
	*  @param posJug Posició del jugador
	*  @param posCarta Posició de la carta
	*/
    public void afegirInfo(int posJug, int posCarta){
    	//Algú altre té aquesta carta?
    	for(int i = 0; i < _memoria.length; i++){
            if(_memoria[i][posCarta] != 0){
                //Si no té aquesta potser té la que creiem que tenia l'altre jugador
                //Tambe posem a 0 la resta de la fila del jugador descobert
                for(int j = 0; j < _memoria[i].length; j++){
                    if(j != posCarta && _memoria[posJug][j] != 0){
                        _memoria[i][j] += _memoria[posJug][j]; 
                        _memoria[posJug][j] = 0;
                        if(_memoria[i][j] > 1)
                            _memoria[i][j] = 1;
                    }
                    else
                        _memoria[i][posCarta] = 0;
                }
            }
    	}
        _memoria[posJug][posCarta] = 1;
    }
    
    /**
    *   @return int
	*   @pre Cert
	*   @post Retorna el jugador que té la carta entrada
	*   @param c Carta buscada
	*/
    public int teCarta(Carta c){
    	int nCarta = _columMem.indexOf(c);
    	int max = 0;
    	double maxProb = 0;
    	for(int i = 0; i < _memoria.length; i++){
            if(_memoria[i][nCarta] > maxProb){
                max = i;
                maxProb = _memoria[i][nCarta];
            }
    	}
    	return max/_jugador.cartes().size();
    }
    
    /**	
    *   @pre c i jug formen part del joc
   	*   @post S'ha modificat la carta amb l'informació entrada
   	*   @param jug Jugador del qual hem obtingut coneixement
   	*   @param c Carta de la qual hem obtingut coneixement
   	*   @param pos Posició de la carta dins del jugador
   	*/
    public void modificarCarta(Jugador jug, Carta c, int pos){
    	int fila;
    	if(jug == null)
            fila = _posTauler + pos;
    	else
            fila = jug.getId()*jug.cartes().size() + pos;
    	int columna = _columMem.indexOf(c);
    	afegirInfo(fila, columna);
    }

    /**	
    *   @return Accio
    *   @pre El joc no s'ha acabat
   	*   @post Retorna la millor acció que pot realitzar el jugador
   	*   @param joc Joc actual
   	*   @param restringit Cert si hi ha restriccions
   	*/
    public Accio millorOpcio(Joc joc, boolean restringit){
    	//Resetejem:
    	_accioMax = null;
    	_guanyMax = -1;
    	int nCartes = _jugador.cartes().size();
    	int jug = _jugador.getId();
    	//Nota: Nomes fem intercanvi amb la pitjor carta que tenim per evitar comportaments ciclics
    	for(int cartaPropia = 0; cartaPropia < nCartes; cartaPropia++){ //Com que a la modalitat de 2 i 3 jugadors podem tenir més d'una carta...
            int fila = jug*nCartes + cartaPropia; //Fila actual del jugador -> JugX, CartaY
            double[] memJug = _memoria[fila];
            for(int cartaPropiaPossible = 0; cartaPropiaPossible < memJug.length; cartaPropiaPossible++){
                if(memJug[cartaPropiaPossible] > 0 && !_jugador.haConsultat(cartaPropia)) //Aquí mirem totes les opcions possibles:
                    guanyAccioRol(joc, restringit, memJug, cartaPropia, cartaPropiaPossible); //Accions de rol
                guanyConsulta(joc, restringit, memJug, cartaPropia, cartaPropiaPossible); //Sempre consultem, independentment de no tenir memoria de res
            }
    	}
    	int pitjorPos = pitjorCartaPropia(joc);
		guanyIntercanvi(joc, restringit, pitjorPos, getMaxPos(_memoria[pitjorPos])); //Intercanvis
    	guanyAccioRolFarol(joc, restringit);
    	passarTorn();
    	if(_accioMax instanceof Intercanvi && _guanyMax == 0)
    		((Intercanvi)_accioMax).real(false);
    	if(_accioMax instanceof AccioRol && ((AccioRol)_accioMax).rol().toString().equals("Maso")){
    		Maso m = (Maso)((AccioRol)_accioMax).rol();
    		if(m.adoptat() != null)
    			_accioMax = new AccioRol(_jugador, m.adoptat());
    		else
    			_accioMax = new AccioRol(_jugador, triarRolMaxim(joc));
    	}
        return _accioMax;
    }
    
    /**	
    *   @pre Cert
   	*   @post Actualitza l'accio màxima si l'acció provada és major
   	*   @param joc Joc actual
   	*   @param restringit Cert si hi ha restriccions
   	*/
    private void guanyAccioRolFarol(Joc joc, boolean restringit){
    	if(!restringit){
	    	double nouGuany;
	    	int nJugs = joc.getJugadors().size();
	    	//Mirem possibles cartes del tauler.
	    	for(int i = nJugs*_jugador.cartes().size() - 1; i < _memoria.length; i++){
	    		for(int j = 0; j < _memoria[i].length; j++){
	    			AccioRol novaAccio = new AccioRol(_jugador, _columMem.get(j).getRol());
	                nouGuany = novaAccio.guany(_memoria[i][j]);
	                nouGuany-=0.5;
                        if(nouGuany < 0)
	                    nouGuany = 0; //Li traiem guany perque estem anat de farol..
	                if(nouGuany > _guanyMax){
	                	_guanyMax = nouGuany;
	                	_accioMax = novaAccio;
	                }
	    		}
	    	}
    	}
    }
    
    /**	
    *   @pre Cert
   	*   @post Actualitza l'accio màxima si l'acció provada és major
   	*   @param joc Joc actual
   	*   @param restringit Cert si hi ha restriccions
   	*   @param memJug Fila corresponent a la carta que s'esta provant
   	*   @param nCartaPropia numero de carta del jugador (dins la ma de cartes)
   	*   @param nCartaPropiaPossible Suposada carta qe tenim (equival al nColumna de la matriu de coneixement)
   	*/
    private void guanyAccioRol(Joc joc, boolean restringit, double[] memJug, int nCartaPropia, int nCartaPropiaPossible){
    	if(!restringit){
    		double nouGuany;
            AccioRol novaAccio = new AccioRol(_jugador, _columMem.get(nCartaPropiaPossible).getRol());
            nouGuany = novaAccio.guany(_memoria[_jugador.getId()*_jugador.cartes().size()+nCartaPropia][nCartaPropiaPossible]);
            if(nouGuany > _guanyMax){
            	_guanyMax = nouGuany;
                _accioMax = novaAccio;
            }
        }
    }
    
    /**	
    *   @pre Cert
   	*   @post Actualitza l'accio màxima si l'acció provada és major
   	*   @param joc Joc actual
   	*   @param restringit Cert si hi ha restriccions
   	*   @param memJug Fila corresponent a la carta que s'esta provant
   	*   @param nCartaPropia numero de carta del jugador (dins la ma de cartes)
   	*   @param nCartaPropiaPossible Suposada carta qe tenim (equival al nColumna de la matriu de coneixement)
   	*/
    private void guanyConsulta(Joc joc, boolean restringit, double[] memJug, int nCartaPropia, int nCartaPropiaPossible){
    	double nouGuany = -1;
    	if(!restringit){
            Consulta novaConsulta = new Consulta(_jugador.getCarta(nCartaPropia), _jugador);
            nouGuany = novaConsulta.guany(memJug[nCartaPropiaPossible], _columMem.get(nCartaPropiaPossible).getRol());
            if(nouGuany > _guanyMax){
            	_guanyMax = nouGuany;
                _accioMax = novaConsulta;
            }
    	}
    }

    /**	
    *   @pre Cert
   	*   @post Actualitza l'accio màxima si l'acció provada és major
   	*   @param joc Joc actual
   	*   @param restringit Cert si hi ha restriccions
   	*   @param memJug Fila corresponent a la carta que s'esta provant
   	*   @param nCartaPropia numero de carta del jugador (dins la ma de cartes)
   	*   @param nCartaPropiaPossible Suposada carta qe tenim (equival al nColumna de la matriu de coneixement)
   	*/
    private void guanyIntercanvi(Joc joc, boolean restringit, int nCartaPropia, int nCartaPropiaPossible){
    	int jug = _jugador.getId();
    	int nCartes = _jugador.cartes().size();
    	double nouGuany;
    	//Mirem per cada jugador i cada carta possible a tenir
		for(int jugInt = 0; jugInt < _memoria.length; jugInt++){
			if(jugInt < jug*nCartes || jugInt > jug*nCartes+nCartes-1){ //La carta no és nostra
				for(int cartaInt = 0; cartaInt < _memoria[jugInt].length; cartaInt++){
					if(_memoria[jugInt][cartaInt] > 0){ //Si en tenim coneixement..
						Jugador j2 = getJugador(joc, jugInt, _jugador.cartes().size(), joc.getJugadors().size());
						Carta c2 = _columMem.get(cartaInt);
						//Necessitem carta jug1, carta jug2, jug2
						nouGuany = Intercanvi.guany(_memoria[jugInt][cartaInt], _columMem.get(nCartaPropiaPossible), c2, _jugador, j2);
		                if(nouGuany > _guanyMax){
		                    if(j2 == null)
		                    	_accioMax = new Intercanvi(_jugador.cartes(), nCartaPropia, _jugador.getId()+1, joc.getCartes(), jugInt - joc.getJugadors().size()*nCartes, 5, true);
		                    else
		                    	_accioMax = new Intercanvi(_jugador.cartes(), nCartaPropia, _jugador.getId()+1, j2.cartes(), jugInt - j2.getId()*nCartes, j2.getId()+1, true);
		                    _guanyMax = nouGuany;
		                }
					}
				}
			}
		}
    }
    
    /**	
    *   @return Jugador
    *   @pre fila < _memoria.length
   	*   @post Retorna l'identificador del jugador
   	*   @param joc Joc actual
   	*   @param fila Fila de _memoria
   	*   @param nCartesJug Nombre de cartes del jugador
   	*   @param nJugs nombre de jugadors totals de la partida
   	*/
    private Jugador getJugador(Joc joc, int fila, int nCartesJug, int nJugs){
    	int jugMax = nJugs*nCartesJug;
    	if(fila < jugMax){ //És un jugador
    		return joc.getJugador(fila/nCartesJug);
    	}
    	else
    		return null;
    }
    
    /**	
    *   @return int
    *   @pre j forma part de joc
   	*   @post Retorna la carta amb la que vol protestar (o -1 si no vol)
   	*   @param joc Joc actual
   	*   @param j Jugador que vol realitzar l'acció
   	*   @param rol Rol que vol executar j
   	*/
    public int protestar(Jugador j, String rol, Joc joc){
        int pos = -1;
        //Mirem si coneixem carta
        int index = 0;
        boolean trobat = false;
        Iterator<Carta> it = _columMem.iterator();
        while(!trobat && it.hasNext()){
            Carta cAux = it.next();
            trobat = cAux.getRol().toString().equals(rol);
            if(!trobat)
                index++;
        }
        double maxProb = 0;
        int aux = 0;
        for(int i = 0; i < _memoria.length; i++){
        	double novaProb = _memoria[i][index];
        	if(novaProb > maxProb){
        		aux = i;
        		maxProb = novaProb;
        	}
        }
        if(maxProb >= 0.10){ //Si tenim minima opcio, protestem, en el pitjor dels casos coneixerem cartes
        	int jugMax = joc.getJugadors().size()*_jugador.cartes().size();
        	if(aux < jugMax){
        		Jugador jug = joc.getJugador(aux/_jugador.cartes().size());
        		//Coneixem la carta
                if(jug.getId() != j.getId()){ //No la té ell
                    //No la té el jugador, la tenim nosaltres?
                    if(jug.getId() == _jugador.getId() || _jugador.monedes() > 1 && (jug.monedes()>=joc.monedesFinals()*0.8 || jug.monedes() == 1) || (maxProb < 0.3 && _jugador.monedes() > 1))
                    	pos = aux - jug.getId()*_jugador.cartes().size(); //Sí, protestem!!!	
                }
        	}
        }
        return pos;
    }

    /**
    *   @pre S'ha realitzat (o no), un intercanvi entre dos jugadors
    *   @post S'han actualitzat els coneixements de la maquina
    *	@param jug1 Primer jugador implicat en l'intercanvi
   	*   @param jug2 Segon jugador implicat en l'intercanvi
    *   @param pos1 Posició de carta asociada al primer jugador
    *   @param pos2 Posició de la carta asociada al segon jugador
   	*   @param propi Cert si a l'intercanvi hi participem nosaltres
    */
    public void actualitzarIntercanvi(int jug1, int jug2, int pos1, int pos2, boolean propi){
    	int midaCartes = _jugador.cartes().size();
    	if(jug1 == -1) jug1 = _posTauler;
    	else jug1 *= midaCartes;
    	if(jug2 == -1) jug2 = _posTauler;
    	else jug2 *= midaCartes;
    	if(propi){
    		double[] aux = _memoria[jug1 + pos1];
    		_memoria[jug1 + pos1] = _memoria[jug2 + pos2];
    		_memoria[jug2 + pos2] = aux;
    	}
    	else{
        	for(int i = 0; i < _memoria[jug2 + pos2].length; i++){
        		if(_memoria[jug1 + pos1][i] == 0 && _memoria[jug2 + pos2][i] != 0){
        			_memoria[jug1 + pos1][i] = _memoria[jug2 + pos2][i]*0.8;
        			_memoria[jug2 + pos2][i] *= 0.4; //Suposem que l'ha intercanviat.. Ratio més baix
        		}
        		else if(_memoria[jug2 + pos2][i] == 0 && _memoria[jug1 + pos1][i] != 0){
        			_memoria[jug2 + pos2][i] = _memoria[jug1 + pos1][i]*0.8;
        			_memoria[jug1 + pos1][i] *= 0.4; //Suposem que l'ha intercanviat.. Ratio més baix
        		}
        		else if(_memoria[jug2 + pos2][i] != 0 && _memoria[jug1 + pos1][i] != 0){
        			double novaProb = (_memoria[jug1 + pos1][i] + _memoria[jug2 + pos2][i])/2;
        			_memoria[jug2 + pos2][i] = novaProb;
        			_memoria[jug1 + pos1][i] = novaProb*0.5;
        		}
        	}
    	}
    }

    /**
    *   @pre Cert
    *   @post Passa un torn i aplica la degeneració corresponent
    */
    private void passarTorn(){
        //A cada torn, la memoria es degenera entre un 1 i 2% (Dificil), un 2 i 10% (Mig) i un 10 i 20% (Fàcil)
        Random random = new Random();
        for(int i = 0; i < _memoria.length; i++){
        	for(int j = 0; j < _memoria.length; j++){
        		int dificultat = ((Maquina)_jugador).dificultat();
        		double prob;
        		if(dificultat == 0)
        			prob = (double)(random.nextInt((20 - 10) + 1) + 10)/100;
        		else if(dificultat == 1)
        			prob = (double)(random.nextInt((10 - 2) + 1) + 2)/100;
        		else
        			prob = (double)(random.nextInt((2 - 1) + 1) + 1)/100;
                _memoria[i][j] -= prob;
                if(_memoria[i][j] < 0)
                	_memoria[i][j] = 0;
        	}
        }
    }
    
    /**
    *   @return int
    *   @pre Cert
    *   @post Retorna la posició amb un coneixement major
    *   @param jugador Fila de la qual volem buscar el major coneixement
    */
    private int getMaxPos(double[] jugador){
    	int max = 0;
    	double maxProb = -1;
    	for(int i = 0; i < jugador.length; i++){
            if(jugador[i] > maxProb){
                max = i;
                maxProb = jugador[i];
            }
    	}
    	return max;
    }

    //Rol Bisbe:

    /**
    *   @return Jugador
    *   @pre Cert
    *   @post Retorna el jugador del quan en tenim el màxim coneixement
    *   @param jugs Llista de jugadors
    */
    public Jugador triarJugador(LinkedList<Jugador> jugs, Joc joc){
        double max = -1;
        Jugador maxJug = null;
        for(Jugador j: jugs){
            //Busquem el guany
        	double nouGuany;
       		nouGuany = guanyGlobal(j, joc);
            if(nouGuany > max){
                maxJug = j;
                max = nouGuany;
            }
        }
        return maxJug;
    }
    
    //Rol Inquisidor:
    
    /**
    *   @return Jugador
    *   @pre Cert
   	*   @post Retorna el jugador del quan en tenim el màxim coneixement
   	*   @param joc Joc actual
   	*/
    public Jugador jugadorMaximConeixement(Joc joc){
    	int nJug = 0;
    	double probMax = 0;
    	int nCartes = _jugador.cartes().size();
    	for(int i = 0; i < joc.getJugadors().size()*nCartes; i++){
            int posMax = getMaxPos(_memoria[i]); //Carta màxima
            Jugador nouJugador = joc.getJugador(i/nCartes);
            if(_memoria[i][posMax] > probMax){
                nJug = nouJugador.getId();
                probMax = _memoria[i][posMax];
            }
    	}
    	return joc.getJugador(nJug);
    }
    
    /**
    *   @return Rol
    *   @pre Cert
   	*   @post Retorna el rol que creu que té el jugador
   	*/
    public Rol rolPropi(){
    	int nJug = _jugador.getId();
    	return _columMem.get(getMaxPos(_memoria[nJug])).getRol();
    }
    
    //Rol Puta:
    
    /**
    *   @return double
    *   @pre Cert
   	*   @post Retorna la carta sobre la qual en tenim un coneixement major
   	*   @param joc Joc actual
   	*/
    public double coneixementMillorCartaNoPropia(Joc joc){
        double guanyMax = -1;
    	for(Carta c: _columMem){
    		if(!c.getRol().toString().equals("Espia") && !c.getRol().toString().equals("Puta") && !c.getRol().toString().equals("Bisbe") && !c.getRol().toString().equals("Maso")){ //Evitem recursió sense fi
	            double nouGuany = c.getRol().guany(_jugador, joc);
	            int carta = teCarta(c);
	            if(carta < joc.getJugadors().size() && nouGuany > guanyMax && joc.getJugador(carta) != _jugador){
	                guanyMax = nouGuany;
	            }
    		}
    	}
    	return guanyMax;
    }
    
    /**
    *   @return int
    *   @pre Cert
   	*   @post Retorna el jugador que té la millor carta (sense comptar-nos a nosaltres)
   	*   @param joc Joc actual
   	*/
    public int jugadorMillorCartaNoPropia(Joc joc){
        double guanyMax = -1;
    	int max = 0;
    	for(Carta c: _columMem){
            double nouGuany = c.getRol().guany(_jugador, joc);
            int carta = teCarta(c);
            if(carta < joc.getJugadors().size() && nouGuany > guanyMax && joc.getJugador(carta) != _jugador){
                max = carta;
                guanyMax = nouGuany;
            }
    	}
    	return max;
    }
    
    //Rol Camperol:
    
    /**
    *   @return boolean
    *   @pre Cert
   	*   @post Retorna el jugador del quan en tenim el màxim coneixement
   	*   @param joc Joc actual
   	*/
    public boolean hiHaCamperol(Camperol r){
    	int pos = -1;
    	int aux = 0;
    	Iterator<Carta> it = _columMem.iterator();
    	while(pos == -1 && it.hasNext()){
            Carta c = it.next();
            if(c.getRol().equals(r))
                pos = aux;
            else
                aux++;
    	}
    	
    	double max1 = 0;
    	double max2 = 0;
    	for(int i = 0; i < _memoria[0].length; i++){
            if(_memoria[i][pos] > max1)
                max1 = _memoria[i][pos];
            if(_memoria[i][pos] > max2)
                max2 = _memoria[i][pos];
    	}
    	return max2 >= 0.5;
    }
    
    //Rol Masó:
    
    /**
    *   @return Rol
    *   @pre Cert
   	*   @post Retorna el rol mb guany màxim
   	*   @param joc Joc actual
   	*/
    public Rol triarRolMaxim(Joc joc){
    	double guanyMax = -1;
    	Rol max = null;
    	for(Carta c: _columMem){
            double nouGuany = c.getRol().guany(_jugador, joc);
            if(nouGuany > guanyMax && !c.getRol().toString().equals("Maso")){
            	System.out.println(c.getRol());
                max = c.getRol();
                guanyMax = nouGuany;
            }
    	}
    	return max;
    }
    
    //Rol Bufó:
    
    /**
    *   @return double
    *   @pre Cert
   	*   @post Retorna el guany global del jugador
   	*   @param joc Joc actual
   	*   @param jug jugador del qual en volem saber el guany
   	*/
    public double guanyGlobal(Jugador jug, Joc joc){
    	int nCartes = jug.cartes().size();
    	int filaInicial = jug.getId()*nCartes;
    	int max = 0;
    	for(int i = filaInicial; i < filaInicial + nCartes; i++){
            max += _memoria[i][getMaxPos(_memoria[i])];
    	}
    	return max/nCartes;
    }
    
    //Rol Espia:
  
    /**
    *   @return Jugador
    *   @pre Cert
   	*   @post Retorna el jugador del quan en tenim el màxim coneixement
   	*   @param joc Joc actual
   	*/
    public int jugadorRolAEspiar(Joc joc){
        double guanyMax = -1;
    	int max = 0;
    	for(Carta c: _columMem){
            double nouGuany = c.getRol().guany(_jugador, joc);
            if(teCarta(c) < joc.getJugadors().size() && nouGuany > guanyMax && joc.getJugador(teCarta(c)) != _jugador){
                max = teCarta(c);
                guanyMax = nouGuany;
            }
    	}
    	return max;
    }
    
    /**
    *   @return int
    *   @pre Cert
   	*   @post Retorna el rol associat al jugador amb un guany més gran
   	*   @param joc Joc actual
   	*   @param jug Jugador del qual volem saber el rol
   	*/
    public int millorCartaJugadorEspiar(Joc joc, int jug){
        //Busquem la carta del jugador amb més guany:
        int inicial, fi;
        if(jug >= joc.getJugadors().size()){
            inicial = _posTauler;
            fi = _memoria.length;
        }
        else{
            inicial = jug*_jugador.cartes().size();
            fi = inicial + _jugador.cartes().size();
        }
        int posMax = 0;
        double guanyMax = 0;
        for(int i = inicial; i < fi; i++){
            int columna = getMaxPos(_memoria[i]);
            double guany = _columMem.get(columna).getRol().guany(_jugador, joc);
            if(guany > guanyMax){
                posMax = i;
                guanyMax = guany;
            }
        }
        return posMax;
    }
    
    /**
    *   @return Rol
    *   @pre Cert
   	*   @post Retorna el Rol més adient a espiar
   	*   @param joc Joc actual
   	*/
    public Rol buscarRolMaximNoPropi(Joc joc){
    	double guanyMax = -1;
    	Rol max = null;
    	for(Carta c: _columMem){
    		if(!c.getRol().toString().equals("Espia") && !c.getRol().toString().equals("Puta") && !c.getRol().toString().equals("Bisbe") && !c.getRol().toString().equals("Maso")){ //Evitem recursió sense fi
	            double nouGuany = c.getRol().guany(_jugador, joc);
	            int carta = teCarta(c);
	            if(carta < joc.getJugadors().size() && nouGuany > guanyMax && joc.getJugador(teCarta(c)) != _jugador){
	                max = c.getRol();
	                guanyMax = nouGuany;
	            }
    		}
    	}
    	return max;
    }
    
    //Generics:
    
    /**
    *   @return int
    *   @pre Cert
   	*   @post Retorna la pitjor carta que tenim
   	*   @param joc Joc actual
   	*/
    public int pitjorCartaPropia(Joc joc){
    	int nCartes = _jugador.cartes().size();
    	int jug = _jugador.getId();
    	int minPos = 0;
    	int maxPos = 0;
    	double minGuany = 1.1;
    	for(int cartaPropia = 0; cartaPropia < nCartes; cartaPropia++){
            int fila = jug*nCartes + cartaPropia; //Fila actual del jugador -> JugX, CartaY
            maxPos = getMaxPos(_memoria[fila]);
            double nouGuanyInter = _columMem.get(maxPos).getRol().guany(_jugador, joc);
            if(nouGuanyInter < minGuany){
                minPos = cartaPropia;
                minGuany = nouGuanyInter;    			
            }
    	}
    	return minPos/nCartes;
    }

}
