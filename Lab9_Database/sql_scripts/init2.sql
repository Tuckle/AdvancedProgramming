set serveroutput on;

drop table matches;
drop table goals;

create table matches(
    id integer not null,
    match_date date,
    team_id1 integer not null,
    team_id2 integer not null,
    goals1 integer not null,
    goals2 integer not null,
    primary key (id)
);
create table goals(
    id integer not null,
    match_id integer not null,
    player_id integer not null,
    minute integer not null,
    penalty integer not null,
    primary key (id)
);

DROP SEQUENCE matchesId;
DROP SEQUENCE goalsId;
CREATE SEQUENCE matchesId START WITH 1;
CREATE SEQUENCE goalsId START WITH 1;

CREATE OR REPLACE TRIGGER matchesIdTrig BEFORE INSERT ON matches FOR EACH ROW BEGIN SELECT matchesId.NEXTVAL INTO :new.id FROM dual; END; 
/
CREATE OR REPLACE TRIGGER goalsIdTrig BEFORE INSERT ON goals FOR EACH ROW BEGIN SELECT goalsId.NEXTVAL INTO :new.id FROM dual; END; 
/
