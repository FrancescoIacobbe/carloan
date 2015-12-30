package ui.controller.cliente;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import presentazione.FrontController;
import presentazione.FrontControllerInt;
import ui.controller.RiepilogoContratto;


/**
 * Controller per l'interfaccia cliente.riepilogoContatto.fxml.
 * @author Antonella
 *
 */
public  class RiepilogoContrattoControlller  extends RiepilogoContratto {

	@FXML 
	private Label TitoloRiepilogoContratto;
	
	private FrontControllerInt fc;
	
	@Override
	protected void indietro() {
		// TODO Auto-generated method stub
			fc.processRequest("homeCliente");
	}


	@Override
	public void initialize(URL location,  ResourceBundle arg1) {
		fc= new FrontController();
		setIndietroButton();
		setPdfButton();
		getDati();
	}



}
