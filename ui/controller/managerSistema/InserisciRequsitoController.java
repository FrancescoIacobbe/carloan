package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.controller.RiepilogoRequisito;

public class InserisciRequsitoController  extends RiepilogoRequisito{

	@FXML 
	protected TextField IDTextField;
	@FXML 
	protected TextArea descrizioneTextArea;
	@FXML 
	protected TextArea modelliTextArea;
	@FXML 
	protected Button confermaButton;
	@FXML 
	protected ImageView descrizioneErrore;
	@FXML 
	protected ImageView modelliErrore;
	@FXML 
	protected ImageView IDErrore;
	
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
		boolean id = controlloId();
		boolean descrizione = controlloDescrizione();
		boolean modelli = controlloModelli();
		
		if (id && descrizione && modelli ) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(IDTextField.getText());
			p.add(descrizioneTextArea.getText());
			p.add(modelliTextArea.getText());
			
			boolean eseguito;
			eseguito = (boolean) fc.processRequest("registraRequisito", p);
		}
	}

	
	protected boolean controlloDescrizione() {
		String testo = descrizioneTextArea.getText();
		boolean controll = false;
		if (testo != null && testo.length() <= 255) {
			controll = true;
		} else {
			this.descrizioneErrore.setVisible(true);
		}
		return controll;
	}
	protected boolean controlloModelli() {
		String testo = modelliTextArea.getText();
		boolean controll = false;
		if (testo != null && testo.length() <= 255) {
			controll = true;
		} else {
			this.modelliErrore.setVisible(true);
		}
		return controll;
	}
	protected boolean controlloId() {
		String Id= IDTextField.getText();
		boolean controll;
		if (Id != null && Id.length() > 0 && Id.length() < 255) {
			controll = true;
			IDErrore.setVisible(false);
		} else {
			controll = false;
			IDErrore.setVisible(true);
		}
		return controll;
	}
	@Override
	protected void indietro() {
		fc.processRequest("homeManagerSistema");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fc= new FrontController();
		setIndietroButton();
		setConfermaButton();
	}

}
