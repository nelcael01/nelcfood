create table cozinha(
    id bigint not null auto_increment,
    nome varchar(60) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

create table estado(
    id bigint not null auto_increment,
    nome varchar(80) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

create table cidade(
    id bigint not null auto_increment,
    nome varchar(80) not null,
    estado_id bigint not null,
    foreign key (estado_id) references estado(id),
    primary key(id)
)engine=InnoDB default charset=utf8;

