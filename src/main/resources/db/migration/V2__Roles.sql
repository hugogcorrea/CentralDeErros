DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS user_application_roles CASCADE;

create table role
(
    id   bigserial not null
        constraint role_pkey
            primary key,
    name varchar(255)
);

create table user_application_roles
(
    users_id bigint not null
        constraint fki0yayu5dmg4p83opq5jnssvfk
            references user_application,
    roles_id bigint not null
        constraint fk8gegcb6iwpx7f6pwnat8deyuj
            references role
);

