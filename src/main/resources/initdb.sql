--To be executed one by one

insert into my_shopping.app_config(is_active, c_key, c_value)values(true, 'JWT_SECRET', 'my$h0pp!nG');

insert into my_shopping.roles(id, is_active, name) values (1, true, 'ROLE_ADMIN');

insert into my_shopping.roles(id, is_active, name) values (2, true, 'ROLE_USER');

insert into my_shopping.users(is_active, email, name, password, status, user_name) values (true, 'syedirfan33@ymail.com', 'Syed Irfan', '$2a$10$marBRTp8YNEVM1T2BWlzZOInBc6tDTuDHWM9Zqa7CEk0GjzGSlJrq','ACTIVE', 'syedirfan33');

insert into my_shopping.user_roles values(1,1);