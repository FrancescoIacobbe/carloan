package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia un messaggio di benvenuto.
 * 
 * @author Gaetano
 * 
 */
public class MessaggioBenvenuto extends Messaggio {

	/**
	 * Costruttore principale.
	 * 
	 * @param sendType
	 *            - tipo di invio da effettuare.
	 * @param destinatari
	 *            - destinatari della email.
	 * @param username
	 *            - username del destinatario.
	 * @param password
	 *            - password del destinatario.
	 */
	public MessaggioBenvenuto(int sendType,
			ArrayList<HashMap<String, String>> destinatari, String username, String password) {
		this.type = sendType;
		this.dest = destinatari;
		this.object = "Benvenuto in CarLoan";
		this.message = "La ringraziamo per essersi iscritto a Carloan!\n"
				+ "Dati di accesso:\n"
				+ "username: " + username + "\n"
				+ "password: " + password;
	}

}
