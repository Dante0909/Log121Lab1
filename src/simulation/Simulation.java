package simulation;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node; 
public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		
		
		try {
			//Instancier la Factory qui permet d'accéder à un parser (appelé DocumentBuilder)
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//Récupérer le parser
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			//Parser le fichier XML
			String filePath = "src/ressources/configuration.xml";
			Document doc = db.parse(new File(filePath));
			doc.getDocumentElement().normalize(); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
