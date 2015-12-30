package application;

import presentazione.FrontController;
import presentazione.FrontControllerInt;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Classe di avvio del sistema.
 * 
 * @author Gianluca 
 * 
 */
public class main extends Application {
	@Override
	public void start(Stage primaryStage) {
		FrontControllerInt fc = new FrontController();
		Scene sc = (Scene) fc.processRequest("avvio");
		sc.getStylesheets().add(
				getClass().getResource("/application/Modena.css").toExternalForm());
		
		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.setTitle("CARLOAN");
		primaryStage.show();
		System.out.println("cazzo funziona");
	}
  
	/**
	 * Metodo di avvio del sistema.
	 * 
	 * @param args
	 *            parametri di avvio.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
