package presentazione;
import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.ApplicationControllerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utility.ReaderXML;
import utility.ReaderXMLStub;



public class ApplicationController implements ApplicationControllerInt {

	private ViewDispatcher dispatcher;
	private static final int CLASSE = 0;
	private static final int METODO = 1;
	private Object ritorno;
	private ReaderXML reader;
	
	public ApplicationController() {
		reader = new ReaderXML("/xml/AC.xml");
		this.ritorno = null;
	}

	@Override
	public Object handleRequest(String key, ArrayList<String> p) {
		// TODO Auto-generated method stub
		ArrayList<String> coppia = reader.leggiParametri(key);
		String classe = coppia.get(CLASSE);
		String metodo = coppia.get(METODO);
		Class<?> c;
		try {
			
			if (!classe.equals("null") && !metodo.equals("null")) {
				c = Class.forName(classe);
				Object o = c.newInstance();
				Method m;
				if (p != null) {
					m = c.getDeclaredMethod(metodo, p.getClass());
					m.setAccessible(true);
					ritorno = m.invoke(o, p);
				} else {
					m = c.getDeclaredMethod(metodo);
					m.setAccessible(true);
					ritorno = m.invoke(o);
				}
			} else {
				ritorno = p;
			}
			ritorno = handleResponse(key, ritorno);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			ApplicationControllerException ex = new ApplicationControllerException(
					e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
		return ritorno;
	}

	@SuppressWarnings("unchecked")
	private Object handleResponse(String request, Object response) {
		Object result = null;
		ArrayList<String> p;
		switch (request) {
		case "login":
			result = login(response);
			break;
		case "avvio":
			dispatcher = ViewDispatcher.getIstance();
			result = dispatcher.avvio();
			break;
		case "caricaInterfaccia":
			p = ((ArrayList<String>) response);
			String view = p.get(0);
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(view);
			break;
		case "modificaDatiCliente":
		case "modificaDatiDipendente":
		case "modificaCredenzialiDipendente":
		case "modificaCredenzialiCliente":
		case "homeCliente":
		case "homeDipendente":
		case "modificaContratto":
		case "homeManagerSistema":
		case "modificaMacchina":
		case "modificaRequisito":
		case "modificaDatiClienteDip":
		case "modificaCredenzialiClienteDip" :
		case "modificaDatiDipendenteMng":
		case "modificaCredenzialiDipendenteMng":
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "usernameDisponibile":
		case "emailDisponibile":
		case "ricercaContratti":
		case "getUsernameCliente":
		case "compilaContratto":
		case "registraCliente":
		case "ricercaMacchine":
		case "ricercaClienti":
		case "mostraTuttiContratti":
		case "getUsernameDipendente":
		case "registraDipendente":
		case "registraMacchina":
		case "registraRequisito":
		case "ricercaRequisito":
		case "ricercaDipendente":
		case "getUsernameManager":
		case "mostraTuttiRequisiti":
		case "mostraTuttiDipendenti":
		case "mostraTutteMacchine":
		case "mostraTuttiClienti":
		case "eliminaMacchina":
		case "getDatiCliente":
		case "mostraStorico": 
		case "getAnagraficaCliente": 
		case "setAnagraficaCliente":
		case "getAccountCliente":
		case "setAccountCliente":
		case "getDatiContratto":
		case "setMacchinaAllocata":
		case "setDatiContratto":
		case "setTariffaBaseAllocato":
		case "setPrezzoExtraAllocato":
		case "setCostoTotAllocato":
		case "getDatiDipendente":
		case "getContrattiDipendendente": 
		case "getAccountDipendente": 
		case "setAccountDipendente":
		case "setDipendente":
		case "getDipendente":
		case "getAccountManager":
		case "setAccountManager":
		case "getDatiRequisito":
		case "setDatiRequisito":
		case "getDatiMacchina":
		case "setDatiMacchina":
		case "getAnagraficaDipendente": 
		
			result = response;
			break;
		case "logout":
			logout();
			break;
		case "visualizzaMessaggio":
			dispatcher = ViewDispatcher.getIstance();
			int i = 0;
			p = (ArrayList<String>) response;
			int code = Integer.parseInt((p.get(i++)));
			String title = p.get(i++);
			String message = p.get(i++);
			result = dispatcher.showMessage(code, title, message);
			// aggiunto il result per i messaggi con richiesta di conferma
			break;
		case "aggiornaPane":
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.aggiornaPane();
			break;
		case "salvaPdf":
			dispatcher = ViewDispatcher.getIstance();
			result = dispatcher.showFileChooser(ViewDispatcher.SAVE_DIALOG,
					"pdf");
			break;
		case "riepilogoContrattoDip":
		case "riepilogoContrattoMng":
		case "riepilogoContrattoClie":
			p = (ArrayList<String>) response;
			if (p != null) {
				GestioneSessione.setIdContrattoCorrente(p.get(0));
			}
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "riepiologoClienteDip":
			p = (ArrayList<String>) response;
			if (p != null) {
				GestioneSessione.setIdClienteCorrente(p.get(0));
			}
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "riepiologoDipendenteMng":
			p = (ArrayList<String>) response;
			if (p != null) {
				GestioneSessione.setIdDipendenteCorrente(p.get(0));
			}
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "riepilogoRequisito":
			p = (ArrayList<String>) response;
			if (p != null) {
				GestioneSessione.setIdRequisitoCorrente(p.get(0));
			}
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "riepiologoMacchineDip":
		case "riepiologoMacchineMng":
			p = (ArrayList<String>) response;
			if (p != null) {
				GestioneSessione.setIdMacchinaCorrente(p.get(0));
			}
			dispatcher = ViewDispatcher.getIstance();
			dispatcher.setPane(request);
			break;
		case "inputDialog": 
			p = (ArrayList<String>) response;
			if (p != null) {
				title = p.get(0);
				message = p.get(1);
				dispatcher = ViewDispatcher.getIstance();
				result = dispatcher.showInputDialog(title, message);
			}
			break;
		default: break;
		}
		return result;
	}

	
	private boolean login(Object response) {
		@SuppressWarnings("unchecked")
		ArrayList<String> param = (ArrayList<String>) response;
		String tipo = param.get(0);
		String username = param.get(1);
		boolean login = false;
		if (tipo != null) {
			GestioneSessione.setUsernameUtenteCorrente(username);
			dispatcher = ViewDispatcher.getIstance();
			if (tipo.equalsIgnoreCase("ManagerSistema")) {
				dispatcher.setInterfaccia("menuGenerale","homeManagerSistema");
			} else if (tipo.equalsIgnoreCase("Dipendente")) {
				dispatcher.setInterfaccia("menuGenerale","homeDipendente");
			} else {
				// è un partecipante
				dispatcher.setInterfaccia("menuGenerale","homeCliente");
			}
			login = true;
		}
		return login;
	}
	/**
	 * Esegue il logout dal sistema.
	 */
	private void logout() {
		GestioneSessione.azzera();
		dispatcher = ViewDispatcher.getIstance();
		dispatcher.setInterfaccia("menuGenerale", "benvenuto");
	}

	@Override
	public void setReader(ReaderXMLStub readerXMLStub) {
		// TODO Auto-generated method stub
		reader = readerXMLStub;
	}
	
}
