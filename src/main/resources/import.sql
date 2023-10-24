insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');


insert into forma_pagamento(descricao) values ('Cartão de Crédito');
insert into forma_pagamento(descricao) values ('Cartão de Débito');
insert into forma_pagamento(descricao) values ('Pix');

insert into permissao(nome, descricao) values ('Admistrador', 'Todas as funções liberadas');
insert into permissao(nome, descricao) values ('Usuário', 'Funções liberadas sob demanda');

insert into estado(nome) values ('Mato Grosso');
insert into estado(nome) values ('São Paulo');

insert into cidade(nome, estado_id) values ('Cuiabá', 1);
insert into cidade(nome, estado_id) values ('Santo André', 2);

insert into restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Marmitaria Sinhá', 0, 1, "Vista Alegre", "78085-340", "Casa portão branco", "Rua", "132", 1, utc_timestamp, utc_timestamp);
insert into restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Zebu', 12, 2, "Pedra 90", "78085-340", "Casa fechado", "Rua", "22", 1, utc_timestamp, utc_timestamp);

insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,1), (2, 2);

