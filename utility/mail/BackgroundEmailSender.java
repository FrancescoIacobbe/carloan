package utility.mail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia email in background, invisibile all'utente.
 * 
 * @author Gaetano
 * 
 */
public class BackgroundEmailSender extends Thread {

	/**
	 * Oggetto {@link EmailSender} si occupa dell'invio delle email.
	 */
	private EmailSender sender;

	/**
	 * Crea un oggetto {@link BackgroundEmailSender}.
	 * 
	 * @param o
	 *            - Stringa contenente l'oggetto del messaggio.
	 * @param mess
	 *            - Strina contenente il messaggio della email.
	 * @param dest
	 *            - {@link Array} di {@link HashMap} contentente email e nome
	 *            dei destinatari.
	 */
	public BackgroundEmailSender(String o, String mess,
			ArrayList<HashMap<String, String>> dest) {
		sender = new EmailSender(o, mess, dest);
	}

	 @Override
	 public void run() {
		 sender.sendAll();
	 }
}
