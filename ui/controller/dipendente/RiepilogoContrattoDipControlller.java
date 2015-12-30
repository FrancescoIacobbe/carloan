package ui.controller.dipendente;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;




import presentazione.FrontController;
import presentazione.FrontControllerInt;
import ui.controller.RiepilogoContratto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Controller per l'interfaccia dipendente.riepilogoContratto.fxml.
 * @author Antonella
 *
 */
public class RiepilogoContrattoDipControlller  extends RiepilogoContratto {

	
	@FXML
	private Button modificaButton;
	@FXML
	private Label titoloRiepilogoContratto;

	private FrontControllerInt fc;
	
	private boolean modificabile;
	
	@Override
	protected void indietro() {
		// TODO Auto-generated method stub
		fc.processRequest("homeDipendente");
	}



	/**
	 * Torna alla schermata precendente.
	 */
	
	protected void setModificaButton() {
		this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modificaContratto();
			}
		});
		this.modificaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					// invia richiesta di stampa
					modificaContratto();
				}
			}
		});
	}
	private void modificaContratto() {
		if (!modificabile) {
			impossibileModificareCon();
		} else {
		fc.processRequest("modificaContratto");
		}
	}
	
	/**
	 * Avvisa l'utente che non è possibile apportare modifiche.
	 */
	private void impossibileModificareCon() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Non è possibile apportare modifiche all'iscrizione per la competizione selezionata.\n"
				+ "Il termine ultimo per apportare modifiche alle iscrizioni è scaduto.");
		fc.processRequest("visualizzaMessaggio", par);
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fc = new FrontController();
		setIndietroButton();
		setPdfButton();
		getDati();
		setModificaButton();
	}

}
