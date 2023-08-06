-- liquibase formatted sql

--changeSet rSevostianov:1

CREATE INDEX student_name_index ON student (name);

--changeSet rSevostianov:2
CREATE INDEX faculty_color_index ON faculty(color);