package gestioneErrori.exception;

import config.GestioneProperties;

/**
 * Classe di errore per la classe {@link GestioneProperties}.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class GestionePropertiesException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public GestionePropertiesException(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	}
}
