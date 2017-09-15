/**
 * 
 */

function validateForm() {
	var notice = document.getElementById("notice");
	notice.innerHTML = "";

	var username = document.getElementById("username");
	var group = document.getElementById("group");
	var fullname = document.getElementById("fullname");
	var email = document.getElementById("email");
	var phoneNumber = document.getElementById("phoneNumber");
	var password = document.getElementById("password");
	var confirm_password = document.getElementById("confirm_password");

	if (username.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào '
				+ username.name + '</label><br>';
	}
	if (group.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy chọn mục ' + group.name
				+ '</label><br>';
	}
	if (fullname.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào '
				+ fullname.name + '</label><br>';
	}
	if (email.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào ' + email.name
				+ '</label><br>';
	}
	if (phoneNumber.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào '
				+ phoneNumber.name + '</label><br>';
	}
	if (password.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào '
				+ password.name + '</label><br>';
	}
	if (confirm_password.value.length < 1) {
		notice.innerHTML += '<label class="error"> Hãy nhập vào '
				+ confirm_password.name + '</label><br>';
	}

	return false;
}

function hiddenTable() {
	if (document.getElementById("bottom_table").style.display == "") {
		document.getElementById("bottom_table").style.display = "none";
	} else {
		document.getElementById("bottom_table").style.display = "";
	}
}

function checkDelete() {
	var notice = confirm("Bạn có muốn xóa không?");
	if (notice) {
		window.location.href = "ADM002.html";
	}
}