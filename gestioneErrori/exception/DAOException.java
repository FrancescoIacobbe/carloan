package gestioneErrori.exception;

/**
 * Classe di errore per le classi DAO.
 * @author Gianluca
 *
 */
@SuppressWarnings("serial")
public class DAOException extends Exception{

	/**
	 * Costruttore principale.
	 * @param s il messaggio di errore.
	 */
	public DAOException(String s) {
		super(s);
	}
}
