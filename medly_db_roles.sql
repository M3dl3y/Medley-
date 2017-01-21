DROP DATABASE medly_db;
CREATE DATABASE medly_db;

use medly_db;

INSERT INTO medly_db.roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO medly_db.roles (role) VALUES ('ROLE_DOCTOR');
INSERT INTO medly_db.roles (role) VALUES ('ROLE_PATIENT');


INSERT INTO users(id, username, email, password, enabled, role_id, city, date_of_birth, first_name, last_name, phone_number, state, street_address, zip_code)
VALUES(1, 'test', 'test@test.com', '$2a$10$O5lrKNXfAdWWpf0P3Ij7Du03J7WuCRRtVDT7JGJwHTighGmweFhDO', 1, 3, 'blah', '2017-01-20 00:00:00', 'text', 'tester', 9999999999, 'tx', 'blah', 99999);

