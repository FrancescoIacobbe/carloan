package entity;

/**
 * Fornisce i servizi di scrittura per gli optional.
 * 
 * @author Gaetano
 * 
 */
public interface SRequisitoInt {

	/**
	 * Inserisce un nuovo optional nel sistema.
	 * 
	 * @param nome
	 *            - nome del nuovo optional.
	 * @param prezzo
	 *            - prezzo del nuovo optional.
	 * @param descrizione
	 *            - descrizione del nuovo optional.
	 * @return true sse l'inserimento va a buon fine.
	 */
	boolean registraRequisito(String idRequisito, String modelli, String descrizione);

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
	 *            - nuova descrizione dell'optional.
	 * @param attivo
	 *            - true sse l'optional è attivo.
	 * @return true sse l'inserimento va a buon fine.
	 */
	boolean setDatiRequisito(String idRequisito, String modelli,
			String descrizione);

}
