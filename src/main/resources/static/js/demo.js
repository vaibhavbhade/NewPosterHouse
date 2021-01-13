/**
 * 
 */

function demo(){


	var result = document.getElementById('sst');
	var sst = result.value;
	if( !isNaN( sst ))
		result.value++;
	alert(sst);

	return false;
}