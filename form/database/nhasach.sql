-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2019 at 11:44 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nhasach`
--

-- --------------------------------------------------------

--
-- Table structure for table `ban_doc`
--

CREATE TABLE `ban_doc` (
  `Ma_Ban_Doc` int(225) NOT NULL,
  `Username` varchar(225) NOT NULL,
  `Password` varchar(225) NOT NULL,
  `Ten_Ban_Doc` varchar(225) NOT NULL,
  `Ngay_Sinh` date NOT NULL,
  `Dia_Chi` varchar(225) NOT NULL,
  `Dien_Thoai` int(12) NOT NULL,
  `Ma_Ban_Doc_Hien_Hanh` int(225) NOT NULL,
  `Da_Xoa` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ban_doc`
--

INSERT INTO `ban_doc` (`Ma_Ban_Doc`, `Username`, `Password`, `Ten_Ban_Doc`, `Ngay_Sinh`, `Dia_Chi`, `Dien_Thoai`, `Ma_Ban_Doc_Hien_Hanh`, `Da_Xoa`) VALUES
(1, 'user', '123', 'Mai Dang Khoa', '1997-09-10', '160/67 bis blah blah', 977446464, 1, 0),
(2, 'tuan', '123', 'Nguyen Quoc Tuan', '1997-09-17', '1234 avenue', 433333333, 1, 0),
(3, 'hoa', '123', 'Tran Van Hoa', '1989-09-11', '65 avenua', 443233343, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_muon`
--

CREATE TABLE `phieu_muon` (
  `Ma_Phieu_Muon` int(225) NOT NULL,
  `Ma_Ban_Doc` int(225) NOT NULL DEFAULT 0,
  `Ma_Sach` int(225) NOT NULL,
  `Ngay_Muon` date NOT NULL,
  `Han_Tra` date NOT NULL,
  `Ngay_Tra` date DEFAULT NULL,
  `Ma_Ban_Doc_Hien_Hanh` int(11) NOT NULL DEFAULT 0,
  `Tra_Sach` tinyint(1) NOT NULL DEFAULT 0,
  `Da_Xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `phieu_muon`
--

INSERT INTO `phieu_muon` (`Ma_Phieu_Muon`, `Ma_Ban_Doc`, `Ma_Sach`, `Ngay_Muon`, `Han_Tra`, `Ngay_Tra`, `Ma_Ban_Doc_Hien_Hanh`, `Tra_Sach`, `Da_Xoa`) VALUES
(16, 2, 1, '2019-09-13', '2019-09-19', '2019-09-17', 1, 1, 1),
(17, 2, 2, '2019-09-13', '2019-09-18', '2019-09-21', 1, 1, 1),
(18, 2, 3, '2019-09-10', '2019-09-25', '2019-09-15', 1, 1, 0),
(19, 2, 4, '2019-09-13', '2019-09-18', NULL, 1, 1, 0),
(20, 1, 6, '2019-09-18', '2019-09-25', '2019-09-21', 1, 1, 0),
(21, 1, 1, '2019-09-21', '2019-09-24', NULL, 1, 0, 1),
(22, 3, 5, '2019-09-21', '2019-09-23', NULL, 1, 0, 0),
(23, 4, 1, '2019-09-21', '2019-09-24', NULL, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `quan_tri`
--

CREATE TABLE `quan_tri` (
  `Ma_Admin` int(225) NOT NULL,
  `username` varchar(225) NOT NULL,
  `Password` varchar(225) NOT NULL,
  `Da_Xoa` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quan_tri`
--

INSERT INTO `quan_tri` (`Ma_Admin`, `username`, `Password`, `Da_Xoa`) VALUES
(1, 'khoa', '123', 0),
(2, 'user123', '1234', 0),
(3, 'nam', '1', 1),
(4, 'tuan', 'tuan123', 0),
(5, 'test', '1234', 0),
(6, 'test', '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sach`
--

CREATE TABLE `sach` (
  `Ma_Sach` int(225) NOT NULL,
  `Ten_Sach` varchar(225) NOT NULL,
  `Ten_Tac_Gia` varchar(225) NOT NULL,
  `Nha_XB` varchar(225) NOT NULL,
  `Gia_Tien` int(11) NOT NULL,
  `So_Luong` int(11) NOT NULL,
  `Da_Xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sach`
--

INSERT INTO `sach` (`Ma_Sach`, `Ten_Sach`, `Ten_Tac_Gia`, `Nha_XB`, `Gia_Tien`, `So_Luong`, `Da_Xoa`) VALUES
(1, 'Doremon', 'Fuji', 'Kim Dong', 3000, 2, 0),
(2, 'Harry Potter', 'J K Rowling', 'Kim Dong', 70000, 37, 0),
(3, 'Nhat Ky Cau Be Nhut Nhat', 'Jeff Kinney', 'Fahasa', 30000, 45, 0),
(4, 'Percy Jackson', 'Rick Riordan', 'Fahasa', 90000, 45, 1),
(5, 'Than Dong Dat Viet ', 'Le Linh', 'Phuong Nam', 14000, 0, 0),
(6, 'Nhung Nguoi Khon Kho', 'Victor Hugo', 'Nguyen Hue', 50000, 5, 0),
(7, 'Kinh Van Hoa', 'Nguyen Nhat Anh', 'BGD', 45000, 49, 1),
(9, 'Twilight', 'No Name', 'BTX', 30000, 4, 0),
(10, 'Twilight Part II', 'No Name', 'Fahasa', 40000, 40, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ban_doc`
--
ALTER TABLE `ban_doc`
  ADD PRIMARY KEY (`Ma_Ban_Doc`);

--
-- Indexes for table `phieu_muon`
--
ALTER TABLE `phieu_muon`
  ADD PRIMARY KEY (`Ma_Phieu_Muon`);

--
-- Indexes for table `quan_tri`
--
ALTER TABLE `quan_tri`
  ADD PRIMARY KEY (`Ma_Admin`);

--
-- Indexes for table `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`Ma_Sach`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ban_doc`
--
ALTER TABLE `ban_doc`
  MODIFY `Ma_Ban_Doc` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phieu_muon`
--
ALTER TABLE `phieu_muon`
  MODIFY `Ma_Phieu_Muon` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `quan_tri`
--
ALTER TABLE `quan_tri`
  MODIFY `Ma_Admin` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sach`
--
ALTER TABLE `sach`
  MODIFY `Ma_Sach` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
