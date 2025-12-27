INSERT IGNORE INTO persona (email, username, first_name, last_name, phone, date_of_birth, address, password)
VALUES
('admin@demo.cl','admin','Admin','Demo','999999999','1990-01-01','Av. Principal 123','admin123'),
('user1@demo.cl','user1','Usuario','Uno','911111111','1997-05-10','Calle 1 #100','pass123'),
('user2@demo.cl','user2','Usuario','Dos','922222222','1995-08-22','Calle 2 #200','pass123'),
('user3@demo.cl','user3','Usuario','Tres','933333333','1993-12-03','Calle 3 #300','pass123');

INSERT IGNORE INTO productos (title, price, description, category, image, createdAt, updatedAt)
VALUES
('Chaqueta Vintage',39990,'Chaqueta estilo vintage, ideal para invierno.','outerwear','https://i.pinimg.com/236x/72/2f/9b/722f9b8ec1cb22cc29dc7b93ec8a9639.jpg',NOW(),NOW()),
('Abrigo Aesthetic',49990,'Abrigo aesthetic con estilo urbano.','outerwear','https://i.pinimg.com/736x/6e/f8/7e/6ef87e133d626f4a463da4b7626e8ea9.jpg',NOW(),NOW()),
('Conjunto Minimal',29990,'Conjunto minimal para uso diario.','set','https://i.pinimg.com/736x/e1/f2/02/e1f202dff4e300be876afc77ce5c0ea1.jpg',NOW(),NOW());

INSERT IGNORE INTO ratings (id_producto, rate)
SELECT id, 4.5 FROM productos;
