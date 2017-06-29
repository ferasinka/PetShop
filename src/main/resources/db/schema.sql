DROP TABLE IF EXISTS wishlist;
DROP SEQUENCE IF EXISTS wishlist_seq;

CREATE SEQUENCE wishlist_seq START 1;

CREATE TABLE wishlist (
	id          INT         NOT NULL PRIMARY KEY DEFAULT nextval('wishlist_seq'),
	description VARCHAR(64) NOT NULL,
	date        TIMESTAMP   NOT NULL,
	is_done     BOOLEAN     NOT NULL             DEFAULT FALSE
);
