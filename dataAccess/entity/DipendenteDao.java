package dataAccess.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per la memorizzazione e il recupero dei dati inerenti i manager delle competizioni.
 * @author Gianluca
 *
 */
public interface DipendenteDao {

	/**
	 * Fornisce id e username del manager di competizione specificato.
	 * @param username l'username del manager di competizione.
	 * @return un {@link HashMap} con i dati.
	 */
	public HashMap<String,String> getDatiDipendente(String username);

	/**
	 * Fornisce un elenco con le competizioni non concluse gestite da un manager di una competizione.
	 * @param id l'id del manager della competizione.
	 * @return una lista di dizionari contenenti le informazioni su ciascuna competizione
	 * 			o una lista vuota se non esiste nessuna competizione attiva gestita dal manager specificato
	 * 			o se non esiste il manager specificato.
	 */
	public ArrayList<HashMap<String, String>> getContrattiDipendente(String id);

	
	/**
	 * Fornisce i dati account di un managerCompetizione.
	 * 
	 * @param username
	 *            l'username del managerCompetizione.
	 * @return un dizionario contenente i dati account del partecipante o null
	 *         se il partecipante non esiste.
	 */
	public HashMap<String, String> getAccountDipendente(String username);

	/**
	 * Imposta i dati account di un manager di una competizione.
	 * @param username l'username del manager.
	 * @param email l'email del manager.
	 * @param password la password del manager.
	 * @return true sse l'inserimento è andato a buon fine, altrimenti false.
	 */
	public boolean setAccountDipendente(String username, String email, String password);

	/**
	 * Fornisce un elenco con le competizioni non concluse gestite da un manager di una competizione,
	 * in base al parametro di ricerca specificato.
	 * @param idManager l'id del manager della competizione.
	 * @param nomeCompetizione il parametro di ricerca.
	 * @return una lista di dizionari contenenti le informazioni di ogni singola competizione che rispetta
	 * 			i parametri di ricerca, o una lista vuota se 
	 * 			nessuna competizione rispetta i parametri
	 * 			o non ci sono competizioni non concluse associate al manager specificato
	 * 			o il manager specificato non esiste.
	 */
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String idDipendente, String idContratto);

	

	/**
	 * Fornisce un elenco con i manager di competizione presenti nel sistema.
	 * @return un {@link ArrayList} di {@link HashMap} contenente i dati relativi i manager di competizione.
	 */
	public ArrayList<HashMap<String, String>> getDipendenteSistema();

	/**
	 * Aggiorna i dati relativi ad un manager di competizione.
	 * 
	 * @param idManager
	 *            - id del manager di competizione.
	 * @param nome
	 *            - nuovo nome del manager di competizione.
	 * @param cognome
	 *            - nuovo congnome del manager di competizione.
	 * @param email
	 *            - nuova email del manager di competizione.
	 * @param password
	 *            - nuova password del manager di competizione.
	 * @return true sse l'aggiornamento va a buon fine.
	 */
	public boolean setDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono);


	/**
	 * Fornisce i dati relativi ad un determinato manager di competizione.
	 * 
	 * @param idManager
	 *            - id del manager di competizione.
	 * @return un dizionario contenente i dati del manager di competizione.
	 */
	public HashMap<String, String> getDipendente(String idDipendente);

	/**
	 * Fornisce un elenco con i manager di competizione presenti nel sistema che rispettano il parametro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui manager.
	 */
	public ArrayList<HashMap<String, String>> ricercaDipendente(String key);


	/**
	 * Memorizza nel sistema un nuovo manager di una competizione.
	 * @param username l'username del manager.
	 * @param nome il nome del manager.
	 * @param cognome il cognome del manager.
	 * @param email l'email del manager.
	 * @param password la password del manager.
	 * @return true sse la memorizzazione avviene correttamente, altrimenti false.
	 */
	public boolean registraDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono);
}
