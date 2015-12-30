package ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * Controller dell'interfaccia benvenuto.fxml.
 * 
 * @author Gianluca
 * 
 */
public class benvenutoController implements Initializable {
	
	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Label titoloBenveuto;

	@FXML
	private Label erroreLogin;

	private FrontControllerInt fc;

	/**
	 * Imposta cosa eseguire se si verificano determinanti eventi sul pulsante
	 * loginButton
	 */
	private void setLoginButton() {
		this.loginButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					login();
				}
			}
		});
		this.loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				login();
			}
		});
		this.passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().getName().equals("Enter")) {
					login();
				}
			}
		});
	}

	/**
	 * Invia una richiesta di login al front controller.
	 */
	private void login() {
		ArrayList<String> data = new ArrayList<String>();
		data.add(this.usernameTextField.getText());
		data.add(this.passwordField.getText());
		 fc.processRequest("login", data);
				
		
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// inizializzo il Front Controller
		fc = new FrontController();
		// Imposto gli eventi associati ai pulsanti
		this.setLoginButton();
	}
}
