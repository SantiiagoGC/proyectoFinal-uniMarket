insert into usuario values ("1007531125", "juanf.londonob@uqvirtual.edu.co", "Juan Londoño","JuanL", "123456", "Carrera 15 # 10N - 35", "3103003738");
insert into usuario values ("1105611510", "mairamandez@uqvirtual.edu.co", "Maira Mendez", "MairaM", "123456", "Carrera 15 # 10N - 35", "3104861412");
insert into usuario values ("1007531127", "juanfranciscol16@hotmail.com", "Aura Londoño", "AuraL", "123456", "Carrera 4 # 6 - 38", "3103003739");

insert into producto values(1, 1, "Jabon 2x1", "2022/10/02", "2023/12/04", "Jabon", 12300, 2, "1007531125");
insert into producto values(2, 1, "iphone 7 plus de 128 gb", "2023/01/06", "2023/12/08", "Televisor 7 plus", 1200000, 1, "1007531125");
insert into producto values(3, 1, "Memoria ram 8GB 3200mhz", "2023/01/06", "2023/12/08", "Televisor Ram 8gb", 1200000, 1, "1010066053");

insert into categoria values (1, "Hogar", 1);
insert into categoria values (2, "Tecnología", 2);
insert into categoria values (3, "Tecnología", 3);

insert into comentario values (1, "2023/02/04", "Muy malo", 2, "1007531125");
insert into comentario values (2, "2023/02/05", "Muy bueno", 2, "1105611510");
insert into comentario values (3, "2023/03/09", "Retracto lo dicho, muy bueno", 2, "1007531125");
insert into comentario values (4, "2023/02/09", "Recomendado", 3, "1010066053");

insert into compra values (1, "2022/10/05", "tarjeta", 12300, "1007531125");
insert into compra values (2, "2022/10/05", "tarjeta", 12300, "1007531125");

insert into detalle_compra values (1, 12300, 1, 1, 1);
insert into detalle_compra values (2, 12300, 1, 2, 1);

insert into moderador values ("28948972", "carmenza@uqvirtual.edu.co", "Carmenza Bastidas", "CarmenB", "123456");

insert into favorito values (1, 2, "1007531125");
insert into favorito values (2, 1, "1007531125");

insert into estado values (1, "Activo");
insert into producto_moderador values (1, "2023/10/05", "porque si", 1, "28948972", 1 );
insert into producto_moderador values (2, "2023/10/06", "porque santi es gei", 1, "28948972", 2 )