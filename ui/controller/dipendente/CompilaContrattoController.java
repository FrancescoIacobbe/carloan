package ui.controller.dipendente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import extfx.scene.control.DatePicker;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import utility.GestioneDate;
import utility.controller.Controller;
import utility.controller.IntegerController;
import utility.controller.LuogoController;
import utility.controller.MacchinaController;
import utility.controller.ValutaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * Controller per l'interfaccia nuovaContratto.fxml.
 * @author Gianluca
 *
 */
public class CompilaContrattoController extends RiepilogoContrattoDipControlller  {
	
	@FXML
	private Button confermaButton;
	
	@FXML
	private Button annullaButton;
	@FXML
	protected DatePicker dataInizioDatePicker;
	@FXML
	protected DatePicker dataLimiteDatePicker;
	@FXML
	protected DatePicker dataRestituzioneDatePicker;
	@FXML
	protected TextField luogoRestituzioneTextField;
	
	protected TextField macchinaTextField;
	@FXML
	protected TextField tariffaBaseTextField;
	
	protected TextField costoKmTextField;
	@FXML
	protected TextField kmEffettiviTextField;
	@FXML
	protected TextField prezzoExtraTextField;
	@FXML
	protected TextField accontoTextField;
	@FXML
	protected TextField costoTotTextField;
	@FXML
	private Button ricercaMacchinaButton;
	@FXML
	private Button indietroButton;

	private FrontControllerInt fc;
	
	protected GestioneDate gd;

	@FXML 
	private Label erroreNome;
	
	private Controller controller;
    
	ObservableList <String> fascia = 
		    FXCollections.observableArrayList 
		        ("A", 
		        "B", 
		        "C",
		        "D"); 
		final ComboBox tipoFasciaComboBox = new ComboBox (fascia);
		
	ObservableList <String> noleggio = 
			 FXCollections.observableArrayList 
			        ("giornaliero", 
			        "settimanale"
			        ); 
			final ComboBox tipoNoleggioComboBox = new ComboBox (noleggio);
		
	ObservableList <String> km = 
			 FXCollections.observableArrayList 
					        ("limitato", 
					        "illimitato"
					        ); 
					final ComboBox tipoKmComboBox = new ComboBox (km);
		
	
	/**
	 * Imposta gli eventi associati al pulsante annullaButton.
	 */
	@SuppressWarnings("unused")
	protected void setAnnullaButton() {
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
		par.add("I dati inseriti verranno non memorizzati.\nSicuro di voler continuare?");
		boolean controll;
		controll = (boolean) fc.processRequest("visualizzaMessaggio",par);
		return controll;
	}

	
	/**
	 * Imposta gli eventi associati al pulsante indietro.
	 */
	protected void setIndietroButton() {
		indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				richiestaIndietro();
			}
		});
		indietroButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					richiestaIndietro();
				}
			}
		});
	}

	/**
	 * Azione legata al pulsante indietroButton.
	 */
	private void richiestaIndietro() {
		if (avvisoAnnulla()) {
			indietro();
		}
	}

	/**
	 * Torna alla schermata precedente.
	 */
	protected void indietro() {
		fc.processRequest("homeDipendente");
	}
	
	/**
	 * Imposta gli eventi associati al pulsante conferma.
	 */
	@SuppressWarnings("unused")
	private void setConfermaButton() {
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
		boolean dataInizio = controlloDataInizio();
		boolean dataLimite = controlloDataLimite();
		boolean dataRestituzione = controlloDataRestituzione();
		boolean luogoRestituzione = controlloLuogo();
		boolean macchina = controlloMacchina();
		boolean tariffaBase = controlloTariffaBase();
		boolean costoKm = controlloCostoKm();
		boolean KmEffettivi = controlloKm();
		boolean prezzoExtra = controlloPrezzoExtra();
		boolean acconto = controlloAcconto();
		boolean costoTot = controlloCostoTot();
		
		if (dataInizio && dataLimite && dataRestituzione && luogoRestituzione &&
				 macchina && tariffaBase && costoKm && KmEffettivi && 
				 prezzoExtra && acconto && costoTot) {
			ArrayList<String> p = new ArrayList<String>();
			p.add(gd.parseData(this.dataInizioDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(gd.parseData(this.dataLimiteDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(gd.parseData(this.dataRestituzioneDatePicker.getCalendar(),
					GestioneDate.DATA_ANGLO));
			p.add(luogoRestituzioneTextField.getText());
			p.add(macchinaTextField.getText());
			p.add(tariffaBaseTextField.getText());
			p.add(costoKmTextField.getText());
			p.add(kmEffettiviTextField.getText());
			p.add(prezzoExtraTextField.getText());
			p.add(accontoTextField.getText());
			p.add(costoTotTextField.getText());
			boolean eseguito;
			eseguito = (boolean) fc.processRequest("compilaContratto", p);
			if(eseguito){
				inserimentoEffettuato();
				fc.processRequest("homeDipendente");
			} else {
				erroreInserimento();
			}
		}
	}


	protected boolean controlloKm() {
		String Km = this.kmEffettiviTextField.getPromptText();
		controller = new IntegerController();
		boolean controllaKm= controller.check(Km);
		if (controllaKm) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaKm;
	}


	protected boolean controlloCostoTot() {
		String costoTot = this.costoTotTextField.getText();
		controller = new ValutaController();
		boolean controllaCostoTot= controller.check(costoTot);
		if (controllaCostoTot) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCostoTot;
	}

	protected boolean controlloAcconto() {
		String acconto = this.accontoTextField.getText();
		controller = new ValutaController();
		boolean controllaAcconto = controller.check(acconto);
		if (controllaAcconto) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaAcconto;
	}

	protected boolean controlloPrezzoExtra() {
		String PrezzoExtra = this.prezzoExtraTextField.getText();
		controller = new ValutaController();
		boolean controllaPrezzoExtra = controller.check(PrezzoExtra);
		if (controllaPrezzoExtra) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaPrezzoExtra;
	}

	protected boolean controlloCostoKm() {
		String costoKm = this.costoKmTextField.getText();
		controller = new ValutaController();
		boolean controllaCostokm = controller.check(costoKm);
		if (controllaCostokm) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaCostokm;
	}

	protected boolean controlloTariffaBase() {
		String tariffa = this.tariffaBaseTextField.getText();
		controller = new ValutaController();
		boolean controllaTariffa = controller.check(tariffa);
		if (controllaTariffa) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaTariffa;
	}

	protected boolean controlloMacchina() {
		String macchina = this.macchinaTextField.getText();
		controller = new MacchinaController();
		boolean controllaMacchina = controller.check(macchina);
		if (controllaMacchina) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaMacchina;
	}

	protected boolean controlloLuogo() {
		String luogo = this.luogoRestituzioneTextField.getText();
		controller = new LuogoController();
		boolean controllaLuogo = controller.check(luogo);
		if (controllaLuogo) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return controllaLuogo;
	}

	protected boolean controlloDataInizio() {
		boolean isValid = (dataInizioDatePicker.getValue() != null);
		if (isValid) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return isValid;
	}
	
	protected boolean controlloDataLimite() {
		boolean isValid = (dataLimiteDatePicker.getValue() != null);
		if (isValid) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return isValid;
	}

	protected boolean controlloDataRestituzione() {
		boolean isValid = (dataInizioDatePicker.getValue() != null);
		if (isValid) {
			this.erroreNome.setVisible(false);
		}
		else{
			this.erroreNome.setVisible(true);
		}
		return isValid;
	}
	/**
	 * Mostra un messaggio di avvenuto inserimento.
	 */
	private void inserimentoEffettuato() {
		ArrayList<String> par = new ArrayList<>();
		par.add("0");
		par.add("Operazione Riuscita");
		par.add("Inserimento della gara avvenuto con successo!");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	/**
	 * Mostra un messaggio di errore scaturito dall'esecuzione del processo di
	 * iscrizione.
	 */
	private void erroreInserimento() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Fallita");
		par.add("Errore nell'iserimento della gara.\nRiprovare");
			fc.processRequest("visualizzaMessaggio", par);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		fc = new FrontController();
		setIndietroButton();
		setAnnullaButton();
		setConfermaButton();
	}
}
