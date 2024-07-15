-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-12-2023 a las 07:12:50
-- Versión del servidor: 8.0.33
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `app_acosta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_usuario`
--

CREATE TABLE `cuentas_usuario` (
  `id` int NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `contraseña` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cuentas_usuario`
--

INSERT INTO `cuentas_usuario` (`id`, `nombre_usuario`, `contraseña`) VALUES
(1, 'Acosta', 'Fabrii'),
(2, 'Teo', 'asdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `edad` int DEFAULT NULL,
  `curso` varchar(50) NOT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `division` varchar(50) DEFAULT NULL,
  `turno` varchar(50) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`id`, `nombre`, `edad`, `curso`, `dni`, `division`, `turno`) VALUES
(1, 'Santiago Pérez', 15, '1ro', '45123698', NULL, NULL),
(2, 'Valentina González', 14, '1ro', '45219876', NULL, NULL),
(3, 'Mateo Fernández', 15, '1ro', '45387654', NULL, NULL),
(4, 'Martina Rodríguez', 16, '2do', '45432109', NULL, NULL),
(5, 'Juan Martín Gómez', 15, '2do', '45560987', NULL, NULL),
(6, 'Luisana Sánchez', 16, '2do', '45674523', NULL, NULL),
(7, 'Lucas Martínez', 17, '3ro', '45783210', NULL, NULL),
(8, 'Camila López', 16, '3ro', '45896754', NULL, NULL),
(9, 'Renzo García', 17, '3ro', '45901234', NULL, NULL),
(10, 'Agustina Díaz', 18, '4to', '45987543', NULL, NULL),
(11, 'Nicolás Ruiz', 17, '4to', '45098612', NULL, NULL),
(12, 'Florencia Acosta', 18, '4to', '45067432', NULL, NULL),
(13, 'Emiliano Silva', 19, '5to', '45123987', NULL, NULL),
(14, 'Abril Torres', 18, '5to', '45237654', NULL, NULL),
(15, 'Thiago Castro', 19, '5to', '45345678', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas_usuario`
--
ALTER TABLE `cuentas_usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_usuario` (`nombre_usuario`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas_usuario`
--
ALTER TABLE `cuentas_usuario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
