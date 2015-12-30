package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia un messaggio di benvenuto.
 * 
 * @author Gaetano
 * 
 */
public class MessaggioBenvenutoDipendente extends Messaggio {

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
	public MessaggioBenvenutoDipendente(int sendType,
			ArrayList<HashMap<String, String>> destinatari, String username, String password) {
		this.type = sendType;
		this.dest = destinatari;
		this.object = "Benvenuto in CarLoan";
		this.message = "Benvenuto nella squadra Carloan!\n"
				+ "Dati accesso come manager di comeptizione:\n"
				+ "username: " + username + "\n"
				+ "password: " + password;
	}

}
