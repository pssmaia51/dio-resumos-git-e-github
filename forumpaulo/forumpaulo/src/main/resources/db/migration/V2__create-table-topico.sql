create table topico(

  id bigint not null auto_increment,
   titulo varchar(100) not null,
  messagem varchar(255) not null,
  datacriacao date,
status varchar(20),
autor varchar(100) not null,
curso varchar(50) not null,
resposta varchar(255),




    primary key(id)

);
