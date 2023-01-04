CREATE TABLE `funcionario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `localidade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `uf` varchar(3) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;