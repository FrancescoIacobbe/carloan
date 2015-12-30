package gestioneErrori.exception;

import utility.pdf.GestionePdf;

/**
 * Classe di errore per la classe {@link GestionePdf}.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class PdfException extends Exception{

	/**
	 * Costruttore Principale.
	 * @param s il messaggio di errore.
	 */
	public PdfException (String s) {
		super(s);
	}
}
