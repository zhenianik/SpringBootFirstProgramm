CREATE TABLE hibernate_sequence(next_val BIGINT);

INSERT INTO hibernate_sequence VALUES(1);

INSERT INTO hibernate_sequence VALUES(1);

CREATE TABLE message(
    id bigint NOT NULL auto_increment,
    filename VARCHAR(255),
    tag VARCHAR(255),
    TEXT VARCHAR(2048) NOT NULL,
    user_id BIGINT,
    PRIMARY KEY(id)
) engine=innodb DEFAULT CHARSET=utf8 DEFAULT COLLATE utf8_general_ci;

CREATE TABLE user_role(
    user_id BIGINT NOT NULL,
    roles VARCHAR(255)
) engine=innodb DEFAULT CHARSET=utf8 DEFAULT COLLATE utf8_general_ci;

 CREATE TABLE usr(
    id BIGINT NOT NULL,
    activation_code VARCHAR(255),
    active BIT NOT NULL,
    email VARCHAR(255),
    PASSWORD VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
) engine=innodb DEFAULT CHARSET=utf8 DEFAULT COLLATE utf8_general_ci;

ALTER TABLE
    message ADD CONSTRAINT message_user_fk FOREIGN KEY(user_id) REFERENCES usr(id);

ALTER TABLE
    user_role ADD CONSTRAINT user_role_user_fk FOREIGN KEY(user_id) REFERENCES usr(id);