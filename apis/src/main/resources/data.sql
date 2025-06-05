-- Carga inicial tabela nome
INSERT INTO nome (nome_completo, nome_paz) VALUES
    ('Carlos', 'Carlos Silva'),
    ('Ana', 'Ana Oliveira'),
    ('João', 'João Souza'),
    ('Paulo', 'Paulo Lima'),
    ('Mariana', 'Mariana Costa'),
    ('Rafael', 'Rafael Mendes'),
    ('Camila', 'Camila Rocha'),
    ('Roberto', 'Roberto Almeida'),
    ('Fernanda', 'Fernanda Santos'),
    ('André', 'André Carvalho'),
    ('Letícia', 'Letícia Pereira'),
    ('Bruno', 'Bruno Martins'),
    ('Carla', 'Carla Vieira'),
    ('Lucas', 'Lucas Borges'),
    ('Juliana', 'Juliana Barros'),
    ('Marcos', 'Marcos Tavares'),
    ('Alice', 'Alice Nunes'),
    ('João', 'João Pinto'),
    ('Sofia', 'Sofia Melo'),
    ('Henrique', 'Henrique Lemos'),
    ('Pedro', 'Pedro Silva'),
    ('Joana', 'Joana Ferreira'),
    ('Gustavo', 'Gustavo Ramos'),
    ('Adriana', 'Adriana Lima'),
    ('Felipe', 'Felipe Castro'),
    ('Aline', 'Aline Monteiro'),
    ('Thiago', 'Thiago Gomes'),
    ('Bianca', 'Bianca Farias');

-- Carga inicial tabela telefone
INSERT INTO telefone (ddd1, numero1, ddd2, numero2) VALUES
    ('85', '000000000', NULL, NULL),
    ('85', '000000001', NULL, NULL),
    ('85', '000000002', NULL, NULL),
    ('85', '000000003', NULL, NULL),
    ('85', '000000004', NULL, NULL),
    ('85', '000000005', NULL, NULL),
    ('85', '000000006', NULL, NULL),
    ('85', '000000007', NULL, NULL),
    ('85', '000000008', NULL, NULL),
    ('85', '000000009', NULL, NULL),
    ('85', '000000010', NULL, NULL),
    ('85', '000000011', NULL, NULL),
    ('85', '000000012', NULL, NULL),
    ('85', '000000013', NULL, NULL),
    ('85', '000000014', NULL, NULL),
    ('85', '000000015', NULL, NULL),
    ('85', '000000016', NULL, NULL),
    ('85', '000000017', NULL, NULL),
    ('85', '000000018', NULL, NULL),
    ('85', '000000019', NULL, NULL),
    ('85', '000000020', NULL, NULL),
    ('85', '000000021', NULL, NULL),
    ('85', '000000022', NULL, NULL),
    ('85', '000000023', NULL, NULL),
    ('85', '000000024', NULL, NULL),
    ('85', '000000025', NULL, NULL),
    ('85', '000000026', NULL, NULL),
    ('85', '000000027', NULL, NULL),
    ('85', '000000028', NULL, NULL);

-- Carga inicial tabela email
INSERT INTO email (profissional, pessoal) VALUES
    ('tenente@example.com', NULL),
    ('subtenente1@example.com', NULL),
    ('subtenente2@example.com', NULL),
    ('sargento1@example.com', NULL),
    ('sargento2@example.com', NULL),
    ('sargento3@example.com', NULL),
    ('sargento4@example.com', NULL),
    ('cabo1@example.com', NULL),
    ('cabo2@example.com', NULL),
    ('cabo3@example.com', NULL),
    ('cabo4@example.com', NULL),
    ('cabo5@example.com', NULL),
    ('cabo6@example.com', NULL),
    ('cabo7@example.com', NULL),
    ('cabo8@example.com', NULL),
    ('cabo9@example.com', NULL),
    ('cabo10@example.com', NULL),
    ('cabo11@example.com', NULL),
    ('cabo12@example.com', NULL),
    ('cabo13@example.com', NULL),
    ('soldado1@example.com', NULL),
    ('soldado2@example.com', NULL),
    ('soldado3@example.com', NULL),
    ('soldado4@example.com', NULL),
    ('soldado5@example.com', NULL),
    ('soldado6@example.com', NULL),
    ('soldado7@example.com', NULL),
    ('soldado8@example.com', NULL);

-- Carga inicial tabela tipo de endereco
INSERT INTO endereco (logradouro, numero, complemento, bairro, cep, municipio, sigla_uf) VALUES
    (NULL, NULL, NULL, NULL, NULL, 'Limoeiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Russas', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Morada Nova', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Quixeré', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Fortaleza', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Tabuleiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Jaguaruana', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'São João do Jaguaribe', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Limoeiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Russas', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Morada Nova', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Quixeré', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Fortaleza', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Tabuleiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Jaguaruana', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'São João do Jaguaribe', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Limoeiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Russas', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Morada Nova', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Quixeré', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Limoeiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Russas', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Morada Nova', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Quixeré', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Limoeiro do Norte', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Russas', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Morada Nova', 'CE'),
    (NULL, NULL, NULL, NULL, NULL, 'Quixeré', 'CE');

-- Carga inicial tabela patente
INSERT INTO patente (dado_patente, folga_especial) VALUES
    ('TEN', 0),
    ('SUBTEN', 0),
    ('SGT', 0),
    ('CB', 0),
    ('SD', 0);

-- Carga inicial tabela tipo de militares
INSERT INTO militar (
    matricula, cpf, id_nome, nascimento, sexo, id_telefone,
    id_email, id_endereco, id_patente, antiguidade, folga_especial, escalavel, cov
    ) 
    VALUES
    -- 01 Tenente
    ('REG874', '00123456789', 1, '1980-01-01', 'M', 1, 1, 1, 1, 1, 0, TRUE, FALSE),
    -- 02 Subtenente
    ('REG532', '00023456789', 2, '1982-02-02', 'F', 2, 2, 2, 2, 2, 0, TRUE, FALSE),
    ('REG687', '00003456789', 3, '1984-03-03', 'M', 3, 3, 3, 2, 3, 0, TRUE, FALSE),
    -- 03 Sargentos 
    ('REG920', '00000456789', 4, '1986-04-04', 'M', 4, 4, 4, 3, 4, 0, TRUE, TRUE),
    ('REG385', '00000056789', 5, '1988-05-05', 'F', 5, 5, 5, 3, 5, 0, TRUE, TRUE),
    ('REG741', '00000006789', 6, '1990-06-06', 'M', 6, 6, 6, 3, 6, 0, TRUE, FALSE),
    ('REG829', '00000000789', 7, '1992-07-07', 'F', 7, 7, 7, 3, 7, 0, TRUE, FALSE),
    -- 13 Cabos
    ('REG273', '00000000089', 8, '1994-09-10', 'M', 8, 8, 8, 4, 8, 0, TRUE, TRUE),
    ('REG104', '00000000009', 9, '1996-09-09', 'F', 9, 9, 9, 4, 9, 0, TRUE, TRUE),
    ('REG672', '00000000000', 10, '1998-10-10', 'M', 10, 10, 10, 4, 10, 0, TRUE, TRUE),
    ('REG835', '00123456780', 11, '2000-11-11', 'F', 11, 11, 11, 4, 11, 0, TRUE, FALSE),
    ('REG329', '00123456700', 12, '2002-12-12', 'M', 12, 12, 12, 4, 12, 0, TRUE, FALSE),
    ('REG450', '00123456000', 13, '2004-01-13', 'F', 13, 13, 13, 4, 13, 0, TRUE, FALSE),
    ('REG582', '00123450000', 14, '2006-02-14', 'M', 14, 14, 14, 4, 14, 0, TRUE, FALSE),
    ('REG193', '00123400000', 15, '2008-03-15', 'F', 15, 15, 15, 4, 15, 0, TRUE, FALSE),
    ('REG405', '00123000000', 16, '1994-03-01', 'M', 16, 16, 16, 4, 16, 0, TRUE, FALSE),
    ('REG764', '00120000000', 17, '1995-02-11', 'F', 17, 17, 17, 4, 17, 0, TRUE, FALSE),
    ('REG328', '00100000000', 18, '1997-06-23', 'M', 18, 18, 18, 4, 18, 0, TRUE, FALSE),
    ('REG412', '01234567890', 19, '1999-09-12', 'F', 19, 19, 19, 4, 19, 0, FALSE, FALSE),
    ('REG713', '00234567890', 20, '2001-01-17', 'M', 20, 20, 20, 4, 20, 0, TRUE, FALSE),
    -- 08 Soldados
    ('REG418', '00034567890', 21, '1990-04-16', 'M', 21, 21, 21, 5, 21, 0, TRUE, TRUE),
    ('REG294', '12345678901', 22, '1991-05-17', 'F', 22, 22, 22, 5, 22, 0, TRUE, TRUE),
    ('REG561', '23456789012', 23, '1992-06-18', 'M', 23, 23, 23, 5, 23, 0, TRUE, FALSE),
    ('REG873', '34567890123', 24, '1993-07-19', 'F', 24, 24, 24, 5, 24, 0, TRUE, FALSE),
    ('REG690', '45678901234', 25, '1994-08-20', 'M', 25, 25, 25, 5, 25, 0, TRUE, FALSE),
    ('REG234', '56789012345', 26, '1995-09-21', 'F', 26, 26, 26, 5, 26, 0, TRUE, FALSE),
    ('REG348', '67890123456', 27, '1996-10-22', 'M', 27, 27, 27, 5, 27, 0, FALSE, FALSE),
    ('REG712', '78901234567', 28, '1997-11-23', 'F', 28 ,28 ,28 ,5 ,28 ,0 ,TRUE ,FALSE);

-- Carga inicial de ausencias
INSERT INTO ausencia (data_inicio, data_fim, motivo, descricao, matricula_militar) VALUES
    ('2024-09-01', '2024-09-30', 'ferias', NULL, 'REG234'),
    ('2024-09-01', '2024-09-30', 'ferias', NULL, 'REG328'),
    ('2024-09-01', '2024-09-30', 'ferias', NULL, 'REG385');