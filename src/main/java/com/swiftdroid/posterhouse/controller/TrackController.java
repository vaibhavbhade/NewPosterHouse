package com.swiftdroid.posterhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.delihivary.ShipmentTracker;
import com.swiftdroid.posterhouse.service.OrderService;
import com.swiftdroid.posterhouse.service.TrackOrderService;

@Controller
public class TrackController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TrackOrderService trackOrderService;
	
	@GetMapping("/trackOrder")
	public String trackOrder(@RequestParam Long id,Model model) {
		Order order = orderService.findOrderByTrackingId(id);
		ShipmentTracker shipmentTrackList = trackOrderService.getTrackingDetails(order.getTackingId());
		
		model.addAttribute("orders" , order);
		model.addAttribute("q" , "1");
		model.addAttribute("shipmentTrackLists" , shipmentTrackList.getShipmentData()[0]);
		return "trackOrder";
	}
}
