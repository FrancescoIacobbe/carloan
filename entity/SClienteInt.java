package entity;

/**
 * Fornisce i servizi di scrittura per i partecipanti.
 * 
 * @author Gianluca
 * 
 */
public interface SClienteInt {

	/**
	 * Imposta i dati anagrafici di un partecipante.
	 * 
	 * @param id
	 *            l'id del partecipante di cui impostare i dati anagrafici.
	 * @param nome
	 *            il nome.
	 * @param cognome
	 *            il cognome.
	 * @param sesso
	 *            il sesso, M o F.
	 * @param dataNascita
	 *            la data di nascita.
	 * @param CF
	 *            il codice fiscale, massimo 16 caratteri.
	 * @param tesseraSanitaria
	 *            la tessera sanitaria, massimo 20 caratteri.
	 * @param città
	 *            la città.
	 * @param via
	 *            l'indirizzo.
	 * @param civico
	 *            il civico.
	 * @return true sse l'inserimento dei dati va a buon fine, altrimenti false.
	 */
	public boolean setAnagraficaCliente( String nome, String cognome,
				String dataNascita, String CF,
				String città, String indirizzo, String cap, String numTelefono);

	/**
	 * Imposta i dati account di un partecipante.
	 * 
	 * @param username
	 *            l'username del partecipante di cui inserire i dati.
	 * @param email
	 *            l'email.
	 * @param password
	 *            la password.
	 * @return true sse l'aggiornamento è andato a buon fine, altrimenti false.
	 */
	public boolean setAccountCliente(String username, String email, String password);

	/**
	 * Inserisce un nuovo partecipante nel sistema.
	 * 
	 * @param username
	 *            l'username.
	 * @param password
	 *            la password.
	 * @param nome
	 *            il nome.
	 * @param cognome
	 *            il cognome.
	 * @param dataNascita
	 *            la data di nascita.
	 * @param cf
	 *            il codice fiscale.
	 * @param tesseraSanitaria
	 *            la tessera sanitaria.
	 * @param città
	 *            la città.
	 * @param via
	 *            la via.
	 * @param civico
	 *            il civico.
	 * @return true sse l'inserimento viene eseguito correttamente, altrimenti
	 *         false.
	 */
	public boolean registraCliente(String username, String password, String email,
			String nome, String cognome,String dataNascita, String CF,
			String città, String indirizzo, String cap, String numTelefono);
}
