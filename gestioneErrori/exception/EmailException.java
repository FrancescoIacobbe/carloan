package gestioneErrori.exception;

/**
 * Classe di errore per le classi inerenti il servizio delle email.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class EmailException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public EmailException (String s) {
		super(s);
	}
}
