/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : uemgp_db

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2015-11-11 23:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `account_condMov` varchar(255) DEFAULT NULL,
  `account_Iban` varchar(255) DEFAULT NULL,
  `account_nib` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_state` varchar(255) DEFAULT NULL,
  `account_styling` varchar(255) DEFAULT NULL,
  `date_opening` datetime DEFAULT NULL,
  `date_waxing` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_type` bigint(20) DEFAULT NULL,
  `bank` bigint(20) DEFAULT NULL,
  `coin` bigint(20) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  `purpose` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qq8k1y7bmaxyu6kbh1var7xl9` (`account_type`),
  KEY `FK_lvabnynk84lny4h41uhl6sf51` (`bank`),
  KEY `FK_l4kopa7hxf04w019dsm3bubw1` (`coin`),
  KEY `FK_60m31u6as6lgdbg7n7ssy06os` (`counter`),
  KEY `FK_iu5w0wukjnlcibxguhr3y7y5m` (`organ`),
  KEY `FK_qb8m7iv5o4d57vlig46mtj57g` (`purpose`),
  CONSTRAINT `FK_60m31u6as6lgdbg7n7ssy06os` FOREIGN KEY (`counter`) REFERENCES `counters` (`id`),
  CONSTRAINT `FK_iu5w0wukjnlcibxguhr3y7y5m` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_l4kopa7hxf04w019dsm3bubw1` FOREIGN KEY (`coin`) REFERENCES `coins` (`id`),
  CONSTRAINT `FK_lvabnynk84lny4h41uhl6sf51` FOREIGN KEY (`bank`) REFERENCES `banks` (`id`),
  CONSTRAINT `FK_qb8m7iv5o4d57vlig46mtj57g` FOREIGN KEY (`purpose`) REFERENCES `purposes` (`id`),
  CONSTRAINT `FK_qq8k1y7bmaxyu6kbh1var7xl9` FOREIGN KEY (`account_type`) REFERENCES `account_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accounts
-- ----------------------------

-- ----------------------------
-- Table structure for `account_sub_scriber`
-- ----------------------------
DROP TABLE IF EXISTS `account_sub_scriber`;
CREATE TABLE `account_sub_scriber` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_sub_scriber
-- ----------------------------

-- ----------------------------
-- Table structure for `account_sub_scribers`
-- ----------------------------
DROP TABLE IF EXISTS `account_sub_scribers`;
CREATE TABLE `account_sub_scribers` (
  `account_id` bigint(20) NOT NULL,
  `sub_scriber_id` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`,`sub_scriber_id`),
  KEY `FK_kn1d8n27c8twcn5m67ik2dhdu` (`sub_scriber_id`),
  CONSTRAINT `FK_kn1d8n27c8twcn5m67ik2dhdu` FOREIGN KEY (`sub_scriber_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK_qb2ydl22uj7kksj1i6bvkgo5g` FOREIGN KEY (`account_id`) REFERENCES `account_sub_scriber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_sub_scribers
-- ----------------------------

-- ----------------------------
-- Table structure for `account_types`
-- ----------------------------
DROP TABLE IF EXISTS `account_types`;
CREATE TABLE `account_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `account_type_desc` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_types
-- ----------------------------

-- ----------------------------
-- Table structure for `actions`
-- ----------------------------
DROP TABLE IF EXISTS `actions`;
CREATE TABLE `actions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beginDate` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `contentColor` varchar(255) DEFAULT NULL,
  `created` datetime NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `headerColor` varchar(255) DEFAULT NULL,
  `locked` bit(1) NOT NULL,
  `updated` datetime NOT NULL,
  `budget` float DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `criticalArea_id` bigint(20) DEFAULT NULL,
  `cycle_id` bigint(20) DEFAULT NULL,
  `organ_id` bigint(20) DEFAULT NULL,
  `plan` bigint(20) DEFAULT NULL,
  `priority_level_id` bigint(20) DEFAULT NULL,
  `strategic_objective_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pif79kt7iw9dww2tx9cjhylns` (`criticalArea_id`),
  KEY `FK_9t5wkj1g8d6qkqmsxufxot8cy` (`cycle_id`),
  KEY `FK_sneodv6dtgufr7n382i3ew4mu` (`organ_id`),
  KEY `FK_iul8vugbkia33wqgy0ttsl4kt` (`plan`),
  KEY `FK_337co17qvb8c9ctgq7dc4u6r3` (`priority_level_id`),
  KEY `FK_k8fwqe208rc77dwqhaokos6ra` (`strategic_objective_id`),
  CONSTRAINT `FK_337co17qvb8c9ctgq7dc4u6r3` FOREIGN KEY (`priority_level_id`) REFERENCES `priority_levels` (`id`),
  CONSTRAINT `FK_9t5wkj1g8d6qkqmsxufxot8cy` FOREIGN KEY (`cycle_id`) REFERENCES `cycles` (`id`),
  CONSTRAINT `FK_iul8vugbkia33wqgy0ttsl4kt` FOREIGN KEY (`plan`) REFERENCES `plan` (`id`),
  CONSTRAINT `FK_k8fwqe208rc77dwqhaokos6ra` FOREIGN KEY (`strategic_objective_id`) REFERENCES `strategic_objectives` (`id`),
  CONSTRAINT `FK_pif79kt7iw9dww2tx9cjhylns` FOREIGN KEY (`criticalArea_id`) REFERENCES `critical_areas` (`id`),
  CONSTRAINT `FK_sneodv6dtgufr7n382i3ew4mu` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of actions
-- ----------------------------
INSERT INTO `actions` VALUES ('1', '2015-11-08 02:00:00', 'Gestao Bancaria', '#6699ff', '2015-11-11 23:26:08', '2015-11-12 02:00:00', '#3366ff', '', '2015-11-11 23:26:08', '0', 'Gestao Bancaria', null, '1', '4', null, '1', null);
INSERT INTO `actions` VALUES ('2', '2015-11-24 02:00:00', 'Gestao Financeira', '#6699ff', '2015-11-11 23:26:44', '2015-11-29 02:00:00', '#3366ff', '', '2015-11-11 23:26:44', '0', 'Gestao Financeira', null, '2', '4', null, '2', null);
INSERT INTO `actions` VALUES ('3', '2016-02-07 02:00:00', 'Financas Publicas', '#6699ff', '2015-11-11 23:29:48', '2016-02-13 02:00:00', '#3366ff', '', '2015-11-11 23:29:48', '0', 'Financas Publicas', '1', '3', '4', null, '3', '2');
INSERT INTO `actions` VALUES ('4', '2015-11-15 02:00:00', 'Financas', '#6699ff', '2015-11-11 23:31:09', '2015-11-22 02:00:00', '#3366ff', '', '2015-11-11 23:31:09', '0', 'Financas', '2', '1', '1', null, '4', '2');

-- ----------------------------
-- Table structure for `allocation_employees`
-- ----------------------------
DROP TABLE IF EXISTS `allocation_employees`;
CREATE TABLE `allocation_employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `task` varchar(255) DEFAULT NULL,
  `allocationSector_id` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  `sub_sector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r81dshe5hu67t1h7i1pig09nd` (`allocationSector_id`),
  KEY `FK_p5ghs9swuarbm18lla7wbypwt` (`responsible_id`),
  KEY `FK_f15rehary4c0dihcibgjpr142` (`sub_sector_id`),
  CONSTRAINT `FK_f15rehary4c0dihcibgjpr142` FOREIGN KEY (`sub_sector_id`) REFERENCES `sub_sectors` (`id`),
  CONSTRAINT `FK_p5ghs9swuarbm18lla7wbypwt` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_r81dshe5hu67t1h7i1pig09nd` FOREIGN KEY (`allocationSector_id`) REFERENCES `allocation_sectors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_employees
-- ----------------------------

-- ----------------------------
-- Table structure for `allocation_organs`
-- ----------------------------
DROP TABLE IF EXISTS `allocation_organs`;
CREATE TABLE `allocation_organs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `seen` bit(1) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `task_also` varchar(255) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `organ_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `specified_action_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_epktfi0bfu468shiutwhox15t` (`comment_id`),
  KEY `FK_qqlh3e4u03gidlfd8q5yuu72y` (`organ_id`),
  KEY `FK_tfcolc9p3589xqmf7w8sh10ty` (`role_id`),
  KEY `FK_3828vk3wnp2spa2vn6092jctf` (`specified_action_id`),
  CONSTRAINT `FK_3828vk3wnp2spa2vn6092jctf` FOREIGN KEY (`specified_action_id`) REFERENCES `specified_actions` (`id`),
  CONSTRAINT `FK_epktfi0bfu468shiutwhox15t` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `FK_qqlh3e4u03gidlfd8q5yuu72y` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_tfcolc9p3589xqmf7w8sh10ty` FOREIGN KEY (`role_id`) REFERENCES `organ_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_organs
-- ----------------------------
INSERT INTO `allocation_organs` VALUES ('1', '2015-11-11 23:32:50', '2015-11-11 23:32:50', '2015-11-22 00:00:00', '', '2015-11-15 00:00:00', '', 'Registar Financas', null, '1', '1', '1');

-- ----------------------------
-- Table structure for `allocation_sectors`
-- ----------------------------
DROP TABLE IF EXISTS `allocation_sectors`;
CREATE TABLE `allocation_sectors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `task` varchar(255) DEFAULT NULL,
  `allocationOrgan_id` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  `sector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t2mptxwoc1llg4ibg8p275hwg` (`allocationOrgan_id`),
  KEY `FK_33ed9ig3t735ryer6tqvr91u7` (`responsible_id`),
  KEY `FK_i5tro8pm0j7d44y4oixgv3d5h` (`sector_id`),
  CONSTRAINT `FK_33ed9ig3t735ryer6tqvr91u7` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_i5tro8pm0j7d44y4oixgv3d5h` FOREIGN KEY (`sector_id`) REFERENCES `sectors` (`id`),
  CONSTRAINT `FK_t2mptxwoc1llg4ibg8p275hwg` FOREIGN KEY (`allocationOrgan_id`) REFERENCES `allocation_organs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_sectors
-- ----------------------------
INSERT INTO `allocation_sectors` VALUES ('1', '2015-11-11 23:33:14', '2015-11-11 23:33:14', '2015-11-19 00:00:00', '2015-11-11 00:00:00', '', 'Reconciliar', '1', '2', '1');

-- ----------------------------
-- Table structure for `attachment`
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `attachment_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `registration_data` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `attachment_type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3s244cbtor95psgeois0vvth5` (`attachment_type`),
  CONSTRAINT `FK_3s244cbtor95psgeois0vvth5` FOREIGN KEY (`attachment_type`) REFERENCES `attachment_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES ('1', '2015-09-14 18:11:27', '2015-09-14 18:11:27', null, 'CD34GR6KL', '2015-09-14 18:11:27', 'Plano Orcamental 2016', '1');
INSERT INTO `attachment` VALUES ('2', '2015-09-14 18:12:07', '2015-09-14 18:12:07', null, 'CD34GR6FG', '2015-09-14 18:12:07', 'Relatorio de Contas', '3');

-- ----------------------------
-- Table structure for `attachment_files`
-- ----------------------------
DROP TABLE IF EXISTS `attachment_files`;
CREATE TABLE `attachment_files` (
  `Attachment_id` bigint(20) NOT NULL,
  `files_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Attachment_id`,`files_id`),
  UNIQUE KEY `UK_k46cigse2y0xx5b2byntafrk4` (`files_id`),
  CONSTRAINT `FK_a8pj92ntqyw99h7tbb2bf2nrn` FOREIGN KEY (`Attachment_id`) REFERENCES `attachment` (`id`),
  CONSTRAINT `FK_k46cigse2y0xx5b2byntafrk4` FOREIGN KEY (`files_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment_files
-- ----------------------------
INSERT INTO `attachment_files` VALUES ('1', '1');
INSERT INTO `attachment_files` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `attachment_type`
-- ----------------------------
DROP TABLE IF EXISTS `attachment_type`;
CREATE TABLE `attachment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment_type
-- ----------------------------
INSERT INTO `attachment_type` VALUES ('1', '2015-09-14 18:10:00', '2015-09-14 18:10:00', '01', 'Memorandos ');
INSERT INTO `attachment_type` VALUES ('2', '2015-09-14 18:10:12', '2015-09-14 18:10:12', '02', 'Contractos');
INSERT INTO `attachment_type` VALUES ('3', '2015-09-14 18:10:19', '2015-09-14 18:10:19', '03', 'Relatorios');

-- ----------------------------
-- Table structure for `banks`
-- ----------------------------
DROP TABLE IF EXISTS `banks`;
CREATE TABLE `banks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `bank_description` varchar(255) NOT NULL,
  `bank_initials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banks
-- ----------------------------

-- ----------------------------
-- Table structure for `based_calculating`
-- ----------------------------
DROP TABLE IF EXISTS `based_calculating`;
CREATE TABLE `based_calculating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `cost` float DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `unit_cost` float DEFAULT NULL,
  `font_id` bigint(20) DEFAULT NULL,
  `specified_action_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3lwwkgcixxbkcv88hgck70apv` (`font_id`),
  KEY `FK_epdtvtrnhsh5u6ai2hl54pvdd` (`specified_action_id`),
  CONSTRAINT `FK_3lwwkgcixxbkcv88hgck70apv` FOREIGN KEY (`font_id`) REFERENCES `fonts` (`id`),
  CONSTRAINT `FK_epdtvtrnhsh5u6ai2hl54pvdd` FOREIGN KEY (`specified_action_id`) REFERENCES `specified_actions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of based_calculating
-- ----------------------------

-- ----------------------------
-- Table structure for `coins`
-- ----------------------------
DROP TABLE IF EXISTS `coins`;
CREATE TABLE `coins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `coin_desc` varchar(255) DEFAULT NULL,
  `coin_initials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coins
-- ----------------------------

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for `contact_points`
-- ----------------------------
DROP TABLE IF EXISTS `contact_points`;
CREATE TABLE `contact_points` (
  `cp_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `carrier` varchar(255) DEFAULT NULL,
  `prefixo` int(11) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g676m8gmw2iut4qvo4tgn1s7f` (`employee`),
  KEY `FK_52g2494f0fyqiq6d0dqbubljd` (`counter`),
  CONSTRAINT `FK_52g2494f0fyqiq6d0dqbubljd` FOREIGN KEY (`counter`) REFERENCES `counters` (`id`),
  CONSTRAINT `FK_g676m8gmw2iut4qvo4tgn1s7f` FOREIGN KEY (`employee`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_points
-- ----------------------------

-- ----------------------------
-- Table structure for `counters`
-- ----------------------------
DROP TABLE IF EXISTS `counters`;
CREATE TABLE `counters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `conter_address` varchar(255) DEFAULT NULL,
  `counter_nm` varchar(255) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m70pxj3k4u9phtttac0lr583k` (`bank_id`),
  CONSTRAINT `FK_m70pxj3k4u9phtttac0lr583k` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counters
-- ----------------------------

-- ----------------------------
-- Table structure for `criterias`
-- ----------------------------
DROP TABLE IF EXISTS `criterias`;
CREATE TABLE `criterias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of criterias
-- ----------------------------
INSERT INTO `criterias` VALUES ('1', '2015-11-11 23:27:29', '2015-11-11 23:27:29', 'Criterio 01', 'Criterio 01', 'Criterio1', '23');
INSERT INTO `criterias` VALUES ('2', '2015-11-11 23:27:39', '2015-11-11 23:27:39', 'Criterio 01', 'Criterio 02', 'Criterio2', '43');

-- ----------------------------
-- Table structure for `critical_areas`
-- ----------------------------
DROP TABLE IF EXISTS `critical_areas`;
CREATE TABLE `critical_areas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of critical_areas
-- ----------------------------
INSERT INTO `critical_areas` VALUES ('1', '2015-11-11 23:28:23', '2015-11-11 23:28:23', '01', 'Turismo');
INSERT INTO `critical_areas` VALUES ('2', '2015-11-11 23:28:34', '2015-11-11 23:28:34', '01', 'Educacao');

-- ----------------------------
-- Table structure for `cycles`
-- ----------------------------
DROP TABLE IF EXISTS `cycles`;
CREATE TABLE `cycles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cycles
-- ----------------------------
INSERT INTO `cycles` VALUES ('1', '2015-09-14 18:37:45', '2015-09-28 16:24:53', '2015', 'Gestao e Orcamento UEM', '2015-12-20 18:37:06', '2015-09-14 18:37:04', 'Started', 'Gestao e Orcamento UEM', 'Normal');
INSERT INTO `cycles` VALUES ('2', '2015-09-28 15:55:23', '2015-11-11 22:52:47', '2015', 'Gestao e Orcamento 2015-2016', '2016-07-08 15:54:52', '2015-09-28 15:54:49', 'Started', 'Gestao e Orcamento 2015-2016', 'Normal');
INSERT INTO `cycles` VALUES ('3', '2015-09-28 16:22:38', '2015-11-11 22:54:29', '2015', 'Plano Geral de Actividades', '2015-12-30 16:22:17', '2015-09-28 16:22:13', 'Started', 'Plano Geral de Actividades', 'Normal');

-- ----------------------------
-- Table structure for `cycles_files`
-- ----------------------------
DROP TABLE IF EXISTS `cycles_files`;
CREATE TABLE `cycles_files` (
  `cycles_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cycles_id`,`attachments_id`),
  UNIQUE KEY `UK_o17w9efsjqn9x8qb0gxj3cwj5` (`attachments_id`),
  CONSTRAINT `FK_9nhyec6k5tfbapcgyy4cyd5l8` FOREIGN KEY (`cycles_id`) REFERENCES `cycles` (`id`),
  CONSTRAINT `FK_o17w9efsjqn9x8qb0gxj3cwj5` FOREIGN KEY (`attachments_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cycles_files
-- ----------------------------
INSERT INTO `cycles_files` VALUES ('1', '3');

-- ----------------------------
-- Table structure for `cycle_organ`
-- ----------------------------
DROP TABLE IF EXISTS `cycle_organ`;
CREATE TABLE `cycle_organ` (
  `cycle_id` bigint(20) NOT NULL,
  `organ_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cycle_id`,`organ_id`),
  KEY `FK_io6f2n9tn0wsthluh99451w0p` (`organ_id`),
  CONSTRAINT `FK_3iqlcata9l1k6ff2e3im1ad8p` FOREIGN KEY (`cycle_id`) REFERENCES `cycles` (`id`),
  CONSTRAINT `FK_io6f2n9tn0wsthluh99451w0p` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cycle_organ
-- ----------------------------
INSERT INTO `cycle_organ` VALUES ('1', '1');
INSERT INTO `cycle_organ` VALUES ('2', '1');
INSERT INTO `cycle_organ` VALUES ('3', '1');
INSERT INTO `cycle_organ` VALUES ('1', '2');
INSERT INTO `cycle_organ` VALUES ('2', '2');
INSERT INTO `cycle_organ` VALUES ('3', '2');
INSERT INTO `cycle_organ` VALUES ('1', '3');
INSERT INTO `cycle_organ` VALUES ('2', '3');
INSERT INTO `cycle_organ` VALUES ('3', '3');
INSERT INTO `cycle_organ` VALUES ('1', '4');
INSERT INTO `cycle_organ` VALUES ('2', '4');
INSERT INTO `cycle_organ` VALUES ('3', '4');

-- ----------------------------
-- Table structure for `delegations`
-- ----------------------------
DROP TABLE IF EXISTS `delegations`;
CREATE TABLE `delegations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m3cauklgpiqh41wvqxktdqx65` (`employee_id`),
  KEY `FK_3nsajedmtirasvovrapjgsxiv` (`responsible_id`),
  CONSTRAINT `FK_3nsajedmtirasvovrapjgsxiv` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_m3cauklgpiqh41wvqxktdqx65` FOREIGN KEY (`employee_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delegations
-- ----------------------------

-- ----------------------------
-- Table structure for `delegations_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `delegations_permissions`;
CREATE TABLE `delegations_permissions` (
  `delegations_id` bigint(20) NOT NULL,
  `permissions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`delegations_id`,`permissions_id`),
  UNIQUE KEY `UK_agxl4nlk6ekw40h2hvjipcdbn` (`permissions_id`),
  CONSTRAINT `FK_agxl4nlk6ekw40h2hvjipcdbn` FOREIGN KEY (`permissions_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `FK_gee83ukjvvjna2u8kwgv0cxsu` FOREIGN KEY (`delegations_id`) REFERENCES `delegations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delegations_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for `embarrassments`
-- ----------------------------
DROP TABLE IF EXISTS `embarrassments`;
CREATE TABLE `embarrassments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of embarrassments
-- ----------------------------

-- ----------------------------
-- Table structure for `employee_executions`
-- ----------------------------
DROP TABLE IF EXISTS `employee_executions`;
CREATE TABLE `employee_executions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `task_also` varchar(255) DEFAULT NULL,
  `allocationOrgan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hay6xh8qlk5n9mpqoa2o8oxct` (`allocationOrgan_id`),
  CONSTRAINT `FK_hay6xh8qlk5n9mpqoa2o8oxct` FOREIGN KEY (`allocationOrgan_id`) REFERENCES `allocation_employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_executions
-- ----------------------------

-- ----------------------------
-- Table structure for `employee_executions_files`
-- ----------------------------
DROP TABLE IF EXISTS `employee_executions_files`;
CREATE TABLE `employee_executions_files` (
  `employee_executions_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_executions_id`,`attachments_id`),
  UNIQUE KEY `UK_byct7y0p44157n963e5s76u18` (`attachments_id`),
  CONSTRAINT `FK_a7puqiegrp3v7vwn93s5fboqj` FOREIGN KEY (`employee_executions_id`) REFERENCES `employee_executions` (`id`),
  CONSTRAINT `FK_byct7y0p44157n963e5s76u18` FOREIGN KEY (`attachments_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_executions_files
-- ----------------------------

-- ----------------------------
-- Table structure for `errors`
-- ----------------------------
DROP TABLE IF EXISTS `errors`;
CREATE TABLE `errors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` longtext,
  `status` bit(1) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of errors
-- ----------------------------

-- ----------------------------
-- Table structure for `files`
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `FROM_CLASS` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `content` longblob,
  `description` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES ('File', '1', '2015-09-14 18:11:27', '2015-09-14 18:11:27', '', null, 'Relatorio Financeiro.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO `files` VALUES ('File', '2', '2015-09-14 18:12:07', '2015-09-14 18:12:07', '', null, 'Relatorio de Contas.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO `files` VALUES ('File', '3', '2015-09-14 18:37:45', '2015-09-14 18:37:45', '', null, 'Relatorio de Contas.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');

-- ----------------------------
-- Table structure for `fonts`
-- ----------------------------
DROP TABLE IF EXISTS `fonts`;
CREATE TABLE `fonts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fonts
-- ----------------------------

-- ----------------------------
-- Table structure for `helps`
-- ----------------------------
DROP TABLE IF EXISTS `helps`;
CREATE TABLE `helps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `what` varchar(255) DEFAULT NULL,
  `attach_id` bigint(20) DEFAULT NULL,
  `to_id` bigint(20) NOT NULL,
  `from_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tlpk3292hhqopf2k6pcx18o2n` (`attach_id`),
  KEY `FK_qels640um438k7r2en86t44gy` (`to_id`),
  KEY `FK_6u1r545k4ei0y791gy59jv97f` (`from_id`),
  CONSTRAINT `FK_6u1r545k4ei0y791gy59jv97f` FOREIGN KEY (`from_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_qels640um438k7r2en86t44gy` FOREIGN KEY (`to_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_tlpk3292hhqopf2k6pcx18o2n` FOREIGN KEY (`attach_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of helps
-- ----------------------------
INSERT INTO `helps` VALUES ('1', '2015-09-14 23:41:14', '2015-09-14 23:41:14', 'Nao Consigo Registar um Documento?', null, '1', '2');
INSERT INTO `helps` VALUES ('2', '2015-09-28 15:21:42', '2015-09-28 15:21:42', 'Como Configurar um Ciclo', '4', '1', '2');
INSERT INTO `helps` VALUES ('3', '2015-09-28 16:06:17', '2015-09-28 16:06:17', 'fsadfsadfsdaf', null, '1', '2');

-- ----------------------------
-- Table structure for `help_comments`
-- ----------------------------
DROP TABLE IF EXISTS `help_comments`;
CREATE TABLE `help_comments` (
  `help_id` bigint(20) NOT NULL,
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`help_id`,`comment_id`),
  KEY `FK_1vl3f9y8ropstfun0cegx9rpy` (`comment_id`),
  CONSTRAINT `FK_1vl3f9y8ropstfun0cegx9rpy` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `FK_ekcpkirs9qyrhvsosg2n2vpgm` FOREIGN KEY (`help_id`) REFERENCES `helps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of help_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `identity_document_types`
-- ----------------------------
DROP TABLE IF EXISTS `identity_document_types`;
CREATE TABLE `identity_document_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `names` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of identity_document_types
-- ----------------------------

-- ----------------------------
-- Table structure for `individuals`
-- ----------------------------
DROP TABLE IF EXISTS `individuals`;
CREATE TABLE `individuals` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `academic_level` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `number_children` int(11) DEFAULT NULL,
  `employee_code` varchar(255) DEFAULT NULL,
  `nuit_number` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  `profession` bigint(20) DEFAULT NULL,
  `sector` bigint(20) DEFAULT NULL,
  `subScriber` bigint(20) DEFAULT NULL,
  `sub_sector` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `cellPhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tj1rrwylr05k2q88n04ogkfpr` (`employee_code`),
  KEY `FK_ca4t2574r7hfjkbbf75siyokj` (`type_id`),
  KEY `FK_8q99frrr73nqsxeqr3n2o3gfu` (`image_id`),
  KEY `FK_nhtt6d8oehr10bbvvhc3c6k14` (`organ`),
  KEY `FK_nubkisqoxscymb9efnoiyamcx` (`profession`),
  KEY `FK_lmpqv190pyxnqmfd628yki76r` (`sector`),
  KEY `FK_idfeir92oyftahx4jkbkm0nes` (`subScriber`),
  KEY `FK_7uiaa2dbu3f53j11nx96k0ol5` (`sub_sector`),
  KEY `FK_gkywvgfb98ve4vvyja58dl6ps` (`user_id`),
  CONSTRAINT `FK_7uiaa2dbu3f53j11nx96k0ol5` FOREIGN KEY (`sub_sector`) REFERENCES `sub_sectors` (`id`),
  CONSTRAINT `FK_8q99frrr73nqsxeqr3n2o3gfu` FOREIGN KEY (`image_id`) REFERENCES `files` (`id`),
  CONSTRAINT `FK_ca4t2574r7hfjkbbf75siyokj` FOREIGN KEY (`type_id`) REFERENCES `identity_document_types` (`id`),
  CONSTRAINT `FK_gkywvgfb98ve4vvyja58dl6ps` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_idfeir92oyftahx4jkbkm0nes` FOREIGN KEY (`subScriber`) REFERENCES `subscribers` (`id`),
  CONSTRAINT `FK_lmpqv190pyxnqmfd628yki76r` FOREIGN KEY (`sector`) REFERENCES `sectors` (`id`),
  CONSTRAINT `FK_nhtt6d8oehr10bbvvhc3c6k14` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_nubkisqoxscymb9efnoiyamcx` FOREIGN KEY (`profession`) REFERENCES `profession` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of individuals
-- ----------------------------
INSERT INTO `individuals` VALUES ('Employee', '1', '2015-09-14 18:04:40', '2015-09-14 18:04:40', 'High Level', 'CIUEM', null, 'Male', null, 'Admin', null, null, 'Admin', null, '0', '2d6b7236-6a5a-4d3d-98e3-6fd43051d058', null, null, null, null, '1', null, null, null, '1', '0058840665903');
INSERT INTO `individuals` VALUES ('Employee', '2', '2015-09-14 18:32:44', '2015-09-14 23:48:32', 'EP2', 'Matola, Bairro da Liberdade', '1991-06-18', 'Masculino', null, 'Maposse', 'Solteiro', null, 'Eusebio', null, '0', 'bbdeb66e-4bad-4f82-82ed-9f7a88b8637f', null, null, null, '1', '1', '1', null, '3', '2', '0058840665903');
INSERT INTO `individuals` VALUES ('Employee', '3', '2015-09-28 15:57:09', '2015-09-28 16:01:39', 'Superior', 'Matola', '1994-12-01', 'Masculino', null, 'Mulungo', 'Solteiro', null, 'Jorge Jose', null, '0', '0a378a08-bcd7-489a-a7ac-fa4b42e35e9a', null, null, null, '2', '1', '2', null, null, '3', '0058840665903');
INSERT INTO `individuals` VALUES ('Employee', '4', '2015-09-28 15:57:55', '2015-09-28 16:02:48', 'Superior', 'Maputo', '1994-07-08', null, null, 'Jaguar', 'Casado', null, 'Lucas Matavele', null, '0', '4af7dcbe-9450-4eb6-9f1a-c4d22acf16d6', null, null, null, '3', '1', '5', null, null, '5', '0058840665903');
INSERT INTO `individuals` VALUES ('Employee', '5', '2015-09-28 16:03:53', '2015-09-28 16:04:26', 'ETM', 'Maputo', '2015-09-09', 'Masculino', null, 'Jossias', 'Solteiro', null, 'Helton ', null, '0', '70d7c236-f349-4034-95ee-4da9715d9d5e', null, null, null, '4', '1', '7', null, null, '6', '0058840665903');
INSERT INTO `individuals` VALUES ('Employee', '6', '2015-11-11 23:13:38', '2015-11-11 23:23:51', 'EP2', 'Matola', '2015-11-22', null, null, 'Sitoe', 'Solteiro', null, 'Marcos Moiseis', null, '0', 'a07241fc-8380-4c63-ac13-400dd9c5af6a', null, null, null, '1', '1', '1', null, '3', '8', null);
INSERT INTO `individuals` VALUES ('Employee', '7', '2015-11-11 23:22:51', '2015-11-11 23:23:23', 'ETB', 'Matola', '2015-11-13', 'Masculino', null, 'Maposse', 'Solteiro', null, 'Igor', null, '0', '711ca960-ff1a-4725-8dc9-118b4bcd3154', null, null, null, '4', '1', '7', null, null, '7', '0058849570336');

-- ----------------------------
-- Table structure for `individuals_contact_points`
-- ----------------------------
DROP TABLE IF EXISTS `individuals_contact_points`;
CREATE TABLE `individuals_contact_points` (
  `individuals_id` bigint(20) NOT NULL,
  `contactPoints_id` bigint(20) NOT NULL,
  PRIMARY KEY (`individuals_id`,`contactPoints_id`),
  UNIQUE KEY `UK_abaqiiy0xnsqocuexxj595f8v` (`contactPoints_id`),
  CONSTRAINT `FK_abaqiiy0xnsqocuexxj595f8v` FOREIGN KEY (`contactPoints_id`) REFERENCES `contact_points` (`id`),
  CONSTRAINT `FK_tw6cp5ga4v06n9as3judr2qr` FOREIGN KEY (`individuals_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of individuals_contact_points
-- ----------------------------

-- ----------------------------
-- Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logged_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qpnb22i0s62295yx0oqkqjl0i` (`logged_user_id`),
  CONSTRAINT `FK_qpnb22i0s62295yx0oqkqjl0i` FOREIGN KEY (`logged_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('1', '2015-09-14 18:05:23', '2015-09-14 18:05:23', 'Registou novo Tipo de Orgão: Centros e Museus', '1');
INSERT INTO `logs` VALUES ('2', '2015-09-14 18:05:36', '2015-09-14 18:05:36', 'Registou novo Tipo de Orgão: Faculdades e Escolas', '1');
INSERT INTO `logs` VALUES ('3', '2015-09-14 18:05:45', '2015-09-14 18:05:45', 'Registou novo Tipo de Orgão: Administrativos', '1');
INSERT INTO `logs` VALUES ('4', '2015-09-14 18:06:17', '2015-09-14 18:06:17', 'Registou novo Orgão: Centro de Informatica da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('5', '2015-09-14 18:06:29', '2015-09-14 18:06:29', 'Registou novo Orgão: Faculdade de Ciencias', '1');
INSERT INTO `logs` VALUES ('6', '2015-09-14 18:06:40', '2015-09-14 18:06:40', 'Registou novo Orgão: Faculdade de Lentras', '1');
INSERT INTO `logs` VALUES ('7', '2015-09-14 18:06:52', '2015-09-14 18:06:52', 'Registou novo Orgão: Gabinete de Planificacao', '1');
INSERT INTO `logs` VALUES ('8', '2015-09-14 18:07:14', '2015-09-14 18:07:14', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('9', '2015-09-14 18:07:38', '2015-09-14 18:07:38', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('10', '2015-09-14 18:07:59', '2015-09-14 18:07:59', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('11', '2015-09-14 18:08:10', '2015-09-14 18:08:10', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('12', '2015-09-14 18:08:35', '2015-09-14 18:08:35', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('13', '2015-09-14 18:09:00', '2015-09-14 18:09:00', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('14', '2015-09-14 18:09:12', '2015-09-14 18:09:12', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('15', '2015-09-14 18:09:26', '2015-09-14 18:09:26', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('16', '2015-09-14 18:09:42', '2015-09-14 18:09:42', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('17', '2015-09-14 18:10:00', '2015-09-14 18:10:00', 'Reportou novo Tipo de Documento : Memorandos ', '1');
INSERT INTO `logs` VALUES ('18', '2015-09-14 18:10:12', '2015-09-14 18:10:12', 'Reportou novo Tipo de Documento : Contractos', '1');
INSERT INTO `logs` VALUES ('19', '2015-09-14 18:10:19', '2015-09-14 18:10:19', 'Reportou novo Tipo de Documento : Relatorios', '1');
INSERT INTO `logs` VALUES ('20', '2015-09-14 18:11:27', '2015-09-14 18:11:27', 'Reportou novodocumento : CD34GR6KL', '1');
INSERT INTO `logs` VALUES ('21', '2015-09-14 18:12:07', '2015-09-14 18:12:07', 'Reportou novodocumento : CD34GR6FG', '1');
INSERT INTO `logs` VALUES ('22', '2015-09-14 18:32:44', '2015-09-14 18:32:44', 'Registou novo funcionário: Eusebio Maposse', '1');
INSERT INTO `logs` VALUES ('23', '2015-09-14 18:33:23', '2015-09-14 18:33:23', 'Registou novo Perfil de Utilizadores: Reitoria', '1');
INSERT INTO `logs` VALUES ('24', '2015-09-14 18:33:57', '2015-09-14 18:33:57', 'Registou novo Utilizador: eusebiomaposse@gmail.com', '1');
INSERT INTO `logs` VALUES ('25', '2015-09-14 18:36:00', '2015-09-14 18:36:00', 'Adicionou: Gestao de Dados Academicos', '2');
INSERT INTO `logs` VALUES ('26', '2015-09-14 18:37:45', '2015-09-14 18:37:45', 'Registou um novo Ciclo: 2015 - Gestao e Orcamento UEM', '1');
INSERT INTO `logs` VALUES ('27', '2015-09-14 22:13:36', '2015-09-14 22:13:36', 'Adicionou: Gestao Financeira', '1');
INSERT INTO `logs` VALUES ('28', '2015-09-14 23:39:04', '2015-09-14 23:39:04', 'Adicionou: gestao financeira', '1');
INSERT INTO `logs` VALUES ('29', '2015-09-14 23:41:15', '2015-09-14 23:41:15', 'Solicitou Ajuda: Nao Consigo Registar um Documento?', '2');
INSERT INTO `logs` VALUES ('30', '2015-09-14 23:48:33', '2015-09-14 23:48:33', 'Actualizou o FuncionarioEusebio', '1');
INSERT INTO `logs` VALUES ('31', '2015-09-28 15:21:43', '2015-09-28 15:21:43', 'Solicitou Ajuda: Como Configurar um Ciclo', '2');
INSERT INTO `logs` VALUES ('32', '2015-09-28 15:55:23', '2015-09-28 15:55:23', 'Registou um novo Ciclo: 2015 - Gestao e Orcamento 2015-2016', '1');
INSERT INTO `logs` VALUES ('33', '2015-09-28 15:55:56', '2015-09-28 15:55:56', 'Atribuiu novo responsável (Eusebio Maposse) ao Orgão: Centro de Informatica da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('34', '2015-09-28 15:57:10', '2015-09-28 15:57:10', 'Registou novo funcionário: Jorge Jose Mulungo', '1');
INSERT INTO `logs` VALUES ('35', '2015-09-28 15:57:55', '2015-09-28 15:57:55', 'Registou novo funcionário: Lucas Matavele Jaguar', '1');
INSERT INTO `logs` VALUES ('36', '2015-09-28 15:58:22', '2015-09-28 15:58:22', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('37', '2015-09-28 15:58:45', '2015-09-28 15:58:45', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('38', '2015-09-28 15:59:13', '2015-09-28 15:59:13', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('39', '2015-09-28 15:59:46', '2015-09-28 15:59:46', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('40', '2015-09-28 16:00:01', '2015-09-28 16:00:01', 'Actualizou o FuncionarioJorge Jose', '1');
INSERT INTO `logs` VALUES ('41', '2015-09-28 16:00:34', '2015-09-28 16:00:34', 'Actualizou o FuncionarioLucas Matavele', '1');
INSERT INTO `logs` VALUES ('42', '2015-09-28 16:00:53', '2015-09-28 16:00:53', 'Actualizou o FuncionarioLucas Matavele', '1');
INSERT INTO `logs` VALUES ('43', '2015-09-28 16:01:39', '2015-09-28 16:01:39', 'Registou novo Utilizador: jj.mulungo@gmail.com', '1');
INSERT INTO `logs` VALUES ('44', '2015-09-28 16:02:48', '2015-09-28 16:02:48', 'Registou novo Utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('45', '2015-09-28 16:02:59', '2015-09-28 16:02:59', 'Atribuiu novo responsável (Jorge Jose Mulungo) ao Orgão: Faculdade de Ciencias', '1');
INSERT INTO `logs` VALUES ('46', '2015-09-28 16:03:05', '2015-09-28 16:03:05', 'Atribuiu novo responsável (Lucas Matavele Jaguar) ao Orgão: Faculdade de Lentras', '1');
INSERT INTO `logs` VALUES ('47', '2015-09-28 16:03:53', '2015-09-28 16:03:53', 'Registou novo funcionário: Helton  Jossias', '1');
INSERT INTO `logs` VALUES ('48', '2015-09-28 16:04:26', '2015-09-28 16:04:26', 'Registou novo Utilizador: heltonjossias@gmail.com', '1');
INSERT INTO `logs` VALUES ('49', '2015-09-28 16:06:17', '2015-09-28 16:06:17', 'Solicitou Ajuda: fsadfsadfsdaf', '2');
INSERT INTO `logs` VALUES ('50', '2015-09-28 16:16:03', '2015-09-28 16:16:03', 'Registou nova Fase: Fase 01', '1');
INSERT INTO `logs` VALUES ('51', '2015-09-28 16:16:10', '2015-09-28 16:16:10', 'Registou nova Fase: Fase 02', '1');
INSERT INTO `logs` VALUES ('52', '2015-09-28 16:16:17', '2015-09-28 16:16:17', 'Registou nova Fase: Fase 04', '1');
INSERT INTO `logs` VALUES ('53', '2015-09-28 16:16:47', '2015-09-28 16:16:47', 'Adicionou a fase: Fase 01 ao Ciclo: 2015 - Gestao e Orcamento UEM', '1');
INSERT INTO `logs` VALUES ('54', '2015-09-28 16:16:58', '2015-09-28 16:16:58', 'Adicionou a fase: Fase 02 ao Ciclo: 2015 - Gestao e Orcamento UEM', '1');
INSERT INTO `logs` VALUES ('55', '2015-09-28 16:17:16', '2015-09-28 16:17:16', 'Adicionou a fase: Fase 04 ao Ciclo: 2015 - Gestao e Orcamento UEM', '1');
INSERT INTO `logs` VALUES ('56', '2015-09-28 16:22:38', '2015-09-28 16:22:38', 'Registou um novo Ciclo: 2015 - Plano Geral de Actividades', '1');
INSERT INTO `logs` VALUES ('57', '2015-09-28 16:24:53', '2015-09-28 16:24:53', 'Mudou o estado do ciclo: 2015 - Gestao e Orcamento UEM para: Started', '1');
INSERT INTO `logs` VALUES ('58', '2015-10-07 15:23:32', '2015-10-07 15:23:32', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('59', '2015-10-07 15:24:25', '2015-10-07 15:24:25', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('60', '2015-10-07 15:24:27', '2015-10-07 15:24:27', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('61', '2015-10-07 15:24:28', '2015-10-07 15:24:28', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('62', '2015-10-07 15:24:29', '2015-10-07 15:24:29', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('63', '2015-10-07 15:24:29', '2015-10-07 15:24:29', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('64', '2015-10-07 15:24:30', '2015-10-07 15:24:30', 'Adicionou: wqeqe', '1');
INSERT INTO `logs` VALUES ('65', '2015-11-03 19:52:51', '2015-11-03 19:52:51', 'Adicionou a fase: Fase 01 ao Ciclo: 2015 - Gestao e Orcamento 2015-2016', '1');
INSERT INTO `logs` VALUES ('66', '2015-11-03 19:53:07', '2015-11-03 19:53:07', 'Adicionou a fase: Fase 02 ao Ciclo: 2015 - Gestao e Orcamento 2015-2016', '1');
INSERT INTO `logs` VALUES ('67', '2015-11-11 22:50:25', '2015-11-11 22:50:25', 'Mudou o estado do ciclo: 2015 - Gestao e Orcamento 2015-2016 para: Started', '1');
INSERT INTO `logs` VALUES ('68', '2015-11-11 22:52:47', '2015-11-11 22:52:47', 'Mudou o estado do ciclo: 2015 - Gestao e Orcamento 2015-2016 para: Started', '1');
INSERT INTO `logs` VALUES ('69', '2015-11-11 22:54:07', '2015-11-11 22:54:07', 'Adicionou a fase: Fase 01 ao Ciclo: 2015 - Plano Geral de Actividades', '1');
INSERT INTO `logs` VALUES ('70', '2015-11-11 22:54:29', '2015-11-11 22:54:29', 'Mudou o estado do ciclo: 2015 - Plano Geral de Actividades para: Started', '1');
INSERT INTO `logs` VALUES ('71', '2015-11-11 23:13:38', '2015-11-11 23:13:38', 'Registou novo funcionÃ¡rio: Marcos Moiseis Sitoe', '1');
INSERT INTO `logs` VALUES ('72', '2015-11-11 23:14:41', '2015-11-11 23:14:41', 'Removeu perfil do utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('73', '2015-11-11 23:15:27', '2015-11-11 23:15:27', 'Registou novo Perfil de Utilizadores: Orgao', '1');
INSERT INTO `logs` VALUES ('74', '2015-11-11 23:16:37', '2015-11-11 23:16:37', 'Registou novo Perfil de Utilizadores: Planificacao', '1');
INSERT INTO `logs` VALUES ('75', '2015-11-11 23:17:03', '2015-11-11 23:17:03', 'Registou novo Perfil de Utilizadores: Sector', '1');
INSERT INTO `logs` VALUES ('76', '2015-11-11 23:17:39', '2015-11-11 23:17:39', 'Removeu perfil do utilizador: eusebiomaposse@gmail.com', '1');
INSERT INTO `logs` VALUES ('77', '2015-11-11 23:18:01', '2015-11-11 23:18:01', 'Adicionou novos perfis ao utilizador: eusebiomaposse@gmail.com', '1');
INSERT INTO `logs` VALUES ('78', '2015-11-11 23:18:22', '2015-11-11 23:18:22', 'Removeu perfil do utilizador: heltonjossias@gmail.com', '1');
INSERT INTO `logs` VALUES ('79', '2015-11-11 23:18:42', '2015-11-11 23:18:42', 'Atribuiu novo responsÃ¡vel (Helton  Jossias) ao OrgÃ£o: Gabinete de Planificacao', '1');
INSERT INTO `logs` VALUES ('80', '2015-11-11 23:19:11', '2015-11-11 23:19:11', 'Removeu perfil do utilizador: jj.mulungo@gmail.com', '1');
INSERT INTO `logs` VALUES ('81', '2015-11-11 23:19:17', '2015-11-11 23:19:17', 'Adicionou novos perfis ao utilizador: jj.mulungo@gmail.com', '1');
INSERT INTO `logs` VALUES ('82', '2015-11-11 23:19:35', '2015-11-11 23:19:35', 'Adicionou novos perfis ao utilizador: heltonjossias@gmail.com', '1');
INSERT INTO `logs` VALUES ('83', '2015-11-11 23:19:56', '2015-11-11 23:19:56', 'Removeu perfil do utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('84', '2015-11-11 23:20:04', '2015-11-11 23:20:04', 'Adicionou novos perfis ao utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('85', '2015-11-11 23:20:24', '2015-11-11 23:20:24', 'Removeu perfil do utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('86', '2015-11-11 23:20:47', '2015-11-11 23:20:47', 'Adicionou novos perfis ao utilizador: eusebiomaposse@outlook.com', '1');
INSERT INTO `logs` VALUES ('87', '2015-11-11 23:22:51', '2015-11-11 23:22:51', 'Registou novo funcionÃ¡rio: Igor Maposse', '1');
INSERT INTO `logs` VALUES ('88', '2015-11-11 23:23:23', '2015-11-11 23:23:23', 'Registou novo Utilizador: imaposse@gmail.com', '1');
INSERT INTO `logs` VALUES ('89', '2015-11-11 23:23:51', '2015-11-11 23:23:51', 'Registou novo Utilizador: mmoiseis@gmail.com', '1');
INSERT INTO `logs` VALUES ('90', '2015-11-11 23:26:08', '2015-11-11 23:26:08', 'Registou uma nova actividade: Gestao Bancaria', '6');
INSERT INTO `logs` VALUES ('91', '2015-11-11 23:26:44', '2015-11-11 23:26:44', 'Registou uma nova actividade: Gestao Financeira', '6');
INSERT INTO `logs` VALUES ('92', '2015-11-11 23:27:29', '2015-11-11 23:27:29', 'Registou novo critÃ©rio: Criterio 01', '1');
INSERT INTO `logs` VALUES ('93', '2015-11-11 23:27:40', '2015-11-11 23:27:40', 'Registou novo critÃ©rio: Criterio 01', '1');
INSERT INTO `logs` VALUES ('94', '2015-11-11 23:27:56', '2015-11-11 23:27:56', 'Registou novo Objectivo EstratÃ©gico: Gestao Estrategica', '1');
INSERT INTO `logs` VALUES ('95', '2015-11-11 23:28:08', '2015-11-11 23:28:08', 'Registou novo Objectivo EstratÃ©gico: Financas Publicas', '1');
INSERT INTO `logs` VALUES ('96', '2015-11-11 23:28:23', '2015-11-11 23:28:23', 'Registou nova Ã�rea de GestÃ£o: Turismo', '1');
INSERT INTO `logs` VALUES ('97', '2015-11-11 23:28:34', '2015-11-11 23:28:34', 'Registou nova Ã�rea de GestÃ£o: Educacao', '1');
INSERT INTO `logs` VALUES ('98', '2015-11-11 23:29:48', '2015-11-11 23:29:48', 'Registou uma nova actividade: Financas Publicas', '6');
INSERT INTO `logs` VALUES ('99', '2015-11-11 23:31:09', '2015-11-11 23:31:09', 'Registou uma nova actividade: Financas', '2');
INSERT INTO `logs` VALUES ('100', '2015-11-11 23:33:14', '2015-11-11 23:33:14', 'Alocou a actividade: Reconciliar ao sector: Departamento de Sistemas e Multimedia', '2');

-- ----------------------------
-- Table structure for `notifications`
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `watched` bit(1) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h85luapamso17us506j999mcd` (`employee_id`),
  CONSTRAINT `FK_h85luapamso17us506j999mcd` FOREIGN KEY (`employee_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for `notification_msgs`
-- ----------------------------
DROP TABLE IF EXISTS `notification_msgs`;
CREATE TABLE `notification_msgs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kxcws2a7tnlc2nst6ktf8id8o` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification_msgs
-- ----------------------------

-- ----------------------------
-- Table structure for `organs`
-- ----------------------------
DROP TABLE IF EXISTS `organs`;
CREATE TABLE `organs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `organType` bigint(20) DEFAULT NULL,
  `plan` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7n49841xhasss2pr2a5qahijg` (`organType`),
  KEY `FK_o391ebf6l1kd1qvbxog770j1k` (`plan`),
  KEY `FK_n20rl0p7xnoarlgm1hjdnk3te` (`responsible_id`),
  CONSTRAINT `FK_7n49841xhasss2pr2a5qahijg` FOREIGN KEY (`organType`) REFERENCES `organ_types` (`id`),
  CONSTRAINT `FK_n20rl0p7xnoarlgm1hjdnk3te` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_o391ebf6l1kd1qvbxog770j1k` FOREIGN KEY (`plan`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organs
-- ----------------------------
INSERT INTO `organs` VALUES ('1', '2015-09-14 18:06:17', '2015-09-28 15:55:55', null, 'Centro de Informatica da Universidade Eduardo Mondlane', 'CIUEM', '1', null, '2');
INSERT INTO `organs` VALUES ('2', '2015-09-14 18:06:29', '2015-09-28 16:02:59', null, 'Faculdade de Ciencias', 'FC', '2', null, '3');
INSERT INTO `organs` VALUES ('3', '2015-09-14 18:06:40', '2015-09-28 16:03:04', null, 'Faculdade de Lentras', 'FL', '2', null, '4');
INSERT INTO `organs` VALUES ('4', '2015-09-14 18:06:52', '2015-11-11 23:18:42', null, 'Gabinete de Planificacao', 'GP', '3', null, '5');

-- ----------------------------
-- Table structure for `organ_cycles`
-- ----------------------------
DROP TABLE IF EXISTS `organ_cycles`;
CREATE TABLE `organ_cycles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `organ_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b4w36o9si6biy8jv9bh1dbs6u` (`organ_id`),
  CONSTRAINT `FK_b4w36o9si6biy8jv9bh1dbs6u` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ_cycles
-- ----------------------------

-- ----------------------------
-- Table structure for `organ_executions`
-- ----------------------------
DROP TABLE IF EXISTS `organ_executions`;
CREATE TABLE `organ_executions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `task_also` varchar(255) DEFAULT NULL,
  `allocationOrgan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tk0acru0lusw1eum5ac7yxlih` (`allocationOrgan_id`),
  CONSTRAINT `FK_tk0acru0lusw1eum5ac7yxlih` FOREIGN KEY (`allocationOrgan_id`) REFERENCES `allocation_organs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ_executions
-- ----------------------------

-- ----------------------------
-- Table structure for `organ_executions_files`
-- ----------------------------
DROP TABLE IF EXISTS `organ_executions_files`;
CREATE TABLE `organ_executions_files` (
  `Organ_executions_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Organ_executions_id`,`attachments_id`),
  UNIQUE KEY `UK_fp0ppstk47o2ns6lqn4j0d1fs` (`attachments_id`),
  CONSTRAINT `FK_1q5caf32d8i39gk75d1s7yi3` FOREIGN KEY (`Organ_executions_id`) REFERENCES `organ_executions` (`id`),
  CONSTRAINT `FK_fp0ppstk47o2ns6lqn4j0d1fs` FOREIGN KEY (`attachments_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ_executions_files
-- ----------------------------

-- ----------------------------
-- Table structure for `organ_roles`
-- ----------------------------
DROP TABLE IF EXISTS `organ_roles`;
CREATE TABLE `organ_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p1ikwcdnytus0y1ublebyttev` (`designation`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ_roles
-- ----------------------------
INSERT INTO `organ_roles` VALUES ('1', '2015-09-14 18:04:38', '2015-09-14 18:04:38', 'Principal');
INSERT INTO `organ_roles` VALUES ('2', '2015-09-14 18:04:38', '2015-09-14 18:04:38', 'Secundario');

-- ----------------------------
-- Table structure for `organ_types`
-- ----------------------------
DROP TABLE IF EXISTS `organ_types`;
CREATE TABLE `organ_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organ_types
-- ----------------------------
INSERT INTO `organ_types` VALUES ('1', '2015-09-14 18:05:23', '2015-09-14 18:05:23', null, 'CM', 'Centros e Museus');
INSERT INTO `organ_types` VALUES ('2', '2015-09-14 18:05:36', '2015-09-14 18:05:36', null, 'FE', 'Faculdades e Escolas');
INSERT INTO `organ_types` VALUES ('3', '2015-09-14 18:05:44', '2015-09-14 18:05:44', null, 'AD', 'Administrativos');

-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` text,
  `permissionname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kja1mxwxcw188e9pusu6vk750` (`permissionname`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', '2015-09-14 18:04:38', '2015-09-14 18:04:38', 'O utilizador poderÃ¡ ver as execuÃ§Ãµes dos funcionÃ¡rios.', 'EMPLOYEE_EXECUTIONS');
INSERT INTO `permissions` VALUES ('2', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os OrgÃ£os, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_ORGANS');
INSERT INTO `permissions` VALUES ('3', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os FuncionÃ¡rios, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_EMPLOYEES');
INSERT INTO `permissions` VALUES ('4', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Sectores, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_SECTORS');
INSERT INTO `permissions` VALUES ('5', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Ã�reas de GestÃ£o, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_CRITICAL_AREAS');
INSERT INTO `permissions` VALUES ('6', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'Super utilizador com acesso as Ã¡reas de administraÃ§Ã£o do sistema e outras configuraÃ§Ãµes crÃ­ticas do sistema.', 'IS_SUPER_ADMIN');
INSERT INTO `permissions` VALUES ('7', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Papeis dos Ã³rgÃ£os intervenientes, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_ORGAN_ROLES');
INSERT INTO `permissions` VALUES ('8', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os CritÃ©rios de Actividade, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_CRITERIAS');
INSERT INTO `permissions` VALUES ('9', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Fontes, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_SOURCES');
INSERT INTO `permissions` VALUES ('10', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ criar, configurar os ciclos, adicionar fases e respectivos perÃ­odos de execuÃ§Ã£o.', 'MANAGE_CYCLES');
INSERT INTO `permissions` VALUES ('11', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ registar actividades, actualizar.', 'MANAGE_ACTIONS');
INSERT INTO `permissions` VALUES ('12', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡, alocar actividades ao sector.', 'SECTOR_ALLOCATIONS');
INSERT INTO `permissions` VALUES ('13', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Tipos de OrgÃ£os, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_ORGAN_TYPES');
INSERT INTO `permissions` VALUES ('14', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ registar, actualizar actividades especÃ­ficas.', 'MANAGE_SPECIFIED_ACTIONS');
INSERT INTO `permissions` VALUES ('15', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre as Fases, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_PHASES');
INSERT INTO `permissions` VALUES ('16', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ fazer todas as operaÃ§Ãµes sobre os Objectivos EstratÃ©gicos, criaÃ§Ã£o, actualizaÃ§Ã£o.', 'MANAGE_STRATEGIC_OBJECTIVES');
INSERT INTO `permissions` VALUES ('17', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ alocar actividades aos funcionÃ¡rios.', 'EMPLOYEE_ALLOCATIONS');
INSERT INTO `permissions` VALUES ('18', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ autenticar no sistema e ver alocaÃ§Ãµes feitas a si pelo seu orgÃ£o ou sector.', 'IS_EMPLOYEE');
INSERT INTO `permissions` VALUES ('19', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'Essa permissÃ£o assumi o utilizador como funcionÃ¡rio do Gabinete de PlanificaÃ§Ã£o, com acesso a visualizaÃ§Ã£o de todos planos de actividades', 'PLANNING_OFFICE');
INSERT INTO `permissions` VALUES ('20', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ Realiza todas operacoes relacionadas com as Contas dos  OrgÃ£os', 'MANEGE_ACCOUNT');
INSERT INTO `permissions` VALUES ('21', '2015-09-14 18:04:39', '2015-09-14 18:04:39', 'O utilizador poderÃ¡ Realiza todas operacoes relacionadas com as Contas dos  OrgÃ£os', 'MANEGE_ATTACHMENT');

-- ----------------------------
-- Table structure for `phases`
-- ----------------------------
DROP TABLE IF EXISTS `phases`;
CREATE TABLE `phases` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `designacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phases
-- ----------------------------
INSERT INTO `phases` VALUES ('1', '2015-09-28 16:16:03', '2015-09-28 16:16:03', '01', 'Fase 01');
INSERT INTO `phases` VALUES ('2', '2015-09-28 16:16:10', '2015-09-28 16:16:10', '02', 'Fase 02');
INSERT INTO `phases` VALUES ('3', '2015-09-28 16:16:17', '2015-09-28 16:16:17', '03', 'Fase 04');

-- ----------------------------
-- Table structure for `phases_organ_cycles`
-- ----------------------------
DROP TABLE IF EXISTS `phases_organ_cycles`;
CREATE TABLE `phases_organ_cycles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `acepted` varchar(255) DEFAULT NULL,
  `executed_activity` varchar(255) DEFAULT NULL,
  `realization_date` datetime DEFAULT NULL,
  `realization_percentage` int(11) DEFAULT NULL,
  `successfully` bit(1) DEFAULT NULL,
  `embarrassment_id` bigint(20) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  `phase_cycle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qb0sxgbh15f50g9encbf2rloe` (`embarrassment_id`),
  KEY `FK_jlw9o5fcxhvuqgg8thd7griot` (`organ`),
  KEY `FK_slmlj00xfkj9pec64anu3slxy` (`phase_cycle_id`),
  CONSTRAINT `FK_jlw9o5fcxhvuqgg8thd7griot` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_qb0sxgbh15f50g9encbf2rloe` FOREIGN KEY (`embarrassment_id`) REFERENCES `embarrassments` (`id`),
  CONSTRAINT `FK_slmlj00xfkj9pec64anu3slxy` FOREIGN KEY (`phase_cycle_id`) REFERENCES `phase_cycles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phases_organ_cycles
-- ----------------------------

-- ----------------------------
-- Table structure for `phases_organ_cycles_files`
-- ----------------------------
DROP TABLE IF EXISTS `phases_organ_cycles_files`;
CREATE TABLE `phases_organ_cycles_files` (
  `phases_organ_cycles_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`phases_organ_cycles_id`,`attachments_id`),
  UNIQUE KEY `UK_7bipsv0pe26gvheg7xvv6e79b` (`attachments_id`),
  CONSTRAINT `FK_4kp5caeuye0tnefv07paltnbb` FOREIGN KEY (`phases_organ_cycles_id`) REFERENCES `phases_organ_cycles` (`id`),
  CONSTRAINT `FK_7bipsv0pe26gvheg7xvv6e79b` FOREIGN KEY (`attachments_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phases_organ_cycles_files
-- ----------------------------

-- ----------------------------
-- Table structure for `phase_cycles`
-- ----------------------------
DROP TABLE IF EXISTS `phase_cycles`;
CREATE TABLE `phase_cycles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` int(11) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `cycle_id` bigint(20) DEFAULT NULL,
  `phase_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cd5upiou2v0sv43g337imuqp7` (`cycle_id`),
  KEY `FK_86rm1qnjkycef7mdq4wj43bt7` (`phase_id`),
  CONSTRAINT `FK_86rm1qnjkycef7mdq4wj43bt7` FOREIGN KEY (`phase_id`) REFERENCES `phases` (`id`),
  CONSTRAINT `FK_cd5upiou2v0sv43g337imuqp7` FOREIGN KEY (`cycle_id`) REFERENCES `cycles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phase_cycles
-- ----------------------------
INSERT INTO `phase_cycles` VALUES ('1', '2015-09-28 16:16:47', '2015-09-28 16:16:47', '0', '2015-12-05 00:00:00', '2015-09-14 00:00:00', '1', '1');
INSERT INTO `phase_cycles` VALUES ('2', '2015-09-28 16:16:57', '2015-09-28 16:16:57', '0', '2015-12-06 00:00:00', '2015-10-02 00:00:00', '1', '2');
INSERT INTO `phase_cycles` VALUES ('3', '2015-09-28 16:17:16', '2015-09-28 16:17:16', '0', '2015-12-20 00:00:00', '2015-10-24 00:00:00', '1', '3');
INSERT INTO `phase_cycles` VALUES ('4', '2015-11-03 19:52:51', '2015-11-03 19:52:51', '0', '2015-12-06 00:00:00', '2015-11-03 00:00:00', '2', '1');
INSERT INTO `phase_cycles` VALUES ('5', '2015-11-03 19:53:07', '2015-11-03 19:53:07', '0', '2016-01-31 00:00:00', '2015-12-03 00:00:00', '2', '2');
INSERT INTO `phase_cycles` VALUES ('6', '2015-11-11 22:54:06', '2015-11-11 22:54:06', '0', '2015-12-06 00:00:00', '2015-10-26 00:00:00', '3', '1');

-- ----------------------------
-- Table structure for `plan`
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_24abnx54yjcdww8my1osmbu7u` (`organ`),
  CONSTRAINT `FK_24abnx54yjcdww8my1osmbu7u` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------

-- ----------------------------
-- Table structure for `priority_levels`
-- ----------------------------
DROP TABLE IF EXISTS `priority_levels`;
CREATE TABLE `priority_levels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `firstCriteria_id` bigint(20) DEFAULT NULL,
  `secondCriteria_id` bigint(20) DEFAULT NULL,
  `thirdCriteria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qniy6wvcrx58caytalrd72b0b` (`firstCriteria_id`),
  KEY `FK_oh7f1a4t7qbnacqgoljjyqb84` (`secondCriteria_id`),
  KEY `FK_s78ip9xwie2kow06o99c8gs8h` (`thirdCriteria_id`),
  CONSTRAINT `FK_oh7f1a4t7qbnacqgoljjyqb84` FOREIGN KEY (`secondCriteria_id`) REFERENCES `criterias` (`id`),
  CONSTRAINT `FK_qniy6wvcrx58caytalrd72b0b` FOREIGN KEY (`firstCriteria_id`) REFERENCES `criterias` (`id`),
  CONSTRAINT `FK_s78ip9xwie2kow06o99c8gs8h` FOREIGN KEY (`thirdCriteria_id`) REFERENCES `criterias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of priority_levels
-- ----------------------------
INSERT INTO `priority_levels` VALUES ('1', '2015-11-11 23:26:08', '2015-11-11 23:26:08', null, null, null);
INSERT INTO `priority_levels` VALUES ('2', '2015-11-11 23:26:44', '2015-11-11 23:26:44', null, null, null);
INSERT INTO `priority_levels` VALUES ('3', '2015-11-11 23:29:48', '2015-11-11 23:29:48', '1', '2', null);
INSERT INTO `priority_levels` VALUES ('4', '2015-11-11 23:31:09', '2015-11-11 23:31:09', '1', '2', null);

-- ----------------------------
-- Table structure for `profession`
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES ('1', '2015-09-14 18:04:40', '2015-09-14 18:04:40', 'Administrador de Sistemas de Software', 'Adminstrador de Sistemas');

-- ----------------------------
-- Table structure for `purposes`
-- ----------------------------
DROP TABLE IF EXISTS `purposes`;
CREATE TABLE `purposes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `porpose` varchar(255) DEFAULT NULL,
  `purpose_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purposes
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `rolename` varchar(50) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jdhyvh8di85ai37phukfemdnx` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '2015-09-14 18:04:40', '2015-09-14 18:04:40', 'SectorChief', 'Delegated');
INSERT INTO `roles` VALUES ('2', '2015-09-14 18:04:40', '2015-09-14 18:04:40', 'OrganChief', 'Delegated');
INSERT INTO `roles` VALUES ('3', '2015-09-14 18:04:40', '2015-09-14 18:04:40', 'Admin', 'Normal');
INSERT INTO `roles` VALUES ('4', '2015-09-14 18:33:23', '2015-09-14 18:33:23', 'Reitoria', 'Normal');
INSERT INTO `roles` VALUES ('5', '2015-11-11 23:15:27', '2015-11-11 23:15:27', 'Orgao', 'Normal');
INSERT INTO `roles` VALUES ('6', '2015-11-11 23:16:37', '2015-11-11 23:16:37', 'Planificacao', 'Normal');
INSERT INTO `roles` VALUES ('7', '2015-11-11 23:17:03', '2015-11-11 23:17:03', 'Sector', 'Normal');

-- ----------------------------
-- Table structure for `role_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FK_d4atqq8ege1sij0316vh2mxfu` (`role_id`),
  CONSTRAINT `FK_d4atqq8ege1sij0316vh2mxfu` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_qfkbccnh2c5o4tc7akq5x11wv` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permissions
-- ----------------------------
INSERT INTO `role_permissions` VALUES ('3', '1');
INSERT INTO `role_permissions` VALUES ('3', '2');
INSERT INTO `role_permissions` VALUES ('3', '3');
INSERT INTO `role_permissions` VALUES ('3', '4');
INSERT INTO `role_permissions` VALUES ('3', '5');
INSERT INTO `role_permissions` VALUES ('3', '6');
INSERT INTO `role_permissions` VALUES ('3', '7');
INSERT INTO `role_permissions` VALUES ('3', '8');
INSERT INTO `role_permissions` VALUES ('3', '9');
INSERT INTO `role_permissions` VALUES ('3', '10');
INSERT INTO `role_permissions` VALUES ('3', '11');
INSERT INTO `role_permissions` VALUES ('3', '12');
INSERT INTO `role_permissions` VALUES ('3', '13');
INSERT INTO `role_permissions` VALUES ('3', '14');
INSERT INTO `role_permissions` VALUES ('3', '15');
INSERT INTO `role_permissions` VALUES ('3', '16');
INSERT INTO `role_permissions` VALUES ('3', '17');
INSERT INTO `role_permissions` VALUES ('3', '18');
INSERT INTO `role_permissions` VALUES ('3', '19');
INSERT INTO `role_permissions` VALUES ('3', '20');
INSERT INTO `role_permissions` VALUES ('3', '21');
INSERT INTO `role_permissions` VALUES ('4', '21');
INSERT INTO `role_permissions` VALUES ('5', '11');
INSERT INTO `role_permissions` VALUES ('5', '12');
INSERT INTO `role_permissions` VALUES ('5', '14');
INSERT INTO `role_permissions` VALUES ('6', '10');
INSERT INTO `role_permissions` VALUES ('6', '11');
INSERT INTO `role_permissions` VALUES ('6', '12');
INSERT INTO `role_permissions` VALUES ('6', '14');
INSERT INTO `role_permissions` VALUES ('6', '19');
INSERT INTO `role_permissions` VALUES ('7', '1');
INSERT INTO `role_permissions` VALUES ('7', '17');

-- ----------------------------
-- Table structure for `sectors`
-- ----------------------------
DROP TABLE IF EXISTS `sectors`;
CREATE TABLE `sectors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h4nrjjda1i1etyoc87w4okf47` (`organ`),
  KEY `FK_d0p8ia3886s533r7pv5aqphco` (`responsible_id`),
  CONSTRAINT `FK_d0p8ia3886s533r7pv5aqphco` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_h4nrjjda1i1etyoc87w4okf47` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sectors
-- ----------------------------
INSERT INTO `sectors` VALUES ('1', '2015-09-14 18:07:14', '2015-09-14 18:07:14', null, 'Departamento de Sistemas e Multimedia', 'DSM', '1', null);
INSERT INTO `sectors` VALUES ('2', '2015-09-14 18:07:38', '2015-09-14 18:07:38', null, 'Departamento de Matematica e Informatica', 'DMI', '2', null);
INSERT INTO `sectors` VALUES ('3', '2015-09-14 18:07:59', '2015-09-14 18:07:59', null, 'Departamento de Fisica ', 'DF', '2', null);
INSERT INTO `sectors` VALUES ('4', '2015-09-14 18:08:10', '2015-09-14 18:08:10', null, 'Departamento de Quimica', 'DQ', '2', null);
INSERT INTO `sectors` VALUES ('5', '2015-09-14 18:08:34', '2015-09-14 18:08:34', null, 'Departamento de Apoio', 'DA', '1', null);
INSERT INTO `sectors` VALUES ('6', '2015-09-28 15:58:22', '2015-09-28 15:58:22', null, 'Departamento Financeiro', 'DF', '3', null);
INSERT INTO `sectors` VALUES ('7', '2015-09-28 15:58:45', '2015-11-11 23:25:05', null, 'Departamento de Planificacao', 'DP', '4', '7');
INSERT INTO `sectors` VALUES ('8', '2015-09-28 15:59:13', '2015-09-28 15:59:13', null, 'Departamento de Estatistica', 'DE', '4', null);
INSERT INTO `sectors` VALUES ('9', '2015-09-28 15:59:46', '2015-09-28 15:59:46', null, 'Departamento de Admissao a Universidade', 'DAU', '1', null);

-- ----------------------------
-- Table structure for `sector_executions`
-- ----------------------------
DROP TABLE IF EXISTS `sector_executions`;
CREATE TABLE `sector_executions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `task_also` varchar(255) DEFAULT NULL,
  `totalRate` int(11) NOT NULL,
  `allocation_sector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gvg5t5g4edf6yj7lhmuhwh4il` (`allocation_sector_id`),
  CONSTRAINT `FK_gvg5t5g4edf6yj7lhmuhwh4il` FOREIGN KEY (`allocation_sector_id`) REFERENCES `allocation_sectors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sector_executions
-- ----------------------------

-- ----------------------------
-- Table structure for `sector_executions_files`
-- ----------------------------
DROP TABLE IF EXISTS `sector_executions_files`;
CREATE TABLE `sector_executions_files` (
  `sector_executions_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sector_executions_id`,`attachments_id`),
  UNIQUE KEY `UK_fovhp3co8csufucgbmu04urkn` (`attachments_id`),
  CONSTRAINT `FK_fovhp3co8csufucgbmu04urkn` FOREIGN KEY (`attachments_id`) REFERENCES `files` (`id`),
  CONSTRAINT `FK_nto9xu7qaq0d45rpejjj6sbtj` FOREIGN KEY (`sector_executions_id`) REFERENCES `sector_executions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sector_executions_files
-- ----------------------------

-- ----------------------------
-- Table structure for `specified_actions`
-- ----------------------------
DROP TABLE IF EXISTS `specified_actions`;
CREATE TABLE `specified_actions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `ambit` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `action_id` bigint(20) DEFAULT NULL,
  `attachment` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g0ey88a24u9glwtelvdkk2fvc` (`action_id`),
  KEY `FK_7x91gcdaoygp73a5p6my3e72h` (`attachment`),
  CONSTRAINT `FK_7x91gcdaoygp73a5p6my3e72h` FOREIGN KEY (`attachment`) REFERENCES `attachment` (`id`),
  CONSTRAINT `FK_g0ey88a24u9glwtelvdkk2fvc` FOREIGN KEY (`action_id`) REFERENCES `actions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specified_actions
-- ----------------------------
INSERT INTO `specified_actions` VALUES ('1', '2015-11-11 23:32:50', '2015-11-11 23:32:50', 'Planificacao', '2015-11-15 00:00:00', 'Registar Financas', '2015-11-22 00:00:00', '4', null);

-- ----------------------------
-- Table structure for `specified_actions_allocation_organs`
-- ----------------------------
DROP TABLE IF EXISTS `specified_actions_allocation_organs`;
CREATE TABLE `specified_actions_allocation_organs` (
  `specified_actions_id` bigint(20) NOT NULL,
  `allocations_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_rsc0r7hrcc2h5y6urg3u70ock` (`allocations_id`),
  KEY `FK_8jxda58qvf03quqm2tov7q5jj` (`specified_actions_id`),
  CONSTRAINT `FK_8jxda58qvf03quqm2tov7q5jj` FOREIGN KEY (`specified_actions_id`) REFERENCES `specified_actions` (`id`),
  CONSTRAINT `FK_rsc0r7hrcc2h5y6urg3u70ock` FOREIGN KEY (`allocations_id`) REFERENCES `allocation_organs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specified_actions_allocation_organs
-- ----------------------------

-- ----------------------------
-- Table structure for `strategic_objectives`
-- ----------------------------
DROP TABLE IF EXISTS `strategic_objectives`;
CREATE TABLE `strategic_objectives` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of strategic_objectives
-- ----------------------------
INSERT INTO `strategic_objectives` VALUES ('1', '2015-11-11 23:27:56', '2015-11-11 23:27:56', '01', 'Gestao Estrategica');
INSERT INTO `strategic_objectives` VALUES ('2', '2015-11-11 23:28:08', '2015-11-11 23:28:08', '02', 'Financas Publicas');

-- ----------------------------
-- Table structure for `subscribers`
-- ----------------------------
DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE `subscribers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `account` bigint(20) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `sector` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f60pklcv8nndcssilxee748rb` (`account`),
  KEY `FK_71xsyhuwbpoglhr07q5tg6au8` (`employee`),
  KEY `FK_jf892hyrel5hujjucf5tbi8tu` (`sector`),
  CONSTRAINT `FK_71xsyhuwbpoglhr07q5tg6au8` FOREIGN KEY (`employee`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_f60pklcv8nndcssilxee748rb` FOREIGN KEY (`account`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK_jf892hyrel5hujjucf5tbi8tu` FOREIGN KEY (`sector`) REFERENCES `sectors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscribers
-- ----------------------------

-- ----------------------------
-- Table structure for `sub_sectors`
-- ----------------------------
DROP TABLE IF EXISTS `sub_sectors`;
CREATE TABLE `sub_sectors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `sector` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fjunyd4ford6qhobmwl584ugd` (`sector`),
  CONSTRAINT `FK_fjunyd4ford6qhobmwl584ugd` FOREIGN KEY (`sector`) REFERENCES `sectors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_sectors
-- ----------------------------
INSERT INTO `sub_sectors` VALUES ('1', '2015-09-14 18:09:00', '2015-09-14 18:09:00', 'SubSectoe de Fisica Aplicada', 'SFA', '3');
INSERT INTO `sub_sectors` VALUES ('2', '2015-09-14 18:09:11', '2015-09-14 18:09:11', 'SubSector de Analise', 'SA', '1');
INSERT INTO `sub_sectors` VALUES ('3', '2015-09-14 18:09:26', '2015-09-14 18:09:26', 'SubSector de Programacao', 'SP', '1');
INSERT INTO `sub_sectors` VALUES ('4', '2015-09-14 18:09:42', '2015-09-14 18:09:42', 'SubSector de Implementacao', 'SI', '1');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` longtext NOT NULL,
  `username` varchar(50) NOT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK_tlayvmkmhejp4mqjsmp7p6vxf` (`responsible_id`),
  CONSTRAINT `FK_tlayvmkmhejp4mqjsmp7p6vxf` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2015-09-14 18:04:40', '2015-09-14 18:04:40', '', '$2a$10$azdAIgvoXOGcGLyn7JxUsu2oyjbRHB6tWYMZA6Xc.mvLTh60itDmO', 'admin@admin.com', '1');
INSERT INTO `users` VALUES ('2', '2015-09-14 18:33:57', '2015-11-11 23:18:01', '', '$2a$10$2pi6jqb5ay5u.qZDpNEKreyt4.1s/onfk1qgGR3l9PDLLzcZml2YO', 'eusebiomaposse@gmail.com', '2');
INSERT INTO `users` VALUES ('3', '2015-09-28 16:01:39', '2015-11-11 23:19:16', '', '$2a$10$a6OJMzAfPGeB.Ct5lKKWF.kb5Nz6IoIudEYWSVzKBmc5r3G2lDGnu', 'jj.mulungo@gmail.com', '3');
INSERT INTO `users` VALUES ('5', '2015-09-28 16:02:48', '2015-11-11 23:20:47', '', '$2a$10$fdRWpLVuUeArljL7dam41e8cpiREkuFl8vjw.kNR6Fkw6IoCxtKUO', 'eusebiomaposse@outlook.com', '4');
INSERT INTO `users` VALUES ('6', '2015-09-28 16:04:26', '2015-11-11 23:19:35', '', '$2a$10$j4Z5BxicsZIRkyByzDMRyeXSAOn/YmToFSn7FWrhWOtsIdEy0KtQi', 'heltonjossias@gmail.com', '5');
INSERT INTO `users` VALUES ('7', '2015-11-11 23:23:23', '2015-11-11 23:25:06', '', '$2a$10$6QIEO.kXsCZOAPnHXklHuuzUulqhmjHGiX7XfhxSTJk2aOVOipYl2', 'imaposse@gmail.com', '7');
INSERT INTO `users` VALUES ('8', '2015-11-11 23:23:51', '2015-11-11 23:23:51', '', '$2a$10$nzS0U0Yg6sGqcFJOBKa3.Ox/9/fFL8CHSQY9taoRvvBqHBHq45Bt2', 'mmoiseis@gmail.com', '6');

-- ----------------------------
-- Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_5q4rc4fh1on6567qk69uesvyf` (`role_id`),
  CONSTRAINT `FK_5q4rc4fh1on6567qk69uesvyf` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_g1uebn6mqk9qiaw45vnacmyo2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '7');
INSERT INTO `user_roles` VALUES ('2', '2');
INSERT INTO `user_roles` VALUES ('2', '3');
INSERT INTO `user_roles` VALUES ('2', '6');
INSERT INTO `user_roles` VALUES ('3', '1');
INSERT INTO `user_roles` VALUES ('5', '2');
INSERT INTO `user_roles` VALUES ('5', '3');
INSERT INTO `user_roles` VALUES ('6', '6');
INSERT INTO `user_roles` VALUES ('7', '5');
INSERT INTO `user_roles` VALUES ('7', '7');
INSERT INTO `user_roles` VALUES ('7', '8');