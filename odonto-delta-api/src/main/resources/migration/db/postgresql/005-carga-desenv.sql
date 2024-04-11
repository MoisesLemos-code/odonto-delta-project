INSERT INTO odonto.tb_plano (pla_id, pla_ciclo, pla_descricao, pla_valor,
pla_disponivel, pla_dthr_cadastro, pla_dthr_alteracao, pla_usuario_cadastro,
pla_usuario_alteracao) VALUES (nextval('odonto.seq_plano'), 'MENSAL', 'Plano Profissional', 250,
                               true, null, null, null, null);

INSERT INTO odonto.tb_empresa (em_id, em_nome_fantasia, em_cnpj_cpf, em_tipo_pessoa,
em_email, em_razao_social, em_logra, em_logra_num, mu_id, em_telefone, em_insc_est, pla_id,
em_dthr_cadastro, em_dthr_alteracao, em_usuario_cadastro, em_usuario_alteracao)
VALUES (nextval('odonto.seq_empresa'), 'Sistemas Molens', '66959004000100', 'J', 'moisesconta4@outlook.com',
        'Sistemas Molens LTDA', null, null, 1301, '67998483400', null, 1, null, null, null, null);


INSERT INTO odonto.tb_usuario (us_id, us_login, us_nome, us_cnpj_cpf, us_email, mu_id, em_id, us_situacao, us_cargo,
us_senha)
VALUES (nextval('odonto.seq_usuario'), 'admin', 'Administrador', '11122233344', 'admin_molens@molens.com', 1301, 1, true, 'Administrador do sistema',
        '$2a$10$c3LO9Q1a7woNhtNey/N.8egG9Ffyp80aW3gRPybcaS1oChU51fp4i');

INSERT INTO odonto.tb_perfil (pf_id, pf_nome, pf_descricao, "em_id", "pf_ativo")
VALUES (nextval('odonto.seq_perfil'), 'Administrador', 'Administrador do sistema.', 1, true);

INSERT INTO odonto.tb_perfil (pf_id, pf_nome, pf_descricao, "em_id", "pf_ativo")
VALUES (nextval('odonto.seq_perfil'), 'Recepcionista', 'Realiza cadastros no geral.', 1, true);

INSERT INTO odonto.tb_perfil_permissao (pp_id, pf_id, pe_id, "pp_ativo")
VALUES (nextval('odonto.seq_perfil_permissao'), (select pf_id from odonto.tb_perfil where pf_nome = 'Administrador' and em_id = 1),
        (select pe_id from odonto.tb_permissao where pe_nome = 'ADMINISTRADOR'), true);

INSERT INTO odonto.tb_perfil_usuario(pf_id, us_id)
VALUES ((select pf_id from odonto.tb_perfil where pf_nome = 'Administrador' and em_id = 1),
        (select us_id from odonto.tb_usuario where us_nome = 'Administrador' and em_id = 1));