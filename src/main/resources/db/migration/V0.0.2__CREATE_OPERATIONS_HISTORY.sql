-- ********************* operation_history *********************
CREATE TABLE IF NOT EXISTS operation_history
(
    id
    UUID
    DEFAULT
    gen_random_uuid
(
) PRIMARY KEY,
    account_id UUID REFERENCES account
(
    id
),
    type VARCHAR
(
    100
) NOT NULL,
    ammount MONEY NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now
(
)
    );

COMMENT
ON TABLE operation_history IS 'История операций';
COMMENT
ON COLUMN operation_history.id IS 'Идентификатор операции';
COMMENT
ON COLUMN operation_history.account_id IS 'Идентификатор счета';
COMMENT
ON COLUMN operation_history.type IS 'Тип операции';
COMMENT
ON COLUMN operation_history.ammount IS 'Измененная сумма';
COMMENT
ON COLUMN operation_history.created_at IS 'Дата создания';


ALTER TABLE account
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP NOT NULL DEFAULT now();
COMMENT
ON COLUMN account.created_at IS 'Дата создания';