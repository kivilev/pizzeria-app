create table topping
(
    id        uuid          not null,
    code      varchar(100)  not null,
    full_name varchar(1000) not null,
    primary key (id)
);

alter table topping
    add constraint topping_code_uq unique (code);
alter table topping
    add constraint topping_code_chk CHECK (code ~* '^[A-Z0-9_]+$');

comment on table topping is 'Toppings';
comment on column topping.id is 'Unique ID';
comment on column topping.code is 'Unique code';
comment on column topping.full_name is 'Topping full name';
