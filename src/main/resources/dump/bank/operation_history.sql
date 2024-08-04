create table bank.operation_history
(
    id                       uuid      default gen_random_uuid() not null
        primary key,
    account_id               uuid,
    type                     varchar(100)                        not null,
    new_amount               numeric                             not null,
    created_at               timestamp default now()             not null,
    transfer_from_account_id uuid
        references bank.account,
    old_amount               numeric   default 0                 not null
);

comment on table bank.operation_history is 'История операций';

comment on column bank.operation_history.id is 'Идентификатор операции';

comment on column bank.operation_history.account_id is 'Идентификатор счета';

comment on column bank.operation_history.type is 'Тип операции';

comment on column bank.operation_history.new_amount is 'Измененная сумма';

comment on column bank.operation_history.created_at is 'Дата создания';

comment on column bank.operation_history.transfer_from_account_id is 'Перевод от пользователя';

comment on column bank.operation_history.old_amount is 'Измененный баланс';

alter table bank.operation_history
    owner to postgres;

