CREATE TABLE IF NOT EXISTS users(
    id int auto_increment primary key,
    username varchar_ignorecase(50) not null,
    password varchar_ignorecase(50) not null,
    enabled tinyint not null,
    unique key uq_users_username (username)
);

CREATE TABLE IF NOT EXISTS authorities (
   id int auto_increment primary key,
   username varchar_ignorecase(50) not null,
   authority varchar_ignorecase(50) default 'USER'
);

CREATE TABLE IF NOT EXISTS orders (
    id int auto_increment primary key,
    user_id int not null,
    order_date timestamp default current_timestamp()
);

CREATE TABLE IF NOT EXISTS order_detail (
    id int auto_increment primary key,
    order_id int not null,
    product_id int not null,
    amount int default 0
);

CREATE TABLE IF NOT EXISTS product (
    id int auto_increment primary key,
    product_name varchar_ignorecase(50) not null
);
