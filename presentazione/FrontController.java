package presentazione;


import java.util.ArrayList;


/**
 * Implementazione dell'interfaccia FrontControllerInt.
 * 
 * @author Gianluca
 * 
 */
public class FrontController implements FrontControllerInt {

	private ApplicationControllerInt ac;
	private Object ritorno;

	/**
	 * Costruttore principale.
	 */
	public FrontController() {
		this.ac = new ApplicationController();
		this.ritorno = null;
	}

	@Override
	public Object processRequest(String key, ArrayList<String> p) {
		ritorno = ac.handleRequest(key,p);
		return ritorno;
	}

	@Override
	public Object processRequest(String key) {
		ritorno = ac.handleRequest(key,null);
		return ritorno;

	}
}

