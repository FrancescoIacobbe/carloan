package ui.controller;

import java.net.URL;
import java.util.ArrayList;
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

public abstract class RiepilogoMacchina implements Initializable {

	@FXML
	protected Label targaLabel;
	@FXML 
	protected Label modelloLabel;
	@FXML 
	protected Label disponibilit‡Label;
	@FXML
	protected Label manutezioneLabel;
	@FXML 
	protected Label ultimoKmLabel;
	@FXML
	protected Label fasciaLabel;
	@FXML
	protected Label requisitoLabel;
	@FXML
	protected Label targa;
	@FXML 
	protected Label modello;
	@FXML 
	protected Label disponibilit‡;
	@FXML
	protected Label manutezione;
	@FXML 
	protected Label ultimoKm;
	@FXML
	protected Label fascia;
	@FXML 
	protected Label requisito;
	@FXML 
	protected Label  titoloRiepilogoMacchina;
	@FXML
	protected Button indietroButton;
	@FXML
	protected Button eliminaButton;
	
	protected FrontControllerInt fc;
	
	/**
	 * Imposta le funzioni del pulsante cancella.
	 */
	protected void setEliminaButton() {
		eliminaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				elimina();
			}
		});
		eliminaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					elimina();
				}
			}
		});
	}

	/**
	 * Imposta il processo di cancellazione di una macchina.
	 */
	private void elimina() {
		ArrayList<String> par = new ArrayList<String>();
		par.add("3");
		par.add("Conferma Operazione");
		par.add("Vuoi davvero cancellare la macchina selezionata?");
		boolean conferma;
			conferma = (boolean) fc.processRequest("visualizzaMessaggio",
					par);
		if (conferma) {
			par = new ArrayList<String>();
			par.add("Conferma Cancellazione");
			par.add("Motivazione cancellazione");
			String motivazione = null;
			motivazione = (String) fc.processRequest("inputDialog", par);
			par.clear();
			par.add(motivazione);
			boolean controll;
			controll = (boolean) fc.processRequest("eliminaMacchina", par);
			if (controll) {
				par = new ArrayList<String>();
				par.add("0");
				par.add("Operazione eseguita");
				par.add("L'iscrizione Ë stata cancellata correttamente");
				fc.processRequest("visualizzaMessaggio", par);
				fc.processRequest("mostraTutteMacchine");
				
			} else {
				par = new ArrayList<String>();
				par.add("1");
				par.add("Operazione non riuscita");
				par.add("La cancellazione dell'iscrizione non Ë riuscita. Riprovare.\nSe il problema persiste, contattare l'amministratore del sistema.");
				fc.processRequest("visualizzaMessaggio", par);
			

			}
		}
	}
	
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
	 * Raccoglie in un {@link HashMap} le informazioni sul contratto.
	 * @return un {@link HashMap} con le informazioni.
	 */
	protected HashMap<String, String> getDati() {
		LinkedHashMap<String,String> dati = new LinkedHashMap<String, String>();
		dati.put(targaLabel.getText(), targa.getText());
		dati.put(modelloLabel.getText(), modello.getText());
		dati.put(manutezioneLabel.getText(), manutezione.getText());
		dati.put(disponibilit‡Label.getText(), disponibilit‡.getText());
		dati.put(ultimoKmLabel.getText(), ultimoKm.getText());
		dati.put(fasciaLabel.getText(), fascia.getText());
		dati.put(requisitoLabel.getText(), requisito.getText());

		return dati;
	}
	

	
	/**
	 * Azione associata al pulsante indietro.
	 */
	protected abstract void indietro();
	
	public abstract void initialize(URL location, ResourceBundle resources);
 

}
