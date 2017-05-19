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
INSERT INTO `caixas` VALUES (1,1,1),(2,1,1),(3,1,1),(4,1,1),(5,1,1),(6,1,0),(7,1,0);
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
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias_produtos`
--

LOCK TABLES `categorias_produtos` WRITE;
/*!40000 ALTER TABLE `categorias_produtos` DISABLE KEYS */;
INSERT INTO `categorias_produtos` VALUES (1,'Alimentos'),(2,'Bebidas'),(3,'Limpeza'),(4,'Higiene Pessoal'),(5,'Perfumaria');
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
INSERT INTO `emails_fornecedores` VALUES (4,'fornecedor1@rmail.com');
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
INSERT INTO `enderecos_fornecedores` VALUES (2,'asdasd',123123,'22222-222','asd','asd','asd','RJ'),(4,'1',1,'11111-111','1','1','1','SP');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entradas_produtos`
--

LOCK TABLES `entradas_produtos` WRITE;
/*!40000 ALTER TABLE `entradas_produtos` DISABLE KEYS */;
INSERT INTO `entradas_produtos` VALUES (16,6,4,100.00,100.00,'2017-05-10 15:40:58',105),(17,2,4,200.00,5.00,'2017-05-17 13:50:35',141),(18,3,4,200.00,3.00,'2017-05-17 13:50:53',141),(19,4,4,200.00,4.00,'2017-05-17 13:51:10',141),(20,5,4,100.00,3.00,'2017-05-17 14:02:45',143),(21,7,4,200.00,3.00,'2017-05-17 14:03:13',143),(22,8,4,200.00,7.00,'2017-05-17 14:03:28',143),(23,9,4,200.00,2.00,'2017-05-17 14:04:01',143),(24,5,2,10.00,3.00,'2017-05-17 15:07:54',150),(25,1,4,10.00,2.00,'2017-02-10 00:00:00',105),(26,1,4,10.00,2.00,'2016-02-10 00:00:00',105);
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
INSERT INTO `estoques` VALUES (1,1,27.00,27.01),(1,2,192.00,960.00),(1,3,195.00,585.00),(1,4,197.00,788.00),(1,5,100.00,300.00),(1,6,85.50,8550.00),(1,7,199.00,597.00),(1,8,199.00,1393.00),(1,9,189.00,378.00);
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
  `Nome_Fantasia` varchar(20) NOT NULL,
  `Razao_Social` varchar(70) NOT NULL,
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
INSERT INTO `fornecedores` VALUES (2,'Fornecedor','Fornecedor Ltda.','22.222.222/2222-22',1),(4,'1','1','11.111.111/1111-11',1);
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
INSERT INTO `funcoes_usuarios` VALUES (1,'Gerente'),(2,'Operador(a) de caixa'),(3,'Estoquista'),(4,'Administrador');
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
INSERT INTO `itens_vendas` VALUES (94,1,1,11.00,33.00,0),(95,1,1,10.00,30.00,0),(96,1,1,1.00,3.00,0),(97,1,1,13.00,39.00,0),(98,6,1,1.50,8.99,0),(99,1,1,1.00,3.00,0),(99,5,2,1.00,3.75,0),(99,6,3,1.00,5.99,0),(100,1,1,1.00,3.00,0),(100,5,2,1.00,3.75,0),(100,6,3,1.00,5.99,0),(101,1,1,1.00,3.00,0),(101,6,2,1.00,5.99,0),(102,1,1,2.00,6.00,0),(103,1,1,2.00,6.00,0),(104,1,1,2.00,6.00,0),(105,1,1,1.00,3.00,0),(106,1,1,2.00,6.00,0),(106,5,2,1.00,3.75,0),(107,1,1,1.00,3.00,0),(109,1,1,2.00,6.00,0),(109,5,2,2.00,7.50,0),(109,6,3,1.00,5.99,0),(110,1,1,3.00,9.00,0),(110,5,2,2.00,7.50,0),(110,6,3,1.00,5.99,0),(111,1,1,1.00,3.00,0),(112,1,1,2.00,6.00,0),(112,5,2,2.00,7.50,0),(112,6,3,1.00,5.99,0),(113,1,1,1.00,3.00,0),(114,1,1,11.00,33.00,0),(115,1,1,1.00,3.00,0),(116,1,1,1.00,3.00,0),(117,1,1,1.00,3.00,0),(118,1,1,1.00,3.00,0),(119,1,1,1.00,3.00,0),(123,1,1,1.00,3.00,0),(123,5,2,1.00,3.75,0),(123,6,3,1.00,5.99,0),(124,1,1,1.00,3.00,0),(124,2,2,1.00,5.26,0),(124,3,3,1.00,3.60,0),(124,4,4,1.00,2.00,0),(124,5,5,1.00,3.75,0),(124,6,6,1.00,5.99,0),(125,1,1,1.00,3.00,0),(125,2,2,1.00,5.26,0),(125,3,3,1.00,3.60,0),(125,4,4,1.00,2.00,0),(125,6,5,1.00,5.99,0),(126,1,1,2.00,6.00,0),(126,2,2,1.00,5.26,0),(126,3,3,1.00,3.60,0),(126,4,4,1.00,2.00,0),(126,5,5,2.00,7.50,0),(126,6,6,1.00,5.99,0),(126,7,7,1.00,2.00,0),(126,8,8,1.00,6.00,0),(126,9,9,1.00,1.50,0),(127,1,1,1.00,3.00,0),(128,2,1,1.00,5.26,0),(129,1,2,1.00,3.00,0),(129,2,1,1.00,5.26,0),(130,1,1,1.00,3.00,0);
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
INSERT INTO `motivos_produtos_retirados` VALUES (1,'Mercadoria vencida'),(2,'Mercadoria Danificada');
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
INSERT INTO `pagamentos_vendas` VALUES (94,100.00,0.00,0.00,0.00,0.00,0.00,67.00),(95,113.00,0.00,0.00,0.00,0.00,0.00,83.00),(97,50.00,0.00,0.00,0.00,0.00,0.00,11.00),(98,10.00,0.00,0.00,0.00,0.00,0.00,1.02),(99,12.74,0.00,0.00,0.00,0.00,0.00,0.00),(100,12.74,0.00,0.00,0.00,0.00,0.00,0.00),(101,8.99,0.00,0.00,0.00,0.00,0.00,0.00),(102,10.00,0.00,0.00,0.00,0.00,0.00,4.00),(103,102.00,0.00,0.00,0.00,0.00,0.00,96.00),(104,6.00,0.00,0.00,0.00,0.00,0.00,0.00),(105,3.00,0.00,0.00,0.00,0.00,0.00,0.00),(111,0.00,3.00,0.00,0.00,0.00,0.00,0.00),(112,0.00,0.00,19.49,0.00,0.00,0.00,0.00),(113,0.00,3.00,0.00,0.00,0.00,0.00,0.00),(114,0.00,0.00,33.00,0.00,0.00,0.00,0.00),(115,0.00,0.00,0.00,0.00,3.00,0.00,0.00),(116,3.00,0.00,0.00,0.00,0.00,0.00,0.00),(117,3.00,0.00,0.00,0.00,0.00,0.00,0.00),(118,3.00,0.00,0.00,0.00,0.00,0.00,0.00),(119,3.00,0.00,0.00,0.00,0.00,0.00,0.00),(123,12.74,0.00,0.00,0.00,0.00,0.00,0.00),(124,23.60,0.00,0.00,0.00,0.00,0.00,0.00),(125,19.85,0.00,0.00,0.00,0.00,0.00,0.00),(126,39.85,0.00,0.00,0.00,0.00,0.00,0.00),(127,0.00,3.00,0.00,0.00,0.00,0.00,0.00),(128,5.26,0.00,0.00,0.00,0.00,0.00,0.00),(129,8.26,0.00,0.00,0.00,0.00,0.00,0.00),(130,3.00,0.00,0.00,0.00,0.00,0.00,0.00);
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
INSERT INTO `precos_produtos` VALUES (1,1,1.95,'2017-04-06 18:17:34'),(1,1,2.10,'2017-04-06 19:05:58'),(1,1,2.75,'2017-04-06 19:06:20'),(1,1,2.90,'2017-04-06 19:34:52'),(1,1,2.95,'2017-04-06 19:35:28'),(1,1,3.01,'2017-04-06 20:23:43'),(1,1,2.50,'2017-04-20 11:58:54'),(1,1,3.00,'2017-04-20 12:30:22'),(1,2,5.15,'2017-04-06 18:39:21'),(1,2,5.26,'2017-04-06 20:23:58'),(1,3,3.60,'2017-04-25 12:16:16'),(1,4,2.00,'2017-05-09 11:23:49'),(1,5,3.75,'2017-05-09 12:22:25'),(1,6,5.99,'2017-05-10 15:39:08'),(1,7,2.00,'2017-05-17 14:02:00'),(1,8,6.00,'2017-05-17 14:02:07'),(1,9,1.50,'2017-05-17 14:02:15');
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
  `Descricao` varchar(60) NOT NULL,
  `Descricao_Reduzida` varchar(50) DEFAULT NULL,
  `Venda_Fracionada` tinyint(1) DEFAULT '0',
  `ID_Categoria` int(11) NOT NULL,
  `Imagem` blob,
  `Codigo_De_Barras` varchar(13) DEFAULT NULL,
  `Estocavel` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID_Produto`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categorias_produtos` (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'Macarrão instantâneo Nissin Miojo','Mac. Inst. Nissin',0,1,NULL,'1739563847362',1),(2,'Detergente Ypê Maçã','Det. Ypê Maça',0,3,NULL,'1739563847351',1),(3,'Macarrão','Mac',0,1,NULL,'1231234212',1),(4,'Detergente','Det',0,3,NULL,'1111111111111',1),(5,'Leite','Leite',0,2,NULL,'1223456789',1),(6,'Tomate','Tomate',1,1,NULL,'6666666666',1),(7,'Sabonete','Sab.',0,4,NULL,'1231312312',1),(8,'Toalha','Toa.',0,4,NULL,'1312312321',1),(9,'Queijo Muzzarela','Que. Muzz.',0,1,NULL,'1233333333',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos_retirados`
--

LOCK TABLES `produtos_retirados` WRITE;
/*!40000 ALTER TABLE `produtos_retirados` DISABLE KEYS */;
INSERT INTO `produtos_retirados` VALUES (1,6,5.00,'2017-05-17 14:16:03',144,1,''),(2,2,3.00,'2017-05-17 14:16:13',144,2,''),(3,9,10.00,'2017-05-17 14:16:29',144,1,''),(4,3,2.00,'2017-05-17 14:18:12',98,1,''),(5,5,8.00,'2017-05-17 14:18:23',98,2,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessoes`
--

LOCK TABLES `sessoes` WRITE;
/*!40000 ALTER TABLE `sessoes` DISABLE KEYS */;
INSERT INTO `sessoes` VALUES (96,1,'2017-05-10 13:38:26','2017-05-10 14:49:20'),(97,10,'2017-05-10 14:01:38','2017-05-10 14:49:58'),(98,11,'2017-05-10 14:38:12','2017-05-17 14:18:28'),(99,3,'2017-05-10 14:38:25',NULL),(100,1,'2017-05-10 14:49:39','2017-05-10 14:49:47'),(101,1,'2017-05-10 14:52:48','2017-05-10 14:54:53'),(102,10,'2017-05-10 14:53:12',NULL),(103,1,'2017-05-10 14:58:29','2017-05-10 15:20:31'),(105,1,'2017-05-10 15:18:22','2017-05-16 14:00:01'),(106,1,'2017-05-16 14:02:48','2017-05-16 14:05:40'),(107,1,'2017-05-16 14:06:31','2017-05-16 14:30:15'),(108,1,'2017-05-16 14:51:15','2017-05-16 14:52:52'),(109,1,'2017-05-16 15:10:25','2017-05-16 21:34:21'),(110,1,'2017-05-16 21:43:27','2017-05-16 21:59:49'),(111,4,'2017-05-16 21:47:59','2017-05-16 21:48:50'),(112,1,'2017-05-16 22:00:01','2017-05-17 10:37:49'),(113,12,'2017-05-17 10:37:58','2017-05-17 10:38:07'),(114,13,'2017-05-17 10:38:14','2017-05-17 10:43:15'),(115,1,'2017-05-17 11:36:14','2017-05-17 11:36:24'),(116,1,'2017-05-17 11:38:39','2017-05-17 11:38:46'),(117,1,'2017-05-17 11:40:22','2017-05-17 11:40:28'),(118,1,'2017-05-17 11:44:18','2017-05-17 11:44:24'),(119,1,'2017-05-17 11:48:52','2017-05-17 11:51:58'),(120,1,'2017-05-17 11:52:09','2017-05-17 11:52:30'),(121,1,'2017-05-17 11:52:55','2017-05-17 11:53:39'),(122,1,'2017-05-17 11:55:51','2017-05-17 11:55:59'),(123,13,'2017-05-17 11:56:08','2017-05-17 11:56:29'),(124,12,'2017-05-17 11:56:42','2017-05-17 11:57:05'),(125,1,'2017-05-17 12:13:20','2017-05-17 12:16:11'),(126,1,'2017-05-17 12:18:33','2017-05-17 12:22:57'),(127,1,'2017-05-17 12:23:42','2017-05-17 12:24:03'),(128,1,'2017-05-17 12:24:19','2017-05-17 12:24:46'),(129,1,'2017-05-17 12:26:10','2017-05-17 12:27:47'),(130,12,'2017-05-17 12:27:55','2017-05-17 12:29:46'),(131,1,'2017-05-17 12:32:11','2017-05-17 12:44:13'),(132,1,'2017-05-17 12:44:21','2017-05-17 12:49:29'),(133,1,'2017-05-17 12:51:47','2017-05-17 12:52:18'),(134,1,'2017-05-17 12:55:00','2017-05-17 12:59:52'),(135,1,'2017-05-17 13:14:38','2017-05-17 13:15:19'),(136,1,'2017-05-17 13:17:53','2017-05-17 13:18:44'),(137,1,'2017-05-17 13:19:19','2017-05-17 13:19:57'),(138,1,'2017-05-17 13:24:38','2017-05-17 13:37:11'),(139,1,'2017-05-17 13:46:36','2017-05-17 13:47:24'),(140,1,'2017-05-17 13:49:10','2017-05-17 13:49:48'),(141,1,'2017-05-17 13:49:55','2017-05-17 13:52:36'),(142,1,'2017-05-17 13:55:00','2017-05-17 13:56:05'),(143,1,'2017-05-17 13:59:54','2017-05-17 14:05:05'),(144,1,'2017-05-17 14:15:07','2017-05-17 14:16:33'),(145,1,'2017-05-17 14:30:27','2017-05-17 14:31:02'),(146,1,'2017-05-17 14:34:27','2017-05-17 14:34:45'),(147,1,'2017-05-17 14:36:27','2017-05-17 14:37:28'),(148,1,'2017-05-17 14:57:26','2017-05-17 14:57:37'),(149,1,'2017-05-17 14:57:51','2017-05-17 14:58:26'),(150,11,'2017-05-17 15:05:28','2017-05-17 15:09:10'),(151,1,'2017-05-18 10:54:37','2017-05-18 10:55:04'),(152,1,'2017-05-18 11:24:29','2017-05-18 11:27:33'),(153,1,'2017-05-18 11:27:53','2017-05-18 11:28:22'),(154,1,'2017-05-18 11:28:56','2017-05-18 11:52:11'),(155,1,'2017-05-18 11:52:21','2017-05-18 11:52:50'),(156,1,'2017-05-18 11:53:36','2017-05-18 11:55:36'),(157,1,'2017-05-18 12:07:29','2017-05-18 12:07:42'),(158,1,'2017-05-18 12:15:57','2017-05-18 12:18:11'),(159,12,'2017-05-18 12:20:58','2017-05-18 12:21:19'),(160,13,'2017-05-18 12:21:49','2017-05-18 12:21:58'),(161,1,'2017-05-18 12:22:04','2017-05-18 12:22:26'),(162,1,'2017-05-18 12:22:33','2017-05-18 12:29:28'),(163,12,'2017-05-18 12:29:56',NULL),(164,1,'2017-05-18 13:00:51','2017-05-18 13:09:57'),(165,1,'2017-05-18 13:10:54','2017-05-18 13:11:10'),(166,1,'2017-05-18 13:19:26','2017-05-18 13:20:38');
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
INSERT INTO `sessoes_caixas` VALUES (102,40.00,NULL,2),(103,50.00,NULL,1),(105,100.00,NULL,3),(106,100.00,112.74,4),(107,100.00,108.99,4),(109,100.00,NULL,4),(110,100.00,106.00,5),(111,100.00,106.00,5),(120,100.00,100.00,5),(129,100.00,103.00,5),(130,100.00,100.00,5),(131,111.00,111.00,5),(132,100.00,122.49,5),(133,11.00,14.00,5),(134,0.00,36.00,5),(135,10.00,13.00,5),(136,10.00,13.00,5),(137,1.00,4.00,5),(138,0.00,3.00,5),(139,100.00,112.74,5),(141,10.00,33.60,5),(142,10.00,29.85,5),(143,100.00,139.85,5),(163,100.00,NULL,5),(164,45.00,58.52,6),(166,1.00,4.00,6);
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
INSERT INTO `telefones_fornecedores` VALUES (2,'(22) 2222-2222'),(4,'(11) 4755-7885');
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
  `Descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Unidade`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades`
--

LOCK TABLES `unidades` WRITE;
/*!40000 ALTER TABLE `unidades` DISABLE KEYS */;
INSERT INTO `unidades` VALUES (1,'Unidade São Paulo');
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
INSERT INTO `usuarios` VALUES (1,'1','1','1',1,1,1),(3,'nome','login','senha',1,1,1),(4,'4','4','4',1,1,1),(10,'2','2','2',1,1,1),(11,'Alex','alex','12345',1,1,1),(12,'caixa','caixa','caixa',1,2,1),(13,'estoque','estoque','estoque',1,3,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (94,103,'2017-05-10 15:01:39',1),(95,103,'2017-05-10 15:05:24',1),(96,103,'2017-05-10 15:07:32',0),(97,105,'2017-05-10 15:31:29',1),(98,105,'2017-05-10 15:41:17',1),(99,105,'2017-05-16 13:59:31',1),(100,106,'2017-05-16 14:05:11',1),(101,107,'2017-05-16 14:29:23',1),(102,109,'2017-05-16 21:32:35',1),(103,111,'2017-05-16 21:48:18',1),(104,110,'2017-05-16 21:59:08',1),(105,129,'2017-05-17 12:26:30',1),(106,130,'2017-05-17 12:28:45',0),(107,131,'2017-05-17 12:32:23',0),(109,131,'2017-05-17 12:39:28',0),(110,132,'2017-05-17 12:44:35',0),(111,132,'2017-05-17 12:48:22',1),(112,132,'2017-05-17 12:48:45',1),(113,133,'2017-05-17 12:51:58',1),(114,134,'2017-05-17 12:55:18',1),(115,134,'2017-05-17 12:59:09',1),(116,135,'2017-05-17 13:14:59',1),(117,136,'2017-05-17 13:18:12',1),(118,137,'2017-05-17 13:19:29',1),(119,138,'2017-05-17 13:24:50',1),(120,138,'2017-05-17 13:28:04',0),(121,138,'2017-05-17 13:34:26',0),(122,138,'2017-05-17 13:35:54',0),(123,139,'2017-05-17 13:46:47',1),(124,141,'2017-05-17 13:51:44',1),(125,142,'2017-05-17 13:55:12',1),(126,143,'2017-05-17 14:04:18',1),(127,163,'2017-05-18 12:30:31',1),(128,164,'2017-05-18 13:01:05',1),(129,164,'2017-05-18 13:02:45',1),(130,166,'2017-05-18 13:19:38',1);
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `precos_atuais`
--

/*!50001 DROP VIEW IF EXISTS `precos_atuais`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
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

-- Dump completed on 2017-05-19 11:33:30
