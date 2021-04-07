package com.swiftdroid.posterhouse.serviceimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swiftdroid.posterhouse.model.delihivary.ShipmentTracker;
import com.swiftdroid.posterhouse.service.TrackOrderService;

@Service
public class TrackOrderServiceImpl implements TrackOrderService{
	
	@Override
	
	public ShipmentTracker getTrackingDetails(Long trackingId) {

		final String uri = "https://staging-express.delhivery.com/api/v1/packages/json/?waybill="+trackingId+"&token=8e6534547d445c2d6f677562d07d396dd0fc974b";
		 
	    //TODO: Autowire the RestTemplate in all the examples
	    RestTemplate restTemplate = new RestTemplate();
	    
	  //  HashMap<String,String> param = new HashMap<String,String>();
	  //  param.put("token" ,"8e6534547d445c2d6f677562d07d396dd0fc974b");
	    ResponseEntity<ShipmentTracker> result=null;
	    try {
	     result = restTemplate.getForEntity(uri, ShipmentTracker.class);
	    System.out.println("In Method "+result.getBody());
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		System.out.println(result);
		return result.getBody();
		
		
	}
}
