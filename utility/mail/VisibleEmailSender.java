package utility.mail;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.EmailException;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Invia email visualizzando una finestra con informazioni e progresso del
 * processo.
 * 
 * @author Gaetano
 * 
 */
public class VisibleEmailSender extends Application {

	/**
	 * Oggetto {@link EmailSender} si occupa dell'invio delle email.
	 */
	private static EmailSender sender;

	/**
	 * Imposta i parametri relativi all'invio delle mail.
	 * 
	 * @param o
	 *            - String contenente l'oggetto della mail.
	 * @param mess
	 *            - String contenente il messaggio della mail.
	 * @param dest
	 *            - {@link ArrayList} di {@link HashMap} contenente indirizzi
	 *            email e nomi dei destinatari.
	 */
	public static void setParams(String o, String mess,
			ArrayList<HashMap<String, String>> dest) {
		sender = new EmailSender(o, mess, dest);
	}

	@Override
	public void start(Stage stage) {
		try {
			AnchorPane root = new AnchorPane();

			final Label titolo = new Label("Invio email");
			titolo.setLayoutX(10);
			titolo.setLayoutY(14);
			titolo.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

			final ProgressBar barra = new ProgressBar();
			barra.setLayoutX(40);
			barra.setLayoutY(65);
			barra.setPrefWidth(470);
			barra.setPrefHeight(25);
			barra.setProgress(0);

			final Button bottone = new Button("Chiudi");
			bottone.setVisible(false);
			bottone.setLayoutX(480);
			bottone.setLayoutY(120);
			bottone.setPrefWidth(65);
			bottone.setPrefHeight(25);
			bottone.setFont(Font.font("Verdana", 14));

			final Label messaggio = new Label();
			messaggio.setLayoutX(40);
			messaggio.setLayoutY(90);
			messaggio.setPrefWidth(470);
			messaggio.setPrefHeight(15);
			messaggio.setFont(Font.font("Verdana", 10));

			root.getChildren().add(titolo);
			root.getChildren().add(barra);
			root.getChildren().add(bottone);
			root.getChildren().add(messaggio);

			Scene scena = new Scene(root, 550, 150);
			scena.getStylesheets().add(
					getClass().getResource("../../application/modena.css")
							.toExternalForm());

			stage.setScene(scena);
			stage.getIcons().add(new Image("/utility/mail/email.png"));
			stage.setTitle("Invio email");
			stage.setResizable(false);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					// TODO Auto-generated method stub
					event.consume();
				}

			});

			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() {
					updateTitle("Invio email in corso...");
					int size = sender.getSize();
					for (int i = 0; i < size; i++) {

						// aggiorno la label posizionata sotto la prog bar
						String email = sender.getCourrentEmail();
						email.concat(" - " + (((i) * 100) / size));
						email.concat("%");
						updateMessage(email);
						sender.send();

						// aggiorno la progress bar
						updateProgress(sender.getCounter(), size);
					}

					// imposto gli eventi associato al completamento
					setOnSucceeded(new EventHandler<WorkerStateEvent>() {
						@Override
						public void handle(WorkerStateEvent arg0) {
							// aggiorno il titolo della scena
							updateTitle("Invio email terminato");

							// aggiorno la label posizionata sotto la prog bar
							updateMessage("Invio avvenuto con successo");

							bottone.setText("Chiudi");
							bottone.setVisible(true);
							bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									Stage stage = (Stage) bottone.getScene()
											.getWindow();
									stage.close();
								}
							});
						}
					});
					return null;
				}
			};

			// associo la proprietà title del task alla label inviaEmail
			titolo.textProperty().bind(task.titleProperty());
			// associo la proprietà progress del tast alla invioEmailProgressBar
			barra.progressProperty().bind(task.progressProperty());
			// assogio la proprietà message del task alla label email
			messaggio.textProperty().bind(task.messageProperty());

			// esecuzione del Thread
			Thread th = new Thread(task);
			th.setDaemon(true);
			th.start();
			stage.show();
		} catch (Exception e) {
			EmailException ex = new EmailException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.NO_MESSAGE);
		}
	}

	/**
	 * Avvia l'interfaccia.
	 */
	public static void launch() {
		new VisibleEmailSender().start(new Stage());
//		utile se non viene lanciato da java fx
//		VisibleEmailSender.launch(null);
	}
}