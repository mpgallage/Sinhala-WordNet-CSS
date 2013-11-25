package org.sinhala.wordnet.css.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.dictionary.Dictionary;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.AdjectiveSynset;
import org.sinhala.wordnet.css.model.wordnet.AdverbSynset;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.VerbSynset;
import org.sinhala.wordnet.css.web.model.BreadCrumb;
import org.sinhala.wordnet.css.web.model.TagModel;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/EditRelationship")
public class EditRelationshipController {

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

			HashMap<MongoSinhalaPointerTyps, String> existingTagStringMap = getExistingRelationshipTagStringsMap(synset);

			List<SinhalaWordNetSynset> hyponymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.HYPONYM);
			List<SinhalaWordNetSynset> hypernymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.HYPERNYM);
			List<SinhalaWordNetSynset> memberHolonymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.MEMBER_HOLONYM);
			List<SinhalaWordNetSynset> substanceHolonymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM);
			List<SinhalaWordNetSynset> partHolonymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.PART_HOLONYM);
			List<SinhalaWordNetSynset> memberMeronymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.MEMBER_MERONYM);
			List<SinhalaWordNetSynset> substanceMeronymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.SUBSTANCE_MERONYM);
			List<SinhalaWordNetSynset> partMeronymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.PART_MERONYM);
			List<SinhalaWordNetSynset> attributeSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.ATTRIBUTE);
			List<SinhalaWordNetSynset> derivationSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.DERIVATION);
			// List<SinhalaWordNetSynset> usageSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.USAGE);
			// List<SinhalaWordNetSynset> regionSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.REGION);

			NounSynset nSynset = new NounSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			NounSynset castMainSynset = (NounSynset) mongoSynsetConvertor.OverWriteByMongo(nSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			model.addAttribute("existingHyponymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.HYPONYM));
			model.addAttribute("existingHypernymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.HYPERNYM));
			model.addAttribute("existingMemberHolonymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.MEMBER_HOLONYM));
			model.addAttribute("existingSubstanceHolonymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM));
			model.addAttribute("existingPartHolonymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.PART_HOLONYM));
			model.addAttribute("existingMemberMeronymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.MEMBER_MERONYM));
			model.addAttribute("existingSubstanceMeronymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.SUBSTANCE_MERONYM));
			model.addAttribute("existingPartMeronymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.PART_MERONYM));
			model.addAttribute("existingAttributesAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.ATTRIBUTE));
			model.addAttribute("existingDerivationsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.DERIVATION));

			model.addAttribute("hyponymSuggestionList", hyponymSuggestionList);
			model.addAttribute("hypernymSuggestionList", hypernymSuggestionList);
			model.addAttribute("memberHolonymSuggestionList", memberHolonymSuggestionList);
			model.addAttribute("substanceHolonymSuggestionList", substanceHolonymSuggestionList);
			model.addAttribute("partHolonymSuggestionList", partHolonymSuggestionList);
			model.addAttribute("memberMeronymSuggestionList", memberMeronymSuggestionList);
			model.addAttribute("substanceMeronymSuggestionList", substanceMeronymSuggestionList);
			model.addAttribute("partMeronymSuggestionList", partMeronymSuggestionList);
			model.addAttribute("attributeSuggestionList", attributeSuggestionList);
			model.addAttribute("derivationSuggestionList", derivationSuggestionList);
			// model.addAttribute("usageSuggestionList", usageSuggestionList);
			// model.addAttribute("regionSuggestionList", regionSuggestionList);

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

			HashMap<MongoSinhalaPointerTyps, String> existingTagStringMap = getExistingRelationshipTagStringsMap(synset);

			List<SinhalaWordNetSynset> hyponymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.HYPONYM);
			List<SinhalaWordNetSynset> hypernymSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.HYPERNYM);
			List<SinhalaWordNetSynset> entailmentSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.ENTAILMENT);
			List<SinhalaWordNetSynset> causeSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.CAUSE);
			List<SinhalaWordNetSynset> derivationSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.DERIVATION);

			VerbSynset vSynset = new VerbSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			VerbSynset castMainSynset = (VerbSynset) mongoSynsetConvertor.OverWriteByMongo(vSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			model.addAttribute("existingHyponymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.HYPONYM));
			model.addAttribute("existingHypernymsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.HYPERNYM));
			model.addAttribute("existingCausesAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.CAUSE));
			model.addAttribute("existingEntailmentsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.ENTAILMENT));
			model.addAttribute("existingDerivationsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.DERIVATION));

			model.addAttribute("hyponymSuggestionList", hyponymSuggestionList);
			model.addAttribute("hypernymSuggestionList", hypernymSuggestionList);
			model.addAttribute("entailmentSuggestionList", entailmentSuggestionList);
			model.addAttribute("causeSuggestionList", causeSuggestionList);
			model.addAttribute("derivationSuggestionList", derivationSuggestionList);

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

			HashMap<MongoSinhalaPointerTyps, String> existingTagStringMap = getExistingRelationshipTagStringsMap(synset);

			List<SinhalaWordNetSynset> similarSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.SIMILAR_TO);
			List<SinhalaWordNetSynset> attributeSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.ATTRIBUTE);
			List<SinhalaWordNetSynset> derivationSuggestionList = getRelationshipPointerSynsetsList(synset, MongoSinhalaPointerTyps.DERIVATION);
			
			AdjectiveSynset aSynset = new AdjectiveSynset(synset);
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			AdjectiveSynset castMainSynset = (AdjectiveSynset) mongoSynsetConvertor.OverWriteByMongo(aSynset, "");

			model.addAttribute("synset", castMainSynset);
			model.addAttribute("type", type);
			model.addAttribute("breadCrumb", breadCrumb);

			model.addAttribute("existingSimilarsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.SIMILAR_TO));
			model.addAttribute("existingAttributesAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.ATTRIBUTE));
			model.addAttribute("existingDerivationsAsString", existingTagStringMap.get(MongoSinhalaPointerTyps.DERIVATION));

			model.addAttribute("similarSuggestionList", similarSuggestionList);
			model.addAttribute("attributeSuggestionList", attributeSuggestionList);
			model.addAttribute("derivationSuggestionList", derivationSuggestionList);
			
			
			TagModel tagModel = new TagModel();
			model.addAttribute("tagModel", tagModel);

			return "EditRelationship";
		} else {
			return "error";
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveTags(@ModelAttribute TagModel tagModel, ModelMap model) {

		String currentSynsetType = tagModel.getSynsetType().trim();
		String currentSynsetId = tagModel.getSynsetId().trim();

		if ("noun".equals(currentSynsetType)) {

			addRelation(tagModel.getHypernymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.HYPERNYM);
			addRelation(tagModel.getHyponymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.HYPONYM);
			addRelation(tagModel.getMemberMeronymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.MEMBER_MERONYM);
			addRelation(tagModel.getSubstanceMeronymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.SUBSTANCE_MERONYM);
			addRelation(tagModel.getPartMeronymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.PART_MERONYM);
			addRelation(tagModel.getMemberHolonymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.MEMBER_HOLONYM);
			addRelation(tagModel.getSubstanceHolonymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM);
			addRelation(tagModel.getPartHolonymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.PART_HOLONYM);
			addRelation(tagModel.getAttributeJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.ATTRIBUTE);
			addRelation(tagModel.getAttributeJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.DERIVATION);

			return editNounRelationship(currentSynsetId, currentSynsetType, model);

		} else if ("verb".equals(currentSynsetType)) {

			addRelation(tagModel.getHypernymJsonString(), currentSynsetId, POS.VERB, MongoSinhalaPointerTyps.HYPERNYM);
			addRelation(tagModel.getHyponymJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.HYPONYM);
			addRelation(tagModel.getAttributeJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.DERIVATION);
			addRelation(tagModel.getEntailmentJsonString(), currentSynsetId, POS.VERB, MongoSinhalaPointerTyps.ENTAILMENT);
			addRelation(tagModel.getCauseJsonString(), currentSynsetId, POS.VERB, MongoSinhalaPointerTyps.CAUSE);
			
			return editVerbRelationship(currentSynsetId, currentSynsetType, model);

		} else if ("adj".equals(currentSynsetType)) {

			addRelation(tagModel.getSimilarJsonString(), currentSynsetId, POS.VERB, MongoSinhalaPointerTyps.SIMILAR_TO);
			addRelation(tagModel.getAttributeJsonString(), currentSynsetId, POS.NOUN, MongoSinhalaPointerTyps.DERIVATION);
			addRelation(tagModel.getAttributeJsonString(), currentSynsetId, POS.ADJECTIVE, MongoSinhalaPointerTyps.ATTRIBUTE);
			
			return editAdjRelationship(currentSynsetId, currentSynsetType, model);

		} else if ("adv".equals(currentSynsetType)) {
			addRelation(tagModel.getDerivedfromJsonString(), currentSynsetId, POS.ADVERB, MongoSinhalaPointerTyps.DERIVATION);
			return "underConstruction";

		} else {
			return "error";
		}

	}

	public void addRelation(String jsonString, String id, POS pos, MongoSinhalaPointerTyps pointerType) {
		
		byte[] ptext = null;
		try {
			ptext = jsonString.getBytes("ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String value = new String(ptext, Charset.forName("UTF-8"));

		if (value != null && !"".equals(value)) {
			JsonFactory factory = new JsonFactory();
			JsonParser jParser;
			List<Long> ids = new ArrayList<Long>();
			List<String> poses = new ArrayList<String>();
			try {
				jParser = factory.createJsonParser(value);

				if (JsonToken.START_ARRAY.equals(jParser.nextToken())) {
					while (jParser.nextToken() != JsonToken.END_ARRAY) {
						String fieldname = jParser.getCurrentName();

						if ("tag".equals(fieldname)) {
							jParser.nextToken();
							String tagString = jParser.getText();
							String[] tagParts = tagString.split("\\(ID:");

							if (tagParts.length != 2)
								return;

							tagParts[1] = tagParts[1].substring(0, tagParts[1].length() - 1); // remove ')' character at the end
							String[] tagPartsIdPos = tagParts[1].split(",");

							if (tagPartsIdPos.length != 2)
								return;

							long rid = Long.parseLong(tagPartsIdPos[0]);
							ids.add(rid);
							poses.add(tagPartsIdPos[1]);
						}
					}
					Long lid = Long.parseLong(id);

					SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
					dbHandler.addSencePointers(lid, pos, pointerType, ids, poses);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public List<SinhalaWordNetSynset> getRelationshipPointerSynsetsList(Synset synset, MongoSinhalaPointerTyps pointerType) {

		PointerUtils pointerUtils = PointerUtils.getInstance();
		PointerTargetNodeList pointerList = new PointerTargetNodeList();
		try {

			if (MongoSinhalaPointerTyps.HYPONYM.equals(pointerType)) {
				pointerList = pointerUtils.getDirectHyponyms(synset);
			} else if (MongoSinhalaPointerTyps.HYPERNYM.equals(pointerType)) {
				pointerList = pointerUtils.getDirectHypernyms(synset);
			} else if (MongoSinhalaPointerTyps.MEMBER_HOLONYM.equals(pointerType)) {
				pointerList = pointerUtils.getMemberHolonyms(synset);
			} else if (MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM.equals(pointerType)) {
				pointerList = pointerUtils.getSubstanceHolonyms(synset);
			} else if (MongoSinhalaPointerTyps.PART_HOLONYM.equals(pointerType)) {
				pointerList = pointerUtils.getPartHolonyms(synset);
			} else if (MongoSinhalaPointerTyps.MEMBER_MERONYM.equals(pointerType)) {
				pointerList = pointerUtils.getMemberMeronyms(synset);
			} else if (MongoSinhalaPointerTyps.SUBSTANCE_MERONYM.equals(pointerType)) {
				pointerList = pointerUtils.getSubstanceMeronyms(synset);
			} else if (MongoSinhalaPointerTyps.PART_MERONYM.equals(pointerType)) {
				pointerList = pointerUtils.getPartMeronyms(synset);
			} else if (MongoSinhalaPointerTyps.ATTRIBUTE.equals(pointerType)) {
				pointerList = pointerUtils.getAttributes(synset);
			} else if (MongoSinhalaPointerTyps.ENTAILMENT.equals(pointerType)) {
				pointerList = pointerUtils.getEntailments(synset);
			} else if (MongoSinhalaPointerTyps.CAUSE.equals(pointerType)) {
				pointerList = pointerUtils.getCauses(synset);
			} else if (MongoSinhalaPointerTyps.DERIVATION.equals(pointerType)) {
				pointerList = pointerUtils.getDerived(synset);
			}

		} catch (JWNLException e) {
			e.printStackTrace();
		}

		List<SinhalaWordNetSynset> synsetList = new ArrayList<SinhalaWordNetSynset>();

		if (pointerList.size() > 0) {

			for (int i = 0; i < pointerList.size(); i++) {
				PointerTargetNode node = (PointerTargetNode) pointerList.get(i);
				PointerTarget target = node.getPointerTarget();
				Synset s = (Synset) target;
				POS pos = s.getPOS();
				SinhalaWordNetSynset tempSynset = null;
				if (POS.NOUN.equals(pos)) {
					tempSynset = new NounSynset(s);
				} else if (POS.VERB.equals(pos)) {
					tempSynset = new VerbSynset(s);
				} else if (POS.ADJECTIVE.equals(pos)) {
					tempSynset = new AdjectiveSynset(s);
				} else if (POS.ADVERB.equals(pos)) {
					tempSynset = new AdverbSynset(s);
				}
				SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
				SinhalaWordNetSynset castSynset = mongoSynsetConvertor.OverWriteByMongo(tempSynset, "");
				synsetList.add(castSynset);
			}
		}
		return synsetList;
	}

	private HashMap<MongoSinhalaPointerTyps, String> getExistingRelationshipTagStringsMap(Synset synset) {

		HashMap<MongoSinhalaPointerTyps, String> relationshipTagStringsMap = new HashMap<MongoSinhalaPointerTyps, String>();
		SynsetMongoDbHandler dbHandler = new SynsetMongoDbHandler();
		MongoSinhalaSynset mongoSinhalaSynset = dbHandler.findBySynsetId(synset.getOffset(), synset.getPOS());

		if (mongoSinhalaSynset != null) {

			List<MongoSinhalaSencePointer> mongoSinhalaSencePointer = mongoSinhalaSynset.getSencePointers();

			for (int i = 0; i < mongoSinhalaSencePointer.size(); i++) {

				SinhalaWordNetSynset mongoSynset = getOverWrittenMongoSynset(mongoSinhalaSencePointer.get(i).getSynsetId(), mongoSinhalaSencePointer.get(i).getPointedFile());
				if (mongoSynset != null) {

					MongoSinhalaPointerTyps pointerTyp = mongoSinhalaSencePointer.get(i).getPointerType();
					String temp = relationshipTagStringsMap.remove(pointerTyp);

					if (temp == null) {
						temp = "";
					}

					if ("n".equals(mongoSinhalaSencePointer.get(i).getPointedFile())) {
						temp = temp + mongoSynset.getWordsAsString() + "(ID:" + mongoSynset.getOffset() + ",noun)" + ";";
					} else if ("v".equals(mongoSinhalaSencePointer.get(i).getPointedFile())) {
						temp = temp + mongoSynset.getWordsAsString() + "(ID:" + mongoSynset.getOffset() + ",verb)" + ";";
					} else if ("adj".equals(mongoSinhalaSencePointer.get(i).getPointedFile())) {
						temp = temp + mongoSynset.getWordsAsString() + "(ID:" + mongoSynset.getOffset() + ",adj)" + ";";
					}

					relationshipTagStringsMap.put(pointerTyp, temp);
				}

			}

		}

		return relationshipTagStringsMap;
	}

	public SinhalaWordNetSynset getOverWrittenMongoSynset(Long id, String pointedFile) {

		SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
		SinhalaWordNetSynset sinhalaWordNetSynset = null;

		Dictionary dict = WordNetDictionary.getInstance();
		Synset synset = null;
		try {
			if ("n".equals(pointedFile)) {
				synset = dict.getSynsetAt(POS.NOUN, id);
				NounSynset nSynset = new NounSynset(synset);
				sinhalaWordNetSynset = mongoSynsetConvertor.OverWriteByMongo(nSynset, "");
			} else if ("v".equals(pointedFile)) {
				synset = dict.getSynsetAt(POS.VERB, id);
				VerbSynset vSynset = new VerbSynset(synset);
				sinhalaWordNetSynset = mongoSynsetConvertor.OverWriteByMongo(vSynset, "");
			} else if ("adj".equals(pointedFile)) {
				synset = dict.getSynsetAt(POS.ADJECTIVE, id);
				AdjectiveSynset aSynset = new AdjectiveSynset(synset);
				sinhalaWordNetSynset = mongoSynsetConvertor.OverWriteByMongo(aSynset, "");
			}
		} catch (NumberFormatException | JWNLException e) {
			e.printStackTrace();
		}

		return sinhalaWordNetSynset;
	}

}
