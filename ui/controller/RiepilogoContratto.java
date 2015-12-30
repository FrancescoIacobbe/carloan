package ui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import presentazione.FrontControllerInt;

import utility.pdf.GestionePdf;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public abstract class RiepilogoContratto implements Initializable {

	@FXML
	protected Label IDLabel;

	@FXML
	protected Label dataInizioLabel;
	@FXML
	protected Label dataLimiteLabel;
	@FXML
	protected Label dataRestituzioneLabel;
	@FXML
	protected Label luogoRestituzioneLabel;
	@FXML
	protected Label tipoNoleggioLabel;
	@FXML
	protected Label macchinaLabel;
	@FXML
	protected Label tariffaBaseLabel;
	@FXML
	protected Label tipoKmLabel;
	@FXML
	protected Label tipoFasciaLabel;
	@FXML
	protected Label costoKmLabel;
	@FXML
	protected Label kmEffettiviabel;
	@FXML
	protected Label prezzoExtraLabel;
	@FXML
	protected Label accontoLabel;
	@FXML
	protected Label costoTotLabel;
	@FXML
	protected Label id;
	@FXML
	protected Label dataInizio;
	@FXML
	protected Label dataLimite;
	@FXML
	protected Label dataRestituzione;
	@FXML
	protected Label luogoRestituzione;
	@FXML
	protected Label tipoNoleggio;
	@FXML
	protected Label macchina;
	@FXML
	protected Label tariffaBase;
	@FXML
	protected Label tipoKm;
	@FXML
	protected Label tipoFascia;
	@FXML
	protected Label costoKm;
	@FXML
	protected Label kmEffettivi;
	@FXML
	protected Label prezzoExtra;
	@FXML
	protected Label acconto;
	@FXML
	protected Label costoTot;
	@FXML
	protected Button indietroButton;
	@FXML
	protected Button salvaPdfButton;
	@FXML
	protected Label titoloRiepilogoContratto;

	protected FrontControllerInt fc;
	
	protected boolean modificabile;
	
	protected void setPdfButton() {
		this.salvaPdfButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pdf();
			}
		});
		this.salvaPdfButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					pdf();
				}
			}
		});
	}
	/**
	 * Invia la richiesta inerente la reportistica al FrontController.
	 */
	protected void pdf() {
		File file;
		file = (File) fc.processRequest("salvaPdf");
		if (file != null) {
			salvaPdf(file);
		}
	}

	/**
	 * Memorizza il file di report in formato pdf.
	 * @param f il file in cui memorizzare il report.
	 */
	protected void salvaPdf(File f) {
		GestionePdf gp = new GestionePdf(f);
		boolean controll;
			controll = gp.saveReport("Riepilogo Contratto", getDati(), null,  null,null);
		if (controll) {
			creazioneRiuscita();
		} else {
			erroreGenerazioneReport();
		}
	}
	
	/**
	 * Mostra un messaggio di warning di errore nella generazione nel report.
	 */
	protected void erroreGenerazioneReport() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Riuscita");
		par.add("Errore nella creazione del file di report");
		fc.processRequest("visualizzaMessaggio", par);
		
	}
	
	
	/**
	 * Mostra un messaggio di avvenuta craezione del file di report.
	 */
	protected void creazioneRiuscita() {
		ArrayList<String> par = new ArrayList<>();
		par.add("0");
		par.add("Operazione Riuscita");
		par.add("File di report creato correttamente!");
		fc.processRequest("visualizzaMessaggio", par);
		
	}
	
	/**
	 * Raccoglie in un {@link HashMap} le informazioni sul contratto.
	 * @return un {@link HashMap} con le informazioni.
	 */
	protected HashMap<String, String> getDati() {
		LinkedHashMap<String,String> dati = new LinkedHashMap<String, String>();
		dati.put(IDLabel.getText(), id.getText());
		dati.put(dataInizioLabel.getText(), dataInizio.getText());
		dati.put(dataLimiteLabel.getText(), dataLimite.getText());
		dati.put(dataRestituzioneLabel.getText(), dataRestituzione.getText());
		dati.put(luogoRestituzioneLabel.getText(), luogoRestituzione.getText());
		dati.put(tipoNoleggioLabel.getText(), tipoNoleggio.getText());
		dati.put(macchinaLabel.getText(), macchina.getText());
		dati.put(tariffaBaseLabel.getText(), tariffaBase.getText()+ "€");
		dati.put(tipoKmLabel.getText(), tipoKm.getText());
		dati.put(tipoFasciaLabel.getText(), tipoFascia.getText());
		dati.put(costoKmLabel.getText(), costoKm.getText()+ "€");
		dati.put(kmEffettiviabel.getText(), kmEffettivi.getText());
		dati.put(prezzoExtraLabel.getText(), prezzoExtra.getText()+ "€");
		dati.put(accontoLabel.getText(), acconto.getText()+ "€");
		dati.put(costoTotLabel.getText(), costoTot.getText()+ "€");
		return dati;
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
	 * Azione associata al pulsante indietro.
	 */
	protected abstract void indietro();
	
	
	
	
	public abstract void initialize(URL location, ResourceBundle resources);

}
