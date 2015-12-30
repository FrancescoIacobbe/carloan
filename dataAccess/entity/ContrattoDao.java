package dataAccess.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per le operazioni di memorizzazione e recupero dati inerenti le
 * competizioni.
 * 
 * @author Gianluca
 * 
 */
public interface ContrattoDao {

	/**
	 * Recupera i dati inerenti una competizione.
	 * 
	 * @param idCompetizione
	 *          - l'id della competizione di cui recupare i dati.
	 *            
	 * @param limiteTemporale
	 * 			- limite temporale entro il quale è possibile apportare modifiche 
	 * 			alla competizione.
	 * 
	 * @return un dizionario contenente i dati della competizione, altrimenti
	 *         null se non esiste nessuna competizione con l'idCompetizione
	 *         specificato.
	 */
	public HashMap<String, String> getDatiContratto(String idContratto);




	public boolean compilaContratto(String idDipendente, String id,
			String dataInizio, String dataLimite, String dataRestituzione,
			String luogoRestituzione, String tipoNoleggio, String tipoKm,
			String tipoFascia, double costoKm, int kmEffetivi, double acconto);




	public boolean setMacchinaAllocata(String idContratto, String macchina);




	public boolean setDatiContratto(String id, String dataInizio,
			String dataLimite, String dataRestituzione,
			String luogoRestituzione, String tipoNoleggio, String tipoKm,
			String tipoFascia, double costoKm, int kmEffetivi, double acconto);




	public boolean setTariffaBaseAllocato(String idContratto, double tariffaBase);




	public boolean setPrezzoExtraAllocato(String idContratto, double prezzoExtra);




	public boolean setCostoTotAllocato(String idContratto, double costoTot);




	public ArrayList<HashMap<String, String>> getContrattoSistema();




	public ArrayList<HashMap<String, String>> getMacchinaAllocata(String idContratto);




	public double getTariffaBaseAllocata(String idContratto);




	public double getPrezzoExtraAllocata(String idContratto);




	public double getCostoTotAllocato(String idContratto);
}
