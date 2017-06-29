DELETE FROM wishlist;
ALTER SEQUENCE wishlist_seq RESTART WITH 1;

INSERT INTO wishlist (description, date, is_done) VALUES ('Some wish #1', '2017-06-28', FALSE);
INSERT INTO wishlist (description, date, is_done) VALUES ('Some wish #2', '2017-06-12', FALSE);
INSERT INTO wishlist (description, date, is_done) VALUES ('Some wish #3', '2015-06-23', TRUE);
INSERT INTO wishlist (description, date, is_done) VALUES ('Some wish #4', '2017-07-20', FALSE);
INSERT INTO wishlist (description, date, is_done) VALUES ('Some wish #5', '2016-12-25', TRUE);
