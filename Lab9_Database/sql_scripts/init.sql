set serveroutput on;

drop table teams;
drop table players;

create table teams(
    id integer not null,
    name varchar(100) not null,
    primary key (id)
);
create table players(
    id integer not null,
    name varchar(100) not null,
    team_id integer not null, --  references teams on delete restrict
    primary key (id)
);

DROP SEQUENCE teamsId;
DROP SEQUENCE playersId;
CREATE SEQUENCE playersId START WITH 1;
CREATE SEQUENCE teamsId START WITH 1;

CREATE OR REPLACE TRIGGER teamsIdTrig BEFORE INSERT ON teams FOR EACH ROW BEGIN SELECT teamsId.NEXTVAL INTO :new.id FROM dual; END; 
/
CREATE OR REPLACE TRIGGER playersIdTrig BEFORE INSERT ON players FOR EACH ROW BEGIN SELECT playersId.NEXTVAL INTO :new.id FROM dual; END; 
/
