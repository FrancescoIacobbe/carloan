package business;

import java.util.ArrayList;
import java.util.HashMap;

import presentazione.GestioneSessione;
import utility.Security;
import entity.LClienteInt;
import entity.Cliente;
import entity.SClienteInt;

/**
 * Application Service per i servizi legati ai partecipanti.
 * 
 * @author Gianluca
 * 
 */
public class GestioneCliente {

	private LClienteInt lCliente;
	private SClienteInt sCliente;
	private GestioneEmail ge;
	private int limiteTemporale = GestioneSessione.getLimiteTemporaleCorrente(); 

	/**
	 * Fornisce l'id e l'username del cliente.
	 * 
	 * @return un dizionario contenente id e username del cliente.
	 */
	public HashMap<String, String> getDati() {
		lCliente = new Cliente();
		String username = GestioneSessione.getUsernameUtenteCorrente();
		HashMap<String, String> result = lCliente.getDatiCliente(username);
		GestioneSessione.setIdClienteCorrente(result.get("id"));
		return result;
	}

	/**
	 * Fornisce le competizioni a cui il cliente corrente è iscritto e la cui
	 * iscrizione non è modificabile.
	 * 
	 * @return una lista di dizionari contenenti le informazioni sulle
	 *         competizioni, altrimenti null se l'id del cliente non è
	 *         presente nel sistema o non ci sono competizioni a cui il
	 *         cliente è iscritto la cui iscrizione non è più modificabile.
	 */
	public ArrayList<HashMap<String, String>> getContrattoCliente() {
		lCliente = new Cliente();
		String id = GestioneSessione.getIdClienteCorrente();
		ArrayList<HashMap<String, String>> result = lCliente
				.getContrattoCliente(id);
		return result;
	}

	/**
	 * Fornisce i dati anagrafici del cliente corrente, in base all'id salvato in GestioneSessione.
	 * 
	 * @return un dizionario contenente i dati anagrafici del cliente o
	 *         null se il cliente non esiste.
	 */
	public HashMap<String, String> getAnagrafica() {
		lCliente = new Cliente();
		String id = GestioneSessione.getIdClienteCorrente();
		HashMap<String, String> result = lCliente.getAnagraficaCliente(id);
		return result;
	}

	/**
	 * Fornisce i dati account del cliente corrente.
	 * @return un dizionario contenente i dati account del cliente o null
	 *         se il cliente non esiste.
	 */
	public HashMap<String, String> getAccount() {
		lCliente = new Cliente();
		String username = GestioneSessione.getUsernameUtenteCorrente();
		HashMap<String, String> result = lCliente.getAccountCliente(username);
		return result;
	}

	/**
	 * Imposta i dati anagrafici di una cliente.
	 * 
	 * @param p
	 *            contiene: 1) nome. 2) cognome. 3) sesso. 4)
	 *            dataNascita. 5) CF. 6) tessera sanitaria. 7) città. 8) via.
	 *            9)civico.
	 * @return true se l'aggiornamento dell'anagrafica è eseguito correttamente,
	 *         altrimenti false.
	 */
	public boolean setAnagrafica(ArrayList<String> p) {
		String id = GestioneSessione.getIdClienteCorrente();
		int i = 0;
		String nome = p.get(i++);
		String cognome = p.get(i++);
		String dataNascita = p.get(i++);
		String CF = p.get(i++);
		String città = p.get(i++);
		String indirizzo = p.get(i++);
		String numTelefono = p.get(i++); 
		String CAP= p.get(i++);
		sCliente = new Cliente();
		return sCliente.setAnagraficaCliente( nome, cognome,
				dataNascita, CF, città, indirizzo, numTelefono, CAP );
	}

	/**
	 * Imposta i dati di un account del cliente corrente.
	 * 
	 * @param p
	 *            contiene: 1) email. 2) password.
	 * @return true se l'aggiornamento va a buon fine, altrimenti false.
	 */
	public boolean setAccount(ArrayList<String> p) {
		String username = GestioneSessione.getUsernameUtenteCorrente();
		int i = 0;
		String email = p.get(i++);
		String pass = p.get(i++);
		String password = Security.cipher(pass);
		sCliente = new Cliente();
		boolean eseguito =  sCliente.setAccountCliente(username, email, password);
		if(eseguito){
			ge = new GestioneEmail();
			ge.modificaAccount(email, username, pass);
		}
		return eseguito;
	}

	
	/**
	 * Ricerca una o più competizioni tra quelle a cui il cliente corrente è
	 * iscritto la cui iscrizione è modificabile, in base al parametro di
	 * ricerca.
	 * 
	 * @param p
	 *            contiene: 1) il nome della
	 *            competizione da cercare.
	 * @return una lista di dizionari contenenti le informazioni sulle
	 *         competizioni, altrimenti null se l'id del cliente non è
	 *         presente nel sistema o non ci sono competizioni a cui il
	 *         cliente è iscritto e la cui iscrizione è modificabile o non
	 *         ci sono competizioni con il parametro di ricerca specificato.
	 */
	public ArrayList<HashMap<String, String>> ricercaContratto(
			ArrayList<String> p) {
		lCliente = new Cliente();
		String id = GestioneSessione.getIdClienteCorrente();
		String idContratto = p.get(0);
		ArrayList<HashMap<String, String>> result = lCliente
				.ricercaContratti(id, idContratto);
		return result;
	}

	

	/**
	 * Inserisce un nuovo cliente nel sistema.
	 * 
	 * @param p
	 *            contiene: 1) username. 2) password. 3) email. 4) nome. 5)
	 *            cognome. 6) data di nascita. 7) codice fiscale. 8) tessera
	 *            sanitaria. 9)città. 10) via. 11) civico. 12) sesso.
	 * @return true sse l'inserimento è stato eseguito correttamente, altrimenti
	 *         false.
	 */
	public boolean registraCliente(ArrayList<String> p) {
		sCliente = new Cliente();
		int i = 0;
		String username = p.get(i++);
		String pass = p.get(i++);
		String password = Security.cipher(pass);
		String email = p.get(i++);
		String nome = p.get(i++);
		String cognome = p.get(i++);
		String dataNascita = p.get(i++);
		String cf = p.get(i++);
		String città = p.get(i++);
		String indirizzo= p.get(i++);
		String cap = p.get(i++);
		String numTelefono= p.get(i++);
		boolean eseguito = sCliente.registraCliente(username, password, email, nome, cognome,
				dataNascita, cf, città, indirizzo, cap, numTelefono);
		if(eseguito) {
			ge = new GestioneEmail();
			ge.registrazione(email, nome, username, pass);
		}
		return eseguito;
	}
	
	/**
	 * Fornisce una lista di dizionari contenenti le informazioni su tutti i partecipanti iscritti al sistema.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui partecipanti iscritti al sistema.
	 */
	public ArrayList<HashMap<String,String>> getClientiSistema() {
		lCliente = new Cliente();
		return lCliente.getClientiSistema();
	}
	
	/**
	 * Fornisce una lista di dizionari contenenti le informazioni su tutti i partecipanti iscritti al sistema
	 * che rispettano il paramentro di ricerca.
	 * @param p contiene il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sui partecipanti iscritti al sistema.
	 */
	public ArrayList<HashMap<String,String>> ricercaCliente(ArrayList<String> p) {
		lCliente = new Cliente();
		String key = p.get(0);
		return lCliente.ricercaClienti(key);
	}
}
