DROP TABLE person;
CREATE TABLE person (
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
    phone_number VARCHAR(12) NOT NULL,
    UNIQUE (last_name, first_name, phone_number)
);
