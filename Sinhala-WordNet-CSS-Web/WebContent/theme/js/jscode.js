function add2list(sourceID,targetID){
  source=document.getElementById(sourceID);
  target=document.getElementById(targetID);
  numberOfItems=source.options.length;
  insertPt=target.options.length; //insert at end
  if(target.options[0].text===""){insertPt=0;} //null option fix
  for(i=0;i<numberOfItems;i++){
    if(source.options[i].selected===true){
    msg=source.options[i].text;
    for(j=0;j<target.options.length;j++){
      if(msg===target.options[j].text){j=99;}}
    if(j<99){    
      target.options[insertPt]=new Option(msg);
      insertPt=target.options.length;}
    }}
}

function takefromlist(targetID){
  target=document.getElementById(targetID);
  if(target.options.length<0){return;}
  for(var i=(target.options.length-1);i>=0;i--){
    if(target.options[i].selected){
      target.options[i]=null;
      if(target.options.length===0){target.options[0]=new Option("");}
    }}
}

function addword(sourceID,targetID){
	source=document.getElementById(sourceID);
  	target=document.getElementById(targetID);
  	insertPt=target.options.length; //insert at end
	if(target.options[0].text===""){insertPt=0;} //null option fix
	msg=source.value;
	if(msg!=""){
		target.options[insertPt]=new Option(msg);
	}
	source.value="";
}

function updateattributes(targetID){
	
	target=document.getElementById(targetID);
	
	if(target.options.length<0){return;}
	
	for(var i=(target.options.length-1);i>=0;i--){
    	if(target.options[i].selected){
			
			if(target.options[i].gender){
				var radioButton = document.getElementById(target.options[i].gender);
				radioButton.checked = true;
			}else{
				var radioButtons = document.getElementsByName("gender");
				radioButtons[0].checked = false;
				radioButtons[1].checked = false;
				radioButtons[2].checked = false;
			}
			
			if(synsetlist.options[i].rootword){
				rootword = document.getElementById("rootword");
				rootword.value = synsetlist.options[i].rootword;
			}else{
				rootword = document.getElementById("rootword");
				rootword.value = "";
			}
			
			
			if(synsetlist.options[i].thadditha){
				thadditha = document.getElementById("thadditha");
				thadditha.value = synsetlist.options[i].thadditha;
			}else{
				thadditha = document.getElementById("thadditha");
				thadditha.value ="";
			}
			
			if(synsetlist.options[i].antonym){
				antonym = document.getElementById("antonym");
				antonym.value = synsetlist.options[i].antonym;
			}else{
				antonym = document.getElementById("antonym");
				antonym.value ="";
			}
   	 	}
	}
}

function addattributes(elementid){
	
	synsetlist = document.getElementById(elementid);
	if(synsetlist.options.length<0){return;}
	
	for(var i=(synsetlist.options.length-1);i>=0;i--){
    	if(synsetlist.options[i].selected){
			
			var radioButtons = document.getElementsByName("gender");
            for (var x = 0; x < radioButtons.length; x++) {
                if (radioButtons[x].checked) {
                    synsetlist.options[i].gender = radioButtons[x].value;
                }
            }
			
			rootword = document.getElementById("rootword");
			synsetlist.options[i].rootword = rootword.value;
			
			thadditha = document.getElementById("thadditha");
			synsetlist.options[i].thadditha = thadditha.value;
			
			antonym = document.getElementById("antonym");
			synsetlist.options[i].antonym = antonym.value;
			
		}
	}
	
}
	
