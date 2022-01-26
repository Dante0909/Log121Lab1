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
import models.Chemin;
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

	public static ArrayList<AUsine> Usines = new ArrayList<AUsine>();
	public static ArrayList<Chemin> Chemins = new ArrayList<Chemin>();

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

					Element meta = (Element) doc.getElementsByTagName("metadonnees").item(0);
					var metausines = meta.getElementsByTagName("usine");

					Element aileUsine = null;
					Element matiereUsine = null;
					Element moteurUsine = null;
					Element avionUsine = null;
					Element entrepotUsine = null;

					for (int i = 0; i < metausines.getLength(); ++i) {
						Node n = metausines.item(i);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							Element el = (Element) n;
							String type = el.getAttribute("type");
							switch (type) {
							case "usine-matiere":
								matiereUsine = el;
								break;
							case "usine-aile":
								aileUsine = el;
								break;
							case "usine-moteur":
								moteurUsine = el;
								break;
							case "usine-assemblage":
								avionUsine = el;
								break;
							case "entrepot":
								entrepotUsine = el;
								break;
							}
						}

					}

					var simul = (Element) doc.getElementsByTagName("simulation").item(0);
					var nList = simul.getElementsByTagName("usine");

					for (int i = 0; i < nList.getLength(); ++i) {
						Node n = nList.item(i);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							Element el = (Element) n;

							int id = Integer.parseInt(el.getAttribute("id"));
							int x = Integer.parseInt(el.getAttribute("x"));
							int y = Integer.parseInt(el.getAttribute("y"));

							String type = el.getAttribute("type");

							switch (type) {
							case "usine-matiere":
								UsineMatiere uMa = new UsineMatiere(GetPaths(matiereUsine), GetInterval(matiereUsine),
										GetEntries(matiereUsine), GetExit(matiereUsine), id, x, y);
								Usines.add(uMa);

								break;
							case "usine-aile":
								UsineAile ua = new UsineAile(GetPaths(aileUsine), GetInterval(aileUsine),
										GetEntries(aileUsine), GetExit(aileUsine), id, x, y);
								Usines.add(ua);
								break;
							case "usine-moteur":
								UsineMoteur uMo = new UsineMoteur(GetPaths(moteurUsine), GetInterval(moteurUsine),
										GetEntries(moteurUsine), GetExit(moteurUsine), id, x, y);
								Usines.add(uMo);
								break;
							case "usine-assemblage":
								UsineAssemblage uAs = new UsineAssemblage(GetPaths(avionUsine), GetInterval(avionUsine),
										GetEntries(avionUsine), GetExit(avionUsine), id, x, y);
								Usines.add(uAs);
								break;
							case "entrepot":
								Entrepot en = new Entrepot(GetPaths(entrepotUsine), GetInterval(entrepotUsine),
										GetEntries(entrepotUsine), GetExit(entrepotUsine), id, x, y);
								Usines.add(en);
								break;
							}
						}
					}

					var ch = simul.getElementsByTagName("chemin");
					for (int i = 0; i < ch.getLength(); ++i) {
						Element el = (Element) ch.item(i);
						int from = Integer.parseInt(el.getAttribute("de"));
						int to = Integer.parseInt(el.getAttribute("vers"));
						AUsine uFrom = null;
						AUsine uTo = null;
						
						for (int j = 0; j < Usines.size(); ++j) {
							AUsine u = Usines.get(j);
							if (u.getId() == from) {
								uFrom = u;
							}
							if (u.getId() == to) {
								uTo = u;
							}
						}
						if (uFrom != null && uTo != null) {
							Chemins.add(new Chemin(uFrom, uTo));
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

	private ArrayList<String> GetPaths(Element e) {
		var icones = e.getElementsByTagName("icone");
		ArrayList<String> paths = new ArrayList<String>();
		for (int i = 0; i < icones.getLength(); ++i) {
			Node no = icones.item(i);
			if (no.getNodeType() == Node.ELEMENT_NODE) {
				Element ic = (Element) icones.item(i);
				paths.add(ic.getAttribute("path"));
			}

		}
		return paths;
	}

	private int GetInterval(Element e) {
		String strInterval = e.getAttribute("interval-production");
		int interval = -1;
		if (!strInterval.equals("")) {
			interval = Integer.parseInt(strInterval);
		}
		return interval;
	}

	private ArrayList<EntryComponent> GetEntries(Element e) {
		ArrayList<EntryComponent> ec = null;
		var entries = e.getElementsByTagName("entree");
		if (entries != null && entries.getLength() > 0) {
			ec = new ArrayList<EntryComponent>();
			for (int i = 0; i < entries.getLength(); ++i) {
				Node no = entries.item(i);
				if (no.getNodeType() == Node.ELEMENT_NODE) {
					Element en = (Element) no;
					String type = en.getAttribute("type");
					String strQuantite = en.getAttribute("quantite");
					if (strQuantite.equals(""))
						return null;
					int amount = Integer.parseInt(strQuantite);
					ec.add(new EntryComponent(EntryComponent.getEntry(type), amount));
				}
			}
		}
		return ec;
	}

	private ComposantE GetExit(Element e) {
		var sorties = e.getElementsByTagName("sortie");
		ComposantE sortie = null;
		if (sorties != null && sorties.getLength() > 0) {
			var s = (Element) sorties.item(0);
			sortie = EntryComponent.getEntry(s.getAttribute("type"));
		}
		return sortie;
	}
}
