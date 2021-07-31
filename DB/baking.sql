-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bakingdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bakingdb` ;

-- -----------------------------------------------------
-- Schema bakingdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bakingdb` DEFAULT CHARACTER SET utf8 ;
USE `bakingdb` ;

-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(75) NOT NULL,
  `last_name` VARCHAR(75) NULL,
  `email` VARCHAR(75) NULL,
  `phone` VARCHAR(12) NULL,
  `street` VARCHAR(75) NULL,
  `city` VARCHAR(50) NULL,
  `state_abbreviation` VARCHAR(2) NULL,
  `postal_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `requisition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `requisition` ;

CREATE TABLE IF NOT EXISTS `requisition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_placed` VARCHAR(45) NULL,
  `due_date` VARCHAR(45) NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product` ;

CREATE TABLE IF NOT EXISTS `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `unit_quantity` SMALLINT NOT NULL,
  `unit_price` DECIMAL(4,2) NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_product` ;

CREATE TABLE IF NOT EXISTS `order_product` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_order_has_product_product1_idx` (`product_id` ASC),
  INDEX `fk_order_has_product_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `requisition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS baker@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'baker'@'localhost' IDENTIFIED BY 'baker';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'baker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (1, 'Erin', 'Gates', 'eringates@example.com', '1234567890', '1234 N Nevada Ave', 'Colorado Springs', 'CO', '80903');

COMMIT;


-- -----------------------------------------------------
-- Data for table `requisition`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`) VALUES (1, '2021-07-30', '2021-08-02', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (1, 'Bagels', 6, 8, 'https://images.unsplash.com/photo-1585445490387-f47934b73b54?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2850&q=80');

COMMIT;

