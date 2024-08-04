create table bank.account
(
    id         uuid      default gen_random_uuid() not null
        primary key,
    balance    numeric   default 0.0               not null,
    created_at timestamp default now()             not null
);

comment on table bank.account is 'Таблица счетов';

comment on column bank.account.id is 'Идентификатор клиента';

comment on column bank.account.balance is 'Баланс клиента';

comment on column bank.account.created_at is 'Дата создания';

alter table bank.account
    owner to postgres;

