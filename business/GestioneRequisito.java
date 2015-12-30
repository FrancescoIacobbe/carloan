package business;

import java.util.ArrayList;
import java.util.HashMap;

import presentazione.GestioneSessione;
import entity.LRequisitoInt;
import entity.Requisito;
import entity.SRequisitoInt;

/**
 * Application Service per i servizi inerenti la gestione degli optional.
 * 
 * @author Gaetano
 * 
 */
public class GestioneRequisito {

	private LRequisitoInt lRequisito;
	private SRequisitoInt sRequisito;

	
	/**
	 * Inserisce un nuovo optiona nel sistema.
	 * 
	 * @param p
	 *            - contiene: 1) nome del nuovo optional; 2) prezzo del nuovo
	 *            optional; 3) descrizione del nuovo optional.
	 * @return true sse l'inserimento va a buon fine.
	 */
	public boolean registraRequisito(ArrayList<String> p) {
		sRequisito = new Requisito();
		int i = 0;
		String id = p.get(i++);
		String modelli = p.get(i++);
		String descrizione = p.get(i++);
		return sRequisito.registraRequisito(id, modelli, descrizione);
	}

	/**
	 * Fornisce una lista con gli optional presenti nel sistema.
	 * 
	 * @return un {@link ArrayList} di {@link HashMap} che rappresentano gli
	 *         optional.
	 */
	public ArrayList<HashMap<String, String>> getRequisitoSistema() {
		lRequisito = new Requisito();
		return lRequisito.getRequisitoSistema();
	}

	/**
	 * Fornisce i dati relativi ad un determinato optional presente nel sistema.
	 * 
	 * @return un dizionario contenente i dati relativi all'optional.
	 */
	public HashMap<String, String> getDatiRequisito() {
		lRequisito = new Requisito();
		String idRequisito = GestioneSessione.getIdRequisitoCorrente();
		return lRequisito.getDatiRequisito(idRequisito);
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
	public boolean setDatiRequisito(ArrayList<String> p) {
		sRequisito = new Requisito();
		String idRequisito = GestioneSessione.getIdRequisitoCorrente();
		int i = 0;
		String id = p.get(i++);
		String modelli = p.get(i++);
		String descrizione = p.get(i++);
		return sRequisito.setDatiRequisito(id, modelli, descrizione);
	}

	/**
	 * Fornisce un elenco degli optional presenti nel sistema che rispettano il parametro di ricerca.
	 * @param p contiene il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni riguardo gli optional.
	 */
	public ArrayList<HashMap<String,String>> ricercaRequisito(ArrayList<String> p) {
		lRequisito = new Requisito();
		String key = p.get(0);
		return lRequisito.ricercaRequisito(key);
	}


}
