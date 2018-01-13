package com.tyres.sales.dao;

import java.util.List;
import com.tyres.sales.model.*;

public interface SalesDAO {
	
	public void saveEvent(SaleEvent saleEvent);
	public void updateEvent(SaleEvent saleEvent);
	public SaleEvent get(String market, String ipcode);
	public List<SalesByKey> list(int requestId);
	public int getSalesGlobally();

}
