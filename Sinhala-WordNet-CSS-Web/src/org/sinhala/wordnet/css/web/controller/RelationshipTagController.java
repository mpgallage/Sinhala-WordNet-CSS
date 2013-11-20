package org.sinhala.wordnet.css.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.didion.jwnl.data.POS;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.sinhala.wordnet.css.web.model.TagModel;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/RelationshipTags")
public class RelationshipTagController {

	@RequestMapping(method = RequestMethod.GET, params = { "action=getTags" })
	// public @ResponseBody List<String> getTags(@RequestParam(value = "term",
	// required = false) String term) {
	public @ResponseBody
	String getTags(@RequestParam(value = "term", required = false) String term) {

		byte ptext[] = null;
		// byte ptext1[] =null;
		String value = null;
		// lemma.getBytes()
		try {
			// ptext = lemma.getBytes();
			ptext = term.getBytes("ISO8859_1");
			value = new String(ptext, Charset.forName("UTF-8"));
			// value = new String(ptext, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		POS pos = POS.NOUN;

		SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
		HashMap<Long, MongoSinhalaSynset> offsetCollection = dbHandler.findSynsetsByLemma(value, POS.NOUN);

		int offsetCollectionSize = offsetCollection.size();
		String jsonString = "[";

		if (offsetCollectionSize > 0) {
			MongoSinhalaSynset[] offsetArray = new MongoSinhalaSynset[offsetCollectionSize];

			MongoSinhalaSynset synset = null;

			Iterator iter = offsetCollection.entrySet().iterator();
			int i = 0;

			while (iter.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iter.next();
				synset = (MongoSinhalaSynset) mEntry.getValue();

				jsonString = jsonString + "{\"word\":\"";

				jsonString = jsonString + synset.getWordsAsString().trim();

				jsonString = jsonString + "\", \"id\":\"";

				jsonString = jsonString + synset.getEWNId();

				jsonString = jsonString + "\", \"pos\":\"";

				jsonString = jsonString + pos.getLabel();

				jsonString = jsonString + "\", \"def\":\"";

				String definition = synset.getGloss().split("\\|")[0].trim();

				jsonString = jsonString + definition;

				jsonString = jsonString + "\"},";
				i++;
			}

			jsonString = jsonString.substring(0, jsonString.length() - 1);
		}

		jsonString = jsonString + "]";

		return jsonString;
	}

}
