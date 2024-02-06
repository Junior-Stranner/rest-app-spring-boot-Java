CREATE TABLE IF NOT EXISTS user_permission (
  id_user long NOT NULL, -- Chave estrangeira para o ID do usuário
  id_permission BIGINT NOT NULL, -- Chave estrangeira para o ID da permissão
  PRIMARY KEY (id_user, id_permission), -- Chave primária composta
  FOREIGN KEY (id_user) REFERENCES users (id), -- Chave estrangeira para a tabela de usuários
  FOREIGN KEY (id_permission) REFERENCES permission (id) -- Chave estrangeira para a tabela de permissões
);
