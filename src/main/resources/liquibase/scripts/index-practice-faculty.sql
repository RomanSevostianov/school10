-- liquibase formatted sql


--changeSet rSevostianov:1

CREATE TABLE faculty(

    id SERIAL,
    name TEXT,
    color TEXT

)
--change rSevostianov:2

CREATE INDEX faculty_color_index ON faculty(color);