CREATE TABLE users (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    phone VARCHAR
);

INSERT INTO users (first_name, last_name, email)
VALUES 
('Fayyadh', 'Hafizh', 'fayyadh@gmail.com');