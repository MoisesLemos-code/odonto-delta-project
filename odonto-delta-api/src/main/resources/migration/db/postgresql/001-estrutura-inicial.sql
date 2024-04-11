/*
Created: 25/05/2023
Modified: 25/05/2023
Project: Odonto Delta
Model: OdontoDelta_postgres10
Company: Molens
Database: PostgreSQL 10
*/


-- Create schemas section -------------------------------------------------
COMMENT ON SCHEMA "odonto" IS 'standard odonto schema';

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "odonto"."seq_plano"
  INCREMENT BY 1
  START WITH 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

CREATE SEQUENCE "odonto"."seq_estado"
  INCREMENT BY 1
  START WITH 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

CREATE SEQUENCE "odonto"."seq_municipio"
  INCREMENT BY 1
  START WITH 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

CREATE SEQUENCE "odonto"."seq_empresa"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_perfil"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_permissao"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_perfil_permissao"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_usuario"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_paciente"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE "odonto"."seq_ficha"
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table odonto.tb_plano

CREATE TABLE "odonto"."tb_plano"
(
  "pla_id" Bigint NOT NULL,
  "pla_ciclo" varchar(255),
  "pla_descricao" varchar(50),
  "pla_valor" numeric,
  "pla_disponivel" Boolean DEFAULT true,
  "pla_dthr_cadastro" timestamp(6),
  "pla_dthr_alteracao" timestamp(6),
  "pla_usuario_cadastro" varchar(255),
  "pla_usuario_alteracao" varchar(255)
)
WITH (
  autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_plano" IS 'Tabela que armazena os planos disponiveis.'
;

COMMENT ON COLUMN "odonto"."tb_plano"."pla_id" IS 'Chave primária da tabela.'
;

COMMENT ON COLUMN "odonto"."tb_plano"."pla_ciclo" IS 'Tipo de ciclo do plano.'
;

COMMENT ON COLUMN "odonto"."tb_plano"."pla_descricao" IS 'Descrição do plano.'
;

COMMENT ON COLUMN "odonto"."tb_plano"."pla_valor" IS 'Valor do plano.'
;

COMMENT ON COLUMN "odonto"."tb_plano"."pla_disponivel" IS 'Indica se o plano está disponível.'
;

ALTER TABLE "odonto"."tb_plano" ADD CONSTRAINT "tb_plano_pkey" PRIMARY KEY ("pla_id")
;

-- Table odonto.tb_estado

CREATE TABLE "odonto"."tb_estado"
(
  "uf_id" Integer NOT NULL,
  "uf_sigla" Character(2) NOT NULL,
  "uf_nome" varchar(20) NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_estado" IS 'Armazena as informacoes dos estados.'
;

COMMENT ON COLUMN "odonto"."tb_estado"."uf_id" IS 'Codigo gerado automaticamente que identifica um unico estado.'
;

COMMENT ON COLUMN "odonto"."tb_estado"."uf_sigla" IS 'Indica a sigla do estado.'
;

COMMENT ON COLUMN "odonto"."tb_estado"."uf_nome" IS 'Indica o nome do estado.'
;

ALTER TABLE "odonto"."tb_estado" ADD CONSTRAINT "pk_uf" PRIMARY KEY ("uf_id")
;

-- Table odonto.tb_municipio

CREATE TABLE "odonto"."tb_municipio"
(
    "mu_id" Integer NOT NULL,
    "mu_nome" varchar(50) NOT NULL,
    "uf_id" Integer,
    "mu_cod_ibge" varchar(7)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_municipio" IS 'Armazena as informacoes dos municipios.'
;

COMMENT ON COLUMN "odonto"."tb_municipio"."mu_id" IS 'Codigo gerado automaticamente que identifica um unico municipio.'
;

COMMENT ON COLUMN "odonto"."tb_municipio"."mu_nome" IS 'Nome do municipio.'
;

COMMENT ON COLUMN "odonto"."tb_municipio"."uf_id" IS 'Código do estado.'
;

ALTER TABLE "odonto"."tb_municipio" ADD CONSTRAINT "pk_municipio" PRIMARY KEY ("mu_id")
;

-- Table odonto.tb_empresa

CREATE TABLE "odonto"."tb_empresa"
(
    "em_id" Integer NOT NULL,
    "em_nome_fantasia" varchar(255) NOT NULL,
    "em_cnpj_cpf" varchar(255) NOT NULL,
    "em_tipo_pessoa" Character(1) NOT NULL,
    "em_email" varchar(50),
    "em_razao_social" varchar(255) NOT NULL,
    "em_logra" varchar(255),
    "em_logra_num" varchar(6),
    "mu_id" Integer,
    "em_telefone" varchar(25),
    "em_insc_est" varchar(20),
    "pla_id" Integer,
    "em_dthr_cadastro" timestamp(6),
    "em_dthr_alteracao" timestamp(6),
    "em_usuario_cadastro" varchar(255),
    "em_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_empresa" IS 'Armazena os dados da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_id" IS 'Codigo gerado automaticamente que identifica uma unica empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_nome_fantasia" IS 'Nome fantasia da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_cnpj_cpf" IS 'CNPJ ou CPF da entidade empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_tipo_pessoa" IS 'Tipo de pessoa da entidade empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_email" IS 'E-mail da entidade empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_razao_social" IS 'Razão social da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_logra" IS 'Logradouro da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_logra_num" IS 'Número do logradouro.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."mu_id" IS 'Múnicipio de onde se encontra a empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_telefone" IS 'Número de telefone da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_insc_est" IS 'Inscrição estadual da empresa.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."pla_id" IS 'Código do plano vinculado a empresa.'
;


CREATE UNIQUE INDEX "uk_empresa" ON "odonto"."tb_empresa" ("em_cnpj_cpf")
;
ALTER TABLE "odonto"."tb_empresa" ADD CONSTRAINT "pk_empresa" PRIMARY KEY ("em_id")
;

-- Table odonto.tb_perfil

CREATE TABLE "odonto"."tb_perfil"
(
    "pf_id" Integer NOT NULL,
    "pf_nome" varchar(255) NOT NULL,
    "pf_descricao" varchar(255) NOT NULL,
    "em_id" Integer,
    "pf_ativo" Boolean DEFAULT true,
    "pf_dthr_cadastro" timestamp(6),
    "pf_dthr_alteracao" timestamp(6),
    "pf_usuario_cadastro" varchar(255),
    "pf_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_perfil" IS 'Armazena os dados de perfil.'
;

COMMENT ON COLUMN "odonto"."tb_perfil"."pf_id" IS 'Codigo gerado automaticamente que identifica um perfil.'
;

COMMENT ON COLUMN "odonto"."tb_perfil"."pf_nome" IS 'Nome do perfil.'
;

COMMENT ON COLUMN "odonto"."tb_perfil"."pf_descricao" IS 'Descrição do perfil.'
;

COMMENT ON COLUMN "odonto"."tb_empresa"."em_id" IS 'Código referente a empresa.'
;

COMMENT ON COLUMN "odonto"."tb_perfil"."pf_ativo" IS 'Indica se o perfil está ativo ou não.'
;

ALTER TABLE "odonto"."tb_perfil" ADD CONSTRAINT "pk_perfil" PRIMARY KEY ("pf_id")
;

-- Table odonto.tb_permissao

CREATE TABLE "odonto"."tb_permissao"
(
    "pe_id" Integer NOT NULL,
    "pe_nome" varchar(255) NOT NULL,
    "pe_descricao" varchar(255) NOT NULL,
    "pe_dthr_cadastro" timestamp(6),
    "pe_dthr_alteracao" timestamp(6),
    "pe_usuario_cadastro" varchar(255),
    "pe_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_permissao" IS 'Armazena os dados de permissão.'
;

COMMENT ON COLUMN "odonto"."tb_permissao"."pe_id" IS 'Codigo gerado automaticamente que identifica uma permissão.'
;

COMMENT ON COLUMN "odonto"."tb_permissao"."pe_nome" IS 'Nome da permissão.'
;

COMMENT ON COLUMN "odonto"."tb_permissao"."pe_descricao" IS 'Descrição da permissão.'
;

ALTER TABLE "odonto"."tb_permissao" ADD CONSTRAINT "pk_tb_permissao" PRIMARY KEY ("pe_id")
;

-- Table odonto.tb_perfil_permissao

CREATE TABLE "odonto"."tb_perfil_permissao"
(
    "pp_id" Integer NOT NULL,
    "pf_id" Integer NOT NULL,
    "pe_id" Integer NOT NULL,
    "pp_ativo" Boolean DEFAULT true,
    "pp_dthr_cadastro" timestamp(6),
    "pp_dthr_alteracao" timestamp(6),
    "pp_usuario_cadastro" varchar(255),
    "pp_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_perfil_permissao" IS 'Armazena os dados de permissão.'
;

COMMENT ON COLUMN "odonto"."tb_perfil_permissao"."pp_id" IS 'Codigo gerado automaticamente que identifica uma permissão de perfil.'
;
COMMENT ON COLUMN "odonto"."tb_perfil_permissao"."pf_id" IS 'Codigo referente a um perfil.'
;
COMMENT ON COLUMN "odonto"."tb_perfil_permissao"."pe_id" IS 'Codigo referente a uma permissão.'
;

ALTER TABLE "odonto"."tb_perfil_permissao" ADD CONSTRAINT "pk_tb_perfil_permissao" PRIMARY KEY ("pp_id")
;

-- Table odonto.tb_perfil_usuario

CREATE TABLE "odonto"."tb_perfil_usuario"
(
    "pf_id" Integer NOT NULL,
    "us_id" Integer NOT NULL,
    "pfu_dthr_cadastro" timestamp(6),
    "pfu_dthr_alteracao" timestamp(6),
    "pfu_usuario_cadastro" varchar(255),
    "pfu_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_perfil_usuario" IS 'Armazena os dados de perfil item.'
;

COMMENT ON COLUMN "odonto"."tb_perfil_usuario"."pf_id" IS 'Código referente ao perfil.'
;

COMMENT ON COLUMN "odonto"."tb_perfil_usuario"."us_id" IS 'Código referente ao usuario.'
;

CREATE INDEX "in_perfil_usuario2" ON "odonto"."tb_perfil_usuario" ("us_id")
;

ALTER TABLE "odonto"."tb_perfil_usuario" ADD CONSTRAINT "pk_perfil_usuario" PRIMARY KEY ("pf_id", "us_id")
;

-- Table odonto.tb_usuario

CREATE TABLE "odonto"."tb_usuario"
(
    "us_id" Integer NOT NULL,
    "us_login" varchar(255) NOT NULL,
    "us_nome" varchar(100) NOT NULL,
    "us_email" varchar(100) NOT NULL,
    "us_cnpj_cpf" varchar(255) NOT NULL,
    "us_telefone" varchar(15),
    "mu_id" Integer,
    "us_cep" varchar(11),
    "us_bairro" varchar(50),
    "us_logra" varchar(70),
    "us_logra_num" varchar(6),
    "us_complemento" varchar(50),
    "em_id" Integer,
    "us_situacao" Boolean DEFAULT true,
    "us_cargo" varchar(250),
    "us_departamento" varchar(255),
    "us_senha" varchar(200),
    "us_dthr_ultimo_acesso" Timestamp(6),
    "us_dthr_cadastro" timestamp(6),
    "us_dthr_alteracao" timestamp(6),
    "us_usuario_cadastro" varchar(255),
    "us_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_usuario" IS 'Armazena os usuarios do sistema.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_id" IS 'Codigo gerado automaticamente que identifica um unico usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_login" IS 'Login para acesso ao sistema do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_nome" IS 'Nome do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_email" IS 'Email do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_cnpj_cpf" IS 'CNPJ/CPF do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_telefone" IS 'Telefone de contato do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."mu_id" IS 'Codigo do municipio de endereco do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_cep" IS 'CEP do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_bairro" IS 'Bairro de endereco do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_logra" IS 'Endereco do usuario(Rua, Avenida, Travessa, etc).'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_logra_num" IS 'Numero de endereco do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_complemento" IS 'Complemento de endereco do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."em_id" IS 'Codigo da empresa relacionada ao usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_situacao" IS 'Ativo; Inativo.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_cargo" IS 'Cargo do usuario.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_departamento" IS 'Departamento em que o usuario trabalha.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_senha" IS 'Senha do usuario para acessar o sistema.'
;

COMMENT ON COLUMN "odonto"."tb_usuario"."us_dthr_ultimo_acesso" IS 'Data do último acesso do usuário no sistema.'
;

CREATE UNIQUE INDEX "uk_usuario" ON "odonto"."tb_usuario" ("us_login")
;
ALTER TABLE "odonto"."tb_usuario" ADD CONSTRAINT "pk_tb_usuario" PRIMARY KEY ("us_id")
;

-- Table odonto.tb_paciente

CREATE TABLE "odonto"."tb_paciente"
(
    "pa_id" Integer NOT NULL,
    "pa_nome" varchar(255) NOT NULL,
    "pa_email" varchar(100),
    "pa_cnpj_cpf" varchar(255) NOT NULL,
    "pa_telefone" varchar(15),
    "pa_rg" varchar(15),
    "pa_data_nascimento" DATE,
    "mu_id" Integer,
    "pa_cep" varchar(11),
    "pa_bairro" varchar(50),
    "pa_logra" varchar(70),
    "pa_logra_num" varchar(6),
    "pa_complemento" varchar(50),
    "em_id" Integer,
    "pa_situacao" varchar(50),
    "pa_ortodontia" Boolean DEFAULT false,
    "pa_dthr_ultima_visita" timestamp(6),
    "pa_dthr_cadastro" timestamp(6),
    "pa_dthr_alteracao" timestamp(6),
    "pa_usuario_cadastro" varchar(255),
    "pa_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_paciente" IS 'Armazena os pacientes.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_id" IS 'Codigo gerado automaticamente que identifica um paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_nome" IS 'Nome do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_email" IS 'E-mail do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_cnpj_cpf" IS 'CNPJ/CPF do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_telefone" IS 'Telefone do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_rg" IS 'RG do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_data_nascimento" IS 'Data de nascimento do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."mu_id" IS 'Codigo do municipio de endereco do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_cep" IS 'CEP do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_bairro" IS 'Bairro de endereco do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_logra" IS 'Endereco do paciente (Rua, Avenida, Travessa, etc).'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_logra_num" IS 'Numero de endereco do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_complemento" IS 'Complemento de endereco do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."em_id" IS 'Codigo da empresa relacionada ao paciente.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_situacao" IS 'ATIVO; INATIVO.'
;

COMMENT ON COLUMN "odonto"."tb_paciente"."pa_dthr_ultima_visita" IS 'Data da última visita do paciente no sistema.'
;

CREATE UNIQUE INDEX "uk_paciente_cnpj_cpf" ON "odonto"."tb_paciente" ("pa_cnpj_cpf")
;

ALTER TABLE "odonto"."tb_paciente" ADD CONSTRAINT "pk_tb_paciente" PRIMARY KEY ("pa_id")
;

-- Table odonto.tb_ficha

CREATE TABLE "odonto"."tb_ficha"
(
    "fi_id" Integer NOT NULL,
    "pa_id" Integer,
    "fi_descricao" varchar(150),
    "fi_tratamento" Boolean,
    "fi_outros_medicamentos" varchar(255),
    "fi_anestesia_local" Boolean,
    "fi_caso_positivo_anestesia_local" Boolean,
    "fi_uso_antibiotico" Boolean,
    "fi_caso_positivo_antibiotico" Boolean,
    "fi_nome_antibiotico" varchar(150),
    "fi_alergia_medicamento" Boolean,
    "fi_nome_medicameto_alergia" varchar(150),
    "fi_cicatrizacao_normal" Boolean,
    "fi_tique_mania" Boolean,
    "fi_qual_tique_mania" varchar(150),
    "fi_esta_gravida" Boolean,
    "fi_fumante" Boolean,
    "fi_outras_informacoes_importantes" varchar(255),
    "fi_dthr_cadastro" timestamp(6),
    "fi_dthr_alteracao" timestamp(6),
    "fi_usuario_cadastro" varchar(255),
    "fi_usuario_alteracao" varchar(255)
)
    WITH (
        autovacuum_enabled=true)
;

COMMENT ON TABLE "odonto"."tb_ficha" IS 'Armazena as fichas do paciente.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_id" IS 'Codigo gerado automaticamente que identifica uma ficha de paciente.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."pa_id" IS 'Código referente ao paciente.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_descricao" IS 'Descrição da ficha.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_tratamento" IS 'Indica se está fazendo tratamento.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_outros_medicamentos" IS 'Indica se está usando outros medicamentos.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_anestesia_local" IS 'Indica se precisa de anestesia local.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_caso_positivo_anestesia_local" IS 'Caso positivo anestesia local.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_uso_antibiotico" IS 'Uso antibiotico.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_caso_positivo_antibiotico" IS 'Caso positivo do uso de antibiotico.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_nome_antibiotico" IS 'Nome do antibiotico.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_alergia_medicamento" IS 'Indica se possui alergia a algum medicamento.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_nome_medicameto_alergia" IS 'Nome do medicamento que possui alergia.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_cicatrizacao_normal" IS 'Indica se possui cicatrizacao normal.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_tique_mania" IS 'Indica se possui algum tique.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_qual_tique_mania" IS 'Indica qual o tipo de tique.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_esta_gravida" IS 'Indica se está gravida.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_fumante" IS 'Indica se é fumante.'
;

COMMENT ON COLUMN "odonto"."tb_ficha"."fi_outras_informacoes_importantes" IS 'Outras informações importante.'
;

CREATE UNIQUE INDEX "uk_ficha_paciente" ON "odonto"."tb_ficha" ("pa_id")
;

ALTER TABLE "odonto"."tb_ficha" ADD CONSTRAINT "pk_tb_ficha" PRIMARY KEY ("fi_id")
;

-- Create foreign keys (relationships) section -------------------------------------------------


ALTER TABLE "odonto"."tb_empresa" ADD CONSTRAINT "fk_empresa_plano" FOREIGN KEY ("pla_id") REFERENCES "odonto"."tb_plano" ("pla_id")
;

ALTER TABLE "odonto"."tb_empresa" ADD CONSTRAINT "fk_empresa_municipio" FOREIGN KEY ("mu_id") REFERENCES "odonto"."tb_municipio" ("mu_id")
;

ALTER TABLE "odonto"."tb_municipio" ADD CONSTRAINT "fk_municipio_estado" FOREIGN KEY ("uf_id") REFERENCES "odonto"."tb_estado" ("uf_id")
;

ALTER TABLE "odonto"."tb_perfil" ADD CONSTRAINT "fk_perfil_empresa" FOREIGN KEY ("em_id") REFERENCES "odonto"."tb_empresa" ("em_id")
;

ALTER TABLE "odonto"."tb_usuario" ADD CONSTRAINT "fk_usuario_empresa" FOREIGN KEY ("em_id") REFERENCES "odonto"."tb_empresa" ("em_id")
;

ALTER TABLE "odonto"."tb_usuario" ADD CONSTRAINT "fk_usuario_municipio" FOREIGN KEY ("mu_id") REFERENCES "odonto"."tb_municipio" ("mu_id")
;

ALTER TABLE "odonto"."tb_paciente" ADD CONSTRAINT "fk_paciente_empresa" FOREIGN KEY ("em_id") REFERENCES "odonto"."tb_empresa" ("em_id")
;

ALTER TABLE "odonto"."tb_paciente" ADD CONSTRAINT "fk_paciente_municipio" FOREIGN KEY ("mu_id") REFERENCES "odonto"."tb_municipio" ("mu_id")
;

ALTER TABLE "odonto"."tb_ficha" ADD CONSTRAINT "fk_ficha_paciente" FOREIGN KEY ("pa_id") REFERENCES "odonto"."tb_paciente" ("pa_id")
;

ALTER TABLE "odonto"."tb_perfil_usuario" ADD CONSTRAINT "fk_perfil_usuario_pf" FOREIGN KEY ("pf_id") REFERENCES "odonto"."tb_perfil" ("pf_id")
;

ALTER TABLE "odonto"."tb_perfil_usuario" ADD CONSTRAINT "fk_perfil_usuario_us" FOREIGN KEY ("us_id") REFERENCES "odonto"."tb_usuario" ("us_id")
;

ALTER TABLE "odonto"."tb_perfil_permissao" ADD CONSTRAINT "fk_perfil_permissao_pf" FOREIGN KEY ("pf_id") REFERENCES "odonto"."tb_perfil" ("pf_id")
;

ALTER TABLE "odonto"."tb_perfil_permissao" ADD CONSTRAINT "fk_perfil_permissao_pe" FOREIGN KEY ("pe_id") REFERENCES "odonto"."tb_permissao" ("pe_id")
;