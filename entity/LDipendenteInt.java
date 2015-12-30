package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia che fornisce i servizi di lettura per i manager delle
 * competizioni.
 * 
 * @author Gianluca
 * 
 */
public interface LDipendenteInt {

	/**
	 * Fornisce l'id e l'username del Manager di Competizione specificato.
	 * 
	 * @param username
	 *            l'username del manager.
	 * @return un {@link HashMap} contenente i dati.
	 */
	public HashMap<String, String> getDatiDipendente(String username);

	/**
	 * Fornisce un elenco con le competizioni non concluse gestite da un manager
	 * di una competizione.
	 * 
	 * @param id
	 *            l'id del manager della competizione.
	 * @return una lista di dizionari contenenti le informazioni su ciascuna
	 *         competizione o null se non esiste nessuna competizione attiva
	 *         gestita dal manager specificato o null se non esiste il manager
	 *         specificato.
	 */
	public ArrayList<HashMap<String, String>> getContrattiDipendente(String id);


	/**
	 * Fornisce i dati account di un managerCompetizione.
	 * 
	 * @param username
	 *            l'username del partecipante.
	 * @return un dizionario contenente i dati account del partecipante o null
	 *         se il partecipante non esiste.
	 */
	public HashMap<String, String> getAccountDipendente(String username);

	/**
	 * Fornisce un elenco con le competizioni non concluse gestite da un manager
	 * di una competizione, in base al parametro di ricerca specificato.
	 * 
	 * @param idManager
	 *            l'id del manager della competizione.
	 * @param nomeCompetizione
	 *            il parametro di ricerca.
	 * @return una lista di dizionari contenenti le informazioni di ogni singola
	 *         competizione che rispetta i parametri di ricerca, o null se
	 *         nessuna competizione rispetta i parametri o null se non ci sono
	 *         competizioni non concluse associate al manager specificato o null
	 *         se il manager specificato non esiste.
	 */
	public ArrayList<HashMap<String, String>> ricercaContratti(
			String idDipendente, String idContratto);

	
	/**
	 * Fornisce un elenco con i dati relativi i manager di competizione presenti
	 * nel sistema.
	 * 
	 * @return un {@link ArrayList} di {@link HashMap} contenenti i dati
	 *         relativi i manager di competizione.
	 */
	public ArrayList<HashMap<String, String>> getDipendenteSistema();

	/**
	 * Fornisce i dari relativi ad una determinato manager di comepetizione.
	 * 
	 * @param idManager
	 *            - id del manager di competizione.
	 * @return un dizionario contenente i dati del manager di competizione.
	 */
	public HashMap<String, String> getDipendente(String idDipendente);

	/**
	 * Fornisce un elenco con i manager di competizione del sistema che rispettano il parametro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui manager.
	 */
	public ArrayList<HashMap<String, String>> ricercaDipendente(String key);
}
