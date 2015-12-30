package business.messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invia un messaggio al manager di competizione di avvenuta iscrizione ad una
 * sua competizione da parte di un partecipante.
 * 
 * @author Gaetano
 * 
 */
public class MessaggioNuovoContratto extends Messaggio {

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
	 * @param partecipante
	 *            - nome del partecipante che si è iscritto.
	 */
	public MessaggioNuovoContratto(int sendType,
			ArrayList<HashMap<String, String>> destinatari,
			String contratto, String cliente) {
		this.type = sendType;
		this.object = "Avviso nuovo contratto " + contratto;
		this.dest = destinatari;
		this.message = "Il cliente " + cliente + " ha "
				+ "appena stipulato il contratto  " + contratto;
	}
}
