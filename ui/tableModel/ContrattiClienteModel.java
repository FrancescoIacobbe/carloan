package ui.tableModel;

import java.util.ArrayList;
import java.util.Collection;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

public class ContrattiClienteModel {

	public static final int MC = 0;
	private SimpleStringProperty IdCon;
	private SimpleStringProperty data;
	private FrontControllerInt fc;
	private SimpleObjectProperty<Hyperlink> bottone;
	
	/**
	 * Costruttore principale.
	 * 
	 * @param idCont
	 *            l'id del contratto
	 * @param data
	 *            la data.
	 */
	public ContrattiClienteModel(String idCont, String data, boolean sololettura, boolean mng) {
		this.IdCon = new SimpleStringProperty(idCont);
		this.data = new SimpleStringProperty(data);
		this.bottone= new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		if(sololettura){
			setButton("riepiologoContrattoClie");
		}else if(mng==false) {
			setButton("riepilogoContrattoDip");
		} else {
			setButton("riepilogoContrattoMng");
		}
	}

	private void setButton(final String richiesta) {
		this.bottone.get().setCursor(Cursor.HAND);
		this.bottone.get().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				fc = new FrontController();
				ArrayList<String> p = new ArrayList<String>(1);
				p.addAll((Collection<? extends String>) IdCon);
				fc.processRequest(richiesta, p);
			}
		});
		
	}

	/**
	 * Restituisce l'IdCont
	 * 
	 * @return l'Id del contratto.
	 */
	public String getIdCont() {
		return IdCon.get();
	}

	/**
	 * Restituisce la data.
	 * 
	 * @return la data della competizione.
	 */
	public String getData() {
		return data.get();
	}

	/**
	 * Restituisce l'hiperlink associato al bottone.
	 * 
	 * @return l'hyperlink associato al bottone.
	 */
	public Hyperlink getBottone() {
		return bottone.get();
	}

	/**
	 * Fornisce un array di stringhe contenenti le informazioni signficative del
	 * modello.
	 * 
	 * @return l'array con le informazioni.
	 */
	public String[] toArray() {
		String[] result = { IdCon.get(), data.get()};
		return result;
	}
}
