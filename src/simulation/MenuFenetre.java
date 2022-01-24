package simulation;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.Usine.*;
import models.Composant.ComposantE;

public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	private static final String MENU_SIMULATION_CHOISIR = "Choisir";
	private static final String MENU_AIDE_TITRE = "Aide";
	private static final String MENU_AIDE_PROPOS = "À propos de...";

	public MenuFenetre() {
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();
	}

	/**
	 * Créer le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Sélectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Créer un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				// TODO - Parser le fichier XML sélectionné
				File selectedFile = fileChooser.getSelectedFile();
				try {
					// Instancier la Factory qui permet d'accï¿½der ï¿½ un parser (appelï¿½
					// DocumentBuilder)
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					// Rï¿½cupï¿½rer le parser
					DocumentBuilder db;
					db = dbf.newDocumentBuilder();
					// Parser le fichier XML
					Document doc = db.parse(selectedFile);
					doc.getDocumentElement().normalize();
					var nList = doc.getDocumentElement().getChildNodes().item(1).getChildNodes();

					// System.out.println(nList.getChildNodes().item(1).getNodeName());

					for (int i = 0; i < nList.getLength(); ++i) {
						Node n = nList.item(i);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							Element el = (Element) nList.item(i);

							var icones = el.getElementsByTagName("icone");
							ArrayList<String> paths = new ArrayList<String>();
							for (int j = 0; j < icones.getLength(); ++j) {
								if (n.getNodeType() == Node.ELEMENT_NODE) {
									Element ic = (Element) icones.item(i);
									paths.add(ic.getAttribute("path"));
								}

							}
							String strInterval = el.getAttribute("interval-production");
							int interval = 0;
							if (!strInterval.equals("")) {
								interval = Integer.parseInt(strInterval);
							}

							ArrayList<EntryComponent> ec = null;
							var entries = el.getElementsByTagName("entree");
							if (entries != null && entries.getLength() > 0) {
								ec = new ArrayList<EntryComponent>();
								for (int j = 0; j < entries.getLength(); ++j) {
									if (n.getNodeType() == Node.ELEMENT_NODE) {
										Element en = (Element) entries.item(i);
										String type = en.getAttribute("type");
										int amount = Integer.parseInt(en.getAttribute("quantite"));
										ec.add(new EntryComponent(EntryComponent.getEntry(type), amount));
									}

								}
							}
							var sorties = el.getElementsByTagName("sortie");
							ComposantE sortie = null;
							if(sorties != null && sorties.getLength() > 0) {
								var s =  (Element) sorties.item(1);
								sortie = EntryComponent.getEntry(s.getAttribute("type"));
							}
							
							
							String s = el.getAttribute("type");
							switch (s) {
							case "usine-matiere":
								UsineMatiere uMa = new UsineMatiere(paths, interval, ec, sortie);
								break;
							case "usine-aile":
								UsineAile ua = new UsineAile(paths, interval, ec,sortie);
								break;
							case "usine-moteur":
								UsineMoteur uMo = new UsineMoteur(paths, interval, ec,sortie);
								break;
							case "usine-assemblage":
								UsineAssemblage uAs = new UsineAssemblage(paths, interval, ec,sortie);
								break;
							case "entrepot":
								Entrepot en = new Entrepot(paths, interval, ec,sortie);
								break;
							}
							System.out.println(s);
						}

					}

				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				System.out.println(selectedFile.getAbsolutePath());
			}
		});

		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);

		add(menuFichier);

	}

	/**
	 * Créer le menu de Simulation
	 */
	private void ajouterMenuSimulation() {
		JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
		JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
		menuSimulation.add(menuChoisir);

		menuChoisir.addActionListener((ActionEvent e) -> {
			// Ouvrir la fenêtre de sélection
			// TODO - Récupérer la bonne stratégie de vente
			new FenetreStrategie();
		});
		add(menuSimulation);

	}

	/**
	 * Créer le menu Aide
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
		});
		add(menuAide);
	}

}
