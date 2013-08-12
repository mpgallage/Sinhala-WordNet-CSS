package org.sinhala.wordnet.css.web.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import org.sinhala.wordnet.css.jwnl.WordNetDictionary;
import org.sinhala.wordnet.css.model.wordnet.NounSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetSynset;
import org.sinhala.wordnet.css.model.wordnet.SinhalaWordNetWord;
import org.sinhala.wordnet.css.utils.maduraapi.MeaningRequestHandler;
import org.sinhala.wordnet.wordnetDB.core.App;
import org.sinhala.wordnet.wordnetDB.core.SinhalaSynsetMongoSynsetConvertor;
import org.sinhala.wordnet.wordnetDB.core.SynsetMongoDbHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/EditSynsets")
public class EditSynsetsController {
	
	@RequestMapping(method = RequestMethod.GET, params = {
			"action=ShowEditSynset", "type=noun" })
	public String showEditSynset(
			@RequestParam(value = "id", required = false) String id,
			ModelMap model,
			@RequestParam(value = "type", required = false) String type) {

		if (id != null && !"".equals(id)) {
			Dictionary dict = WordNetDictionary.getInstance();
			Synset synset = null;
			try {
				synset = dict.getSynsetAt(POS.NOUN, Long.parseLong(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			NounSynset castSynset = new NounSynset(synset);
			NounSynset mongoCastSynset= new NounSynset();
			SinhalaSynsetMongoSynsetConvertor mongoSynsetConvertor = new SinhalaSynsetMongoSynsetConvertor();
			mongoCastSynset = mongoSynsetConvertor.OverWriteByMongo(castSynset);
			
			//System.out.println("hyperrrrrrrrrrrrrrrrr"+castSynset.getTest());
			//System.out.println("castSynset object1"+castSynset.getWords().get(0));
			 
					/*String wordLemma1 = words.get(i).getLemma();
					byte[] ptext1=null;
					try {
						ptext = wordLemma1.getBytes("UTF-8");
						String value1 = new String(ptext1, "UTF-8");
						System.out.println("converted"+value1);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					//words.get(i).getSynsetOffset();
					//System.out.println(i+"-wordLemma"+wordLemma+"synset offset"+words.get(i).toString());
				
			 //System.out.println("castSynset object2"+castSynset.getWordsAsString());
			//NounSynset castSynset1 = new NounSynset();
			// override cast synset with database values here
			
			MeaningRequestHandler meaningRequestHandler = new MeaningRequestHandler();
			List<String> wordList = castSynset.getWordArrayList();
			List<String> mongoWordList = mongoCastSynset.getWordArrayList();
			System.out.println("22222222220"+wordList);
			
			List<List<String>> meaningsList = meaningRequestHandler
					.getMeaningLists(wordList);
			List<String> intersection = meaningRequestHandler
					.getIntersection(meaningsList);
			
			model.addAttribute("synset", mongoCastSynset);
			model.addAttribute("enSynset", castSynset);
			model.addAttribute("meaningsList", meaningsList);
			model.addAttribute("intersection", intersection);
			model.addAttribute("wordList", mongoWordList);
			model.addAttribute("enWordList", wordList);
			model.addAttribute("type", type);

			return "EditSynset";
		}

		else {
			return "error";
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String editSynset(@ModelAttribute NounSynset synset,
			ModelMap model) {
	
		
		System.out.println("test"+synset.getOffset());
		System.out.println("gloss"+synset.getDefinition());
		
		
		SinhalaWordNetSynset CommSynset = synset;
		//System.out.println("off set - "+synset.getOffset());
		//System.out.println("example - "+synset.getExample());
		//synset.setExample("ffffffffffffffffffffffffffffff");
		//System.out.println("exampleff - "+synset.getExample());
		//System.out.println("words string - "+synset.getWordsAsString());
		//List<SinhalaWordNetWord> words =  synset.getWords();
		List<SinhalaWordNetWord> words = synset.getWords();
		//List<SinhalaWordNetWord> words1 = synset.getMyWords();
		//System.out.println("wordsize"+words.size());
		//byte ptext[] = myString.getBytes("ISO-8859-1"); 
		//String value = new String(ptext, "UTF-8"); 
		
		for(int i=0; i<words.size(); i++){
			if("".equals(words.get(i).getLemma()) || words.get(i).getLemma() == null){
				words.remove(i);
			}
		}
		
		for(int i=0;i<words.size();i++){
			String wordLemma = words.get(i).getLemma();
			String anto = words.get(i).getAntonym().getLemma();
			String origine = words.get(i).getOrigin().getLemma();
			String root = words.get(i).getRoot().getLemma();
			
			System.out.println(wordLemma+"Lemmaorigin"+anto);
			System.out.println(root+"root orgine"+origine);
			
			
			//System.out.println(words.get(0).getMyAnto().getLemma()+"antonymy ");
			/*byte[] ptext=null;
			try {
				ptext = wordLemma.getBytes("ISO8859_1");
				String value = new String(ptext, Charset.forName("UTF-8"));
				System.out.println("converted"+value);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//words.get(i).getSynsetOffset();
			//System.out.println(i+"-wordLemma"+wordLemma+"synset offset"+words.get(i).toString());
		}
		//String testlemm = synset.getWords().get(1).getLemma();
		//System.out.println("synset"+synset.toString());
		//System.out.println("lemma"+testlemm);
		//NounSynset nsynset = synset;
		//System.out.println(synset.getOffset());
		 // NounSynset  mytkt = new NounSynset(synset);
		//BeanUtilsBean.getInstance().getConvertUtils().register(SinhalaWordNetSynset.class,NounSynset.class); //register(false,true,-1);
     
       // BeanUtils.copyProperties(mytkt, synset);
		
		//System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff  යුනිකෝඩ්");
		SynsetMongoDbHandler synsetdb = new SynsetMongoDbHandler();
		synsetdb.addNounSynset(synset);
		//App app = new App();
		//synsetdb.test();
		
		//model.addAttribute("synset", synset);
		
		
		return showEditSynset(String.valueOf(synset.getOffset()) , model, "noun");
	}
}
