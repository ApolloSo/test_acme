CREATE TABLE tb_store (
	id serial PRIMARY KEY,
	code VARCHAR ( 500 ),
	description VARCHAR ( 4000 ),
	name VARCHAR ( 100 ),
	opening_date VARCHAR ( 30 ),
	store_type VARCHAR ( 50 )
);

CREATE TABLE tb_season (
	store_id serial PRIMARY KEY,
	season VARCHAR ( 100 )
);

CREATE TABLE tb_svalue (
	store_id serial PRIMARY KEY,
	special_f1 VARCHAR ( 200 ),
	special_f2 VARCHAR ( 200 )
);