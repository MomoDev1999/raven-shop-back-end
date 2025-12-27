-- Crea el usuario para la aplicación
CREATE USER user_springboot IDENTIFIED BY desarrollo2024;

-- Otorga privilegios básicos al usuario
GRANT CONNECT, RESOURCE, DBA TO user_springboot;
ALTER USER user_springboot QUOTA UNLIMITED ON USERS;
