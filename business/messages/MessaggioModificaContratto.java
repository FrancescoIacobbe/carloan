package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia una mail ai partecipanti iscritti ad una determinata competizione per 
 * notificare eventuali modifiche apportate ad essa.
 * @author Gaetano
 *
 */
public class MessaggioModificaContratto extends Messaggio {
	
	/**
	 * Costruttore principale.
	 * 
	 * @param sendType
	 *            - tipo di invio del messaggio.
	 * @param destinatari
	 *            - elenco di dizionari contenente i dati dei destinatari del
	 *            messaggio.
	 * @param competizione
	 *            - competizione a cui è stata effettuate l'iscrizione.
	 * @param motivazione
	 *            - motivazione dell'avvenuta modifica.
	 */
	public MessaggioModificaContratto(int sendType,
			ArrayList<HashMap<String, String>> destinatari,
			String contratto, String motivazione) {
		this.type = sendType;
		this.object = "Avviso modifica dati contratto " + contratto;
		this.dest = destinatari;
		this.message = "Il contratto  " + contratto + " ha subito delle "
				+ "variazioni.\n\nLe motivazioni delle variazioni sono:\n" + motivazione
				+ "\nAccedi al sistema per visualizzare le variazioni.";
	}
}
