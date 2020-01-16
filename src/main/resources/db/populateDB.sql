DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('Admin', 'admin@gmail.com', '{noop}admin'),
('User', 'user@gmail.com', '{noop}password');


INSERT INTO user_roles (role, user_id) VALUES
('ROLE_ADMIN', 100000),
('ROLE_USER', 100000),
('ROLE_USER', 100001);


INSERT INTO restaurants (name) VALUES
  ('Шарманка'),
  ('Тарас Бульба'),
  ('Караван'),
  ('8 SECONDS PUB'),
  ('Ёлки Палки');

INSERT INTO dishes (name, price, restaurant_id, date) VALUES
  ('Шашлык из Каре Ягненка', 450, 100002, '2020-01-16'),
  ('Домашний суп', 200, 100002, '2020-01-16'),
  ('Узбекский плов', 330, 100002, '2020-01-16'),
  ('Фирменный борщ Корчма', 310, 100003, '2020-01-16'),
  ('Вареники Староукраинские', 310, 100003, '2020-01-16'),
  ('Шурпа', 485, 100004, '2020-01-16'),
  ('Самса с тыквой', 145, 100004, '2020-01-16'),
  ('Салат с осьминогом', 590, 100005, '2020-01-16'),
  ('Кальмар', 530, 100005, '2020-01-16'),
  ('Говядина велингтон', 950, 100005, '2020-01-16'),
  ('Суп из белых грибов', 175, 100006, '2020-01-16'),
  ('Блины с семгой', 227, 100006, '2020-01-16');


INSERT INTO votes (user_id, restaurant_id, date) VALUES
  (100000, 100002, '2020-01-15'),
  (100000, 100005, '2020-01-16'),
  (100001, 100004, '2020-01-16');
