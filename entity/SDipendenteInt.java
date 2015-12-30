package entity;

/**
 * Fornisce i servizi di scrittura per i manager delle competizioni.
 * 
 * @author Gianluca
 * 
 */
public interface SDipendenteInt {

	/**
	 * Aggiorna i dati account di un managerCompetizione.
	 * 
	 * @param username
	 *            l'username del managerCompetizione di cui inserire i dati.
	 * @param email
	 *            l'email.
	 * @param password
	 *            la password.
	 * @return true sse l'aggiornamento è andato a buon fine, altrimenti false.
	 */
	public boolean setAccountDipendente(String username, String email, String password);

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
	 * Inserisce un nuovo mananager di una competizione nel sistema
	 * @param username l'username del manager.
	 * @param nome il nome del manager.
	 * @param cognome il cognome del manager.
	 * @param email l'email del manager.
	 * @param password la password del manager.
	 * @return true sse l'inserimento è eseguito correttamente, altrimenti false.
	 */
	public boolean registraDipendente(String username,String password,String email,String nome,String cognome,
			String dataNascita,String cf,String città,String indirizzo,String cap,String numTelefono);

}
