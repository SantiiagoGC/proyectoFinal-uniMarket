insert into usuario values ("1007531125", "juanf.londonob@uqvirtual.edu.co", "Juan Londoño","JuanL", "123456", "Carrera 15 # 10N - 35", "3103003738");
insert into usuario values ("1105611510", "mairamandez@uqvirtual.edu.co", "Maira Mendez", "MairaM", "123456", "Carrera 15 # 10N - 35", "3104861412");
insert into usuario values ("1007531127", "juanfranciscol16@hotmail.com", "Aura Londoño", "AuraL", "123456", "Carrera 4 # 6 - 38", "3103003739");

insert into producto values("123", 1, "Jabon 2x1", "2022/10/02", "2022/11/04", "Jabon", 12300, 2, "1007531125");
insert into producto values("124", 1, "iphone 7 plus de 128 gb", "2022/05/06", "2022/12/08", "Iphone 7 plus", 1200000, 1, "1007531125");

insert into compra values (1, "2022/10/05", "tarjeta", 12300, "1007531125");
insert into compra values (2, "2022/10/05", "tarjeta", 12300, "1007531125");

insert into detalle_compra values (1, 12300, 1, 1, "123");
insert into detalle_compra values (2, 12300, 1, 2, "123");

insert into moderador values ("28948972", "carmenza@uqvirtual.edu.co", "Carmenza Bastidas", "CarmenB", "123456");

insert into favorito values (1, "124", "1007531125")