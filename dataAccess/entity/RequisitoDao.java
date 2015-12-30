package dataAccess.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per le operazioni di memorizzazione e recupero dati inerenti i
 * vari Requisito.
 * 
 * @author Gaetano
 * 
 */
public interface RequisitoDao {
	
	
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
	public boolean registraRequisito(String id, String modelli, String descrizione);

	/**
	 * Fornisce le informazioni relative gli optional presenti nel sistema.
	 * @return un {@link ArrayList} di {@link HashMap} con i dati relativi gli optional del sistema.
	 */
	public ArrayList<HashMap<String, String>> getRequisitoSistema();

	/**
	 * Fornisce le informazioni relative ad un determinato optional presente nel
	 * sistema.
	 * @param idRequisito - id dell'optional.
	 * @return un dizionario contenente i dati relativi all'optional
	 * 			o null se non esiste un optional con l'id specificato.
	 */
	public HashMap<String, String> getDatiRequisito(String idRequisito);

	/**
	 * Aggiorna i dati relativi ad una determinato optional presente nel
	 * sistema.
	 * 
	 * @param idRequisito
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
	public boolean setDatiRequisito(String id, String modelli, String descrizione);

	/**
	 * Fornisce un elenco con gli optional presenti nel sistema che rispettano il parametro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sugli optional.
	 */
	public ArrayList<HashMap<String, String>> ricercaRequisito(String key);
}
