INSERT INTO user_application(id, password, username) VALUES (900, '$2a$10$N/EcYXDwjhBgOM2fWnWGm.ZyIGCqkeVicr19bFzQ8STpadG1OoghC', 'joao');
INSERT INTO user_application(id, password, username) VALUES (901, '$2a$10$N/EcYXDwjhBgOM2fWnWGm.ZyIGCqkeVicr19bFzQ8STpadG1OoghC', 'hugo');

insert into role (id, name) values (1, 'ADMIN');
insert into role (id, name) values (2, 'USER');
	
INSERT INTO user_application_roles(users_id, roles_id) VALUES (900, 1);
INSERT INTO user_application_roles(users_id, roles_id) VALUES (901, 2);
