package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia una mail ad una determinato partecipante contenente le nuove
 * credenziali di accesso.
 * 
 * @author Gaetano
 * 
 */
public class MessaggioDatiAccount extends Messaggio {

	/**
	 * Costruttore principale.
	 * 
	 * @param sendType
	 *            - tipo di invio da effettuare.
	 * @param destinatari
	 *            - destinatari della email.
	 * @param username
	 *            - username del destinatario.
	 * @param email
	 *            - email del destinatario.
	 * @param password
	 *            - password del destinatario.
	 */
	public MessaggioDatiAccount(int sendType,
			ArrayList<HashMap<String, String>> destinatari, String username,
			String email, String password) {
		this.type = sendType;
		this.dest = destinatari;
		this.object = "Modifica Account Carloan";
		this.message = "Modifica dati account avvenuta con successo!\n"
				+ "I nuovi dati di accesso sono:\n" 
				+ "username: " + username + "\n" 
				+ "email: " + email + "\n" 
				+ "password: " + password;
	}
}
