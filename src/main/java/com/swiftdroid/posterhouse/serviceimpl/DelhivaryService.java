package com.swiftdroid.posterhouse.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.model.UserPayment;
import com.swiftdroid.posterhouse.model.delihivary.PickupLocation;
import com.swiftdroid.posterhouse.model.delihivary.Request;
import com.swiftdroid.posterhouse.model.delihivary.Response;
import com.swiftdroid.posterhouse.model.delihivary.Shipment;

@Service
public class DelhivaryService {
	
	


	public static  Response createorderDelivery(String wayBill , Order order) {
			
		final String uri = "https://staging-express.delhivery.com/api/cmu/create.json";
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    final String accessToken = "8e6534547d445c2d6f677562d07d396dd0fc974b";
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Token "+accessToken);
	    Request request = new Request();
	    Shipment shipment = new Shipment(order.getShippingAddress().getShippingAddressStreet1(), order.getUser().getPhone(), "Prepaid",order.getUser().getUsername(),order.getShippingAddress().getShippingAddressZipcode(), wayBill);
	    List<Shipment> shipmentList = request.getShipments();
	    shipmentList.add(shipment);
	    PickupLocation pickUpLocation = new PickupLocation("ShoppingHub.com");
	    request.setPickupLocation(pickUpLocation);
	    ObjectMapper mapper = new ObjectMapper();
	    String json = null;
	    try {
	       json = mapper.writeValueAsString(request);
	      System.out.println("@Override\r\n"
	      		+ "	public Order findOrderById(Long Id) {\r\n"
	      		+ "		// TODO Auto-generated method stub\r\n"
	      		+ "		return orderRepository.findById(Id).orElse(null);\r\n"
	      		+ "	} = " + json);
	      //System.out.println(json);
	    } catch (JsonProcessingException e) {
	       e.printStackTrace();
	    }
	    String payload="format=json&data="+json;
	    //	    Response result = restTemplate.postForObject( uri, request, Response.class);
	    HttpEntity<String> entity = new HttpEntity<String>(payload,headers);
	      
	      Response result =  restTemplate.exchange(
	         uri, HttpMethod.POST, entity, Response.class).getBody();
	 
	    System.out.println(result);
		
		return result;
	}
	
	

	public static String getWayBill() {
		
		final String uri = "http://localhost:8080/springrestexample/employees";
		 
	    //TODO: Autowire the RestTemplate in all the examples
	    RestTemplate restTemplate = new RestTemplate();
	    
	  //  HashMap<String,String> param = new HashMap<String,String>();
	  //  param.put("token" ,"8e6534547d445c2d6f677562d07d396dd0fc974b");
	    ResponseEntity<String> result=null;
	    try {
	     result = restTemplate.getForEntity("https://staging-express.delhivery.com/waybill/api/fetch/json/?token=8e6534547d445c2d6f677562d07d396dd0fc974b", String.class);
	    System.out.println("In Method "+result.getBody());
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		return result.getBody();
		
	}
	
	public static UserPayment savePaymentDetails(TreeMap<String, String> parameters, Order Grandorder, User user) {

		  UserPayment userPayment =new UserPayment();
		  
			userPayment.setAmount(Grandorder.getFinalPrice());
			userPayment.setAT(true);
			userPayment.setCB(user.getFirstName());
			userPayment.setCO(parameters.get("TXNDATE"));
			userPayment.setCurrency(parameters.get("CURRENCY"));
			userPayment.setGatewayName(parameters.get("GATEWAYNAME"));
			userPayment.setMB(user.getFirstName());
			userPayment.setMO(parameters.get("TXNDATE"));
			userPayment.setOrder(Grandorder);
			userPayment.setTXNID(parameters.get("TXNID"));
			userPayment.setPaymentMode(parameters.get("PAYMENTMODE"));
			userPayment.setST(parameters.get("TXNDATE"));
			userPayment.setStatus(parameters.get("STATUS"));
			userPayment.setTransectionDate(parameters.get("TXNDATE"));
			userPayment.setAmount_paid(parameters.get("TXNAMOUNT"));
			return userPayment;


	}
	
}
