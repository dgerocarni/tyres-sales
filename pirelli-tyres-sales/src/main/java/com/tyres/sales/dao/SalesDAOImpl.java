package com.tyres.sales.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.tyres.sales.model.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SalesDAOImpl implements SalesDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public SalesDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveEvent(SaleEvent saleEvent) {
		
			String sql = "INSERT INTO tyres_sales (event_id, region, area, market, ipcode,quantity, timestamp)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, saleEvent.getEvent_id(), saleEvent.getRegion(),
					saleEvent.getArea(), saleEvent.getMarket(), saleEvent.getIpcode(), saleEvent.getQuantity(), saleEvent.getTimestamp());
		
	}
	
	@Override
	public void updateEvent(SaleEvent saleEvent) {
		
		String sql = "UPDATE tyres_sales SET event_id='"+saleEvent.getEvent_id()+"', region='"+saleEvent.getRegion()+"', area='"+saleEvent.getArea()+"', quantity="+saleEvent.getQuantity()+", timestamp="+saleEvent.getTimestamp()+" WHERE market='"+saleEvent.getMarket()+"' and ipcode='"+saleEvent.getIpcode()+"'";
			jdbcTemplate.update(sql);
		
	}
	
	
	@Override
	public SaleEvent get(String market, String ipcode) {
		
		String sql = "SELECT * FROM tyres_sales WHERE  market= '"+market+"' and ipcode='"+ipcode+"'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<SaleEvent>() {
			

			@Override
			public SaleEvent extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SaleEvent event = new SaleEvent();
					event.setEvent_id(rs.getString("event_id"));
					event.setRegion(rs.getString("region"));
					event.setArea(rs.getString("area"));
					event.setMarket(rs.getString("market"));
					event.setIpcode(rs.getString("ipcode"));
					event.setQuantity(rs.getInt("quantity"));
					event.setTimestamp(rs.getLong("timestamp"));
					return event;
				}
				
				return null;
			}
			
		});
	}
	
	@Override
	
	public List<SalesByKey> list(int requestId){
		
		if(requestId == 1) {
			
			String sql = "SELECT market, sum(quantity) as QUANTITY from tyres_sales group by market order by quantity desc limit 5";
			List<SalesByKey> listSales = jdbcTemplate.query(sql, new RowMapper<SalesByKey>() {

				@Override
				public SalesByKey mapRow(ResultSet rs, int rowNum) throws SQLException {
					SalesByKey sales = new SalesByKey();
		
					sales.setKey(rs.getString("market"));
					sales.setQuantity(rs.getInt("QUANTITY"));
					
					return sales;
				}
				
			});
			
			return listSales;
		}
		else if (requestId == 2) {
			
			String sql = "SELECT area, sum(quantity) as QUANTITY from tyres_sales group by area order by quantity desc limit 5";
			List<SalesByKey> listSales = jdbcTemplate.query(sql, new RowMapper<SalesByKey>() {

				@Override
				public SalesByKey mapRow(ResultSet rs, int rowNum) throws SQLException {
					SalesByKey sales = new SalesByKey();
		
					sales.setKey(rs.getString("area"));
					sales.setQuantity(rs.getInt("QUANTITY"));
					
					return sales;
				}
				
			});
			
			return listSales;
		}
		else {
			
			String sql = "SELECT region, sum(quantity) as QUANTITY from tyres_sales group by region order by quantity desc";
			List<SalesByKey> listSales = jdbcTemplate.query(sql, new RowMapper<SalesByKey>() {

				@Override
				public SalesByKey mapRow(ResultSet rs, int rowNum) throws SQLException {
					SalesByKey sales = new SalesByKey();
		
					sales.setKey(rs.getString("region"));
					sales.setQuantity(rs.getInt("QUANTITY"));
					
					return sales;
				}
				
			});
			
			return listSales;
			
		}
		
		
	}
	
	@Override
	public int getSalesGlobally() {
		
		String sql = "SELECT sum(quantity) AS QUANTITY from tyres_sales";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					int  globalSales = rs.getInt("QUANTITY");
					return globalSales;
				}
				
				return null;
			}
			
		});
	}
	

}
