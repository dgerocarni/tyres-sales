The Tyres Sales REST-API receives the input event, store the information in a relational DB (MySQL) and provide KPIs in output through many endpoints.

The endpoint /entity/sales can be reacheble in POST method and it receives data for insert/update (input events).
The endpoint /entity/topMarkets can be reachable in GET method and it provide data about TOP 5 Markets by Sales.
The endpoint /entity/topAreas can be reachable in GET method and it provide data about TOP 5 Areas by Sales.
The endpoint /entity/regions can be reachable in GET method and it provide data about Sales by regions in descending order.
The endpoint /entity/globalSales can be reachable in GET method and it provide data about Sum of sales globally.

The rest-api was developed in Spring MVC framework  and Junit tests have been added by using following components:

  1. MockMvc
  2. Embedded DBMS H2
  3. Junit
