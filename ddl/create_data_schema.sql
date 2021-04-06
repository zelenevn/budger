CREATE TABLE user_role (
	id SERIAL PRIMARY KEY,
	title VARCHAR(50) UNIQUE NOT NULL 
);

CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	password INTEGER NOT NULL,
	account_role INTEGER REFERENCES user_role(id)
);

CREATE TABLE personal_information (
	id INTEGER PRIMARY KEY REFERENCES account(id),
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	birthday_date DATE
);

CREATE TABLE family (
	id SERIAL PRIMARY KEY,
	family_name VARCHAR(50)
);

CREATE TABLE family_account (
	account_id INTEGER UNIQUE REFERENCES account(id),
	family_id INTEGER UNIQUE REFERENCES family(id)
);

CREATE TABLE budget(
	id SERIAL PRIMARY KEY
);

CREATE TABLE goal (
	id SERIAL PRIMARY KEY,
	budget_id INTEGER NOT NULL REFERENCES budget(id),
	title VARCHAR(50),
	value MONEY NOT NULL CHECK(value::numeric>0),
	description TEXT,
	date TIMESTAMP NOT NULL
);

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	category_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE transaction (
	budget_id INTEGER UNIQUE REFERENCES budget(id),
	category_id INTEGER UNIQUE REFERENCES category(id),
	value MONEY NOT NULL,
	date TIMESTAMP NOT NULL
);