package ui.controller.cliente;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.StampaException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import utility.pdf.GestionePdf;
import utility.stampa.GestioneStampa;
import ui.controller.RiepilogoUtente;
import ui.tableModel.ContrattiClienteModel;
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
 * Controller dell'interfaccia home.fxml.
 * @author  Antonella
 * 
 */
 public class homeClienteController extends RiepilogoUtente {
	
	
	@FXML 
	private Label titoloBen;
	@FXML
	
	
	/**
	 * intestazione tabella
	 */
	private String[] intestazione = {"ID","Data"};
	
	private FrontControllerInt fc;
	
	
	/**
	 * Carica i dati dell'interfaccia.
	 */
	@SuppressWarnings("unchecked")
	private void setDati() {
		HashMap<String, String> dati;
		dati = (HashMap<String,String>) fc.processRequest("getUsernameCliente");
		String username = dati.get("username");
		titoloBen.setText(titoloBen.getText() + " " + username);
		caricaTabellaContratti();
	}
	
	/**
	 * Carica i dati della tabella Prossime Competizioni.
	 */
	@SuppressWarnings("unchecked")
	private void caricaTabellaContratti() {
		ArrayList<HashMap<String, String>> contratti;
		contratti = (ArrayList<HashMap<String, String>>) fc.processRequest("mostraStorico");
		setTabellaCon(contratti);
	}
	
	protected void setTabellaCon(ArrayList<HashMap<String,String>> contratti) {
		ArrayList<ContrattiClienteModel> list = new ArrayList<ContrattiClienteModel>();
		for(HashMap<String,String> contratto: contratti) {
			String idCont = contratto.get("id");
			String data = contratto.get("data");
			
			
			ContrattiClienteModel m = new ContrattiClienteModel(idCont, data,true, false);
		}
		IDContrattoColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("IdCon"));
		dataColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,String>("data"));
		riepilogoConColTable.setCellValueFactory(new PropertyValueFactory<ContrattiClienteModel,Hyperlink>("bottone"));
		contrattiTableView.getItems().setAll(list);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fc = new FrontController();
		setDati();
		setDatiUtente();
		setRicercaContrattoButton();
		setModificaAnaButton();
		setModificaCreButton();
		getDati();
	}

	@Override
	protected void modificaAna() {
		if (!modificabile) {
			impossibileModificareAna();
		} else {
		fc.processRequest("modificaDatiCliente");
		
	}
		
	}

	@Override
	protected void modificaCre()  {
		if (!modificabile) {
			impossibileModificareAna();
		} else {
		fc.processRequest("modificaCredenzialiCliente");
		}
	}
	 
}

 