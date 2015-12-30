package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Fornisce i servizi di scrittura per le competizioni.
 * 
 * @author Gianluca
 * 
 */
public interface SContrattoInt {

	/**
	 * Inserisce una nuova competizione nel sistema.
	 * 
	 * @param idManager
	 *            - id del manager della competizione.
	 * @param nome
	 *            - nome della competizione.
	 * @param tipo
	 *            - tipo della competizione.
	 * @param data
	 *            - data della competizione.
	 * @param prezzo
	 *            - prezzo della competizione.
	 * @param durata
	 *            - durata della competizione.
	 * @param descrizione
	 *            - descrizione della competizione.
	 * @param minPartecipanti
	 *            - numero minimo di partecipanti da raggiungere.
	 * @param maxPartecipanti
	 *            - limite massimo di partecipanti iscrivibili alla
	 *            competizione.
	 * @return - true sse la competizione viene inserita correttamente.
	 */
	boolean compilaContratto(String idDipendente,String id, String dataInizio,String dataLimite,String dataRestituzione,String luogoRestituzione, String tipoNoleggio, 
			String tipoKm, String tipoFascia, double costoKm,int kmEffetivi,double acconto);

	

	/**
	 * Aggiorna i dati relativi ad una specifica competizione.
	 * 
	 * @param idCompetizione
	 *            - id della competizione
	 * @param nome
	 *            - nome della competizione
	 * @param tipo
	 *            - tipo della competizione
	 * @param data
	 *            - data della competizione
	 * @param prezzo
	 *            - prezzo della competizione
	 * @param durata
	 *            - durata della competizione
	 * @param descrizione
	 *            - descrizione della competizione
	 * @param minPartecipanti
	 *            - numero minimo di partecipanti
	 * @param maxPartecipanti
	 *            - numero massimo di partecipanti.
	 * @return true sse l'aggiornamento va a buon fine, altrimenti false.
	 */
	boolean setDatiContratto(String id, String dataInizio,String dataLimite,String dataRestituzione,String luogoRestituzione, String tipoNoleggio,
			String tipoKm, String tipoFascia ,double costoKm,int kmEffetivi, double acconto);


	boolean setMacchinaAllocata(String idContratto, String macchina);



	boolean setTariffaBaseAllocato(String idContratto, double tariffaBase);



	boolean setCostoTotAllocato(String idContratto, double costoTot);



	boolean setPrezzoExtraAllocato(String idContratto, double prezzoExtra);



}
