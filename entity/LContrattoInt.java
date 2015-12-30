package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia che fornisce i servizi di lettura per le competizioni.
 * 
 * @author Gianluca
 * 
 */
public interface LContrattoInt {

	/**
	 * Fornisce i dati relativi ad una competizione.
	 * 
	 * @param idCompetizione
	 *          - l'id della competizione.
	 *            
	 * @param limiteTemporale 
	 * 			- limite temporale entro il quale è possibile 
	 * 			modificare una competizione.
	 * 
	 * @return un dizionario contenente i dati relativi la competizione o null
	 *         se non esiste nel sistema una competizione con l'id specificato.
	 */
	public HashMap<String, String> getDatiContratto(String idCompetizione);
	
	public ArrayList<HashMap<String, String>> getContrattoSistema();

}
