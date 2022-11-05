--TODO: Ver script de Mati para validar campo estado

--CRITICIDAD
CREATE TABLE IF NOT EXISTS `criticidad` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`descripcion` varchar(25) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- PROVINCIAS
CREATE TABLE IF NOT EXISTS `provincias`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(65) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--LOCALIDADES
CREATE TABLE IF NOT EXISTS `localidades`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(65) NOT NULL,
    `id_provincia` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--SOLICITUDES
CREATE TABLE IF NOT EXISTS `solicitudes`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `codigo` varchar(65) NOT NULL,
    `nombre` varchar(65) NOT NULL,
    `apellido` varchar(65) NOT NULL,
    `fecha` date NOT NULL,
    `id_localidad` int(11) NOT NULL,
    `id_provincia` int(11) NOT NULL,
    `direccion` varchar(65) NOT NULL,
    `cantidadDonantes` int(11) NOT NULL,
    `estado` binary NOT NULL DEFAULT true,
    PRIMARY KEY (`id`),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id),
    FOREIGN KEY (id_localidad) REFERENCES localidades(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--PERSONAS

CREATE TABLE IF NOT EXISTS `personas`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(65) NOT NULL,
    `apellido` varchar(65) NULL,
    `telefono` int(15) NOT NULL,
    `direccion` varchar(65) NOT NULL,
    `id_provincia` int(11) NOT NULL,
    `id_localidad` int(11) NOT NULL,
    `fecha_nacimiento` date NULL,
    `cuil` varchar(65) NULL,
    `horario_inicio` varchar(65) NULL,
    `horario_fin` varchar(65) NULL,
    `es_juridica` binary NOT NULL DEFAULT true,
    PRIMARY KEY(`id`),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id),
    FOREIGN KEY (id_localidad) REFERENCES localidades(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- USUARIOS
CREATE TABLE IF NOT EXISTS `usuarios`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre_usuario` varchar(65) NOT NULL,
    `email` varchar(65) NOT NULL,
    `password` varchar(65) NOT NULL,
    `tipo_usuario` int(11) NOT NULL,
    `id_persona` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (id_persona) REFERENCES personas(id),
    UNIQUE(`email`),
    UNIQUE(`nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--POSTULACIONES
CREATE TABLE IF NOT EXISTS `postulaciones`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `codigo` varchar(65) NOT NULL,
    `fecha_generacion` date NOT NULL,
    `id_usuario` int(11) NOT NULL,
    `categoria` int(11) NOT NULL,
    `fecha_confirmacion` date NULL,
    `estado` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--PERSONA FISICA