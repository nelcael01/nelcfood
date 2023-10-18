insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');

insert into restaurante(nome, taxa_frete, cozinha_id) values ('Marmitaria Sinhá', 0, 1);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Zebu', 10, 2);

insert into forma_pagamento(descricao) values ('Cartão de Crédito');
insert into forma_pagamento(descricao) values ('Cartão de Débito');

insert into permissao(nome, descricao) values ('Admistrador', 'Todas as funções liberadas');
insert into permissao(nome, descricao) values ('Usuário', 'Funções liberadas sob demanda');

insert into estado(nome) values ('Mato Grosso');
insert into estado(nome) values ('São Paulo');

insert into cidade(nome, estado_id) values ('Cuiabá', 1);
insert into cidade(nome, estado_id) values ('Santo André', 2);

