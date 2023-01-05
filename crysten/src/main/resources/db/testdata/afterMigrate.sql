set foreign_key_checks = 0;

lock tables cliente write, funcionario write;

delete from cliente;
delete from funcionario;

set foreign_key_checks = 1;

alter table cliente auto_increment = 1;
alter table funcionario auto_increment = 1;

INSERT INTO cliente
(bairro, cep, complemento, localidade, logradouro, uf, nome_completo, cpf, numero)
VALUES('Habitasa', '69905104', 'Rua Castanheira', 'Rio Branco', '', 'SP', 'Mellicent Cummungs', '67900069070', 1);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('78043914', 'Avenida José Monteiro de Figueiredo 510', 'Duque de Caxias I', 'norte', 'SP', '', 'Eva Petican', '86811497092', 2);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('17920970', 'Avenida Brasil 764', 'Centro', 'Ouro Verde', 'SP', '', 'Julieta Lownie', '87931201094', 3);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('15480970', 'Avenida Vereador Osvaldo Kushida 505', 'Centro', 'Orindiúva', 'SP', '', 'Mellicent Cummungs', '80969042051', 4);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('15340970', 'Rua Pedro Pereira Dias 1859', 'Centro', 'Nova Luzitânia', 'SP', '', 'Ninon Richichi', '37904558068', 5);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('17860970', 'Avenida Vereador J. Gomes Duda 873', 'Centro', 'Pacaembu', 'SP', '', 'Fedora Kremer', '39187934000', 6);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('17810970', 'Rua Roberto Nelchert 715', 'Centro', 'Mariápolis', 'SP', '', 'Kennan Brittin', '96122680028', 7);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('15310970', 'Rua Sete de Setembro 1087', 'Centro', 'Mágda', 'SP', '', 'Olympia Henriet', '67699257022', 8);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('16460970', 'Avenida Joaquim Grotão 186', 'Macucos', 'Macucos', 'SP', '', 'Engracia Baldin', '22008837068', 9);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento,  nome_completo, cpf, numero)
VALUES('15620970', 'Rua Deputado Cunha Bueno 360', 'Centro', 'Macedônia', 'SP', '', 'Paulita Poinsett', '27952108000', 10);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('19750970', 'Rua Henrique Botteri 403', 'Centro', 'Lutécia', 'SP', '', 'Marshall Eayrs', '46169679034', 11);

INSERT INTO cliente
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('19750970', 'Rua Henrique Botteri 403', 'Centro', 'Lutécia', 'SP', '', 'Sallyanne Cutler', '34918029035', 12);






INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('19750970', 'Rua Henrique Botteri 403', 'Centro', 'Lutécia', 'SP', '', 'Nicky Orans', '11995334081', 1);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('14775970', 'Rua Jaime Nicolau Martins 601', 'Centro', 'Jaborandi', 'SP', '', 'Monika Andryushchenko', '13960221045', 2);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('14500972', 'Rua Jovina Trajano Borges 1150', 'Centro', 'Ituverava', 'SP', '', 'Thorvald Boucher', '14179364018', 3);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('13295970', 'Rua Emilia Muraro Vanini 45', 'Parque Amarylis', 'Itupeva', 'SP', '', 'Tiphany Dedam', '59634064043', 4);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('14420970', 'Rua Dozito Malvar Ribas 5000', 'Centro', 'Itirapuã', 'SP', '', 'Olva Horry', '00172998026', 5);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('08598388', 'Rua Raul Pompéia', 'Jardim Altos de Itaquá', 'Itaquaquecetuba', 'SP', '', 'Dirk Fourcade', '46651488058', 6);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('14420970', 'Rua Dozito Malvar Ribas 5000', 'Centro', 'Itirapuã', 'SP', '', 'Rheta Vasnev', '28070312009', 7);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('19750970', 'Rua Henrique Botteri 403', 'Centro', 'Lutécia', 'SP', '', 'Chip Scoullar', '86520896047', 8);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('17260970', 'Rua João Batista Módolo 268', 'Centro', 'Itaju', 'SP', '', 'Sonny Husthwaite', '37117982098', 9);

INSERT INTO funcionario
(cep, logradouro, bairro, localidade, uf, complemento, nome_completo, cpf, numero)
VALUES('19750970', 'Rua Henrique Botteri 403', 'Centro', 'Lutécia', 'SP', '', 'Lulita Cleen', '07494292080', 10);



unlock tables;