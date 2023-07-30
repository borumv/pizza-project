--liquibase formatted sql

--changeset borisvlasevsky:1

CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        date DATE,
                        final_price FLOAT,
                        phone_number varchar(64),
                        status VARCHAR(50)
);

--changeset borisvlasevsky:2
CREATE TABLE order_pizzas (
                              id SERIAL PRIMARY KEY,
                              order_id BIGINT,
                              pizzaId INT,
                              typeId VARCHAR(64),
                              size INT,
                              count INT,
                              FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


