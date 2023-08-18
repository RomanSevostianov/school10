--liquibase formatted sql

--changeSet rSevostianov:1
CREATE INDEX IDX_students_name ON student(name);

--changeSet rSevostianov:2
CREATE INDEX IDX_faculty_color ON faculty (name, color);


