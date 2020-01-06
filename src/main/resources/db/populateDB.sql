DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM restaurants;
DELETE FROM dishes;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('Admin', 'admin@gmail.com', '{noop}admin'),
('User', 'user@gmail.com', '{noop}password');

INSERT INTO user_roles (user_id, role) VALUES
(100000, 'ROLE_ADMIN'),
(100001, 'ROLE_USER');

INSERT INTO restaurants (name) VALUES
('Шарманка'),
('Тарас Бульба'),
('Караван');

INSERT INTO dishes (name, price, restaurant_id, date) VALUES
('Шашлык', 400, 100002, now()),
('Борщ', 300, 100003, now()),
('Плов', 300, 100004, now());



