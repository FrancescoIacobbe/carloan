package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia che fornisce i servizi di lettura per i partecipanti.
 * 
 * @author Gianluca
 * 
 */
public interface LClienteInt {

	/**
	 * Fornisce l'id e l'username del partecipante specificato.
	 * 
	 * @param username
	 *            l'username del partecipante.
	 * @return un dizionario contenente l'id e l'username del partecipante o
	 *         null se non esiste un partecipante con l'username specificato.
	 */
	public HashMap<String, String> getDatiCliente(String username);
	/**
	 * Fornisce le competizioni a cui un partecipante è iscritto e la cui
	 * iscrizione è modificabile.
	 * 
	 * @param id
	 *            l'id del partecipante.
	 * @return una lista di dizionari contenenti le informazioni sulle
	 *         competizioni, altrimenti null se l'id del partecipante non è
	 *         presente nel sistema o non ci sono competizioni a cui il
	 *         partecipante è iscritto e la cui iscrizione è modificabile.
	 */
	public ArrayList<HashMap<String, String>> getContrattoCliente(String id);


	/**
	 * Fornisce i dati account di un partecipante.
	 * 
	 * @param username
	 *            l'username del partecipante.
	 * @return un dizionario contenente i dati account del partecipante o null
	 *         se il partecipante non esiste.
	 */
	public HashMap<String, String> getAccountCliente(String username);

	/**
	 * Fornisce i dati anagrafici di un partecipante.
	 * 
	 * @param id
	 *            l'id del partecipante.
	 * @return un dizionario contenente i dati anagrafici del partecipante o
	 *         null se il partecipante non esiste.
	 */
	public HashMap<String, String> getAnagraficaCliente(String id);


	/**
	 * Fornisce una o più competizioni le cui iscrizioni sono modificabili a cui
	 * un partecipante è iscritto, in base al criterio di ricerca.
	 * 
	 * @param id
	 *            l'id del partecipante.
	 * @param nomeCompetizione
	 *            il parametro di ricerca.
	 * @return una lista di dizionari che rappresentano le competizioni o null
	 *         se non ci sono competizioni che rispettano i criteri di ricerca.
	 */
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String id, String idContratto);

	

	/**
	 * Fornisce una lista di dizionari contenenti le informazioni su tutti i partecipanti iscritti al sistema.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui partecipanti iscritti al sistema.
	 */
	public ArrayList<HashMap<String, String>> getClientiSistema();

	/**
	 * Fornisce una lista di dizionari contenenti le informazioni su tutti i partecipanti iscritti al sistema
	 * che rispettano il paramentro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui partecipanti iscritti al sistema.
	 */
	public ArrayList<HashMap<String, String>> ricercaClienti(String key);
	
	boolean registraCliente(String username, String password, String email,
			String nome, String cognome, String dataNascita, String CF,
			String città, String indirizzo, String cap, String numTelefono);

}
