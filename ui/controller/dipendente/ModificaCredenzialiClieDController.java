package ui.controller.dipendente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;

public class ModificaCredenzialiClieDController  extends ModificaCredenzialiDipController{
	private FrontControllerInt fc;
	
	/**
	 * Controlla la correttezza dei dati inseriti e termina la registrazione.
	 */
	private void salvaModifiche() {
		boolean username = controllaUsername();
		boolean password = controllaPassword();
		boolean email = controllaEmail();
		boolean confermaPass = controllaConfermaPass();
		
		
		if (username && password && email && confermaPass ) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(this.usernameTextField.getText());
			p.add(this.passwordField.getText());
			p.add(this.emailTextField.getText());
			p.add(this.confermaPasswordField.getText());
			boolean save;
			save = (boolean) fc.processRequest("setAccountCliente", p);
			if (save) {
				ArrayList<String> par = new ArrayList<>();
				par.add("0");
				par.add("Operazione Riuscita");
				par.add("Dati credenziali aggiornati correttamente!");
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
