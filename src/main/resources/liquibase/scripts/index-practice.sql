-- liquibase formatted sql


--changeSet rSevostianov:1

CREATE TABLE student
(
    id   SERIAL,
    name TEXT,
    age  INTEGER
)

--changeSet rSevostianov:2

CREATE INDEX student_name_index ON student (name);
