package entity;

/**
 * Fornisce i servizi inerenti la registrazione di un nuovo partecipante.
 * 
 * @author Gianluca
 * 
 */
public interface RegistrazioneInt {

	/**
	 * Verifica che l'username inserito sia disponibile.
	 * 
	 * @param username
	 *            l'username inserito.
	 * @return true sse l'username è disponibile, altrimenti false.
	 */
	public boolean isAvailableUsername(String username);

	/**
	 * Verifica che l'email inserita non sia già presente nel sistema.
	 * 
	 * @param email
	 *            l'email inserita.
	 * @return true sse l'email non è già presente nel sistema, altrimenti
	 *         false.
	 */
	public boolean isAvailableEmail(String email);

	/**
	 * Restituisce l'username del partecipante, se contenuto in memoria.
	 * 
	 * @return l'username del partecipante.
	 */
	public String getUsername();

	/**
	 * Restituisce la password del partecipante, se contenuta in memoria.
	 * 
	 * @return la password del partecipante.
	 */
	public String getPassword();

	/**
	 * Mantiene in memoria l'username.
	 * 
	 * @param username
	 *            l'username.
	 */
	public void setUsername(String username);

	/**
	 * Mantiene in memoria la password.
	 * 
	 * @param password
	 *            la password.
	 */
	public void setPassword(String password);

	/**
	 * Mantiene in memoria l'email.
	 * 
	 * @param email
	 */
	public void setEmail(String email);

	/**
	 * Restituisce l'email del partecipante, se mantenuta in memoria.
	 * 
	 * @return l'email del partecipante.
	 */
	public String getEmail();
}
