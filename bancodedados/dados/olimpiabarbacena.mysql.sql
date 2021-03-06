SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `OB` ;
USE `OB` ;

-- -----------------------------------------------------
-- Table `OB`.`ESTADO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`ESTADO` (
  `ESTADOID` CHAR(2) NOT NULL ,
  `NOME` VARCHAR(150) NULL DEFAULT NULL ,
  PRIMARY KEY (`ESTADOID`) );


-- -----------------------------------------------------
-- Table `OB`.`CIDADE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`CIDADE` (
  `CIDADEID` BIGINT NOT NULL ,
  `ESTADOID` CHAR(2) NULL DEFAULT NULL ,
  `NOME` VARCHAR(150) NULL DEFAULT NULL ,
  PRIMARY KEY (`CIDADEID`) ,
  INDEX `FK_ESTADOCIDADE` (`ESTADOID` ASC) ,
  CONSTRAINT `FK_ESTADOCIDADE`
    FOREIGN KEY (`ESTADOID` )
    REFERENCES `OB`.`ESTADO` (`ESTADOID` )
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `OB`.`SEXO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`SEXO` (
  `SEXOID` CHAR(1) NOT NULL ,
  `NOME` VARCHAR(150) NULL DEFAULT NULL ,
  PRIMARY KEY (`SEXOID`) );


-- -----------------------------------------------------
-- Table `OB`.`MEMBRO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`MEMBRO` (
  `MEMBROID` BIGINT NOT NULL ,
  `CADASTRO` DATE NOT NULL ,
  `NOME` VARCHAR(150) NOT NULL ,
  `SEXOID` CHAR(1) NULL DEFAULT NULL ,
  `NASCIMENTO` DATE NULL DEFAULT NULL ,
  `TELEFONE` VARCHAR(32) NOT NULL ,
  `ENDERECO` VARCHAR(255) NOT NULL ,
  `CIDADEID` BIGINT NULL DEFAULT NULL ,
  `CEP` VARCHAR(16) NULL DEFAULT NULL ,
  `EMAIL` VARCHAR(128) NULL DEFAULT NULL ,
  `RENOVAREM` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`MEMBROID`) ,
  INDEX `FK_CIDADEMEMBRO` (`CIDADEID` ASC) ,
  INDEX `FK_SEXOMEMBRO` (`SEXOID` ASC) ,
  CONSTRAINT `FK_CIDADEMEMBRO`
    FOREIGN KEY (`CIDADEID` )
    REFERENCES `OB`.`CIDADE` (`CIDADEID` )
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `FK_SEXOMEMBRO`
    FOREIGN KEY (`SEXOID` )
    REFERENCES `OB`.`SEXO` (`SEXOID` )
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `OB`.`MIDIA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`MIDIA` (
  `MIDIAID` BIGINT NOT NULL ,
  `TITULO` TEXT NOT NULL ,
  `EDICAO` VARCHAR(8) NULL DEFAULT NULL ,
  `AUTOR` TEXT NULL DEFAULT NULL ,
  `PUBLICACAO` DATE NULL DEFAULT NULL ,
  `EDITORA` TEXT NULL DEFAULT NULL ,
  `CATEGORIA` TEXT NULL DEFAULT NULL ,
  `DESCRICAO` TEXT NULL DEFAULT NULL ,
  `IDIOMA` VARCHAR(64) NULL DEFAULT NULL ,
  `CONDICAO` TEXT NULL DEFAULT NULL ,
  `LOCALIDADE` TEXT NULL DEFAULT NULL ,
  `ISBN13` VARCHAR(16) NULL DEFAULT NULL ,
  `FRENTE` LONGBLOB NULL DEFAULT NULL ,
  `VERSO` LONGBLOB NULL DEFAULT NULL ,
  `MARC` TEXT NULL DEFAULT NULL ,
  `BARRA` VARCHAR(128) NULL DEFAULT NULL ,
  `AUDIO` BIT NULL DEFAULT 0 ,
  `TIPO` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`MIDIAID`) );


-- -----------------------------------------------------
-- Table `OB`.`EMPRESTIMO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `OB`.`EMPRESTIMO` (
  `EMPRESTIMOID` BIGINT NOT NULL ,
  `MIDIAID` BIGINT NULL DEFAULT NULL ,
  `MEMBROID` BIGINT NULL DEFAULT NULL ,
  `RESERVA` DATETIME NULL DEFAULT NULL ,
  `RESERVADOPOR` VARCHAR(150) NULL DEFAULT NULL ,
  `EMPRESTIMO` DATETIME NULL DEFAULT NULL ,
  `DIASENTREGA` INT NULL DEFAULT NULL ,
  `ENTREGA` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`EMPRESTIMOID`) ,
  INDEX `FK_EMPRESTIMOMEMBRO` (`MEMBROID` ASC) ,
  INDEX `FK_EMPRESTIMOMIDIA` (`MIDIAID` ASC) ,
  CONSTRAINT `FK_EMPRESTIMOMEMBRO`
    FOREIGN KEY (`MEMBROID` )
    REFERENCES `OB`.`MEMBRO` (`MEMBROID` )
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `FK_EMPRESTIMOMIDIA`
    FOREIGN KEY (`MIDIAID` )
    REFERENCES `OB`.`MIDIA` (`MIDIAID` )
    ON DELETE restrict
    ON UPDATE restrict);



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `OB`.`SEXO`
-- -----------------------------------------------------
START TRANSACTION;
USE `OB`;
INSERT INTO `OB`.`SEXO` (`SEXOID`, `NOME`) VALUES ('M', 'Masculino');
INSERT INTO `OB`.`SEXO` (`SEXOID`, `NOME`) VALUES ('F', 'Feminino');

COMMIT;
