-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 24, 2022 at 08:17 AM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `javafx`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users`
(
    `id`        int(11)     NOT NULL,
    `firstname` varchar(80) NOT NULL,
    `lastname`  varchar(80) NOT NULL,
    `email`     varchar(80) NOT NULL,
    `password`  varchar(80) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `email`, `password`)
VALUES (1, 'Boris', 'S', 'boris@gmail.com', 'qwerty'),
       (2, 'Coraline', 'R', 'coraline@gmail.com', 'azerty');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 9;
