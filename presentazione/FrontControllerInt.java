package presentazione;

import java.util.ArrayList;

/**
 * Riceve le richieste dalle interfacce e le inoltra all'ApplicationController.
 * 
 * @author Gianluca
 * 
 */
public interface FrontControllerInt {

	/**
	 * Invia la richiesta di un servizio all'ApplicationController.
	 * 
	 * @param key
	 *            la chiave del servizio da cercare nel file FC.xml.
	 * @param p
	 *            i parametri del servizio.
	 * @return null se il servizio è di tipo void, altrimenti il tipo restituito
	 *         dall'esecuzione del servizio richiesto.
	 */
	public Object processRequest(String key, ArrayList<String> p);

	/**
	 * Invia la richiesta di un servizio all'ApplicationController.
	 * 
	 * @param key
	 *            la chiave del servizio da cercare nel file FC.xml.
	 * @return null se il servizio è di tipo void, altrimenti il tipo restituito
	 *         dall'esecuzione del servizio richiesto.
	 */
	public Object processRequest(String key);
}
