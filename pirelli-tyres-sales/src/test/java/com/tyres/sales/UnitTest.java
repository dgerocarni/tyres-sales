package com.tyres.sales;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.tyres.sales.model.*;
import com.tyres.sales.service.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class) 
@SpringBootTest
@ContextConfiguration(classes = ContextConfigurationClass.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration

public class UnitTest {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UnitTest.class);

	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private RestService restService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("env", "dev");
	}
	
	
    @Before
    public void setup() throws InterruptedException {
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
         


    }

    
    
  	//##################################################
	//############# TEST SERVICE METHODS ###############
	//##################################################
    
    @Before
    public void dumpDB() {
    	
    	final SaleEvent event1 = new SaleEvent("1000xds","EUROPE","CENTRAL EUROPE","SWIZTERLAND","1000",100,1513191152);
    	final SaleEvent event2 = new SaleEvent("1000xss","EUROPE","CENTRAL EUROPE","SWIZTERLAND","2000",200,1513191152);
    	final SaleEvent event3 = new SaleEvent("1000asas","EUROPE","CENTRAL EUROPE","SWIZTERLAND","3000",300,1513191152);
    	final SaleEvent event4 = new SaleEvent("100fdfd0","EUROPE","CENTRAL EUROPE","ITALY","5000",100,1513191152);
    	final SaleEvent event5 = new SaleEvent("1000gggf","EUROPE","CENTRAL EUROPE","ITALY","6000",100,1513191152);
    	final SaleEvent event6 = new SaleEvent("10ww0ee0","ASIA","SOUTH ASIA","CINA","1000",9000,1513191152);
    	
        restService.saveEvent(event1);
        restService.saveEvent(event2);
        restService.saveEvent(event3);
        restService.saveEvent(event4);
        restService.saveEvent(event5);
        restService.saveEvent(event6);
        
        LOGGER.info("SalesEvent inseriti!!!");
        
       /* salesDao.saveEvent(event1);
        salesDao.saveEvent(event2);
        salesDao.saveEvent(event3);
        salesDao.saveEvent(event4);
        salesDao.saveEvent(event5);
        salesDao.saveEvent(event6);*/
        
    }
    
  	//##################################################
	//#############  INSERT NEW SALE ###################
	//##################################################
	
	@Test
	public void saleEventInsert() throws Exception {
		 mockMvc.perform(
				post("/entity/sales")
		 		.content("{\"event_id\":\"4e9iAk\",\"region\":\"EUROPE\",\"area\":\"CENTRAL EUROPE\",\"market\":\"GERMANY\",\"ipcode\":\"2157100\",\"quantity\":742,\"timestamp\":1513191152}")
		 		.contentType(APPLICATION_JSON_UTF8))
		 		.andExpect(status().isOk());

		//wait before to deque
		Thread.sleep(1000);
		
		SaleEvent event = restService.getEvent("GERMANY", "2157100");
		
		assertThat(event.getIpcode()).isEqualTo("2157100");

	}
 

	
  	//##################################################
	//#############  UPDATE NEW SALE ###################
	//##################################################
	
	
	@Test
	public void saleEventUpdate() throws Exception {
		 mockMvc.perform(
				post("/entity/sales")
		 		.content("{\"event_id\":\"4e9iAk\",\"region\":\"EUROPE\",\"area\":\"CENTRAL EUROPE\",\"market\":\"GERMANY\",\"ipcode\":\"2157100\",\"quantity\":789,\"timestamp\":1513191152}")
		 		.contentType(APPLICATION_JSON_UTF8))
		 		.andExpect(status().isOk());

		//wait before to deque
		Thread.sleep(1000);
		
		SaleEvent event = restService.getEvent("GERMANY", "2157100");
		
		assertThat(event.getQuantity()).isEqualTo(789);

	}
	
	
	//####################################################
	//############# TOP 5 MARKETS BY SALES ###############
	//####################################################
 
	@Test
	public void topMarketsBySales() throws Exception {
		mockMvc.perform(
				get("/entity/topMarkets"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].key").value("CINA"))
		.andExpect(jsonPath("$[0].quantity").value(9000))
		.andExpect(jsonPath("$[1].key").value("SWIZTERLAND"))
		.andExpect(jsonPath("$[1].quantity").value(600))
		.andExpect(jsonPath("$[2].key").value("ITALY"))
		.andExpect(jsonPath("$[2].quantity").value(200));

	}
	
	
	//####################################################
	//############# TOP 5 AREAS BY SALES #################
	//####################################################
	
	@Test
	public void topAreasBySales() throws Exception {
		mockMvc.perform(
				get("/entity/topAreas"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].key").value("SOUTH ASIA"))
		.andExpect(jsonPath("$[0].quantity").value(9000))
		.andExpect(jsonPath("$[1].key").value("CENTRAL EUROPE"))
		.andExpect(jsonPath("$[1].quantity").value(800));

	}
	
	
	//####################################################
	//####### REGIONS BY SALES IN DESCENDING ORDER #######
	//####################################################
	
	@Test
	public void regionsBySales() throws Exception {
		mockMvc.perform(
				get("/entity/regions"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].key").value("ASIA"))
		.andExpect(jsonPath("$[0].quantity").value(9000))
		.andExpect(jsonPath("$[1].key").value("EUROPE"))
		.andExpect(jsonPath("$[1].quantity").value(800));

	}
	
	//####################################################
	//####### SUM OF SALES GLOBALLY ######################
	//####################################################
	
	@Test
	public void globalSales() throws Exception {
		mockMvc.perform(
				get("/entity/globalSales"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value(9800));

	}

	
}
