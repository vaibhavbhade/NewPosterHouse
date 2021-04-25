
/**
 * 
 */




function checkBillingAddress() {
	if ($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}



function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();

	if (password == "" && confirmPassword == "") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if (password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}


function checkPasswordLengthInProfile() {
	//	alert("hiiiiiiiiii");
	var password = $("#txtNewPassword").val();


	if (password.length < 5 || password.length > 11) {
		$("#checkpasswordSize").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#updateUserInfoButton").prop('disabled', true);
	}
	else {
		$("#checkpasswordSize").html(" ");

		$("#updateUserInfoButton").prop('disabled', false);

	}

}

function checkPasswordLength() {
	//	alert("hiiiiiiiiii");
	var password = $("#password").val();


	if (password.length < 5 || password.length > 11) {
		$("#checkPasswordMsg").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#registerButton").prop('disabled', true);
	}
	else {
		$("#checkPasswordMsg").html(" ");

		$("#registerButton").prop('disabled', false);

	}

}

function checkUsernameLength() {
	//alert("hiiiiiiiiii");
	var username = $("#username").val();

	if (username.length < 5 || username.length > 11) {
		$("#checkUserNameMsg").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#registerButton").prop('disabled', true);
	}
	else {
		$("#checkUserNameMsg").html("");

		$("#registerButton").prop('disabled', false);

	}
}

function checkPhoneNumber() {

	var phoneNumberVal = $("#phoneNumber").val();

	var regx = /^[6-9]\d{9}$/;
	if (regx.test(phoneNumberVal)) {
		$("#checkPhoneNoMsg").html("");
		$("#registerButton").prop('disabled', false);
	}
	else {
		$("#checkPhoneNoMsg").html(" ** Please Enter Valid Phone Number");
		$("#registerButton").prop('disabled', true);
	}
}


function checkEditPhoneNumber() {

	var phoneNumberVal = $("#editphoneNumber").val();
	var regx = /^[6-9]\d{9}$/;
	if (regx.test(phoneNumberVal)) {
		$("#checkEditPhoneNoMsg").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	}
	else {
		$("#checkEditPhoneNoMsg").html(" ** Please Enter Valid Phone Number");
		$("#updateUserInfoButton").prop('disabled', true);
	}
}


function checkZipCode() {

	var zipcodeVal = $("#shippingZipcode").val();
	var regx = /[1-9][0-9]{5}/;
	if (regx.test(zipcodeVal) && zipcodeVal.length == 6) {
		$("#shippingZipcodeMsg").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	}
	else {
	$("#shippingZipcodeMsg").html(" ** Please Enter Valid Zipcode");
    $("#updateUserInfoButton").prop('disabled', true);
	}
}


$(document).ready(function() {
	$(".cartItemQty").on('change', function() {
		var id = this.id;

		$('#update-item-' + id).css('display', 'inline-block');
	});
	$("#theSameAsShippingAddress").on('click', checkBillingAddress);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
	$("#password").keyup(checkPasswordLength);
	$("#username").keyup(checkUsernameLength);
	$("#phoneNumber").keyup(checkPhoneNumber);
	$("#editphoneNumber").keyup(checkEditPhoneNumber);
	$("#shippingZipcode").keyup(checkZipCode);
	$("#txtNewPassword").keyup(checkPasswordLengthInProfile);

});

var i = 1;

var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
function ValidateSingleInput(oInput) {
	if (oInput.type == "file") {
		var sFileName = oInput.value;
		if (sFileName.length > 0) {
			var blnValid = false;
			for (var j = 0; j < _validFileExtensions.length; j++) {
				var sCurExtension = _validFileExtensions[j];
				if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
					blnValid = true;
					break;
				}
			}

			if (!blnValid) {
				alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
				oInput.value = "";
				location.reload();
				return false;
			}
		}
	}

	showImageThumbnailThird(oInput);
	return true;
}

function showImageThumbnailThird(fileInput) {

	file = fileInput.files[0];
	reader = new FileReader();
	var id = 'thumbnail' + i;
	reader.onload = function(e) {
		$('#' + id).attr('src', e.target.result);
	}
	i++;
	reader.readAsDataURL(file)

	var a = document.getElementById(id);
	a.style.display = "block";
}



var productID = 1;

function addMoreImage() {
	/* var el = $(".divUploadImage").get(0); */
	/* var nextProduct = $(el).after(el.cloneNode(true)); */



	var x = document.getElementById("btnUpload" + productID);
	x.style.display = "none";
	var y = document.getElementById("btnAddMore" + productID);
	y.style.display = "none";
	/*	var z = document.getElementById("btnDelete"+productID);
		z.style.display = "block";*/
	var a = document.getElementById("thumbnail" + productID);
	a.style.display = "block";

	var nextProduct = $(".divUploadImage" + productID).clone();
	nextProduct.removeClass('divUploadImage' + productID).addClass('divUploadImage' + (++productID));

	nextProduct.find('input').eq(0).attr({
		id: 'btnUpload' + productID,
	});
	/* nextProduct.find('a').eq(0).attr({
		 id: 'btnDelete' + productID,
		 name: 'btnDelete' + productID
	 });*/
	nextProduct.find('img').eq(0).attr({
		id: 'thumbnail' + productID,
		name: 'thumbnail' + productID
	});
	//if you use delete button make it eq(1)
	nextProduct.find('a').eq(0).attr({
		id: 'btnAddMore' + productID,
		name: 'btnAddMore' + productID,
	});

	$('.div_row').append(nextProduct);


	var newId = 'btnUpload' + productID;

	//reset input by id;

	$("#" + newId).val("");


	var x = document.getElementById("btnUpload" + productID);
	x.style.display = "block";
	var y = document.getElementById("btnAddMore" + productID);
	y.style.display = "none";
	/*	var z = document.getElementById("btnDelete"+productID);
		z.style.display = "none";*/
	var a = document.getElementById("thumbnail" + productID);
	a.style.display = "none";
}

function uploadImage() {

	var x = document.getElementById("btnUpload" + productID);
	x.style.display = "none";
	var y = document.getElementById("btnAddMore" + productID);
	y.style.display = "block";
	/*var z = document.getElementById("btnDelete"+productID);
	z.style.display = "block";*/
}

function deleteImage() {
	location.reload();
}

