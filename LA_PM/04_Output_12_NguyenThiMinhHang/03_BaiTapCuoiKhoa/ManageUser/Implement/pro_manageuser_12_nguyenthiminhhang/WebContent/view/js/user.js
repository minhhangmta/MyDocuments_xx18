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
