//var counter = 2;

function copyDiv() {
	var addWordsDiv = document.getElementById("words_div");
	var newWordDiv = document.getElementById("word_1");
	var copyDiv = newWordDiv.cloneNode(true);
	copyDiv.setAttribute("id", "word_" + counter);
	var span1 = copyDiv.querySelector("#ex_span_1");
	span1.setAttribute("id", "ex_span_" + counter);
	var span2 = copyDiv.querySelector("#show_span_1");
	span2.setAttribute("id", "show_span_" + counter);
	var div1 = copyDiv.querySelector("#ex_div_1");
	div1.setAttribute("id", "ex_div_" + counter);
	var img = span1.getElementsByTagName("img");
	img[0].setAttribute("src", "theme/images/expand.png");
	var texts = copyDiv.getElementsByTagName("input");
	for(i=0; i<texts.length; i++){
		if(texts[i].getAttribute("type")=="text"){
			texts[i].value = "";
		}
	}
	addWordsDiv.appendChild(copyDiv);
	ExpandCollapse("ex_span_" + counter);
	counter++;
	
	return true;
}
