DROP DATABASE medly_db;
CREATE DATABASE medly_db;

use medly_db;

INSERT INTO medly_db.roles (role) VALUES ('Admin');
INSERT INTO medly_db.roles (role) VALUES ('Doctor');
INSERT INTO medly_db.roles (role) VALUES ('Patient');