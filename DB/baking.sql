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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `business_name` VARCHAR(255) NULL,
  `enabled` TINYINT(4) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `requisition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `requisition` ;

CREATE TABLE IF NOT EXISTS `requisition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_placed` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `due_date` VARCHAR(30) NOT NULL,
  `customer_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `completed` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  INDEX `fk_requisition_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requisition_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
-- Table `req_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `req_product` ;

CREATE TABLE IF NOT EXISTS `req_product` (
  `requisition_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `units_ordered` SMALLINT NOT NULL,
  PRIMARY KEY (`requisition_id`, `product_id`),
  INDEX `fk_order_has_product_product1_idx` (`product_id` ASC),
  INDEX `fk_order_has_product_order1_idx` (`requisition_id` ASC),
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`requisition_id`)
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
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (1, 'Erin', 'Gates', 'eringates@example.com', '1234567890', '1217 N Nevada Ave', 'Colorado Springs', 'CO', '80903');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (2, 'Kyle', 'Robison', 'kylerobison@example.com', '1234567891', '2727 N Cascade Ave', 'Colorado Springs', 'CO', '80907');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (3, 'Guy', 'Fierri', 'guyfierri@example.com', '1234567892', '702 W Colorado Ave', 'Colorado Springs', 'CO', '80905');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (4, 'Bobby', 'Flay', 'bobbyflay@example.com', '1234567893', '140 W Mountain Ave', 'Fort Collins', 'CO', '80524');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (5, 'Gabriel', 'Landeskog', 'gabelandeskog@example.com', '1234567894', '1000 Chopper Cir', 'Denver', 'CO', '80204');
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `street`, `city`, `state_abbreviation`, `postal_code`) VALUES (6, 'Nathan', 'MacKinnon', 'natedawg@example.com', '1234567895', '702 S Cascade Ave', 'Colorado Springs', 'CO', '80903');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `business_name`, `enabled`, `role`) VALUES (1, 'Kyle', 'Robison', 'test@example.com', 'ExampleUser', 'password', 'ExampleBiz', 1, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `requisition`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (1, '2021-06-30', '2021-07-02', 1, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (2, '2021-07-01', '2021-07-02', 2, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (3, '2021-07-08', '2021-07-02', 2, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (4, '2021-07-14', '2021-07-16', 2, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (5, '2021-07-14', '2021-07-16', 3, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (6, '2021-07-14', '2021-07-16', 4, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (7, '2021-07-15', '2021-07-17', 5, 1, 1);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (8, '2021-08-12', '2021-08-30', 6, 1, 0);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (9, '2021-08-13', '2021-08-30', 1, 1, 0);
INSERT INTO `requisition` (`id`, `date_placed`, `due_date`, `customer_id`, `user_id`, `completed`) VALUES (10, '2021-08-14', '2021-08-30', 1, 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (1, 'Bagels', 6, 8, 'https://images.unsplash.com/photo-1585445490387-f47934b73b54?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2850&q=80');
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (2, 'Sourdough', 1, 12, 'https://images.unsplash.com/photo-1600398138360-ae1ac2285bc3?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2734&q=80');
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (3, 'English Muffins', 6, 8, 'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F865284.jpg');
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (4, 'Pretzels', 6, 8, 'https://www.recipesfromeurope.com/wp-content/uploads/2021/01/german-pretzels-in-basket-with-radler-720x540.jpg');
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (5, 'Naan', 6, 10, 'https://rasamalaysia.com/wp-content/uploads/2019/03/naan3.jpg');
INSERT INTO `product` (`id`, `name`, `unit_quantity`, `unit_price`, `image_url`) VALUES (6, 'Croissants', 6, 10, 'https://images.unsplash.com/photo-1623334044303-241021148842?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2850&q=80');

COMMIT;


-- -----------------------------------------------------
-- Data for table `req_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `bakingdb`;
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (1, 1, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (2, 1, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (2, 5, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (3, 6, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (4, 2, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (5, 3, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (5, 4, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (6, 2, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (7, 2, 1);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (8, 2, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (8, 6, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (9, 1, 3);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (10, 1, 2);
INSERT INTO `req_product` (`requisition_id`, `product_id`, `units_ordered`) VALUES (10, 2, 1);

COMMIT;

