CREATE TABLE CATEGORY (
  ID INT NOT NULL,
  NAME VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE PRODUCT (
  ID INT NOT NULL,
  NAME VARCHAR(20) NOT NULL,
  CATEGORY_ID INT,
  FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID),
  PRIMARY KEY (ID)
);
