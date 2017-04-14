CREATE DATABASE  IF NOT EXISTS `prizy`; 
USE `prizy`;


DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `price`;
DROP TABLE IF EXISTS `product_price`;
DROP TABLE IF EXISTS `store_product`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `store`;
--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) DEFAULT NULL,
  `bar_code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) DEFAULT NULL,
  `address` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `line3` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `price`
--

CREATE TABLE `price` (
  `product_id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  KEY `FKk4mbgqf5yru5ib5b6w5l6ukkj` (`product_id`),
  CONSTRAINT `FKk4mbgqf5yru5ib5b6w5l6ukkj` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `product_price`
--

CREATE TABLE `product_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `product_uuid` binary(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Table structure for table `store_product`
--

CREATE TABLE `store_product` (
  `productuuid` binary(255) NOT NULL,
  `storeuuid` binary(255) NOT NULL,
  PRIMARY KEY (`productuuid`,`storeuuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

