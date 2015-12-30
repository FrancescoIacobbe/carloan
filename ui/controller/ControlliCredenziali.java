package ui.controller;

import java.util.ArrayList;

import presentazione.FrontControllerInt;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import utility.controller.Controller;
import utility.controller.EmailController;
import utility.controller.PasswordController;
import utility.controller.UsernameController;

/**
 * Classe di supporto alle interfacce, effettua i controlli sui dati account.
 * @author Gaetano
 *
 */
public class ControlliCredenziali {
	
	@FXML
	protected ImageView okEmailImageView;

	@FXML
	protected ImageView okConfermaEmailImageView;

	@FXML
	protected ImageView okPasswordImageView;

	@FXML
	protected ImageView okConfermaPasswordImageView;

	@FXML
	protected ImageView errorEmailImageView;

	@FXML
	protected ImageView errorConfermaEmailImageView;

	@FXML
	protected ImageView errorPasswordImageView;

	@FXML
	protected ImageView errorConfermaPasswordImageView;

	@FXML
	protected TextField emailTextField;

	@FXML
	protected TextField confermaEmailTextField;

	@FXML
	protected PasswordField passwordTextField;

	@FXML
	protected PasswordField confermaPasswordTextField;

	@FXML
	protected Label erroreEmailDuplicata;

	@FXML
	protected Label erroreEmailNonValida;

	@FXML
	protected Label erroreConfermaEmail;

	@FXML
	protected Label errorePasswordNonValida;

	@FXML
	protected Label erroreConfermaPassword;
	
	@FXML
	protected ImageView okUsernameImageView;

	@FXML
	protected ImageView errorUsernameImageView;

	@FXML
	protected TextField usernameTextField;

	@FXML
	protected Label erroreUsernameDuplicato;

	@FXML
	protected Label erroreCaratteriUsername;
	
	protected String userEmail;
	
	protected Controller controller;
	
	protected FrontControllerInt fc;
	
	/**
	 * Controlla la correttezza dell'username inserito.
	 * 
	 * @return true sse è corretto.
	 */
	protected boolean controllaUsername() {
		String user = this.usernameTextField.getText();
		controller = new UsernameController();
		boolean controlloUsername = controller.check(user);
		if (controlloUsername) {
			ArrayList<String> p = new ArrayList<>(1);
			p.add(user);
			controlloUsername = (boolean) fc.processRequest(
						"usernameDisponibile", p);
			if (controlloUsername) {
				this.erroreCaratteriUsername.setVisible(false);
				this.erroreUsernameDuplicato.setVisible(false);
				this.errorUsernameImageView.setVisible(false);
				this.okUsernameImageView.setVisible(true);
			} else {
				this.okUsernameImageView.setVisible(false);
				this.erroreCaratteriUsername.setVisible(false);
				this.erroreUsernameDuplicato.setVisible(true);
				this.errorUsernameImageView.setVisible(true);
			}
		} else {
			this.okUsernameImageView.setVisible(false);
			this.errorUsernameImageView.setVisible(true);
			this.erroreCaratteriUsername.setVisible(true);
		}
		return controlloUsername;
	}
	
	/**
	 * Controlla la correttezza della mail inserita.
	 * 
	 * @return true sse è corretta
	 */
	protected boolean controllaEmail() {
		String email = this.emailTextField.getText();
		controller = new EmailController();
		boolean conrollaConfermaEmail = controller.check(email);
		if (conrollaConfermaEmail) {
			ArrayList<String> p = new ArrayList<>(1);
			p.add(email);
			conrollaConfermaEmail = (boolean) fc.processRequest(
						"emailDisponibile", p);
			if (conrollaConfermaEmail) {
				this.erroreEmailDuplicata.setVisible(false);
				this.erroreEmailNonValida.setVisible(false);
				this.errorEmailImageView.setVisible(false);
				this.erroreEmailNonValida.setVisible(false);
				this.okEmailImageView.setVisible(true);
			} else {
				this.erroreEmailNonValida.setVisible(false);
				this.okEmailImageView.setVisible(false);
				this.erroreEmailDuplicata.setVisible(true);
				this.errorEmailImageView.setVisible(true);
			}
		} else {
			okEmailImageView.setVisible(false);
			errorEmailImageView.setVisible(true);
			erroreEmailNonValida.setVisible(true);
		}
		return conrollaConfermaEmail;
	}

	/**
	 * Verifica che la mail di conferma corrisponda.
	 * 
	 * @return true sse le due mail corrispondono e sono corrette.
	 */
	protected boolean controllaConfermaEmail() {
		String email = this.emailTextField.getText();
		String confermaEmail = this.confermaEmailTextField.getText();
		boolean controllaConfermaEmail = (controllaEmail())
				&& (email.equalsIgnoreCase(confermaEmail));
		if (controllaConfermaEmail) {
			this.errorConfermaEmailImageView.setVisible(false);
			this.erroreConfermaEmail.setVisible(false);
			this.okConfermaEmailImageView.setVisible(true);
		} else {
			this.okConfermaEmailImageView.setVisible(false);
			this.errorConfermaEmailImageView.setVisible(true);
			this.erroreConfermaEmail.setVisible(true);
		}
		return controllaConfermaEmail;
	}

	/**
	 * Controlla la correttezza della password inserita.
	 * 
	 * @return true sse è corretta.
	 */
	protected boolean controllaPassword() {
		String password = this.passwordTextField.getText();
		controller = new PasswordController();
		boolean controllaPassword = controller.check(password);
		if (controllaPassword) {
			this.errorePasswordNonValida.setVisible(false);
			this.errorPasswordImageView.setVisible(false);
			this.okPasswordImageView.setVisible(true);
		} else {
			this.okPasswordImageView.setVisible(false);
			this.errorPasswordImageView.setVisible(true);
			this.errorePasswordNonValida.setVisible(true);
		}
		return controllaPassword;
	}

	/**
	 * Verifica che la password di conferma corrisponda.
	 * 
	 * @return true sse le due password correispondono e sono corrette.
	 */
	protected boolean controllaConfermaPassword() {
		String password = this.passwordTextField.getText();
		String confermaPassword = this.confermaPasswordTextField.getText();
		boolean controllaConfermaPassword = (controllaPassword() && password
				.equals(confermaPassword));
		if (controllaConfermaPassword) {
			this.erroreConfermaPassword.setVisible(false);
			this.errorConfermaPasswordImageView.setVisible(false);
			this.okConfermaPasswordImageView.setVisible(true);
		} else {
			this.okConfermaPasswordImageView.setVisible(false);
			this.errorConfermaPasswordImageView.setVisible(true);
			this.erroreConfermaPassword.setVisible(true);
		}
		return controllaConfermaPassword;
	}

}
