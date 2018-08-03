-- phpMyAdmin SQL Dump
-- version 4.0.10.10
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 03, 2018 at 08:17 AM
-- Server version: 5.1.72-community
-- PHP Version: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `appusers`
--

-- --------------------------------------------------------

--
-- Table structure for table `create_remmitance_account`
--

CREATE TABLE IF NOT EXISTS `create_remmitance_account` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(10) NOT NULL,
  `trans_date_time` varchar(25) DEFAULT NULL,
  `inst_id` varchar(10) DEFAULT NULL,
  `account_id` varchar(25) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birth_date` varchar(20) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `citizenship` varchar(100) DEFAULT NULL,
  `id_number` varchar(50) DEFAULT NULL,
  `fund_resource` varchar(100) DEFAULT NULL,
  `regency_code` varchar(100) DEFAULT NULL,
  `signature` varchar(1000) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `create_remmitance_account_resp`
--

CREATE TABLE IF NOT EXISTS `create_remmitance_account_resp` (
  `id` varchar(50) NOT NULL,
  `trxstan` varchar(20) NOT NULL,
  `trx_trans_date_time` varchar(30) DEFAULT NULL,
  `trx_inst_id` varchar(30) DEFAULT NULL,
  `sender_account_id` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_address` varchar(100) DEFAULT NULL,
  `sender_birth_date` varchar(50) DEFAULT NULL,
  `sender_phone_number` varchar(50) DEFAULT NULL,
  `sender_occupation` varchar(100) DEFAULT NULL,
  `sender_citizenship` varchar(50) DEFAULT NULL,
  `sender_id_number` varchar(50) DEFAULT NULL,
  `sender_fund_resource` varchar(100) DEFAULT NULL,
  `benef_inst_id` varchar(100) DEFAULT NULL,
  `benefAccount_id` varchar(100) DEFAULT NULL,
  `benef_name` varchar(100) DEFAULT NULL,
  `benef_relationship` varchar(100) DEFAULT NULL,
  `benef_regency_code` varchar(100) DEFAULT NULL,
  `err_code` varchar(50) DEFAULT NULL,
  `err_description` varchar(100) DEFAULT NULL,
  `data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `create_remmitance_transfer`
--

CREATE TABLE IF NOT EXISTS `create_remmitance_transfer` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(6) NOT NULL,
  `trx_trans_date_time` varchar(50) DEFAULT NULL,
  `trx_inst_id` varchar(50) DEFAULT NULL,
  `trx_proc_code` varchar(50) DEFAULT NULL,
  `trx_channel_type` varchar(50) DEFAULT NULL,
  `trx_ref_number` varchar(50) DEFAULT NULL,
  `trx_terminal_id` varchar(50) DEFAULT NULL,
  `trx_country_code` varchar(50) DEFAULT NULL,
  `trx_local_date_time` varchar(50) DEFAULT NULL,
  `sender_account_id` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_curr_code` varchar(50) DEFAULT NULL,
  `sender_amount` varchar(50) DEFAULT NULL,
  `sender_rate` varchar(50) DEFAULT NULL,
  `sender_area_code` varchar(50) DEFAULT NULL,
  `benef_inst_id` varchar(50) DEFAULT NULL,
  `benef_account_id` varchar(50) DEFAULT NULL,
  `benef_curr_code` varchar(50) DEFAULT NULL,
  `benef_amount` varchar(50) DEFAULT NULL,
  `benef_cust_ref_number` varchar(50) DEFAULT NULL,
  `benef_name` varchar(50) DEFAULT NULL,
  `benef_regency_code` varchar(50) DEFAULT NULL,
  `benef_purpose_code` varchar(50) DEFAULT NULL,
  `benef_purpose_description` varchar(50) DEFAULT NULL,
  `data` varchar(1000) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `create_remmitance_transfer_resp`
--

CREATE TABLE IF NOT EXISTS `create_remmitance_transfer_resp` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(10) NOT NULL,
  `trx_trans_date_time` varchar(50) DEFAULT NULL,
  `trx_inst_id` varchar(50) DEFAULT NULL,
  `sender_account_id` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_curr_code` varchar(50) DEFAULT NULL,
  `sender_amount` varchar(50) DEFAULT NULL,
  `sender_rate` varchar(50) DEFAULT NULL,
  `sender_area_code` varchar(50) DEFAULT NULL,
  `benef_inst_id` varchar(50) DEFAULT NULL,
  `benef_account_id` varchar(50) DEFAULT NULL,
  `benef_curr_code` varchar(50) DEFAULT NULL,
  `benef_amount` varchar(50) DEFAULT NULL,
  `benef_cust_ref_number` varchar(50) DEFAULT NULL,
  `benef_name` varchar(50) DEFAULT NULL,
  `benef_regency_code` varchar(50) DEFAULT NULL,
  `benef_purpose_code` varchar(50) DEFAULT NULL,
  `benef_purpose_desc` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `Data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `create_transfer_inquiry`
--

CREATE TABLE IF NOT EXISTS `create_transfer_inquiry` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(6) NOT NULL,
  `trans_date_time` varchar(30) DEFAULT NULL,
  `inst_id` varchar(30) DEFAULT NULL,
  `proc_code` varchar(30) DEFAULT NULL,
  `channel_type` varchar(50) DEFAULT NULL,
  `ref_number` varchar(50) DEFAULT NULL,
  `terminal_id` varchar(50) DEFAULT NULL,
  `country_code` varchar(50) DEFAULT NULL,
  `local_date_time` varchar(50) DEFAULT NULL,
  `sender_account_id` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `curr_code` varchar(50) DEFAULT NULL,
  `sender_amount` varchar(50) DEFAULT NULL,
  `sender_rate` varchar(50) DEFAULT NULL,
  `area_code` varchar(50) DEFAULT NULL,
  `benef_inst_id` varchar(50) DEFAULT NULL,
  `benef_account_id` varchar(50) DEFAULT NULL,
  `benef_curr_code` varchar(50) DEFAULT NULL,
  `benef_amount` varchar(50) DEFAULT NULL,
  `benef_cust_ref_number` varchar(50) DEFAULT NULL,
  `benef_regency_code` varchar(50) DEFAULT NULL,
  `benef_purpose_code` varchar(50) DEFAULT NULL,
  `benef_purpose_desc` varchar(50) DEFAULT NULL,
  `data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `create_transfer_inquiry_resp`
--

CREATE TABLE IF NOT EXISTS `create_transfer_inquiry_resp` (
  `id` varchar(50) NOT NULL,
  `stan` varchar(10) NOT NULL,
  `trans_date_time` varchar(50) DEFAULT NULL,
  `inst_id` varchar(50) DEFAULT NULL,
  `sender_account_id` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_curr_code` varchar(50) DEFAULT NULL,
  `sender_amount` varchar(20) DEFAULT NULL,
  `sender_rate` varchar(50) DEFAULT NULL,
  `sender_area_code` varchar(50) DEFAULT NULL,
  `benef_inst_id` varchar(50) DEFAULT NULL,
  `benef_account_id` varchar(50) DEFAULT NULL,
  `benef_curr_code` varchar(50) DEFAULT NULL,
  `benef_amount` varchar(50) DEFAULT NULL,
  `benef_cust_ref_number` varchar(50) DEFAULT NULL,
  `benef_name` varchar(50) DEFAULT NULL,
  `benef_regency_code` varchar(50) DEFAULT NULL,
  `benef_purpose_code` varchar(50) DEFAULT NULL,
  `benef_purpose_desc` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `inquiry_status`
--

CREATE TABLE IF NOT EXISTS `inquiry_status` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(10) NOT NULL,
  `trx_trans_date_time` varchar(50) DEFAULT NULL,
  `trx_inst_id` varchar(50) DEFAULT NULL,
  `trx_country_code` varchar(50) DEFAULT NULL,
  `trx_local_date_time` varchar(50) DEFAULT NULL,
  `query_stan` varchar(50) DEFAULT NULL,
  `query_trans_date_time` varchar(50) DEFAULT NULL,
  `data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `inquiry_status_resp`
--

CREATE TABLE IF NOT EXISTS `inquiry_status_resp` (
  `id` varchar(50) NOT NULL,
  `trx_stan` varchar(50) NOT NULL,
  `trx_trans_date_time` varchar(50) DEFAULT NULL,
  `trx_inst_id` varchar(50) DEFAULT NULL,
  `query_stan` varchar(50) DEFAULT NULL,
  `query_trans_date_time` varchar(50) DEFAULT NULL,
  `sender_trans_date_time` varchar(50) DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_curr_code` varchar(50) DEFAULT NULL,
  `sender_amount` varchar(50) DEFAULT NULL,
  `sender_rate` varchar(50) DEFAULT NULL,
  `benef_inst_id` varchar(50) DEFAULT NULL,
  `benef_account_id` varchar(50) DEFAULT NULL,
  `benef_curr_code` varchar(50) DEFAULT NULL,
  `benef_amount` varchar(50) DEFAULT NULL,
  `benef_cust_ref_number` varchar(50) DEFAULT NULL,
  `benef_name` varchar(50) DEFAULT NULL,
  `benef_regency_code` varchar(50) DEFAULT NULL,
  `trx_code` varchar(50) DEFAULT NULL,
  `trx_description` varchar(50) DEFAULT NULL,
  `inq_code` varchar(50) DEFAULT NULL,
  `inq_description` varchar(50) DEFAULT NULL,
  `data` varchar(300) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stan_gen`
--

CREATE TABLE IF NOT EXISTS `stan_gen` (
  `id` varchar(100) NOT NULL,
  `stan_generator` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`stan_generator`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=76 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
