package gestioneErrori;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import config.ConfiguratorErrorReader;
import config.ConfiguratorReader;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.stage.Stage;

/**
 * Implementa l'interfaccia {@link GestioneErroriInt}.
 * Classe Singleton.
 * @author Gaetano
 *
 */
public class GestioneErrori implements GestioneErroriInt {

	private static Logger logger;
	private static FileHandler handler;
	private static ConfiguratorReader reader;
	
	/**
	 * Costruttore privato dell'oggetto {@link GestioneErrori}.
	 */
	private  GestioneErrori() {
		readerIstance();
		handlerIstance();
	}
	
	/**
	 * Fornisce l'unica istanza in memoria di {@link GestioneErrori}.
	 * @return l'istanza di {@link GestioneErrori}.
	 */
	public static GestioneErrori getIstance() {
		return new GestioneErrori();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void submitError(Class c, Throwable e, Level l) {
		// TODO Auto-generated method stub
		
		//RICORDARSI DI TOGLIERLA 
		e.printStackTrace();
		
		String className = c.getName();
		LogManager.getLogManager().reset();
		logger = Logger.getLogger(className);
		logger.addHandler(handler);
		logger.log(l, e.getMessage(), e);
		if (!l.equals(GestioneErroriInt.NO_MESSAGE)) {
			showMessage(getMessage(className), l);
		}
	}

	/**
	 * Istanzia il {@link FileHandler} per il logger della classe.
	 */
	private void handlerIstance() {
		if (handler == null) {
			try {
				handler = new FileHandler("log.%u.%g.xml", 1024 * 1024, 10);
				handler.setLevel(Level.ALL);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Impossibile creare file di log", e);
			}
		}
	}
	
	/**
	 * Mostra un messaggio di errore.
	 * @param message il messaggio di errore da visualizzare.
	 * @param l il livello dell'errore.
	 */
	private void showMessage(String message, Level l) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (l.equals(GestioneErroriInt.SEVERE)) {
            	//Platform.exit();
            	//RICORDARE DI DECOMMENTARE
            }
        }
	}
	
	/**
	 * Fornisce l'unica istanza Singleton di {@link ConfiguratorReader} specifico per la classe.
	 */
	private void readerIstance() {
		if (reader == null) {
			reader = new ConfiguratorErrorReader();
		}
	}
	
	/**
	 * Fornisce il testo del messaggio da visualizzare.
	 * @param key la chiave da cui recuperare il messaggio.
	 * @return il messaggio da visualizzare.
	 */
	private String getMessage(String key) {
		String message = reader.getField(key);
		return message;
	}
	
}
