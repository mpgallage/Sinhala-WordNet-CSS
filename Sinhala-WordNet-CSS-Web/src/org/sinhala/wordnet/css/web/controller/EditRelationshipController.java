package org.sinhala.wordnet.css.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.css.web.model.BreadCrumb;
import org.sinhala.wordnet.css.web.model.TagModel;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/EditRelationship")
public class EditRelationshipController {

	/*
	 * @RequestMapping(method = RequestMethod.GET, params = {
	 * "action=EditRelationship", "type=noun" }) public String
	 * editRelationship(@RequestParam(value = "id", required = false) String id,
	 * ModelMap model, @RequestParam(value = "type", required = false) String
	 * type) {
	 * 
	 * BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.NOUN);
	 * model.addAttribute("breadCrumb", breadCrumb); TagModel tagModel = new
	 * TagModel(); model.addAttribute("tagModel", tagModel);
	 * 
	 * return "EditRelationship";
	 * 
	 * }
	 */

	@RequestMapping(method = RequestMethod.GET, params = { "action=EditRelationship", "type=noun" })
	public String editNounRelationship(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.NOUN);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
			} catch (NumberFormatException | JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			NounSynset nSynset = new NounSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			NounSynset castMainSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(nSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			TagModel tagModel = new TagModel();
			model.addAttribute("tagModel", tagModel);

			return "EditRelationship";
		} else {
			return "error";
		}

	}

	@RequestMapping(method = RequestMethod.GET, params = { "action=EditRelationship", "type=verb" })
	public String editVerbRelationship(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.VERB);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.VERB, Long.parseLong(id));
			} catch (NumberFormatException | JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			VerbSynset vSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			VerbSynset castMainSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(vSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			TagModel tagModel = new TagModel();
			model.addAttribute("tagModel", tagModel);

			return "EditRelationship";
		} else {
			return "error";
		}

	}

	@RequestMapping(method = RequestMethod.GET, params = { "action=EditRelationship", "type=adj" })
	public String editAdjRelationship(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type, ModelMap model) {

		if (id != null && !"".equals(id)) {
			BreadCrumb breadCrumb = new BreadCrumb(Long.parseLong(id), POS.ADJECTIVE);
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.ADJECTIVE, Long.parseLong(id));
			} catch (NumberFormatException | JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AdjectiveSynset aSynset = new AdjectiveSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			AdjectiveSynset castMainSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(aSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			TagModel tagModel = new TagModel();
			model.addAttribute("tagModel", tagModel);

			return "EditRelationship";
		} else {
			return "error";
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveTags(@ModelAttribute TagModel tagModel, ModelMap model) {

		byte[] ptext = null;
		try {
			ptext = tagModel.gethypernymJsonString().getBytes("ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = new String(ptext, Charset.forName("UTF-8"));

		JsonFactory factory = new JsonFactory();
		JsonParser jParser;

		try {
			jParser = factory.createJsonParser(value);
			while (jParser.nextToken() != JsonToken.END_ARRAY) {
				String fieldname = jParser.getCurrentName();

				if ("tag".equals(fieldname)) {
					jParser.nextToken();
					String tagString = jParser.getText();
					String[] tagParts = tagString.split("\\(ID:");
					// remove ')' character at the end
					tagParts[1] = tagParts[1].substring(0, tagParts[1].length() - 1);
					String[] tagPartsIdPos = tagParts[1].split(",");
					System.out.println(tagPartsIdPos[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(value);
		System.out.println(tagModel.getSynsetId());

		String currentSynsetType = tagModel.getSynsetType().trim();

		if ("noun".equals(currentSynsetType)) {
			return editNounRelationship(tagModel.getSynsetId().trim(), currentSynsetType, model);

		} else if ("verb".equals(currentSynsetType)) {
			return editVerbRelationship(tagModel.getSynsetId().trim(), currentSynsetType, model);

		} else if ("adj".equals(currentSynsetType)) {
			return editAdjRelationship(tagModel.getSynsetId().trim(), currentSynsetType, model);

		} else if ("adv".equals(currentSynsetType)) {
			return "underConstruction";

		} else {
			return "error";
		}

	}

}
