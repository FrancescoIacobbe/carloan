package ui.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import presentazione.FrontControllerInt;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public  abstract class RiepilogoRequisito implements Initializable{

	@FXML 
	protected Label IDLabel;
	@FXML
	protected Label descrizioneLabel; 
	@FXML 
	protected Label modelliLabel;
	@FXML
	protected Label id;
	@FXML
	protected Label descrizione;
	@FXML
	protected Label modelli;
	@FXML
	protected Button indietroButton;
	@FXML
	protected Label titoloRiepilogoRequisito;
	
	protected FrontControllerInt fc;
	
	/**
	 * Imposta gli eventi associati al pulsante indietro.
	 */
	protected void setIndietroButton() {
		indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				indietro();
			}
		});
		indietroButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					indietro();
				}
			}
		});
	}
	
	
	/**
	 * Raccoglie in un {@link HashMap} le informazioni sul requisito.
	 * @return un {@link HashMap} con le informazioni.
	 */
	protected HashMap<String, String> getDati() {
		LinkedHashMap<String,String> dati = new LinkedHashMap<String, String>();
		dati.put(IDLabel.getText(), id.getText());
		dati.put(descrizioneLabel.getText(), descrizione.getText());
		dati.put(modelliLabel.getText(), modelli.getText());
		return dati;
	}
	
	/**
	 * Azione associata al pulsante indietro.
	 */
	protected abstract void indietro();

	public abstract void initialize(URL location, ResourceBundle resources);


	
	
}
