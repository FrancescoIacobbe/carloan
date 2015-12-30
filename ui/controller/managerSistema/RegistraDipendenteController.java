package ui.controller.managerSistema;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontControllerInt;
import ui.controller.RegistraController;
import utility.GestioneDate;

public class RegistraDipendenteController  extends RegistraController{
	private FrontControllerInt fc; 
	
	

	@Override
	protected void indietro() {
		// TODO Auto-generated method stub
				fc.processRequest("riepilogoDipendente");
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setConfermaButton();
		setAnnullaButton();
		setIndietroButton();
	}


	@Override
	protected void terminaRegistrazione() {
		
			boolean nome = controllaNome();
			boolean cognome = controllaCognome();
			boolean data = controllaData();
			boolean CF = controllaCodiceFiscale();
			boolean città = controllaCitta();
			boolean indirizzo = controllaIndirizzo();
			boolean CAP = controllaCAP();
			boolean numTel = controllaNumeroTel();
			boolean username = controllaUsername();
			boolean password = controllaPassword();
			boolean email = controllaEmail();
			boolean confermaPass = controllaConfermaPass();
			if (nome && cognome && data && CF  && città && indirizzo
					&& CAP && numTel && numTel && username && password && 
					email && confermaPass) {
				ArrayList<String> p = new ArrayList<String>();
				p.add(nomeTextField.getText());
				p.add(cognomeTextField.getText());
				GestioneDate gs = new GestioneDate();
				String dataNascita = gs.parseData(
						dataNascitaDatePicker.getCalendar(),
						GestioneDate.DATA_ANGLO);
				p.add(dataNascita);
				p.add(CFTextField.getText());
				p.add(cittaTextField.getText());
				p.add(indirizzoTextField.getText());
				p.add(CAPTextField.getText());
				p.add(numTelTextField.getText());
				p.add(usernameTextField.getText());
				p.add(passwordField.getText());
				p.add(emailTextField.getText());
				p.add(confermaPassField.getText());
				fc.processRequest("registraDipendente", p);
			}
		}
				
	}

