package presentazione;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.ViewDispatcherException;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import utility.ReadXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

/**
 * Supporta l'ApplicaionController nel dispatching delle view.
 * Classe Singleton.
 * @author Gianluca
 *
 */
public class ViewDispatcher {

	/**
	 * Larghezza di default della finestra di sistema.
	 */
	private final int larghezza = 640;
	
	/**
	 * Lunghezza di default della finestra di sistema.
	 */
	private final int lunghezza = 480;
	
	/**
	 * Codice per apertura FileChooser di tipo showOpenDialog.
	 */
	public static final int OPEN_DIALOG = 0;
	
	/**
	 * Codice per apertura FileChooser di tipo showSaveDialog.
	 */
	public static final int SAVE_DIALOG = 1;
	
	private ReadXML readerInterface;
	
	private static ViewDispatcher vd;
	
	/**
	 * Fornisce l'unica istanza Singleton dell'oggetto {@link ViewDispatcher}.
	 * @return l'unica istanza della classe.
	 */
	public static ViewDispatcher getIstance() {
		if (vd == null) {
			vd = new ViewDispatcher();
		}
		return vd;
	}
	
	/**
	 * Costruttore principale.
	 */
	private ViewDispatcher() {
		this.readerInterface = new ReadXML("/ui/mappaInterfacce.xml");
	}
	
	/**
	 * Carica la prima interfaccia.
	 * @return la scena con l'interfaccia.
	 */
	public Scene avvio() {
			try {
				VBox root = FXMLLoader.load(getClass().getResource("/ui/menuGenerale.fxml"));
				Pane pane = FXMLLoader.load(getClass().getResource("/ui/benvenuto.fxml"));
				root.getChildren().add(pane);
				Scene scena = new Scene(root, larghezza, lunghezza);
				GestioneSessione.setLimiteTemporaleCorrente();
				GestioneSessione.setScenaCorrente(scena);
				GestioneSessione.setIdInterfacciaCorrente("benvenuto");
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
				gestisciEccezione(e);
			}
			return GestioneSessione.getScenaCorrente();
	}
	
	/**
	 * Carica una interfaccia attraverso il file mappaInterfacce.xml tenendo traccia della lingua selezionata.
	 * @param menuId la menuBar da impostare nell'interfaccia.
	 * @param paneId l'interfaccia da caricare.
	 */
	public void setInterfaccia(String menuId, String paneId) {
		try {
			VBox root = FXMLLoader.load(getClass().getResource(readerInterface.leggiPercorso("interface", menuId)));
			Pane pane = FXMLLoader.load(getClass().getResource(readerInterface.leggiPercorso("interface", paneId)));
			root.getChildren().add(pane);
			GestioneSessione.getScenaCorrente().setRoot(root);
			GestioneSessione.setIdInterfacciaCorrente(paneId);
		} catch (IOException | NullPointerException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e);
		}
	}
	
	/**
	 * Mostra un messaggio in una finestra pop-up.
	 * @param code il tipo di messaggio.
	 * @param title il titolo del messaggio.
	 * @param message il messaggio.
	 * @return true sse la scelta dell'utente è affermativa, altrimenti false.
	 */
	public boolean showMessage(int code, String title, String message) {
		boolean scelta = true;
		Stage stage = (Stage) GestioneSessione.getScenaCorrente().getWindow();
		DialogResponse avviso = null;
		switch(code){
			case 0:
				Dialogs.showInformationDialog(stage, message, null, title);	
				break;
			case 1:
				avviso = Dialogs.showWarningDialog(stage, message ,null, title,
						DialogOptions.OK);
				break;
			case 2:
				avviso = Dialogs.showErrorDialog(stage, message, null, title, 
						DialogOptions.OK);
				if (avviso.equals(DialogResponse.OK) || avviso.equals(DialogResponse.CLOSED)) {
					stage.close();
				}
				break;
			case 3:
				avviso = Dialogs.showWarningDialog(stage, message ,null, title,
						DialogOptions.YES_NO);
				scelta = avviso.equals(DialogResponse.YES); //se l'utente sceglie yes restituisce true
				break;
			default:
				break;
			}
		return scelta;
	}
	
	/**
	 * Mostra una finestra pop-up di input.
	 * @param title il titolo della finestra.
	 * @param message il messaggio da mostrare nella finestra.
	 * @return il testo inserito nella casella di testo.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showInputDialog(String title, String message) {
		Stage stage = (Stage) GestioneSessione.getScenaCorrente().getWindow();
		Pane p = new Pane();
		final TextArea testo = new TextArea();
		testo.setPrefSize(420,50);
		testo.setWrapText(true);
		p.getChildren().add(testo);
		Callback myCallback = new Callback() {
			@Override
			public Object call(Object arg0) {
				// TODO Auto-generated method stub
				return testo.getText();
			}
			};
		Dialogs.showCustomDialog(stage, p, message, title, DialogOptions.OK, myCallback);
		String result = (String) myCallback.call(null);
		return result;
	}
	
	/**
	 * Imposta il pane centrale in una scena con menù.
	 * @param id l'interfaccia da settare.
	 */
	public void setPane(String id) {
		String path = readerInterface.leggiPercorso("interface", id);
		try {
			Pane root = FXMLLoader.load(getClass().getResource(path));
			VBox box = (VBox) GestioneSessione.getScenaCorrente().getRoot();
			box.getChildren().remove(1);
			box.getChildren().add(root);
			GestioneSessione.setIdInterfacciaCorrente(id);
		} catch (IOException | NullPointerException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e);
		}
	}
	
	/**
	 * Aggiorna il pane dell'interfaccia corrente.
	 */
	public void aggiornaPane() {
		String interfacciaCorrente = GestioneSessione.getIdInterfacciaCorrente();
		setPane(interfacciaCorrente);
	}
	
	/**
	 * Apre una finestra di dialogo di tipo FileChooser.
	 * @param type il tipo di finestra.
	 * @param estensione l'estensione dei file consentiti.
	 * @return il file selezionato.
	 */
	public File showFileChooser(int type, String estensione) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter(estensione, "*." + estensione));
		File file = null;
		switch(type) {
		case OPEN_DIALOG:
			file = fileChooser.showOpenDialog(new Stage().getOwner());
			break;
		case SAVE_DIALOG:
			file = fileChooser.showSaveDialog(new Stage().getOwner());
			String pattern = ".+\\.pdf";
			if (file != null) {
				//aggiugno l'estensione al file se non è già presente.
				String nomeFile = file.getName();
				if (!Pattern.matches(pattern, nomeFile)) {
					String path = file.getAbsolutePath();
					file = new File (path + ".pdf");
				}
			}
			
			break;
		default: break;
		}
		return file;
	}
	
	/**
	 * Inoltra l'eccezione sollevata al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 */
	private void gestisciEccezione (Exception e) {
		ViewDispatcherException ex = new ViewDispatcherException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
	}
}
