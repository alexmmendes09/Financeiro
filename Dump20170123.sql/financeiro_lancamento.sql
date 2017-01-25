CREATE DATABASE  IF NOT EXISTS `financeiro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `financeiro`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: financeiro
-- ------------------------------------------------------
-- Server version	5.5.47

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lancamento`
--

DROP TABLE IF EXISTS `lancamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `isPago` tinyint(1) DEFAULT NULL,
  `tipo` varchar(255) NOT NULL,
  `username` varchar(80) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  `pessoa_id` bigint(20) NOT NULL,
  `parcelas` int(11) DEFAULT NULL,
  `tipoPagto` varchar(255) NOT NULL DEFAULT '',
  `session_id` varchar(255) NOT NULL,
  `num_parcelas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_35f2lwpu208qfknqi2g5xflev` (`categoria_id`),
  KEY `FK_ax5wcm3fcn1xss5lkmlbt68pu` (`pessoa_id`),
  CONSTRAINT `FK_35f2lwpu208qfknqi2g5xflev` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_ax5wcm3fcn1xss5lkmlbt68pu` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamento`
--

LOCK TABLES `lancamento` WRITE;
/*!40000 ALTER TABLE `lancamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `lancamento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-23 17:35:37
