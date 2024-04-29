-- Check if the 'deployment' database exists, if not, create it
CREATE DATABASE IF NOT EXISTS `deployment`;

-- Use the 'deployment' database
USE `deployment`;


CREATE TABLE IF NOT EXISTS `deployment`.`roles` (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

-- Create Vendor Table
CREATE TABLE IF NOT EXISTS `deployment`.`vendors` (
    vendor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vendor_name VARCHAR(255) NOT NULL,
    registration_date DATE
);

CREATE TABLE IF NOT EXISTS `deployment`.`users` (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    vendor_id BIGINT NULL,
    FOREIGN KEY (vendor_id) REFERENCES `deployment`.`vendors`(vendor_id),
    FOREIGN KEY (role_id) REFERENCES `deployment`.`roles`(role_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`products` (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    product_description TEXT,
    shelf_life_in_days INT,
    expiry_date DATE,
    current_stock_level INT,
    min_acceptable_stock_level INT,
    unit_cost DECIMAL(10, 2),
    unit_selling_price DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS `deployment`.`raw_ingredients` (
    raw_ingredient_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    raw_ingredient_name VARCHAR(255) NOT NULL,
    raw_ingredient_description TEXT,
    raw_ingredient_quantity INT,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `deployment`.`products`(product_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`orders` (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE,
    order_status VARCHAR(50),
    vendor_id BIGINT,
    FOREIGN KEY (vendor_id) REFERENCES `deployment`.`vendors`(vendor_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`order_items` (
    order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    item_cost DECIMAL(10, 2),
    item_selling_price DECIMAL(10, 2),
    item_profit DECIMAL(10, 2),
    product_id BIGINT,
    order_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `deployment`.`products`(product_id),
    FOREIGN KEY (order_id) REFERENCES `deployment`.`orders`(order_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`reports` (
    report_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    report_name VARCHAR(255) NOT NULL,
    report_description TEXT,
    file_path VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `deployment`.`payment_data` (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    payment_method VARCHAR(50),
    transaction_amount DECIMAL(10, 2),
    payment_status VARCHAR(50),
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES `deployment`.`orders`(order_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`sales_data` (
    sale_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_date DATE,
    revenue DECIMAL(10, 2),
    order_volume INT
);

CREATE TABLE IF NOT EXISTS `deployment`.`order_fulfillment_data` (
    fulfillment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    processing_time_hours DECIMAL(10, 2),
    fulfillment_rate DECIMAL(5, 2),
    shipping_performance VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES `deployment`.`orders`(order_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`notification_data` (
    notification_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    timestamp DATETIME,
    delivery_channel VARCHAR(50),
    message TEXT,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `deployment`.`products`(product_id)
);

CREATE TABLE IF NOT EXISTS `deployment`.`alert_data` (
    alert_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    alert_type VARCHAR(50),
    alert_message TEXT,
    alert_timestamp DATETIME,
    notification_id BIGINT,
    FOREIGN KEY (notification_id) REFERENCES `deployment`.`notification_data`(notification_id)
);

-- Check if the 'test' database exists, if not, create it
CREATE DATABASE IF NOT EXISTS `test`;

-- Use the 'test' database
USE `test`;

-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop or truncate dependent tables first
DROP TABLE IF EXISTS `test`.`order_items`;
DROP TABLE IF EXISTS `test`.`order_fulfillment_data`;
DROP TABLE IF EXISTS `test`.`reports_sales_data`;
DROP TABLE IF EXISTS `test`.`sales_data`;
DROP TABLE IF EXISTS `test`.`payment_data`;
DROP TABLE IF EXISTS `test`.`users`;
DROP TABLE IF EXISTS `test`.`recipes`;
-- Drop other dependent tables here if needed

-- Drop the notification_data table
DROP TABLE IF EXISTS `test`.`notification_data`;

-- Drop the orders table
DROP TABLE IF EXISTS `test`.`orders`;

-- Drop the vendors table
DROP TABLE IF EXISTS `test`.`vendors`;

-- Drop other tables if needed
DROP TABLE IF EXISTS `test`.`raw_ingredients`;
DROP TABLE IF EXISTS `test`.`reports`;
DROP TABLE IF EXISTS `test`.`products`;
DROP TABLE IF EXISTS `test`.`alert_data`;
DROP TABLE IF EXISTS `test`.`roles`;

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Create tables in the 'test' database if they do not already exist

CREATE TABLE IF NOT EXISTS `test`.`roles` (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

-- Create Vendor Table
CREATE TABLE IF NOT EXISTS `test`.`vendors` (
    vendor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vendor_name VARCHAR(255) NOT NULL,
    registration_date DATE
);

CREATE TABLE IF NOT EXISTS `test`.`users` (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    vendor_id BIGINT NULL,
    FOREIGN KEY (vendor_id) REFERENCES `test`.`vendors`(vendor_id),
    FOREIGN KEY (role_id) REFERENCES `test`.`roles`(role_id)
);

CREATE TABLE IF NOT EXISTS `test`.`products` (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    sku_code VARCHAR(255) NOT NULL,
    product_description TEXT,
    shelf_life_in_days INT,
    expiry_date DATE,
    current_stock_level INT,
    min_acceptable_stock_level INT,
    unit_cost DECIMAL(10, 2),
    unit_selling_price DECIMAL(10, 2),
    image_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `test`.`recipes` (
    recipe_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `test`.`products`(product_id)
);


CREATE TABLE IF NOT EXISTS `test`.`raw_ingredients` (
    raw_ingredient_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    raw_ingredient_name VARCHAR(255) NOT NULL,
    raw_ingredient_description TEXT,
    raw_ingredient_quantity INT,
    recipe_id BIGINT,
    FOREIGN KEY (recipe_id) REFERENCES `test`.`recipes`(recipe_id)
);

CREATE TABLE IF NOT EXISTS `test`.`orders` (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATE,
    order_status VARCHAR(50),
    vendor_id BIGINT,
    FOREIGN KEY (vendor_id) REFERENCES `test`.`vendors`(vendor_id)
);

CREATE TABLE IF NOT EXISTS `test`.`order_items` (
    order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    item_cost DECIMAL(10, 2),
    item_selling_price DECIMAL(10, 2),
    item_profit DECIMAL(10, 2),
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

CREATE TABLE IF NOT EXISTS `test`.`sales_data` (
    sale_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_date DATE,
    revenue DECIMAL(10, 2),
    order_volume INT,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES `test`.`orders`(order_id)
);

-- Updated Payment Data Table
CREATE TABLE IF NOT EXISTS `test`.`payment_data` (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    payment_method VARCHAR(50),
    transaction_amount DECIMAL(10, 2),
    payment_status VARCHAR(50),
    sale_id BIGINT,
    FOREIGN KEY (sale_id) REFERENCES `test`.`sales_data`(sale_id)
);

CREATE TABLE IF NOT EXISTS `test`.`reports_sales_data` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    report_id BIGINT,
    sale_id BIGINT,
    FOREIGN KEY (report_id) REFERENCES `test`.`reports`(report_id),
    FOREIGN KEY (sale_id) REFERENCES `test`.`sales_data`(sale_id)
);

CREATE TABLE IF NOT EXISTS `test`.`order_fulfillment_data` (
    fulfillment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    processing_time_hours DECIMAL(10, 2),
    fulfillment_rate DECIMAL(5, 2),
    shipping_performance VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES `test`.`orders`(order_id)
);

CREATE TABLE IF NOT EXISTS `test`.`alert_data` (
    alert_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    alert_type VARCHAR(50),
    alert_message TEXT,
    alert_timestamp DATETIME,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES `test`.`products`(product_id)
);

CREATE TABLE IF NOT EXISTS `test`.`notification_data` (
    notification_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    timestamp DATETIME,
    delivery_channel VARCHAR(50),
    message TEXT,
    recipient_information VARCHAR(50),
    alert_id BIGINT,
    FOREIGN KEY (alert_id) REFERENCES `test`.`alert_data`(alert_id)
);


-- Drop the existing procedures if they exist
DROP PROCEDURE IF EXISTS `test`.PopulateProducts;
DROP PROCEDURE IF EXISTS `test`.PopulateRawIngredients;
DROP PROCEDURE IF EXISTS `test`.PopulateOrders;
DROP PROCEDURE IF EXISTS `test`.PopulateOrderItems;
DROP PROCEDURE IF EXISTS `test`.PopulateReports;
DROP PROCEDURE IF EXISTS `test`.PopulateTestTables;
DROP PROCEDURE IF EXISTS `test`.PopulateUsers;
DROP PROCEDURE IF EXISTS `test`.PopulateRoles;
DROP PROCEDURE IF EXISTS `test`.PopulateVendors;
DROP PROCEDURE IF EXISTS `test`.PopulateNotificationData;
DROP PROCEDURE IF EXISTS `test`.PopulateAlertData;
DROP PROCEDURE IF EXISTS `test`.PopulatePaymentData;
DROP PROCEDURE IF EXISTS `test`.PopulateSalesData;
DROP PROCEDURE IF EXISTS `test`.PopulateOrderFulfillmentData;
DROP PROCEDURE IF EXISTS `test`.PopulateReportsSalesData;
DROP PROCEDURE IF EXISTS `test`.UpdateOrderItems;
DROP PROCEDURE IF EXISTS `test`.`PopulateRecipes`;

DELIMITER //

-- Create the PopulateProducts procedure
CREATE PROCEDURE `test`.PopulateProducts()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE url_index INT DEFAULT 1;
    DECLARE image_urls VARCHAR(4096); -- Increased buffer size to handle longer URLs
    DECLARE num_urls INT;

    -- Initialize the list of image URLs
    SET image_urls = 'products/crackers-orange.jpg,products/matzo-crackers-small-red.jpg,products/100713_RAK_MATZOS_TEA_RED_150G_ART01_AG1.jpg,products/100713_RAK_SNACKERS_CHOCOLATE_90G_ART03_SM1.jpg,products/3275527-1.jpg,products/farina-potato-flour-.jpg,products/fine_matzo_meal.jpg,products/gluten-free-crackers-front.jpg,products/GLUTENFREE_175G.jpg,products/HI_BAKED_150G-500px.jpg,products/MATZO_CRACKER_150g.jpg,products/MATZOS_BIGBLUE_300G.jpg,products/RAK_BISUITS_DIGESTIVE_AD01_AG.jpg,products/RAK_CHOC_DIGESTIVE_AD01_AG.jpg,products/RAK_CHOC_OATIE_BISCUITS_AD01_AG.png,products/RAK_GF300_AD01_AG.png,products/RAK_GINGER2_BISCUIT_AG01.png,products/RAK_OATIE_CRUNCH_BISCUIT_AG01.png,products/RAK_Pass_300_AD01_AG.png,products/RAK_PASS_MED_MEAL_AD01_AG.png,products/RAK_VEGAN_CRACKERS_AD01_AG.png,products/Raksuens-HerbAndOnion-500px.jpg,products/Rakusens-Fine-Matzo-Meal.jpg,products/Rakusens-gluten-free-snackers.jpg,products/Rakusens-Medium-Matzo-Meal.jpg,products/Rakusens_Baked_Beans.jpg,products/Rakusens_CarrotAndLentil.jpg,products/Rakusens_ThickPea.jpg,products/Rakusens_TomatoAndBasil.jpg,products/Snacker_Visuals_PLAIN_AG01.jpg,products/TEA_MATZOS_PINK_150G.jpg,products/Tomor-sunflower-margarine.jpg,products/Tomor-vegetarian-margarine.jpg';

    -- Split the image URLs into an array
    SET num_urls = (LENGTH(image_urls) - LENGTH(REPLACE(image_urls, ',', '')) + 1);

    WHILE i < max_records DO
        -- Generate random product_name, sku_code, product_description
        SET @product_name = CONCAT('Product ', i);
        SET @sku_code = CONCAT('SKU', i);
        SET @product_description = CONCAT('Description for Product ', i);

        -- Generate random product_price, shelf_life_in_days, expiry_date, current_stock_level, min_acceptable_stock_level
        SET @shelf_life_in_days = FLOOR(1 + RAND() * 365);
        SET @expiry_date = DATE_ADD(CURRENT_DATE(), INTERVAL @shelf_life_in_days DAY);
        SET @current_stock_level = FLOOR(1 + RAND() * 100);
        SET @min_acceptable_stock_level = FLOOR(0.2 * @current_stock_level);

        -- Generate random unit_cost and unit_selling_price
        SET @unit_cost = ROUND(5 + RAND() * 500, 2);
        SET @unit_selling_price = ROUND(@unit_cost * (1 + RAND()), 2);

        -- Insert into products table
        INSERT INTO `test`.`products` (product_name, sku_code, product_description, shelf_life_in_days, expiry_date, current_stock_level, min_acceptable_stock_level, unit_cost, unit_selling_price, image_url)
        VALUES (@product_name, @sku_code, @product_description, @shelf_life_in_days, @expiry_date, @current_stock_level, @min_acceptable_stock_level, @unit_cost, @unit_selling_price, TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(image_urls, ',', url_index), ',', -1)));

        SET i = i + 1;
        SET url_index = url_index + 1;

        -- Reset url_index if it exceeds the number of URLs
        IF url_index > num_urls THEN
            SET url_index = 1;
        END IF;
    END WHILE;
END//



-- Create the PopulateRawMaterials procedure
CREATE PROCEDURE `test`.PopulateRawIngredients()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;

    WHILE i < max_records DO
        -- Generate random raw_ingredient_name, raw_ingredient_description
        SET @raw_ingredient_name = CONCAT('Raw ingredient ', i);
        SET @raw_ingredient_description = CONCAT('Description for Raw ingredient ', i);

        -- Generate random raw_ingredient_quantity
        SET @raw_ingredient_quantity = ROUND(10 + RAND() * 1000, 2);

        -- Select a random product_id from the products table
        SELECT recipe_id INTO @recipe_id FROM `test`.`recipes` ORDER BY RAND() LIMIT 1;

        -- Insert into table
        INSERT INTO `test`.`raw_ingredients` (raw_ingredient_name, raw_ingredient_description, raw_ingredient_quantity, recipe_id)
        VALUES (@raw_ingredient_name, @raw_ingredient_description, @raw_ingredient_quantity, @recipe_id);

        SET i = i + 1;
    END WHILE;
END//

-- Create the PopulateVendor procedure
CREATE PROCEDURE `test`.PopulateVendors()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE registration_date DATE;

    WHILE i < max_records DO
        -- Generate random registration_date within the last year
        SET registration_date = DATE_SUB(CURRENT_DATE(), INTERVAL FLOOR(1 + RAND() * 365) DAY);

        -- Generate random vendor_name
        SET @vendor_name = CONCAT('Vendor ', i);

        -- Insert into vendors table
        INSERT INTO `test`.`vendors` (vendor_name, registration_date)
        VALUES (@vendor_name, registration_date);

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
    DECLARE vendor_count INT;
    DECLARE vendor_id_val INT;

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

        -- Select a random vendor_id from the vendors table
        SELECT COUNT(*) INTO vendor_count FROM `test`.`vendors`;
        SET vendor_id_val = FLOOR(1 + RAND() * vendor_count);

        -- Insert into orders table in the test schema
        INSERT INTO `test`.`orders` (order_date, order_status, vendor_id)
        VALUES (order_date, order_status, vendor_id_val);

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

DELIMITER //

CREATE PROCEDURE `test`.UpdateOrderItems()
BEGIN
    -- Update item_cost and item_selling_price
    UPDATE `test`.`order_items` oi
    JOIN `test`.`products` p ON oi.product_id = p.product_id
    SET oi.item_cost = oi.quantity * p.unit_cost,
        oi.item_selling_price = oi.quantity * p.unit_selling_price;

    -- Update item_profit
    UPDATE `test`.`order_items`
    SET item_profit = item_selling_price - item_cost;
END//

DELIMITER //

CREATE PROCEDURE `test`.PopulateOrderItems()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_orders INT DEFAULT 1000;
    DECLARE product_count INT;
    DECLARE order_id_val INT;
    DECLARE product_id_val INT;
    DECLARE quantity_val INT;

    WHILE i < max_orders DO
        -- Randomly select an order from the orders table
        SELECT order_id INTO order_id_val FROM `test`.`orders` ORDER BY RAND() LIMIT 1;

        -- Randomly select a product from the products table
        SELECT COUNT(*) INTO product_count FROM `test`.`products`;
        SET product_id_val = FLOOR(1 + RAND() * product_count);

        -- Generate random quantity and price for the order item
        SET quantity_val = FLOOR(1 + RAND() * 10);

        -- Insert into order_item table
        INSERT INTO `test`.`order_items` (order_id, product_id, quantity)
        VALUES (order_id_val, product_id_val, quantity_val);

        SET i = i + 1;
    END WHILE;
       -- After populating order items, update with calculated values
        CALL `test`.UpdateOrderItems();
END//

CREATE PROCEDURE `test`.PopulateRoles()
BEGIN
    DECLARE role_id_external_vendor INT;
    DECLARE role_id_employee INT;
    DECLARE role_id_admin INT;

    -- Check if the roles already exist
    SELECT role_id INTO role_id_external_vendor FROM `test`.`roles` WHERE role_name = 'EXTERNAL';
    SELECT role_id INTO role_id_employee FROM `test`.`roles` WHERE role_name = 'EMPLOYEE';
    SELECT role_id INTO role_id_admin FROM `test`.`roles` WHERE role_name = 'ADMIN';

    -- Insert roles if they do not exist
    IF role_id_external_vendor IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('EXTERNAL');
    END IF;

    IF role_id_employee IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('EMPLOYEE');
    END IF;

    IF role_id_admin IS NULL THEN
        INSERT INTO `test`.`roles` (role_name) VALUES ('ADMIN');
    END IF;
END//

CREATE PROCEDURE `test`.PopulateUsers()
BEGIN
    DECLARE employee_count INT;
    DECLARE employee_id_val INT;
    DECLARE employee_id_admin INT;
    DECLARE vendor_id_val INT;
    DECLARE admin_id_val INT;

    -- Get a random vendor_id from the vendors table
    SELECT vendor_id INTO vendor_id_val FROM `test`.`vendors` ORDER BY RAND() LIMIT 1;

    -- Insert into users table for external vendor
    INSERT INTO `test`.`users` (username, password, role_id, vendor_id)
    VALUES ('test_vendor', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', 1, vendor_id_val);

    -- Insert into users table for employee
    INSERT INTO `test`.`users` (username, password, role_id)
    VALUES ('test_employee', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', 2);

    -- Insert into users table for admin
    INSERT INTO `test`.`users` (username, password, role_id)
    VALUES ('test_admin', '420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380', 3);
END//



CREATE PROCEDURE `test`.PopulateNotificationData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE alert_count INT;
    DECLARE alert_id_val INT;

    -- Get count of alerts
    SELECT COUNT(*) INTO alert_count FROM `test`.`alert_data`;

    WHILE i < max_records DO
        -- Randomly select an alert_id from the alert_data table
        SET alert_id_val = FLOOR(1 + RAND() * alert_count);

        -- Generate random timestamp
        SET @timestamp = CURRENT_TIMESTAMP();

        -- Generate random delivery_channel
        SET @delivery_channel = CASE FLOOR(1 + RAND() * 3)
                                    WHEN 1 THEN 'Email'
                                    WHEN 2 THEN 'SMS'
                                    ELSE 'App Notification'
                                END;

        -- Generate random message
        SET @message = CONCAT('Notification message ', i);

        -- Generate random recipient_information
        SET @recipient_information = CONCAT('Recipient info ', i);

        -- Insert into notification_data table
        INSERT INTO `test`.`notification_data` (timestamp, delivery_channel, message, recipient_information, alert_id)
        VALUES (@timestamp, @delivery_channel, @message, @recipient_information, alert_id_val);

        SET i = i + 1;
    END WHILE;
END //

-- Create the PopulateAlertData procedure
CREATE PROCEDURE `test`.PopulateAlertData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE product_count INT;
    DECLARE product_id_val INT;

    -- Get count of products
    SELECT COUNT(*) INTO product_count FROM `test`.`products`;

    WHILE i < max_records DO
        -- Randomly select a product_id from the products table
        SET product_id_val = FLOOR(1 + RAND() * product_count);

        -- Generate random alert_type
        SET @alert_type = CASE FLOOR(1 + RAND() * 2)
                                WHEN 1 THEN 'Out of Date'
                                WHEN 2 THEN 'Low in Stock'
                            END;

        -- Generate random alert_message
        SET @alert_message = CONCAT('Alert message for product ', product_id_val);

        -- Generate random alert_timestamp
        SET @alert_timestamp = CURRENT_TIMESTAMP();

        -- Insert into alert_data table
        INSERT INTO `test`.`alert_data` (alert_type, alert_message, alert_timestamp, product_id)
        VALUES (@alert_type, @alert_message, @alert_timestamp, product_id_val);

        SET i = i + 1;
    END WHILE;
END//

-- Create the procedure for populating order fulfillment data
CREATE PROCEDURE `test`.PopulateOrderFulfillmentData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE order_id_val BIGINT;
    DECLARE processing_time DECIMAL(10, 2);
    DECLARE fulfillment_rate DECIMAL(5, 2);
    DECLARE shipping_performance VARCHAR(255);

    WHILE i < max_records DO
        -- Generate random order fulfillment data
        SET order_id_val = FLOOR(1 + RAND() * 1000); -- Assuming order_id exists in orders table
        SET processing_time = ROUND(1 + RAND() * 48, 2); -- Hours
        SET fulfillment_rate = ROUND(RAND(), 2);
        SET shipping_performance = CASE FLOOR(1 + RAND() * 3)
                                        WHEN 1 THEN 'On-time'
                                        WHEN 2 THEN 'Delayed'
                                        ELSE 'Unknown'
                                   END;

        -- Insert into order_fulfillment_data table
        INSERT INTO `test`.`order_fulfillment_data` (order_id, processing_time_hours, fulfillment_rate, shipping_performance)
        VALUES (order_id_val, processing_time, fulfillment_rate, shipping_performance);

        SET i = i + 1;
    END WHILE;
END//

-- Create the procedure for populating sales data
CREATE PROCEDURE `test`.PopulateSalesData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE transaction_date DATE;
    DECLARE revenue DECIMAL(10, 2);
    DECLARE order_volume INT;
    DECLARE order_id_val BIGINT;

    WHILE i < max_records DO
        -- Generate random sales data
        SET transaction_date = NOW() - INTERVAL FLOOR(1 + RAND() * 365) DAY;

        -- Randomly select an order_id from the orders table
        SELECT order_id INTO order_id_val FROM `test`.`orders` ORDER BY RAND() LIMIT 1;

        -- Initialize order_volume and revenue to 0 to enter the loop
        SET order_volume = 0;
        SET revenue = 0;

        -- Calculate order_volume and revenue
        SELECT SUM(item_profit), COUNT(*) INTO revenue, order_volume FROM `test`.`order_items` WHERE order_id = order_id_val;

        -- Loop until a non-zero order_volume is obtained
        WHILE order_volume = 0 DO
            -- If order_volume is 0, select another random order_id
            IF order_volume = 0 THEN
                SELECT order_id INTO order_id_val FROM `test`.`orders` ORDER BY RAND() LIMIT 1;

                -- Recalculate order_volume and revenue
                SELECT SUM(item_profit), COUNT(*) INTO revenue, order_volume FROM `test`.`order_items` WHERE order_id = order_id_val;
            END IF;
        END WHILE;

        -- Insert into sales_data table with order_id foreign key and calculated order_volume and revenue
        INSERT INTO `test`.`sales_data` (transaction_date, revenue, order_volume, order_id)
        VALUES (transaction_date, revenue, order_volume, order_id_val);

        SET i = i + 1;
    END WHILE;
END//

-- Create the procedure to populate recipes
CREATE PROCEDURE `test`.PopulateRecipes()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 100;
    DECLARE product_id_val INT;

    WHILE i < max_records DO
        -- Generate random product_id
        SET product_id_val = FLOOR(1 + RAND() * 100); -- Adjust the range as per your actual product_id range

        -- Insert into recipes table with product_id foreign key
        INSERT INTO `test`.`recipes` (product_id)
        VALUES (product_id_val);

        SET i = i + 1;
    END WHILE;
END//
DELIMITER //

-- Create the procedure for populating payment data with sale_id foreign key
CREATE PROCEDURE `test`.PopulatePaymentData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE payment_method VARCHAR(50);
    DECLARE transaction_amount DECIMAL(10, 2);
    DECLARE payment_status VARCHAR(50);
    DECLARE sale_id_val BIGINT;

    WHILE i < max_records DO
        -- Generate random payment data
        SET payment_method = CASE FLOOR(1 + RAND() * 3)
                                WHEN 1 THEN 'Credit Card'
                                WHEN 2 THEN 'PayPal'
                                ELSE 'Bank Transfer'
                            END;
        SET transaction_amount = ROUND(10 + RAND() * 1000, 2);
        SET payment_status = CASE FLOOR(1 + RAND() * 3)
                                WHEN 1 THEN 'Success'
                                WHEN 2 THEN 'Pending'
                                ELSE 'Failed'
                             END;
        -- Fetch a random sale_id from existing sales_data
        SELECT sale_id INTO sale_id_val FROM `test`.`sales_data` ORDER BY RAND() LIMIT 1;

        -- Insert into payment_data table with sale_id foreign key
        INSERT INTO `test`.`payment_data` (payment_method, transaction_amount, payment_status, sale_id)
        VALUES (payment_method, transaction_amount, payment_status, sale_id_val);

        SET i = i + 1;
    END WHILE;
END//


CREATE PROCEDURE `test`.PopulateReportsSalesData()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_records INT DEFAULT 1000;
    DECLARE report_count INT;
    DECLARE sale_count INT;
    DECLARE report_id_val BIGINT;
    DECLARE sale_id_val BIGINT;

    -- Get count of reports and sales_data
    SELECT COUNT(*) INTO report_count FROM `test`.`reports`;
    SELECT COUNT(*) INTO sale_count FROM `test`.`sales_data`;

    WHILE i < max_records DO
        -- Randomly select a report_id and sale_id
        SET report_id_val = FLOOR(1 + RAND() * report_count);
        SET sale_id_val = FLOOR(1 + RAND() * sale_count);

        -- Insert into reports_sales_data table
        INSERT INTO `test`.`reports_sales_data` (report_id, sale_id)
        VALUES (report_id_val, sale_id_val);

        SET i = i + 1;
    END WHILE;
END//



CREATE PROCEDURE `test`.PopulateTestTables()
BEGIN

	-- Call PopulateVendors
	CALL `test`.PopulateVendors();

	-- Call PopulateProducts
	CALL `test`.PopulateProducts();

    CALL `test`.PopulateRecipes();

	-- Call PopulateRawMaterials
	CALL `test`.PopulateRawIngredients();

	-- Call PopulateOrders procedure
	CALL `test`.PopulateOrders();

	-- Call PopulateReports procedure
	CALL `test`.PopulateReports();

	CALL `test`.PopulateRoles();

	CALL `test`.PopulateUsers();

	CALL `test`.PopulateOrderItems();

    CALL `test`.PopulateAlertData();

	CALL `test`.PopulateNotificationData();

	CALL `test`.PopulateOrderFulfillmentData();

	CALL `test`.PopulateSalesData();

    CALL `test`.PopulatePaymentData();

	CALL `test`.PopulateReportsSalesData();

END//

-- Populate the products table
CALL `test`.PopulateProducts();


DELIMITER ;
