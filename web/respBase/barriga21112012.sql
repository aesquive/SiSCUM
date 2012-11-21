-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 21-11-2012 a las 11:48:08
-- Versión del servidor: 5.5.24
-- Versión de PHP: 5.3.10-1ubuntu3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `barriga`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE IF NOT EXISTS `Cliente` (
  `IdCliente` int(11) NOT NULL AUTO_INCREMENT,
  `PrimerNombre` varchar(100) DEFAULT NULL,
  `SegundoNombre` varchar(100) DEFAULT NULL,
  `ApellidoPaterno` varchar(100) DEFAULT NULL,
  `ApellidoMaterno` varchar(100) DEFAULT NULL,
  `IdEstatusCliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdCliente`),
  KEY `fk_Cliente_1_idx` (`IdEstatusCliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `Cliente`
--

INSERT INTO `Cliente` (`IdCliente`, `PrimerNombre`, `SegundoNombre`, `ApellidoPaterno`, `ApellidoMaterno`, `IdEstatusCliente`) VALUES
(1, 'Jose', '', 'Joseso', 'Josesofo', 1),
(2, 'Alberto', 'Emmanuel', 'Esquivel', 'Vega', 1),
(3, 'www', '', 'qwerty', 'qwerty', 1),
(4, 'Nombre', 'SegundoNombre', 'Paterno', 'Materno', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Empleado`
--

CREATE TABLE IF NOT EXISTS `Empleado` (
  `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdRol` int(11) NOT NULL,
  `PrimerNombre` varchar(100) DEFAULT NULL,
  `SegundoNombre` varchar(100) DEFAULT NULL,
  `ApellidoPaterno` varchar(100) DEFAULT NULL,
  `ApellidoMaterno` varchar(100) DEFAULT NULL,
  `IdEstatusEmpleado` int(11) DEFAULT NULL,
  `Usuario` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdEmpleado`),
  KEY `fk_Empleado_1_idx` (`IdEstatusEmpleado`),
  KEY `fk_Empleado_1_idx1` (`IdRol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `Empleado`
--

INSERT INTO `Empleado` (`IdEmpleado`, `IdRol`, `PrimerNombre`, `SegundoNombre`, `ApellidoPaterno`, `ApellidoMaterno`, `IdEstatusEmpleado`, `Usuario`, `Password`) VALUES
(1, 1, 'CobradorNombre', 'CobradorSegNombre', 'CobradorApellido', 'CobradorMaterno', 1, 'Cobrador', 'cobrador'),
(2, 2, 'SupervisorNombre', 'SupervisorSegundoNombre', 'SupervisorApellido', 'SupervidorMaterno', 1, 'Supervisor', 'supervisor'),
(3, 3, 'GerenteNombre', 'GerenteSegNombre', 'GerentePaterno', 'GerenteMaterno', 1, 'Gerente', 'gerente'),
(4, 4, 'LiderNombre', 'LiderSegNombre', 'LiderPaterno', 'LiderMaterno', 1, 'Lider', 'lider'),
(5, 3, 'Diego', '', 'Filloy', 'Ring', 1, 'filloy', 'filloy'),
(6, 3, 'Test|,Ã±{2', '', 'Test|,Ã±{2', 'Test|,Ã±{2', 2, '!"#', '!"#'),
(7, 1, 'Cobrador2', 'Cobrador2', 'Cobrador2', 'Cobrador2', 1, 'cobrador2', 'cobrador2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstatusCartera`
--

CREATE TABLE IF NOT EXISTS `EstatusCartera` (
  `IdEstatusCartera` int(11) NOT NULL,
  `DesEstatusCartera` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEstatusCartera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `EstatusCartera`
--

INSERT INTO `EstatusCartera` (`IdEstatusCartera`, `DesEstatusCartera`) VALUES
(0, ''),
(1, 'Cartera Asignada'),
(2, 'Cartera No Asignada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstatusCliente`
--

CREATE TABLE IF NOT EXISTS `EstatusCliente` (
  `IdEstatusCliente` int(11) NOT NULL,
  `DesEstatusCliente` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEstatusCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `EstatusCliente`
--

INSERT INTO `EstatusCliente` (`IdEstatusCliente`, `DesEstatusCliente`) VALUES
(1, 'Cliente Activo'),
(2, 'Cliente No Activo'),
(3, 'Cliente No Asignado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstatusConvenio`
--

CREATE TABLE IF NOT EXISTS `EstatusConvenio` (
  `IdEstatusConvenio` int(11) NOT NULL,
  `DesEstatusConvenio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEstatusConvenio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `EstatusConvenio`
--

INSERT INTO `EstatusConvenio` (`IdEstatusConvenio`, `DesEstatusConvenio`) VALUES
(1, 'Convenio Activo'),
(2, 'Convenio Pagado'),
(3, 'Convenio No Pagado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstatusEmpleado`
--

CREATE TABLE IF NOT EXISTS `EstatusEmpleado` (
  `IdEstatusEmpleado` int(11) NOT NULL,
  `DesEstatusEmpleado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEstatusEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `EstatusEmpleado`
--

INSERT INTO `EstatusEmpleado` (`IdEstatusEmpleado`, `DesEstatusEmpleado`) VALUES
(1, 'Empleado Activo'),
(2, 'Empleado Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstatusVivienda`
--

CREATE TABLE IF NOT EXISTS `EstatusVivienda` (
  `IdEstatusVivienda` int(11) NOT NULL,
  `DesEstatusVivienda` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdEstatusVivienda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `EstatusVivienda`
--

INSERT INTO `EstatusVivienda` (`IdEstatusVivienda`, `DesEstatusVivienda`) VALUES
(1, 'Vivienda Habitada'),
(2, 'Vivienda Deshabitada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HistoriaCartera`
--

CREATE TABLE IF NOT EXISTS `HistoriaCartera` (
  `IdHistoriaCartera` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) DEFAULT NULL,
  `IdCliente` int(11) NOT NULL,
  `OrdenHistorico` int(11) NOT NULL,
  `FechaEntrada` datetime DEFAULT NULL,
  `FechaSalida` datetime DEFAULT NULL,
  `IdEstatusCartera` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdHistoriaCartera`),
  KEY `fk_HistoriaCartera_1_idx` (`IdEstatusCartera`),
  KEY `fk_HistoriaCartera_1_idx1` (`IdEmpleado`),
  KEY `fk_HistoriaCartera_1_idx2` (`IdCliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `HistoriaCartera`
--

INSERT INTO `HistoriaCartera` (`IdHistoriaCartera`, `IdEmpleado`, `IdCliente`, `OrdenHistorico`, `FechaEntrada`, `FechaSalida`, `IdEstatusCartera`) VALUES
(1, 1, 1, 0, '2012-11-05 08:43:40', NULL, 1),
(2, 1, 2, 0, '2012-11-05 08:43:49', NULL, 1),
(3, 5, 3, 0, '2012-11-05 13:26:58', NULL, 1),
(4, 7, 4, 0, '2012-11-21 11:35:37', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HistoriaConvenio`
--

CREATE TABLE IF NOT EXISTS `HistoriaConvenio` (
  `IdHistoriaConvenio` int(11) NOT NULL AUTO_INCREMENT,
  `IdCartera` int(11) NOT NULL,
  `FechaCreacion` date NOT NULL,
  `FechaCompromiso` date NOT NULL,
  `Monto` double NOT NULL,
  `IdEstatusConvenio` int(11) NOT NULL,
  PRIMARY KEY (`IdHistoriaConvenio`),
  KEY `fk_HistoriaConvenio_1_idx` (`IdEstatusConvenio`),
  KEY `fk_HistoriaConvenio_1_idx1` (`IdCartera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HistoriaPagos`
--

CREATE TABLE IF NOT EXISTS `HistoriaPagos` (
  `IdHistoriaPagos` int(11) NOT NULL AUTO_INCREMENT,
  `IdCartera` int(11) DEFAULT NULL,
  `FechaPago` datetime DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  PRIMARY KEY (`IdHistoriaPagos`),
  KEY `fk_HistoriaPagos_1_idx` (`IdCartera`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `JerarquiaEmpleado`
--

CREATE TABLE IF NOT EXISTS `JerarquiaEmpleado` (
  `IdJerarquiaEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdJefe` int(11) DEFAULT NULL,
  `IdEmpleado` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdJerarquiaEmpleado`),
  KEY `fk_JerarquiaEmpleado_1_idx` (`IdJefe`),
  KEY `fk_JerarquiaEmpleado_1_idx1` (`IdEmpleado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `JerarquiaEmpleado`
--

INSERT INTO `JerarquiaEmpleado` (`IdJerarquiaEmpleado`, `IdJefe`, `IdEmpleado`) VALUES
(1, 2, 1),
(2, 3, 2),
(3, 4, 3),
(4, 4, 5),
(5, 4, 6),
(6, 2, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Rol`
--

CREATE TABLE IF NOT EXISTS `Rol` (
  `IdRol` int(11) NOT NULL,
  `DesRol` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Rol`
--

INSERT INTO `Rol` (`IdRol`, `DesRol`) VALUES
(1, 'Cobrador'),
(2, 'Supervisor'),
(3, 'Gerente'),
(4, 'Lider de Cobranza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Vivienda`
--

CREATE TABLE IF NOT EXISTS `Vivienda` (
  `IdVivienda` int(11) NOT NULL AUTO_INCREMENT,
  `IdCliente` int(11) NOT NULL,
  `PosicionLatitud` int(11) DEFAULT NULL,
  `PosicionLongitud` int(11) DEFAULT NULL,
  `DireccionCalle` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `DireccionNumero` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `DireccionColonia` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `DireccionDelegacionMunicipio` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `DireccionCodigoPostal` int(11) DEFAULT NULL,
  `IdEstatusVivienda` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdVivienda`),
  KEY `fk_Vivienda_1_idx` (`IdCliente`),
  KEY `fk_Vivienda_1_idx1` (`IdEstatusVivienda`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `Vivienda`
--

INSERT INTO `Vivienda` (`IdVivienda`, `IdCliente`, `PosicionLatitud`, `PosicionLongitud`, `DireccionCalle`, `DireccionNumero`, `DireccionColonia`, `DireccionDelegacionMunicipio`, `DireccionCodigoPostal`, `IdEstatusVivienda`) VALUES
(1, 1, 100, 100, 'Gardenias', '47', 'Flores', 'Azcapo', 6500, 1),
(2, 2, NULL, NULL, 'Calle', 'Numero', 'COl', 'Ciud', 50000, 1),
(3, 3, NULL, NULL, 'ww', 'ocho', 'col', 'ci', 14210, 1),
(4, 4, NULL, NULL, 'Calle', 'Num', 'Col', 'Ciu', 55040, 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD CONSTRAINT `fk_ClienteEstatusCliente` FOREIGN KEY (`IdEstatusCliente`) REFERENCES `EstatusCliente` (`IdEstatusCliente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Empleado`
--
ALTER TABLE `Empleado`
  ADD CONSTRAINT `fk_EmpleadoEstatusEmpleado` FOREIGN KEY (`IdEstatusEmpleado`) REFERENCES `EstatusEmpleado` (`IdEstatusEmpleado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_EmpleadoRol` FOREIGN KEY (`IdRol`) REFERENCES `Rol` (`IdRol`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `HistoriaCartera`
--
ALTER TABLE `HistoriaCartera`
  ADD CONSTRAINT `fk_HistoriaCarteraCliente` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_HistoriaCarteraEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `Empleado` (`IdEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_HistoriaCarteraEstatusCartera` FOREIGN KEY (`IdEstatusCartera`) REFERENCES `EstatusCartera` (`IdEstatusCartera`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `HistoriaConvenio`
--
ALTER TABLE `HistoriaConvenio`
  ADD CONSTRAINT `fk_HistoriaConvenio_Cartera` FOREIGN KEY (`IdCartera`) REFERENCES `HistoriaCartera` (`IdHistoriaCartera`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_HistoriaConvenio_EstatusConvenio` FOREIGN KEY (`IdEstatusConvenio`) REFERENCES `EstatusConvenio` (`IdEstatusConvenio`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `HistoriaPagos`
--
ALTER TABLE `HistoriaPagos`
  ADD CONSTRAINT `fk_HistoriaPagosCartera` FOREIGN KEY (`IdCartera`) REFERENCES `HistoriaCartera` (`IdHistoriaCartera`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `JerarquiaEmpleado`
--
ALTER TABLE `JerarquiaEmpleado`
  ADD CONSTRAINT `fk_JerarquiaEmpleado_Empleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `Empleado` (`IdEmpleado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_JerarquiaEmpleado_Jefe` FOREIGN KEY (`IdJefe`) REFERENCES `Empleado` (`IdEmpleado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `Vivienda`
--
ALTER TABLE `Vivienda`
  ADD CONSTRAINT `fk_ViviendaCliente` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ViviendaEstatusVivienda` FOREIGN KEY (`IdEstatusVivienda`) REFERENCES `EstatusVivienda` (`IdEstatusVivienda`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
