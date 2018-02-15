/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : uemgp_db

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2015-08-03 13:00:38
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
  `account_type` bigint(20) DEFAULT NULL,
  `bank` bigint(20) DEFAULT NULL,
  `coin` bigint(20) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  `organ` bigint(20) DEFAULT NULL,
  `purpose` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_types
-- ----------------------------
INSERT INTO `account_types` VALUES ('1', '2015-07-27 18:26:32', '2015-07-27 18:26:32', 'Operacoes Correntes', 'Corrente');
INSERT INTO `account_types` VALUES ('2', '2015-08-02 15:11:37', '2015-08-02 15:11:37', 'Conjunta', 'Conjunta');

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
  `designation` varchar(255) DEFAULT NULL,
  `criticalArea_id` bigint(20) DEFAULT NULL,
  `cycle_id` bigint(20) DEFAULT NULL,
  `organ_id` bigint(20) DEFAULT NULL,
  `priority_level_id` bigint(20) DEFAULT NULL,
  `strategic_objective_id` bigint(20) DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `budgetA` float DEFAULT NULL,
  `plan` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pif79kt7iw9dww2tx9cjhylns` (`criticalArea_id`),
  KEY `FK_9t5wkj1g8d6qkqmsxufxot8cy` (`cycle_id`),
  KEY `FK_sneodv6dtgufr7n382i3ew4mu` (`organ_id`),
  KEY `FK_337co17qvb8c9ctgq7dc4u6r3` (`priority_level_id`),
  KEY `FK_k8fwqe208rc77dwqhaokos6ra` (`strategic_objective_id`),
  KEY `FK_iul8vugbkia33wqgy0ttsl4kt` (`plan`),
  CONSTRAINT `FK_337co17qvb8c9ctgq7dc4u6r3` FOREIGN KEY (`priority_level_id`) REFERENCES `priority_levels` (`id`),
  CONSTRAINT `FK_9t5wkj1g8d6qkqmsxufxot8cy` FOREIGN KEY (`cycle_id`) REFERENCES `cycles` (`id`),
  CONSTRAINT `FK_iul8vugbkia33wqgy0ttsl4kt` FOREIGN KEY (`plan`) REFERENCES `plan` (`id`),
  CONSTRAINT `FK_k8fwqe208rc77dwqhaokos6ra` FOREIGN KEY (`strategic_objective_id`) REFERENCES `strategic_objectives` (`id`),
  CONSTRAINT `FK_pif79kt7iw9dww2tx9cjhylns` FOREIGN KEY (`criticalArea_id`) REFERENCES `critical_areas` (`id`),
  CONSTRAINT `FK_sneodv6dtgufr7n382i3ew4mu` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of actions
-- ----------------------------
INSERT INTO `actions` VALUES ('1', '2015-07-07 02:00:00', 'Elaborar o Plano de Actividades ', '#3333ff', '2015-07-23 14:38:31', '2015-08-30 02:00:00', '#3366ff', '', '2015-07-23 14:38:31', 'Elaborar o Plano de Actividades ', '1', '1', '2', '1', '2', null, null, null);
INSERT INTO `actions` VALUES ('2', '2015-06-28 02:00:00', 'Gestao e Orcamentos', '#6699ff', '2015-07-23 14:49:51', '2015-07-17 02:00:00', '#3366ff', '', '2015-07-23 14:49:51', 'Gestao e Orcamentos', '2', '1', '2', '2', '4', null, null, null);
INSERT INTO `actions` VALUES ('3', '2015-06-29 02:00:00', 'Qualidade da Docencia', '#ccff33', '2015-07-23 14:50:35', '2015-08-02 02:00:00', '#3366ff', '', '2015-07-23 14:50:35', 'Qualidade da Docencia', '2', '1', '2', '3', '1', null, null, null);
INSERT INTO `actions` VALUES ('4', '2015-06-29 02:00:00', 'Montar Servidores', '#6699ff', '2015-07-23 19:26:14', '2015-07-17 02:00:00', '#3366ff', '', '2015-07-23 19:26:14', 'Montar Servidores', '1', '1', '1', '4', '1', '0', null, null);
INSERT INTO `actions` VALUES ('5', '2015-07-05 02:00:00', 'Gestao de Paginas', '#6699ff', '2015-07-23 19:26:33', '2015-07-31 02:00:00', '#3366ff', '', '2015-07-23 19:26:33', 'Gestao de Paginas', '2', '1', '1', '5', '2', '0', null, null);
INSERT INTO `actions` VALUES ('6', '2015-07-06 02:00:00', 'Gestao de Sistemas', '#00ffff', '2015-07-23 19:27:00', '2015-07-17 02:00:00', '#3366ff', '', '2015-07-23 19:27:00', 'Gestao de Sistemas', '1', '1', '1', '6', '1', '0', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_employees
-- ----------------------------
INSERT INTO `allocation_employees` VALUES ('1', '2015-07-23 22:08:46', '2015-07-23 22:08:46', '2015-08-02 00:00:00', '2015-07-16 00:00:00', 'Codificar Aplicacoes de Software', '3', '16', '2');

-- ----------------------------
-- Table structure for `allocation_organs`
-- ----------------------------
DROP TABLE IF EXISTS `allocation_organs`;
CREATE TABLE `allocation_organs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `task_also` varchar(255) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `organ_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `specified_action_id` bigint(20) DEFAULT NULL,
  `seen` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_epktfi0bfu468shiutwhox15t` (`comment_id`),
  KEY `FK_qqlh3e4u03gidlfd8q5yuu72y` (`organ_id`),
  KEY `FK_tfcolc9p3589xqmf7w8sh10ty` (`role_id`),
  KEY `FK_3828vk3wnp2spa2vn6092jctf` (`specified_action_id`),
  CONSTRAINT `FK_3828vk3wnp2spa2vn6092jctf` FOREIGN KEY (`specified_action_id`) REFERENCES `specified_actions` (`id`),
  CONSTRAINT `FK_epktfi0bfu468shiutwhox15t` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `FK_qqlh3e4u03gidlfd8q5yuu72y` FOREIGN KEY (`organ_id`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_tfcolc9p3589xqmf7w8sh10ty` FOREIGN KEY (`role_id`) REFERENCES `organ_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_organs
-- ----------------------------
INSERT INTO `allocation_organs` VALUES ('1', '2015-07-23 14:39:45', '2015-07-23 14:39:45', '2015-08-02 00:00:00', null, '', 'Disponibilizar Material Informatico', null, '1', '2', '1', '');
INSERT INTO `allocation_organs` VALUES ('2', '2015-07-23 14:39:45', '2015-07-23 14:39:45', '2015-08-28 00:00:00', '2015-07-07 00:00:00', '', 'Montar Equipe', null, '2', '1', '1', '');
INSERT INTO `allocation_organs` VALUES ('3', '2015-07-23 14:51:21', '2015-07-23 14:51:21', '2015-08-02 00:00:00', '2015-07-23 00:00:00', '', 'Montar Equipe de Choque', null, '2', '1', '2', '');
INSERT INTO `allocation_organs` VALUES ('4', '2015-07-23 14:53:35', '2015-07-23 14:53:35', '2015-07-16 00:00:00', '2015-07-02 00:00:00', '', 'Gestao po Orgao', null, '2', '1', '3', '');
INSERT INTO `allocation_organs` VALUES ('5', '2015-07-23 14:54:27', '2015-07-23 14:54:27', '2015-07-17 00:00:00', '2015-07-15 00:00:00', '', 'Gestao Sector', null, '2', '1', '4', '');
INSERT INTO `allocation_organs` VALUES ('6', '2015-07-23 14:55:19', '2015-07-23 14:55:19', '2015-07-17 00:00:00', '2015-06-29 00:00:00', '', 'Gestao por Funcionario', null, '2', '1', '5', '');
INSERT INTO `allocation_organs` VALUES ('7', '2015-07-23 19:28:27', '2015-07-23 19:28:27', '2015-07-08 00:00:00', '2015-07-06 00:00:00', '', 'Elaboracao do Plano de Formacao', null, '1', '1', '6', '');
INSERT INTO `allocation_organs` VALUES ('8', '2015-07-23 19:29:50', '2015-07-24 15:35:57', '2015-07-17 00:00:00', null, '', 'Garantir Legalidade', '1', '2', '2', '7', '');
INSERT INTO `allocation_organs` VALUES ('9', '2015-07-23 19:29:50', '2015-07-23 19:29:50', '2015-07-17 00:00:00', '2015-07-06 00:00:00', '', 'Elaboracao do Plano Anual dos Sectores', null, '1', '1', '7', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allocation_sectors
-- ----------------------------
INSERT INTO `allocation_sectors` VALUES ('1', '2015-07-23 14:40:33', '2015-07-23 14:40:33', '2015-08-02 00:00:00', '2015-07-23 00:00:00', '', 'Elaborar o Documento ', '2', '12', '3');
INSERT INTO `allocation_sectors` VALUES ('2', '2015-07-23 14:41:09', '2015-07-23 14:41:09', '2015-08-28 00:00:00', '2015-07-30 00:00:00', '', 'Fazer Estudo de Viabilidade', '2', '6', '4');
INSERT INTO `allocation_sectors` VALUES ('3', '2015-07-23 21:48:40', '2015-07-23 21:48:40', '2015-07-09 00:00:00', '2015-07-09 00:00:00', '', 'Contruir a Equipe', '7', '8', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banks
-- ----------------------------
INSERT INTO `banks` VALUES ('1', '2015-07-23 10:51:06', '2015-07-28 19:11:23', 'Banco Internacional de Mocambique', 'BIM');
INSERT INTO `banks` VALUES ('2', '2015-07-23 10:51:28', '2015-07-23 11:43:23', 'Banco Comercial de Investimentos', 'BCI');

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
  `font_id` bigint(20) DEFAULT NULL,
  `specified_action_id` bigint(20) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `unit_cost` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3lwwkgcixxbkcv88hgck70apv` (`font_id`),
  KEY `FK_epdtvtrnhsh5u6ai2hl54pvdd` (`specified_action_id`),
  CONSTRAINT `FK_3lwwkgcixxbkcv88hgck70apv` FOREIGN KEY (`font_id`) REFERENCES `fonts` (`id`),
  CONSTRAINT `FK_epdtvtrnhsh5u6ai2hl54pvdd` FOREIGN KEY (`specified_action_id`) REFERENCES `specified_actions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of based_calculating
-- ----------------------------
INSERT INTO `based_calculating` VALUES ('1', '2015-07-23 14:39:45', '2015-07-23 14:39:45', '3000', '30 Cadeiras', '2', '1', '10', '30');
INSERT INTO `based_calculating` VALUES ('2', '2015-07-23 14:51:21', '2015-07-23 14:51:21', '300', '30 Cadeiras', '2', '2', '40', '3');
INSERT INTO `based_calculating` VALUES ('3', '2015-07-23 14:54:27', '2015-07-23 14:54:27', '50000', '4000 Entrevistas', '2', '4', '344', '32');
INSERT INTO `based_calculating` VALUES ('4', '2015-07-23 14:55:19', '2015-07-23 14:55:19', '3000', '400 Filas', '1', '5', '44', '45');
INSERT INTO `based_calculating` VALUES ('5', '2015-07-23 19:28:27', '2015-07-23 19:28:27', '6800', '200 Bilhetes Azgo', '1', '6', '200', '34');
INSERT INTO `based_calculating` VALUES ('6', '2015-07-23 19:29:50', '2015-07-23 19:29:50', '8600', '400 Resmas', '2', '7', '43', '200');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coins
-- ----------------------------
INSERT INTO `coins` VALUES ('1', '2015-07-28 16:56:54', '2015-07-28 16:56:54', 'Dolar', 'USD');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '2015-07-24 15:35:57', '2015-07-24 15:35:57', 'Actividade Aceite');

-- ----------------------------
-- Table structure for `contact_points`
-- ----------------------------
DROP TABLE IF EXISTS `contact_points`;
CREATE TABLE `contact_points` (
  `cp_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `carrier` varchar(255) DEFAULT NULL,
  `prefixo` int(11) DEFAULT NULL,
  `counter` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g676m8gmw2iut4qvo4tgn1s7f` (`employee`),
  KEY `FK_52g2494f0fyqiq6d0dqbubljd` (`counter`),
  CONSTRAINT `FK_52g2494f0fyqiq6d0dqbubljd` FOREIGN KEY (`counter`) REFERENCES `counters` (`id`),
  CONSTRAINT `FK_g676m8gmw2iut4qvo4tgn1s7f` FOREIGN KEY (`employee`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_points
-- ----------------------------
INSERT INTO `contact_points` VALUES ('cell_phone', '1', '2015-07-28 14:20:11', '2015-07-28 14:20:11', '840665903', null, null, 'Vodacom', '258', '1', null);
INSERT INTO `contact_points` VALUES ('cell_phone', '2', '2015-08-03 12:09:34', '2015-08-03 12:09:34', '21345683', null, null, 'TDM', '0', '1', 'Fixo');

-- ----------------------------
-- Table structure for `counters`
-- ----------------------------
DROP TABLE IF EXISTS `counters`;
CREATE TABLE `counters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `conter_address` varchar(255) DEFAULT NULL,
  `conter_contact` int(11) DEFAULT NULL,
  `counter_nm` varchar(255) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `contactPoint` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m70pxj3k4u9phtttac0lr583k` (`bank_id`),
  KEY `FK_iw5wea59q0913dnkb6fn0hins` (`contactPoint`),
  CONSTRAINT `FK_iw5wea59q0913dnkb6fn0hins` FOREIGN KEY (`contactPoint`) REFERENCES `contact_points` (`id`),
  CONSTRAINT `FK_m70pxj3k4u9phtttac0lr583k` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counters
-- ----------------------------
INSERT INTO `counters` VALUES ('1', '2015-07-28 12:36:13', '2015-07-28 17:21:32', 'Alto Mae ', '840665', 'Tchamba', '2', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of criterias
-- ----------------------------
INSERT INTO `criterias` VALUES ('1', '2015-07-23 12:40:19', '2015-07-23 12:41:13', 'Satisfação Alta', 'Satisfação Alta', 'Criterio1', '0');
INSERT INTO `criterias` VALUES ('2', '2015-07-23 12:40:30', '2015-07-23 12:40:30', 'Satisfação Media', 'Satisfação Media', 'Criterio1', '0');
INSERT INTO `criterias` VALUES ('3', '2015-07-23 12:40:43', '2015-07-23 12:40:43', 'Satisfação Baixa', 'Satisfação Baixa', 'Criterio1', '0');
INSERT INTO `criterias` VALUES ('4', '2015-07-23 14:35:58', '2015-07-23 14:35:58', 'Satisfação Baixa', 'Satisfação Baixa', 'Criterio3', '0');
INSERT INTO `criterias` VALUES ('5', '2015-07-23 14:36:10', '2015-07-23 14:36:10', 'Satisfação Media', 'Satisfação Media', 'Criterio2', '0');
INSERT INTO `criterias` VALUES ('6', '2015-07-23 14:36:34', '2015-07-23 14:36:34', 'Satisfação Alta', 'Satisfação Alta', 'Criterio2', '0');
INSERT INTO `criterias` VALUES ('7', '2015-07-23 14:36:45', '2015-07-23 14:36:45', 'Satisfação Media', 'Satisfação Media', 'Criterio3', '0');
INSERT INTO `criterias` VALUES ('8', '2015-07-23 14:36:58', '2015-07-23 14:36:58', 'Satisfação Baixa', 'Satisfação Baixa', 'Criterio3', '0');
INSERT INTO `criterias` VALUES ('9', '2015-07-23 14:37:15', '2015-07-23 14:37:15', 'Satisfação Alta', 'Satisfação Alta', 'Criterio3', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of critical_areas
-- ----------------------------
INSERT INTO `critical_areas` VALUES ('1', '2015-07-23 12:31:25', '2015-07-23 12:31:25', '01', 'Ensino Aprendizagem');
INSERT INTO `critical_areas` VALUES ('2', '2015-07-23 12:31:36', '2015-07-23 12:31:36', '02', 'Investigação e Extensão');
INSERT INTO `critical_areas` VALUES ('3', '2015-07-23 12:31:47', '2015-07-23 12:31:47', '03', 'Recursos Humanos');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cycles
-- ----------------------------
INSERT INTO `cycles` VALUES ('1', '2015-07-23 13:54:53', '2015-07-23 14:29:37', '2015', 'Eaboração do plano de actividade e orcamento da UEM para 2016', '2015-12-31 13:52:53', '2015-08-01 13:52:45', 'Started', 'Eaboração do plano de actividade e orcamento da UEM para 2016', 'Normal');
INSERT INTO `cycles` VALUES ('2', '2015-07-25 12:05:11', '2015-07-25 12:05:11', '2017', 'Elaboracao de Propostas Financeiras', '2015-12-31 12:04:37', '2015-07-25 12:04:33', 'Stopped', 'Elaboracao de Propostas Financeiras', 'Normal');

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
INSERT INTO `cycle_organ` VALUES ('1', '2');
INSERT INTO `cycle_organ` VALUES ('2', '2');
INSERT INTO `cycle_organ` VALUES ('1', '3');
INSERT INTO `cycle_organ` VALUES ('2', '3');
INSERT INTO `cycle_organ` VALUES ('1', '4');
INSERT INTO `cycle_organ` VALUES ('2', '4');
INSERT INTO `cycle_organ` VALUES ('1', '5');
INSERT INTO `cycle_organ` VALUES ('2', '5');
INSERT INTO `cycle_organ` VALUES ('1', '6');
INSERT INTO `cycle_organ` VALUES ('2', '6');
INSERT INTO `cycle_organ` VALUES ('1', '7');
INSERT INTO `cycle_organ` VALUES ('2', '7');
INSERT INTO `cycle_organ` VALUES ('1', '8');
INSERT INTO `cycle_organ` VALUES ('2', '8');
INSERT INTO `cycle_organ` VALUES ('1', '9');
INSERT INTO `cycle_organ` VALUES ('2', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of errors
-- ----------------------------
INSERT INTO `errors` VALUES ('1', '2015-07-31 14:47:00', '2015-07-31 14:47:00', 'Replicated UUID is not allowed for class org.zkoss.zhtml.Table: tblBaseCal', '', 'class org.zkoss.zk.ui.UiException');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of files
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fonts
-- ----------------------------
INSERT INTO `fonts` VALUES ('1', '2015-07-23 12:33:49', '2015-07-23 12:33:49', '01', 'Credito');
INSERT INTO `fonts` VALUES ('2', '2015-07-23 12:33:59', '2015-07-23 12:33:59', '02', 'Doações');
INSERT INTO `fonts` VALUES ('3', '2015-07-23 12:34:10', '2015-07-23 12:34:10', '03', 'Orçamento do Estado');
INSERT INTO `fonts` VALUES ('4', '2015-07-23 12:34:21', '2015-07-23 12:34:21', '04', 'Receitas Proprias');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of helps
-- ----------------------------

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
  `sub_sector` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `subScriber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tj1rrwylr05k2q88n04ogkfpr` (`employee_code`),
  KEY `FK_ca4t2574r7hfjkbbf75siyokj` (`type_id`),
  KEY `FK_8q99frrr73nqsxeqr3n2o3gfu` (`image_id`),
  KEY `FK_nhtt6d8oehr10bbvvhc3c6k14` (`organ`),
  KEY `FK_nubkisqoxscymb9efnoiyamcx` (`profession`),
  KEY `FK_lmpqv190pyxnqmfd628yki76r` (`sector`),
  KEY `FK_7uiaa2dbu3f53j11nx96k0ol5` (`sub_sector`),
  KEY `FK_gkywvgfb98ve4vvyja58dl6ps` (`user_id`),
  KEY `FK_idfeir92oyftahx4jkbkm0nes` (`subScriber`),
  CONSTRAINT `FK_7uiaa2dbu3f53j11nx96k0ol5` FOREIGN KEY (`sub_sector`) REFERENCES `sub_sectors` (`id`),
  CONSTRAINT `FK_8q99frrr73nqsxeqr3n2o3gfu` FOREIGN KEY (`image_id`) REFERENCES `files` (`id`),
  CONSTRAINT `FK_ca4t2574r7hfjkbbf75siyokj` FOREIGN KEY (`type_id`) REFERENCES `identity_document_types` (`id`),
  CONSTRAINT `FK_gkywvgfb98ve4vvyja58dl6ps` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_idfeir92oyftahx4jkbkm0nes` FOREIGN KEY (`subScriber`) REFERENCES `subscribers` (`id`),
  CONSTRAINT `FK_lmpqv190pyxnqmfd628yki76r` FOREIGN KEY (`sector`) REFERENCES `sectors` (`id`),
  CONSTRAINT `FK_nhtt6d8oehr10bbvvhc3c6k14` FOREIGN KEY (`organ`) REFERENCES `organs` (`id`),
  CONSTRAINT `FK_nubkisqoxscymb9efnoiyamcx` FOREIGN KEY (`profession`) REFERENCES `profession` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of individuals
-- ----------------------------
INSERT INTO `individuals` VALUES ('Employee', '1', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'High Level', 'CIUEM', null, 'Male', null, 'Admin', null, null, 'Admin', null, '0', '566e994c-5c9f-473d-a90c-57c3a1234100', null, null, null, null, '1', null, null, '1', null);
INSERT INTO `individuals` VALUES ('Employee', '2', '2015-07-23 12:53:45', '2015-07-23 12:53:45', 'Superior', 'Maputo', '1982-05-15', 'Masculino', null, 'Mandra', 'Casado', null, 'Laye Titos ', null, '0', '4b316933-27a6-4990-850b-b810eacedec3', null, null, null, '3', '1', '5', null, null, null);
INSERT INTO `individuals` VALUES ('Employee', '3', '2015-07-23 12:58:37', '2015-07-23 13:29:17', 'Superior', 'Matola', '2015-07-13', 'Femenino', null, 'Zamba', 'Divorciado', null, 'Joana ', null, '0', '3445dcde-52e0-43f2-b4c4-85ccf1247fb7', null, null, null, '2', '8', '3', null, '3', null);
INSERT INTO `individuals` VALUES ('Employee', '4', '2015-07-23 12:59:28', '2015-07-23 12:59:28', 'Superior', 'Matola', '2015-07-02', 'Femenino', null, 'Daniel', 'Solteiro', null, 'Josefina ', null, '0', '9237057b-fb31-4a12-8ba5-a7a6f015782a', null, null, null, '2', '3', '4', null, null, null);
INSERT INTO `individuals` VALUES ('Employee', '5', '2015-07-23 13:00:23', '2015-07-23 13:00:23', 'Superior', 'Maputo', '2015-07-02', 'Femenino', null, 'Maqueque', 'Solteiro', null, 'Francina ', null, '0', 'a57f3289-535d-47af-a998-4a547eb2b73d', null, null, null, '3', '4', '5', null, null, null);
INSERT INTO `individuals` VALUES ('Employee', '6', '2015-07-23 13:01:31', '2015-07-23 13:44:05', 'Superior', 'Maputo', '2015-07-09', 'Femenino', null, 'Jawana', 'Casado', null, 'Filomena ', null, '0', 'fcb2c029-bf5e-4f29-a27b-ae8aa28bc6a5', null, null, null, '2', '7', '4', null, '10', null);
INSERT INTO `individuals` VALUES ('Employee', '7', '2015-07-23 13:02:39', '2015-07-23 13:45:55', 'Superior', 'Maputo', '2015-07-02', 'Masculino', null, 'Notico', 'Solteiro', null, 'Aderito ', null, '0', 'd31a1b64-2759-4158-8520-9255aba04ca0', null, null, null, '2', '8', '3', null, '11', null);
INSERT INTO `individuals` VALUES ('Employee', '8', '2015-07-23 13:04:02', '2015-07-23 13:32:39', 'Superior', 'Maputo', '2015-07-08', 'Masculino', null, 'Munguanaze', 'Casado', null, 'Marcelo ', null, '0', 'ec11050f-f65d-4c54-a012-d024027f9899', null, null, null, '1', '9', '1', null, '4', null);
INSERT INTO `individuals` VALUES ('Employee', '9', '2015-07-23 13:04:52', '2015-07-23 13:41:46', 'Superior', 'Maputo', '2015-07-01', 'Femenino', null, 'Pedro', 'Solteiro', null, 'Lina ', null, '0', '132d5ffb-c1f4-4d2e-b91f-4a29814c768a', null, null, null, '1', '8', '1', '1', '9', null);
INSERT INTO `individuals` VALUES ('Employee', '10', '2015-07-23 13:05:37', '2015-07-23 13:05:37', 'Superior', 'Maputo', '2015-07-21', 'Femenino', null, 'Sacama', 'Solteiro', null, 'Julia ', null, '0', '5273869c-7b86-4488-b812-33829c3a88e0', null, null, null, '1', '7', '1', '1', null, null);
INSERT INTO `individuals` VALUES ('Employee', '11', '2015-07-23 13:06:36', '2015-07-23 13:38:57', 'Superior', 'Maputo', '2015-07-13', 'Femenino', null, 'Navalha', 'Casado', null, 'Marta Chico ', null, '0', '1428c37e-4bf3-4d3d-9956-9e407e68b630', null, null, null, '1', '4', '2', null, '7', null);
INSERT INTO `individuals` VALUES ('Employee', '12', '2015-07-23 13:07:21', '2015-07-23 20:03:49', 'Superior', 'Maputo', '2015-07-09', 'Femenino', null, 'Layina', 'Casado', null, 'Nkoleka ', null, '0', '6ac65d69-8c10-460b-9659-7919d4a84b7f', null, null, null, '2', '9', '3', null, '12', null);
INSERT INTO `individuals` VALUES ('Employee', '13', '2015-07-23 13:08:10', '2015-07-23 13:08:10', 'Superior', 'Maputo', '2015-07-07', 'Masculino', null, 'Castanheira', 'Solteiro', null, 'Silvano ', null, '0', 'c4472e68-2a8e-493c-81fb-a3afd6c7d15d', null, null, null, '1', '8', '1', '1', null, null);
INSERT INTO `individuals` VALUES ('Employee', '14', '2015-07-23 13:09:07', '2015-07-23 13:26:22', 'Superior', 'Maputo', '2015-07-16', null, null, 'Mondlane', 'Casado', null, 'Avelino I. ', null, '0', '4afd0469-8447-4d51-8941-8fc49584790c', null, null, null, '1', '7', '2', null, '2', null);
INSERT INTO `individuals` VALUES ('Employee', '15', '2015-07-23 13:12:39', '2015-07-23 13:33:34', 'Superior', 'Maputo', '2015-07-10', 'Femenino', null, 'Gove', 'Casado', null, 'Yolanda', null, '0', '780621f8-f99f-4709-83e6-8b022c05ae8e', null, null, null, '1', '10', '1', '1', '5', null);
INSERT INTO `individuals` VALUES ('Employee', '16', '2015-07-23 13:15:47', '2015-07-23 13:34:08', 'Superior', 'Matola', '2015-07-08', 'Masculino', null, 'Maposse', 'Solteiro', null, 'Eusebio Jose ', null, '0', 'c3f0c6a7-2b10-4fb4-ac56-dee0ce34de2b', null, null, null, '1', '2', '1', '2', '6', null);
INSERT INTO `individuals` VALUES ('Employee', '17', '2015-07-23 13:16:47', '2015-07-23 13:40:32', 'Superior', 'Maputo', '2015-07-21', 'Masculino', null, 'Jossias', 'Solteiro', null, 'Helton ', null, '0', 'dcf6389d-2d0e-4a82-a76e-115542cc0f78', null, null, null, '1', '8', '1', '1', '8', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('1', '2015-07-23 10:51:06', '2015-07-23 10:51:06', 'Registou novo critério: BIM', '1');
INSERT INTO `logs` VALUES ('2', '2015-07-23 10:51:28', '2015-07-23 10:51:28', 'Registou novo critério: BCI', '1');
INSERT INTO `logs` VALUES ('3', '2015-07-23 10:52:52', '2015-07-23 10:52:52', 'Registou nova Profissao: Programador', '1');
INSERT INTO `logs` VALUES ('4', '2015-07-23 11:36:15', '2015-07-23 11:36:15', 'Actualizou os dados do Banco: null', '1');
INSERT INTO `logs` VALUES ('5', '2015-07-23 11:36:28', '2015-07-23 11:36:28', 'Actualizou os dados do Banco: null', '1');
INSERT INTO `logs` VALUES ('6', '2015-07-23 11:43:23', '2015-07-23 11:43:23', 'Actualizou os dados do Banco: null', '1');
INSERT INTO `logs` VALUES ('7', '2015-07-23 11:43:28', '2015-07-23 11:43:28', 'Actualizou os dados do Banco: null', '1');
INSERT INTO `logs` VALUES ('8', '2015-07-23 12:19:26', '2015-07-23 12:19:26', 'Registou novo Tipo de Orgão: Centros e Museus', '1');
INSERT INTO `logs` VALUES ('9', '2015-07-23 12:19:36', '2015-07-23 12:19:36', 'Registou novo Tipo de Orgão: Faculdades e Escolas', '1');
INSERT INTO `logs` VALUES ('10', '2015-07-23 12:19:49', '2015-07-23 12:19:49', 'Registou novo Tipo de Orgão: Administrativos', '1');
INSERT INTO `logs` VALUES ('11', '2015-07-23 12:20:08', '2015-07-23 12:20:08', 'Registou novo Orgão: Centro de Informatica da UEM', '1');
INSERT INTO `logs` VALUES ('12', '2015-07-23 12:20:31', '2015-07-23 12:20:31', 'Registou novo Orgão: Gabinete de Planificacao da UEM', '1');
INSERT INTO `logs` VALUES ('13', '2015-07-23 12:20:47', '2015-07-23 12:20:47', 'Registou novo Orgão: Direcao Pedagogica', '1');
INSERT INTO `logs` VALUES ('14', '2015-07-23 12:21:02', '2015-07-23 12:21:02', 'Registou novo Orgão: Faculdade de Ciencias', '1');
INSERT INTO `logs` VALUES ('15', '2015-07-23 12:28:01', '2015-07-23 12:28:01', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('16', '2015-07-23 12:29:05', '2015-07-23 12:29:05', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('17', '2015-07-23 12:29:30', '2015-07-23 12:29:30', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('18', '2015-07-23 12:30:09', '2015-07-23 12:30:09', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('19', '2015-07-23 12:30:26', '2015-07-23 12:30:26', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('20', '2015-07-23 12:30:41', '2015-07-23 12:30:41', 'Registou novo subsector: null ao sector: null', '1');
INSERT INTO `logs` VALUES ('21', '2015-07-23 12:31:25', '2015-07-23 12:31:25', 'Registou nova Área de Gestão: Ensino Aprendizagem', '1');
INSERT INTO `logs` VALUES ('22', '2015-07-23 12:31:37', '2015-07-23 12:31:37', 'Registou nova Área de Gestão: Investigação e Extensão', '1');
INSERT INTO `logs` VALUES ('23', '2015-07-23 12:31:47', '2015-07-23 12:31:47', 'Registou nova Área de Gestão: Recursos Humanos', '1');
INSERT INTO `logs` VALUES ('24', '2015-07-23 12:32:40', '2015-07-23 12:32:40', 'Registou nova Fase: Preparação do Processo de Planificação', '1');
INSERT INTO `logs` VALUES ('25', '2015-07-23 12:32:50', '2015-07-23 12:32:50', 'Registou nova Fase: Elaborar e Submeter o Plano', '1');
INSERT INTO `logs` VALUES ('26', '2015-07-23 12:33:00', '2015-07-23 12:33:00', 'Registou nova Fase: Executar o Plano', '1');
INSERT INTO `logs` VALUES ('27', '2015-07-23 12:33:11', '2015-07-23 12:33:11', 'Registou nova Fase: Elaborar e Submeter Relatorio de Actividades', '1');
INSERT INTO `logs` VALUES ('28', '2015-07-23 12:33:33', '2015-07-23 12:33:33', 'Registou nova Fase: Credito', '1');
INSERT INTO `logs` VALUES ('29', '2015-07-23 12:33:49', '2015-07-23 12:33:49', 'Reportou nova Fonte de Orçamento: Credito', '1');
INSERT INTO `logs` VALUES ('30', '2015-07-23 12:33:59', '2015-07-23 12:33:59', 'Reportou nova Fonte de Orçamento: Doações', '1');
INSERT INTO `logs` VALUES ('31', '2015-07-23 12:34:10', '2015-07-23 12:34:10', 'Reportou nova Fonte de Orçamento: Orçamento do Estado', '1');
INSERT INTO `logs` VALUES ('32', '2015-07-23 12:34:22', '2015-07-23 12:34:22', 'Reportou nova Fonte de Orçamento: Receitas Proprias', '1');
INSERT INTO `logs` VALUES ('33', '2015-07-23 12:34:51', '2015-07-23 12:34:51', 'Registou novo Objectivo Estratégico: Promover a eficiência administrativa e de gestão, de comunicação e marketing', '1');
INSERT INTO `logs` VALUES ('34', '2015-07-23 12:35:09', '2015-07-23 12:35:09', 'Registou novo Objectivo Estratégico: Conceber, implementar e monitorar a reforma académica tendo em vista a integração regional.', '1');
INSERT INTO `logs` VALUES ('35', '2015-07-23 12:35:32', '2015-07-23 12:35:32', 'Registou novo Objectivo Estratégico: Promover o acesso equitativo', '1');
INSERT INTO `logs` VALUES ('36', '2015-07-23 12:35:56', '2015-07-23 12:35:56', 'Registou novo Objectivo Estratégico: Assegurar excelência e qualidade na docência', '1');
INSERT INTO `logs` VALUES ('37', '2015-07-23 12:36:12', '2015-07-23 12:36:12', 'Registou novo Objectivo Estratégico: Assegurar excelência e qualidade nas actividades de investigação e deextensão', '1');
INSERT INTO `logs` VALUES ('38', '2015-07-23 12:36:47', '2015-07-23 12:36:47', 'Registou novo Objectivo Estratégico: Desenvolver a Planta Física', '1');
INSERT INTO `logs` VALUES ('39', '2015-07-23 12:37:03', '2015-07-23 12:37:03', 'Registou novo Objectivo Estratégico: Desenvolver e valorizar os recursos humanos', '1');
INSERT INTO `logs` VALUES ('40', '2015-07-23 12:37:22', '2015-07-23 12:37:22', 'Registou novo Objectivo Estratégico: Desenvolver e fortalecer a cooperação nacional, regional e internacional', '1');
INSERT INTO `logs` VALUES ('41', '2015-07-23 12:37:41', '2015-07-23 12:37:41', 'Registou nova Profissao: Contabilista', '1');
INSERT INTO `logs` VALUES ('42', '2015-07-23 12:39:39', '2015-07-23 12:39:39', 'Registou nova Profissao: Gestor', '1');
INSERT INTO `logs` VALUES ('43', '2015-07-23 12:40:19', '2015-07-23 12:40:19', 'Registou novo critério: Satisfação Baixa', '1');
INSERT INTO `logs` VALUES ('44', '2015-07-23 12:40:30', '2015-07-23 12:40:30', 'Registou novo critério: Satisfação Media', '1');
INSERT INTO `logs` VALUES ('45', '2015-07-23 12:40:43', '2015-07-23 12:40:43', 'Registou novo critério: Satisfação Baixa', '1');
INSERT INTO `logs` VALUES ('46', '2015-07-23 12:41:13', '2015-07-23 12:41:13', 'Actualizou o Criterio: Satisfação Alta', '1');
INSERT INTO `logs` VALUES ('47', '2015-07-23 12:46:28', '2015-07-23 12:46:28', 'Actualizou o Orgão: Centro de Informática da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('48', '2015-07-23 12:46:59', '2015-07-23 12:46:59', 'Actualizou o Orgão: Gabinete de Planificação da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('49', '2015-07-23 12:47:26', '2015-07-23 12:47:26', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('50', '2015-07-23 12:47:40', '2015-07-23 12:47:40', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('51', '2015-07-23 12:47:54', '2015-07-23 12:47:54', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('52', '2015-07-23 12:48:09', '2015-07-23 12:48:09', 'Registou novo sector: null', '1');
INSERT INTO `logs` VALUES ('53', '2015-07-23 12:48:40', '2015-07-23 12:48:40', 'Actualizou novo sector: null', '1');
INSERT INTO `logs` VALUES ('54', '2015-07-23 12:48:57', '2015-07-23 12:48:57', 'Actualizou novo sector: null', '1');
INSERT INTO `logs` VALUES ('55', '2015-07-23 12:49:12', '2015-07-23 12:49:12', 'Actualizou novo sector: null', '1');
INSERT INTO `logs` VALUES ('56', '2015-07-23 12:53:46', '2015-07-23 12:53:46', 'Registou novo funcionário: Laye Titos  Mandra', '1');
INSERT INTO `logs` VALUES ('57', '2015-07-23 12:54:20', '2015-07-23 12:54:20', 'Registou nova Profissao: Analista de Dados', '1');
INSERT INTO `logs` VALUES ('58', '2015-07-23 12:54:37', '2015-07-23 12:54:37', 'Registou nova Profissao: Financeiro', '1');
INSERT INTO `logs` VALUES ('59', '2015-07-23 12:55:04', '2015-07-23 12:55:04', 'Registou nova Profissao: Engeheiro de Sistemas', '1');
INSERT INTO `logs` VALUES ('60', '2015-07-23 12:56:56', '2015-07-23 12:56:56', 'Registou nova Profissao: Analista de Sistemas', '1');
INSERT INTO `logs` VALUES ('61', '2015-07-23 12:57:16', '2015-07-23 12:57:16', 'Registou nova Profissao: Gestor de Projectos', '1');
INSERT INTO `logs` VALUES ('62', '2015-07-23 12:58:37', '2015-07-23 12:58:37', 'Registou novo funcionário: Joana  Zamba', '1');
INSERT INTO `logs` VALUES ('63', '2015-07-23 12:59:28', '2015-07-23 12:59:28', 'Registou novo funcionário: Josefina  Daniel', '1');
INSERT INTO `logs` VALUES ('64', '2015-07-23 13:00:23', '2015-07-23 13:00:23', 'Registou novo funcionário: Francina  Maqueque', '1');
INSERT INTO `logs` VALUES ('65', '2015-07-23 13:01:31', '2015-07-23 13:01:31', 'Registou novo funcionário: Filomena  Jawana', '1');
INSERT INTO `logs` VALUES ('66', '2015-07-23 13:02:40', '2015-07-23 13:02:40', 'Registou novo funcionário: Aderito  Notico', '1');
INSERT INTO `logs` VALUES ('67', '2015-07-23 13:04:02', '2015-07-23 13:04:02', 'Registou novo funcionário: Marcelo  Munguanaze', '1');
INSERT INTO `logs` VALUES ('68', '2015-07-23 13:04:53', '2015-07-23 13:04:53', 'Registou novo funcionário: Lina  Pedro', '1');
INSERT INTO `logs` VALUES ('69', '2015-07-23 13:05:37', '2015-07-23 13:05:37', 'Registou novo funcionário: Julia  Sacama', '1');
INSERT INTO `logs` VALUES ('70', '2015-07-23 13:06:36', '2015-07-23 13:06:36', 'Registou novo funcionário: Marta Chico  Navalha', '1');
INSERT INTO `logs` VALUES ('71', '2015-07-23 13:07:21', '2015-07-23 13:07:21', 'Registou novo funcionário: Nkoleka  Layina', '1');
INSERT INTO `logs` VALUES ('72', '2015-07-23 13:08:10', '2015-07-23 13:08:10', 'Registou novo funcionário: Silvano  Castanheira', '1');
INSERT INTO `logs` VALUES ('73', '2015-07-23 13:09:07', '2015-07-23 13:09:07', 'Registou novo funcionário: Avelino I.  Mondlane', '1');
INSERT INTO `logs` VALUES ('74', '2015-07-23 13:11:54', '2015-07-23 13:11:54', 'Registou nova Profissao: Implantador', '1');
INSERT INTO `logs` VALUES ('75', '2015-07-23 13:12:39', '2015-07-23 13:12:39', 'Registou novo funcionário: Yolanda Gove', '1');
INSERT INTO `logs` VALUES ('76', '2015-07-23 13:15:47', '2015-07-23 13:15:47', 'Registou novo funcionário: Eusebio Jose  Maposse', '1');
INSERT INTO `logs` VALUES ('77', '2015-07-23 13:16:47', '2015-07-23 13:16:47', 'Registou novo funcionário: Helton  Jossias', '1');
INSERT INTO `logs` VALUES ('78', '2015-07-23 13:17:59', '2015-07-23 13:17:59', 'Registou novo Perfil de Utilizadores: Funcionario', '1');
INSERT INTO `logs` VALUES ('79', '2015-07-23 13:18:23', '2015-07-23 13:18:23', 'Registou novo Perfil de Utilizadores: Sector', '1');
INSERT INTO `logs` VALUES ('80', '2015-07-23 13:19:03', '2015-07-23 13:19:03', 'Registou novo Perfil de Utilizadores: Orgão', '1');
INSERT INTO `logs` VALUES ('81', '2015-07-23 13:20:24', '2015-07-23 13:20:24', 'Registou novo Perfil de Utilizadores: Planificão', '1');
INSERT INTO `logs` VALUES ('82', '2015-07-23 13:21:49', '2015-07-23 13:21:49', 'Registou novo Perfil de Utilizadores: Financas', '1');
INSERT INTO `logs` VALUES ('83', '2015-07-23 13:22:24', '2015-07-23 13:22:24', 'Registou novo Orgão: Direccao Cientifica', '1');
INSERT INTO `logs` VALUES ('84', '2015-07-23 13:22:46', '2015-07-23 13:22:46', 'Registou novo Orgão: Centro de Análise de Políticas', '1');
INSERT INTO `logs` VALUES ('85', '2015-07-23 13:23:16', '2015-07-23 13:23:16', 'Registou novo Orgão: Escola Superior de Ciencias de Desporto', '1');
INSERT INTO `logs` VALUES ('86', '2015-07-23 13:23:41', '2015-07-23 13:23:41', 'Registou novo Orgão: Faculdade Arquitectura e Planeamento Fisico', '1');
INSERT INTO `logs` VALUES ('87', '2015-07-23 13:24:43', '2015-07-23 13:24:43', 'Registou novo Orgão: Faculdade de Engenharia', '1');
INSERT INTO `logs` VALUES ('88', '2015-07-23 13:26:22', '2015-07-23 13:26:22', 'Registou novo Utilizador: mondlane@uem.mz', '1');
INSERT INTO `logs` VALUES ('89', '2015-07-23 13:26:36', '2015-07-23 13:26:36', 'Atribuiu novo responsável (Avelino I.  Mondlane) ao Orgão: Centro de Informática da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('90', '2015-07-23 13:29:17', '2015-07-23 13:29:17', 'Registou novo Utilizador: joanazamba@gmail.com', '1');
INSERT INTO `logs` VALUES ('91', '2015-07-23 13:29:33', '2015-07-23 13:29:33', 'Atribuiu novo responsável (Joana  Zamba) ao Orgão: Gabinete de Planificação da Universidade Eduardo Mondlane', '1');
INSERT INTO `logs` VALUES ('92', '2015-07-23 13:32:39', '2015-07-23 13:32:39', 'Registou novo Utilizador: marcelo@uem.com', '1');
INSERT INTO `logs` VALUES ('93', '2015-07-23 13:33:34', '2015-07-23 13:33:34', 'Registou novo Utilizador: yolanda@gmail.com', '1');
INSERT INTO `logs` VALUES ('94', '2015-07-23 13:34:08', '2015-07-23 13:34:08', 'Registou novo Utilizador: eusebio@gmail.com', '1');
INSERT INTO `logs` VALUES ('95', '2015-07-23 13:38:57', '2015-07-23 13:38:57', 'Registou novo Utilizador: marta@zebra.uem.mz', '1');
INSERT INTO `logs` VALUES ('96', '2015-07-23 13:40:32', '2015-07-23 13:40:32', 'Registou novo Utilizador: helton@uem.com', '1');
INSERT INTO `logs` VALUES ('97', '2015-07-23 13:41:47', '2015-07-23 13:41:47', 'Registou novo Utilizador: lina@uem.com', '1');
INSERT INTO `logs` VALUES ('98', '2015-07-23 13:44:06', '2015-07-23 13:44:06', 'Registou novo Utilizador: filo.jawana@uem.mz', '1');
INSERT INTO `logs` VALUES ('99', '2015-07-23 13:45:55', '2015-07-23 13:45:55', 'Registou novo Utilizador: aderito.notico@gmail.com', '1');
INSERT INTO `logs` VALUES ('100', '2015-07-23 13:54:53', '2015-07-23 13:54:53', 'Registou um novo Ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016', '3');
INSERT INTO `logs` VALUES ('101', '2015-07-23 13:57:21', '2015-07-23 13:57:21', 'Adicionou a fase: Preparação do Processo de Planificação ao Ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016', '3');
INSERT INTO `logs` VALUES ('102', '2015-07-23 13:57:42', '2015-07-23 13:57:42', 'Adicionou a fase: Elaborar e Submeter o Plano ao Ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016', '3');
INSERT INTO `logs` VALUES ('103', '2015-07-23 13:58:06', '2015-07-23 13:58:06', 'Adicionou a fase: Executar o Plano ao Ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016', '3');
INSERT INTO `logs` VALUES ('104', '2015-07-23 13:58:35', '2015-07-23 13:58:35', 'Adicionou a fase: Elaborar e Submeter Relatorio de Actividades ao Ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016', '3');
INSERT INTO `logs` VALUES ('105', '2015-07-23 14:29:38', '2015-07-23 14:29:38', 'Mudou o estado do ciclo: 2015 - Eaboração do plano de actividade e orcamento da UEM para 2016 para: Started', '3');
INSERT INTO `logs` VALUES ('106', '2015-07-23 14:35:58', '2015-07-23 14:35:58', 'Registou novo critério: Satisfação Baixa', '1');
INSERT INTO `logs` VALUES ('107', '2015-07-23 14:36:11', '2015-07-23 14:36:11', 'Registou novo critério: Satisfação Media', '1');
INSERT INTO `logs` VALUES ('108', '2015-07-23 14:36:34', '2015-07-23 14:36:34', 'Registou novo critério: Satisfação Alta', '1');
INSERT INTO `logs` VALUES ('109', '2015-07-23 14:36:46', '2015-07-23 14:36:46', 'Registou novo critério: Satisfação Media', '1');
INSERT INTO `logs` VALUES ('110', '2015-07-23 14:36:58', '2015-07-23 14:36:58', 'Registou novo critério: Satisfação Baixa', '1');
INSERT INTO `logs` VALUES ('111', '2015-07-23 14:37:15', '2015-07-23 14:37:15', 'Registou novo critério: Satisfação Alta', '1');
INSERT INTO `logs` VALUES ('112', '2015-07-23 14:38:31', '2015-07-23 14:38:31', 'Registou uma nova actividade: Elaborar o Plano de Actividades ', '3');
INSERT INTO `logs` VALUES ('113', '2015-07-23 14:39:20', '2015-07-23 14:39:20', 'Registou nova Base Calculo: 30 Cadeiras', '3');
INSERT INTO `logs` VALUES ('114', '2015-07-23 14:40:33', '2015-07-23 14:40:33', 'Alocou a actividade: Elaborar o Documento  ao sector: Departamento de Planificação, Monitoria e Estudo', '3');
INSERT INTO `logs` VALUES ('115', '2015-07-23 14:41:09', '2015-07-23 14:41:09', 'Alocou a actividade: Fazer Estudo de Viabilidade ao sector: Departamento de Estatística e Informação', '3');
INSERT INTO `logs` VALUES ('116', '2015-07-23 14:49:51', '2015-07-23 14:49:51', 'Registou uma nova actividade: Gestao e Orcamentos', '3');
INSERT INTO `logs` VALUES ('117', '2015-07-23 14:50:35', '2015-07-23 14:50:35', 'Registou uma nova actividade: Qualidade da Docencia', '3');
INSERT INTO `logs` VALUES ('118', '2015-07-23 14:51:18', '2015-07-23 14:51:18', 'Registou nova Base Calculo: 30 Cadeiras', '3');
INSERT INTO `logs` VALUES ('119', '2015-07-23 14:52:26', '2015-07-23 14:52:26', 'Registou nova Base Calculo: 300 Bilhetes', '3');
INSERT INTO `logs` VALUES ('120', '2015-07-23 14:54:10', '2015-07-23 14:54:10', 'Registou nova Base Calculo: 4000 Entrevistas', '3');
INSERT INTO `logs` VALUES ('121', '2015-07-23 14:55:04', '2015-07-23 14:55:04', 'Registou nova Base Calculo: 400 Filas', '3');
INSERT INTO `logs` VALUES ('122', '2015-07-23 16:08:16', '2015-07-23 16:08:16', 'Reportou \'null\' na fase: Preparação do Processo de Planificação', '3');
INSERT INTO `logs` VALUES ('123', '2015-07-23 18:25:43', '2015-07-23 18:25:43', 'Registou nova Base Calculo: 50 Cadeiras Plasticas ', '3');
INSERT INTO `logs` VALUES ('124', '2015-07-23 19:26:14', '2015-07-23 19:26:14', 'Registou uma nova actividade: Montar Servidores', '2');
INSERT INTO `logs` VALUES ('125', '2015-07-23 19:26:33', '2015-07-23 19:26:33', 'Registou uma nova actividade: Gestao de Paginas', '2');
INSERT INTO `logs` VALUES ('126', '2015-07-23 19:27:00', '2015-07-23 19:27:00', 'Registou uma nova actividade: Gestao de Sistemas', '2');
INSERT INTO `logs` VALUES ('127', '2015-07-23 19:28:18', '2015-07-23 19:28:18', 'Registou nova Base Calculo: 200 Bilhetes Azgo', '2');
INSERT INTO `logs` VALUES ('128', '2015-07-23 19:29:15', '2015-07-23 19:29:15', 'Registou nova Base Calculo: 400 Resmas', '2');
INSERT INTO `logs` VALUES ('129', '2015-07-23 20:03:49', '2015-07-23 20:03:49', 'Registou novo Utilizador: nkolekalayina@gmail.com', '1');
INSERT INTO `logs` VALUES ('130', '2015-07-23 21:48:41', '2015-07-23 21:48:41', 'Alocou a actividade: Contruir a Equipe ao sector: Departamento de Sistemas e Multimédia', '2');
INSERT INTO `logs` VALUES ('131', '2015-07-23 22:08:46', '2015-07-23 22:08:46', 'Alocou a actividade: Codificar Aplicacoes de Software ao funcionário: Eusebio Jose  Maposse', '4');
INSERT INTO `logs` VALUES ('132', '2015-07-23 23:45:00', '2015-07-23 23:45:00', 'Reportou \'Fase Terminada com Sucessso\' na fase: Elaborar e Submeter o Plano', '3');
INSERT INTO `logs` VALUES ('133', '2015-07-24 15:35:58', '2015-07-24 15:35:58', 'Integrou a Acção: Garantir Legalidade', '3');
INSERT INTO `logs` VALUES ('134', '2015-07-25 12:05:12', '2015-07-25 12:05:12', 'Registou um novo Ciclo: 2017 - Elaboracao de Propostas Financeiras', '3');
INSERT INTO `logs` VALUES ('135', '2015-07-27 18:26:32', '2015-07-27 18:26:32', 'Registou novo Tipo de Conta: Corrente', '1');
INSERT INTO `logs` VALUES ('136', '2015-07-28 12:36:13', '2015-07-28 12:36:13', 'Registou : Tchamba', '1');
INSERT INTO `logs` VALUES ('137', '2015-07-28 16:56:55', '2015-07-28 16:56:55', 'Reportou nova Fonte de Orçamento: USD', '1');
INSERT INTO `logs` VALUES ('138', '2015-07-28 17:21:32', '2015-07-28 17:21:32', 'Actualizou os dados do Balcao: Tchamba', '1');
INSERT INTO `logs` VALUES ('139', '2015-07-28 18:57:16', '2015-07-28 18:57:16', 'Registou nova Finalidade: Gestao Orcamental', '1');
INSERT INTO `logs` VALUES ('140', '2015-07-28 19:11:23', '2015-07-28 19:11:23', 'Actualizou os dados do Banco: null', '1');
INSERT INTO `logs` VALUES ('141', '2015-07-30 14:03:19', '2015-07-30 14:03:19', 'Removeu permissão do perfil: Admin', '1');
INSERT INTO `logs` VALUES ('142', '2015-07-30 14:03:41', '2015-07-30 14:03:41', 'Removeu perfil do utilizador: eusebio@gmail.com', '1');
INSERT INTO `logs` VALUES ('143', '2015-07-30 14:03:51', '2015-07-30 14:03:51', 'Adicionou novos perfis ao utilizador: eusebio@gmail.com', '1');
INSERT INTO `logs` VALUES ('144', '2015-07-30 14:07:08', '2015-07-30 14:07:08', 'Removeu permissão do perfil: Financas', '1');
INSERT INTO `logs` VALUES ('145', '2015-07-30 14:07:12', '2015-07-30 14:07:12', 'Removeu permissão do perfil: Financas', '1');
INSERT INTO `logs` VALUES ('146', '2015-07-30 14:07:15', '2015-07-30 14:07:15', 'Removeu permissão do perfil: Financas', '1');
INSERT INTO `logs` VALUES ('147', '2015-07-31 14:47:00', '2015-07-31 14:47:00', 'Reportou um erro: Replicated UUID is not allowed for class org.zkoss.zhtml.Table: tblBaseCal', '6');
INSERT INTO `logs` VALUES ('148', '2015-08-02 15:11:37', '2015-08-02 15:11:37', 'Registou novo Tipo de Conta: Conjunta', '6');

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
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h85luapamso17us506j999mcd` (`employee_id`),
  CONSTRAINT `FK_h85luapamso17us506j999mcd` FOREIGN KEY (`employee_id`) REFERENCES `individuals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notifications
-- ----------------------------
INSERT INTO `notifications` VALUES ('1', '2015-07-25 12:05:12', '2015-07-25 12:05:12', 'Eaboração do plano de actividade e orcamento da UEM para 2016', 'CIUEM', '', null);

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
  `responsible_id` bigint(20) DEFAULT NULL,
  `plan` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7n49841xhasss2pr2a5qahijg` (`organType`),
  KEY `FK_n20rl0p7xnoarlgm1hjdnk3te` (`responsible_id`),
  KEY `FK_o391ebf6l1kd1qvbxog770j1k` (`plan`),
  CONSTRAINT `FK_7n49841xhasss2pr2a5qahijg` FOREIGN KEY (`organType`) REFERENCES `organ_types` (`id`),
  CONSTRAINT `FK_n20rl0p7xnoarlgm1hjdnk3te` FOREIGN KEY (`responsible_id`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_o391ebf6l1kd1qvbxog770j1k` FOREIGN KEY (`plan`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organs
-- ----------------------------
INSERT INTO `organs` VALUES ('1', '2015-07-23 12:20:08', '2015-07-23 13:26:36', null, 'Centro de Informática da Universidade Eduardo Mondlane', 'CIUEM', '1', '14', null);
INSERT INTO `organs` VALUES ('2', '2015-07-23 12:20:31', '2015-07-23 13:29:32', null, 'Gabinete de Planificação da Universidade Eduardo Mondlane', 'GP', '3', '3', null);
INSERT INTO `organs` VALUES ('3', '2015-07-23 12:20:47', '2015-07-23 12:20:47', null, 'Direcao Pedagogica', 'DP', '2', null, null);
INSERT INTO `organs` VALUES ('4', '2015-07-23 12:21:01', '2015-07-23 12:21:01', null, 'Faculdade de Ciencias', 'FC', '2', null, null);
INSERT INTO `organs` VALUES ('5', '2015-07-23 13:22:24', '2015-07-23 13:22:24', null, 'Direccao Cientifica', 'DC', '3', null, null);
INSERT INTO `organs` VALUES ('6', '2015-07-23 13:22:46', '2015-07-23 13:22:46', null, 'Centro de Análise de Políticas', 'CAP', '1', null, null);
INSERT INTO `organs` VALUES ('7', '2015-07-23 13:23:16', '2015-07-23 13:23:16', null, 'Escola Superior de Ciencias de Desporto', 'ESCD', '2', null, null);
INSERT INTO `organs` VALUES ('8', '2015-07-23 13:23:41', '2015-07-23 13:23:41', null, 'Faculdade Arquitectura e Planeamento Fisico', 'FAPF', '2', null, null);
INSERT INTO `organs` VALUES ('9', '2015-07-23 13:24:43', '2015-07-23 13:24:43', null, 'Faculdade de Engenharia', 'FE', '2', null, null);

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
INSERT INTO `organ_roles` VALUES ('1', '2015-07-23 07:25:32', '2015-07-23 07:25:32', 'Principal');
INSERT INTO `organ_roles` VALUES ('2', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'Secundario');

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
INSERT INTO `organ_types` VALUES ('1', '2015-07-23 12:19:26', '2015-07-23 12:19:26', null, 'CM', 'Centros e Museus');
INSERT INTO `organ_types` VALUES ('2', '2015-07-23 12:19:36', '2015-07-23 12:19:36', null, 'FE', 'Faculdades e Escolas');
INSERT INTO `organ_types` VALUES ('3', '2015-07-23 12:19:49', '2015-07-23 12:19:49', null, 'AD', 'Administrativos');

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
INSERT INTO `permissions` VALUES ('1', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá ver as execuções dos funcionários.', 'EMPLOYEE_EXECUTIONS');
INSERT INTO `permissions` VALUES ('2', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Orgãos, criação, actualização.', 'MANAGE_ORGANS');
INSERT INTO `permissions` VALUES ('3', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Funcionários, criação, actualização.', 'MANAGE_EMPLOYEES');
INSERT INTO `permissions` VALUES ('4', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Sectores, criação, actualização.', 'MANAGE_SECTORS');
INSERT INTO `permissions` VALUES ('5', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre as Áreas de Gestão, criação, actualização.', 'MANAGE_CRITICAL_AREAS');
INSERT INTO `permissions` VALUES ('6', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'Super utilizador com acesso as áreas de administração do sistema e outras configurações críticas do sistema.', 'IS_SUPER_ADMIN');
INSERT INTO `permissions` VALUES ('7', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Papeis dos órgãos intervenientes, criação, actualização.', 'MANAGE_ORGAN_ROLES');
INSERT INTO `permissions` VALUES ('8', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Critérios de Actividade, criação, actualização.', 'MANAGE_CRITERIAS');
INSERT INTO `permissions` VALUES ('9', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre as Fontes, criação, actualização.', 'MANAGE_SOURCES');
INSERT INTO `permissions` VALUES ('10', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá criar, configurar os ciclos, adicionar fases e respectivos períodos de execução.', 'MANAGE_CYCLES');
INSERT INTO `permissions` VALUES ('11', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá registar actividades, actualizar.', 'MANAGE_ACTIONS');
INSERT INTO `permissions` VALUES ('12', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá, alocar actividades ao sector.', 'SECTOR_ALLOCATIONS');
INSERT INTO `permissions` VALUES ('13', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Tipos de Orgãos, criação, actualização.', 'MANAGE_ORGAN_TYPES');
INSERT INTO `permissions` VALUES ('14', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá registar, actualizar actividades específicas.', 'MANAGE_SPECIFIED_ACTIONS');
INSERT INTO `permissions` VALUES ('15', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre as Fases, criação, actualização.', 'MANAGE_PHASES');
INSERT INTO `permissions` VALUES ('16', '2015-07-23 07:25:33', '2015-07-23 07:25:33', 'O utilizador poderá fazer todas as operações sobre os Objectivos Estratégicos, criação, actualização.', 'MANAGE_STRATEGIC_OBJECTIVES');
INSERT INTO `permissions` VALUES ('17', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'O utilizador poderá alocar actividades aos funcionários.', 'EMPLOYEE_ALLOCATIONS');
INSERT INTO `permissions` VALUES ('18', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'O utilizador poderá autenticar no sistema e ver alocações feitas a si pelo seu orgão ou sector.', 'IS_EMPLOYEE');
INSERT INTO `permissions` VALUES ('19', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'Essa permissão assumi o utilizador como funcionário do Gabinete de Planificação, com acesso a visualização de todos planos de actividades', 'PLANNING_OFFICE');
INSERT INTO `permissions` VALUES ('20', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'O utilizador poderá Realiza todas operacoes relacionadas com as Contas dos  Orgãos', 'MANEGE_ACCOUNT');
INSERT INTO `permissions` VALUES ('21', '2015-07-22 14:58:00', '2015-07-31 14:58:05', 'O  Utilizador poderá submeter todas as dificuldades que esta com o sistema', 'IS_HELPDESK');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phases
-- ----------------------------
INSERT INTO `phases` VALUES ('1', '2015-07-23 12:32:40', '2015-07-23 12:32:40', '01', 'Preparação do Processo de Planificação');
INSERT INTO `phases` VALUES ('2', '2015-07-23 12:32:50', '2015-07-23 12:32:50', '02', 'Elaborar e Submeter o Plano');
INSERT INTO `phases` VALUES ('3', '2015-07-23 12:33:00', '2015-07-23 12:33:00', '03', 'Executar o Plano');
INSERT INTO `phases` VALUES ('4', '2015-07-23 12:33:11', '2015-07-23 12:33:11', '04', 'Elaborar e Submeter Relatorio de Actividades');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phases_organ_cycles
-- ----------------------------
INSERT INTO `phases_organ_cycles` VALUES ('1', '2015-07-23 16:08:16', '2015-08-02 15:22:55', 'acepted', null, '2015-07-23 16:08:16', '25', '', null, '2', '1');
INSERT INTO `phases_organ_cycles` VALUES ('2', '2015-07-23 23:45:00', '2015-08-02 15:22:55', 'acepted', 'Fase Terminada com Sucessso', '2015-07-23 23:45:00', '25', '', null, '2', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phase_cycles
-- ----------------------------
INSERT INTO `phase_cycles` VALUES ('1', '2015-07-23 13:57:21', '2015-07-23 13:57:21', '0', '2015-08-30 00:00:00', '2015-08-01 00:00:00', '1', '1');
INSERT INTO `phase_cycles` VALUES ('2', '2015-07-23 13:57:42', '2015-07-23 13:57:42', '0', '2015-09-30 00:00:00', '2015-08-30 00:00:00', '1', '2');
INSERT INTO `phase_cycles` VALUES ('3', '2015-07-23 13:58:06', '2015-07-23 13:58:06', '0', '2015-12-05 00:00:00', '2015-10-01 00:00:00', '1', '3');
INSERT INTO `phase_cycles` VALUES ('4', '2015-07-23 13:58:34', '2015-07-23 13:58:34', '0', '2015-12-31 00:00:00', '2015-10-31 00:00:00', '1', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of priority_levels
-- ----------------------------
INSERT INTO `priority_levels` VALUES ('1', '2015-07-23 14:38:31', '2015-07-23 14:38:31', '2', '5', '7');
INSERT INTO `priority_levels` VALUES ('2', '2015-07-23 14:49:51', '2015-07-23 14:49:51', '1', '5', '4');
INSERT INTO `priority_levels` VALUES ('3', '2015-07-23 14:50:35', '2015-07-23 14:50:35', '2', '5', '4');
INSERT INTO `priority_levels` VALUES ('4', '2015-07-23 19:26:14', '2015-07-23 19:26:14', '1', '5', '4');
INSERT INTO `priority_levels` VALUES ('5', '2015-07-23 19:26:33', '2015-07-23 19:26:33', '2', '5', '7');
INSERT INTO `priority_levels` VALUES ('6', '2015-07-23 19:27:00', '2015-07-23 19:27:00', '1', '5', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES ('1', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'Administrador de Sistemas de Software', 'Adminstrador de Sistemas');
INSERT INTO `profession` VALUES ('2', '2015-07-23 10:52:52', '2015-07-23 10:52:52', 'Codifica aplicacoes de software', 'Programador');
INSERT INTO `profession` VALUES ('3', '2015-07-23 12:37:41', '2015-07-23 12:37:41', 'Tecnico de Contas', 'Contabilista');
INSERT INTO `profession` VALUES ('4', '2015-07-23 12:39:39', '2015-07-23 12:39:39', 'Gestor', 'Gestor');
INSERT INTO `profession` VALUES ('5', '2015-07-23 12:54:20', '2015-07-23 12:54:20', 'Analise de Dados', 'Analista de Dados');
INSERT INTO `profession` VALUES ('6', '2015-07-23 12:54:37', '2015-07-23 12:54:37', 'Financas', 'Financeiro');
INSERT INTO `profession` VALUES ('7', '2015-07-23 12:55:04', '2015-07-23 12:55:04', 'Engeheiro de Sistemas', 'Engeheiro de Sistemas');
INSERT INTO `profession` VALUES ('8', '2015-07-23 12:56:56', '2015-07-23 12:56:56', 'Analise de Sistemas', 'Analista de Sistemas');
INSERT INTO `profession` VALUES ('9', '2015-07-23 12:57:16', '2015-07-23 12:57:16', 'Gestor de Projectos', 'Gestor de Projectos');
INSERT INTO `profession` VALUES ('10', '2015-07-23 13:11:54', '2015-07-23 13:11:54', 'Implantador', 'Implantador');

-- ----------------------------
-- Table structure for `purposes`
-- ----------------------------
DROP TABLE IF EXISTS `purposes`;
CREATE TABLE `purposes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `purpose_description` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `porpose` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purposes
-- ----------------------------
INSERT INTO `purposes` VALUES ('1', '2015-07-28 18:57:15', '2015-07-28 18:57:15', 'Gestao Orcamental', '01', 'Gestao Orcamental');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'SectorChief', 'Delegated');
INSERT INTO `roles` VALUES ('2', '2015-07-23 07:25:34', '2015-07-23 07:25:34', 'OrganChief', 'Delegated');
INSERT INTO `roles` VALUES ('3', '2015-07-23 07:25:34', '2015-07-30 14:03:18', 'Admin', 'Normal');
INSERT INTO `roles` VALUES ('4', '2015-07-23 13:17:59', '2015-07-23 13:17:59', 'Funcionario', 'Normal');
INSERT INTO `roles` VALUES ('5', '2015-07-23 13:18:23', '2015-07-23 13:18:23', 'Sector', 'Normal');
INSERT INTO `roles` VALUES ('6', '2015-07-23 13:19:03', '2015-07-23 13:19:03', 'Orgão', 'Normal');
INSERT INTO `roles` VALUES ('7', '2015-07-23 13:20:24', '2015-07-23 13:20:24', 'Planificão', 'Normal');
INSERT INTO `roles` VALUES ('8', '2015-07-23 13:21:49', '2015-07-30 14:07:15', 'Financas', 'Normal');

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
INSERT INTO `role_permissions` VALUES ('4', '18');
INSERT INTO `role_permissions` VALUES ('5', '1');
INSERT INTO `role_permissions` VALUES ('5', '17');
INSERT INTO `role_permissions` VALUES ('6', '11');
INSERT INTO `role_permissions` VALUES ('6', '12');
INSERT INTO `role_permissions` VALUES ('6', '14');
INSERT INTO `role_permissions` VALUES ('7', '10');
INSERT INTO `role_permissions` VALUES ('7', '11');
INSERT INTO `role_permissions` VALUES ('7', '12');
INSERT INTO `role_permissions` VALUES ('7', '14');
INSERT INTO `role_permissions` VALUES ('7', '19');
INSERT INTO `role_permissions` VALUES ('8', '20');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sectors
-- ----------------------------
INSERT INTO `sectors` VALUES ('1', '2015-07-23 12:28:00', '2015-07-23 13:32:54', null, 'Departamento de Sistemas e Multimédia', 'DSM', '1', '8');
INSERT INTO `sectors` VALUES ('2', '2015-07-23 12:29:05', '2015-07-23 13:39:15', null, 'Departamento de Administracao e Financas', 'DAF', '1', '11');
INSERT INTO `sectors` VALUES ('3', '2015-07-23 12:29:30', '2015-07-23 13:46:19', null, 'Departamento de Planificação, Monitoria e Estudo', 'DPME', '2', '7');
INSERT INTO `sectors` VALUES ('4', '2015-07-23 12:30:09', '2015-07-23 12:48:40', null, 'Departamento de Estatística e Informação', 'DEI', '2', null);
INSERT INTO `sectors` VALUES ('5', '2015-07-23 12:47:26', '2015-07-23 12:47:26', null, 'Departamento de Planificacao Pedagogica', 'DPP', '3', null);
INSERT INTO `sectors` VALUES ('6', '2015-07-23 12:47:40', '2015-07-23 12:47:40', null, 'Departamento de Orientação', 'DO', '3', null);
INSERT INTO `sectors` VALUES ('7', '2015-07-23 12:47:54', '2015-07-23 12:47:54', null, 'Departamento de Admissão', 'DA', '3', null);
INSERT INTO `sectors` VALUES ('8', '2015-07-23 12:48:09', '2015-07-23 12:48:09', null, 'Departamento de Administração e Finanças', 'DAF', '3', null);

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
  `start_date` datetime DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `action_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g0ey88a24u9glwtelvdkk2fvc` (`action_id`),
  CONSTRAINT `FK_g0ey88a24u9glwtelvdkk2fvc` FOREIGN KEY (`action_id`) REFERENCES `actions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specified_actions
-- ----------------------------
INSERT INTO `specified_actions` VALUES ('1', '2015-07-23 14:39:45', '2015-07-23 14:39:45', '2015-07-07 00:00:00', 'Montar Equipe', '2015-08-28 00:00:00', '1');
INSERT INTO `specified_actions` VALUES ('2', '2015-07-23 14:51:21', '2015-07-23 14:51:21', '2015-07-23 00:00:00', 'Montar Equipe de Choque', '2015-08-02 00:00:00', '3');
INSERT INTO `specified_actions` VALUES ('3', '2015-07-23 14:53:35', '2015-07-23 14:53:35', '2015-07-02 00:00:00', 'Gestao po Orgao', '2015-07-16 00:00:00', '2');
INSERT INTO `specified_actions` VALUES ('4', '2015-07-23 14:54:27', '2015-07-23 14:54:27', '2015-07-15 00:00:00', 'Gestao Sector', '2015-07-17 00:00:00', '2');
INSERT INTO `specified_actions` VALUES ('5', '2015-07-23 14:55:19', '2015-07-23 14:55:19', '2015-06-29 00:00:00', 'Gestao por Funcionario', '2015-07-17 00:00:00', '2');
INSERT INTO `specified_actions` VALUES ('6', '2015-07-23 19:28:27', '2015-07-23 19:28:27', '2015-07-06 00:00:00', 'Elaboracao do Plano de Formacao', '2015-07-08 00:00:00', '6');
INSERT INTO `specified_actions` VALUES ('7', '2015-07-23 19:29:50', '2015-07-23 19:29:50', '2015-07-06 00:00:00', 'Elaboracao do Plano Anual dos Sectores', '2015-07-17 00:00:00', '6');

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
INSERT INTO `specified_actions_allocation_organs` VALUES ('1', '1');
INSERT INTO `specified_actions_allocation_organs` VALUES ('7', '8');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of strategic_objectives
-- ----------------------------
INSERT INTO `strategic_objectives` VALUES ('1', '2015-07-23 12:34:51', '2015-07-23 12:34:51', 'OBEST7', 'Promover a eficiência administrativa e de gestão, de comunicação e marketing');
INSERT INTO `strategic_objectives` VALUES ('2', '2015-07-23 12:35:09', '2015-07-23 12:35:09', 'OBJEST1', 'Conceber, implementar e monitorar a reforma académica tendo em vista a integração regional.');
INSERT INTO `strategic_objectives` VALUES ('3', '2015-07-23 12:35:32', '2015-07-23 12:35:32', 'OBJEST2', 'Promover o acesso equitativo');
INSERT INTO `strategic_objectives` VALUES ('4', '2015-07-23 12:35:56', '2015-07-23 12:35:56', 'OBJEST3', 'Assegurar excelência e qualidade na docência');
INSERT INTO `strategic_objectives` VALUES ('5', '2015-07-23 12:36:11', '2015-07-23 12:36:11', 'OBJEST4', 'Assegurar excelência e qualidade nas actividades de investigação e deextensão');
INSERT INTO `strategic_objectives` VALUES ('6', '2015-07-23 12:36:47', '2015-07-23 12:36:47', 'OBJEST5', 'Desenvolver a Planta Física');
INSERT INTO `strategic_objectives` VALUES ('7', '2015-07-23 12:37:03', '2015-07-23 12:37:03', 'OBJEST6', 'Desenvolver e valorizar os recursos humanos');
INSERT INTO `strategic_objectives` VALUES ('8', '2015-07-23 12:37:22', '2015-07-23 12:37:22', 'OBJEST6', 'Desenvolver e fortalecer a cooperação nacional, regional e internacional');

-- ----------------------------
-- Table structure for `subscribers`
-- ----------------------------
DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE `subscribers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `subscriber_employee` varchar(255) DEFAULT NULL,
  `subscriber_number` varchar(255) DEFAULT NULL,
  `subscriber_sector` bigint(20) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `employee` bigint(20) DEFAULT NULL,
  `sector` bigint(20) DEFAULT NULL,
  `account` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9lse4p33vidxuixc210k2e3kj` (`subscriber_sector`),
  KEY `FK_71xsyhuwbpoglhr07q5tg6au8` (`employee`),
  KEY `FK_jf892hyrel5hujjucf5tbi8tu` (`sector`),
  KEY `FK_f60pklcv8nndcssilxee748rb` (`account`),
  CONSTRAINT `FK_71xsyhuwbpoglhr07q5tg6au8` FOREIGN KEY (`employee`) REFERENCES `individuals` (`id`),
  CONSTRAINT `FK_9lse4p33vidxuixc210k2e3kj` FOREIGN KEY (`subscriber_sector`) REFERENCES `sectors` (`id`),
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
  `initial` varchar(255) DEFAULT NULL,
  `sector` bigint(20) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fjunyd4ford6qhobmwl584ugd` (`sector`),
  CONSTRAINT `FK_fjunyd4ford6qhobmwl584ugd` FOREIGN KEY (`sector`) REFERENCES `sectors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_sectors
-- ----------------------------
INSERT INTO `sub_sectors` VALUES ('1', '2015-07-23 12:30:25', '2015-07-23 12:30:25', 'SubSector de Analise', null, '1', 'SA');
INSERT INTO `sub_sectors` VALUES ('2', '2015-07-23 12:30:41', '2015-07-23 12:30:41', 'SubSector de Programacao', null, '1', 'SP');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2015-07-23 07:25:34', '2015-07-23 07:25:34', '', '$2a$10$8aAeRACXGu1OMVhtS2/jAOU6md3pxrWbwmHZvYYK0H5ir14XSIhgm', 'admin@admin.com', '1');
INSERT INTO `users` VALUES ('2', '2015-07-23 13:26:22', '2015-07-23 13:26:36', '', '$2a$10$Lw1KH//h0KNd/Nxlo7oSO.iQtKb7PfIysauh4SDU2hYzjwCm6XVvW', 'mondlane@uem.mz', '14');
INSERT INTO `users` VALUES ('3', '2015-07-23 13:29:17', '2015-07-23 13:29:33', '', '$2a$10$h3xFxWyrWFTabCjCfoZnD.DFZcwjmWmSvCOcYy8nUOTq/VpB72fSW', 'joanazamba@gmail.com', '3');
INSERT INTO `users` VALUES ('4', '2015-07-23 13:32:39', '2015-07-23 13:32:54', '', '$2a$10$5x.2s2m10Xj9svjQL02kH.9/RI0zA3L4Dbvd6qBv6VGc6w4fnmh3S', 'marcelo@uem.com', '8');
INSERT INTO `users` VALUES ('5', '2015-07-23 13:33:34', '2015-07-23 13:33:34', '', '$2a$10$XxyhpSmN1n6x6dieE3U2uu7uD6RgM7UBpAYZVq27TKElAk4rW.cTe', 'yolanda@gmail.com', '15');
INSERT INTO `users` VALUES ('6', '2015-07-23 13:34:08', '2015-07-30 14:03:51', '', '$2a$10$Dt3xBAN1L58Mrnv7iJtiHuF0hhPPnq7TBK12MjoIDfJYXq/VgtGs2', 'eusebio@gmail.com', '16');
INSERT INTO `users` VALUES ('7', '2015-07-23 13:38:57', '2015-07-23 13:39:16', '', '$2a$10$2LBPrVc48wEcAv0jgCaCXOmYxw/RxPxPnHHbYK1N4UkFOEGtk6a3W', 'marta@zebra.uem.mz', '11');
INSERT INTO `users` VALUES ('8', '2015-07-23 13:40:32', '2015-07-23 13:40:32', '', '$2a$10$aPuoaS8gCtDNC5Zd1Ki6ou8HagFju6jP3l3kX7rSQWsHxScPBYO6S', 'helton@uem.com', '17');
INSERT INTO `users` VALUES ('9', '2015-07-23 13:41:46', '2015-07-23 13:41:46', '', '$2a$10$gPMKnlWgOl68jca1DYB12.ggzm4kHvaysF8y1ka/gmn2ERN6thUQW', 'lina@uem.com', '9');
INSERT INTO `users` VALUES ('10', '2015-07-23 13:44:05', '2015-07-23 13:44:05', '', '$2a$10$Plia4zH620vouyBjTuJYaO4bXfHYXoc.gdrEwusnFeWDZxvscHglG', 'filo.jawana@uem.mz', '6');
INSERT INTO `users` VALUES ('11', '2015-07-23 13:45:55', '2015-07-23 13:46:19', '', '$2a$10$xL4ahmzBALF39wK2VNj21upLI9sudst3ye1U8LanDMwh5oFiTGHhu', 'aderito.notico@gmail.com', '7');
INSERT INTO `users` VALUES ('12', '2015-07-23 20:03:48', '2015-07-23 20:03:48', '', '$2a$10$xp5M8v3hw/zx8.rZEFhheOkAyifBeuvxXjrRo3UQqfDK4ZEp72gZK', 'nkolekalayina@gmail.com', '12');

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
INSERT INTO `user_roles` VALUES ('1', '4');
INSERT INTO `user_roles` VALUES ('1', '7');
INSERT INTO `user_roles` VALUES ('1', '11');
INSERT INTO `user_roles` VALUES ('2', '2');
INSERT INTO `user_roles` VALUES ('2', '3');
INSERT INTO `user_roles` VALUES ('3', '1');
INSERT INTO `user_roles` VALUES ('4', '5');
INSERT INTO `user_roles` VALUES ('4', '8');
INSERT INTO `user_roles` VALUES ('4', '9');
INSERT INTO `user_roles` VALUES ('4', '12');
INSERT INTO `user_roles` VALUES ('5', '4');
INSERT INTO `user_roles` VALUES ('5', '7');
INSERT INTO `user_roles` VALUES ('5', '10');
INSERT INTO `user_roles` VALUES ('5', '11');
INSERT INTO `user_roles` VALUES ('6', '2');
INSERT INTO `user_roles` VALUES ('7', '3');
INSERT INTO `user_roles` VALUES ('8', '6');
