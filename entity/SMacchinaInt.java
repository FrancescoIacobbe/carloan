package entity;

/**
 * Fornisce i servizi di scrittura per gli optional.
 * 
 * @author Gaetano
 * 
 */
public interface SMacchinaInt {

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
	boolean registraMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia);

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
	 *            - nuova descrizione dell'optional.
	 * @param attivo
	 *            - true sse l'optional è attivo.
	 * @return true sse l'inserimento va a buon fine.
	 */
	boolean setDatiMacchina(String targa,String modello,String disponibilita,
			String manuntenzione, int ultimoKm,String fascia);

	boolean eliminaMacchina(String targa);

}
