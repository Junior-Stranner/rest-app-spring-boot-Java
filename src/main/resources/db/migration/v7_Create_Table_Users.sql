CREATE TABLE IF NOT EXISTS users (
  id serial PRIMARY KEY not NULL, -- Chave primária autoincrementada
  user_name VARCHAR(255) UNIQUE, -- Nome de usuário único
  full_name VARCHAR(255), -- Nome completo do usuário
  password VARCHAR(255), -- Senha do usuário
  account_non_expired BOOLEAN, -- Indica se a conta do usuário está expirada
  account_non_locked BOOLEAN, -- Indica se a conta do usuário está bloqueada
  credentials_non_expired BOOLEAN, -- Indica se as credenciais do usuário estão expiradas
  enabled BOOLEAN -- Indica se a conta do usuário está habilitada
);
