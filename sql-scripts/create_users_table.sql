DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    active BOOLEAN DEFAULT true,
    email VARCHAR UNIQUE NOT NULL,
    phone VARCHAR,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

INSERT INTO users (first_name, last_name, email)
VALUES 
('Fayyadh', 'Hafizh', 'fayyadh@gmail.com');