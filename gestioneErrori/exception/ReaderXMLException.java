package gestioneErrori.exception;

import utility.ReadXML;

/**
 * Classe di errore per la classe {@link ReaderXML}.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class ReaderXMLException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public ReaderXMLException(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	}
}
