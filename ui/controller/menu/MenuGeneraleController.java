package ui.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.Stage;

/**
 * Controller per l'interfaccia menuGenerale.fxml.
 * 
 * @author Gianluca
 * 
 */
public class MenuGeneraleController implements Initializable {

	@FXML
	private MenuBar menuMenuBar;

	@FXML
	private MenuItem esciMenuItem;

	@FXML
	private Menu linguaMenu;

	private FrontControllerInt fc;

	/**
	 * Aggiorna la lingua del sistema se questa viene cambiata dal linguaMenu.
	 * 
	 * @param e
	 *            l'evento.
	 */
	public void aggiornaLingua(ActionEvent e) {
		String lingua = ((RadioMenuItem) e.getSource()).getText();
		fc.processRequest("aggiornaPane");
	}

	/**
	 * Interrompe l'esecuzione e chiude l'interfaccia.
	 * 
	 * @param e
	 *            l'evento.
	 */
	public void esci(ActionEvent e) {
		Stage stage = (Stage) this.menuMenuBar.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fc = new FrontController();
	}

}
