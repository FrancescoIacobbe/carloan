package ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Classe astratta di supporto per le interfacce.
 * Fattorizza variabili e metodi legati all'account di un utente.
 * @author Gianluca
 *
 */
public abstract class ModificaCredenziali extends ControlliCredenziali implements Initializable{

	@FXML
	protected Button annullaButton;

	@FXML
	protected Button confermaButton;

	@FXML
	protected Button modificaButton;

	@FXML
	protected Label titoloModificaAccount;
	

	/**
	 * Imposta gli eventi associati al pulsante modifica.
	 */
	protected void setModificaButton() {
		modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				disabilitaModifica(false);
			}
		});
		modificaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					disabilitaModifica(false);
				}
			}
		});
	}

	/**
	 * Disabilita il form per la modifica dei dati dell'account.
	 * @param value true per disabilitare le modifiche, altrimenti false.
	 */
	protected void disabilitaModifica(boolean value) {
		this.emailTextField.setDisable(value);
		this.confermaEmailTextField.setDisable(value);
		this.passwordTextField.setDisable(value);
		this.confermaPasswordTextField.setDisable(value);
		this.modificaButton.setVisible(value);
		this.confermaButton.setVisible(!value);
		this.annullaButton.setDisable(value);
	}

	/**
	 * Imposta gli eventi associati al pulsante conferma.
	 */
	protected void setConfermaButton() {
		confermaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				salvaModifiche();
			}
		});
		confermaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					salvaModifiche();
				}
			}
		});
		confermaPasswordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					salvaModifiche();
				}
			}
		});
	}
	
	/**
	 * Imposta gli eventi associati al pulsante annullaButton.
	 */
	private void setAnnullaButton() {
		this.annullaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				annulla();
			}
		});
		this.annullaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					annulla();
				}
			}
		});
	}

	/**
	 * Azione legata al pulsante annullaButton.
	 */
	private void annulla() {
		if (avvisoAnnulla()) {
			fc.processRequest("aggiornaPane");
		}
	}

	/**
	 * Avviso per l'operazione annulla.
	 * 
	 * @return la scelta dell'utente.
	 */
	private boolean avvisoAnnulla() {
		ArrayList<String> par = new ArrayList<>();
		par.add("3");
		par.add("Conferma operazione");
		par.add("Le modifiche apportate non verranno memorizzate.\nSicuro di voler continuare?");
		boolean controll;
		controll = (boolean) fc.processRequest("visualizzaMessaggio",
					par);
		return controll;
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// inizializzo il Front Controller
		fc = new FrontController();
	
		// imposto gli eventi associati ai pulsanti
		setModificaButton();
		setConfermaButton();
		setAnnullaButton();
		setDati();
	}
	
	/**
	 * Carica i dati dell'interfaccia.
	 */
	protected abstract void setDati();
	
	/**
	 * Azione legata al pulsante conferma.
	 */
	protected abstract void salvaModifiche();
	
}
