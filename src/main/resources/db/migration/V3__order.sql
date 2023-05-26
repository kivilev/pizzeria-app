create type orders_state_type as enum
    ( 'CREATED', 'DELIVERING', 'DELIVERED', 'CANCELLED');

create table orders
(
    id            uuid                                   not null,
    created_dtime timestamp with time zone default now() not null,
    state         orders_state_type                      not null,
    customer_id   uuid                                   not null,
    email         varchar(100)                           not null,
    primary key (id)
);

create index orders_customer_id_idx on orders (customer_id);

alter table orders
    add constraint orders_customer_fk
        foreign key (customer_id) references customer;

comment on table orders is 'Order for delivery';
comment on column orders.created_dtime is 'Create date time';
comment on column orders.state is 'State';
comment on column orders.customer_id is 'Customer Id';
comment on column orders.email is 'Email';


create table orders_topping
(
    orders_id  uuid not null,
    topping_id uuid not null,
    primary key (orders_id, topping_id)
);

alter table orders_topping
    add constraint orders_topping_order_id_fk
        foreign key (orders_id) references orders;

alter table orders_topping
    add constraint orders_topping_topping_id_fk
        foreign key (topping_id) references topping;

create index orders_topping_topping_id_i on orders_topping (topping_id);

comment on table orders_topping is 'Items of order';
comment on column orders_topping.orders_id is 'Order Id';
comment on column orders_topping.topping_id is 'Topping id';
