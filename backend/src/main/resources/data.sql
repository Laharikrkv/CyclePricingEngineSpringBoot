LOAD DATA LOCAL INFILE 'D:/CSV Files/parts.csv'
INTO TABLE parts
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(name, component);

LOAD DATA LOCAL INFILE 'D:/CSV Files/prices.csv'
INTO TABLE prices
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(part_id, valid_from, @valid_till, amount)
SET valid_till = NULLIF(@valid_till, '');