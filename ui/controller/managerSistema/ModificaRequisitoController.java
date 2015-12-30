package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import ui.controller.managerSistema.InserisciRequsitoController;

public class ModificaRequisitoController  extends InserisciRequsitoController{
	
	private FrontControllerInt fc;
	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("riepilogoRequsito");
	}
	
	/**
	 * Azione legata al pulsante confermaButton.
	 */
	private void conferma() {
		boolean id = controlloId();
		boolean descrizione = controlloDescrizione();
		boolean modelli = controlloModelli();
		
		if (id && descrizione && modelli ) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(IDTextField.getText());
			p.add(descrizioneTextArea.getText());
			p.add(modelliTextArea.getText());
		
			boolean save;
			save = (boolean) fc.processRequest("setDatiRequisito", p);
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
		setConfermaButton();
		setIndietroButton();
	

	}

}
