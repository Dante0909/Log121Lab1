package models;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import models.Composant.ComposantE;
import models.Composant.EntryComponent;
import models.Usine.*;
import simulation.Simulation;

public class XmlParser {

	public static void ParseFile(File f) {
		try {
			var Usines = Simulation.Usines;
			Usines.clear();
			// Instancier la Factory qui permet d'acc�der � un parser (appel�
			// DocumentBuilder)
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// R�cup�rer le parser
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			// Parser le fichier XML
			Document doc = db.parse(f);
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
			Entrepot en = null;

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
						UsineAile ua = new UsineAile(GetPaths(aileUsine), GetInterval(aileUsine), GetEntries(aileUsine),
								GetExit(aileUsine), id, x, y);
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
						en = new Entrepot(GetPaths(entrepotUsine), GetEntries(entrepotUsine), id, x, y);

						Usines.add(en);
						break;
					}
				}
			}
			for(int i = 0; i < Usines.size(); ++i) {
				var u = Usines.get(i);
				if(u instanceof AUsineProduction) {
					en.attach((AUsineProduction)u);
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
					((AUsineProduction) uFrom).setProductPath(new Chemin(uFrom, uTo));
				}
			}

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	private static ArrayList<String> GetPaths(Element e) {
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

	private static int GetInterval(Element e) {
		String strInterval = e.getElementsByTagName("interval-production").item(0).getTextContent();
		int interval = -1;
		if (!strInterval.equals("")) {
			interval = Integer.parseInt(strInterval);
		}
		return interval;
	}

	private static ArrayList<EntryComponent> GetEntries(Element e) {
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
					
					if (strQuantite.equals("")) {
						strQuantite = en.getAttribute("capacite");
						if (strQuantite.equals("")) return null;
					}

					int amount = Integer.parseInt(strQuantite);
					ec.add(new EntryComponent(EntryComponent.getEntry(type), amount));
				}
			}
		}
		return ec;
	}

	private static ComposantE GetExit(Element e) {
		var sorties = e.getElementsByTagName("sortie");
		ComposantE sortie = null;
		if (sorties != null && sorties.getLength() > 0) {
			var s = (Element) sorties.item(0);
			sortie = EntryComponent.getEntry(s.getAttribute("type"));
		}
		return sortie;
	}
}
