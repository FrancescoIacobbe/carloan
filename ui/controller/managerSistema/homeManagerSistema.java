package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.controller.dipendente.homeDipendenteController;
import ui.tableModel.ClientiModel;
import ui.tableModel.ContrattiClienteModel;
import ui.tableModel.DipendenteModel;
import ui.tableModel.MacchineModel;
import ui.tableModel.RequisitoModel;
 
/**
 * controller interfaccia homeManagerSistema.fxml 
 * @author Antonella
 *
 */

public class homeManagerSistema  extends homeDipendenteController{

	@FXML
	private TableView<RequisitoModel> requisitoTableView;
	@FXML
	private TableColumn<RequisitoModel,  String > IDColTable;
	@FXML
	private TableColumn<RequisitoModel, String> descrizioneColTable;
	@FXML
	private TableColumn<RequisitoModel, Hyperlink> riepilogoRColTable;
	@FXML
	private TableView<DipendenteModel> dipendentiTableView;
	@FXML
	private TableColumn<DipendenteModel,  String > CFDipColTable;
	@FXML
	private TableColumn<DipendenteModel, String> cognomeDipColTable;
	@FXML
	private TableColumn<DipendenteModel, String> nomeDipColTable;
	@FXML
	private TableColumn<DipendenteModel, Hyperlink> riepilogoDColTable;
	@FXML 
	private Label benvenutoTitolo;
	@FXML 
	private Button ricercaRequisitoButton;
	@FXML
	private TextField ricercaRequisitoTextField;
	@FXML
	private Button ricercaDipendenteButton;
	@FXML 
	private Button inserisciDipendenteButton;
	@FXML 
	private Button inserisciMacchinaButton;
	@FXML 
	private Button inserisciRequisitoButton;
	@FXML 
	private TextField ricercaDipendenteTextField;
	
	private FrontControllerInt fc;
	
	protected void setTabellaCon(ArrayList<HashMap<String,String>> contratti) {
		ArrayList<ContrattiClienteModel> list = new ArrayList<ContrattiClienteModel>();
		for(HashMap<String,String> contratto: contratti) {
			String idCont = contratto.get("id");
			String data = contratto.get("data");
			
			
			ContrattiClienteModel m = new ContrattiClienteModel(idCont, data,false, true);
		}
		IDContrattoColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("ID"));
		dataColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("data"));
		riepilogoConColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,Hyperlink>("bottone"));
		contrattiTableView.getItems().setAll(list);
	}
	
	/**
	 * Imposta gli eventi associati al pulsante inserisci dipendente.
	 */
	private void setInserisciDipendenteButton() {
		inserisciDipendenteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inserisciDipendente();
			}
		});
		inserisciDipendenteButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					inserisciDipendente();
				}
			}
		});
	}
	
	/**
	 *   va alla schermata registra dipedente
	 */
	private void inserisciDipendente () {
		fc.processRequest("registraDipendente");
	}
	
	/**
	 * Imposta gli eventi associati al pulsante inserisci macchina.
	 */
	private void setInserisciMacchinaButton() {
		inserisciMacchinaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inserisciMacchina();
			}
		});
		inserisciMacchinaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					inserisciMacchina();
				}
			}
		});
	}
	
	/**
	 *   va alla schermata registra macchina
	 */
	private void inserisciMacchina () {
		fc.processRequest("registraMacchina");
	}
	
	/**
	 * Imposta gli eventi associati al pulsante inserisci requsito.
	 */
	private void setInserisciRequisitoButton() {
		inserisciRequisitoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				inserisciRequisito();
			}
		});
		inserisciRequisitoButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					inserisciRequisito();
				}
			}
		});
	}
	
	/**
	 *   va alla schermata registra requisito
	 */
	private void inserisciRequisito () {
		fc.processRequest("registraRequsito");
	}
	
	private void setRicercaRequisitoButton() {
		this.ricercaRequisitoButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaRequisito();
				}
			}
		});
		this.ricercaRequisitoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RicercaRequisito();
			}
		});
		this.ricercaRequisitoTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaRequisito();
				}
			}
		});
	}
	private void setRicercaDipendenteButton() {
		this.ricercaDipendenteButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaDipendente();
				}
			}
		});
		this.ricercaDipendenteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RicercaDipendente();
			}
		});
		this.ricercaDipendenteTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaDipendente();
				}
			}
		});
	}
	
	/**
	 * esegue la funzione di ricerca requisito in base all' ID immesso
	 * 
	 */
	private void RicercaRequisito(){
		String testo = this.ricercaRequisitoTextField.getText();
		ArrayList<String> p = new ArrayList<String>(1);
		p.add(testo.trim());
		ArrayList<HashMap<String, String>> dati = (ArrayList<HashMap<String, String>>) fc.processRequest("ricercaRequisito",p);
		
	}
	
	/**
	 * esegue la funzione di ricerca dipendente in base al CF immesso
	 * 
	 */
	private void RicercaDipendente(){
		String testo = this.ricercaDipendenteTextField.getText();
		ArrayList<String> p = new ArrayList<String>(1);
		p.add(testo.trim());
		ArrayList<HashMap<String, String>> dati = (ArrayList<HashMap<String, String>>) fc.processRequest("ricercaDipendente",p);	
	}
	

	/**
	 * Fornisce una rappresentazione matriciale della tabella correntemente visualizzata.
	 * @return la tabella in forma matriciale.
	 */
	private String[][] getTabellaReq() {
		ObservableList<RequisitoModel> elementi;
		String[][] tabellaReq = null;
		elementi = requisitoTableView.getItems();
		if (elementi.size() == 0) {
			creazioneNonPossibile();
		} else {
			int colonne = requisitoTableView.getColumns().size();
			tabellaReq = new String[elementi.size()][colonne];
		}
		if (tabellaReq != null) {
			int i = 0;
			for (RequisitoModel m : elementi) {
				tabellaReq[i] = m.toArray();
				i++;
			}
		}
		return tabellaReq;
	}

	/**
	 * Fornisce una rappresentazione matriciale della tabella correntemente visualizzata.
	 * @return la tabella in forma matriciale.
	 */
	private String[][] getTabellaDip() {
		ObservableList<DipendenteModel> elementi;
		String[][] tabellaDip = null;
		elementi = dipendentiTableView.getItems();
		if (elementi.size() == 0) {
			creazioneNonPossibile();
		} else {
			int colonne = dipendentiTableView.getColumns().size();
			tabellaDip = new String[elementi.size()][colonne];
		}
		if (tabellaDip != null) {
			int i = 0;
			for (DipendenteModel m : elementi) {
				tabellaDip[i] = m.toArray();
				i++;
			}
		}
		return tabellaDip;
	}
	
	private void setDati() {
		HashMap<String, String> dati;
		dati = (HashMap<String,String>) fc.processRequest("getUsernameMng");
		String username = dati.get("username");
		benvenutoTitolo.setText(benvenutoTitolo.getText() + " " + username);
		caricaTabellaContratti();
		caricaTabellaMacc();
		caricaTabellaCli();
		caricaTabellaDip();
		caricaTabellaReq();
	}
	
	/**
	 * Carica i dati della tabella Requsiti.
	 */
	private void caricaTabellaReq() {
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> requisiti;
		requisiti = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraTuttiRequisiti");
		setTabellaReq(requisiti);
	}
	
	/**
	 * Carica i dati della tabella Dipendenti.
	 */
	private void caricaTabellaDip() {
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> dipendenti;
		dipendenti = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraTuttiDipendenti");
		setTabellaDip(dipendenti);
	}
	
	private void setTabellaReq(ArrayList<HashMap<String,String>> requisiti) {
		ArrayList<RequisitoModel> list = new ArrayList<RequisitoModel>();
		for(HashMap<String,String> requisito: requisiti) {
			String id = requisito.get("id");
			String descrizione = requisito.get("descrizione");
			
			RequisitoModel m = new RequisitoModel(id, descrizione);
		}
		IDColTable.setCellValueFactory(new PropertyValueFactory<RequisitoModel,String>("ID"));
		descrizioneColTable.setCellValueFactory(new PropertyValueFactory<RequisitoModel,String>("descrizione"));
		riepilogoRColTable.setCellValueFactory(new PropertyValueFactory<RequisitoModel,Hyperlink>("bottone"));
		requisitoTableView.getItems().setAll(list);
	}

	
	private void setTabellaDip(ArrayList<HashMap<String,String>> dipendenti) {
		ArrayList<DipendenteModel> list = new ArrayList<DipendenteModel>();
		for(HashMap<String,String> dipendente: dipendenti) {
			String CF = dipendente.get("CF");
			String cognome = dipendente.get("cognome");
			String nome = dipendente.get("nome");
			
			DipendenteModel m = new DipendenteModel(CF, cognome, nome, false);
		}
		
		CFDipColTable.setCellValueFactory(new PropertyValueFactory<DipendenteModel,String>("CF"));
		cognomeDipColTable.setCellValueFactory(new PropertyValueFactory<DipendenteModel,String>("congome"));
		nomeDipColTable.setCellValueFactory(new PropertyValueFactory<DipendenteModel,String>("nome"));
		riepilogoDColTable.setCellValueFactory(new PropertyValueFactory<DipendenteModel,Hyperlink>("bottone"));		
		dipendentiTableView.getItems().setAll(list);
	}
	
	private void setTabellaMac(ArrayList<HashMap<String,String>> macchine) {
		ArrayList<MacchineModel> list = new ArrayList<MacchineModel>();
		for(HashMap<String,String> macchina: macchine) {
			String targa = macchina.get("targa");
			String modello = macchina.get("modello");
			String disponibilita = macchina.get("disponiblita");
			
			MacchineModel m = new MacchineModel(targa, modello, disponibilita, false);
		}
		targaColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("targa"));
		modelloColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("modello"));
		disponibilitaColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,String>("disponibilita"));
		riepilogoMColTable.setCellValueFactory(new PropertyValueFactory<MacchineModel,Hyperlink>("bottone"));
		macchineTableView.getItems().setAll(list);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fc = new FrontController();
		setDati();
		setRicercaContrattoButton();
		setricercaMacchinaButton();
		setricercaClienteButton();
		setRicercaRequisitoButton();
		setRicercaDipendenteButton();
		setInserisciRequisitoButton();
		setInserisciMacchinaButton();
		setInserisciDipendenteButton();
	}
}