package gestioneErrori.exception;

/**
 * Classe di errore per il servizio di stampa.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class StampaException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public StampaException (String s) {
		super(s);
	}
}
