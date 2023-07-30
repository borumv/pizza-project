--liquibase formatted sql

--changeset borisvlasevsky:1

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO categories VALUES (1, 'Мясные');
INSERT INTO categories VALUES (2, 'Вегетарианская');
INSERT INTO categories VALUES (3, 'Гриль');
INSERT INTO categories VALUES (4, 'Острые');
INSERT INTO categories VALUES (5, 'Закрытые');


-- ----------------------------
-- Records of pizza_category
-- ----------------------------
INSERT INTO pizza_category VALUES (1, 1);
INSERT INTO pizza_category VALUES (2, 1);
INSERT INTO pizza_category VALUES (3, 2);
INSERT INTO pizza_category VALUES (4, 3);
INSERT INTO pizza_category VALUES (5, 4);
INSERT INTO pizza_category VALUES (6, 3);
INSERT INTO pizza_category VALUES (7, 2);
INSERT INTO pizza_category VALUES (8, 5);
INSERT INTO pizza_category VALUES (9, 5);
INSERT INTO pizza_category VALUES (10, 5);


-- ----------------------------
-- Records of pizza_price_info
-- ----------------------------
INSERT INTO pizza_price_info VALUES (1, 1, 1, 803);
INSERT INTO pizza_price_info VALUES (2, 1, 2, 850);
INSERT INTO pizza_price_info VALUES (3, 1, 3, 950);
INSERT INTO pizza_price_info VALUES (4, 2, 1, 245);
INSERT INTO pizza_price_info VALUES (5, 2, 2, 358);
INSERT INTO pizza_price_info VALUES (6, 3, 1, 295);
INSERT INTO pizza_price_info VALUES (7, 3, 2, 410);
INSERT INTO pizza_price_info VALUES (8, 4, 1, 275);
INSERT INTO pizza_price_info VALUES (9, 4, 2, 320);
INSERT INTO pizza_price_info VALUES (10, 4, 3, 410);
INSERT INTO pizza_price_info VALUES (11, 5, 1, 415);
INSERT INTO pizza_price_info VALUES (12, 5, 2, 480);
INSERT INTO pizza_price_info VALUES (13, 5, 3, 510);
INSERT INTO pizza_price_info VALUES (14, 6, 2, 580);
INSERT INTO pizza_price_info VALUES (15, 6, 3, 650);
INSERT INTO pizza_price_info VALUES (16, 7, 1, 675);
INSERT INTO pizza_price_info VALUES (17, 7, 2, 750);
INSERT INTO pizza_price_info VALUES (18, 7, 3, 825);
INSERT INTO pizza_price_info VALUES (19, 8, 1, 450);
INSERT INTO pizza_price_info VALUES (20, 8, 2, 470);
INSERT INTO pizza_price_info VALUES (21, 8, 3, 510);
INSERT INTO pizza_price_info VALUES (22, 9, 1, 395);
INSERT INTO pizza_price_info VALUES (23, 9, 2, 450);
INSERT INTO pizza_price_info VALUES (24, 9, 3, 510);
INSERT INTO pizza_price_info VALUES (25, 10, 1, 285);
INSERT INTO pizza_price_info VALUES (26, 10, 2, 370);
INSERT INTO pizza_price_info VALUES (27, 10, 3, 425);

-- ----------------------------
-- Records of pizza_types
-- ----------------------------
INSERT INTO pizza_types VALUES (1, 1);
INSERT INTO pizza_types VALUES (1, 2);
INSERT INTO pizza_types VALUES (2, 1);
INSERT INTO pizza_types VALUES (2, 2);
INSERT INTO pizza_types VALUES (3, 2);
INSERT INTO pizza_types VALUES (4, 1);
INSERT INTO pizza_types VALUES (5, 2);
INSERT INTO pizza_types VALUES (6, 1);
INSERT INTO pizza_types VALUES (6, 2);
INSERT INTO pizza_types VALUES (7, 1);
INSERT INTO pizza_types VALUES (7, 2);
INSERT INTO pizza_types VALUES (8, 2);
INSERT INTO pizza_types VALUES (9, 1);
INSERT INTO pizza_types VALUES (10, 2);
INSERT INTO pizza_types VALUES (10, 1);

-- ----------------------------
-- Records of pizzes
-- ----------------------------
INSERT INTO pizzes VALUES (1, 'Пепперони Фреш с перцем', 3);
INSERT INTO pizzes VALUES (2, 'Сырная', 4);
INSERT INTO pizzes VALUES (3, 'Цыпленок барбекю', 5);
INSERT INTO pizzes VALUES (4, 'Кисло-сладкий цыпленок', 2);
INSERT INTO pizzes VALUES (5, 'Чизбургер-пицца', 4);
INSERT INTO pizzes VALUES (6, 'Крэйзи пепперони', 1);
INSERT INTO pizzes VALUES (7, 'Пепперони', 3);
INSERT INTO pizzes VALUES (8, 'Маргарита', 3);
INSERT INTO pizzes VALUES (9, 'Четыре сезона', 5);
INSERT INTO pizzes VALUES (10, 'Овощи и грибы 🌱', 1);

-- ----------------------------
-- Records of pizzes_img
-- ----------------------------
INSERT INTO pizzes_img VALUES (1, 1, 'https://dodopizza.azureedge.net/static/Img/Products/f035c7f46c0844069722f2bb3ee9f113_584x584.jpeg');
INSERT INTO pizzes_img VALUES (2, 2, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/2ffc31bb-132c-4c99-b894-53f7107a1441.jpg');
INSERT INTO pizzes_img VALUES (3, 3, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/6652fec1-04df-49d8-8744-232f1032c44b.jpg');
INSERT INTO pizzes_img VALUES (4, 4, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/af553bf5-3887-4501-b88e-8f0f55229429.jpg');
INSERT INTO pizzes_img VALUES (5, 5, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/b750f576-4a83-48e6-a283-5a8efb68c35d.jpg');
INSERT INTO pizzes_img VALUES (6, 6, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/1e1a6e80-b3ba-4a44-b6b9-beae5b1fbf27.jpg');
INSERT INTO pizzes_img VALUES (7, 7, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/d2e337e9-e07a-4199-9cc1-501cc44cb8f8.jpg');
INSERT INTO pizzes_img VALUES (8, 8, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/d48003cd-902c-420d-9f28-92d9dc5f73b4.jpg');
INSERT INTO pizzes_img VALUES (9, 9, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/ec29465e-606b-4a04-a03e-da3940d37e0e.jpg');
INSERT INTO pizzes_img VALUES (10, 10, 'https://dodopizza.azureedge.net/static/Img/Products/Pizza/ru-RU/30367198-f3bd-44ed-9314-6f717960da07.jpg');


-- ----------------------------
-- Records of sizes
-- ----------------------------
INSERT INTO sizes VALUES (1, 26);
INSERT INTO sizes VALUES (2, 30);
INSERT INTO sizes VALUES (3, 40);

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO types VALUES (1, 'тонкое');
INSERT INTO types VALUES (2, 'традиционное');

