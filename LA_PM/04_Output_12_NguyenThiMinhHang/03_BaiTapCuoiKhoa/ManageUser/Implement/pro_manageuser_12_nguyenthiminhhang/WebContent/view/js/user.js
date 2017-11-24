//Hàm ẩn table trình độ tiếng nhật
function hiddenTable() {
	// Nếu đang hiện
	if (document.getElementById("japan_table").style.display == "block") {
		document.getElementById("japan_table").style.display = "none";
	} else {// Nếu đang ẩn
		document.getElementById("japan_table").style.display = "block";
	}
}

// Hàm hiện thông báo confirm xóa user
function confirmDelete(userId, message, url) {
	var alert = confirm(message);
	if (alert) {
		window.location = url;
	}
}
//Hàm tạo sự kiện cho các button cần chuyển hướng tới url khác
function sendToAnotherController(path) {
	window.location = path;
}