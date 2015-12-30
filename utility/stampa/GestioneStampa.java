package utility.stampa;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.StampaException;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.stage.Stage;

/**
 * Classe adibita alla stampa di un file.
 * 
 * @author Gianluca
 * 
 */
public class GestioneStampa {

	/**
	 * Richiama il gestore predefinito associato ad un file per effettuare la
	 * stampa. Se non è impostato alcun gestore predefinito per l'estensione del
	 * file, la stampa non è eseguita.
	 * 
	 * @param f
	 *            il file da stampare. Può essere null.
	 * @return true sse la richiesta di stampa è stata inoltrata correttamente,
	 *         altrimenti false.
	 */
	public static boolean stampa(File f) {
		boolean controll = false;
		String message;
		if (Desktop.isDesktopSupported()) {
			Desktop stampante = Desktop.getDesktop();
			if (f != null) {
				try {
					stampante.print(f);
					controll = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					StampaException ex = new StampaException(e.getMessage());
					GestioneErroriInt ge = GestioneErrori.getIstance();
					ge.submitError(ex.getClass(), ex, GestioneErroriInt.WARNING);
				}
			} else {
				//errore file non valido
				message = "Impossibile stampare il file selezionato.\nRiprovare";
				Dialogs.showErrorDialog(new Stage(), message,
						null, "Errore Stampa", DialogOptions.OK);
			}
		} else {
			//errore funzionalità non supportata dal sistema
			message = "Funzionalità non supportata da questo sistema operativo.";
			Dialogs.showErrorDialog(new Stage(), message,
					null, "Errore Stampa", DialogOptions.OK);
		}
		return controll;
	}

	/**
	 * @see #stampa(File).
	 * @param path
	 *            il percorso del file da stampare.
	 * @return true sse la richiesta di stampa è stata inoltrata correttamente,
	 *         altrimenti false.
	 */
	public static boolean stampa(String path) {
		File f = new File(path);
		return stampa(f);
	}

}
