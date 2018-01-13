The Tyres Sales REST-API receives the input event, store the information in a relational DB (MySQL) and provide KPIs in output through many endpoints.

List of endpoints:

 - /entity/topMarkets (GET) to retrieve information about TOP 5 Markets by Sales.
 - /entity/topAreas (GET) to retrieve information about TOP 5 Areas by Sales.
 - /entity/regions (GET) to retrieve information about Sales by region in descending order.
 - /entity/globalSales (GET) to retrieve information about the sum of sales globally.
 - /entity/sales (POST) for input events.

The rest-api was developed in Spring MVC framework  and Junit tests have been added by using following components:

  1. MockMvc
  2. Embedded DBMS H2
  3. Junit
