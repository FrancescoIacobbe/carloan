package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import presentazione.GestioneSessione;
import entity.Contratto;
import entity.Dipendente;
import entity.LContrattoInt;
import entity.SContrattoInt;

/**
 * Application Service per i servizi relativi le competizioni.
 * 
 * @author Gianluca
 * 
 */
public class GestioneContratto {

	private LContrattoInt lContratto;
	private SContrattoInt sContratto;
	private GestioneEmail ge;
	private int limiteTemporale = GestioneSessione.getLimiteTemporaleCorrente();

	/**
	 * Fornisce i dati relativi alla competizione corrente.
	 * 
	 * @return un dizionario contenente i dati relativi la competizione o null
	 *         se non esiste nel sistema una competizione con l'id specificato.
	 */
	public HashMap<String, String> getDati() {
		lContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		HashMap<String, String> result = lContratto.getDatiContratto(idContratto);
		return result;
	}
	/**
	 * Inserisce una nuova competizione nel sistema.
	 * 
	 * @param p
	 *            contiene: 1) nome della
	 *            competizione. 2) tipo della competizione. 3) data della
	 *            competizione. 4) prezzo della competizione. 5) durata della
	 *            competizione. 6) descrizione della competizione.7) numero
	 *            minimo di partecipanti. 8) numero massimo di partecipanti.
	 * @return true se l'inserimento è andato a buon fine, altrimenti false. 
	 * 			Se l'id è maggiore di zero, l'inserimento è andato a buon fine.
	 */
	public boolean nuovoContratto(ArrayList<String> p) {
		sContratto = new Contratto();
		int i = 0;
		String idDipendente = GestioneSessione.getIdDipendenteCorrente();
		String id = p.get(i++);
		String dataInizio = p.get(i++);
		String dataLimite = p.get(i++);
		String dataRestituzione = p.get(i++);
		String luogoRestituzione = p.get(i++);
		String tipoNoleggio = p.get(i++);
		String tipoKm = p.get(i++);
		String tipoFascia = p.get(i++);
		double  costoKm= Double.parseDouble(p.get(i++));
		int kmEffetivi = Integer.parseInt(p.get(i++));
		double  acconto= Double.parseDouble(p.get(i++));
	 
		return sContratto.compilaContratto(idDipendente, id, 
				dataInizio, dataLimite, dataRestituzione, luogoRestituzione, tipoNoleggio, 
				 tipoKm, tipoFascia, costoKm, kmEffetivi, acconto);
	}

	/**
	 * Imposta l'elenco degli optional allocati per una determinata
	 * competizione.
	 * 
	 * @param p
	 *            contiene: 1) id della competizione. i = 2...n) id
	 *            dell'optional. j = i + 1) quantità allocata dell'optional. k =
	 *            j + 1) quantità venduta dell'optional
	 * @return true sse l'operazione è andata a buon fine.
	 */
	public boolean setMacchinaAllocata(ArrayList<String> p) {
		sContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		String macchina;
		ArrayList<HashMap<String, String>> emails = new ArrayList<HashMap<String, String>>();
		int i = 0;
			String targaMacchina = p.get(i++);
			macchina=targaMacchina;
		return sContratto.setMacchinaAllocata(idContratto, macchina);
	}

	/**
	 * Aggiorna i dati relativi ad una specifica competizione.
	 * 
	 * @param p
	 *            - contiene i dati aggiornati:
	 *            1) nome della competizione.
	 *            2) tipo della competizione. 
	 *            3) data della competizione. 
	 *            4) prezzo della competizione. 
	 *            5) durata della competizione. 
	 *            6) descrizione della competizione. 
	 *            7) numero minimo di partecipanti. 
	 *            8) numero massimo di partecipanti.
	 *            9) motivazione della modifica.
	 * 
	 * @return true sse l'aggiornamento va a buon fine.
	 */
	public boolean setDati(ArrayList<String> p) {
		GregorianCalendar gc = new GregorianCalendar();
		int oggi = gc.get(Calendar.DATE);
		sContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		String dataInizio = lContratto.getDatiContratto(idContratto).get("datainizio");
		String giorno= dataInizio.charAt(0)+ "" + dataInizio.charAt(1);
		int giornoInt= Integer.parseInt(giorno);
		limiteTemporale= limiteTemporale/24;
		int modifica= giornoInt -limiteTemporale;
		boolean ritorno = false;
		if(modifica <= oggi){
		int i=0;
		String id = p.get(i++);
		dataInizio = p.get(i++);
		String dataLimite = p.get(i++);
		String dataRestituzione = p.get(i++);
		String luogoRestituzione = p.get(i++);
		String tipoNoleggio = p.get(i++);
		String tipoKm = p.get(i++);
		String tipoFascia = p.get(i++); 
		double  costoKm= Double.parseDouble(p.get(i++));
		int kmEffetivi = Integer.parseInt(p.get(i++));
		double  acconto= Double.parseDouble(p.get(i++));
		ritorno= sContratto.setDatiContratto( id,dataInizio, dataLimite, dataRestituzione, luogoRestituzione, tipoNoleggio, 
			 tipoKm, tipoFascia, costoKm, kmEffetivi, acconto);}
		return ritorno; 
	}

	public boolean setTariffaBaseAllocato(ArrayList<String> p) {
		sContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		double tariffaBase = 0;
		String tipoFascia = lContratto.getDatiContratto(idContratto).get("tipoFascia");
		String tipoNoleggio = lContratto.getDatiContratto(idContratto).get("tipoNoleggio");
			if(tipoFascia=="A" && tipoNoleggio=="giornaliero"){
				tariffaBase=150.00;
			} else if( tipoFascia=="B" && tipoNoleggio=="giornaliero") {
				tariffaBase=100.00;
			} else if(tipoFascia=="C" && tipoNoleggio=="giornaliero") {
				tariffaBase=75.00;
			} else if(tipoFascia=="D" && tipoNoleggio=="giornaliero"){
				tariffaBase=50.00;
			} else if(tipoFascia=="A" && tipoNoleggio=="settimanale"){
				tariffaBase=400.00;
			} else if( tipoFascia=="B" && tipoNoleggio=="settimanale") {
				tariffaBase=300.00;
			} else if(tipoFascia=="C" && tipoNoleggio=="settimanale") {
				tariffaBase=200.00;
			} else if(tipoFascia=="D" && tipoNoleggio=="settimanale"){
				tariffaBase=150.00;
			}
		
		return sContratto.setTariffaBaseAllocato(idContratto, tariffaBase);
	}

	public boolean setPrezzoExtraAllocato(ArrayList<String> p) {
		sContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		double prezzoExtra;
		double costoKm = Double.parseDouble(lContratto
				.getDatiContratto(idContratto).get("costoKm"));
		int kmEffettivi = Integer.parseInt(lContratto
				.getDatiContratto(idContratto).get("kmEffettivi"));
			prezzoExtra=costoKm*kmEffettivi;
			
		return sContratto.setPrezzoExtraAllocato(idContratto, prezzoExtra);
	}

	public boolean setCostoTotAllocato(ArrayList<String> p) {
		sContratto = new Contratto();
		String idContratto = GestioneSessione.getIdContrattoCorrente();
		double costoTot;
		double prezzoExtra = Double.parseDouble(lContratto
				.getDatiContratto(idContratto).get("costoKm"));
		double tariffaBase = Double.parseDouble(lContratto
				.getDatiContratto(idContratto).get("tariffaBase"));
			costoTot=prezzoExtra+tariffaBase;
			
		return sContratto.setCostoTotAllocato(idContratto, costoTot);
	}

	public ArrayList<HashMap<String, String>> getContrattoSistema(){
		lContratto = new Contratto();
		return lContratto.getContrattoSistema();
	}
	
	
}
