package ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import presentazione.FrontControllerInt;
import ui.tableModel.ContrattiClienteModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public  abstract class RiepilogoUtente  implements Initializable{
	@FXML 
	protected Label nomeLabel;
	@FXML 
	protected Label cognomeLabel;
	@FXML 
	protected Label CFLabel;
	@FXML
	protected Label dataNascitaLabel;
	@FXML
	protected Label indirizzoLabel;
	@FXML 
	protected Label citt‡Label;
	@FXML
	protected Label CAPLabel;
	@FXML
	protected Label numeroTelefonoLabel;
	@FXML
	protected Label usernameLabel;
	@FXML
	protected Label passwordLabel;
	@FXML 
	protected Label emailLabel;
	@FXML
	protected Label nome;
	@FXML 
	protected Label cognome;
	@FXML 
	protected Label CF;
	@FXML
	protected Label dataNascita;
	@FXML
	protected Label indirizzo;
	@FXML 
	protected Label citt‡;
	@FXML
	protected Label CAP;
	@FXML
	protected Label numTel;
	@FXML
	protected Label username;
	@FXML
	protected Label password;
	@FXML 
	protected Label email;
	@FXML
	protected Button modAnagraficaButton;
	@FXML
	protected Button modCredenzialiButton;
	
	protected boolean modificabile;
	
	protected FrontControllerInt fc;

	@FXML
	protected TableView<ContrattiClienteModel> contrattiTableView;
	@FXML
	protected TableColumn<ContrattiClienteModel,  String > IDContrattoColTable;
	@FXML
	protected TableColumn<ContrattiClienteModel, String> dataColTable;
	@FXML
	protected TableColumn<ContrattiClienteModel, Hyperlink> riepilogoConColTable;
	
	protected Button ricercaContrattoButton;
	
	@FXML 
	private TextField ricercaTextField;

	
	/**
	 * Fornisce una rappresentazione matriciale della tabella  contratti correntemente visualizzata.
	 * @return la tabella in forma matriciale.
	 */
	protected String[][] getTabellaCon() {
		ObservableList<ContrattiClienteModel> elementi;
		String[][] tabellaCon = null;
		elementi = contrattiTableView.getItems();
		if (elementi.size() == 0) {
			creazioneNonPossibile();
		} else {
			int colonne = contrattiTableView.getColumns().size();
			tabellaCon = new String[elementi.size()][colonne];
		}
		if (tabellaCon != null) {
			int i = 0;
			for (ContrattiClienteModel m : elementi) {
					tabellaCon[i] = m.toArray();
					i++;
			}
		}
		return tabellaCon;
	}
	
	/**
	 * Mostra un messaggio di avviso di warning per l'operazione richiesta.
	 */
	protected void creazioneNonPossibile() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Impossibile creare il report di una tabella vuota");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	protected abstract void setTabellaCon(ArrayList<HashMap<String,String>> contratti);
	
	
	
	protected void setRicercaContrattoButton() {
		this.ricercaContrattoButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaContratto();
				}
			}
		});
		this.ricercaContrattoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				RicercaContratto();
			}
		});
		this.ricercaTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					RicercaContratto();
				}
			}
		});
	}
	
	/**
	 * esegue la funzione di ricerca contratto in base all'Id immesso
	 * 
	 */
	protected void RicercaContratto(){
		String testo = this.ricercaTextField.getText();
		ArrayList<String> p = new ArrayList<String>(1);
		p.add(testo.trim());
		ArrayList<HashMap<String, String>> dati = (ArrayList<HashMap<String, String>>) fc.processRequest("ricercaContratti",p);
		
	}

	/**
	 * Imposta gli eventi associati al pulsante modifica anagrafica.
	 */

	protected void setModificaAnaButton() {
		this.modAnagraficaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modificaAna();
			}
		});
		this.modAnagraficaButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					// invia richiesta di stampa
					modificaAna();
				}
			}
		});
	}
	protected abstract void modificaAna();
	
	/**
	 * Avvisa l'utente che non Ë possibile apportare modifiche.
	 */
	protected void impossibileModificareAna() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Non Ë possibile apportare modifiche all'iscrizione per la competizione selezionata.\n"
				+ "Il termine ultimo per apportare modifiche alle iscrizioni Ë scaduto.");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	/**
	 * Imposta gli eventi associati al pulsante modifica credenziali.
	 */
	protected void setModificaCreButton() {
		this.modCredenzialiButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				modificaCre();
			}
		});
		this.modCredenzialiButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					// invia richiesta di stampa
					modificaCre();
				}
			}
		});
	}
	protected abstract void modificaCre();
	
	/**
	 * Avvisa l'utente che non Ë possibile apportare modifiche.
	 */
	protected void impossibileModificareCre() {
		ArrayList<String> par = new ArrayList<>();
		par.add("1");
		par.add("Operazione Non Consentita");
		par.add("Non Ë possibile apportare modifiche all'iscrizione per la competizione selezionata.\n"
				+ "Il termine ultimo per apportare modifiche alle iscrizioni Ë scaduto.");
		fc.processRequest("visualizzaMessaggio", par);
	}
	
	/**
	 * Raccoglie in un {@link HashMap} le informazioni sul contratto
	 * @return un {@link HashMap} con le informazioni.
	 */
	protected HashMap<String, String> getDati() {
		LinkedHashMap<String,String> dati = new LinkedHashMap<String, String>();
		dati.put(nome.getText(), nomeLabel.getText());
		dati.put(cognome.getText(), cognomeLabel.getText());
		dati.put(CF.getText(), CFLabel.getText() );
		dati.put(dataNascita.getText(),dataNascitaLabel.getText());
		dati.put(indirizzo.getText(), indirizzoLabel.getText());
		dati.put(citt‡.getText(), citt‡Label.getText());
		dati.put(CAP.getText(), CAPLabel.getText());
		dati.put(numTel.getText(), numeroTelefonoLabel.getText());
		dati.put(username.getText(), usernameLabel.getText());
		dati.put(email.getText(), emailLabel.getText());
		dati.put(password.getText(), passwordLabel.getText());
		return dati;
	}
	
	@SuppressWarnings("unchecked")
	protected void setDatiUtente() {
		HashMap<String, String> dati;
		dati = (HashMap<String, String>) fc.processRequest("mostraAreaPersonaleUtente");
		this.nomeLabel.setText(dati.get("nome"));
		this.cognomeLabel.setText(dati.get("cognome"));
		this.CFLabel.setText(dati.get("CodiceFiscale"));
		this.dataNascitaLabel.setText(dati.get("dataNascita"));
		this.indirizzoLabel.setText(dati.get("indirizzo"));
		this.citt‡Label.setText(dati.get("citt‡"));
		this.CAPLabel.setText(dati.get("cap"));
		this.emailLabel.setText(dati.get("mail"));
		this.numeroTelefonoLabel.setText(dati.get("numTel"));
		this.usernameLabel.setText(dati.get("username"));
		this.passwordLabel.setText(dati.get("pass"));
		modificabile = dati.get("modificabile").equalsIgnoreCase("true");
		boolean conclusa = dati.get("conclusa").equalsIgnoreCase("true");
		if (conclusa) {
			this.modAnagraficaButton.setVisible(false);
			this.modCredenzialiButton.setVisible(false);
		}
		
	}
	public abstract void initialize(URL location, ResourceBundle resources);

}
