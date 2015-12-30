package ui.controller.dipendente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.GestioneDate;

public class ModificaAnagraficaCliDController  extends modificaAnagraficaDipController{
	
	private FrontControllerInt fc;
	/**
	 * Controlla la correttezza dei dati inseriti e termina la registrazione.
	 */
	private void salvaModifiche() {
		boolean nome = controllaNome();
		boolean cognome = controllaCognome();
		boolean dataNascita = controllaData();
		boolean CF = controllaCodiceFiscale();
		boolean citta = controllaCitta();
		boolean indirizzo = controllaIndirizzo();
		boolean numeroTel= controlloNumTel();
		boolean CAP = controlloCAP();
		
		
		if (nome && cognome && dataNascita && CF  && citta
				&& indirizzo && numeroTel && CAP) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(this.nomeTextField.getText());
			p.add(this.cognomeTextField.getText());
			p.add(gs.parseData(this.dataNascitaDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(this.CFTextField.getText());
			p.add(this.cittaTextField.getText());
			p.add(this.indirizzoTextField.getText());
			p.add(this.numTelTextField.getText());
			p.add(this.CAPTextField.getText());
			boolean save;
			save = (boolean) fc.processRequest("setAnagraficaCliente", p);
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
	
	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
			fc.processRequest("riepilogoCliente");
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// inizializza il front controller
		fc = new FrontController();
		// imposta gli eventi associati ai pulsanti
		setModificaButton();
		// imposto le text field di tipo autocompletmento
		setAutoText();
		// imposto i dati relativi all'utente corrente
		setDati();
		setIndietroButton();

	}

}
