
--- Insert permissões.
INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'ADMINISTRADOR', 'Usuário administrador.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'FINANCEIRO_CADASTRAR', 'Permite realizar o cadastro de cobranças.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'FINANCEIRO_ATUALIZAR', 'Permite atualizar o cadastro de cobranças.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'FINANCEIRO_DELETAR', 'Permite excluir o cadastro de cobranças.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'FINANCEIRO_ESTORNO', 'Permite estornar o pagamento de cobranças.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'PACIENTE_CADASTRAR', 'Permite realizar cadastros de pacientes.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'PACIENTE_ATUALIZAR', 'Permite atualizar cadastros de pacientes.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'PACIENTE_DELETAR', 'Permite excluir cadastros de pacientes.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'USUARIO_CADASTRAR', 'Permite realizar cadastros de usuários.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'USUARIO_ATUALIZAR', 'Permite atualizar cadastros de usuários.');

INSERT INTO odonto.tb_permissao (pe_id, pe_nome, pe_descricao)
VALUES (nextval('odonto.seq_permissao'), 'USUARIO_DELETAR', 'Permite excluir cadastros de usuários.');

