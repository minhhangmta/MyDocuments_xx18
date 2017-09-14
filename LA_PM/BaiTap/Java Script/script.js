var dateNow = new Date()
/**
 * Hàm cập nhật ngày tháng hiện tại
 * 
 */
function loadData() {
	var date = document.getElementById("date");
	var month = document.getElementById("month");

	for (var i = 1; i < 13; i++) {
		var option = document.createElement("option");
		option.value = i;
		option.text = i;
		month.add(option);
		if ((dateNow.getMonth() + 1) == i)
			option.selected = true;
	}

	for (var i = 1; i < 32; i++) {
		var option = document.createElement("option");
		option.value = i;
		option.text = i;
		date.add(option);
		if (dateNow.getDate() == i)
			option.selected = true;
	}

}

/**
 * Hàm cập nhật lại ngày khi thay đổi tháng
 * 
 */
function loadDate() {
	if (month.value == "2") {
		date.removeChild(date.options[30]);
		date.removeChild(date.options[29]);
		date.removeChild(date.options[28]);
	} else {
		for (var i = 29; i < 32; i++) {
			var option = document.createElement("option");
			option.value = i;
			option.text = i;
			date.add(option);
			if (dateNow.getDate() == i)
				option.selected = true;
		}
	}
}