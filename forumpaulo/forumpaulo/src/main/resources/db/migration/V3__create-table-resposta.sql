create table resposta(

  id bigint not null auto_increment,
   messagem varchar(255) not null,
  topico varchar(100) not null,
  datacriacao date not null,
autor varchar(100) not null,
solucao varchar(255) not null,

    primary key(id)

);
