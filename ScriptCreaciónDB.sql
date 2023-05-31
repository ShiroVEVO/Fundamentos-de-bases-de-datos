-- PRIMERO CREAR UNA BASE LLAMADA mydb Y SOBRE ELLA EJECUTAR ESTE SCRIPT


CREATE SCHEMA IF NOT EXISTS mydb;

CREATE TABLE IF NOT EXISTS mydb.Ciudad (
  k_Ciudad INT NOT NULL,
  n_Ciudad VARCHAR(40) NOT NULL,
  f_InicioServicio TIMESTAMP NULL,
  f_FinalServicio TIMESTAMP NOT NULL,
  PRIMARY KEY (k_Ciudad));
  
--/////////////////
CREATE TABLE IF NOT EXISTS mydb.Localidad (
  k_Localidad INT NOT NULL,
  n_Localidad VARCHAR(40) NOT NULL,
  Ciudad_k_Ciudad INT NOT NULL,
  PRIMARY KEY (k_Localidad),
  CONSTRAINT fk_Localidad_Ciudad
    FOREIGN KEY (Ciudad_k_Ciudad)
    REFERENCES mydb.Ciudad (k_Ciudad)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Localidad_Ciudad_idx
ON mydb.Localidad (Ciudad_k_Ciudad);


CREATE TABLE IF NOT EXISTS mydb.Bicicleta (
  k_Bicicleta INT NOT NULL,
  n_Tipo VARCHAR(40) NOT NULL,
  PRIMARY KEY (k_Bicicleta));

CREATE TABLE IF NOT EXISTS mydb.Estacion (
  k_Estacion INT NOT NULL,
  q_AnclajesDisponibles INT NOT NULL,
  q_AnclajesTotales INT NOT NULL,
  n_Direccion VARCHAR(60) NOT NULL,
  Localidad_k_Localidad INT NOT NULL,
  PRIMARY KEY (k_Estacion),
  CONSTRAINT fk_Estacion_Localidad1
    FOREIGN KEY (Localidad_k_Localidad)
    REFERENCES mydb.Localidad (k_Localidad)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Estacion_Localidad1_idx
ON mydb.Estacion (Localidad_k_Localidad);
	
CREATE TABLE IF NOT EXISTS mydb.Plan(
  k_Plan INT NOT NULL,
  i_ValorRetiroMecanica FLOAT NOT NULL,
  i_ValorRetiroElectrica FLOAT NOT NULL,
  i_TarifaSuscripcion FLOAT NOT NULL,
  i_TiempoSuscripcion INT NULL,
  i_DuracionMaxViaje INT NULL,
  i_CantidadMaxViajes INT NULL,
  q_ViajesExtra INT NULL,
  i_ValorViajeExtra FLOAT NULL,
  i_ValorMinAdicional FLOAT NOT NULL,
  n_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (k_Plan));

CREATE TABLE IF NOT EXISTS mydb.Cuenta (
  k_Cuenta INT NOT NULL,
  i_SaldoFinal FLOAT NULL,
  i_SaldoInicial FLOAT NOT NULL,
  n_estado VARCHAR(10) NOT NULL,
  n_Contraseña VARCHAR(20) NOT NULL,
  n_CorreoElectronico VARCHAR(25) NOT NULL,
  Plan_k_Plan INT NOT NULL,
  PRIMARY KEY (k_Cuenta),
  CONSTRAINT fk_Cuenta_Plan1
    FOREIGN KEY (Plan_k_Plan)
    REFERENCES mydb.Plan (k_Plan)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Cuenta_Plan1_idx
ON mydb.Cuenta (Plan_k_Plan);

CREATE TABLE IF NOT EXISTS mydb.Usuario (
  k_NumIdentificacion INT NOT NULL,
  n_TipoIdenntificacion VARCHAR(15) NOT NULL,
  f_fechaNacimiento TIMESTAMP NOT NULL,
  n_Nacionalidad VARCHAR(20) NOT NULL,
  v_NumCelular BIGINT NOT NULL,
  i_Sexo CHAR(1) NOT NULL,
  n_Eps VARCHAR(20) NOT NULL,
  n_PrimerNombre VARCHAR(25) NOT NULL,
  n_SegundoNombre VARCHAR(25) NULL,
  n_PrimerApellido VARCHAR(25) NOT NULL,
  n_SegundoApellido VARCHAR(25) NULL,
  Cuenta_k_Cuenta INT NOT NULL,
  PRIMARY KEY (k_NumIdentificacion),
  CONSTRAINT fk_Usuario_Cuenta1
    FOREIGN KEY (Cuenta_k_Cuenta)
    REFERENCES mydb.Cuenta (k_Cuenta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Usuario_Cuenta1_idx
ON mydb.Usuario (Cuenta_k_Cuenta);


CREATE TABLE IF NOT EXISTS mydb.Viaje (
  k_Viaje INT NOT NULL,
  f_Entrega TIMESTAMP,
  f_Desbloqueo TIMESTAMP NOT NULL,
  i_Costo FLOAT NULL,
  Cuenta_k_Cuenta INT NOT NULL,
  PRIMARY KEY (k_Viaje),
  CONSTRAINT fk_Viaje_Cuenta1
    FOREIGN KEY (Cuenta_k_Cuenta)
    REFERENCES mydb.Cuenta (k_Cuenta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Viaje_Cuenta1_idx
ON mydb.Viaje (Cuenta_k_Cuenta);


CREATE TABLE IF NOT EXISTS mydb.FormaPago (
  k_NumeroTarjeta INT NOT NULL,
  n_NombreTarjeta VARCHAR(45) NOT NULL,
  n_Estado VARCHAR(45) NOT NULL,
  f_FechaVencimientoTarjeta VARCHAR(4) NOT NULL,
  Usuario_k_NumIdentificacion INT NOT NULL,
  PRIMARY KEY (k_NumeroTarjeta),
  CONSTRAINT fk_FormaPago_Usuario1
    FOREIGN KEY (Usuario_k_NumIdentificacion)
    REFERENCES mydb.Usuario (k_NumIdentificacion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_FormaPago_Usuario1_idx
ON mydb.FormaPago (Usuario_k_NumIdentificacion);


CREATE TABLE IF NOT EXISTS mydb.EstacioTieneBicicleta (
  fk_Estacion INT NOT NULL,
  fk_Bicicleta INT NOT NULL,
  PRIMARY KEY (fk_Estacion, fk_Bicicleta),
  CONSTRAINT fk_Estacion_has_Bicicleta_Estacion1
    FOREIGN KEY (fk_Estacion)
    REFERENCES mydb.Estacion (k_Estacion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Estacion_has_Bicicleta_Bicicleta1
    FOREIGN KEY (fk_Bicicleta)
    REFERENCES mydb.Bicicleta (k_Bicicleta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_Estacion_has_Bicicleta_Bicicleta1_idx
ON mydb.EstacioTieneBicicleta (fk_Bicicleta);
CREATE INDEX fk_Estacion_has_Bicicleta_Estacion1_idx
ON mydb.EstacioTieneBicicleta (fk_Estacion);


CREATE TABLE IF NOT EXISTS mydb.EstacioTieneBicicleta_has_Viaje (
  EstacioTieneBicicleta_fk_Estacion INT NOT NULL,
  EstacioTieneBicicleta_fk_Bicicleta INT NOT NULL,
  Viaje_k_Viaje INT NOT NULL,
  i_entrega BOOLEAN NOT NULL,
  PRIMARY KEY (EstacioTieneBicicleta_fk_Estacion, EstacioTieneBicicleta_fk_Bicicleta, Viaje_k_Viaje),
  CONSTRAINT fk_EstacioTieneBicicleta_has_Viaje_Viaje1
    FOREIGN KEY (Viaje_k_Viaje)
    REFERENCES mydb.Viaje (k_Viaje)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX fk_EstacioTieneBicicleta_has_Viaje_Viaje1_idx
ON mydb.EstacioTieneBicicleta_has_Viaje (Viaje_k_Viaje);
CREATE INDEX fk_EstacioTieneBicicleta_has_Viaje_EstacioTieneBicicleta1_idx
ON mydb.EstacioTieneBicicleta_has_Viaje (EstacioTieneBicicleta_fk_Estacion, EstacioTieneBicicleta_fk_Bicicleta);

-- INSERSIÓN PLANES 
INSERT INTO mydb.Plan VALUES (1,1300,2500,0,999,24,999,999,0,150,'Esporadico');
INSERT INTO mydb.Plan VALUES (2,890,890,9990,1,60,4,1,3990,150,'Diario');
INSERT INTO mydb.Plan VALUES (3,999,999,31990,30,60,4,0,0,75,'Mensual');
INSERT INTO mydb.Plan VALUES (4,999,999,229900,364,60,4,0,0,75,'Anual');

-- INSERSIÓN CUENTA
INSERT INTO mydb.cuenta VALUES (1,0,0,'Activa','pass','email@email.com',1);
INSERT INTO mydb.cuenta VALUES (2,0,0,'Activa','pass2','email2@email.com',1);
INSERT INTO mydb.cuenta VALUES (3,0,0,'Activa','pass3','email3@email.com',1);
INSERT INTO mydb.cuenta VALUES (4,0,0,'Activa','pass4','email4@email.com',1);
INSERT INTO mydb.cuenta VALUES (5,100,100,'Activa', 'contraseña123', 'adrianstratos@gmail.com', 2);

-- INSERSIÓN USUARIO 
INSERT INTO mydb.usuario VALUES(1010043148,'CC','2023-05-23 12:34:56','Colombiano', 3125544942,'M','Famisanar','Adrian','Stiven','Olmos','Ardila',5);
INSERT INTO mydb.usuario VALUES(1923810412,'CC','2023-05-23 12:34:56','Venezolano', 2147483647,'F','Servisalud','Adriana','Maria','Gutierrez','Paez',1);
INSERT INTO mydb.usuario VALUES(1314689031,'CC','2023-05-23 12:34:56','Colombiano', 3113494567,'M','Sura','Juan','Manuel','Ortega','Sanchez',2);
INSERT INTO mydb.usuario VALUES(1010283192,'CC','2023-05-23 12:34:56','Venezolano', 3211231689,'M','Saludcop','Santiago','Alberto','Villanueva','Lugo',3);
INSERT INTO mydb.usuario VALUES(1679192311,'CC','2023-05-23 12:34:56','Colombiano', 3230101848,'M','Compensar','Esteban','Camilo','Paez','Mayorga',4);

-- ISERCIÓN de ciudad
INSERT INTO mydb.ciudad values (1, 'Bogotá', '2001-09-11 05:00:00', '2001-09-11 22:00:00');
INSERT INTO mydb.ciudad values (2, 'Medellin', '2001-09-11 06:00:00', '2001-09-11 20:00:00');

-- ISERCIÓN de localidad
INSERT INTO mydb.localidad VALUES (1, 'Teusaquillo', 1);
INSERT INTO mydb.localidad VALUES (2, 'Usme', 1);
INSERT INTO mydb.localidad VALUES (3, 'Santa Fe', 1);

-- INSERCIÓN de estaciones NOTA: el estado original debe ser consistente. Aquí se tiene en cuenta las bicicletas 
-- que se agregarán a continuación.
INSERT INTO mydb.estacion VALUES(1, 25, 30, 'Cr 100 No. 11-60', 1);
INSERT INTO mydb.estacion VALUES(2, 5, 10, 'Cl 39SUR No. 41-19', 2);
INSERT INTO mydb.estacion VALUES(3, 15, 20, 'Cl 29 No. 15-100', 3);

-- INSERCIÓN Bicicletas y su rel con estación
INSERT INTO mydb.bicicleta VALUES(1, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(2, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(3, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(4, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(5, 'Mecanica');
INSERT INTO mydb.estaciotienebicicleta VALUES(1,1);
INSERT INTO mydb.estaciotienebicicleta VALUES(1,2);
INSERT INTO mydb.estaciotienebicicleta VALUES(1,3);
INSERT INTO mydb.estaciotienebicicleta VALUES(1,4);
INSERT INTO mydb.estaciotienebicicleta VALUES(1,5);

INSERT INTO mydb.bicicleta VALUES(6, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(7, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(8, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(9, 'Electrica');
INSERT INTO mydb.bicicleta VALUES(10, 'Electrica');
INSERT INTO mydb.estaciotienebicicleta VALUES(2,6);
INSERT INTO mydb.estaciotienebicicleta VALUES(2,7);
INSERT INTO mydb.estaciotienebicicleta VALUES(2,8);
INSERT INTO mydb.estaciotienebicicleta VALUES(2,9);
INSERT INTO mydb.estaciotienebicicleta VALUES(2,10);

INSERT INTO mydb.bicicleta VALUES(11, 'Electrica');
INSERT INTO mydb.bicicleta VALUES(12, 'Electrica');
INSERT INTO mydb.bicicleta VALUES(13, 'Electrica');
INSERT INTO mydb.bicicleta VALUES(14, 'Mecanica');
INSERT INTO mydb.bicicleta VALUES(15, 'Mecanica');
INSERT INTO mydb.estaciotienebicicleta VALUES(3,11);
INSERT INTO mydb.estaciotienebicicleta VALUES(3,12);
INSERT INTO mydb.estaciotienebicicleta VALUES(3,13);
INSERT INTO mydb.estaciotienebicicleta VALUES(3,14);
INSERT INTO mydb.estaciotienebicicleta VALUES(3,15);
