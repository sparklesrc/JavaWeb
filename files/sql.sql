SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `TallerWeb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `TallerWeb`;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`local` (
  `id` BIGINT(20) NOT NULL ,
  `direccion` VARCHAR(200) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL ,
  `estado` TINYINT(4) NULL DEFAULT NULL ,
  `maps` VARCHAR(800) NULL DEFAULT NULL ,
  `telefono` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`campo` (
  `id` BIGINT(20) NOT NULL ,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL ,
  `estado` TINYINT(4) NULL DEFAULT NULL ,
  `tipo` INT(11) NULL DEFAULT NULL ,
  `costo_hora` DOUBLE NULL DEFAULT NULL ,
  `id_local` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`, `id_local`) ,
  INDEX `fk_campo_local_idx` (`id_local` ASC) ,
  CONSTRAINT `fk_campo_local`
    FOREIGN KEY (`id_local` )
    REFERENCES `TallerWeb`.`local` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`servicio` (
  `id` BIGINT(20) NOT NULL ,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL ,
  `costo_hora` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`general` (
  `id` BIGINT(20) NOT NULL ,
  `usuario` VARCHAR(200) NULL DEFAULT NULL ,
  `password` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`socio` (
  `id` BIGINT(20) NOT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `nombres` VARCHAR(45) NULL DEFAULT NULL ,
  `paterno` VARCHAR(45) NULL DEFAULT NULL ,
  `materno` VARCHAR(45) NULL DEFAULT NULL ,
  `celular` VARCHAR(45) NULL DEFAULT NULL ,
  `sexo` VARCHAR(45) NULL DEFAULT NULL ,
  `direccion` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `TallerWeb`.`solicitud_alquiler` (
  `id` BIGINT(20) NOT NULL ,
  `hora_inicio` CHAR(5) NULL ,
  `hora_fin` CHAR(5) NULL ,
  `dia` INT(11) NULL DEFAULT NULL ,
  `servicios` VARCHAR(45) NULL DEFAULT NULL ,
  `estado` TINYINT(4) NULL DEFAULT NULL ,
  `id_socio` BIGINT(20) NOT NULL ,
  `id_campo` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_solicitud_alquiler_socio1_idx` (`id_socio` ASC) ,
  INDEX `fk_solicitud_alquiler_campo1_idx` (`id_campo` ASC) ,
  CONSTRAINT `fk_solicitud_alquiler_socio1`
    FOREIGN KEY (`id_socio` )
    REFERENCES `TallerWeb`.`socio` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitud_alquiler_campo1`
    FOREIGN KEY (`id_campo` )
    REFERENCES `TallerWeb`.`campo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
