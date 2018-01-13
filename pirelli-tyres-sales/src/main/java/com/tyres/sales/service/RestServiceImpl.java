package com.tyres.sales.service;

import com.tyres.sales.model.*;
import com.tyres.sales.dao.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl implements RestService {
	
	private static final Logger log = LoggerFactory.getLogger(RestServiceImpl.class);
	
	@Autowired
	private SalesDAO salesDao;

	public RestServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void saveEvent(SaleEvent event) {
		
		log.debug("Sale Event to insert --> "+event.toString());
		salesDao.saveEvent(event);
		
	}
	
	@Override
	public void updateEvent(SaleEvent event) {
		
		log.debug("Sale Event to insert --> "+event.toString());
		salesDao.updateEvent(event);
		
	}
	
	@Override
	public SaleEvent getEvent(String market, String ipcode) {
		
		log.debug("Looking for event with market --> "+market+" and ipcode -->"+ipcode);
		return salesDao.get(market, ipcode);
	}
	
	@Override
	public List<SalesByKey> getKPIs(int requestId){
		
		if(requestId == 1) {
			log.debug("GETTING TOP 5 MARKETS BY SALES -->");
			return salesDao.list(requestId);
			
		}
		else if (requestId == 2) {
			
			log.debug("GETTING TOP 5 AREAS BY SALES -->");
			return salesDao.list(requestId);
		}
		else if (requestId == 3) {
			
			log.debug("GETTING REGIONS BY SALES IN DESCENDING ORDER -->");
			return salesDao.list(requestId);
		}
		return null;
		
		
	}
	
	@Override
	public int getGlobalSales() {
		
		log.debug("RETRIEVING SUM OF SALES GLOBALLY -->");
		return salesDao.getSalesGlobally();
	}
	
	
	

}
