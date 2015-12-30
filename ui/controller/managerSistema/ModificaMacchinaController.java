package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.GestioneDate;

public class ModificaMacchinaController  extends InserisciMacchinaController{
	
	private FrontControllerInt fc;
	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("riepilogoMacchina");
	}
	
	/**
	 * Azione legata al pulsante confermaButton.
	 */
	private void conferma() {
		boolean targaC = controlloTarga();
		boolean modelloC = controlloModello();
		boolean ultimoKmC = controlloUltimoKm();
		boolean requisitoC = controlloRequsito();
		
		if (targaC && modelloC  && ultimoKmC && requisitoC) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(targaTextField.getText());
			p.add(modelloTextField.getText());
			p.add(manutenzioneComboBox.getPromptText());
			p.add(ultimoKmTextField.getText());
			p.add(fasciaComboBox.getPromptText());
			p.add(requisitoTextArea.getText());

			String disponibilita;
			if (yesRadioButton.isSelected()) {
				disponibilita = yesRadioButton.getText();
			} else {
				disponibilita = noRadioButton.getText();
			}
			p.add(disponibilita);
			boolean save;
			save = (boolean) fc.processRequest("setDatiMacchina", p);
			
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
		// imposto le text field di tipo autocompletmento
		setIndietroButton();
	

	}
}

