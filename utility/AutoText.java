package utility;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;
import dataAccess.factory.DaoFactory;
import dataAccess.utility.AutoTextDao;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Imposta la funzione di auto-inserimento su una TextField.
 * 
 * @author Ziri
 * 
 */
public class AutoText {

	/**
	 * Campo nome.
	 */
	public final static String NOME = "Nome";

	/**
	 * Campo Cognome.
	 */
	public final static String COGNOME = "Cognome";

	/**
	 * Campo Città.
	 */
	public final static String CITTA = "Citta";

	/**
	 * Campo Username.
	 */
	public final static String USERNAME = "Username";
	
	/**
	 * Campo Email.
	 */
	public final static String EMAIL = "Email";
	/**
	 * JTextField su cui abilitare l'auto inserimento.
	 */
	private TextField autoTextField;

	private AutoTextDao dao;

	/**
	 * Crea l'oggetto AutoJTextField
	 * 
	 * @param cercaTextField
	 *            - TextField su cui abilitare l'auto inserimento.
	 */
	public AutoText(TextField cercaTextField) {
		this.autoTextField = cercaTextField;
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getAutoTextDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	/**
	 * Esegue l'auto-inserimento.
	 * 
	 * @param table
	 *            la tabella in cui cercare la miglior corrispondenza.
	 * @param field
	 *            il campo della tabella in cui cercare la miglior
	 *            corrispondenza.
	 */
	public void autoInsertion(final String table, final String field) {
		autoTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if ((e.getCode().isLetterKey()) || e.getCode().isDigitKey()) {
					String text = autoTextField.getText(0,
							autoTextField.getCaretPosition());
					String result = dao.autoText(text, table, field);
					if (result != null) {
						autoTextField.setText(result);
						autoTextField.positionCaret(result.length());
						autoTextField.selectPositionCaret(text.length());
					}
				}
			}
		});
	}
}
