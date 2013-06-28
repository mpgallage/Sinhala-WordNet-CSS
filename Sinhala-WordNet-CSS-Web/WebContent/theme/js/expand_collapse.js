function ExpandCollapse(ElementId) {
	var ClickedElement = document.getElementById(ElementId);
	var SectionElement = ClickedElement.parentNode;
	var GroupElement = SectionElement.parentNode;
	var SpanSiblings = SectionElement.getElementsByTagName("span");
	var DivSiblings = SectionElement.getElementsByTagName("div");
	if (ClickedElement.innerHTML == "<img src=\"theme/images/collapse.png\">") {
		// this code turns this section off
		ClickedElement.innerHTML = "<img src=\"theme/images/expand.png\"/>";
		SpanSiblings[1].innerHTML = "පෙන්වන්න";
		DivSiblings[0].style.display = "none";
	} else {
		// this code turns this section on and all other sections off
		ClickedElement.innerHTML = "<img src=\"theme/images/collapse.png\"/>";
		SpanSiblings[1].innerHTML = "සඟවන්න";
		DivSiblings[0].style.display = "block";
		var otherSections = GroupElement.getElementsByTagName("div");
		for (i = 0; i < otherSections.length; i++) {
			// first make sure this div is an immediate child of the parent
			// group
			if (otherSections[i].parentNode.id == GroupElement.id) {
				// next make sure this section is not the one you just expanded
				if (otherSections[i].id != SectionElement.id) {
					// collapse this section
					var x = document.getElementById(otherSections[i].id);
					var SpanSiblings = x.getElementsByTagName("span");
					var DivSiblings = x.getElementsByTagName("div");
					SpanSiblings[0].innerHTML = "<img src=\"theme/images/expand.png\"/>";
					SpanSiblings[1].innerHTML = "පෙන්වන්න";
					DivSiblings[0].style.display = "none";
				}
			}
		}
	}
}