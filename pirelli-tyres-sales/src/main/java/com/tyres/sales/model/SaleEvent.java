package com.tyres.sales.model;

import org.apache.avro.reflect.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class SaleEvent {
	
	@JsonProperty(value= "event_id")
	@Nullable
	private String event_id;
	@JsonProperty(value= "region")
	private String region;
	@JsonProperty(value= "area")
	private String area;
	@JsonProperty(value= "market")
	private String market;
	@JsonProperty(value= "ipcode")
	private String ipcode;
	@JsonProperty(value= "quantity")
	@Nullable
	private int quantity;
	@JsonProperty(value= "timestamp")
	@Nullable
	private long timestamp;
	
	
	public SaleEvent() {
		super();
		// TODO Auto-generated constructor stub
	}


	@JsonCreator
	public SaleEvent(@JsonProperty(value= "event_id") String event_id, @JsonProperty(value= "region") String region, @JsonProperty(value= "area") String area, @JsonProperty(value= "market") String market, @JsonProperty(value= "ipcode") String ipcode, @JsonProperty(value= "quantity") int quantity, @JsonProperty(value= "timestamp") long timestamp) {
		super();
		this.event_id = event_id;
		this.region = region;
		this.area = area;
		this.market = market;
		this.ipcode = ipcode;
		this.quantity = quantity;
		this.timestamp = timestamp;
	}


	public String getEvent_id() {
		return event_id;
	}


	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getMarket() {
		return market;
	}


	public void setMarket(String market) {
		this.market = market;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public String getIpcode() {
		return ipcode;
	}


	public void setIpcode(String ipcode) {
		this.ipcode = ipcode;
	}


	@Override
	public String toString() {
		return "SaleEvent [event_id=" + event_id + ", region=" + region + ", area=" + area + ", market=" + market
				+ ", ipcode=" + ipcode + ", quantity=" + quantity + ", timestamp=" + timestamp + "]";
	}



	
	
	
	
	
	
	
	
	
	
	

}
