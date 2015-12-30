package ui.controller.cliente;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.AutoText;
import utility.GestioneDate;
import utility.controller.UsernameController;
import utility.controller.EmailController;
import utility.controller.PasswordController;
import utility.controller.Controller;
import utility.controller.ConfermaPassController;
/**
 * Controller per l'interfaccia modificaCredenziali.fxml.
 * 
 * @author Gianluca
 * 
 */
public class ModificaCredenzialiController implements Initializable{

	@FXML 
	private Label titoloModifica;
	@FXML 
	private Label usernameLabel;
	@FXML 
	private Label passwordLabel;
	@FXML 
	private Label emailLabel;
	@FXML
	private Label confermaPassabel;
	@FXML
	private TextField usernameTextField;
	@FXML 
	private PasswordField passwordField;
	@FXML 
	private TextField emailTextField;
	@FXML 
	private PasswordField confermaPassField;
	@FXML
	private Button modificaButton;
	@FXML 
	private Label erroreNome;
	
	private FrontControllerInt fc;
	private Controller controller;
	

	/**
	 * Imposta gli eventi associati al pulsante modifica.
	 */
	private void setModificaButton() {
		modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				salvaModifiche();
			}
		});
		modificaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					salvaModifiche();
				}
			}

			
		});
		confermaPassField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					salvaModifiche();
				}
			}
		});
	}
	
	/**
	 * Controlla la correttezza dei dati inseriti e termina la registrazione.
	 */
	private void salvaModifiche() {
		boolean username = controllaUsername();
		boolean password = controllaPassword();
		boolean email = controllaEmail();
		boolean confermaPass = controllaConfermaPass();
		
		
		if (username && password && email && confermaPass ) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(this.usernameTextField.getText());
			p.add(this.passwordField.getText());
			p.add(this.emailTextField.getText());
			p.add(this.confermaPassField.getText());
			boolean save;
			save = (boolean) fc.processRequest("setAccountCliente", p);
			if (save) {
				ArrayList<String> par = new ArrayList<>();
				par.add("0");
				par.add("Operazione Riuscita");
				par.add("Dati credenziali aggiornati correttamente!");
				fc.processRequest("visualizzaMessaggio", par);
				fc.processRequest("aggiornaPane");
			}
		}
		
	}
	
	private boolean controllaUsername(){
		String CAP = this.usernameTextField.getText();
		controller = new UsernameController();
		boolean controllaUsername = controller.check(CAP);
		if (controllaUsername) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaUsername;
	}

	private boolean controllaPassword() {
		String password = this.passwordField.getText();
		controller = new PasswordController();
		boolean controllaPassword = controller.check(password);
		if (controllaPassword) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaPassword;
	}

	private boolean controllaEmail() {
		String email = this.emailTextField.getText();
		controller = new EmailController();
		boolean controllaEmail = controller.check(email);
		if (controllaEmail) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaEmail;
	}

	private boolean controllaConfermaPass() {
		String confermaPass = this.confermaPassField.getText();
		controller = new ConfermaPassController();
		boolean controllaConfermaPass = controller.check(confermaPass);
		if (controllaConfermaPass) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaConfermaPass;
	}

	/**
	 * Disabilita il form per la modifica dei dati anagrafici.
	 * 
	 * @param value true per disabilitare le modifiche, false per abilitarle.
	 */
	private void disabilitaModifica(boolean value) {
		this.usernameTextField.setDisable(value);
		this.emailTextField.setDisable(value);
		this.passwordField.setDisable(value);
		this.confermaPassField.setDisable(value);
		this.modificaButton.setVisible(value);
	}
	
	/**
	 * Imposta le TextField con auto completamento
	 */
	private void setAutoText() {
		AutoText autoTextUsername = new AutoText(this.usernameTextField);
		autoTextUsername.autoInsertion("Clienti", AutoText.USERNAME);

		AutoText autoTextEmail = new AutoText(this.emailTextField);
		autoTextEmail.autoInsertion("Clienti", AutoText.EMAIL);
	}
	
	/**
	 * Carica i dati dell'interfaccia.
	 */
	@SuppressWarnings("unchecked")
	private void setDati() {
		@SuppressWarnings("unchecked")
		HashMap<String, String> dati;
		dati = (HashMap<String, String>) fc.processRequest("getAccountCliente");
		this.usernameTextField.setText(dati.get("username"));
		this.emailTextField.setText(dati.get("email"));
		this.passwordField.setText(dati.get("password"));
		this.confermaPassField.setText(dati.get("confermaPass"));
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// inizializza il front controller
		fc = new FrontController();
		// imposta gli eventi associati ai pulsanti
		setModificaButton();
		// imposto le text field di tipo autocompletmento
		setAutoText();
		// imposto i dati relativi all'utente corrente
		setDati();
	}
}
