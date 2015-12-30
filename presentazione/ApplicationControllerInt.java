package presentazione;

import java.util.ArrayList;

import utility.ReaderXMLStub;

/**
 * Riceve le richieste dal {@link FrontController} e delega l'esecuzione dei servizi
 * agli Application Service.
 * Attraverso la classe {@link ViewDispatcher}, dispaccia l'interfaccia corretta in base
 * alla richiesta.
 * @author Gianluca
 *
 */
public interface ApplicationControllerInt {

	/**
	 * Esegue una specifica richiesta.
	 * @param key - chiave della richiesta.
	 * @param p - paramentri necessari per processare la richiesta.
	 * @return {@link Object} contenente la risposta.
	 */
	public Object handleRequest(String key, ArrayList<String> p);

	public void setReader(ReaderXMLStub readerXMLStub);
}
