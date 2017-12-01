-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: core_estagios
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id` int(11) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `curso` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `imagem` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'19375192859','Hewitt Benz','Análise e Desenvolvimento de Sistemas','Female',NULL,'hbenz0@miitbeian.gov.cn'),(2,'97050950369','Rowney Seligson','Análise e Desenvolvimento de Sistemas','Male',NULL,'rseligson1@clickbank.net'),(3,'57607338835','Alyosha Sentance','Análise e Desenvolvimento de Sistemas','Male',NULL,'asentance2@sun.com'),(4,'42723864821','Emanuele Lacknor','Análise e Desenvolvimento de Sistemas','Female',NULL,'elacknor3@cnn.com'),(5,'35905909999','Cherlyn Yardy','Análise e Desenvolvimento de Sistemas','Male',NULL,'cyardy4@gnu.org'),(6,'87037987886','Raddie Alf','Análise e Desenvolvimento de Sistemas','Female',NULL,'ralf5@accuweather.com'),(7,'18393703091','Lonee Bush','Análise e Desenvolvimento de Sistemas','Male',NULL,'lbush6@timesonline.co.uk'),(8,'41314021117','Viola Mulder','Análise e Desenvolvimento de Sistemas','Female',NULL,'vmulder7@nba.com'),(9,'42410713635','Lee Bastistini','Análise e Desenvolvimento de Sistemas','Male',NULL,'lbastistini8@house.gov'),(10,'96017066768','Danell Torre','Análise e Desenvolvimento de Sistemas','Female',NULL,'dtorre9@chron.com'),(11,'74222704669','Thor Moye','Análise e Desenvolvimento de Sistemas','Female',NULL,'tmoyea@upenn.edu'),(12,'78392143628','Gaston Rojas','Análise e Desenvolvimento de Sistemas','Male',NULL,'grojasb@indiatimes.com'),(13,'31333666992','Blithe Biagi','Análise e Desenvolvimento de Sistemas','Male',NULL,'bbiagic@eepurl.com'),(14,'44669725383','Elizabeth Collimore','Análise e Desenvolvimento de Sistemas','Male',NULL,'ecollimored@desdev.cn'),(15,'28183180262','Nada Forster','Análise e Desenvolvimento de Sistemas','Male',NULL,'nforstere@hc360.com'),(16,'49622103835','Adena O\'Cullinane','Análise e Desenvolvimento de Sistemas','Female',NULL,'aocullinanef@chicagotribune.com'),(17,'70844697612','Hermie Antao','Análise e Desenvolvimento de Sistemas','Female',NULL,'hantaog@chicagotribune.com'),(18,'32588230150','Emmanuel Wardale','Análise e Desenvolvimento de Sistemas','Female',NULL,'ewardaleh@gov.uk'),(19,'73572939056','Cleopatra Tamblyn','Análise e Desenvolvimento de Sistemas','Male',NULL,'ctamblyni@tripod.com'),(20,'12489171465','Manfred Pender','Análise e Desenvolvimento de Sistemas','Male',NULL,'mpenderj@newyorker.com'),(21,'13469969653','Ailyn Vearncombe','Análise e Desenvolvimento de Sistemas','Male',NULL,'avearncombek@stumbleupon.com'),(22,'61983746674','Sheryl Betty','Análise e Desenvolvimento de Sistemas','Male',NULL,'sbettyl@bloglovin.com'),(23,'32475580161','Sherilyn Bilt','Análise e Desenvolvimento de Sistemas','Female',NULL,'sbiltm@nydailynews.com'),(24,'82207147108','Orton Jados','Análise e Desenvolvimento de Sistemas','Female',NULL,'ojadosn@constantcontact.com'),(25,'04529926995','Laurella Joiris','Análise e Desenvolvimento de Sistemas','Female',NULL,'ljoiriso@jiathis.com'),(26,'76061733632','Raviv Spyvye','Análise e Desenvolvimento de Sistemas','Male',NULL,'rspyvyep@usatoday.com'),(27,'83842651763','Any Dannohl','Análise e Desenvolvimento de Sistemas','Male',NULL,'adannohlq@ibm.com'),(28,'32234182458','Isabella Dorrance','Análise e Desenvolvimento de Sistemas','Male',NULL,'idorrancer@google.pl'),(29,'72400933555','Waylen Glaze','Análise e Desenvolvimento de Sistemas','Female',NULL,'wglazes@aol.com'),(30,'86501249211','Karla Matresse','Análise e Desenvolvimento de Sistemas','Female',NULL,'kmatresset@loc.gov'),(31,'60618104492','Allison Child','Análise e Desenvolvimento de Sistemas','Male',NULL,'achildu@ning.com'),(32,'40986733635','Georgi Josskoviz','Análise e Desenvolvimento de Sistemas','Male',NULL,'gjosskovizv@gnu.org'),(33,'56975168306','Hesther Volette','Análise e Desenvolvimento de Sistemas','Female',NULL,'hvolettew@ftc.gov'),(34,'69459098677','Tory Rudd','Análise e Desenvolvimento de Sistemas','Female',NULL,'truddx@jimdo.com'),(35,'05828778323','Butch Beldan','Análise e Desenvolvimento de Sistemas','Male',NULL,'bbeldany@last.fm'),(36,'75393168513','Tresa Crosland','Análise e Desenvolvimento de Sistemas','Female',NULL,'tcroslandz@furl.net'),(37,'03821416295','Torrie Payn','Análise e Desenvolvimento de Sistemas','Male',NULL,'tpayn10@cbslocal.com'),(38,'50491317405','Brandi Hudleston','Análise e Desenvolvimento de Sistemas','Male',NULL,'bhudleston11@vinaora.com'),(39,'95845542252','Benedetta Greydon','Análise e Desenvolvimento de Sistemas','Male',NULL,'bgreydon12@list-manage.com'),(40,'06144183437','Lyman De Ath','Análise e Desenvolvimento de Sistemas','Male',NULL,'lde13@dagondesign.com'),(41,'08456921890','Hirsch Pinwell','Análise e Desenvolvimento de Sistemas','Female',NULL,'hpinwell14@issuu.com'),(42,'44015001022','Barnabe Hyslop','Análise e Desenvolvimento de Sistemas','Male',NULL,'bhyslop15@woothemes.com'),(43,'82712000648','Ibbie Bootland','Análise e Desenvolvimento de Sistemas','Female',NULL,'ibootland16@tamu.edu'),(44,'48796184164','Charity Tilte','Análise e Desenvolvimento de Sistemas','Female',NULL,'ctilte17@facebook.com'),(45,'41765805908','Mollie Placido','Análise e Desenvolvimento de Sistemas','Female',NULL,'mplacido18@paypal.com'),(46,'33923197899','Sumner Blockley','Análise e Desenvolvimento de Sistemas','Female',NULL,'sblockley19@cnbc.com'),(47,'58646307696','Hanna Crowcombe','Análise e Desenvolvimento de Sistemas','Male',NULL,'hcrowcombe1a@github.com'),(48,'82979855891','Kendrick Cettell','Análise e Desenvolvimento de Sistemas','Male',NULL,'kcettell1b@hubpages.com'),(49,'84652307641','Karney Esch','Análise e Desenvolvimento de Sistemas','Female',NULL,'kesch1c@narod.ru'),(50,'47974670693','Darb Pigeon','Análise e Desenvolvimento de Sistemas','Male',NULL,'dpigeon1d@chronoengine.com');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Yakidoo',NULL,'Kansas City','United States','2 Parkside Terrace'),(2,'Avavee',NULL,'Albany','United States','21001 Namekagon Trail'),(3,'Wordpedia',NULL,'Washington','United States','1149 Sutherland Terrace'),(4,'Quaxo',NULL,'Ocala','United States','6 Dakota Avenue'),(5,'Babbleset',NULL,'Erie','United States','40968 Kennedy Trail'),(6,'Tagopia',NULL,'Santa Ana','United States','338 Manley Pass'),(7,'DabZ',NULL,'Kansas City','United States','1028 Manitowish Crossing'),(8,'Voomm',NULL,'Detroit','United States','29 Brentwood Way'),(9,'Agimba',NULL,'New York City','United States','194 Union Plaza'),(10,'Skilith',NULL,'El Paso','United States','8 Hollow Ridge Alley');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estagio`
--

DROP TABLE IF EXISTS `estagio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estagio` (
  `aluno` int(11) NOT NULL,
  `empresa` int(11) NOT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date DEFAULT NULL,
  PRIMARY KEY (`aluno`,`empresa`),
  KEY `fk_Aluno_has_Empresa_Empresa1_idx` (`empresa`),
  KEY `fk_Aluno_has_Empresa_Aluno_idx` (`aluno`),
  CONSTRAINT `fk_Aluno_has_Empresa_Aluno` FOREIGN KEY (`aluno`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aluno_has_Empresa_Empresa1` FOREIGN KEY (`empresa`) REFERENCES `empresa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estagio`
--

LOCK TABLES `estagio` WRITE;
/*!40000 ALTER TABLE `estagio` DISABLE KEYS */;
/*!40000 ALTER TABLE `estagio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-16 15:44:12
