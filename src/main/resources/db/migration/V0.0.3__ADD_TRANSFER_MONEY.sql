
ALTER TABLE operation_history ADD COLUMN IF NOT EXISTS transfer_from_account_id UUID REFERENCES account(id);
ALTER TABLE operation_history RENAME COLUMN amount TO new_amount;
ALTER TABLE operation_history ADD COLUMN IF NOT EXISTS old_amount NUMERIC NOT NULL;

COMMENT ON COLUMN operation_history.transfer_from_account_id IS 'Перевод от пользователя';
COMMENT ON COLUMN operation_history.old_amount IS 'Предыдущий баланс';
COMMENT ON COLUMN operation_history.old_amount IS 'Измененный баланс';