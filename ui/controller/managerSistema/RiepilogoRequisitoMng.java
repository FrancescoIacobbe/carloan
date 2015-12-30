package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import ui.controller.RiepilogoRequisito;

public class RiepilogoRequisitoMng extends RiepilogoRequisito{

	private FrontControllerInt fc;
	@FXML
	private Button modificaButton;
	
	private boolean modificabile;
	

	/**
	 * Torna alla schermata precendente.
	 */
	
	private void setModificaButton() {
		this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modificaRequisito();
			}
		});
		this.modificaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					// invia richiesta di stampa
					modificaRequisito();
				}
			}
		});
	}
	private void modificaRequisito() {
		if (!modificabile) {
			impossibileModificare();
		} else {
		fc.processRequest("modificaRequisito");
		}
	}
	
	/**
	 * Avvisa l'utente che non è possibile apportare modifiche.
	 */
	private void impossibileModificare() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Non è possibile apportare modifiche al requisito selezionato");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	private void setDatiRequisito() {
		HashMap<String, String> dati ;
		dati = (HashMap<String, String>) fc.processRequest("getDatiRequisito");
		this.IDLabel.setText(dati.get("Id"));
		this.descrizioneLabel.setText(dati.get("descrizione"));
		this.modelliLabel.setText(dati.get("modelli"));
		modificabile = dati.get("modificabile").equalsIgnoreCase("true");
		boolean conclusa = dati.get("conclusa").equalsIgnoreCase("true");
		if (conclusa) {
			this.modificaButton.setVisible(false);
		}
		
	}
	@Override
	protected void indietro() {
		// TODO Auto-generated method stub
		fc.processRequest("homeManagerSistema");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fc= new FrontController();
		setModificaButton();
		setIndietroButton();
		getDati();
		setDatiRequisito();
	}
	

}
