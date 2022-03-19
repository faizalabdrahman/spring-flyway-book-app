DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS hibernate_sequence;

CREATE TABLE book
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(255),
    publisher VARCHAR(255),
    title VARCHAR(255),
    PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE hibernate_sequence
(
    next_val BIGINT
) engine=InnoDB;

INSERT INTO hibernate_sequence VALUES (1);