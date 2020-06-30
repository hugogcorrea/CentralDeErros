INSERT INTO user_application(id, password, username) VALUES (900, '$2a$10$N/EcYXDwjhBgOM2fWnWGm.ZyIGCqkeVicr19bFzQ8STpadG1OoghC', 'joao');
INSERT INTO user_application(id, password, username) VALUES (901, '$2a$10$N/EcYXDwjhBgOM2fWnWGm.ZyIGCqkeVicr19bFzQ8STpadG1OoghC', 'hugo');

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');
	
INSERT INTO user_application_roles(users_id, roles_id) VALUES (900, 1);
INSERT INTO user_application_roles(users_id, roles_id) VALUES (901, 2);

INSERT INTO server VALUES (1, 'BR', 'BR-01');
INSERT INTO server VALUES (2, 'US', 'US-01');
INSERT INTO server VALUES (3, 'EUW', 'EUW-01');

INSERT INTO application_instance VALUES (1, '2020-01-01 10:10:10', 'Apolo', 1, 2);
INSERT INTO application_instance VALUES (2, '2020-02-10 11:20:10', '14-Bis', 1, 1);
INSERT INTO application_instance VALUES (3, '2020-04-20 01:30:20', 'Tupi', 0, 1);
INSERT INTO application_instance VALUES (4, '2020-05-08 02:10:20', 'Eiffel', 1, 3);

INSERT INTO permission VALUES ('2020-01-01 10:10:10', 901, 1);
INSERT INTO permission VALUES ('2020-01-01 10:10:10', 901, 2);

INSERT INTO error VALUES (1302923, 'Stack overflow', 1, '2020-05-08 02:10:20', 2);
INSERT INTO error VALUES (3209323, 'Request limit reached', 0, '2020-05-08 02:10:20', 2);
INSERT INTO error VALUES (323313, 'Underflow', 0, '2020-05-08 02:10:20', 1);
INSERT INTO error VALUES (1313313, 'Request time limit exceeded', 1, '2020-05-08 02:10:20', 3);
INSERT INTO error VALUES (131313, 'Buffer overflow', 1, '2020-05-08 02:10:20', 4);