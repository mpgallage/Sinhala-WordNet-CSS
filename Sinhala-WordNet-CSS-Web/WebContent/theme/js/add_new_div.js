
function copyDiv() {
	
	var addWordsDiv = document.getElementById("words_div");
	var newWordDiv = document.getElementById("word_0");
	var copyDiv = newWordDiv.cloneNode(true);
	copyDiv.setAttribute("id", "word_" + counter);
	
	var span1 = copyDiv.querySelector("#ex_span_0");
	span1.setAttribute("id", "ex_span_" + counter);
	var span2 = copyDiv.querySelector("#show_span_0");
	span2.setAttribute("id", "show_span_" + counter);
	var div1 = copyDiv.querySelector("#ex_div_0");
	div1.setAttribute("id", "ex_div_" + counter);
	var img = span1.getElementsByTagName("img");
	img[0].setAttribute("src", "theme/images/expand.png");
	//
	var tesxt = copyDiv.querySelector(".lemmaval");
	tesxt.setAttribute("class", "lemmaval" + counter);
	tesxt.setAttribute("path", "words["+counter+"].lemma");
	tesxt.setAttribute("name", "words["+counter+"].lemma");
	tesxt.setAttribute("id", "words"+counter+".lemma");
	/*var texts = copyDiv.getElementsByTagName("input");
	for(var i=0; i<texts.length; i++){
		if(texts[i].getAttribute("type")=="text"){
			texts[i].value = "";
		}
	}*/
	addWordsDiv.appendChild(copyDiv);
	ExpandCollapse("ex_span_" + counter);
	counter++;
	
	return true;
}
