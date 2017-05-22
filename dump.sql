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
INSERT INTO `caixas` VALUES (1,1,0),(2,1,1),(3,1,0),(4,1,0),(5,1,0),(6,1,0),(7,1,0);
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
INSERT INTO `estoques` VALUES (1,1,12.00,12.01),(1,2,192.00,960.00),(1,3,195.00,585.00),(1,4,197.00,788.00),(1,5,100.00,300.00),(1,6,85.50,8550.00),(1,7,199.00,597.00),(1,8,199.00,1393.00),(1,9,189.00,378.00);
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
INSERT INTO `itens_vendas` VALUES (150,1,1,1.00,3.00,0),(151,1,1,3.00,9.00,0),(152,1,1,3.00,9.00,0),(152,2,2,1.00,5.26,0),(155,1,2,3.00,9.00,0),(155,3,1,2.00,7.20,0),(157,1,2,3.00,9.00,0),(157,2,3,2.00,10.52,0),(157,3,1,1.00,3.60,0);
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
INSERT INTO `pagamentos_vendas` VALUES (150,10.00,0.00,0.00,0.00,0.00,0.00,7.00),(151,10.00,0.00,0.00,0.00,0.00,0.00,1.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessoes`
--

LOCK TABLES `sessoes` WRITE;
/*!40000 ALTER TABLE `sessoes` DISABLE KEYS */;
INSERT INTO `sessoes` VALUES (231,12,'2017-05-22 17:38:18','2017-05-22 17:38:29'),(232,10,'2017-05-22 17:40:26','2017-05-22 17:40:51'),(233,10,'2017-05-22 17:41:18','2017-05-22 17:41:27'),(234,10,'2017-05-22 17:42:36','2017-05-22 17:42:57'),(235,10,'2017-05-22 17:43:22','2017-05-22 17:43:34'),(236,12,'2017-05-22 17:44:00','2017-05-22 17:45:04'),(237,10,'2017-05-22 17:46:45','2017-05-22 17:46:57'),(238,10,'2017-05-22 17:48:13','2017-05-22 17:48:43'),(239,12,'2017-05-22 17:54:06','2017-05-22 20:13:03'),(240,10,'2017-05-22 18:05:02','2017-05-22 18:05:17'),(241,10,'2017-05-22 18:13:29','2017-05-22 18:13:46'),(242,10,'2017-05-22 18:15:09','2017-05-22 18:19:09'),(243,10,'2017-05-22 20:05:47','2017-05-22 20:08:29'),(244,10,'2017-05-22 20:09:41',NULL),(245,12,'2017-05-22 20:13:09','2017-05-22 20:15:30'),(246,12,'2017-05-22 20:15:37','2017-05-22 20:17:00');
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
INSERT INTO `sessoes_caixas` VALUES (239,0.00,0.00,1),(243,100.00,103.00,2),(244,100.00,NULL,2),(245,100.00,100.00,1),(246,100.00,109.00,1);
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
INSERT INTO `usuarios` VALUES (1,'1','1','1',1,1,0),(3,'nome','login','senha',1,1,1),(4,'4','4','4',1,1,1),(10,'2','2','2',1,1,1),(11,'Alex','alex','12345',1,1,1),(12,'caixa','caixa','caixa',1,2,1),(13,'estoque','estoque','estoque',1,3,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (150,243,'2017-05-22 20:07:06',1),(151,246,'2017-05-22 20:15:48',1),(152,244,'2017-05-22 20:23:16',0),(155,244,'2017-05-22 20:27:34',0),(157,244,'2017-05-22 20:28:56',0);
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

-- Dump completed on 2017-05-22 20:50:00
