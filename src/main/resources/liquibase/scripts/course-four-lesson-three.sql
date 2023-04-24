--liquibase formatted sql

--changeSet srudnev:1
CREATE INDEX student_name_index ON student (name);

--changeSet srudnev:2
CREATE INDEX faculty_nc_idx ON faculty (name, color);