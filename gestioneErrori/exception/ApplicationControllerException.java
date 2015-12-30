package gestioneErrori.exception;

import presentazione.ApplicationController;


/**
 * Classe di errore per la classe {@link ApplicationController}
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class ApplicationControllerException extends Exception{
	

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public ApplicationControllerException (String s) {
		super(s);
	}

}
