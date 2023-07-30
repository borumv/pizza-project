--liquibase formatted sql

--changeset borisvlasevsky:1
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
-- ...

-- ----------------------------
-- Table structure for order_pizzas
-- ----------------------------

DROP TABLE IF EXISTS order_pizzas;

-- ----------------------------
-- Table structure for orders
-- ----------------------------

DROP TABLE IF EXISTS orders;


-- ----------------------------
-- Table structure for pizzes_img
-- ----------------------------
--changeset borisvlasevsky:2
CREATE TABLE pizzes_img (
                            id int4 NOT NULL PRIMARY KEY,
                            pizza_id int4,
                            imageurl text
);

-- ----------------------------
-- Table structure for sizes
-- ----------------------------
--changeset borisvlasevsky:3
CREATE TABLE sizes (
                       id int4 NOT NULL,
                       size int4
);

-- ----------------------------
-- Table structure for types
-- ----------------------------
--changeset borisvlasevsky:4
CREATE TABLE types (
                       id int4 NOT NULL PRIMARY KEY,
                       description varchar(255)
);

-- ----------------------------
-- Table structure for pizzes
-- ----------------------------
--changeset borisvlasevsky:5
CREATE TABLE pizzes (
                        id int4 NOT NULL PRIMARY KEY,
                        title varchar(255),
                        rating int4
);

-- ----------------------------
-- Table structure for categories
-- ----------------------------
--changeset borisvlasevsky:6
CREATE TABLE categories (
                            id int4 NOT NULL,
                            name varchar(255)
);

-- ----------------------------
-- Table structure for pizza_category
-- ----------------------------
--changeset borisvlasevsky:7
CREATE TABLE pizza_category (
                                pizza_id int4,
                                category_id int4
);

-- ----------------------------
-- Table structure for pizza_price_info
-- ----------------------------
--changeset borisvlasevsky:8
CREATE TABLE pizza_price_info (
                                  id int4 NOT NULL PRIMARY KEY,
                                  pizza_id int4,
                                  size_id int4,
                                  price int4
);

-- ----------------------------
-- Table structure for pizza_types
-- ----------------------------
--changeset borisvlasevsky:9
CREATE TABLE pizza_types (
                             pizza_id int4,
                             type_id int4
);

