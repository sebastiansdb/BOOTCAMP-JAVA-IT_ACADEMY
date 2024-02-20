DROP DATABASE IF EXISTS PepeFlowerShop;
CREATE DATABASE PepeFlowerShop;
USE PepeFlowerShop;

CREATE TABLE products (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    attribute VARCHAR(100) NOT NULL
);

CREATE TABLE sales (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    totalPrice DOUBLE NOT NULL,
    date DATE NOT NULL,
    productList VARCHAR(1000)
);

INSERT INTO products (type, name, price, attribute) VALUES
('FLOWER', 'Rosas', 15.5, 'Rojo'),
('TREE', 'Pino', 25.75, '3.5'),
('DECORATION', 'Jarrón', 10.2, 'wood'),
('FLOWER', 'Margaritas', 12.8, 'Blanco'),
('DECORATION', 'Lámpara', 30.0, 'plastic'),
('TREE', 'Roble', 35.25, '4.2'),
('FLOWER', 'Tulipanes', 18.9, 'Amarillo'),
('DECORATION', 'Silla', 22.5, 'wood'),
('FLOWER', 'Lirios', 20.3, 'Púrpura'),
('TREE', 'Arce', 28.6, '3.8'),
('DECORATION', 'Mesa', 45.8, 'wood'),
('FLOWER', 'Girasoles', 14.75, 'Amarillo'),
('DECORATION', 'Estantería', 55.0, 'wood'),
('FLOWER', 'Orquídeas', 30.5, 'Rosado'),
('DECORATION', 'Maceta', 8.9, 'plastic'),
('TREE', 'Abeto', 22.75, '4.0'),
('FLOWER', 'Jacintos', 16.2, 'Azul'),
('DECORATION', 'Sofá', 65.3, 'plastic'),
('TREE', 'Abies', 30.5, '4.1'),
('FLOWER', 'Dalias', 22.8, 'Naranja'),
('TREE', 'Olivo', 53.4, '4.8');

INSERT INTO sales (totalPrice, date, productList) VALUES
(58.20, '2015-03-10', 'Flower: Rosas, Color: Rojo, Price: 15.50€;Tree: Abeto, Heigth: 4.1, Price: 42.70€'),
(97.80, '2016-07-20', 'Decoration: Maceta, Material: PLASTIC, Price: 8.90€;Flower: Orquídeas, Color: Rosado, Price: 22.50€;Tree: Pino, Heigth: 3.5, Price: 66.40€'),
(45.30, '2017-11-05', 'Decoration: Sofá, Material: WOOD, Price: 45.30€'),
(98.45, '2018-04-15', 'Decoration: Mesa, Material: WOOD, Price: 45.80€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Decoration: Jarrón, Material: WOOD, Price: 12.05€'),
(68.70, '2019-09-22', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Decoration: Lámpara, Material: PLASTIC, Price: 29.50€'),
(37.80, '2020-05-10', 'Flower: Tulipanes, Color: Amarillo, Price: 18.90€;Decoration: Silla, Material: WOOD, Price: 18.90€'),
(79.90, '2015-01-02', 'Decoration: Estantería, Material: PLASTIC, Price: 15.90€;Flower: Margaritas, Color: Blanco, Price: 12.80€;Tree: Abies, Heigth: 4.1, Price: 51.20€'),
(125.30, '2016-07-15', 'Decoration: Lámpara, Material: WOOD, Price: 30.00€;Flower: Jacintos, Color: Azul, Price: 16.20€;Tree: Arce, Heigth: 3.8, Price: 78.10€'),
(55.40, '2017-11-30', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Flower: Girasoles, Color: Amarillo, Price: 14.75€'),
(92.00, '2018-04-20', 'Decoration: Maceta, Material: PLASTIC, Price: 8.90€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Flower: Rosas, Color: Rojo, Price: 42.50€'),
(72.30, '2019-08-05', 'Decoration: Estantería, Material: WOOD, Price: 55.00€;Tree: Pino, Heigth: 3.5, Price: 25.75€'),
(118.60, '2020-01-30', 'Tree: Abeto, Heigth: 4.1, Price: 78.90€;Flower: Orquídeas, Color: Rosado, Price: 22.50€;Decoration: Maceta, Material: PLASTIC, Price: 17.20€'),
(67.80, '2015-02-15', 'Flower: Tulipanes, Color: Amarillo, Price: 18.90€;Tree: Pino, Heigth: 3.5, Price: 25.75€;Decoration: Jarrón, Material: WOOD, Price: 23.15€'),
(85.40, '2016-06-10', 'Tree: Abeto, Heigth: 4.1, Price: 63.70€;Flower: Rosas, Color: Rojo, Price: 15.50€;Decoration: Estantería, Material: PLASTIC, Price: 6.20€'),
(42.20, '2017-10-25', 'Decoration: Lámpara, Material: WOOD, Price: 30.00€;Flower: Girasoles, Color: Amarillo, Price: 14.75€'),
(92.60, '2018-03-05', 'Tree: Roble, Heigth: 4.2, Price: 39.20€;Decoration: Mesa, Material: WOOD, Price: 45.80€;Flower: Margaritas, Color: Blanco, Price: 7.60€'),
(107.90, '2019-07-20', 'Decoration: Sofá, Material: PLASTIC, Price: 65.30€;Flower: Jacintos, Color: Azul, Price: 16.20€;Tree: Arce, Heigth: 3.8, Price: 26.40€'),
(73.80, '2020-12-10', 'Tree: Abies, Heigth: 4.1, Price: 31.50€;Decoration: Maceta, Material: PLASTIC, Price: 15.90€;Flower: Tulipanes, Color: Amarillo, Price: 26.40€'),
(69.20, '2015-02-28', 'Flower: Orquídeas, Color: Rosado, Price: 30.50€;Decoration: Maceta, Material: PLASTIC, Price: 19.70€;Tree: Roble, Heigth: 4.2, Price: 18.00€'),
(84.30, '2016-06-10', 'Flower: Rosas, Color: Rojo, Price: 15.50€;Tree: Arce, Heigth: 3.8, Price: 40.60€;Decoration: Estantería, Material: WOOD, Price: 28.20€');


