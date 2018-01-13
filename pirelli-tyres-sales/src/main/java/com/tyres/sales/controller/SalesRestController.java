package com.tyres.sales.controller;

import com.tyres.sales.model.*;
import com.tyres.sales.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.IOException;
import java.util.List;


@RestController
@EnableAutoConfiguration
public class SalesRestController {
	
	private static final Logger log = LoggerFactory.getLogger(SalesRestController.class);
	
	@Autowired
	private RestService restService;

	public SalesRestController() {
		log.info("SalesRestController()");
	}
	
	@RequestMapping(path = "/entity/sales", method = RequestMethod.POST)
	private void postAction(@RequestBody SaleEvent event) throws IOException{
		
		log.info("Call the endpoint /entity/sales");
		log.info("Event Id received=[" + event.getEvent_id() + "]");
		log.info("Region received=[" + event.getRegion() + "]");
		log.info("Area received=[" + event.getArea() + "]");
		log.info("Market received=[" + event.getMarket() + "]");
		log.info("IpCode received=[" + event.getIpcode() + "]");
		log.info("Quantity received=[" + event.getQuantity() + "]");
		log.info("Timestamp received: [" + event.getTimestamp() + "]");;
		
		try {
			
			SaleEvent retrieved = restService.getEvent(event.getMarket(), event.getIpcode());
			if(retrieved == null)
				restService.saveEvent(event);
			restService.updateEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping(path = "/entity/topMarkets", method = RequestMethod.GET)
	private List<SalesByKey> topFiveMarkets() {
		
		log.info("Call the endpoint /entity/topMarkets");
		log.info("Retrieveng TOP 5 MARKETS BY SALES");

		return restService.getKPIs(1);
		
	}
	
	@RequestMapping(path = "/entity/topAreas", method = RequestMethod.GET)
	private List<SalesByKey> topAreas() {
		
		log.info("Call the endpoint /entity/topAreas");
		log.info("Retrieveng TOP 5 AREAS BY SALES");

		return restService.getKPIs(2);
		
	}
	
	@RequestMapping(path = "/entity/regions", method = RequestMethod.GET)
	private List<SalesByKey> regionsBySales() {
		
		log.info("Call the endpoint /entity/regions");
		log.info("Retrieveng REGIONS BY SALES IN DESCENDING ORDER");

		return restService.getKPIs(3);
		
	}
	
	@RequestMapping(path = "/entity/globalSales", method = RequestMethod.GET)
	private int getSalesGlobally() {
		
		log.info("Call the endpoint /entity/globalSales");
		log.info("Retrieveng the SUM OF SALES GLOBALLY");

		return restService.getGlobalSales();
		
	}
	
	
	
	
	
	
	

}
