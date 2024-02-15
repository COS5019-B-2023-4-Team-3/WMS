-- Check if the 'test' database exists, if not, create it
CREATE DATABASE IF NOT EXISTS `test`;

-- Use the 'test' database
USE `test`;

-- Check if the 'deployment' database exists, if not, create it
CREATE DATABASE IF NOT EXISTS `deployment`;

-- Use the 'deployment' database
USE `deployment`;

-- Disable foreign key checks
SET GLOBAL FOREIGN_KEY_CHECKS = 0;

-- Drop or truncate and reset AUTO INCREMENT for all 'test' tables
DROP TABLE IF EXISTS `test`.`order_item`;
DROP TABLE IF EXISTS `test`.`orders`;
DROP TABLE IF EXISTS `test`.`users`;
DROP TABLE IF EXISTS `test`.`raw_materials`;
DROP TABLE IF EXISTS `test`.`reports`;
DROP TABLE IF EXISTS `test`.`roles`;
DROP TABLE IF EXISTS `test`.`products`;

-- Enable foreign key checks
SET GLOBAL FOREIGN_KEY_CHECKS = 1;

-- Create tables in the 'test' database if they do not already exist
CREATE TABLE IF NOT EXISTS `test`.`roles` (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `test`.`users` (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES `test`.`roles`(role_id)
);

CREATE TABLE IF NOT EXISTS `test`.`products` (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    product_description TEXT,    
    product_price DECIMAL(10, 2),
    shelf_life_in_days INT,
    expiry_date DATE,
    current_stock_level INT,
    min_acceptable_stock_level INT
);

CREATE TABLE IF NOT EXISTS `test`.`raw_materials` (
    raw_material_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    raw_material_name VARCHAR(255) NOT NULL,
    raw_material_description TEXT,
    raw_material_quantity INT
);

CREATE TABLE IF NOT EXISTS `test`.`orders` (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE,
    order_status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `test`.`order_item` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    price DECIMAL(10, 2),
    product_id BIGINT,
    order_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `test`.`products`(product_id),
    FOREIGN KEY (order_id) REFERENCES `test`.`orders`(order_id)
);

CREATE TABLE IF NOT EXISTS `test`.`reports` (
    report_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    report_name VARCHAR(255) NOT NULL,
    report_description TEXT,
    file_path VARCHAR(255)
);

-- Create tables in the 'deployment' database if they do not already exist
CREATE TABLE IF NOT EXISTS `deployment`.`roles` (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `deployment`.`users` (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES `deployment`.`roles`(role_id)
);


CREATE TABLE IF NOT EXISTS `deployment`.`products` (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    product_description TEXT,    
    product_price DECIMAL(10, 2),
    shelf_life_in_days INT,
    expiry_date DATE,
    current_stock_level INT,
    min_acceptable_stock_level INT
);


CREATE TABLE IF NOT EXISTS `deployment`.`raw_materials` (
    raw_material_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    raw_material_name VARCHAR(255) NOT NULL,
    raw_material_description TEXT,
    raw_material_quantity INT
);

CREATE TABLE IF NOT EXISTS `deployment`.`orders` (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE,
    order_status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `deployment`.`OrderItem` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    price DECIMAL(10, 2),
    product_id BIGINT,
    order_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `deployment`.`products`(product_id),
    FOREIGN KEY (order_id) REFERENCES `deployment`.`orders`(order_id)
);

-- Drop the existing procedures if they exist
DROP PROCEDURE IF EXISTS `test`.PopulateProducts;
DROP PROCEDURE IF EXISTS `test`.PopulateRawMaterials;
DROP PROCEDURE IF EXISTS `test`.PopulateOrders;
DROP PROCEDURE IF EXISTS `test`.PopulateReports;
DROP PROCEDURE IF EXISTS `test`.PopulateTestTables;

DELIMITER //

-- Create the PopulateProducts procedure
CREATE PROCEDURE `test`.PopulateProducts()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    
    WHILE i < max_records DO
        -- Generate random product_name, sku_code, product_description
        SET @product_name = CONCAT('Product ', i);
        SET @sku_code = CONCAT('SKU', i);
        SET @product_description = CONCAT('Description for Product ', i);
        
        -- Generate random product_price, shelf_life_in_days, expiry_date, current_stock_level, min_acceptable_stock_level
        SET @product_price = ROUND(10 + RAND() * 1000, 2);
        SET @shelf_life_in_days = FLOOR(1 + RAND() * 365);
        SET @expiry_date = DATE_ADD(CURRENT_DATE(), INTERVAL @shelf_life_in_days DAY);
        SET @current_stock_level = FLOOR(1 + RAND() * 100);
        SET @min_acceptable_stock_level = FLOOR(0.2 * @current_stock_level);
        
        -- Insert into products table
        INSERT INTO `test`.`products` (product_name, sku_code, product_description, product_price, shelf_life_in_days, expiry_date, current_stock_level, min_acceptable_stock_level)
        VALUES (@product_name, @sku_code, @product_description, @product_price, @shelf_life_in_days, @expiry_date, @current_stock_level, @min_acceptable_stock_level);
        
        SET i = i + 1;
    END WHILE;
END//

-- Create the PopulateRawMaterials procedure
CREATE PROCEDURE `test`.PopulateRawMaterials()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    
    WHILE i < max_records DO
        -- Generate random raw_material_name, raw_material_description
        SET @raw_material_name = CONCAT('Raw Material ', i);
        SET @raw_material_description = CONCAT('Description for Raw Material ', i);
        
        -- Generate random raw_material_quantity
        SET @raw_material_quantity = ROUND(10 + RAND() * 1000, 2);
        
        -- Insert into raw_materials table
        INSERT INTO `test`.`raw_materials` (raw_material_name, raw_material_description, raw_material_quantity)
        VALUES (@raw_material_name, @raw_material_description, @raw_material_quantity);
        
        SET i = i + 1;
    END WHILE;
END//

-- Create the PopulateOrders procedure
CREATE PROCEDURE `test`.PopulateOrders()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE random_days INT;
    DECLARE order_date DATE;
    DECLARE order_status VARCHAR(50);
    
    WHILE i < max_records DO
        -- Generate random order_date within the last 14 days
        SET random_days = FLOOR(1 + RAND() * 14);
        SET order_date = DATE_SUB(CURRENT_DATE(), INTERVAL random_days DAY);
        
        -- Generate random order_status
        SET order_status = CASE FLOOR(1 + RAND() * 3)
                            WHEN 1 THEN 'pending'
                            WHEN 2 THEN 'fulfilled'
                            ELSE 'shipped'
                          END;
        
        -- Insert into orders table in the test schema
        INSERT INTO `test`.`orders` (order_date, order_status)
        VALUES (order_date, order_status);
        
        SET i = i + 1;
    END WHILE;
END//


-- Create the PopulateReports procedure
CREATE PROCEDURE `test`.PopulateReports()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE random_num INT;
    DECLARE report_name VARCHAR(255);
    DECLARE report_description VARCHAR(255);
    DECLARE file_path VARCHAR(255);
    
    WHILE i < max_records DO
        -- Generate random number (1-1000)
        SET random_num = FLOOR(1 + RAND() * 1000);
        
        -- Set report_name, report_description, and file_path using random number
        SET report_name = CONCAT('Report Name ', random_num);
        SET report_description = CONCAT('Description for Report ', random_num);
        SET file_path = CONCAT('path/to/report/', random_num);
        
        -- Insert into reports table
        INSERT INTO `test`.`reports` (report_name, report_description, file_path)
        VALUES (report_name, report_description, file_path);
        
        SET i = i + 1;
    END WHILE;
END//

CREATE PROCEDURE `test`.PopulateOrderItems()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_orders INT DEFAULT 100;
    DECLARE product_count INT;
    DECLARE order_id_val INT;
    DECLARE product_id_val INT;
    DECLARE quantity_val INT;
    DECLARE price_val DECIMAL(10, 2);
    
    WHILE i < max_orders DO
        -- Randomly select an order from the orders table
        SELECT order_id INTO order_id_val FROM `test`.`orders` ORDER BY RAND() LIMIT 1;
        
        -- Randomly select a product from the products table
        SELECT COUNT(*) INTO product_count FROM `test`.`products`;
        SET product_id_val = FLOOR(1 + RAND() * product_count);
        
        -- Generate random quantity and price for the order item
        SET quantity_val = FLOOR(1 + RAND() * 10);
        SET price_val = ROUND(10 + RAND() * 100, 2);
        
        -- Insert into order_item table
        INSERT INTO `test`.`order_item` (order_id, product_id, quantity, price)
        VALUES (order_id_val, product_id_val, quantity_val, price_val);
        
        SET i = i + 1;
    END WHILE;
END//

CREATE PROCEDURE `test`.PopulateRoles()
BEGIN
    DECLARE role_id_external_vendor INT;
    DECLARE role_id_employee INT;
    DECLARE role_id_admin INT;

    -- Check if the roles already exist
    SELECT role_id INTO role_id_external_vendor FROM `test`.`roles` WHERE role_name = 'External_Vendor';
    SELECT role_id INTO role_id_employee FROM `test`.`roles` WHERE role_name = 'Employee';
    SELECT role_id INTO role_id_admin FROM `test`.`roles` WHERE role_name = 'Admin';

    -- Insert roles if they do not exist
    IF role_id_external_vendor IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('External_Vendor');
    END IF;

    IF role_id_employee IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('Employee');
    END IF;

    IF role_id_admin IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('Admin');
    END IF;
END//

CREATE PROCEDURE `test`.PopulateUsers()
BEGIN
    DECLARE role_id_external_vendor INT;
    DECLARE role_id_employee INT;
    DECLARE role_id_admin INT;

    -- Get role IDs
    SELECT role_id INTO role_id_external_vendor FROM `test`.`roles` WHERE role_name = 'External_Vendor';
    SELECT role_id INTO role_id_employee FROM `test`.`roles` WHERE role_name = 'Employee';
    SELECT role_id INTO role_id_admin FROM `test`.`roles` WHERE role_name = 'Admin';

    -- Insert users with hashed passwords
    INSERT INTO `test`.`users` (username, password, role_id)
    VALUES ('test_vendor', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', role_id_external_vendor),
           ('test_employee', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', role_id_employee),
           ('test_admin', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', role_id_admin);
END//


CREATE PROCEDURE `test`.PopulateTables()
BEGIN
	-- Call PopulateProducts
	CALL `test`.PopulateProducts();

	-- Call PopulateRawMaterials
	CALL `test`.PopulateRawMaterials();

	-- Call PopulateOrders procedure
	CALL `test`.PopulateOrders();

	-- Call PopulateReports procedure
	CALL `test`.PopulateReports();
	
	CALL `test`.PopulateRoles();
	
	CALL `test`.PopulateUsers();
	
END//

DELIMITER ;
