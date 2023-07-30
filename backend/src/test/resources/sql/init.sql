-- ----------------------------
-- Table structure for pizza_category
-- ----------------------------
DROP TABLE IF EXISTS pizza_category;
-- ...

-- ----------------------------
-- Table structure for pizza_price_info
-- ----------------------------
DROP TABLE IF EXISTS pizza_price_info;
-- ...

-- ----------------------------
-- Table structure for pizza_types
-- ----------------------------
DROP TABLE IF EXISTS pizza_types;
-- ...

-- ----------------------------
-- Table structure for pizzes_img
-- ----------------------------
DROP TABLE IF EXISTS pizzes_img;
-- ...

-- ----------------------------
-- Table structure for sizes
-- ----------------------------
DROP TABLE IF EXISTS sizes;
-- ...

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS types;
-- ...

-- ----------------------------
-- Table structure for pizzes
-- ----------------------------
DROP TABLE IF EXISTS pizzes;
-- ...

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS categories;

DROP TABLE IF EXISTS orders CASCADE ;

DROP TABLE IF EXISTS order_pizzas;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        date DATE,
                        final_price FLOAT,
                        phone_number varchar(64),
                        status VARCHAR(50)
);

-- ----------------------------
-- Table structure for order_pizzas
-- ----------------------------

CREATE TABLE order_pizzas (
                              id SERIAL PRIMARY KEY,
                              order_id BIGINT,
                              pizzaId INT,
                              typeId VARCHAR(64),
                              size INT,
                              count INT
);


-- ----------------------------
-- Table structure for pizzes_img
-- ----------------------------
CREATE TABLE pizzes_img (
                            id int4 NOT NULL PRIMARY KEY,
                            pizza_id int4,
                            imageurl text
);

-- ----------------------------
-- Table structure for sizes
-- ----------------------------
CREATE TABLE sizes (
                       id int4 NOT NULL,
                       size int4
);

-- ----------------------------
-- Table structure for types
-- ----------------------------
CREATE TABLE types (
                       id int4 NOT NULL PRIMARY KEY,
                       description varchar(255)
);

-- ----------------------------
-- Table structure for pizzes
-- ----------------------------
CREATE TABLE pizzes (
                        id int4 NOT NULL PRIMARY KEY,
                        title varchar(255),
                        rating int4
);

-- ----------------------------
-- Table structure for categories
-- ----------------------------
CREATE TABLE categories (
                            id int4 NOT NULL,
                            name varchar(255)
);

-- ----------------------------
-- Table structure for pizza_category
-- ----------------------------
CREATE TABLE pizza_category (
                                pizza_id int4,
                                category_id int4
);

-- ----------------------------
-- Table structure for pizza_price_info
-- ----------------------------
CREATE TABLE pizza_price_info (
                                  id int4 NOT NULL PRIMARY KEY,
                                  pizza_id int4,
                                  size_id int4,
                                  price int4
);

-- ----------------------------
-- Table structure for pizza_types
-- ----------------------------
CREATE TABLE pizza_types (
                             pizza_id int4,
                             type_id int4
);
