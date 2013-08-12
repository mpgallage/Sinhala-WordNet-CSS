package org.sinhala.wordnet.css.utils.maduraapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MeaningRequestHandler {

	public MeaningRequestHandler() {
		// enable this line if necessary
		this.setProxy("cache.mrt.ac.lk", "3128");
	}

	private void setProxy(String host, String port) {
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
	}

	public List<String> getIntersection(List<List<String>> meaningsList) {
		if (meaningsList.size() > 0) {
			List<String> intersection = new ArrayList<String>(meaningsList.get(0));
			for (int i = 0; i < meaningsList.size(); i++) {
				intersection.retainAll(meaningsList.get(i));
			}
			return intersection;
		} else {
			return new ArrayList<String>();
		}
	}

	public List<List<String>> getMeaningLists(List<String> wordList) {

		List<List<String>> outList = new ArrayList<List<String>>();

		if (wordList.size() > 0) {
			for (String word : wordList) {
				outList.add(this.getMeanings(word));
			}
		}

		return outList;
	}

	private List<String> getMeanings(String word) {
		String url = "http://maduradic.appspot.com/?find=" + word;
		ArrayList<String> meaningList = new ArrayList<String>();
		//meaningList.add("-");
		int check = 0;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			check=1;
		}
		Document doc=null;
		try {
			doc = docBuilder.parse(url);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			check = 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			check = 1;
		}
		catch (NullPointerException e){
			check = 1;
		}
		
		if(check == 0){
		doc.getDocumentElement().normalize();

		//ArrayList<String> meaningList = new ArrayList<String>();

		NodeList nList = doc.getElementsByTagName("definition");
		for (int j = 0; j < nList.getLength(); j++) {
			Element element = (Element) nList.item(j);
			String meaning = element.getTextContent().trim();
			if (!"".equals(meaning)) {
				meaningList.add(meaning);
			}
		}
		}
		else{
			
		}
		return meaningList;
	}

}
