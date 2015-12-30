package ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import extfx.scene.control.DatePicker;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.GestioneDate;
import utility.controller.CAPController;
import utility.controller.CFController;
import utility.controller.CittaController;
import utility.controller.CognomeController;
import utility.controller.Controller;
import utility.controller.EmailController;
import utility.controller.IndirizzoController;
import utility.controller.NomeController;
import utility.controller.NumeroTelController;
import utility.controller.PasswordController;
import utility.controller.UsernameController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Controller dell'interfaccia registra.fxml.
 * 
 * @author Gianluca
 * 
 */
public  abstract class RegistraController implements Initializable{
	
	

	@FXML
	private Label nome;
	
	@FXML
	protected TextField nomeTextField;
	
	@FXML
	private ImageView okNomeImageView;
	
	@FXML
	private ImageView errorNomeImageView;
	
	@FXML
	private Label cognome;
	
	@FXML
	protected TextField cognomeTextField;
	
	@FXML
	private ImageView errorCognomeImageView;
	
	@FXML
	private ImageView okCognomeImageView;
	
	@FXML
	private Label CF;

	@FXML
	protected TextField CFTextField;
	
	@FXML
	private ImageView errorCodiceFiscaleImageView;
	
	@FXML
	private ImageView okCodiceFiscaleImageView;
	
	@FXML
	private Label dataNascita;

	@FXML
	protected DatePicker dataNascitaDatePicker;
	
	@FXML
	private ImageView errorDataImageView;
	
	@FXML
	private ImageView okDataImageView;
	
	@FXML
	private Label indirizzo;
	
	@FXML
	protected TextField indirizzoTextField;

	@FXML
	private ImageView errorIndirizzoImageView;
	
	@FXML
	private ImageView okIndirizzoImageView;

	@FXML
	private Label cittaLabel;

	@FXML
	protected TextField cittaTextField;
	
	@FXML
	private ImageView okCittaImageView;

	@FXML
	private ImageView errorCittaImageView;

	@FXML
	private Label CAPLabel;

	@FXML
	protected TextField CAPTextField;
	
	@FXML
	private ImageView okCAPImageView;

	@FXML
	private ImageView errorCAPImageView;
	
	@FXML
	private Label numTelLabel;

	@FXML
	protected TextField numTelTextField;
	
	@FXML
	private ImageView oknumTelImageView;

	@FXML
	private ImageView errorNumTelImageView;
	
	@FXML
	private Label usernameLabel;

	@FXML
	protected TextField usernameTextField;
	
	@FXML
	private ImageView okUsernameImageView;

	@FXML
	private ImageView errorUsernameImageView;
	
	@FXML
	private Label passwordLabel;

	@FXML
	protected PasswordField passwordField;
	
	@FXML
	private ImageView okPasswordImageView;

	@FXML
	private ImageView errorConfermaPasswordImageView;
	
	@FXML
	private Label emailLabel;

	@FXML
	protected TextField emailTextField;
	
	@FXML
	private ImageView okEmailImageView;

	@FXML
	private ImageView errorEmailImageView;
	
	@FXML
	private Label confermaPassLabel;

	@FXML
	protected PasswordField confermaPassField;
	
	@FXML
	private ImageView okConfermaPassImageView;

	@FXML
	private ImageView errorPassImageView;
	@FXML
	private Button annullaButton;
		
	@FXML
	private Button confermaButton;
	
	@FXML
	private Button indietroButton;

	private FrontControllerInt fc;
	private Controller controller;
	private GregorianCalendar date;

	private Node confermaPasswordField;

	/**
	 * Controlla la correttezza del nome inserito
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaNome() {
		String nome = this.nomeTextField.getText();
		controller = new NomeController();
		boolean controllaNome = controller.check(nome);
		if (controllaNome) {
			this.errorNomeImageView.setVisible(false);
			this.okNomeImageView.setVisible(true);
		} else {
			this.okNomeImageView.setVisible(false);
			this.errorNomeImageView.setVisible(true);
		}
		return controllaNome;
	}

	/**
	 * Controlla la correttezza del congnome inserito.
	 * 
	 * @return treu sse è corretto
	 */
	protected boolean controllaCognome() {
		String congnome = this.cognomeTextField.getText();
		controller = new CognomeController();
		boolean controllaCognome = controller.check(congnome);
		if (controllaCognome) {
			this.errorCognomeImageView.setVisible(false);
			this.okCognomeImageView.setVisible(true);
		} else {
			this.okCognomeImageView.setVisible(false);
			this.errorCognomeImageView.setVisible(true);
		}
		return controllaCognome;

	}

	/**
	 * Controlla la correttezza della data inserita
	 * 
	 * @return true sse è corretta.
	 */
	protected boolean controllaData() {
		boolean isValid = (dataNascitaDatePicker.getValue() != null);
		if (isValid) {
			this.errorDataImageView.setVisible(false);
			this.okDataImageView.setVisible(true);
		} else {
			this.okDataImageView.setVisible(false);
			this.errorDataImageView.setVisible(true);
		}
		return isValid;
	}

	/**
	 * Controlla la correttezza del CF inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaCodiceFiscale() {
		String CF = this.CFTextField.getText();
		controller = new CFController();
		boolean controllaCodiceFiscale = controller.check(CF);
		if (controllaCodiceFiscale) {
			this.errorCodiceFiscaleImageView.setVisible(false);
			this.okCodiceFiscaleImageView.setVisible(true);
		} else {
			this.okCodiceFiscaleImageView.setVisible(false);
			this.errorCodiceFiscaleImageView.setVisible(true);
		}
		return controllaCodiceFiscale;
	}

	

	/**
	 * Controlla la correttezza della città inserita.
	 * 
	 * @return true sse è corretta.
	 */
	protected boolean controllaCitta() {
		String citta = this.cittaTextField.getText();
		controller = new CittaController();
		boolean controllaCitta = controller.check(citta);
		if (controllaCitta) {
			this.errorCittaImageView.setVisible(false);
			this.okCittaImageView.setVisible(true);
		} else {
			this.okCittaImageView.setVisible(false);
			this.errorCittaImageView.setVisible(true);
		}
		return controllaCitta;
	}

	/**
	 * Controlla la correttezza dell'indirizzo inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaIndirizzo() {
		String indirizzo = this.indirizzoTextField.getText();
		controller = new IndirizzoController();
		boolean controllaIndirizzo = controller.check(indirizzo);
		if (controllaIndirizzo) {
			this.errorIndirizzoImageView.setVisible(false);
			this.okIndirizzoImageView.setVisible(true);
		} else {
			this.okIndirizzoImageView.setVisible(false);
			this.errorIndirizzoImageView.setVisible(true);
		}
		return controllaIndirizzo;
	}


	/**
	 * Controlla la correttezza dell'CAP inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaCAP() {
		String CAP = this.CAPTextField.getText();
		controller = new CAPController();
		boolean controllaCAP = controller.check(CAP);
		if (controllaCAP) {
			this.errorCAPImageView.setVisible(false);
			this.okCAPImageView.setVisible(true);
		} else {
			this.okCAPImageView.setVisible(false);
			this.errorCAPImageView.setVisible(true);
		}
		return controllaCAP;
	}
	

	/**
	 * Controlla la correttezza del numero di telefono inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaNumeroTel() {
		String numTel = this.numTelTextField.getText();
		controller = new NumeroTelController();
		boolean controllaNumTel = controller.check(numTel);
		if (controllaNumTel) {
			this.errorNumTelImageView.setVisible(false);
			this.oknumTelImageView.setVisible(true);
		} else {
			this.oknumTelImageView.setVisible(false);
			this.errorNumTelImageView.setVisible(true);
		}
		return controllaNumTel;
	}
	
	/**
	 * Controlla la correttezza del username inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaUsername() {
		String username = this.usernameTextField.getText();
		controller = new UsernameController();
		boolean controllaUsername= controller.check(username);
		if (controllaUsername) {
			this.errorUsernameImageView.setVisible(false);
			this.okUsernameImageView.setVisible(true);
		} else {
			this.okUsernameImageView.setVisible(false);
			this.errorUsernameImageView.setVisible(true);
		}
		return controllaUsername;
	}
	
	/**
	 * Controlla la correttezza della password inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaPassword() {
		String Password = this.passwordField.getText();
		controller = new PasswordController();
		boolean controllaPassword = controller.check(Password);
		if (controllaPassword) {
			this.errorPassImageView.setVisible(false);
			this.okPasswordImageView.setVisible(true);
		} else {
			this.okPasswordImageView.setVisible(false);
			this.errorPassImageView.setVisible(true);
		}
		return controllaPassword;
	}
	
	/**
	 * Controlla la correttezza del email inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaEmail() {
		String email = this.emailTextField.getText();
		controller = new EmailController();
		boolean controllaEmail = controller.check(email);
		if (controllaEmail) {
			this.errorEmailImageView.setVisible(false);
			this.okEmailImageView.setVisible(true);
		} else {
			this.okEmailImageView.setVisible(false);
			this.errorEmailImageView.setVisible(true);
		}
		return controllaEmail;
	}
	
	/**
	 * Controlla la correttezza del conferma password inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaConfermaPass() {
		String ConfermaPass = this.confermaPassField.getText();
		controller = new PasswordController();
		boolean controllaConfermaPass = controller.check(ConfermaPass);
		if (controllaConfermaPass) {
			this.errorConfermaPasswordImageView.setVisible(false);
			this.okConfermaPassImageView.setVisible(true);
		} else {
			this.okConfermaPassImageView.setVisible(false);
			this.errorConfermaPasswordImageView.setVisible(true);
		}
		return controllaConfermaPass;
	}
	
	/**
	 * Imposta gli eventi associati al pulsante conferma.
	 */
	protected void setConfermaButton() {
		confermaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				terminaRegistrazione();
			}
		});
		confermaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					terminaRegistrazione();
				}
			}
		});
		confermaPasswordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					terminaRegistrazione();
				}
			}
		});
	}

	protected abstract void terminaRegistrazione();


	/**
	 * Imposta gli eventi associati al pulsante annulla.
	 */
	protected void setAnnullaButton() {
		annullaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				cancellaDatiInseriti();
			}
		});
		annullaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					cancellaDatiInseriti();
				}
			}
		});
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
	

	/**
	 * Avviso per l'operazione indietro.
	 * 
	 * @return la scelta dell'utente.
	 */
	private boolean avvisoIndietro() {
		ArrayList<String> par = new ArrayList<>();
		par.add("3");
		par.add("Conferma operazione");
		par.add("Abbandonando questa finestra, i dati inseriti verranno cancellati.\nSicuro di voler uscire?");
		boolean controll;
		controll = (boolean) fc.processRequest("visualizzaMessaggio",
					par);
		return controll;
	}

	/**
	 * Svuota tutti i campi del form.
	 */
	private void cancellaDatiInseriti() {
		svuotaTextField(this.nomeTextField);
		svuotaTextField(this.cognomeTextField);
		svuotaTextField(this.CFTextField);
		svuotaTextField(this.cittaTextField);
		svuotaTextField(this.indirizzoTextField);
		svuotaTextField(this.CAPTextField);
		svuotaTextField(this.numTelTextField);
		svuotaTextField(this.usernameTextField);
		svuotaTextField(this.passwordField);
		svuotaTextField(this.emailTextField);
		svuotaTextField(this.confermaPassField);
		nascondiNode(this.errorNomeImageView);
		nascondiNode(this.errorCognomeImageView);
		nascondiNode(this.errorDataImageView);
		nascondiNode(this.errorCodiceFiscaleImageView);
		nascondiNode(this.errorCittaImageView);
		nascondiNode(this.errorIndirizzoImageView);
		nascondiNode(this.errorCAPImageView);
		nascondiNode(this.errorNumTelImageView);
		nascondiNode(this.errorUsernameImageView);
		nascondiNode(this.errorPassImageView);
		nascondiNode(this.errorEmailImageView);
		nascondiNode(this.errorConfermaPasswordImageView);
		nascondiNode(this.okNomeImageView);
		nascondiNode(this.okCognomeImageView);
		nascondiNode(this.okDataImageView);
		nascondiNode(this.okCodiceFiscaleImageView);
		nascondiNode(this.okCittaImageView);
		nascondiNode(this.okIndirizzoImageView);
		nascondiNode(this.okCAPImageView);
		nascondiNode(this.oknumTelImageView);
		nascondiNode(this.okUsernameImageView);
		nascondiNode(this.okPasswordImageView);
		nascondiNode(this.okEmailImageView);
		nascondiNode(this.okConfermaPassImageView);
		
		dataNascitaDatePicker.setValue(null);
	}

	/**
	 * Cancella il contenuto di una textField.
	 * 
	 * @param textField
	 *            elemento da svuotare
	 */
	private void svuotaTextField(TextField textField) {
		textField.setText("");
	}

	/**
	 * Nasconde un nodo.
	 * 
	 * @param node
	 *            elemento da nascodere.
	 */
	private void nascondiNode(Node node) {
		node.setVisible(false);
	}

	public abstract void initialize(URL location, ResourceBundle resources);



}
