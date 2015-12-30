package dataAccess.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per le operazioni di memorizzazione e recupero dati inerenti i
 * vari Macchina.
 * 
 * @author Gaetano
 * 
 */
public interface MacchinaDao {
	
	
	/**
	 * Inserisce un nuovo optional nel sistema.
	 * 
	 * @param nome
	 *            - nome del nuovo optional.
	 * @param prezzo
	 *            - prezzo del nuovo optional.
	 * @param descrizione
	 *            - descrizione del nuovo optional. Può essere null.
	 * @return true sse l'inserimento va a buon fine.
	 */
	public boolean registraMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia);

	/**
	 * Fornisce le informazioni relative gli optional presenti nel sistema.
	 * @return un {@link ArrayList} di {@link HashMap} con i dati relativi gli optional del sistema.
	 */
	public ArrayList<HashMap<String, String>> getMacchinaSistema();

	/**
	 * Fornisce le informazioni relative ad un determinato optional presente nel
	 * sistema.
	 * @param idMacchina - id dell'optional.
	 * @return un dizionario contenente i dati relativi all'optional
	 * 			o null se non esiste un optional con l'id specificato.
	 */
	public HashMap<String, String> getDatiMacchina(String targa);

	/**
	 * Aggiorna i dati relativi ad una determinato optional presente nel
	 * sistema.
	 * 
	 * @param idMacchina
	 *            - id dell'optional da aggiornare.
	 * @param nome
	 *            - nuovo nome dell'optional.
	 * @param prezzo
	 *            - nuovo prezzo dell'optional.
	 * @param descrizione
	 *            - nuova descrizione dell'optional. Può essere null.
	 * @param attivo
	 *            - true sse l'optiona è attivo.
	 * @return true sse l'inserimento va a buon fine.
	 */
	public boolean setDatiMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia);

	/**
	 * Fornisce un elenco con gli optional presenti nel sistema che rispettano il parametro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sugli optional.
	 */
	public ArrayList<HashMap<String, String>> ricercaMacchine(String key);

	public boolean eliminaMacchina(String targa);
}
