-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2021 at 10:32 AM
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
-- Database: `icash`
--

-- --------------------------------------------------------

--
-- Table structure for table `security_questions`
--

CREATE TABLE `security_questions` (
  `security_question_id` int(200) NOT NULL,
  `security_question` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `security_questions`
--

INSERT INTO `security_questions` (`security_question_id`, `security_question`) VALUES
(1, 'Fav Dish'),
(2, 'Girl?'),
(3, 'song?'),
(4, 'FRIEND'),
(5, 'HOME');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `fullname` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `salt` varchar(200) NOT NULL,
  `securityQuestion` varchar(200) NOT NULL,
  `answer` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `fullname`, `email`, `password`, `gender`, `salt`, `securityQuestion`, `answer`) VALUES
(1, 'aye', 'aye', 'aye@g.c', '$2y$10$o9U.XW7PN.oPddvOMpqxweCrYb6wiBHWyKM1XC6oi7s9LmQxZO9tO', 'Male', '95a7b7cf1046f796f7738c1f0d1d4f29ee693b401e79fbf7a4360718df00f784', 'f', 'u'),
(2, 'ayee', 'ayee', 'ayee@g.c', '$2y$10$A8HGy.toDRfsVIWN6S1kfOuUsdadYzBdwC.1AGQsMGkCKqOhQA65m', 'Female', '72ad64f0c5281e460b4fb99356515416aef4dc3af3b72afe39b768ed378a1842', 'Fav Dish', 'ugali'),
(3, 'kevo', 'kevo', 'kevo@gmail.com', '$2y$10$DVKCG/IhuAgJPsA.EX2qh.PpHUWWUjaImYQT/wblPdO5CYiubGvwW', 'Male', 'fb576455e59d408d9a4c8313464745f23a6b377daafda5b4f5d9f94afe8e427a', 'Fav Dish', 'ugali'),
(4, 'mama', 'mama', 'mama@g.com', '$2y$10$QkO92QvWXC9jm5lP7TBMzO5qjEhIA0HScMsqHJlmEgexdvg9I406W', 'Female', '14075650cea1323dbf5ab22a5ee2c8ade97f9ef7d121122f2720f7248523dc85', 'Fav Dish', 'mama'),
(5, 's', 's', 's@f.v', '$2y$10$Z7ddln9Fjmcoo0dfOu0YC.YG.o0cuklg9WRx2ggbpGfOm1vTs574a', 'Female', 'dd88792c7ce178ac0d312d16c4fdd6ed9bf04b598e91766402a3b8ac9597cf1e', '', ''),
(6, 'zack', 'zac', 'zack@h.x', '$2y$10$P17Rxi3RpLnvvazezeuFoOgU2G.vhntaaGpFydw18cZ15Ec0vqCL.', 'Female', '93ff6202969ae29a916080f33a4f3cb58275b52e995631517c2cb96e59ff8c25', '', ''),
(7, 'lea', 'lea', 'lea@g.b', '$2y$10$e51AHQHu3jUcpk3uVsYXmuHkwe2bsplPqLe.9WK6mOwNi1iV1MdKC', 'Female', '8249d7b1eb2d3b7388808252ebdf45866723f6290505469858e7b3510e5b0efa', '', ''),
(8, 'gvbfg', 'dyc', 'ggf@t.h', '$2y$10$0qy7U75cw8UBRORYDRsGlOkgUiA5WSRzR2hhq4YuYne/v/DXtXns6', 'Male', '48f613469c0f2aed94774acc45e677da8aed8fd338252c8335e8ada4e22d5fb9', '', ''),
(9, 'gvbfgh', 'dych', 'ggf@t.hh', '$2y$10$2fbShJj0Yb1phvrD42X/DO5BvNNPS.WSj/MmY/nA.KMk7uCHdQV8K', 'Male', '8397a0c7cd9e946ce2c195bf5753427b3b56b47662f3029ec1d4ff942463bdf7', 'song?', 'beautiful'),
(10, 'tee', 'tet', 't@g.d', '$2y$10$LEeTrIEFP8b33J6xSmLl9uBq8dRODOojQhs3gBxJHhn9TLTumlRvu', 'Female', 'a6cac5a622741a5b0cf181c6ae6688d007893caf24cbec94081ec7cde0c720d1', '', ''),
(11, 'teed', 'teth', 't@g.dj', '$2y$10$zDGJ9dUeXmEmf.d1ssI36.WKLpQEeTU89NzkK30ujaKIpyrySnTe.', 'Female', '20f0098a8adc1b4365082fee627f65c06089be7efe0b0d78e0d1bb912ec7bfb1', '', ''),
(12, 'teedc', 'teth', 't@g.djf', '$2y$10$mTLKtXfXT.7MJlmSJ2ylB.W78BfaqhXW9Jja6Cq.3XwDdEdTA7Fjm', 'Female', 'dc70a287e7d7ebf82d7906e98ed8110a4728a2a52ecb696dbfabecb93cb34e3a', '', ''),
(13, 'teedcg', 'teth', 't@g.djfv', '$2y$10$v3WY6l3Z9w1tuidxWiiO9uy6ALTcX5qUEBbmqJ8DsFOTkYTCEnkQS', 'Female', '5ceff0d5dac0910186766047c1af4908f8391a1b5377e3b87ad7b47d98df452c', '', ''),
(14, 'teedcgx', 'teth', 't@g.djfvc', '$2y$10$mLSrormDlhEN9cS43xKQaujnljgPFXXHky7FP.NpvSPIT5/jhCV4S', 'Female', 'c4b1a08ad918775c56da62bd49a7d47a2137e1fc1efdd1f5df7a1dc6e3adc365', 'FRIEND', 'yayee'),
(15, 'zii', 'zii', 'zii@g.c', '$2y$10$u8iCz0nmEdwT.phShuyoc.uRtX84TtBGmPlBuyFjh8uwStWeyiq9O', 'Male', '60b477160fbb5868e5babfd4c3de15b582915af35da10fbc5a9d49f933940108', 'Fav Dish', ''),
(16, 'sgs', 'gbs', 'gaha@hsh.d', '$2y$10$OPBv2uBGmmVneGkv7MSMJefzyppmqlS4VzpFYX47u77fM7A90bChC', 'Female', 'a77f29ecf386d94cb60c6388f9549e1b4a1a9c29ad4051881a198652b90d5bb2', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_logs`
--

CREATE TABLE `user_logs` (
  `log_id` int(200) NOT NULL,
  `user_id` int(200) NOT NULL,
  `devices_logged_in` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_logs`
--

INSERT INTO `user_logs` (`log_id`, `user_id`, `devices_logged_in`) VALUES
(1, 1, 1),
(2, 1, 0),
(3, 1, 1),
(4, 1, 0),
(5, 1, 1),
(6, 1, 0),
(7, 1, 1),
(8, 1, 0),
(9, 1, 1),
(10, 1, 0),
(11, 1, 1),
(12, 1, 2),
(13, 1, 1),
(14, 4, 1),
(15, 4, 2),
(16, 4, 1),
(17, 1, 2),
(18, 1, 1),
(19, 4, 2),
(20, 4, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `security_questions`
--
ALTER TABLE `security_questions`
  ADD PRIMARY KEY (`security_question_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_logs`
--
ALTER TABLE `user_logs`
  ADD PRIMARY KEY (`log_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `security_questions`
--
ALTER TABLE `security_questions`
  MODIFY `security_question_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user_logs`
--
ALTER TABLE `user_logs`
  MODIFY `log_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
