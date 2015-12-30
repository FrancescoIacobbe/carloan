package business;

import java.util.ArrayList;
import java.util.HashMap;

import presentazione.GestioneSessione;
import entity.LMacchinaInt;
import entity.Macchina;
import entity.SMacchinaInt;

/**
 * Application Service per i servizi inerenti la gestione degli optional.
 * 
 * @author Gaetano
 * 
 */
public class GestioneMacchina {

	private LMacchinaInt lMacchina;
	private SMacchinaInt sMacchina;

	
	/**
	 * Inserisce un nuovo optiona nel sistema.
	 * 
	 * @param p
	 *            - contiene: 1) nome del nuovo optional; 2) prezzo del nuovo
	 *            optional; 3) descrizione del nuovo optional.
	 * @return true sse l'inserimento va a buon fine.
	 */
	public boolean nuovoMacchina(ArrayList<String> p) {
		sMacchina = new Macchina();
		int i = 0;
		String targa = p.get(i++);
		String modello = p.get(i++);
		String disponibilita = p.get(i++);
		String manuntenzione = p.get(i++);
		int ultimoKm = Integer.parseInt(p.get(i++));
		String fascia = p.get(i++);
		return sMacchina.registraMacchina(targa, modello, disponibilita, manuntenzione, ultimoKm, fascia);
	}

	/**
	 * Fornisce una lista con gli optional presenti nel sistema.
	 * 
	 * @return un {@link ArrayList} di {@link HashMap} che rappresentano gli
	 *         optional.
	 */
	public ArrayList<HashMap<String, String>> getMacchinaSistema() {
		lMacchina = new Macchina();
		return lMacchina.getMacchinaSistema();
	}

	/**
	 * Fornisce i dati relativi ad un determinato optional presente nel sistema.
	 * 
	 * @return un dizionario contenente i dati relativi all'optional.
	 */
	public HashMap<String, String> getDati() {
		lMacchina = new Macchina();
		String targa = GestioneSessione.getIdMacchinaCorrente();
		return lMacchina.getDatiMacchina(targa);
	}

	/**
	 * Aggiorna i dati relativi ad un determinato optional presente nel sistema.
	 * 
	 * @param p
	 *            - contiene: 
	 *            1) il nuovo nome dell'optional; 
	 *            2) il nuovo prezzo dell'optional; 
	 *            3) la nuova descrizione dell'optional.
	 * @return true se l'aggiornamento va a buon fine;
	 */
	public boolean setDati(ArrayList<String> p) {
		sMacchina = new Macchina();
		String idMacchina = GestioneSessione.getIdMacchinaCorrente();
		int i = 0;
		String targa = p.get(i++);
		String modello = p.get(i++);
		String disponibilita = p.get(i++);
		String manuntenzione = p.get(i++);
		int ultimoKm = Integer.parseInt(p.get(i++));
		String fascia = p.get(i++);
		return sMacchina.setDatiMacchina(targa, modello, disponibilita, manuntenzione, ultimoKm, fascia);
	}

	/**
	 * Fornisce un elenco degli optional presenti nel sistema che rispettano il parametro di ricerca.
	 * @param p contiene il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni riguardo gli optional.
	 */
	public ArrayList<HashMap<String,String>> ricerca(ArrayList<String> p) {
		lMacchina = new Macchina();
		String key = p.get(0);
		return lMacchina.ricercaMacchine(key);
	}
	
	public boolean elimina(ArrayList<String> p) {
		sMacchina = new Macchina();
		String targa = GestioneSessione.getIdMacchinaCorrente();
		boolean eseguito = sMacchina.eliminaMacchina(targa);
		
		return eseguito;
	}
}
