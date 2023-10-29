
DROP TABLE IF EXISTS "MEALS";
DROP TABLE IF EXISTS "ORDERS";
DROP TABLE IF EXISTS "ORDER_MEAL";

CREATE TABLE "MEALS"
(
    MEAL_ID INT AUTO_INCREMENT PRIMARY KEY,
    MEAL_NAME VARCHAR(255),
    PRICE DOUBLE
);

CREATE TABLE "ORDERS"
(
    ORDER_ID INT AUTO_INCREMENT PRIMARY KEY,
    CREATED_AT timestamp,
    UPDATED_AT timestamp,
    ORDERED boolean
);

CREATE TABLE "ORDER_MEAL"
(
    ID        INT AUTO_INCREMENT primary key,
    ID_ORDER  INT,
    ID_MEAL   INT
);

INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (1, 'Varivo od mix mahunarki', 3.60);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (2, 'Pohani file oslića – krumpir salata s rikulom', 6.30);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (3, 'Pohani file oslića, umak od vlasca i krastavca - slani krumpir', 6.30);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (4, 'Steak tune sa žara, tršćanski umak – blitva s krumpirom', 10.00);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (5, 'Orada sa žara, tršćanski umak – blitva s krumpirom', 7.10);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (6, 'Crni rižoto od liganja s parmezanom', 6.50);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (7, 'Pureći medaljoni u umaku od pesta s tjesteninom', 6.00);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (8, 'Juha od rajčice', 1.20);
INSERT INTO "MEALS" (MEAL_ID, MEAL_NAME, PRICE) VALUES (9, 'Salata miješana', 1.10);


