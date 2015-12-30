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
import ui.controller.RiepilogoMacchina;

public class RiepilogoMacchinaMng  extends RiepilogoMacchina{

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
				modificaMacchina();
			}
		});
		this.modificaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					// invia richiesta di stampa
					modificaMacchina();
				}
			}
		});
	}
	private void modificaMacchina() {
		if (!modificabile) {
			impossibileModificare();
		} else {
		fc.processRequest("modificaMacchina");
		}
	}
	
	/**
	 * Avvisa l'utente che non Ë possibile apportare modifiche.
	 */
	private void impossibileModificare() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Non Ë possibile apportare modifiche all'iscrizione per la competizione selezionata.\n"
				+ "Il termine ultimo per apportare modifiche alle iscrizioni Ë scaduto.");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	private void setDatiMacchina() {
		HashMap<String, String> dati;
		dati = (HashMap<String, String>) fc.processRequest("getDatiMacchina");
		this.targaLabel.setText(dati.get("targa"));
		this.modelloLabel.setText(dati.get("modello"));
		this.manutezioneLabel.setText(dati.get("manutezione"));
		this.disponibilit‡Label.setText(dati.get("disponibilit‡"));
		this.ultimoKmLabel.setText(dati.get("utltimoKm"));
		this.fasciaLabel.setText(dati.get("fascia"));
		this.requisitoLabel.setText(dati.get("requisito"));

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
		fc=new FrontController();
		setIndietroButton();
		setModificaButton();
		getDati();
		setDatiMacchina();
		setEliminaButton();
		
	}
	
	

}
