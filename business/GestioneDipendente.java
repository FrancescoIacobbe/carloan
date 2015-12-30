package business;

import java.util.ArrayList;
import java.util.HashMap;

import presentazione.GestioneSessione;
import utility.Security;
import entity.Cliente;
import entity.LDipendenteInt;
import entity.Dipendente;
import entity.SDipendenteInt;

/**
 * Application Service per i servizi relativi i manager delle competizioni.
 * 
 * @author Gianluca
 * 
 */
public class GestioneDipendente {

	private LDipendenteInt lDipendente;
	private SDipendenteInt sDipendente;
	private GestioneEmail ge;

	/**
	 * Fornisce id e username del manager di competizione specificato.
	 * 
	 * @return un {@link HashMap} contenente i dati.
	 */
	public HashMap<String, String> getDati() {
		String username = GestioneSessione.getUsernameUtenteCorrente();
		lDipendente = new Dipendente();
		HashMap<String, String> result = lDipendente.getDatiDipendente(username);
		GestioneSessione.setIdDipendenteCorrente(result.get("id"));
		return result;
	}

	

	/**
	 * Fornisce una lista con le competizioni concluse gestite dal manager di 
	 * competizione corrente.
	 * 
	 * @return una lista di dizionari che contengono le informazioni delle
	 *         singole competizioni o null se non ci sono competizioni non
	 *         svolte gestite dal manager specificato o null se non esiste il
	 *         manager specificato.
	 */
	public ArrayList<HashMap<String, String>> getContratti() {
		lDipendente = new Dipendente();
		String id = GestioneSessione.getIdDipendenteCorrente();
		return lDipendente.getContrattiDipendente(id);
	}

	/**
	 * Fornisce i dati account di un manager di una competizione.
	 * 
	 * @return un dizionario contenente i dati account del manager o null se non
	 *         esiste un manager con l'username specificato.
	 */
	public HashMap<String, String> getAccount() {
		lDipendente = new Dipendente();
		String username = GestioneSessione.getUsernameUtenteCorrente();
		HashMap<String, String> result = lDipendente.getAccountDipendente(username);
		return result;
	}

	/**
	 * Imposta i dati di un account di un manager di una competizione.
	 * 
	 * @param p
	 *            contiene: 1) username. 2) email. 3) password.
	 * @return true se l'aggiornamento va a buon fine, altrimenti false.
	 */
	public boolean setAccount(ArrayList<String> p) {
		int i = 0;
		String username = GestioneSessione.getUsernameUtenteCorrente();
		String email = p.get(i++);
		String password = p.get(i++);
		if (password.length() <= 15) {
			password = Security.cipher(password);
		}
		sDipendente = new Dipendente();
		return sDipendente.setAccountDipendente(username, email, password);
	}

	/**
	 * Fornisce un elenco con le competizioni non concluse gestite da un manager
	 * di una competizione, in base al parametro di ricerca specificato.
	 * 
	 * @param p contiene il nome della competizione.
	 * 
	 * @return una lista di dizionari contenenti le informazioni di ogni singola
	 *         competizione che rispetta i parametri di ricerca, o null se
	 *         nessuna competizione rispetta i parametri o null se non ci sono
	 *         competizioni non concluse associate al manager specificato o null
	 *         se il manager specificato non esiste.
	 */
	public ArrayList<HashMap<String, String>> ricercaContratto(
			ArrayList<String> p) {
		String idDipendente = GestioneSessione.getIdDipendenteCorrente();
		String idContratto = p.get(0);
		lDipendente = new Dipendente();
		return lDipendente.ricercaContratti(idDipendente, idContratto);
	}

	

	/**
	 * Fornisce un elenco con i dati relativi i manager di competizione presenti
	 * nel sistema.
	 * 
	 * @return un {@link ArrayList} di {@link HashMap} contenenti i dati dei
	 *         manager.
	 */
	public ArrayList<HashMap<String, String>> getDipendenteSistema() {
		lDipendente = new Dipendente();
		return lDipendente.getDipendenteSistema();
	}

	/**
	 * Fornisce i dati relativi a un determinato manager di competizione.
	 * 
	 * @return un dizionario contenente i dati del manager di competizione.
	 */
	public HashMap<String, String> getDipendente() {
		lDipendente = new Dipendente();
		String idDipendente = GestioneSessione.getIdDipendenteCorrente();
		return lDipendente.getDipendente(idDipendente);
	}

	/**
	 * Aggiorna i dati relativi a un determinato manager di Competizione.
	 * 
	 * @param p
	 *            - contiene: 1) nome del
	 *            manager di competizione; 2) cognome del manager di
	 *            competizione; 3) email dela manager di competizione; 4)
	 *            password del manager di competizione.
	 * @return true sse l'aggiornamento va a buon fine.
	 */
	public boolean setDipendente(ArrayList<String> p) {
		sDipendente = new Dipendente();
		String idDipendente = GestioneSessione.getIdDipendenteCorrente();
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
		return sDipendente.setDipendente(username, password, email, nome, cognome,
				dataNascita, cf, città, indirizzo, cap, numTelefono);
	}
	
	/**
	 * Fornisce un elenco dei manager di competizione presenti nel sistema che rispettano il parametro di ricerca.
	 * @param p contiene il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni riguardanti i manager.
	 */
	public ArrayList<HashMap<String,String>> ricerca(ArrayList<String> p) {
		lDipendente = new Dipendente();
		String key = p.get(0);
		return lDipendente.ricercaDipendente(key);
	}
	
	/**
	 * Inserisce un nuovo manager di una competizione nel sistema.
	 * @param p contiene:
	 * 1) username del manager.
	 * 2) nome del manager.
	 * 3) cognome del manager.
	 * 4) email del manager.
	 * 5) password del manager.
	 * @return true sse l'inserimento va a buon fine, altrimenti false.
	 */
	public boolean registraDipendente(ArrayList<String> p) {
		sDipendente = new Dipendente();
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
		boolean eseguito = sDipendente.registraDipendente(username, password, email, nome, cognome,
				dataNascita, cf, città, indirizzo, cap, numTelefono);
		if(eseguito) {
			ge = new GestioneEmail();
			ge.registrazione(email, nome, username, pass);
		}
		return eseguito;
	}
}