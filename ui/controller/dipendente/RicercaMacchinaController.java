package ui.controller.dipendente;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentazione.FrontControllerInt;
import ui.controller.dipendente.CompilaContrattoController;


public class RicercaMacchinaController extends homeDipendenteController  {
	
	@FXML
	private Label titoloricerca; 
	@FXML  
	private Button scegliButton;
	
	private FrontControllerInt fc;
	
	private void setDati() {
		HashMap<String, String> dati ;
		dati = (HashMap<String,String>) fc.processRequest("getUsernameDipendente");
		String username = dati.get("username");
		titoloricerca.setText(titoloricerca.getText() + " " + username);
		caricaTabellaMacc();
		
	}
	
	/**
	 * Imposta gli eventi associati al pulsante scegliButton.
	 */
	@SuppressWarnings("unused")
	private void setScegliButton() {
		this.scegliButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scegli();
			}
		});
		this.scegliButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					scegli();
				}
			}
		});
	}
	
	/**
	 * Torna alla schermata precedente.
	 */
	protected void scegli() {
		String macchina;
			fc.processRequest("CompilaContratto");
			CompilaContrattoController macchine= new CompilaContrattoController();
			macchina=targaColTable.getText();
			macchine.macchinaTextField.setText(macchina);
	}

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setricercaMacchinaButton();
		setDati();
		setScegliButton();
	}

}
