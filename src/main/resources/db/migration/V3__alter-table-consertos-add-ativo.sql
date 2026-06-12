-- Adiciona o campo ativo (para exclusão lógica)
ALTER TABLE consertos ADD COLUMN ativo BOOLEAN;

-- Atualiza os registros existentes como ativos (valor TRUE)
UPDATE consertos SET ativo = TRUE;