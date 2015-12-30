package ui.controller.dipendente;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.StampaException;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import utility.pdf.GestionePdf;
import utility.stampa.GestioneStampa;
import ui.tableModel.MacchineModel;
import ui.tableModel.ContrattiClienteModel;
import ui.tableModel.ClientiModel;
import ui.controller.RiepilogoUtente;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
 
/**
 * Controller dell'interfaccia homedipendente.fxml.
 * @author 
 * 
 */
 public class homeDipendenteController extends RiepilogoUtente {
	
	@FXML
	protected TableView<MacchineModel> macchineTableView;
	@FXML
	protected TableColumn<MacchineModel,  String > targaColTable;
	@FXML
	protected TableColumn<MacchineModel, String> modelloColTable;
	@FXML
	protected TableColumn<MacchineModel, String> disponibilitaColTable;
	@FXML
	protected TableColumn<MacchineModel, Hyperlink> riepilogoMColTable;
	@FXML
	private TableView<ClientiModel> clientiTableView;
	@FXML
	private TableColumn<ClientiModel,  String > CFColTable;
	@FXML
	private TableColumn<ClientiModel, String> cognomeColTable;
	@FXML
	private TableColumn<ClientiModel, String> nomeColTable;
	@FXML
	private TableColumn<ClientiModel, Hyperlink> riepilogoCColTable;
	
	@FXML 
	private Label benvenutoTitolo;
	@FXML
 
	private Button mostraButton;
	@FXML 
	protected Button ricercaMacchinaButton;
	@FXML
	protected TextField ricercaMacchinaTextField;
	@FXML 
	private Button ricercaClienteButton;
	@FXML
	private TextField ricercaClienteTextField;
	@FXML 
	private Button registraCliente;
	@FXML 
	private Button compilaContratto;
		
	private FrontControllerInt fc;
	
	protected void setTabellaCon(ArrayList<HashMap<String,String>> contratti) {
		ArrayList<ContrattiClienteModel> list = new ArrayList<ContrattiClienteModel>();
		for(HashMap<String,String> contratto: contratti) {
			String idCont = contratto.get("id");
			String data = contratto.get("data");
			
			
			ContrattiClienteModel m = new ContrattiClienteModel(idCont, data,false, false);
		}
		IDContrattoColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("ID"));
		dataColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("data"));
		riepilogoConColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,Hyperlink>("bottone"));
		contrattiTableView.getItems().setAll(list);
	}
	
	/**
	 * Imposta gli eventi associati al pulsante registra cliente.
	 */
	private void setRegistraClienteButton() {
		registraCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				registraCliente();
			}
		});
		registraCliente.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					registraCliente();
				}
			}
		});
	}
	
	/**
	 *   va alla schermata registra client.
	 */
	private void registraCliente() {
		fc.processRequest("registraCliente");
		
	}
	
	/**
	 * Imposta gli eventi associati al pulsante compila contratto.
	 */
	private void setCompilaContrattoButton() {
		compilaContratto.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				compilaContratto();
			}
		});
		compilaContratto.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					compilaContratto();
				}
			}
		});
	}
	
	/**
	 *   va alla schermata compila Contratto.
	 */
	private void compilaContratto () {
		fc.processRequest("compilaContratto");
	}
	
	protected void setricercaMacchinaButton() {
		this.ricercaMacchinaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaMacchina();
				}
			}
		});
		this.ricercaMacchinaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RicercaMacchina();
			}
		});
		this.ricercaMacchinaTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaMacchina();
				}
			}
		});
	}
	protected void setricercaClienteButton() {
		this.ricercaClienteButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaCliente();
				}
			}
		});
		this.ricercaClienteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RicercaCliente();
			}
		});
		this.ricercaClienteTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaCliente();
				}
			}
		});
	}
	
	/**
	 * esegue la funzione di ricerca macchina in base alla targa immesso
	 * 
	 */
	private void RicercaMacchina(){
		String testo = this.ricercaMacchinaTextField.getText();
		ArrayList<String> p = new ArrayList<String>(1);
		p.add(testo.trim());
		ArrayList<HashMap<String, String>> dati = (ArrayList<HashMap<String, String>>) fc.processRequest("ricercaMacchine",p);
	}
	
	/**
	 * esegue la funzione di ricerca cliente in base al CF immesso
	 * 
	 */
	private void RicercaCliente(){
		String testo = this.ricercaClienteTextField.getText();
		ArrayList<String> p = new ArrayList<String>(1);
		p.add(testo.trim());
		ArrayList<HashMap<String, String>> dati = (ArrayList<HashMap<String, String>>) fc.processRequest("ricercaClienti",p);

	}
	
	
	/**
	 * Fornisce una rappresentazione matriciale della tabella correntemente visualizzata.
	 * @return la tabella in forma matriciale.
	 */
	private String[][] getTabellaMac() {
		ObservableList<MacchineModel> elementi;
		String[][] tabellaMac = null;
		elementi = macchineTableView.getItems();
		if (elementi.size() == 0) {
			creazioneNonPossibile();
		} else {
			int colonne = macchineTableView.getColumns().size();
			tabellaMac = new String[elementi.size()][colonne];
		}
		if (tabellaMac != null) {
			int i = 0;
			for (MacchineModel m : elementi) {
					tabellaMac[i] = m.toArray();
					i++;
			}
		}
		return tabellaMac;
	}
	
	/**
	 * Fornisce una rappresentazione matriciale della tabella correntemente visualizzata.
	 * @return la tabella in forma matriciale.
	 */
	private String[][] getTabellaClie() {
		ObservableList<ClientiModel> elementi;
		String[][] tabellaCli = null;
		elementi = clientiTableView.getItems();
		if (elementi.size() == 0) {
			creazioneNonPossibile();
		} else {
			int colonne = clientiTableView.getColumns().size();
			tabellaCli = new String[elementi.size()][colonne];
		}
		if (tabellaCli != null) {
			int i = 0;
			for (ClientiModel m : elementi) {
					tabellaCli[i] = m.toArray();
					i++;
			}
		}
		return tabellaCli;
	}
	
	/**
	 * Mostra un messaggio di warning di errore nella generazione nel report.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	private void erroreGenerazioneReport() throws SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Riuscita");
		par.add("Errore nella creazione del file di report");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	/**
	 * Carica i dati dell'interfaccia.
	 */
	@SuppressWarnings("unchecked")
	private void setDati() {
		HashMap<String, String> dati;
		dati = (HashMap<String,String>) fc.processRequest("getUsernameDipendente");
		String username = dati.get("username");
		benvenutoTitolo.setText(benvenutoTitolo.getText() + " " + username);
		caricaTabellaContratti();
		caricaTabellaMacc();
		caricaTabellaCli();
	}
	
	/**
	 * Carica i dati della tabella Contratti.
	 */
	protected void caricaTabellaContratti() {
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> contratti;
		contratti = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraTuttiContratti");
		setTabellaCon(contratti);
	}
	
	/**
	 * Carica i dati della tabella Macchine.
	 */
	protected void caricaTabellaMacc() {
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> macchine;
		macchine = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraTutteMacchine");
		setTabellaMac(macchine);
	}
	
	/**
	 * Carica i dati della tabella Clienti.
	 */
	protected void caricaTabellaCli() {
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> clienti;
		clienti = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraTuttiClienti");
		setTabellaCli(clienti);
	}
	
	
	private void setTabellaMac(ArrayList<HashMap<String,String>> macchine) {
		ArrayList<MacchineModel> list = new ArrayList<MacchineModel>();
		for(HashMap<String,String> macchina: macchine) {
			String targa = macchina.get("targa");
			String modello = macchina.get("modello");
			String disponibilita = macchina.get("disponiblita");
			
			MacchineModel m = new MacchineModel(targa, modello, disponibilita, true);
		}
		targaColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("targa"));
		modelloColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("modello"));
		disponibilitaColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("disponibilita"));
		riepilogoMColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,Hyperlink>("bottone"));
		macchineTableView.getItems().setAll(list);
	}

	
	private void setTabellaCli(ArrayList<HashMap<String,String>> clienti) {
		ArrayList<ClientiModel> list = new ArrayList<ClientiModel>();
		for(HashMap<String,String> cliente: clienti) {
			String CF = cliente.get("CF");
			String cognome = cliente.get("cognome");
			String nome = cliente.get("nome");
			
			ClientiModel m = new ClientiModel(CF, cognome, nome, false);
		}
		
		CFColTable.setCellValueFactory(new PropertyValueFactory<ClientiModel,String>("CF"));
		cognomeColTable.setCellValueFactory(new PropertyValueFactory<ClientiModel,String>("congome"));
		nomeColTable.setCellValueFactory(new PropertyValueFactory<ClientiModel,String>("nome"));
		riepilogoCColTable.setCellValueFactory(new PropertyValueFactory<ClientiModel,Hyperlink>("bottone"));
		clientiTableView.getItems().setAll(list);
	}
	
	protected void modificaAna() {
		if (!modificabile) {
			impossibileModificareAna();
		} else {
		fc.processRequest("modificaDatiDipendente");
		
	}}
	protected void modificaCre()  {
		if (!modificabile) {
			impossibileModificareAna();
		} else {
		fc.processRequest("modificaCredenzialiDipendente");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fc = new FrontController();
		setDati();
		setDatiUtente();
		setRicercaContrattoButton();
		setricercaMacchinaButton();
		setricercaClienteButton();
		setModificaAnaButton();
		setModificaCreButton();
		getDati();
		setCompilaContrattoButton();
		setRegistraClienteButton();
	}
	 
}

 