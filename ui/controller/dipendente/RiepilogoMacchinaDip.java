package ui.controller.dipendente;

import java.net.URL;
import java.util.ResourceBundle;

import presentazione.FrontController;
import ui.controller.RiepilogoMacchina;

public class RiepilogoMacchinaDip  extends RiepilogoMacchina{

	@Override
	protected void indietro() {
		// TODO Auto-generated method stub
		fc.processRequest("homeDipendente");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fc=new FrontController();
		setIndietroButton();
		getDati();
	}

}
