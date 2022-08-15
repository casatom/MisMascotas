-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-08-2022 a las 00:16:44
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_mismascotas`
--
CREATE DATABASE IF NOT EXISTS `bd_mismascotas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_mismascotas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `animal` (
  `id_animal` int(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `id_dueño` int(100) DEFAULT NULL,
  `estado` enum('conDueño','enBusqueda','conDueño') DEFAULT NULL,
  `id_dieta` int(100) DEFAULT NULL,
  `tipoAnimal` enum('perro','gato','exotico') DEFAULT NULL,
  `id_refugio` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consulta`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `consulta` (
  `id_consulta` int(100) NOT NULL,
  `fechaConsulta` date DEFAULT NULL,
  `id_veterinario` int(100) DEFAULT NULL,
  `id_animal` int(100) DEFAULT NULL,
  `id_dieta` int(100) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dieta`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `dieta` (
  `id_dieta` int(100) NOT NULL,
  `observacionDieta` varchar(255) DEFAULT NULL,
  `id_animal` int(100) DEFAULT NULL,
  `dietaRecomendada` enum('balanceada','ligera','deportiva') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dueño`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `dueño` (
  `id_dueño` int(100) NOT NULL,
  `nombreCompleto` varchar(100) DEFAULT NULL,
  `nroDocumento` varchar(255) DEFAULT NULL,
  `tipoDocumento` enum('DNI','PASAPORTE') DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `esValidoParaAdoptar` tinyint(1) DEFAULT NULL,
  `id_user` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugar`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `lugar` (
  `id_lugar` int(100) NOT NULL,
  `lugar` varchar(100) DEFAULT NULL,
  `id_rescatista` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `refugio`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `refugio` (
  `id_refugio` int(100) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `tipoRefugio` enum('perro','gato','exotico') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `refugioxdueño`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `refugioxdueño` (
  `id_refugio` int(100) NOT NULL,
  `id_dueño` int(100) DEFAULT NULL,
  `id_refugioxdueño` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rescatista`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `rescatista` (
  `id_rescatista` int(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `id_refugio` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `usuario` (
  `id_user` int(100) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `validado` tinyint(1) DEFAULT NULL,
  `contraHasheada` varchar(255) DEFAULT NULL,
  `esAdmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinario`
--
-- Creación: 15-08-2022 a las 22:10:40
--

CREATE TABLE `veterinario` (
  `id_veterinario` int(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `cuit` int(255) DEFAULT NULL,
  `id_refugio` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`id_animal`),
  ADD KEY `dueño_id_dueño_animal` (`id_dueño`),
  ADD KEY `dieta_id_dieta_animal` (`id_dieta`),
  ADD KEY `refugio_id_Refugio_animal` (`id_refugio`);

--
-- Indices de la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id_consulta`),
  ADD KEY `animal_id_animal_consulta` (`id_animal`),
  ADD KEY `dieta_id_dieta_consulta` (`id_dieta`),
  ADD KEY `veterinario_id_veterinario_consulta` (`id_veterinario`);

--
-- Indices de la tabla `dieta`
--
ALTER TABLE `dieta`
  ADD PRIMARY KEY (`id_dieta`),
  ADD KEY `animal_id_animal_dieta` (`id_animal`);

--
-- Indices de la tabla `dueño`
--
ALTER TABLE `dueño`
  ADD PRIMARY KEY (`id_dueño`),
  ADD KEY `Usuario_id_user_Dueño` (`id_user`);

--
-- Indices de la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD PRIMARY KEY (`id_lugar`),
  ADD KEY `rescatista_id_rescatista_lugar` (`id_rescatista`);

--
-- Indices de la tabla `refugio`
--
ALTER TABLE `refugio`
  ADD PRIMARY KEY (`id_refugio`);

--
-- Indices de la tabla `refugioxdueño`
--
ALTER TABLE `refugioxdueño`
  ADD PRIMARY KEY (`id_refugioxdueño`),
  ADD KEY `refugio_id_Refugio_refugioxdueño` (`id_refugio`),
  ADD KEY `dueño_id_dueño_refugioxdueño` (`id_dueño`);

--
-- Indices de la tabla `rescatista`
--
ALTER TABLE `rescatista`
  ADD PRIMARY KEY (`id_rescatista`),
  ADD KEY `refugio_id_refugio_rescatista` (`id_refugio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_user`);

--
-- Indices de la tabla `veterinario`
--
ALTER TABLE `veterinario`
  ADD PRIMARY KEY (`id_veterinario`),
  ADD KEY `refugio_id_refugio_veterinario` (`id_refugio`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `dieta_id_dieta_animal` FOREIGN KEY (`id_dieta`) REFERENCES `dieta` (`id_dieta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `dueño_id_dueño_animal` FOREIGN KEY (`id_dueño`) REFERENCES `dueño` (`id_dueño`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `refugio_id_Refugio_animal` FOREIGN KEY (`id_refugio`) REFERENCES `refugio` (`id_refugio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `animal_id_animal_consulta` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `dieta_id_dieta_consulta` FOREIGN KEY (`id_dieta`) REFERENCES `dieta` (`id_dieta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `veterinario_id_veterinario_consulta` FOREIGN KEY (`id_veterinario`) REFERENCES `veterinario` (`id_veterinario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `dieta`
--
ALTER TABLE `dieta`
  ADD CONSTRAINT `animal_id_animal_dieta` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `dueño`
--
ALTER TABLE `dueño`
  ADD CONSTRAINT `Usuario_id_user_Dueño` FOREIGN KEY (`id_user`) REFERENCES `usuario` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `dueño_ibfk_1` FOREIGN KEY (`id_dueño`) REFERENCES `refugioxdueño` (`id_dueño`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD CONSTRAINT `rescatista_id_rescatista_lugar` FOREIGN KEY (`id_rescatista`) REFERENCES `rescatista` (`id_rescatista`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `refugioxdueño`
--
ALTER TABLE `refugioxdueño`
  ADD CONSTRAINT `dueño_id_dueño_refugioxdueño` FOREIGN KEY (`id_dueño`) REFERENCES `dueño` (`id_dueño`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `refugio_id_Refugio_refugioxdueño` FOREIGN KEY (`id_refugio`) REFERENCES `refugio` (`id_refugio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `rescatista`
--
ALTER TABLE `rescatista`
  ADD CONSTRAINT `refugio_id_refugio_rescatista` FOREIGN KEY (`id_refugio`) REFERENCES `refugio` (`id_refugio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `dueño` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `veterinario`
--
ALTER TABLE `veterinario`
  ADD CONSTRAINT `refugio_id_refugio_veterinario` FOREIGN KEY (`id_refugio`) REFERENCES `refugio` (`id_refugio`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
