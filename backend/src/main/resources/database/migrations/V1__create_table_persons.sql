-- V1__create_persons_table.sql

CREATE TABLE person (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    parent_id BIGINT,
    CONSTRAINT fk_person_parent FOREIGN KEY (parent_id) REFERENCES person(id) ON DELETE CASCADE
);