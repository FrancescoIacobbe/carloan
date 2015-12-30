package utility.mail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per l'invio di email.
 * 
 * @author Gaetano
 * 
 */
public interface GestioneEmailInt {

	/**
	 * Invia email visualizzando una nuova finestra con indicazione sullo stato
	 * del'invio.
	 * 
	 * @param o
	 *            - Stringa contenente l'oggetto del messaggio.
	 * @param mess
	 *            - Stringa contenente il contenuto della email.
	 * @param dest
	 *            - {@link ArrayList} di {@link HashMap} contenente nome e email
	 *            dei destinatari del messaggio.
	 */
	public void visibleSend(String o, String mess,
			ArrayList<HashMap<String, String>> dest);

	/**
	 * Invia email in background, invisibile all'utente.
	 * 
	 * @param o
	 *            - Stringa contenente l'oggetto del messaggio.
	 * @param mess
	 *            - Stringa contenente il contenuto della email.
	 * @param dest
	 *            - {@link ArrayList} di {@link HashMap} contenente nome e email
	 *            dei destinatari del messaggio.
	 */
	public void backgroundSend(String o, String mess,
			ArrayList<HashMap<String, String>> dest);
}
