create table bank.account
(
    id      uuid  default gen_random_uuid() not null
        primary key,
    balance money default 0.0               not null
);

comment on table bank.account is 'Таблица счетов';

comment on column bank.account.id is 'Идентификатор клиента';

comment on column bank.account.balance is 'Баланс клиента';

alter table bank.account
    owner to postgres;

