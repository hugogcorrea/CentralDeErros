DROP TABLE IF EXISTS application_instance CASCADE;
DROP TABLE IF EXISTS error CASCADE;
DROP TABLE IF EXISTS permission CASCADE;
DROP TABLE IF EXISTS server CASCADE;
DROP TABLE IF EXISTS user_application CASCADE;

create table application_instance
(
    id                 integer not null
        constraint application_instance_pkey
            primary key,
    instantiation_date timestamp,
    name               varchar(255),
    status             integer,
    server_id          integer
        constraint fki3ma9nh04udjqsyeb59yxwjtt
            references server
);

create table error
(
    id                      integer not null
        constraint error_pkey
            primary key,
    description             varchar(255),
    error_type              integer,
    registration_date       timestamp,
    application_instance_id integer
        constraint fki000srodnostkc5loehc7iqc6
            references application_instance
);

create table permission
(
    created_at              timestamp,
    user_application_id     bigint  not null
        constraint fkq1q805dopewr6v0n9ocxxuad8
            references user_application,
    application_instance_id integer not null
        constraint fkcpqq4fn05ymqptc9i1xuhey6l
            references application_instance,
    constraint permission_pkey
        primary key (application_instance_id, user_application_id)
);

create table server
(
    id       integer not null
        constraint server_pkey
            primary key,
    location varchar(255),
    name     varchar(255)
);

create table user_application
(
    id       bigserial    not null
        constraint user_application_pkey
            primary key,
    password varchar(255),
    username varchar(255) not null
);
