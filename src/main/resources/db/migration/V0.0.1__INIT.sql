-- ********************* account *********************
CREATE TABLE IF NOT EXISTS account
(
    id
    UUID
    DEFAULT
    gen_random_uuid
(
) PRIMARY KEY,
    balance INTEGER NOT NULL DEFAULT 0.0
    );

COMMENT
ON TABLE account IS 'Таблица счетов';
COMMENT
ON COLUMN account.id IS 'Идентификатор клиента';
COMMENT
ON COLUMN account.balance IS 'Баланс клиента';