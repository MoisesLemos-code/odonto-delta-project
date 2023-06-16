INSERT INTO odonto.tb_plano (pla_id, pla_ciclo, pla_descricao, pla_valor,
pla_disponivel, pla_dthr_cadastro, pla_dthr_alteracao, pla_usuario_cadastro,
pla_usuario_alteracao) VALUES (nextval('odonto.seq_plano'), 'MENSAL', 'Plano Profissional', 250,
                               true, null, null, null, null);


INSERT INTO odonto.tb_empresa (em_id, em_nome_fantasia, em_cnpj_cpf, em_tipo_pessoa,
em_email, em_razao_social, em_logra, em_logra_num, mu_id, em_telefone, em_insc_est, pla_id,
em_dthr_cadastro, em_dthr_alteracao, em_usuario_cadastro, em_usuario_alteracao)
VALUES (nextval('odonto.seq_empresa'), 'Sistemas Molens', '66959004000100', 'J', 'moisesconta4@outlook.com',
        'Sistemas Molens LTDA', null, null, 1301, '67998483400', null, 1, null, null, null, null);
