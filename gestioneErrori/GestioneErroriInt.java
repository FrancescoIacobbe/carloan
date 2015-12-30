package gestioneErrori;

import java.util.logging.Level;

/**
 * Interfaccia per la gestione degli errori di sistema. Raccoglie gli errori
 * generati e mostra una finestra di dialogo in base al tipo di errore.
 * 
 * @author Gianluca
 * 
 */
public interface GestioneErroriInt {

	/**
	 * Livello di errore grave.
	 * Comporta la terminazione del programma.
	 */
	public static Level SEVERE = Level.SEVERE;
	
	/**
	 * Livello di errore non grave.
	 * Il programma continua la sua esecuzione.
	 */
	public static Level WARNING = Level.WARNING;
	
	/**
	 * Livello di errore più basso.
	 * Il programma continua la sua esecuzione e non viene mostrato alcun messaggio di errore.
	 */
	public static Level NO_MESSAGE = Level.INFO;
	
	/**
	 * Invia un errore al logger.
	 * @param c - classe che ha sollevato l'eccezione.
	 * @param e - eccezzione sollevata.
	 * @param l - il livello dell'eccezione sollevata.
	 */
	@SuppressWarnings("rawtypes")
	public void submitError(Class c, Throwable e, Level l);
}
