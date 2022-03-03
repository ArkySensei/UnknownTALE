-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2020 at 12:15 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unknowntale`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `UID` int(11) NOT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Nickname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`UID`, `Username`, `Password`, `Nickname`) VALUES
(1, 'Vantus2001', '123456', 'Vantus'),
(2, 'GodBlader', 'ilovegod8', 'SoloDrive'),
(3, 'GodBlader', 'ASD', '12345'),
(4, 'GodBlader', '12345', '12332132'),
(5, 'GodBlader', 'asd', 'asd'),
(6, 'ASD', '123456', '213123213'),
(7, 'Lmao', '123456', 'Lmao'),
(8, 'OP', 'Op123', 'Opman'),
(9, 'applestem', 'apple123', 'AppleBoy'),
(10, 'Maple', 'maple123', 'MapleEx'),
(11, 'MyAdmin', '123456', 'Advertise'),
(12, 'HelloWorld', '123456', 'Hello'),
(13, 'ZoowieMAma', 'Nani123', 'Zamamiru'),
(14, 'Sandy', 'sandy123', 'Sandy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD UNIQUE KEY `UID` (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
