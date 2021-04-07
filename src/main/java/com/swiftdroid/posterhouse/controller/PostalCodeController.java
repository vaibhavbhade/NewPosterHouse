package com.swiftdroid.posterhouse.controller;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class PostalCodeController {
	
	   @Autowired
	   RestTemplate restTemplate;
	
public boolean postal(String code) {
	boolean result = false;
	System.out.println( "call PostalCodeController ................");
	 HttpHeaders headers = new HttpHeaders();
     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     HttpEntity <String> entity = new HttpEntity<String>(headers);
     
     String str=restTemplate.exchange("https://staging-express.delhivery.com/c/api/pin-codes/json/?token=8e6534547d445c2d6f677562d07d396dd0fc974b&filter_codes="+code, HttpMethod.GET, entity, String.class).getBody();
 
     System.out.println("str::"+str);
     System.out.println("str::"+str.length());
     JSONObject obj;
	try {
		obj = new JSONObject(str);
		 System.out.println(obj.length());
	     JSONArray arr = obj.getJSONArray("delivery_codes");
	     JSONObject postalcodeObj = arr.getJSONObject(0);
	    String prepaid= postalcodeObj.getJSONObject("postal_code").getString("pre_paid");
	    String pin= postalcodeObj.getJSONObject("postal_code").getString("pin");
	    System.out.println("prepaid: "+prepaid);
	    System.out.println("pin: "+pin);
	    System.out.println("code: "+code);
	    if(prepaid.equals("Y") && pin.equals(code) ) {
	    	System.out.println("in loop if ");
	    	result= true;
	    }
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;

	}
     return result;
	}

	


}

/*"{\r\n"
	+ "    \"delivery_codes\": [\r\n"
	+ "        {\r\n"
	+ "            \"postal_code\": {\r\n"
	+ "                \"inc\": null, \r\n"
	+ "                \"covid_zone\": null, \r\n"
	+ "                \"pin\": 444001, \r\n"
	+ "                \"max_amount\": 10000.0, \r\n"
	+ "                \"pre_paid\": \"Y\", \r\n"
	+ "                \"cash\": \"N\", \r\n"
	+ "                \"pickup\": \"Y\", \r\n"
	+ "                \"repl\": \"N\", \r\n"
	+ "                \"cod\": \"N\", \r\n"
	+ "                \"country_code\": \"IN\", \r\n"
	+ "                \"sort_code\": \"AMI/AMI\", \r\n"
	+ "                \"is_oda\": \"N\", \r\n"
	+ "                \"district\": \"Akola\", \r\n"
	+ "                \"state_code\": \"MH\", \r\n"
	+ "                \"max_weight\": 0.0\r\n"
	+ "            }\r\n"
	+ "        }\r\n"
	+ "    ]\r\n"
	+ "}";
*/
