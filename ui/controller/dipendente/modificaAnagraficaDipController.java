package ui.controller.dipendente;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import extfx.scene.control.DatePicker;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.AutoText;
import utility.GestioneDate;
import utility.controller.CAPController;
import utility.controller.CFController;
import utility.controller.CittaController;
import utility.controller.NumeroTelController;
import utility.controller.CognomeController;
import utility.controller.Controller;
import utility.controller.IndirizzoController;
import utility.controller.NomeController;
import utility.controller.CAPController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * Controller per l'interfaccia modificaAnagrafica.fxml.
 * 
 * @author Gianluca
 * 
 */
public class modificaAnagraficaDipController implements Initializable{

	@FXML 
	private Label titoloModifica;
	@FXML 
	private Label nomeLabel;
	@FXML 
	private Label cognomeLabel;
	@FXML 
	private Label indirizzoLabel;
	@FXML
	private Label CFLabel;
	@FXML 
	private Label dataNascitaLabel;
	@FXML
	private Label cittaLabel;
	@FXML
	private Label CAPLabel;
	@FXML
	private Label numTelLabel;
	@FXML
	protected TextField nomeTextField;
	@FXML
	protected TextField cognomeTextField;
	@FXML
	protected TextField indirizzoTextField;
	@FXML
	protected TextField CFTextField;
	@FXML
	protected DatePicker  dataNascitaDatePicker;
	@FXML
	protected TextField cittaTextField;
	@FXML
	protected TextField CAPTextField;
	@FXML
	protected TextField numTelTextField;
	@FXML
	private Button modificaButton;
	@FXML 
	private Label erroreNome;
	@FXML
	private Button indietroButton;
	
	
	private FrontControllerInt fc;
	private Controller controller;
	protected GestioneDate gs;
	
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
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("homeDipendente");
	}
	
	/**
	 * Imposta gli eventi associati al pulsante modifica.
	 */
	protected void setModificaButton() {
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
		numTelTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
		boolean nome = controllaNome();
		boolean cognome = controllaCognome();
		boolean dataNascita = controllaData();
		boolean CF = controllaCodiceFiscale();
		boolean citta = controllaCitta();
		boolean indirizzo = controllaIndirizzo();
		boolean numeroTel= controlloNumTel();
		boolean CAP = controlloCAP();
		
		
		if (nome && cognome && dataNascita && CF  && citta
				&& indirizzo && numeroTel && CAP) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(this.nomeTextField.getText());
			p.add(this.cognomeTextField.getText());
			p.add(gs.parseData(this.dataNascitaDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(this.CFTextField.getText());
			p.add(this.cittaTextField.getText());
			p.add(this.indirizzoTextField.getText());
			p.add(this.numTelTextField.getText());
			p.add(this.CAPTextField.getText());
			boolean save;
			save = (boolean) fc.processRequest("setDipendente", p);
			if (save) {
				ArrayList<String> par = new ArrayList<>();
				par.add("0");
				par.add("Operazione Riuscita");
				par.add("Dati anagrafici aggiornati correttamente!");
				fc.processRequest("visualizzaMessaggio", par);
				fc.processRequest("aggiornaPane");
				}
		}
		
	}
	
	protected boolean controlloCAP() {
		String CAP = this.CAPTextField.getText();
		controller = new CAPController();
		boolean controllaCAP = controller.check(CAP);
		if (controllaCAP) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCAP;
	}

	protected boolean controlloNumTel() {
		String NumTel = this.numTelTextField.getText();
		controller = new NumeroTelController();
		boolean controllaNumeroTel = controller.check(NumTel);
		if (controllaNumeroTel) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaNumeroTel;
	}

	protected boolean controllaIndirizzo() {
		String indirizzo = this.indirizzoTextField.getText();
		controller = new IndirizzoController();
		boolean controllaIndirizzo = controller.check(indirizzo);
		if (controllaIndirizzo) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaIndirizzo;
	}

	protected boolean controllaCitta() {
		String citta = this.cittaTextField.getText();
		controller = new CittaController();
		boolean controllaCitta = controller.check(citta);
		if (controllaCitta) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCitta;
	}

	protected boolean controllaData() {
		boolean isValid = (dataNascitaDatePicker.getValue() != null);
		if (isValid) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return isValid;
	}

	protected boolean controllaCodiceFiscale() {
		String CF = this.CFTextField.getText();
		controller = new CFController();
		boolean controllaCF = controller.check(CF);
		if (controllaCF) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCF;
	}

	protected boolean controllaCognome() {
		String cognome = this.cognomeTextField.getText();
		controller = new CognomeController();
		boolean controllaCognome = controller.check(cognome);
		if (controllaCognome) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCognome;
	}

	protected boolean controllaNome() {
			String nome = this.nomeTextField.getText();
			controller = new NomeController();
			boolean controllaNome = controller.check(nome);
			if (controllaNome) {
				this.erroreNome.setVisible(false);
			}
			else{
				this.erroreNome.setVisible(true);
			}
			return controllaNome;
	}

	/**
	 * Disabilita il form per la modifica dei dati anagrafici.
	 * 
	 * @param value true per disabilitare le modifiche, false per abilitarle.
	 */
	private void disabilitaModifica(boolean value) {
		this.nomeTextField.setDisable(value);
		this.cognomeTextField.setDisable(value);
		this.dataNascitaDatePicker.setDisable(value);
		this.CFTextField.setDisable(value);
		this.cittaTextField.setDisable(value);
		this.indirizzoTextField.setDisable(value);
		this.numTelTextField.setDisable(value);
		this.CAPTextField.setDisable(value);
		this.modificaButton.setVisible(value);
	}
	
	/**
	 * Imposta le TextField con auto completamento
	 */
	protected void setAutoText() {
		AutoText autoTextNome = new AutoText(this.nomeTextField);
		autoTextNome.autoInsertion("Dipendenti", AutoText.NOME);

		AutoText autoTextCognome = new AutoText(this.cognomeTextField);
		autoTextCognome.autoInsertion("Dipendenti", AutoText.COGNOME);

		AutoText autoTextCitta = new AutoText(this.cittaTextField);
		autoTextCitta.autoInsertion("Dipendenti", AutoText.CITTA);
	}
	
	/**
	 * Carica i dati dell'interfaccia.
	 */
	@SuppressWarnings("unchecked")
	protected void setDati() {
		@SuppressWarnings("unchecked")
		HashMap<String, String> dati;
		dati = (HashMap<String, String>) fc.processRequest("getAnagraficaDipendente");
		this.nomeTextField.setText(dati.get("nome"));
		this.cognomeTextField.setText(dati.get("cognome"));
		Calendar date = gs.parseString(dati.get("dataNascita"),
				GestioneDate.DATA);
		this.dataNascitaDatePicker.setValue(date.getTime());
		this.CFTextField.setText(dati.get("CF"));
		this.indirizzoTextField.setText(dati.get("indirizzo"));
		this.cittaTextField.setText(dati.get("citta"));
		this.CAPTextField.setText(dati.get("CAP"));
		this.numTelTextField.setText(dati.get("numTel"));
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// inizializza il front controller
		fc = new FrontController();
		// inizializzo il gestore delle date.
		gs = new GestioneDate();
		// imposta gli eventi associati ai pulsanti
		setModificaButton();
		// imposto le text field di tipo autocompletmento
		setAutoText();
		// imposto i dati relativi all'utente corrente
		setDati();
		setIndietroButton();
	}
}
