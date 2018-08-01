-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17 Jan 2018 pada 07.59
-- Versi Server: 10.1.19-MariaDB
-- @Author : Wanda Priatna 
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appusers`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_remmitance_account`
--

CREATE TABLE `create_remmitance_account` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(10) NOT NULL,
  `trans_date_time` varchar(25) NOT NULL,
  `inst_id` varchar(10) NOT NULL,
  `account_id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `address` varchar(100) NOT NULL,
  `birth_date` varchar(20) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `occupation` varchar(50) NOT NULL,
  `citizenship` varchar(100) NOT NULL,
  `id_number` varchar(50) NOT NULL,
  `fund_resource` varchar(100) NOT NULL,
  `regency_code` varchar(100) NOT NULL,
  `signature` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_remmitance_account_resp`
--

CREATE TABLE `create_remmitance_account_resp` (
  `id` varchar(50) NOT NULL,
  `trxstan` varchar(20) NOT NULL,
  `trx_trans_date_time` varchar(30) NOT NULL,
  `trx_inst_id` varchar(30) NOT NULL,
  `sender_account_id` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_address` varchar(100) NOT NULL,
  `sender_birth_date` varchar(50) NOT NULL,
  `sender_phone_number` varchar(50) NOT NULL,
  `sender_occupation` varchar(100) NOT NULL,
  `sender_citizenship` varchar(50) NOT NULL,
  `sender_id_number` varchar(50) NOT NULL,
  `sender_fund_resource` varchar(100) NOT NULL,
  `benef_inst_id` varchar(100) NOT NULL,
  `benefAccount_id` varchar(100) NOT NULL,
  `benef_name` varchar(100) NOT NULL,
  `benef_relationship` varchar(100) NOT NULL,
  `benef_regency_code` varchar(100) NOT NULL,
  `err_code` varchar(50) NOT NULL,
  `err_description` varchar(100) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_remmitance_transfer`
--

CREATE TABLE `create_remmitance_transfer` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(6) NOT NULL,
  `trx_trans_date_time` varchar(50) NOT NULL,
  `trx_inst_id` varchar(50) NOT NULL,
  `trx_proc_code` varchar(50) NOT NULL,
  `trx_channel_type` varchar(50) NOT NULL,
  `trx_ref_number` varchar(50) NOT NULL,
  `trx_terminal_id` varchar(50) NOT NULL,
  `trx_country_code` varchar(50) NOT NULL,
  `trx_local_date_time` varchar(50) NOT NULL,
  `sender_account_id` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_curr_code` varchar(50) NOT NULL,
  `sender_amount` varchar(50) NOT NULL,
  `sender_rate` varchar(50) NOT NULL,
  `sender_area_code` varchar(50) NOT NULL,
  `benef_inst_id` varchar(50) NOT NULL,
  `benef_account_id` varchar(50) NOT NULL,
  `benef_curr_code` varchar(50) NOT NULL,
  `benef_amount` varchar(50) NOT NULL,
  `benef_cust_ref_number` varchar(50) NOT NULL,
  `benef_name` varchar(50) NOT NULL,
  `benef_regency_code` varchar(50) NOT NULL,
  `benef_purpose_code` varchar(50) NOT NULL,
  `benef_purpose_description` varchar(50) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_remmitance_transfer_resp`
--

CREATE TABLE `create_remmitance_transfer_resp` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(10) NOT NULL,
  `trx_trans_date_time` varchar(50) NOT NULL,
  `trx_inst_id` varchar(50) NOT NULL,
  `sender_account_id` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_curr_code` varchar(50) NOT NULL,
  `sender_amount` varchar(50) NOT NULL,
  `sender_rate` varchar(50) NOT NULL,
  `sender_area_code` varchar(50) NOT NULL,
  `benef_inst_id` varchar(50) NOT NULL,
  `benef_account_id` varchar(50) NOT NULL,
  `benef_curr_code` varchar(50) NOT NULL,
  `benef_amount` varchar(50) NOT NULL,
  `benef_cust_ref_number` varchar(50) NOT NULL,
  `benef_name` varchar(50) NOT NULL,
  `benef_regency_code` varchar(50) NOT NULL,
  `benef_purpose_code` varchar(50) NOT NULL,
  `benef_purpose_desc` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `Data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_transfer_inquiry`
--

CREATE TABLE `create_transfer_inquiry` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(6) NOT NULL,
  `trans_date_time` varchar(30) NOT NULL,
  `inst_id` varchar(30) NOT NULL,
  `proc_code` varchar(30) NOT NULL,
  `channel_type` varchar(50) NOT NULL,
  `ref_number` varchar(50) NOT NULL,
  `terminal_id` varchar(50) NOT NULL,
  `country_code` varchar(50) NOT NULL,
  `local_date_time` varchar(50) NOT NULL,
  `sender_account_id` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `curr_code` varchar(50) NOT NULL,
  `sender_amount` varchar(50) NOT NULL,
  `sender_rate` varchar(50) NOT NULL,
  `area_code` varchar(50) NOT NULL,
  `benef_inst_id` varchar(50) NOT NULL,
  `benef_account_id` varchar(50) NOT NULL,
  `benef_curr_code` varchar(50) NOT NULL,
  `benef_amount` varchar(50) NOT NULL,
  `benef_cust_ref_number` varchar(50) NOT NULL,
  `benef_regency_code` varchar(50) NOT NULL,
  `benef_purpose_code` varchar(50) NOT NULL,
  `benef_purpose_desc` varchar(50) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `create_transfer_inquiry_resp`
--

CREATE TABLE `create_transfer_inquiry_resp` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(10) NOT NULL,
  `trans_date_time` varchar(50) NOT NULL,
  `inst_id` varchar(50) NOT NULL,
  `sender_account_id` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_curr_code` varchar(50) NOT NULL,
  `sender_amount` varchar(20) NOT NULL,
  `sender_rate` varchar(50) NOT NULL,
  `sender_area_code` varchar(50) NOT NULL,
  `benef_inst_id` varchar(50) NOT NULL,
  `benef_account_id` varchar(50) NOT NULL,
  `benef_curr_code` varchar(50) NOT NULL,
  `benef_amount` varchar(50) NOT NULL,
  `benef_cust_ref_number` varchar(50) NOT NULL,
  `benef_name` varchar(50) NOT NULL,
  `benef_regency_code` varchar(50) NOT NULL,
  `benef_purpose_code` varchar(50) NOT NULL,
  `benef_purpose_desc` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `inquiry_status`
--

CREATE TABLE `inquiry_status` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(10) NOT NULL,
  `trx_trans_date_time` varchar(50) NOT NULL,
  `trx_inst_id` varchar(50) NOT NULL,
  `trx_country_code` varchar(50) NOT NULL,
  `trx_local_date_time` varchar(50) NOT NULL,
  `query_stan` varchar(50) NOT NULL,
  `query_trans_date_time` varchar(50) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `inquiry_status_resp`
--

CREATE TABLE `inquiry_status_resp` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(50) NOT NULL,
  `trx_trans_date_time` varchar(50) NOT NULL,
  `trx_inst_id` varchar(50) NOT NULL,
  `query_stan` varchar(50) NOT NULL,
  `query_trans_date_time` varchar(50) NOT NULL,
  `sender_trans_date_time` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_curr_code` varchar(50) NOT NULL,
  `sender_amount` varchar(50) NOT NULL,
  `sender_rate` varchar(50) NOT NULL,
  `benef_inst_id` varchar(50) NOT NULL,
  `benef_account_id` varchar(50) NOT NULL,
  `benef_curr_code` varchar(50) NOT NULL,
  `benef_amount` varchar(50) NOT NULL,
  `benef_cust_ref_number` varchar(50) NOT NULL,
  `benef_name` varchar(50) NOT NULL,
  `benef_regency_code` varchar(50) NOT NULL,
  `trx_code` varchar(50) NOT NULL,
  `trx_description` varchar(50) NOT NULL,
  `inq_code` varchar(50) NOT NULL,
  `inq_description` varchar(50) NOT NULL,
  `data` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `stan_gen`
--

CREATE TABLE `stan_gen` (
  `id` varchar(100) NOT NULL,
  `stan_generator` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `create_remmitance_account`
--
ALTER TABLE `create_remmitance_account`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `create_remmitance_account_resp`
--
ALTER TABLE `create_remmitance_account_resp`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `create_remmitance_transfer`
--
ALTER TABLE `create_remmitance_transfer`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `create_remmitance_transfer_resp`
--
ALTER TABLE `create_remmitance_transfer_resp`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `create_transfer_inquiry`
--
ALTER TABLE `create_transfer_inquiry`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `create_transfer_inquiry_resp`
--
ALTER TABLE `create_transfer_inquiry_resp`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `inquiry_status`
--
ALTER TABLE `inquiry_status`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `inquiry_status_resp`
--
ALTER TABLE `inquiry_status_resp`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `stan_gen`
--
ALTER TABLE `stan_gen`
  ADD PRIMARY KEY (`stan_generator`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stan_gen`
--
ALTER TABLE `stan_gen`
  MODIFY `stan_generator` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
