insert into usuario values ("1007531125", "juanf.londonob@uqvirtual.edu.co", "Juan Londoño","JuanL", "123456", "Carrera 15 # 10N - 35", "3103003738");
insert into usuario values ("1105611510", "mairamandez@uqvirtual.edu.co", "Maira Mendez", "MairaM", "123456", "Carrera 15 # 10N - 35", "3104861412");
insert into usuario values ("1007531127", "juanfranciscol16@hotmail.com", "Aura Londoño", "AuraL", "123456", "Carrera 4 # 6 - 38", "3103003739");
insert into usuario values ("1010066053", "santii0628@gmail.com", "Santiago Garcia", "Santii0628", "123456", "Carrera 11 #5-42", "3234327001");

insert into producto values("123", 1, "Jabon 2x1", "2022/10/02", "2023/01/04", "Jabon", 12300, 2, "1007531125");
insert into producto values("124", 1, "iphone 7 plus de 128 gb", "2023/01/06", "2023/12/08", "Iphone 7 plus", 1200000, 1, "1007531125");
insert into producto values("125", 1, "Memoria ram 8GB 3200mhz", "2023/01/06", "2023/12/08", "Memoria Ram 8gb", 150000, 1, "1010066053");

insert into comentario values (1, "2023/02/04", "Muy malo", "124", "1007531125");
insert into comentario values (2, "2023/02/05", "Muy bueno", "124", "1105611510");
insert into comentario values (3, "2023/03/09", "Retracto lo dicho, muy bueno", "124", "1007531125");
insert into comentario values (4, "2023/02/09", "Recomendado", "125", "1010066053");

insert into compra values (1, "2022/10/05", "tarjeta", 12300, "1007531125");
insert into compra values (2, "2022/10/05", "tarjeta", 12300, "1007531125");

insert into detalle_compra values (1, 12300, 1, 1, "123");
insert into detalle_compra values (2, 12300, 1, 2, "123");

insert into moderador values ("28948972", "carmenza@uqvirtual.edu.co", "Carmenza Bastidas", "CarmenB", "123456");

insert into favorito values (1, "124", "1007531125");
insert into favorito values (2, "123", "1007531125");