	$("#size_select").on('change', function(){
		alert("adsfghnm");
   var size=$("#size_select").val();
   var productId=$("#productId").val();
  // var productConfigId=$("#productConfigId").val();

   
   alert(size);
  // alert(productId);
  // alert(productConfigId);
	$("#productConfigId").empty();
alert("aaaaaaaaaaaaaa")
   
   $.ajax({

		type : "GET",
		url : "/POSTERHOUSE/fetchProductBysizeandproductId",
		contentType : "application/json",
		data:{'productId':productId,'size':size},
		async : false,
		success : function(productConfigData) {
			alert("succc"+productConfigData.id)
			console.log("Fetch Login Details sucess");
		//	var obj = JSON.parse(productConfigData);
			//alert(obj);

		$("#productConfigId").append('<input value='+productConfigData.id+' th:field='+productConfigData.id+'>');
			alert("product con"+productCon)
			//document.getElementById("productConfigId").innerHTML = productConfigData.id;
     //    $("<option>").val(productConfigData.id).text(productConfigData.id).appendTo(productCon);
		// console.log("data = " + JSON.stringify(productConfigData));



		},
		error:function(data){
			alert("error")
		}

	});


	});
