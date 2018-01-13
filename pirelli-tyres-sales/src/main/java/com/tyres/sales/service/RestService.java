package com.tyres.sales.service;

import java.util.List;

import com.tyres.sales.model.*;

public interface RestService {
	
	public void saveEvent(SaleEvent event);
	public void updateEvent(SaleEvent event);
	public SaleEvent getEvent(String market, String ipcode);
	public List<SalesByKey> getKPIs(int requestId);
	public int getGlobalSales();

}
