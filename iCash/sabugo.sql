-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2021 at 10:09 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sabugo`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(200) NOT NULL,
  `customer_name` varchar(200) NOT NULL,
  `customer_phone` int(200) NOT NULL,
  `shop_id` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `customer_name`, `customer_phone`, `shop_id`) VALUES
(1, 'KEVO', 0, 6),
(2, 'EDGAR', 0, 4),
(3, 'OUMA', 0, 4),
(4, 'SAMUEL', 0, 4),
(5, 'MALOSH', 0, 6),
(6, 'MALONGO', 0, 6),
(7, 'KELVIN', 0, 6),
(8, 'OBANDE', 0, 1),
(9, 'SAMMY', 0, 1),
(11, 'JUSTUS', 0, 3),
(12, 'CLIVE', 0, 3),
(13, 'KAMALIKI', 0, 3),
(14, 'JOSEPH', 0, 5),
(15, 'JOSPHAT', 0, 5),
(16, 'JACKY', 0, 2),
(17, 'ITHA', 0, 2),
(18, 'WAMAITHA', 0, 2),
(19, 'STEVE', 0, 7),
(20, 'TANUI', 0, 7),
(21, 'GIDEON', 0, 8),
(22, 'CHEROP', 0, 8),
(23, 'YAYEEZ', 0, 5),
(24, 'KIMS', 0, 4),
(25, 'SHARON', 790780464, 9);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(200) NOT NULL,
  `product_buying_price` int(11) NOT NULL,
  `min_stock` int(11) NOT NULL,
  `current_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_buying_price`, `min_stock`, `current_stock`) VALUES
(1, 'HIGH GAS', 1200, 0, 0),
(2, 'HELIUM 20KG', 35000, 0, 0),
(3, 'OILIBYA CYLINDER 13KG', 1200, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(200) NOT NULL,
  `role` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(0, 'NO ROLE'),
(1, 'SALESMAN'),
(2, 'MANAGER'),
(3, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `shops_mobiles`
--

CREATE TABLE `shops_mobiles` (
  `shops_mobile_id` int(200) NOT NULL,
  `shops_mobile_name` varchar(200) NOT NULL,
  `user_assigned_id` int(200) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shops_mobiles`
--

INSERT INTO `shops_mobiles` (`shops_mobile_id`, `shops_mobile_name`, `user_assigned_id`) VALUES
(1, 'KITALE', 0),
(2, 'NAITIRI', 0),
(3, 'LUMAKANDA', 3),
(4, 'KIMILILI', 0),
(5, 'MOI\'S BRIDGE', 2),
(6, 'MATUNDA', 0),
(7, 'SOY', 0),
(8, 'ZIWA', 0),
(9, 'ZAYUNI', 0);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` int(200) NOT NULL,
  `supplier_name` varchar(200) NOT NULL,
  `supplier_email` varchar(200) NOT NULL,
  `supplier_phone` int(200) NOT NULL,
  `supplier_kra` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `supplier_name`, `supplier_email`, `supplier_phone`, `supplier_kra`) VALUES
(1, 'OLA', '', 0, ''),
(2, 'MIRIMI', '', 0, ''),
(3, 'ABDI', 'ABDI@GMAIL.COM', 790780464, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(200) NOT NULL,
  `fullname` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `salt` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` int(200) NOT NULL,
  `id_number` int(200) NOT NULL,
  `nhif` varchar(200) NOT NULL,
  `nssf` varchar(200) NOT NULL,
  `kra_pin` varchar(200) NOT NULL,
  `role` int(200) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `username`, `salt`, `password`, `phone`, `id_number`, `nhif`, `nssf`, `kra_pin`, `role`) VALUES
(1, 'ZACK MWANGI', 'ZAC', '18de30c76211a9d986c4ee925fe3c6024ffb1f8ee8993a103c886c59059534b8', '$2y$10$J3/k3b5NU4mXsZVSPIVifeydNtOyYDpoD9BN4viD78nY7w253q3Y6', 1, 1, 'HGFYFYU54', 'RRTYG516', '48884546664', 3),
(2, 'BUNDOX RIENG', 'BUNDOX', 'c0e3408cd37720e9170fe627459a2845106050c87d05c6189df170aa459ee760', '$2y$10$8LTO0fEYXAFktARX.JotKOJNsqbKW3lTXgHQa2ZVg.SswzYHHsxDi', 790780464, 35736109, '', '', '', 1),
(3, 'STEPHEN OTIENO', 'OTIS', '893d33557651de11784d9a6cc2e776a4a52a2f6801862b4a0e6be4ec357c5745', '$2y$10$4pJbbw4DN2/3WQ4jeMguqeGiKMGJLKzhWQPHWQS0.WT8SAP17JeJi', 790780464, 35736109, '', '', '', 1),
(4, 'AYE AYE', 'AYE', '40528458e28a3d21a5072ba198eef56445c61992b3eec3dfe4c0e8e47d6a7f94', '$2y$10$tvJWcQFXV549VuggRfLiW.lmnNoGoVMNFvyIY9KlUy4SToF0jPxHa', 722000000, 25358962, '', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `shops_mobiles`
--
ALTER TABLE `shops_mobiles`
  ADD PRIMARY KEY (`shops_mobile_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shops_mobiles`
--
ALTER TABLE `shops_mobiles`
  MODIFY `shops_mobile_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
