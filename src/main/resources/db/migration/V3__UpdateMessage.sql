drop table message;

create table message
(
    id        int8 generated by default as identity,
    date_time timestamp,
    message   varchar(255),
    username  varchar(255),
    user_id   int8,
    primary key (id)
);
