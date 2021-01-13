/**
 * 
 */

function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}



function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}
function checkPasswordLengthInProfile(){
//	alert("hiiiiiiiiii");
	var password = $("#txtNewPassword").val();

	
	if(password.length < 5 || password.length > 11) {
		$("#checkpasswordSize").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#registerButton").prop('disabled', true);
	}
	else{
		$("#checkpasswordSize").html(" ");

		$("#registerButton").prop('disabled', false);

	}
	
}

function checkPasswordLength(){
//	alert("hiiiiiiiiii");
	var password = $("#password").val();
	
	
	if(password.length < 5 || password.length > 11) {
		$("#checkPasswordMsg").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#registerButton").prop('disabled', true);
	}
	else{
		$("#checkPasswordMsg").html(" ");

		$("#registerButton").prop('disabled', false);

	}
	
}

function checkUsernameLength(){
	//alert("hiiiiiiiiii");
	var username = $("#username").val();
	
	if(username.length < 5 || username.length > 11) {
		$("#checkUserNameMsg").html(" ** you have to enter at least 5 digit and less then 10 digit ");
		$("#updateUserInfoButton").prop('disabled', true);
	}
	else{
		$("#checkUserNameMsg").html("");

		$("#updateUserInfoButton").prop('disabled', false);

	}
}
	
function checkPhoneNumber(){

	var phoneNumberVal = $("#phoneNumber").val();
	
	  var regx = /^[6-9]\d{9}$/ ;
	  if(regx.test(phoneNumberVal)){
		$("#checkPhoneNoMsg").html("");
		$("#registerButton").prop('disabled', false);
	  }
	  else{
			$("#checkPhoneNoMsg").html(" ** Please Enter Valid Phone Number");
			$("#registerButton").prop('disabled', true);
	  }
	}

	
function checkEditPhoneNumber(){

	var phoneNumberVal = $("#editphoneNumber").val();
	
	  var regx = /^[6-9]\d{9}$/;
	  if(regx.test(phoneNumberVal)){
		$("#checkEditPhoneNoMsg").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	  }
	  else{
			$("#checkEditPhoneNoMsg").html(" ** Please Enter Valid Phone Number");
			$("#updateUserInfoButton").prop('disabled', true);
	  }
	}


function checkZipCode(){

	var zipcodeVal = $("#shippingZipcode").val();
	
	  var regx = /[1-9][0-9]{5}/;
	  if(regx.test(zipcodeVal) && zipcodeVal.length==6){
		$("#shippingZipcodeMsg").html("");
		//$("#updateUserInfoButton").prop('disabled', false);
	  }
	  else{
			$("#shippingZipcodeMsg").html(" ** Please Enter Valid Zipcode");
			//$("#updateUserInfoButton").prop('disabled', true);
	  }
}
$(document).ready(function(){
	$(".cartItemQty").on('change', function(){
		var id=this.id;
		
		$('#update-item-'+id).css('display', 'inline-block');
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

var  _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];    
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
                return false;
            }
        }
    }
    return true;
}