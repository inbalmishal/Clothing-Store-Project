--
-- Table structure for table `allpurchase`
--

DROP TABLE IF EXISTS `allpurchase`;
CREATE TABLE `allpurchase` (
  `id` int DEFAULT NULL,
  `itemid` int DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `shoppingrating` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `allpurchase`
--

LOCK TABLES `allpurchase` WRITE;
INSERT INTO `allpurchase` VALUES (1,1,'2020-3-30',80),(1,2,'2020-3-30',80),(1,3,'2020-5-4',90),(1,4,'2020-5-4',90),(2,5,'2020-5-10',80),(2,6,'2020-5-15',90),(2,7,'2020-5-15',90),(3,8,'2020-5-20',10),(3,1,'2020-5-25',90),(3,2,'2020-5-25',90),(4,3,'2020-4-4',50),(4,4,'2020-4-15',60),(4,5,'2020-4-15',60),(4,6,'2020-4-15',60);
UNLOCK TABLES;

--
-- Table structure for table `clubmembers`
--

DROP TABLE IF EXISTS `clubmembers`;
CREATE TABLE `clubmembers` (
  `name` varchar(45) DEFAULT NULL,
  `id` int NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `pointgained` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `clubmembers`
--

LOCK TABLES `clubmembers` WRITE;
INSERT INTO `clubmembers` VALUES ('Yuval',1,'1999-2-30','4539'),('Matan',2,'2000-5-4','1113'),('Inbar',3,'2001-1-1','9493'),('Noa',4,'2004-4-4','3454'),('test',9999,'1990-3-4','1111');
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `itemid` int NOT NULL,
  `color` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `size` int NOT NULL,
  `brand` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `drawstringcolor` varchar(45) NOT NULL,
  `pantstype` varchar(45) NOT NULL,
  `shirtstype` varchar(45) NOT NULL,
  `basestock` int NOT NULL,
  `currentStock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
INSERT INTO `items` VALUES (1,'blue',300,'shoe',40,'nabibas','men','red','null','null',500,200),(2,'red',400,'shoe',40,'niki','women','blue','null','null',400,100),(3,'black',1000,'pants',30,'nabibas','men','null','bermuda','null',100,50),(4,'black',500,'pants',30,'niki','women','null','jeans','null',1000,900),(5,'green',2000,'shirt',20,'nabibas','men','null','null','lycra',550,50),(6,'yellow',5000,'shirt',20,'niki','women','null','null','drifit',450,430),(7,'white',6000,'shirt',20,'crocodile','men','null','null','polo',200,197),(8,'black',50,'pants',30,'crocodile','women','null','skirt shorts','null',500,400),(9,'white',20,'shirt',50,'nikiba','men','null','null','bermuda',100,50),(10,'black',20,'shirt',50,'nikiba','men','null','null','bermuda',100,50);
UNLOCK TABLES;

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
CREATE TABLE `workers` (
  `name` varchar(45) DEFAULT NULL,
  `id` int DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `hourlysalary` int DEFAULT NULL,
  `numHourMonth` int DEFAULT NULL,
  `jobType` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `workers`
--

LOCK TABLES `workers` WRITE;
INSERT INTO `workers` VALUES ('yoni',12,'1999-4-3',500,60,'manager','1234'),('kobi',10,'2000-5-4',100,50,'worker','0404'),('yakov',30,'2005-6-4',100,50,'worker','1111'),(NULL,NULL,NULL,NULL,NULL,NULL,'');
UNLOCK TABLES;
