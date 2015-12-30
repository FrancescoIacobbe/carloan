package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import presentazione.FrontControllerInt;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.controller.RiepilogoUtente;

public class riepiologoDipendenteMng extends RiepilogoUtente{

	@FXML 
	private Button indietroButton;
	
	private FrontControllerInt fc;


	/**
	 * Imposta gli eventi associati al pulsante indietro.
	 */
	private void setIndietroButton() {
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
	 * Torna alla schermata precendente.
	 */
	 private  void indietro() {
		fc.processRequest("homeManegerSistema");
		
	}
	
	 protected void modificaAna() {
			if (!modificabile) {
				impossibileModificareAna();
			} else {
			fc.processRequest("modificaDatiDipendenteMng");
			
		}}
	 protected void modificaCre()  {
			if (!modificabile) {
				impossibileModificareAna();
			} else {
			fc.processRequest("modificaCredenzialiDipendenteMng");
			}
		}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setIndietroButton();
		setDatiUtente();
		setModificaAnaButton();
		setModificaCreButton();
		getDati();
		
	}


	@Override
	protected void setTabellaCon(ArrayList<HashMap<String, String>> contratti) {
		// TODO Auto-generated method stub
		
	}

}
