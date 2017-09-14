/**
 * 
 */

function validateForm() {
	var i, allElems, state, notice;

	// allElems = document.getElementsByTagName("input");
	// notice = document.getElementByID("notice");
	// alert("d");
	// return false;

	// for (i = 0; i < allElems.length; i++) {
	// alert(allElems[i].value);
	// if (allElems[i].value == null || allElems[i].value == "") {
	// alert(allElems[i].value);
	// notice.value = "Hãy nhập " + allElems[i].name;
	// break;
	// return false;
	// }
	// }

	notice = document.getElementByID("notice");
	console.log("s"+notice);
	var checkName = document.forms["mainForm"]["username"].value;
	
//	if (checkName.value == "" || checkName.value == null) {
//		notice.value = "Hãy nhập ";
//	
//	}
	
	return false;
}