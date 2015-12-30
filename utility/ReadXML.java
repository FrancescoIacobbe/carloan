package utility;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.ReaderXMLException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe utility per l'accesso e la lettura di valori da file in formato xml.
 * 
 * @author Gianluca
 * 
 */
public class ReadXML {
	
	private Document doc;

	/**
	 * Legge il file XML situato nel percorso specificato.
	 * 
	 * @param percorsoFile
	 *            percorso del file XML da leggere.
	 */
	public ReadXML(String percorsoFile) {
		try {
			URL url = getClass().getResource(percorsoFile);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(url.openStream());
		} catch (ParserConfigurationException | SAXException | IOException  e) {
			gestisciEccezione(e);
		} 
	}

	/**
	 * Restituisce l'attributo di un tag presente in un file XML.
	 * 
	 * @param tag
	 *            - tag su cui si vuole effettuare la ricerca.
	 * @param id
	 *            - attributo che identifica il tag.
	 * @param key
	 *            - chiave con cui matchare id.
	 * @param attribute
	 *            attributo di cui si vuole leggere il valore.
	 * @return valore dell'attributo cercato.
	 */
	private String readElement(String tag, String id, String key,
			String attribute) {
		String value = "";
		try {
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(tag);
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					if (eElement.getAttribute(id).matches(key)) {
						value = eElement.getAttribute(attribute);
					}
				}
			}
		} catch (Exception e) {
			gestisciEccezione(e);
		}
		return value;
	}

	/**
	 * Restituisce una lista contenente tutti i possibili valori che un
	 * attributo di un tag assume.
	 * 
	 * @param tag
	 *            - tag su cui effettuare la ricerca.
	 * @param attribute
	 *            - attributo di cui si vuole leggere il valore.
	 * @return lista dei possibili valori dell'attributo.
	 */
	public ArrayList<String> readElements(String tag, String attribute) {
		ArrayList<String> elements = new ArrayList<String>();
		try {
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(tag);
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					elements.add(eElement.getAttribute(attribute));
				}
			}
		} catch (Exception e) {
			gestisciEccezione(e);
		}

		return elements;
	}

	/**
	 * Legge il percorso in cui si trova il file XML contenente le stringhe di
	 * una determinata lingua.
	 * 
	 * @param key
	 *            lingua da cercare.
	 * @return percorso del file XML.
	 */
	public String leggiPercorso(String tag, String key) {
		return this.readElement(tag, "id", key, "src");
	}

	/**
	 * Legge il valore dell'attributo di uno specifico elemento.
	 * 
	 * @param key
	 *            la chiave in cui cercare il valore.
	 * @return valore dell'attributo.
	 */
	public String leggi(String key) {
		return this.readElement("element", "id", key, "value");
	}

	/**
	 * Restituisce un elenco delle lingue disponibili.
	 * 
	 * @return elenco delle lingue.
	 */
	public ArrayList<String> leggiLingueDisponibili() {
		return this.readElements("lingua", "value");
	}

	/**
	 *Legge i valori dei parametri class e method di un tag call.
	 * 
	 * @param id
	 *            - id del nodo da cercare.
	 * @return valori dei tag.
	 */
	public ArrayList<String> leggiParametri(String id) {
		ArrayList<String> elementi = new ArrayList<String>();
		try {
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("call");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// TROVARE UN MODO PER VERIFICARE CHE L'ATTRIBUTO CHE LEGGO
					// SIA UGUALE AL VALORE key PASSATO AL METODO

					if (eElement.getAttribute("id").matches(id)) {
						elementi.add(getTagValue("class", eElement));
						elementi.add(getTagValue("method", eElement));
					}
				}
			}
		} catch (Exception e) {
			gestisciEccezione(e);
		}
		return elementi;
	}

	/**
	 * Permette di ottenere il valore di un tag appartenente ad un nodo.
	 * 
	 * @param sTag
	 *            - tag da cui prelevare il valore.
	 * @param eElement
	 *            - nodo a cui appartiene il tag.
	 * @return valore del tag.
	 */
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
	
	/**
	 * Invia la richiesta al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 */
	private void gestisciEccezione(Exception e) {
		ReaderXMLException ex = new ReaderXMLException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
	}
}
