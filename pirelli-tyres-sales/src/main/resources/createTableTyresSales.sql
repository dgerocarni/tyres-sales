CREATE TABLE tyres_sales (
  event_id VARCHAR(100) NULL,
  region VARCHAR(100) NOT NULL,
  area VARCHAR(100) NOT NULL,
  market VARCHAR(100) NOT NULL,
  ipcode VARCHAR(45) NOT NULL,
  quantity INT NULL,
  timestamp BIGINT NULL,
  PRIMARY KEY (market, ipcode));

