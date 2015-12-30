package gestioneErrori.exception;

import utility.GestioneDate;

/**
 * Classe di errore per la classe {@link GestioneDate}.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class GestioneDateException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public GestioneDateException (String s) {
		super(s);
	}
}
