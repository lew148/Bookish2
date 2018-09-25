CREATE DATABASE `bookish` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorFirstName` varchar(45) DEFAULT NULL,
  `authorLastName` varchar(45) DEFAULT NULL,
  `authorcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `authortobook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookID` int(11) DEFAULT NULL,
  `authorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authorToBookLink_idx` (`authorID`),
  KEY `bookToAuthor-BookLink_idx` (`bookID`),
  CONSTRAINT `authorToAuthor-BookLink` FOREIGN KEY (`authorID`) REFERENCES `author` (`idauthor`),
  CONSTRAINT `bookToAuthor-BookLink` FOREIGN KEY (`bookID`) REFERENCES `books` (`idbooks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(45) DEFAULT NULL,
  `checkedOut` tinyint(4) DEFAULT NULL,
  `isbn` varchar(45) DEFAULT NULL,
  `copyNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `loans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownerID` int(11) DEFAULT NULL,
  `bookID` int(11) DEFAULT NULL,
  `dateOfLoan` datetime DEFAULT NULL,
  `dateOfExpectedReturn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `books_idx` (`bookID`),
  KEY `loansToAccount_idx` (`ownerID`),
  CONSTRAINT `loansToAccount` FOREIGN KEY (`ownerID`) REFERENCES `accounts` (`idaccounts`),
  CONSTRAINT `loansToBook` FOREIGN KEY (`bookID`) REFERENCES `books` (`idbooks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;