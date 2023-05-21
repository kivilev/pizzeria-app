insert into topping(id,
                    code,
                    full_name)
values ('7d1f29b7-4651-40b6-8042-e8e8e2345150', 'CODE1', 'TOPPING1') on conflict do nothing;

insert into topping(id,
                    code,
                    full_name)
values ('7d1f29b7-4651-40b6-8042-e8e8e2345152', 'CODE2', 'TOPPING2') on conflict do nothing;
