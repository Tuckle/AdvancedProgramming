set serveroutput on;

create table teams(
    id integer not null generated always as identity (start with 1, increment by 1),
    name varchar(100) not null,
    primary key (id)
);
create table players(
    id integer not null generated always as identity (start with 1, increment by 1),
    name varchar(100) not null,
    team_id integer not null references teams on delete restrict,
    primary key (id)
);