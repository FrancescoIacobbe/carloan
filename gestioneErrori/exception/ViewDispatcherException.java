package gestioneErrori.exception;

import presentazione.ViewDispatcher;

/**
 * Classe di errore per la classe {@link ViewDispatcher}.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class ViewDispatcherException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public ViewDispatcherException (String s) {
		super(s);
	}
}
