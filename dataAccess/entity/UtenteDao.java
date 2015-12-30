package dataAccess.entity;

/**
 * Interfaccia per la memorizzazione e il recupero dei dati inerenti la
 * procedura di accesso e registrazione al sistema.
 * 
 * @author Gianluca
 * 
 */
public interface UtenteDao {

	/**
	 * Esegue il login.
	 * 
	 * @param username l'username.
	 * @param password la password.
	 * 
	 * @return il tipo di utente autenticato o null se la combinazione
	 *         username-password non è presente nel sistema.
	 */
	public String login(String username, String password);

	/**
	 * Verifica che un username sia disponibile per una nuova registrazione.
	 * 
	 * @param username
	 *             l'username da verificare.
	 * @return true se l'username è disponibile, altrimenti false.
	 */
	public boolean isAvailableUsername(String username);

	/**
	 * Verifica che una email sia disponibile per una nuova registrazione.
	 * 
	 * @param email
	 *             l'email da verificare.
	 * @return true se l'email è disponibile, altrimenti false.
	 */
	public boolean isAvailableEmail(String email);
}
