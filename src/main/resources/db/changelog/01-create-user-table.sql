create table users (
    id BIGINT not null,
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    authority varchar(10),
    primary key (id))

