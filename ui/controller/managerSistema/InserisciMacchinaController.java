package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.controller.RiepilogoMacchina;
import utility.GestioneDate;
import utility.controller.Controller;
import utility.controller.IntegerController;
import utility.controller.MacchinaController;

public class InserisciMacchinaController extends RiepilogoMacchina  {

	@FXML 
	protected TextField targaTextField;
	@FXML 
	protected TextField modelloTextField;
	@FXML
	protected RadioButton yesRadioButton;
	@FXML
	protected RadioButton noRadioButton;
	
	protected TextField ultimoKmTextField;
	
	protected TextArea requisitoTextArea;
	@FXML 
	protected Button confermaButton;
	@FXML
	protected ImageView modelloErrore;
	@FXML
	protected ImageView targaErrore;
	@FXML
	protected ImageView ultimoKmErrore;
	@FXML
	protected ImageView requisitoErrore;
	
	private FrontControllerInt fc;
	private Controller controller;
	
	ObservableList<String> fascia = 
		    FXCollections.observableArrayList(
		        "A",
		        "B",
		        "C",
		        "D"
		    );
		final ComboBox fasciaComboBox = new ComboBox(fascia);
		
		ObservableList<String> manuntenzione = 
			    FXCollections.observableArrayList(
			        "Si",
			        "No"
			    );
			final ComboBox manutenzioneComboBox = new ComboBox(manuntenzione);
		
			
	/**
	 * Imposta gli eventi associati al pulsante conferma.
	 */
	@SuppressWarnings("unused")
	protected void setConfermaButton() {
		confermaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				conferma();
			}
		});
		confermaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					conferma();
				}
			}
		});
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
			boolean eseguito;
			eseguito = (boolean) fc.processRequest("registraMacchina", p);
				
		}
	}

	
	protected boolean controlloRequsito() {
		String testo = requisitoTextArea.getText();
		boolean controll = false;
		if (testo != null && testo.length() <= 255) {
			controll = true;
		} else {
			this.requisitoErrore.setVisible(true);
		}
		return controll;
	}

	protected boolean controlloUltimoKm() {
		String UltimoKm = this.ultimoKmTextField.getText();
		controller = new IntegerController();
		boolean controllaKm= controller.check(UltimoKm);
		if (controllaKm) {
			this.ultimoKmErrore.setVisible(false);
		}
		else{
			this.ultimoKmErrore.setVisible(true);
		}
		return controllaKm;
	}

	protected boolean controlloModello() {
		String modello= modelloTextField.getText();
		boolean controll;
		if (modello != null && modello.length() > 0 && modello.length() < 255) {
			controll = true;
			modelloErrore.setVisible(false);
		} else {
			controll = false;
			modelloErrore.setVisible(true);
		}
		return controll;
	}

	protected boolean controlloTarga() {
		String targa = this.targaTextField.getText();
		controller = new MacchinaController();
		boolean controllaTarga = controller.check(targa);
		if (controllaTarga) {
			this.targaErrore.setVisible(false);
		}
		else{
			this.targaErrore.setVisible(true);
		}
		return controllaTarga;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fc= new FrontController();
		setIndietroButton();
		setConfermaButton();
		
	}

	@Override
	protected void indietro() {
		fc.processRequest("homeMangerSistema");
	}

}
