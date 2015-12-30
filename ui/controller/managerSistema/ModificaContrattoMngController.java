package ui.controller.managerSistema;

import java.net.URL;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import ui.controller.dipendente.ModificaContrattoController;

public class ModificaContrattoMngController extends ModificaContrattoController{

	private FrontControllerInt fc;
	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("riepilogoContrattoMng");
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
