select * from mydb.ciudad;
select * from mydb.localidad;
select * from mydb.estacion;
select * from mydb.bicicleta;
select * from mydb.estaciotienebicicleta;
SELECT * FROM mydb.estaciotienebicicleta_has_viaje;
select * from mydb.viaje;
select * from mydb.cuenta;

SELECT * FROM mydb.viaje ORDER BY k_viaje;

SELECT * FROM mydb.bicicleta, mydb.estaciotienebicicleta, mydb.estacion Where mydb.bicicleta.k_bicicleta = mydb.estaciotienebicicleta.fk_bicicleta and mydb.estaciotienebicicleta.fk_estacion = mydb.estacion.k_estacion;

delete from mydb.bicicleta;

delete from mydb.estaciotienebicicleta;
delete from mydb.estaciotienebicicleta_has_viaje;
delete from mydb.viaje;
delete from mydb.estacion;

delete from mydb.estaciotienebicicleta where mydb.estaciotienebicicleta.fk_estacion = 3 and mydb.estaciotienebicicleta.fk_bicicleta = 11;

SELECT * FROM mydb.estaciotienebicicleta WHERE fk_estacion = 3;

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


INSERT INTO mydb.viaje VALUES(1, null, NOW(), null, 1);

INSERT INTO mydb.estaciotienebicicleta_has_viaje VALUES(1, 1, 1, false);
INSERT INTO mydb.estaciotienebicicleta_has_viaje VALUES(1, 2, 1);


UPDATE mydb.viaje SET q_anclajesdisponibles = 0, q_anclajestotales = 0,
  n_direccion = 'pepe', localidad_k_localidad = 1 WHERE k_estacion = 2;
