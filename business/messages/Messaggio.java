package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

import utility.mail.GestioneEmail;
import utility.mail.GestioneEmailInt;

/**
 * Classe per l'invio di email.
 * 
 * @author Gaetano
 * 
 */
public class Messaggio {

	/**
	 * Tipo di invio visibile.
	 */
	public final static int VISIBLE = 0;

	/**
	 * Tipo di invio nascosto.
	 */
	public final static int BACKGROUND = 1;

	/**
	 * Tipo di invio da effettuare.
	 */
	protected int type;

	/**
	 * Oggetto del messaggio.
	 */
	protected String object;

	/**
	 * Messaggio da inviare.
	 */
	protected String message;
 
	/**
	 * Elenco di dizionari contenenti email e nome di ogni destinatario del
	 * messaggio.
	 */
	protected ArrayList<HashMap<String, String>> dest;

	private GestioneEmailInt ge = new GestioneEmail();

	/**
	 * Invia le email.
	 */
	public void send() {
		if (type == 0) {
			ge.visibleSend(object, message, dest);
		} else {
			ge.backgroundSend(object, message, dest);
		}
	}

}
