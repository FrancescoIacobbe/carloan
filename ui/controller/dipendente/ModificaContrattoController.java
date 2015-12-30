package ui.controller.dipendente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.GestioneDate;

public class ModificaContrattoController  extends CompilaContrattoController {
	
	private FrontControllerInt fc;
	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("riepilogoContrattoDip");
	}
	
	/**
	 * Azione legata al pulsante confermaButton.
	 */
	private void conferma() {
		boolean dataInizio = controlloDataInizio();
		boolean dataLimite = controlloDataLimite();
		boolean dataRestituzione = controlloDataRestituzione();
		boolean luogoRestituzione = controlloLuogo();
		boolean macchina = controlloMacchina();
		boolean tariffaBase = controlloTariffaBase();
		boolean costoKm = controlloCostoKm();
		boolean KmEffettivi = controlloKm();
		boolean prezzoExtra = controlloPrezzoExtra();
		boolean acconto = controlloAcconto();
		boolean costoTot = controlloCostoTot();
		
		if (dataInizio && dataLimite && dataRestituzione && luogoRestituzione &&
				 macchina && tariffaBase && costoKm && KmEffettivi && 
				 prezzoExtra && acconto && costoTot) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(gd.parseData(this.dataInizioDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(gd.parseData(this.dataLimiteDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(gd.parseData(this.dataRestituzioneDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(luogoRestituzioneTextField.getText());
			p.add(macchinaTextField.getText());
			p.add(tariffaBaseTextField.getText());
			p.add(costoKmTextField.getText());
			p.add(kmEffettiviTextField.getText());
			p.add(prezzoExtraTextField.getText());
			p.add(accontoTextField.getText());
			p.add(costoTotTextField.getText());
			boolean save ;
				save = (boolean) fc.processRequest("setDatiContratto", p);
			if (save) {
				ArrayList<String> par = new ArrayList<>();
				par.add("0");
				par.add("Operazione Riuscita");
				par.add("Dati anagrafici aggiornati correttamente!");
				fc.processRequest("visualizzaMessaggio", par);
				fc.processRequest("aggiornaPane");
			}
		}
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// inizializza il front controller
		fc = new FrontController();
		// imposta gli eventi associati ai pulsanti
		setModificaButton();
		// imposto le text field di tipo autocompletmento
		setIndietroButton();
		setAnnullaButton();

	}

}
