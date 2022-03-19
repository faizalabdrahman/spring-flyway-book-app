DROP TABLE IF EXISTS author;

CREATE TABLE author
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    PRIMARY KEY (id)
) engine=InnoDB;