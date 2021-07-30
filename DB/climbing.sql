-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema climbingdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `climbingdb` ;

-- -----------------------------------------------------
-- Schema climbingdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `climbingdb` DEFAULT CHARACTER SET utf8 ;
USE `climbingdb` ;

-- -----------------------------------------------------
-- Table `climb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `climb` ;

CREATE TABLE IF NOT EXISTS `climb` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS climber@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'climber'@'localhost' IDENTIFIED BY 'climber';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'climber'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `climb`
-- -----------------------------------------------------
START TRANSACTION;
USE `climbingdb`;
INSERT INTO `climb` (`id`, `name`) VALUES (1, 'New Era');

COMMIT;

