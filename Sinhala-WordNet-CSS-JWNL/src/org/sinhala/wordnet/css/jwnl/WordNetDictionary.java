package org.sinhala.wordnet.css.jwnl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.dictionary.Dictionary;

public class WordNetDictionary {

	private static Dictionary dict;
	
	private WordNetDictionary(){	
	}

	public static Dictionary getInstance() {
		if (dict != null) {
			return dict;
		} else {
			try {
				JWNL.initialize(new FileInputStream(
						"C:\\Users\\Iresha\\git\\Sinhala-WordNet-CSS\\Resources\\config\\file_properties.xml"));
				//JWNL.initialize(new FileInputStream(
					//	"/home/malaka/Resources/config/file_properties.xml"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO: handle exception
			}
			dict = Dictionary.getInstance();
			return dict;
		}
	}

}
