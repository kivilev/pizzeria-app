create table customer
(
    id           uuid          not null,
    full_name    varchar(1000) not null,
    phone_number varchar(30),
    email        varchar(100),
    primary key (id)
);

comment on table customer is 'Customers';
comment on column customer.id is 'Unique ID';
comment on column customer.full_name is 'Customer full name';
comment on column customer.phone_number is 'Phone';
comment on column customer.email is 'Email';
