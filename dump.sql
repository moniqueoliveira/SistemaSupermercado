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
  PRIMARY KEY (`Numero_Caixa`,`ID_Unidade`),
  KEY `ID_Unidade` (`ID_Unidade`),
  CONSTRAINT `caixas_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `entradas_produtos`
--

DROP TABLE IF EXISTS `entradas_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entradas_produtos` (
  `ID_Entrada` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` int(11) NOT NULL,
  `ID_Fornecedor` int(11) NOT NULL,
  `Quantidade` decimal(5,2) NOT NULL,
  `Valor_Unitario` decimal(7,2) DEFAULT NULL,
  `Data` datetime NOT NULL,
  `ID_Sessao` int(11) NOT NULL,
  PRIMARY KEY (`ID_Entrada`),
  KEY `Codigo` (`Codigo`),
  KEY `ID_Sessao` (`ID_Sessao`),
  KEY `ID_Fornecedor` (`ID_Fornecedor`),
  CONSTRAINT `entradas_produtos_ibfk_1` FOREIGN KEY (`Codigo`) REFERENCES `produtos` (`Codigo`),
  CONSTRAINT `entradas_produtos_ibfk_2` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`),
  CONSTRAINT `entradas_produtos_ibfk_3` FOREIGN KEY (`ID_Fornecedor`) REFERENCES `fornecedores` (`ID_Fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estoques`
--

DROP TABLE IF EXISTS `estoques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoques` (
  `ID_Unidade` int(11) NOT NULL,
  `Codigo` int(11) NOT NULL,
  `Quantidade` decimal(5,2) DEFAULT NULL,
  `Valor_Total` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID_Unidade`,`Codigo`),
  KEY `Codigo` (`Codigo`),
  CONSTRAINT `estoques_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `estoques_ibfk_2` FOREIGN KEY (`Codigo`) REFERENCES `produtos` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `funcoes_usuarios`
--

DROP TABLE IF EXISTS `funcoes_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcoes_usuarios` (
  `ID_Funcao` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `itens_vendas`
--

DROP TABLE IF EXISTS `itens_vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens_vendas` (
  `ID_Venda` int(11) NOT NULL,
  `Codigo` int(11) NOT NULL,
  `Quantidade` decimal(5,2) NOT NULL,
  PRIMARY KEY (`ID_Venda`,`Codigo`),
  KEY `Codigo` (`Codigo`),
  CONSTRAINT `itens_vendas_ibfk_1` FOREIGN KEY (`ID_Venda`) REFERENCES `vendas` (`ID_Venda`),
  CONSTRAINT `itens_vendas_ibfk_2` FOREIGN KEY (`Codigo`) REFERENCES `produtos` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `precos_produtos`
--

DROP TABLE IF EXISTS `precos_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precos_produtos` (
  `ID_Unidade` int(11) NOT NULL,
  `Codigo` int(11) NOT NULL,
  `Valor` decimal(7,2) NOT NULL,
  `Data` datetime NOT NULL,
  PRIMARY KEY (`ID_Unidade`,`Codigo`,`Data`),
  KEY `Codigo` (`Codigo`),
  CONSTRAINT `precos_produtos_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `precos_produtos_ibfk_2` FOREIGN KEY (`Codigo`) REFERENCES `produtos` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `Codigo` int(11) NOT NULL,
  `Descricao` varchar(60) NOT NULL,
  `Descricao_Reduzida` varchar(50) DEFAULT NULL,
  `Venda_Fracionada` tinyint(1) DEFAULT '0',
  `ID_Categoria` int(11) NOT NULL,
  `Imagem` blob,
  `Codigo_De_Barras` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categorias_produtos` (`ID_Categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produtos_retirados`
--

DROP TABLE IF EXISTS `produtos_retirados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos_retirados` (
  `ID_Retirada` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` int(11) NOT NULL,
  `Quantidade` decimal(5,2) DEFAULT NULL,
  `Data` datetime NOT NULL,
  `ID_Sessao` int(11) NOT NULL,
  `ID_Motivo` int(11) NOT NULL,
  PRIMARY KEY (`ID_Retirada`),
  KEY `Codigo` (`Codigo`),
  KEY `ID_Sessao` (`ID_Sessao`),
  KEY `ID_Motivo` (`ID_Motivo`),
  CONSTRAINT `produtos_retirados_ibfk_1` FOREIGN KEY (`Codigo`) REFERENCES `produtos` (`Codigo`),
  CONSTRAINT `produtos_retirados_ibfk_2` FOREIGN KEY (`ID_Sessao`) REFERENCES `sessoes` (`ID_Sessao`),
  CONSTRAINT `produtos_retirados_ibfk_3` FOREIGN KEY (`ID_Motivo`) REFERENCES `motivos_produtos_retirados` (`ID_Motivo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `unidades`
--

DROP TABLE IF EXISTS `unidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidades` (
  `ID_Unidade` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Unidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`ID_Usuario`),
  KEY `ID_Unidade` (`ID_Unidade`),
  KEY `ID_Funcao` (`ID_Funcao`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_Unidade`) REFERENCES `unidades` (`ID_Unidade`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`ID_Funcao`) REFERENCES `funcoes_usuarios` (`ID_Funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-25 10:45:43
