CREATE DATABASE  IF NOT EXISTS `db_mercado` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_mercado`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: db_mercado
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `caixas`
--

DROP TABLE IF EXISTS `caixas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caixas` (
  `Numero_Caixa` int(11) NOT NULL,
  `ID_Unidade` int(11) NOT NULL,
  `Aberto` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`Numero_Caixa`,`ID_Unidade`),
  KEY `ID_Unidade` (`ID_Unidade`),
  CONSTRAINT `caixas_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixas`
--

LOCK TABLES `caixas` WRITE;
/*!40000 ALTER TABLE `caixas` DISABLE KEYS */;
INSERT INTO `caixas` VALUES (1,1,0),(2,1,0),(3,1,0),(4,1,0),(5,1,0);
/*!40000 ALTER TABLE `caixas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias_produtos`
--

DROP TABLE IF EXISTS `categorias_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias_produtos` (
  `ID_Categoria` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias_produtos`
--

LOCK TABLES `categorias_produtos` WRITE;
/*!40000 ALTER TABLE `categorias_produtos` DISABLE KEYS */;
INSERT INTO `categorias_produtos` VALUES (1,'Alimentos'),(2,'Bebidas'),(3,'Higienico'),(4,'Limpeza'),(5,'Enlatados');
/*!40000 ALTER TABLE `categorias_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emails_fornecedores`
--

DROP TABLE IF EXISTS `emails_fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emails_fornecedores` (
  `ID_Fornecedor` int(11) NOT NULL,
  `Email` varchar(80) NOT NULL,
  PRIMARY KEY (`ID_Fornecedor`,`Email`),
  CONSTRAINT `emails_fornecedores_ibfk_1` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedores` (`ID_Fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emails_fornecedores`
--

LOCK TABLES `emails_fornecedores` WRITE;
/*!40000 ALTER TABLE `emails_fornecedores` DISABLE KEYS */;
INSERT INTO `emails_fornecedores` VALUES (1,'itaipava.ita@hotmail.com'),(2,'italac.ita@gmai.com'),(3,'atacadao.ataca@gmail.com');
/*!40000 ALTER TABLE `emails_fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos_fornecedores`
--

DROP TABLE IF EXISTS `enderecos_fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enderecos_fornecedores` (
  `ID_Fornecedor` int(11) NOT NULL,
  `Logradouro` varchar(80) DEFAULT NULL,
  `Numero` int(11) NOT NULL,
  `CEP` char(9) NOT NULL,
  `Complemento` varchar(30) DEFAULT NULL,
  `Bairro` varchar(60) DEFAULT NULL,
  `Cidade` varchar(60) DEFAULT NULL,
  `UF` char(2) NOT NULL,
  PRIMARY KEY (`ID_Fornecedor`),
  CONSTRAINT `enderecos_fornecedores_ibfk_1` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedores` (`ID_Fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos_fornecedores`
--

LOCK TABLES `enderecos_fornecedores` WRITE;
/*!40000 ALTER TABLE `enderecos_fornecedores` DISABLE KEYS */;
INSERT INTO `enderecos_fornecedores` VALUES (1,'Rua das Maravilhas',788,'09998-221','','Vila Curuça','Santo André','SP'),(2,'Rua Plutão',2344,'09839-232','Centro','Jardim Botânico','São Paulo','SP'),(3,'Rua Nova York',2003,'90423-890','Centro','Vila Nova York','São Paulo ','SP');
/*!40000 ALTER TABLE `enderecos_fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entradas_produtos`
--

DROP TABLE IF EXISTS `entradas_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entradas_produtos` (
  `ID_Entrada` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Produto` int(11) NOT NULL,
  `ID_Fornecedor` int(11) NOT NULL,
  `Quantidade` decimal(5,2) NOT NULL,
  `Valor_Unitario` decimal(7,2) DEFAULT NULL,
  `Data` datetime NOT NULL,
  `ID_Sessao` int(11) NOT NULL,
  PRIMARY KEY (`ID_Entrada`),
  KEY `ID_Produto` (`ID_Produto`),
  KEY `ID_Sessao` (`ID_Sessao`),
  KEY `ID_Fornecedor` (`ID_Fornecedor`),
  CONSTRAINT `entradas_produtos_ibfk_1` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`),
  CONSTRAINT `entradas_produtos_ibfk_2` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`),
  CONSTRAINT `entradas_produtos_ibfk_3` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedores` (`ID_Fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entradas_produtos`
--

LOCK TABLES `entradas_produtos` WRITE;
/*!40000 ALTER TABLE `entradas_produtos` DISABLE KEYS */;
INSERT INTO `entradas_produtos` VALUES (27,3,3,36.00,1.88,'2017-05-26 13:51:32',253),(28,1,3,36.00,1.88,'2017-05-26 13:51:51',253),(29,2,3,36.00,1.99,'2017-05-26 13:52:06',253),(30,4,3,36.00,1.88,'2017-05-26 13:52:31',253),(31,5,3,20.00,2.49,'2017-05-26 13:53:16',253),(32,6,3,6.00,2.45,'2017-05-26 13:55:34',253),(33,7,3,6.00,2.45,'2017-05-26 13:55:51',253),(34,8,3,6.00,2.75,'2017-05-26 13:56:15',253),(35,9,3,10.00,1.56,'2017-05-26 13:56:55',253),(36,10,3,48.00,0.94,'2017-05-26 13:57:22',253),(37,11,3,48.00,0.94,'2017-05-26 13:57:33',253),(38,12,3,10.00,1.35,'2017-05-26 13:57:49',253),(39,13,3,10.00,1.35,'2017-05-26 14:01:34',253),(40,14,3,10.00,2.00,'2017-05-26 14:01:51',253),(41,35,3,100.00,1.09,'2017-05-26 14:20:50',258),(42,36,3,100.00,3.50,'2017-05-26 14:21:29',258),(43,26,3,100.00,2.69,'2017-05-26 14:21:52',258),(44,13,3,100.00,2.00,'2017-05-26 14:22:48',258),(45,14,3,100.00,3.00,'2017-05-26 14:23:20',258),(46,4,2,100.00,2.00,'2017-05-26 15:04:45',261),(47,30,1,100.00,2.00,'2017-05-26 15:05:59',261);
/*!40000 ALTER TABLE `entradas_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoques`
--

DROP TABLE IF EXISTS `estoques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoques` (
  `ID_Unidade` int(11) NOT NULL,
  `ID_Produto` int(11) NOT NULL,
  `Quantidade` decimal(5,2) DEFAULT NULL,
  `Valor_Total` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID_Unidade`,`ID_Produto`),
  KEY `ID_Produto` (`ID_Produto`),
  CONSTRAINT `estoques_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `estoques_ibfk_2` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoques`
--

LOCK TABLES `estoques` WRITE;
/*!40000 ALTER TABLE `estoques` DISABLE KEYS */;
INSERT INTO `estoques` VALUES (1,1,34.00,63.92),(1,2,32.00,63.68),(1,3,35.00,65.80),(1,4,128.00,252.19),(1,5,20.00,49.80),(1,6,4.00,9.80),(1,7,2.00,4.90),(1,8,6.00,16.50),(1,9,3.00,4.68),(1,10,48.00,45.12),(1,11,45.00,42.30),(1,12,10.00,13.50),(1,13,108.00,210.80),(1,14,109.00,318.00),(1,26,100.00,269.00),(1,30,97.00,194.00),(1,35,94.00,102.46),(1,36,99.00,346.50);
/*!40000 ALTER TABLE `estoques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores` (
  `ID_Fornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nome_Fantasia` varchar(80) NOT NULL,
  `Razao_Social` varchar(120) NOT NULL,
  `CNPJ` char(18) NOT NULL,
  `Ativo` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID_Fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (1,'Cevada','Itaipava','98.822.919/2222-11',1),(2,'Italac','Italac Ltda','45.677.889/8999-99',1),(3,'Atacadão','Atacadão LTDA','54.364.373/4634-63',1);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcoes_usuarios`
--

DROP TABLE IF EXISTS `funcoes_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcoes_usuarios` (
  `ID_Funcao` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcoes_usuarios`
--

LOCK TABLES `funcoes_usuarios` WRITE;
/*!40000 ALTER TABLE `funcoes_usuarios` DISABLE KEYS */;
INSERT INTO `funcoes_usuarios` VALUES (1,'Gerente'),(2,'Operador(a) de Caixa'),(3,'Estoquista'),(4,'Administrador');
/*!40000 ALTER TABLE `funcoes_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_vendas`
--

DROP TABLE IF EXISTS `itens_vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens_vendas` (
  `ID_Venda` int(11) NOT NULL,
  `ID_Produto` int(11) NOT NULL,
  `Numero_Item` int(11) NOT NULL,
  `Quantidade` decimal(5,2) NOT NULL,
  `Subtotal` decimal(10,2) NOT NULL,
  `Cancelado` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID_Venda`,`ID_Produto`,`Numero_Item`),
  KEY `ID_Produto` (`ID_Produto`),
  CONSTRAINT `itens_vendas_ibfk_1` FOREIGN KEY (`ID_Venda`) REFERENCES `vendas` (`ID_Venda`),
  CONSTRAINT `itens_vendas_ibfk_2` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_vendas`
--

LOCK TABLES `itens_vendas` WRITE;
/*!40000 ALTER TABLE `itens_vendas` DISABLE KEYS */;
INSERT INTO `itens_vendas` VALUES (162,1,1,1.00,3.50,0),(162,2,2,1.00,3.75,0),(162,7,4,2.00,8.00,0),(162,9,3,5.00,11.50,0),(163,2,2,1.00,3.75,0),(163,3,1,1.00,3.50,0),(163,6,3,1.00,4.00,0),(163,7,4,1.00,4.00,0),(163,9,5,1.00,2.30,0),(163,11,6,1.00,1.40,0),(163,14,7,1.00,3.40,0),(164,2,3,1.00,3.75,0),(164,4,1,1.00,3.50,0),(164,6,2,1.00,4.00,0),(164,7,4,1.00,4.00,0),(165,35,1,3.00,5.25,0),(166,1,2,1.00,3.50,0),(166,9,1,1.00,2.30,0),(166,36,3,1.00,5.25,0),(167,1,1,1.00,3.50,0);
/*!40000 ALTER TABLE `itens_vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motivos_produtos_retirados`
--

DROP TABLE IF EXISTS `motivos_produtos_retirados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `motivos_produtos_retirados` (
  `ID_Motivo` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(70) NOT NULL,
  PRIMARY KEY (`ID_Motivo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motivos_produtos_retirados`
--

LOCK TABLES `motivos_produtos_retirados` WRITE;
/*!40000 ALTER TABLE `motivos_produtos_retirados` DISABLE KEYS */;
INSERT INTO `motivos_produtos_retirados` VALUES (1,'Mercadoria Vencida'),(2,'Mercadoria Danificada');
/*!40000 ALTER TABLE `motivos_produtos_retirados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentos_vendas`
--

DROP TABLE IF EXISTS `pagamentos_vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamentos_vendas` (
  `ID_Venda` int(11) NOT NULL,
  `Dinheiro` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Debito` decimal(7,2) DEFAULT '0.00',
  `Credito` decimal(7,2) DEFAULT '0.00',
  `Voucher` decimal(7,2) DEFAULT '0.00',
  `Cheque` decimal(7,2) DEFAULT '0.00',
  `Outros` decimal(7,2) DEFAULT '0.00',
  `Troco` decimal(7,2) DEFAULT '0.00',
  PRIMARY KEY (`ID_Venda`),
  CONSTRAINT `pagamentos_vendas_ibfk_1` FOREIGN KEY (`ID_Venda`) REFERENCES `vendas` (`ID_Venda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentos_vendas`
--

LOCK TABLES `pagamentos_vendas` WRITE;
/*!40000 ALTER TABLE `pagamentos_vendas` DISABLE KEYS */;
INSERT INTO `pagamentos_vendas` VALUES (162,26.75,0.00,0.00,0.00,0.00,0.00,0.00),(163,22.35,0.00,0.00,0.00,0.00,0.00,0.00),(164,15.25,0.00,0.00,0.00,0.00,0.00,0.00),(165,5.25,0.00,0.00,0.00,0.00,0.00,0.00),(166,11.05,0.00,0.00,0.00,0.00,0.00,0.00);
/*!40000 ALTER TABLE `pagamentos_vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `precos_atuais`
--

DROP TABLE IF EXISTS `precos_atuais`;
/*!50001 DROP VIEW IF EXISTS `precos_atuais`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `precos_atuais` AS SELECT 
 1 AS `ID_Unidade`,
 1 AS `ID_Produto`,
 1 AS `Valor`,
 1 AS `Data`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `precos_produtos`
--

DROP TABLE IF EXISTS `precos_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precos_produtos` (
  `ID_Unidade` int(11) NOT NULL,
  `ID_Produto` int(11) NOT NULL,
  `Valor` decimal(7,2) NOT NULL,
  `Data` datetime NOT NULL,
  PRIMARY KEY (`ID_Unidade`,`ID_Produto`,`Data`),
  KEY `ID_Produto` (`ID_Produto`),
  CONSTRAINT `precos_produtos_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `precos_produtos_ibfk_2` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precos_produtos`
--

LOCK TABLES `precos_produtos` WRITE;
/*!40000 ALTER TABLE `precos_produtos` DISABLE KEYS */;
INSERT INTO `precos_produtos` VALUES (1,1,3.50,'2017-05-26 12:41:16'),(1,2,3.75,'2017-05-26 12:41:33'),(1,3,3.50,'2017-05-26 12:41:49'),(1,4,3.50,'2017-05-26 12:42:26'),(1,5,3.75,'2017-05-26 12:42:39'),(1,6,4.00,'2017-05-26 12:42:50'),(1,7,4.00,'2017-05-26 12:43:00'),(1,8,4.00,'2017-05-26 12:43:10'),(1,9,2.30,'2017-05-26 12:43:22'),(1,10,1.40,'2017-05-26 12:43:39'),(1,11,1.40,'2017-05-26 12:43:47'),(1,12,2.25,'2017-05-26 12:43:59'),(1,13,2.25,'2017-05-26 12:44:09'),(1,14,3.40,'2017-05-26 12:44:26'),(1,15,3.40,'2017-05-26 12:44:36'),(1,16,3.00,'2017-05-26 12:44:50'),(1,17,3.00,'2017-05-26 12:45:00'),(1,20,5.75,'2017-05-26 12:45:16'),(1,21,11.00,'2017-05-26 12:45:26'),(1,22,3.50,'2017-05-26 12:45:35'),(1,23,3.50,'2017-05-26 12:45:46'),(1,24,3.25,'2017-05-26 12:45:56'),(1,25,6.75,'2017-05-26 12:46:14'),(1,26,4.25,'2017-05-26 12:46:31'),(1,27,4.00,'2017-05-26 13:22:03'),(1,28,3.25,'2017-05-26 13:22:34'),(1,29,2.30,'2017-05-26 13:22:51'),(1,30,5.00,'2017-05-26 13:23:07'),(1,30,4.99,'2017-05-26 13:25:43'),(1,31,1.75,'2017-05-26 13:23:16'),(1,32,6.00,'2017-05-26 13:23:25'),(1,32,5.99,'2017-05-26 13:25:31'),(1,33,1.50,'2017-05-26 13:23:35'),(1,34,3.25,'2017-05-26 13:23:46'),(1,35,1.75,'2017-05-26 13:23:56'),(1,36,5.25,'2017-05-26 13:24:14'),(1,37,7.00,'2017-05-26 13:24:24'),(1,37,6.99,'2017-05-26 13:24:49'),(1,38,6.99,'2017-05-26 13:24:37'),(1,39,5.99,'2017-05-26 13:25:22');
/*!40000 ALTER TABLE `precos_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `ID_Produto` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(150) NOT NULL,
  `Descricao_Reduzida` varchar(50) DEFAULT NULL,
  `Venda_Fracionada` tinyint(1) DEFAULT '0',
  `ID_Categoria` int(11) NOT NULL,
  `Imagem` blob,
  `Codigo_De_Barras` varchar(13) DEFAULT NULL,
  `Estocavel` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID_Produto`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categorias_produtos` (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'Leite Piracanjuba','Leite P',0,2,NULL,'7898215151890',1),(2,'Leite Italac','Leite Ita',0,2,NULL,'7898080640017',1),(3,'Leite Piracanjuba','Leite Pira',0,2,NULL,'7896283800221',1),(4,'Leite Tirol','Leite Ti',0,2,NULL,'7896256600223',1),(5,'Biscoito Vitarella Cream Cracker Tradicional','Biscoito Cream Cracker',0,1,NULL,'7896213000448',1),(6,'Biscoito Vitarella Maizena chocolate','Maizena Chocolate',0,1,NULL,'7896213002138',1),(7,'Biscoito Vitarella Maizena Tradicional','Vitarella Maizana',0,1,NULL,'7896213000691',1),(8,'Biscoito Mabel chocolate Rosquinha','Rosquinha Chocolate',0,1,NULL,'7896071023139',1),(9,'Bolacha Recheada Passa Tempo','Passa tempo',0,1,NULL,'7891000241356',1),(10,'Bolacha Recheada Bauduco Recheado Bob Esponja Chocolate','Bob Esponja Chocolate',0,1,NULL,'7891962026138',1),(11,'Bolacha Recheada Bauduco Recheado Bob Esponja Morango',' Bob Esponja Morango',0,1,NULL,'7891962008325',1),(12,'Tortinhas Adria chocolate branco','chocolate branco',0,1,NULL,'7896085055836',1),(13,'Mousse Adria Chocolate com Morango','Mousse Adria Moran/Choco',0,1,NULL,'7896085072314',1),(14,'Sardinha Gomes da Costa com óleo','Gomes da Costa óleo',0,1,NULL,'7891167021013',1),(15,'Sardinha Gomes da Costa Com Molho','Gomes da Costa com Molho',0,1,NULL,'7891167021020',1),(16,'Sardinha Pescador com molho','Pescador com molho',0,1,NULL,'7896114900045',1),(17,'Sardinha Pescador com óleo','Pescador com óleo',0,1,NULL,'7896114900014',1),(18,'Atum Gomes da Costa Natural','Gomes da Costa Atum',0,1,NULL,'7891167011731',1),(19,'Atum Coqueiro Ralado Natural','Atum Natural Coqueiro',0,1,NULL,'7894321822020',1),(20,'Feijoada Borbom Pequena','Borbom Pequena',0,1,NULL,'7896031224088',1),(21,'Feijoada Borbom Grande','Borbom Grande',0,1,NULL,'7896031224583',1),(22,'Açucar União','União 1 KG',0,1,NULL,'7891910020340',1),(23,'Açucar Da Barra 1KG','Da Barra 1KG',0,1,NULL,'7896032510104',1),(24,'Açucar Caravelas 1KG','Caravelas 1KG',0,1,NULL,'7896894900013',1),(25,'Feijão Pantera 1KG',' Pantera 1KG',0,1,NULL,'7896070800014',1),(26,'Óleo Liza ','Liza',0,1,NULL,'1789603609024',1),(27,'Óleo Soya','Soya',0,1,NULL,'7891107101820',1),(28,'Amaciante Ypê 500ML','Ypê 500ML',0,3,NULL,'7896098900406',1),(29,'Macarrão Parafuso Dona Benta',' Parafuso Dona Benta',0,1,NULL,'7896005286593',1),(30,'Farofa Yoki 1/2 quilo','Yoki 1/2 quilo',0,1,NULL,'7891095300488',1),(31,'Molho de Tomate Quero ','Molho Quero',0,1,NULL,'7896102509410',1),(32,'Farinha Mandioca 1KG','Mandioca 1KG',0,1,NULL,'7898140070077',1),(33,'Vinhagre Palhinha ','Palhinha',0,1,NULL,'7896076220052',1),(34,'Vinagre Galo Maçã',' Galo Maçã',0,1,NULL,'7896057800198',1),(35,'Molho de Tomate Fugini ','Tomate Fugini',0,1,NULL,'7897517206086',1),(36,'Massa de bolo Dona Benta Chocolate','Dona Benta Chocolate',0,1,NULL,'7896005217023',1),(37,'Amaciante 2 Litros Ypê Intenso','2 Litros Ypê Intenso',0,1,NULL,'7896098903032',1),(38,'Amaciante 2 Litros Ypê Amor','2 Litros Ypê Amor',0,1,NULL,'7896098902394',1),(39,'Amanciante BobSoft 2 Litros Toque de Carinho','BobSoft 2 Litros Toque de Carinho',0,1,NULL,'7896075990727',1);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos_retirados`
--

DROP TABLE IF EXISTS `produtos_retirados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos_retirados` (
  `ID_Retirada` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Produto` int(11) NOT NULL,
  `Quantidade` decimal(5,2) DEFAULT NULL,
  `Data` datetime NOT NULL,
  `ID_Sessao` int(11) NOT NULL,
  `ID_Motivo` int(11) NOT NULL,
  `Observacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Retirada`),
  KEY `ID_Produto` (`ID_Produto`),
  KEY `ID_Sessao` (`ID_Sessao`),
  KEY `ID_Motivo` (`ID_Motivo`),
  CONSTRAINT `produtos_retirados_ibfk_1` FOREIGN KEY (`ID_Produto`) REFERENCES `produtos` (`ID_Produto`),
  CONSTRAINT `produtos_retirados_ibfk_2` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`),
  CONSTRAINT `produtos_retirados_ibfk_3` FOREIGN KEY (`ID_Motivo`) REFERENCES `motivos_produtos_retirados` (`ID_Motivo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos_retirados`
--

LOCK TABLES `produtos_retirados` WRITE;
/*!40000 ALTER TABLE `produtos_retirados` DISABLE KEYS */;
INSERT INTO `produtos_retirados` VALUES (7,11,2.00,'2017-05-26 14:18:08',258,1,''),(8,4,2.00,'2017-05-26 14:18:21',258,1,''),(9,2,1.00,'2017-05-26 14:18:49',258,2,''),(10,13,2.00,'2017-05-26 14:18:58',258,2,''),(11,35,3.00,'2017-05-26 14:23:39',258,2,''),(12,4,5.00,'2017-05-26 15:04:54',261,1,''),(13,30,3.00,'2017-05-26 15:06:14',261,1,'');
/*!40000 ALTER TABLE `produtos_retirados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessoes`
--

DROP TABLE IF EXISTS `sessoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessoes` (
  `ID_Sessao` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Usuario` int(11) NOT NULL,
  `Inicio_Sessao` datetime NOT NULL,
  `Fim_Sessao` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_Sessao`),
  KEY `ID_Usuario` (`ID_Usuario`),
  CONSTRAINT `sessoes_ibfk_1` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuarios` (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessoes`
--

LOCK TABLES `sessoes` WRITE;
/*!40000 ALTER TABLE `sessoes` DISABLE KEYS */;
INSERT INTO `sessoes` VALUES (252,1,'2017-05-26 11:35:07','2017-05-26 11:38:02'),(253,1,'2017-05-26 11:40:19','2017-05-26 14:03:56'),(254,1,'2017-05-26 14:04:04','2017-05-26 14:07:20'),(255,1,'2017-05-26 14:07:52','2017-05-26 14:07:58'),(256,1,'2017-05-26 14:08:30','2017-05-26 14:09:20'),(257,1,'2017-05-26 14:12:41','2017-05-26 14:14:53'),(258,1,'2017-05-26 14:15:04','2017-05-26 14:23:51'),(259,1,'2017-05-26 14:25:02','2017-05-26 14:26:59'),(260,1,'2017-05-26 14:55:46','2017-05-26 14:57:21'),(261,1,'2017-05-26 15:04:07','2017-05-26 15:09:24');
/*!40000 ALTER TABLE `sessoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessoes_caixas`
--

DROP TABLE IF EXISTS `sessoes_caixas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessoes_caixas` (
  `ID_Sessao` int(11) NOT NULL,
  `Valor_Inicial_Caixa` decimal(6,2) NOT NULL,
  `Valor_Fechamento` decimal(10,2) DEFAULT NULL,
  `numero_caixa` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Sessao`),
  KEY `numero_caixa` (`numero_caixa`),
  CONSTRAINT `sessoes_caixas_ibfk_1` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`),
  CONSTRAINT `sessoes_caixas_ibfk_2` FOREIGN KEY (`numero_caixa`) REFERENCES `caixas` (`Numero_Caixa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessoes_caixas`
--

LOCK TABLES `sessoes_caixas` WRITE;
/*!40000 ALTER TABLE `sessoes_caixas` DISABLE KEYS */;
INSERT INTO `sessoes_caixas` VALUES (253,100.00,100.00,1),(254,100.00,100.00,2),(256,50.00,50.00,1),(257,100.00,100.00,1),(258,100.00,164.35,1),(259,45.00,61.30,3),(260,100.00,100.00,1);
/*!40000 ALTER TABLE `sessoes_caixas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefones_fornecedores`
--

DROP TABLE IF EXISTS `telefones_fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefones_fornecedores` (
  `ID_Fornecedor` int(11) NOT NULL,
  `Telefone` char(14) NOT NULL,
  PRIMARY KEY (`ID_Fornecedor`,`Telefone`),
  CONSTRAINT `telefones_fornecedores_ibfk_1` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedores` (`ID_Fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones_fornecedores`
--

LOCK TABLES `telefones_fornecedores` WRITE;
/*!40000 ALTER TABLE `telefones_fornecedores` DISABLE KEYS */;
INSERT INTO `telefones_fornecedores` VALUES (1,'(11) 3456-6899'),(2,'(11) 4567-8908'),(3,'(11) 2232-1421');
/*!40000 ALTER TABLE `telefones_fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidades`
--

DROP TABLE IF EXISTS `unidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidades` (
  `ID_Unidade` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Unidade`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades`
--

LOCK TABLES `unidades` WRITE;
/*!40000 ALTER TABLE `unidades` DISABLE KEYS */;
INSERT INTO `unidades` VALUES (1,'Unidade São Caetano do Sul');
/*!40000 ALTER TABLE `unidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(80) NOT NULL,
  `Login` varchar(10) NOT NULL,
  `Senha` varchar(10) NOT NULL,
  `ID_Unidade` int(11) DEFAULT NULL,
  `ID_Funcao` int(11) NOT NULL,
  `Ativo` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID_Usuario`),
  KEY `ID_Unidade` (`ID_Unidade`),
  KEY `ID_Funcao` (`ID_Funcao`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`ID_Funcao`) REFERENCES `funcoes_usuarios` (`ID_Funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Alex','alex','1',1,1,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `ID_Venda` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Sessao` int(11) NOT NULL,
  `Data` datetime NOT NULL,
  `Finalizada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Venda`),
  KEY `ID_Sessao` (`ID_Sessao`),
  CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (162,258,'2017-05-26 14:15:29',1),(163,258,'2017-05-26 14:19:29',1),(164,258,'2017-05-26 14:20:00',1),(165,259,'2017-05-26 14:25:17',1),(166,259,'2017-05-26 14:26:27',1),(167,260,'2017-05-26 14:56:23',0);
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `precos_atuais`
--

/*!50001 DROP VIEW IF EXISTS `precos_atuais`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `precos_atuais` AS select `precos_produtos`.`ID_Unidade` AS `ID_Unidade`,`precos_produtos`.`ID_Produto` AS `ID_Produto`,`precos_produtos`.`Valor` AS `Valor`,`precos_produtos`.`Data` AS `Data` from `precos_produtos` where ((to_days(curdate()) - to_days(`precos_produtos`.`Data`)) = (select min((to_days(curdate()) - to_days(`precos_produtos`.`Data`))))) group by (`precos_produtos`.`ID_Unidade` and `precos_produtos`.`ID_Produto`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-29 17:39:32
