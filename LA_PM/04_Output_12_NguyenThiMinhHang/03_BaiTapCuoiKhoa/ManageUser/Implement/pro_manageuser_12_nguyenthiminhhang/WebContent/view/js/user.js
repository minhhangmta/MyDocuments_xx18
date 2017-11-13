function hiddenTable() {
	if (document.getElementById("japan_table").style.display == "block") {
		document.getElementById("japan_table").style.display = "none";
	} else {
		document.getElementById("japan_table").style.display = "block";
	}
}

function hiddenPassTxt() {
	document.getElementsByClassName("password_txt").style.display = "none";
}
